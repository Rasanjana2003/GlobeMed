package com.auction.ee.ejb.session;

import jakarta.ejb.Stateful;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;

@Stateful
@SessionScoped
public class UserSessionBean implements Serializable {
    private Long userId;
    private String username;
    public void login(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }
    public void logout() {
        this.userId = null;
        this.username = null;
    }
    public boolean isLoggedIn() {
        return userId != null;
    }
    public Long getUserId() {
        return userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
