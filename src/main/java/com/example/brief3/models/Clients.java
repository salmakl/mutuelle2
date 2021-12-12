package com.example.brief3.models;

import java.util.Date;


public class Clients {
    private int id;
    private String badge;
    private String fname;
    private String lname;
    private String cin;
    private String passport;
    private String phone;
    private String email;
    private String address;
    private Date created_at;
    private Date date;
    private String company;





    public Clients(int id, String badge, String fname, String lname, String phone, String email, String address, Date date, String company_name) {
        this.id = id;
        this.badge = badge;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.date = date;
        this.company = company_name;
    }

    public Clients() {

    }



/*    public Clients(int id, String badge, String fname, String lname, String cin, String passport, String phone, String email, String address, Date created_at, Date date, String company) {
        this.id = id;
        this.badge = badge;
        this.fname = fname;
        this.lname = lname;
        this.cin = cin;
        this.passport = passport;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.created_at = created_at;
        this.date = date;
        this.company = company;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


}


