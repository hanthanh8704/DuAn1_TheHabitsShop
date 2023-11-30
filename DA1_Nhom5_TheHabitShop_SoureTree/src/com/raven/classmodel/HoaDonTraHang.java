/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.classmodel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class HoaDonTraHang {

    private UUID id;
    private String ma;
    private BigDecimal tienHoanTra;
    private Date ngayTraHang;
    private HoaDon hoaDon;
    private NhanVien nhanVien;
    private KhachHang khachHang;
    private UUID id_nguoitao;
    private Date ngaytao;

    public HoaDonTraHang() {
    }

    public HoaDonTraHang(UUID id, String ma, BigDecimal tienHoanTra, Date ngayTraHang, HoaDon hoaDon, NhanVien nhanVien, KhachHang khachHang, UUID id_nguoitao, Date ngaytao) {
        this.id = id;
        this.ma = ma;
        this.tienHoanTra = tienHoanTra;
        this.ngayTraHang = ngayTraHang;
        this.hoaDon = hoaDon;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.id_nguoitao = id_nguoitao;
        this.ngaytao = ngaytao;
    }

    public HoaDonTraHang(String ma, BigDecimal tienHoanTra, Date ngayTraHang, HoaDon hoaDon, NhanVien nhanVien, KhachHang khachHang, UUID id_nguoitao, Date ngaytao) {
        this.ma = ma;
        this.tienHoanTra = tienHoanTra;
        this.ngayTraHang = ngayTraHang;
        this.hoaDon = hoaDon;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.id_nguoitao = id_nguoitao;
        this.ngaytao = ngaytao;
    }

    public HoaDonTraHang(String ma, BigDecimal tienHoanTra, Date ngayTraHang, HoaDon hoaDon, NhanVien nhanVien, KhachHang khachHang) {
        this.ma = ma;
        this.tienHoanTra = tienHoanTra;
        this.ngayTraHang = ngayTraHang;
        this.hoaDon = hoaDon;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public BigDecimal getTienHoanTra() {
        return tienHoanTra;
    }

    public void setTienHoanTra(BigDecimal tienHoanTra) {
        this.tienHoanTra = tienHoanTra;
    }

    public Date getNgayTraHang() {
        return ngayTraHang;
    }

    public void setNgayTraHang(Date ngayTraHang) {
        this.ngayTraHang = ngayTraHang;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public UUID getId_nguoitao() {
        return id_nguoitao;
    }

    public void setId_nguoitao(UUID id_nguoitao) {
        this.id_nguoitao = id_nguoitao;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }
    
    
}
