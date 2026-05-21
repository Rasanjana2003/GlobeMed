package com.auction.ee.web;

import com.auction.ee.ejb.model.AuctionItem;
import com.auction.ee.ejb.model.Bid;
import com.auction.ee.ejb.jms.BidMessage;
import com.auction.ee.ejb.service.AuctionStorageBean;

import com.auction.ee.ejb.session.UserSessionBean;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import jakarta.jms.Topic;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

@Path("/auction")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuctionResource {

    @Inject
    private UserSessionBean userSession;

    private AuctionStorageBean storage;
    private ConnectionFactory connectionFactory;
    private Queue auctionBidQueue;
    private Topic bidBroadcastTopic;

    @PostConstruct
    public void init() {
        try {
            Context ctx = new InitialContext();
            storage = (AuctionStorageBean) ctx.lookup("java:global/auction-ear/ejb-module/AuctionStorageBean");


            connectionFactory = (ConnectionFactory) ctx.lookup("jms/__defaultConnectionFactory");
            auctionBidQueue = (Queue) ctx.lookup("jms/AuctionBidQueue");
            bidBroadcastTopic = (Topic) ctx.lookup("jms/BidBroadcastTopic");

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/items")
    public List<AuctionItem> getItems() {
        return storage.getItems();
    }

    @GET
    @Path("/bids")
    public List<Bid> getBids() {
        return storage.getBids();
    }

    @POST
    @Path("/item")
    public Response createAuctionItem(AuctionItem item) {
        try {
            storage.addItem(item);
            return Response.status(Response.Status.CREATED).entity("Auction item created").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error creating item: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("userId") Long userId, @FormParam("username") String username) {
        if (userId == null || username == null || username.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid login data").build();
        }

        userSession.login(userId, username);
        return Response.ok("Login successful").build();
    }


    @POST
    @Path("/bid")
    public Response placeBid(BidMessage bidMsg) {
        if (!userSession.isLoggedIn()) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("You must be logged in to place a bid.").build();
        }

        bidMsg.setUserId(userSession.getUserId());
        bidMsg.setUsername(userSession.getUsername());

        try (JMSContext context = connectionFactory.createContext()) {
            context.createProducer().send(auctionBidQueue, bidMsg);
            context.createProducer().send(bidBroadcastTopic, bidMsg);
            return Response.ok("Bid placed and broadcast successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to place bid: " + e.getMessage()).build();
        }
    }

}
