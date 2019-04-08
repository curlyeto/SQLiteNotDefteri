package com.serifgungor.sqlitenotdefteri.Model;

public class Not {
    private int id;
    private String baslik;
    private String icerik;
    private String tarih;
    private String konu;

    public Not() {
    }

    public Not(int id, String baslik, String icerik, String tarih, String konu) {
        this.id = id;
        this.baslik = baslik;
        this.icerik = icerik;
        this.tarih = tarih;
        this.konu = konu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getKonu() {
        return konu;
    }

    public void setKonu(String konu) {
        this.konu = konu;
    }
}
