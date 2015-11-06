package com.san.pro.BookStore.service;

import org.joda.time.Instant;

/**
 * Created by sandeepkumar.s on 11/6/2015.
 */
public class Service {

    private Long createdAt;

    private Long updatedAt;

    private Long createdBy;

    private Long updatedBy;

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        createdAt = Long.valueOf((new Instant()).getMillis());
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        updatedAt = Long.valueOf((new Instant()).getMillis());
        this.updatedAt = updatedAt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }
}
