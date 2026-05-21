package com.auction.ee.ejb.jms;

import com.auction.ee.ejb.model.Bid;
import com.auction.ee.ejb.service.AuctionStorageBean;

import jakarta.annotation.Resource;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.*;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/AuctionBidQueue")
        }
)
public class BidProcessorMDB implements MessageListener {

    @Inject
    private AuctionStorageBean auctionStorage;

    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = "jms/BidBroadcastTopic")
    private Topic bidBroadcastTopic;

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                ObjectMessage objMsg = (ObjectMessage) message;
                BidMessage bidMsg = (BidMessage) objMsg.getObject();
                Bid bid = new Bid(bidMsg.getUserId(), bidMsg.getAmount(), System.currentTimeMillis());
                bid.setItemId(bidMsg.getItemId());
                bid.setUsername(bidMsg.getUsername());

                auctionStorage.addBid(bid);
                System.out.println(" Bid received and stored via MDB: " + bid.getUserId() + "  Rs" + bid.getAmount());


                jmsContext.createProducer().send(bidBroadcastTopic, bidMsg);
                System.out.println(" Broadcasted new bid to topic.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
