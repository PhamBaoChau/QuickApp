package com.baochau.dmt.quickapp.model;

public class Account {
    public int id;
    public String name;
    public String email;
    public String password;

    public Account(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
