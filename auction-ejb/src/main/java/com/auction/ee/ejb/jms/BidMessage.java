package com.auction.ee.ejb.jms;

import java.io.Serializable;

public class BidMessage implements Serializable {
    private Long userId;
    private Long itemId;
    private Double amount;
    private String username;

    public BidMessage() {
    }

    public BidMessage(Long userId, Long itemId, Double amount) {
        this.userId = userId;
        this.itemId = itemId;
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
