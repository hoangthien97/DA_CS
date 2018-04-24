package com.thienthien97.noobstore.model;

/**
 * Created by thien on 08/04/2018.
 */

public class Loaihang {
    public int Idloai;
    public String Tenloaihang;
    public String Anhloaihang;

    public Loaihang(int idloai, String tenloaihang, String anhloaihang) {
        Idloai = idloai;
        Tenloaihang = tenloaihang;
        Anhloaihang = anhloaihang;
    }

    public int getIdloai() {
        return Idloai;
    }

    public void setIdloai(int idloai) {
        Idloai = idloai;
    }

    public String getTenloaihang() {
        return Tenloaihang;
    }

    public void setTenloaihang(String tenloaihang) {
        Tenloaihang = tenloaihang;
    }

    public String getAnhloaihang() {
        return Anhloaihang;
    }

    public void setAnhloaihang(String anhloaihang) {
        Anhloaihang = anhloaihang;
    }
}
