/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.classmodel;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class LichSuHoaDon {

    private UUID id;
    private Integer stt;
    private String ma;
    private String liDoHuy;
    private UUID id_nhanVien;
    private UUID id_hoaDon;
    private UUID id_nguoitao;
    private Date ngayTao;
    private HoaDon hoaDon;
    private HoaDonChiTiet hdct;
    private SanPham sp;
    private ChiTietSanPham spct;

    public LichSuHoaDon() {
    }

    public LichSuHoaDon(UUID id, Integer stt, String ma, String liDoHuy, UUID id_nhanVien, UUID id_hoaDon, UUID id_nguoitao, Date ngayTao, HoaDon hoaDon, HoaDonChiTiet hdct, SanPham sp, ChiTietSanPham spct) {
        this.id = id;
        this.stt = stt;
        this.ma = ma;
        this.liDoHuy = liDoHuy;
        this.id_nhanVien = id_nhanVien;
        this.id_hoaDon = id_hoaDon;
        this.id_nguoitao = id_nguoitao;
        this.ngayTao = ngayTao;
        this.hoaDon = hoaDon;
        this.hdct = hdct;
        this.sp = sp;
        this.spct = spct;
    }

    public LichSuHoaDon(Integer stt, String ma, String liDoHuy, UUID id_nhanVien, UUID id_hoaDon, UUID id_nguoitao, HoaDon hoaDon, HoaDonChiTiet hdct, SanPham sp, ChiTietSanPham spct) {
        this.stt = stt;
        this.ma = ma;
        this.liDoHuy = liDoHuy;
        this.id_nhanVien = id_nhanVien;
        this.id_hoaDon = id_hoaDon;
        this.id_nguoitao = id_nguoitao;
        this.hoaDon = hoaDon;
        this.hdct = hdct;
        this.sp = sp;
        this.spct = spct;
    }

    public LichSuHoaDon(Integer stt, String ma, String liDoHuy, UUID id_nhanVien, UUID id_hoaDon, UUID id_nguoitao, Date ngayTao, HoaDon hoaDon, HoaDonChiTiet hdct, SanPham sp, ChiTietSanPham spct) {
        this.stt = stt;
        this.ma = ma;
        this.liDoHuy = liDoHuy;
        this.id_nhanVien = id_nhanVien;
        this.id_hoaDon = id_hoaDon;
        this.id_nguoitao = id_nguoitao;
        this.ngayTao = ngayTao;
        this.hoaDon = hoaDon;
        this.hdct = hdct;
        this.sp = sp;
        this.spct = spct;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getLiDoHuy() {
        return liDoHuy;
    }

    public void setLiDoHuy(String liDoHuy) {
        this.liDoHuy = liDoHuy;
    }

    public UUID getId_nhanVien() {
        return id_nhanVien;
    }

    public void setId_nhanVien(UUID id_nhanVien) {
        this.id_nhanVien = id_nhanVien;
    }

    public UUID getId_hoaDon() {
        return id_hoaDon;
    }

    public void setId_hoaDon(UUID id_hoaDon) {
        this.id_hoaDon = id_hoaDon;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public HoaDonChiTiet getHdct() {
        return hdct;
    }

    public void setHdct(HoaDonChiTiet hdct) {
        this.hdct = hdct;
    }

    public SanPham getSp() {
        return sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }

    public ChiTietSanPham getSpct() {
        return spct;
    }

    public void setSpct(ChiTietSanPham spct) {
        this.spct = spct;
    }

    public UUID getId_nguoitao() {
        return id_nguoitao;
    }

    public void setId_nguoitao(UUID id_nguoitao) {
        this.id_nguoitao = id_nguoitao;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

}
