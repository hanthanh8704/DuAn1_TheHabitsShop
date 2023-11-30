/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.classmodel;

import java.math.BigDecimal;

/**
 *
 * @author ThanhDat
 */
public class SanPhamChiTietRespose {

    private String ma;
    private int soluong;
    private int trangthai;

    private String idmauSac;
    private String idchatLieu;
    private String idkichCo;
    private String idnhanHieu;
    private String deGiay;
    private String dayGiay;
    private String kieuDang;
    private String idsanpham;
    private BigDecimal giaBan;

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public SanPhamChiTietRespose() {
    }

    public SanPhamChiTietRespose(String ma, int soluong, int trangthai, BigDecimal gia, String idmauSac, String idchatLieu, String idkichCo, String idnhanHieu, String deGiay, String dayGiay, String kieuDang, String idsanpham) {
        this.ma = ma;
        this.soluong = soluong;
        this.trangthai = trangthai;
        this.giaBan = gia;
        this.idmauSac = idmauSac;
        this.idchatLieu = idchatLieu;
        this.idkichCo = idkichCo;
        this.idnhanHieu = idnhanHieu;
        this.deGiay = deGiay;
        this.dayGiay = dayGiay;
        this.kieuDang = kieuDang;
        this.idsanpham = idsanpham;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

//    public float getGia() {
//        return gia;
//    }
//
//    public void setGia(float gia) {
//        this.gia = gia;
//    }

    public String getIdmauSac() {
        return idmauSac;
    }

    public void setIdmauSac(String idmauSac) {
        this.idmauSac = idmauSac;
    }

    public String getIdchatLieu() {
        return idchatLieu;
    }

    public void setIdchatLieu(String idchatLieu) {
        this.idchatLieu = idchatLieu;
    }

    public String getIdkichCo() {
        return idkichCo;
    }

    public void setIdkichCo(String idkichCo) {
        this.idkichCo = idkichCo;
    }

    public String getIdnhanHieu() {
        return idnhanHieu;
    }

    public void setIdnhanHieu(String idnhanHieu) {
        this.idnhanHieu = idnhanHieu;
    }

    public String getDeGiay() {
        return deGiay;
    }

    public void setDeGiay(String deGiay) {
        this.deGiay = deGiay;
    }

    public String getDayGiay() {
        return dayGiay;
    }

    public void setDayGiay(String dayGiay) {
        this.dayGiay = dayGiay;
    }

    public String getKieuDang() {
        return kieuDang;
    }

    public void setKieuDang(String kieuDang) {
        this.kieuDang = kieuDang;
    }

    public String getIdsanpham() {
        return idsanpham;
    }

    public void setIdsanpham(String idsanpham) {
        this.idsanpham = idsanpham;
    }

    @Override
    public String toString() {
        return "SanPhamChiTietRespose{" + "ma=" + ma + ", soluong=" + soluong + ", trangthai=" + trangthai + ", idmauSac=" + idmauSac + ", idchatLieu=" + idchatLieu + ", idkichCo=" + idkichCo + ", idnhanHieu=" + idnhanHieu + ", deGiay=" + deGiay + ", dayGiay=" + dayGiay + ", kieuDang=" + kieuDang + ", idsanpham=" + idsanpham + ", giaBan=" + giaBan + '}';
    }
    

   

}
