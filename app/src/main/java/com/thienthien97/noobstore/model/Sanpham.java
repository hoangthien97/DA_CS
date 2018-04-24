package com.thienthien97.noobstore.model;

/**
 * Created by thien on 21/04/2018.
 */

public class Sanpham {
    public int IDsp;
    public String Tensp;
    public int Giasp;
    public String Hinhsp;
    public String Motasp;
    public int IDLoaisp;

    public Sanpham(int IDsp, String tensp, int giasp, String hinhsp, String motasp, int IDLoaisp) {
        this.IDsp = IDsp;
        Tensp = tensp;
        Giasp = giasp;
        Hinhsp = hinhsp;
        Motasp = motasp;
        this.IDLoaisp = IDLoaisp;
    }

    public int getIDsp() {
        return IDsp;
    }

    public void setIDsp(int IDsp) {
        this.IDsp = IDsp;
    }

    public String getTensp() {
        return Tensp;
    }

    public void setTensp(String tensp) {
        Tensp = tensp;
    }

    public int getGiasp() {
        return Giasp;
    }

    public void setGiasp(int giasp) {
        Giasp = giasp;
    }

    public String getHinhsp() {
        return Hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        Hinhsp = hinhsp;
    }

    public String getMotasp() {
        return Motasp;
    }

    public void setMotasp(String motasp) {
        Motasp = motasp;
    }

    public int getIDLoaisp() {
        return IDLoaisp;
    }

    public void setIDLoaisp(int IDLoaisp) {
        this.IDLoaisp = IDLoaisp;
    }
}
