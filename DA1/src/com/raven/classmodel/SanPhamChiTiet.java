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
 * @author ThanhDat
 */
public class SanPhamChiTiet {

    private int stt;
    private UUID id;
    private BigDecimal gia;
    private String ma;
    private String ten;
    private int soLuong;
    private int trangThai;
    private int ghiChu;
    private String id_mausac;
    private String id_chatlieu;
    private String id_kichCo;
    private String id_nhanHieu;
    private String id_deGiay;
    private String id_daygiay;
    private String id_kieuDang;
    private String id_nhanVien;
    private String id_khachHang;
    private String id_nguoiTao;
    private Date ngayTao;
    private String tenmausac;
    private String tenchatlieu;
    private String tenkichCo;
    private String tennhanHieu;
    private String tendeGiay;
    private String tendaygiay;
    private String tenkieuDang;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(int stt, UUID id, BigDecimal gia, String ma, String ten, int soLuong, int trangThai, int ghiChu, String id_mausac, String id_chatlieu, String id_kichCo, String id_nhanHieu, String id_deGiay, String id_daygiay, String id_kieuDang, String id_nhanVien, String id_khachHang, String id_nguoiTao, Date ngayTao, String tenmausac, String tenchatlieu, String tenkichCo, String tennhanHieu, String tendeGiay, String tendaygiay, String tenkieuDang) {
        this.stt = stt;
        this.id = id;
        this.gia = gia;
        this.ma = ma;
        this.ten = ten;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
        this.id_mausac = id_mausac;
        this.id_chatlieu = id_chatlieu;
        this.id_kichCo = id_kichCo;
        this.id_nhanHieu = id_nhanHieu;
        this.id_deGiay = id_deGiay;
        this.id_daygiay = id_daygiay;
        this.id_kieuDang = id_kieuDang;
        this.id_nhanVien = id_nhanVien;
        this.id_khachHang = id_khachHang;
        this.id_nguoiTao = id_nguoiTao;
        this.ngayTao = ngayTao;
        this.tenmausac = tenmausac;
        this.tenchatlieu = tenchatlieu;
        this.tenkichCo = tenkichCo;
        this.tennhanHieu = tennhanHieu;
        this.tendeGiay = tendeGiay;
        this.tendaygiay = tendaygiay;
        this.tenkieuDang = tenkieuDang;
    }

    public SanPhamChiTiet(String ma, int soLuong, int trangThai, int ghiChu, String id_mausac, String id_kichCo, String id_nhanHieu, String id_deGiay, String id_daygiay, String id_kieuDang, String id_nhanVien, String id_khachHang, String id_nguoiTao, Date ngayTao) {
        this.ma = ma;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
        this.id_mausac = id_mausac;
        this.id_kichCo = id_kichCo;
        this.id_nhanHieu = id_nhanHieu;
        this.id_deGiay = id_deGiay;
        this.id_daygiay = id_daygiay;
        this.id_kieuDang = id_kieuDang;
        this.id_nhanVien = id_nhanVien;
        this.id_khachHang = id_khachHang;
        this.id_nguoiTao = id_nguoiTao;
        this.ngayTao = ngayTao;
    }

