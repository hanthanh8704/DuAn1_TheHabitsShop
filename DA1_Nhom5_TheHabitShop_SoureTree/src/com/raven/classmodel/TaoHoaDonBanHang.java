/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.classmodel;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class TaoHoaDonBanHang {

    private String mahd;
    private Date ngaytaohd;
    private String id_nv;
    private String id_kh;
    private String hinhthuc;
    private Integer trangThai;

    public TaoHoaDonBanHang() {
    }

    public TaoHoaDonBanHang(String mahd, Date ngaytaohd, String id_nv, String id_kh, String hinhthuc, Integer trangThai) {
        this.mahd = mahd;
        this.ngaytaohd = ngaytaohd;
        this.id_nv = id_nv;
        this.id_kh = id_kh;
        this.hinhthuc = hinhthuc;
        this.trangThai = trangThai;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public Date getNgaytaohd() {
        return ngaytaohd;
    }

    public void setNgaytaohd(Date ngaytaohd) {
        this.ngaytaohd = ngaytaohd;
    }

    public String getId_nv() {
        return id_nv;
    }

    public void setId_nv(String id_nv) {
        this.id_nv = id_nv;
    }

    public String getId_kh() {
        return id_kh;
    }

    public void setId_kh(String id_kh) {
        this.id_kh = id_kh;
    }

    public String getHinhthuc() {
        return hinhthuc;
    }

    public void setHinhthuc(String hinhthuc) {
        this.hinhthuc = hinhthuc;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "TaoHoaDonBanHang{" + "mahd=" + mahd + ", ngaytaohd=" + ngaytaohd + ", id_nv=" + id_nv + ", id_kh=" + id_kh + ", hinhthuc=" + hinhthuc + ", trangThai=" + trangThai + '}';
    }

}
