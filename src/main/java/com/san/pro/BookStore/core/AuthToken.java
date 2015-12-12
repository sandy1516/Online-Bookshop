package com.san.pro.BookStore.core;

/**
 * Created by sandeepkumar.s on 11/27/2015.
 */
public class AuthToken {
    private Long userId;
    public AuthToken(Long userId) {
        this.userId = userId;
    }
    public Long getUserId() {
        return userId;
    }
}
