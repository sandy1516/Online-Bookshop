package com.san.pro.BookStore.model;

/**
 * Created by Administrator on 21-10-2015.
 */
public enum Role {

    admin("admin"),
    user("user");

    String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static Role fromString(String role) {
        if(role != null) {
            for(Role roleValues : Role.values()) {
                return roleValues;
            }
        }
        return null;
    }

}
