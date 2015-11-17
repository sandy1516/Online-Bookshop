package com.san.pro.BookStore.model;

import com.google.common.base.Objects;

/**
 * Created by Administrator on 21-10-2015.
 */
public class User extends Model {

    private String firstName;
    private String middleName;
    private String lastName;
    private String emailId;
    private long mobile;
    private String userName;
    private String password;
    private Role role;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void merge(User user) {
        if(!Objects.equal(null, user.getFirstName())) {
            this.setFirstName(user.getFirstName());
        }
        if(!Objects.equal(null, user.getMiddleName())) {
            this.setMiddleName(user.getMiddleName());
        }
        if(!Objects.equal(null, user.getLastName())) {
            this.setLastName(user.getLastName());
        }
        if(!Objects.equal(null, user.getEmailId())) {
            this.setEmailId(user.getEmailId());
        }
        if(!Objects.equal(null, user.getMobile())) {
            this.setMobile(user.getMobile());
        }
        if(!Objects.equal(null, user.getUserName())) {
            this.setUserName(user.getUserName());
        }
        if(!Objects.equal(null, user.getPassword())) {
            this.setPassword(user.getPassword());
        }
        if(!Objects.equal(null, user.getRole())) {
            this.setRole(user.getRole());
        }

    }
}