    public SanPhamChiTiet(String ma, String ten, int soLuong, String tenmausac, String tenchatlieu, String tenkichCo, String tennhanHieu, String tendeGiay, String tendaygiay, String tenkieuDang) {
        this.ma = ma;
        this.ten = ten;
        this.soLuong = soLuong;
        this.tenmausac = tenmausac;
        this.tenchatlieu = tenchatlieu;
        this.tenkichCo = tenkichCo;
        this.tennhanHieu = tennhanHieu;
        this.tendeGiay = tendeGiay;
        this.tendaygiay = tendaygiay;
        this.tenkieuDang = tenkieuDang;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(int ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getId_mausac() {
        return id_mausac;
    }

    public void setId_mausac(String id_mausac) {
        this.id_mausac = id_mausac;
    }

    public String getId_chatlieu() {
        return id_chatlieu;
    }

    public void setId_chatlieu(String id_chatlieu) {
        this.id_chatlieu = id_chatlieu;
    }

    public String getId_kichCo() {
        return id_kichCo;
    }

    public void setId_kichCo(String id_kichCo) {
        this.id_kichCo = id_kichCo;
    }

    public String getId_nhanHieu() {
        return id_nhanHieu;
    }

    public void setId_nhanHieu(String id_nhanHieu) {
        this.id_nhanHieu = id_nhanHieu;
    }

    public String getId_deGiay() {
        return id_deGiay;
    }

    public void setId_deGiay(String id_deGiay) {
        this.id_deGiay = id_deGiay;
    }

    public String getId_daygiay() {
        return id_daygiay;
    }

    public void setId_daygiay(String id_daygiay) {
        this.id_daygiay = id_daygiay;
    }

    public String getId_kieuDang() {
        return id_kieuDang;
    }

    public void setId_kieuDang(String id_kieuDang) {
        this.id_kieuDang = id_kieuDang;
    }

    public String getId_nhanVien() {
        return id_nhanVien;
    }

    public void setId_nhanVien(String id_nhanVien) {
        this.id_nhanVien = id_nhanVien;
    }

    public String getId_khachHang() {
        return id_khachHang;
    }

    public void setId_khachHang(String id_khachHang) {
        this.id_khachHang = id_khachHang;
    }

    public String getId_nguoiTao() {
        return id_nguoiTao;
    }

    public void setId_nguoiTao(String id_nguoiTao) {
        this.id_nguoiTao = id_nguoiTao;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTenmausac() {
        return tenmausac;
    }

    public void setTenmausac(String tenmausac) {
        this.tenmausac = tenmausac;
    }

    public String getTenchatlieu() {
        return tenchatlieu;
    }

    public void setTenchatlieu(String tenchatlieu) {
        this.tenchatlieu = tenchatlieu;
    }

    public String getTenkichCo() {
        return tenkichCo;
    }

    public void setTenkichCo(String tenkichCo) {
        this.tenkichCo = tenkichCo;
    }

    public String getTennhanHieu() {
        return tennhanHieu;
    }

    public void setTennhanHieu(String tennhanHieu) {
        this.tennhanHieu = tennhanHieu;
    }

    public String getTendeGiay() {
        return tendeGiay;
    }

    public void setTendeGiay(String tendeGiay) {
        this.tendeGiay = tendeGiay;
    }

    public String getTendaygiay() {
        return tendaygiay;
    }

    public void setTendaygiay(String tendaygiay) {
        this.tendaygiay = tendaygiay;
    }

    public String getTenkieuDang() {
        return tenkieuDang;
    }

    public void setTenkieuDang(String tenkieuDang) {
        this.tenkieuDang = tenkieuDang;
    }

    @Override
    public String toString() {
        return "SanPhamChiTiet{" + "ma=" + ma + ", ten=" + ten + ", soLuong=" + soLuong + ", trangThai=" + trangThai + ", ghiChu=" + ghiChu + ", id_mausac=" + id_mausac + ", id_chatlieu=" + id_chatlieu + ", id_kichCo=" + id_kichCo + ", id_nhanHieu=" + id_nhanHieu + ", id_deGiay=" + id_deGiay + ", id_daygiay=" + id_daygiay + ", id_kieuDang=" + id_kieuDang + ", id_nhanVien=" + id_nhanVien + ", id_khachHang=" + id_khachHang + ", id_nguoiTao=" + id_nguoiTao + ", ngayTao=" + ngayTao + ", tenmausac=" + tenmausac + ", tenchatlieu=" + tenchatlieu + ", tenkichCo=" + tenkichCo + ", tennhanHieu=" + tennhanHieu + ", tendeGiay=" + tendeGiay + ", tendaygiay=" + tendaygiay + ", tenkieuDang=" + tenkieuDang + '}';
    }

}
