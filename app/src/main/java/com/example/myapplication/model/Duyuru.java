package com.example.myapplication.model;

public class Duyuru {
    public String email;
    public String duyuru;
    public String downloadUrl;

    public Duyuru(String email, String duyuru, String downloadUrl){
        this.email = email;
        this.duyuru = duyuru;
        this.downloadUrl = downloadUrl;
    }
}
