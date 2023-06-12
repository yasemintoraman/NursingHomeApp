package com.example.myapplication.model;

import java.util.Date;

public class Ilac {

    public String email;
    public Date tarih;
    public String ilacAdi;
    public String ilacSaati;
    public String aciklama;
    public String gun;

    public Ilac(String aciklama,String ilacAdi,String email, String gun,String ilacSaati) {
        this.email = email;
        this.ilacAdi = ilacAdi;
        this.aciklama = aciklama;
        this.ilacSaati = ilacSaati;
        this.gun = gun;

    }

}
