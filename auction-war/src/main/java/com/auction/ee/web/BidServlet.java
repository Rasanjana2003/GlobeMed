package com.auction.ee.web;

import com.auction.ee.ejb.jms.BidMessage;
import com.auction.ee.ejb.jms.BidSenderBean;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/submitBid")
public class BidServlet extends HttpServlet {

    @EJB
    private BidSenderBean bidSender;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            Long userId = Long.parseLong(req.getParameter("userId"));
            Long itemId = Long.parseLong(req.getParameter("itemId"));
            Double amount = Double.parseDouble(req.getParameter("amount"));

            BidMessage bidMsg = new BidMessage(userId, itemId, amount);
            bidSender.sendBid(bidMsg);

            resp.getWriter().write(" Bid submitted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(" Failed to submit bid: " + e.getMessage());
        }
    }
}
