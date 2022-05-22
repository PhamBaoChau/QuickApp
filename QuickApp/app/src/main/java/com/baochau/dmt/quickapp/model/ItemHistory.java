package com.baochau.dmt.quickapp.model;

public class ItemHistory {
    public int id;
    public int idAccount;
    public String name;
    public String result;
    public String time;

    public ItemHistory(int id, int idAccount, String name, String result, String time) {
        this.id = id;
        this.idAccount = idAccount;
        this.name = name;
        this.result = result;
        this.time = time;
    }
}
