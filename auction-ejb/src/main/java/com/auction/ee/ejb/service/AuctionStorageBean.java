package com.auction.ee.ejb.service;

import com.auction.ee.ejb.model.*;

import jakarta.ejb.Singleton;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Startup;

import java.util.ArrayList;
import java.util.List;

@Singleton
@Startup
public class AuctionStorageBean {
    private final List<User> users = new ArrayList<>();
    private final List<AuctionItem> items = new ArrayList<>();
    private final List<Bid> bids = new ArrayList<>();

    @Lock(LockType.WRITE)
    public void addUser(User user) {
        users.add(user);
    }
    @Lock(LockType.WRITE)
    public void addItem(AuctionItem item) {
        items.add(item);
    }
    @Lock(LockType.WRITE)
    public void addBid(Bid bid) {
        bids.add(bid);
    }
    @Lock(LockType.READ)
    public List<User> getUsers() {
        return new ArrayList<>(users);
    }
    @Lock(LockType.READ)
    public List<AuctionItem> getItems() {
        return new ArrayList<>(items);
    }
    @Lock(LockType.READ)
    public List<Bid> getBids() {
        return new ArrayList<>(bids);
    }
}
