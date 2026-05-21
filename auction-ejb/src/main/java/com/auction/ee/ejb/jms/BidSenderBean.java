package com.auction.ee.ejb.jms;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;

@Stateless
public class BidSenderBean {

    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = "jms/AuctionBidQueue")
    private Queue auctionBidQueue;

    public void sendBid(BidMessage bidMsg) {
        if (jmsContext == null) {
            System.err.println(" JMSContext injection failed (null)");
            return;
        }
        if (auctionBidQueue == null) {
            System.err.println(" Queue injection failed (null)");
            return;
        }
        try {
            jmsContext.createProducer().send(auctionBidQueue, bidMsg);
            System.out.println(" BidMessage sent to queue: " +
                    "UserId=" + bidMsg.getUserId() +
                    ", ItemId=" + bidMsg.getItemId() +
                    ", Amount=" + bidMsg.getAmount());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
