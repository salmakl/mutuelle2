package com.example.brief3.models;

import java.time.LocalDate;
import java.util.Date;

public class Users {
    private String fname;
    private String lname;
    private String mail;
    private String adress;
    private LocalDate date;
    private String phone;
    private String id;
    private String company;


    public Users(String fname, String lname, String mail, String adress, LocalDate date, String phone, String id, String company) {
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
        this.adress = adress;
        this.date = date;
        this.phone = phone;
        this.id = id;
        this.company = company;
    }

    public Users() {
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

    public String getEmail() {
        return mail;
    }

    public void setEmail(String email) {
        this.mail = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
