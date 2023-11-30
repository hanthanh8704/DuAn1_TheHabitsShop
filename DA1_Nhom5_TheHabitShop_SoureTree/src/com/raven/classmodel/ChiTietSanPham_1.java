/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.classmodel;

import java.math.BigDecimal;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class ChiTietSanPham_1 {

    private int stt;
    private UUID id;
    private String ma;
    private String ten;
    private int soLuong;
    private BigDecimal gia;
    private int trangThai;
    private int ghiChu;
    private MauSac id_mausac;
    private ChatLieu id_chatlieu;
    private KichCo id_kichCo;
    private NhanHieu id_nhanHieu;
    private DeGiay id_deGiay;
    private DayGiay id_daygiay;
    private KieuDang id_kieuDang;
    private SanPham id_sanPham;
    private NhanVien id_nhanVien;
    private KhachHang id_khachHang;

    public ChiTietSanPham_1() {
    }

    public ChiTietSanPham_1(int stt, UUID id, String ma, String ten, int soLuong, BigDecimal gia, int trangThai, int ghiChu, MauSac id_mausac, ChatLieu id_chatlieu, KichCo id_kichCo, NhanHieu id_nhanHieu, DeGiay id_deGiay, DayGiay id_daygiay, KieuDang id_kieuDang, SanPham id_sanPham, NhanVien id_nhanVien, KhachHang id_khachHang) {
        this.stt = stt;
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.soLuong = soLuong;
        this.gia = gia;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
        this.id_mausac = id_mausac;
        this.id_chatlieu = id_chatlieu;
        this.id_kichCo = id_kichCo;
        this.id_nhanHieu = id_nhanHieu;
        this.id_deGiay = id_deGiay;
        this.id_daygiay = id_daygiay;
        this.id_kieuDang = id_kieuDang;
        this.id_sanPham = id_sanPham;
        this.id_nhanVien = id_nhanVien;
        this.id_khachHang = id_khachHang;
    }

    public ChiTietSanPham_1(int stt, String ma, String ten, int soLuong, BigDecimal gia, int trangThai, int ghiChu, MauSac id_mausac, ChatLieu id_chatlieu, KichCo id_kichCo, NhanHieu id_nhanHieu, DeGiay id_deGiay, DayGiay id_daygiay, KieuDang id_kieuDang, SanPham id_sanPham, NhanVien id_nhanVien, KhachHang id_khachHang) {
        this.stt = stt;
        this.ma = ma;
        this.ten = ten;
        this.soLuong = soLuong;
        this.gia = gia;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
        this.id_mausac = id_mausac;
        this.id_chatlieu = id_chatlieu;
        this.id_kichCo = id_kichCo;
        this.id_nhanHieu = id_nhanHieu;
        this.id_deGiay = id_deGiay;
        this.id_daygiay = id_daygiay;
        this.id_kieuDang = id_kieuDang;
        this.id_sanPham = id_sanPham;
        this.id_nhanVien = id_nhanVien;
        this.id_khachHang = id_khachHang;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
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

    public MauSac getId_mausac() {
        return id_mausac;
    }

    public void setId_mausac(MauSac id_mausac) {
        this.id_mausac = id_mausac;
    }

    public ChatLieu getId_chatlieu() {
        return id_chatlieu;
    }

    public void setId_chatlieu(ChatLieu id_chatlieu) {
        this.id_chatlieu = id_chatlieu;
    }

    public KichCo getId_kichCo() {
        return id_kichCo;
    }

    public void setId_kichCo(KichCo id_kichCo) {
        this.id_kichCo = id_kichCo;
    }

    public NhanHieu getId_nhanHieu() {
        return id_nhanHieu;
    }

    public void setId_nhanHieu(NhanHieu id_nhanHieu) {
        this.id_nhanHieu = id_nhanHieu;
    }

    public DeGiay getId_deGiay() {
        return id_deGiay;
    }

    public void setId_deGiay(DeGiay id_deGiay) {
        this.id_deGiay = id_deGiay;
    }

    public DayGiay getId_daygiay() {
        return id_daygiay;
    }

    public void setId_daygiay(DayGiay id_daygiay) {
        this.id_daygiay = id_daygiay;
    }

    public KieuDang getId_kieuDang() {
        return id_kieuDang;
    }

    public void setId_kieuDang(KieuDang id_kieuDang) {
        this.id_kieuDang = id_kieuDang;
    }

    public SanPham getId_sanPham() {
        return id_sanPham;
    }

    public void setId_sanPham(SanPham id_sanPham) {
        this.id_sanPham = id_sanPham;
    }

    public NhanVien getId_nhanVien() {
        return id_nhanVien;
    }

    public void setId_nhanVien(NhanVien id_nhanVien) {
        this.id_nhanVien = id_nhanVien;
    }

    public KhachHang getId_khachHang() {
        return id_khachHang;
    }

    public void setId_khachHang(KhachHang id_khachHang) {
        this.id_khachHang = id_khachHang;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
