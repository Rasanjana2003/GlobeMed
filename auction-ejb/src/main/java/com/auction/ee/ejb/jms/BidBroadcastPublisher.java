package com.auction.ee.ejb.jms;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Topic;

@Stateless
public class BidBroadcastPublisher {

    @Resource(lookup = "jms/BidBroadcastTopic")
    private Topic broadcastTopic;

    @Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory connectionFactory;

    public void broadcastBid(BidMessage bidMessage) {
        try (JMSContext context = connectionFactory.createContext()) {
            context.createProducer().send(broadcastTopic, bidMessage);
            System.out.println("Broadcasted bid to topic: Rs. " + bidMessage.getAmount());
        }
    }
}
