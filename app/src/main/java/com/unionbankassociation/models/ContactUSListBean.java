package com.unionbankassociation.models;

import java.io.Serializable;

/*
 *
 * Generic Bean for selection actiivty
 *
 * */
public class ContactUSListBean implements Serializable {
    String Portfolio;
    String Name;
    String Place;
    String Mobile;
    String Email;

    public String getPortfolio() {
        return Portfolio;
    }

    public void setPortfolio(String portfolio) {
        Portfolio = portfolio;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
