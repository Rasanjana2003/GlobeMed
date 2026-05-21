package com.auction.ee.ejb.jms;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Topic"),
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/BidBroadcastTopic")
        }
)
public class BidBroadcastSubscriberMDB implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                ObjectMessage objMsg = (ObjectMessage) message;
                BidMessage bidMsg = (BidMessage) objMsg.getObject();
                System.out.println(" Broadcast received: User " + bidMsg.getUserId()
                        + " placed bid of Rs. " + bidMsg.getAmount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
