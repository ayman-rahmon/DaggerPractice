package com.tatsujin.daggerpractice.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    @Expose
    private int id ;
    @SerializedName("username")
    @Expose
    private String user_name ;
    @SerializedName("email")
    @Expose
    private String email ;
    @SerializedName("website")
    @Expose
    private String website ;

    public User() {
    }

    public User(int id, String user_name, String email, String website) {
        this.id = id;
        this.user_name = user_name;
        this.email = email;
        this.website = website;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
