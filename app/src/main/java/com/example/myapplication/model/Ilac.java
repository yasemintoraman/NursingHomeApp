package com.example.myapplication.model;

import java.util.Date;

public class Ilac {

    private Date tarih;
    private String ilacAdi;
    private String ilacSaati;
    private String aciklama;
    private int verilecekGun;

    public Ilac(Date tarih, String ilacAdi, String ilacSaati, String aciklama, int verilecekGun) {
        this.tarih = tarih;
        this.ilacAdi = ilacAdi;
        this.ilacSaati = ilacSaati;
        this.aciklama = aciklama;
        this.verilecekGun = verilecekGun;
    }
}
