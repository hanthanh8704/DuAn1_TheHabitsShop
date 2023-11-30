/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.classmodel;

import java.util.Date;

/**
 *
 * @author ThanhDat
 */
public class SanPhamChiTiet_Dang {
// INSERT [dbo].[SanPhamChiTiet] ([id], [stt], [ma], [soluong], [trangthai], [gia], [ghichu], [id_mausac], [id_chatlieu], [id_kichco], [id_nhanhieu], [id_sanpham], [id_degiay], [id_daygiay], [id_anh], [id_kieudang], [id_nhanvien], [id_khachhang], [id_nguoitao], [ngaytao])
    private String id;    
    private int stt;
    private MauSac tenMauSac;
    private ChatLieu tenChatlieu;
    private KichCo tenKichCo;
    private NhanHieu tenNhanHieu;
    private DeGiay tenDeGiay;
    private DayGiay tenDaygiay;
    private KieuDang tenKieuDang;
    

    public SanPhamChiTiet_Dang(String ma, int soLuong, int trangThai, double giaBan, int ghiChu, String id_mausac, String id_chatlieu, String id_kichCo, String id_nhanHieu, String id_sanPham, String id_deGiay, String id_daygiay, String id_anh, String id_kieuDang, String id_nhanVien, String id_khachHang, String id_nguoiTao, Date ngayTao) {
        this.ma = ma;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.giaBan = giaBan;
        this.ghiChu = ghiChu;
        this.id_mausac = id_mausac;
        this.id_chatlieu = id_chatlieu;
        this.id_kichCo = id_kichCo;
        this.id_nhanHieu = id_nhanHieu;
        this.id_sanPham = id_sanPham;
        this.id_deGiay = id_deGiay;
        this.id_daygiay = id_daygiay;
        this.id_anh = id_anh;
        this.id_kieuDang = id_kieuDang;
        this.id_nhanVien = id_nhanVien;
        this.id_khachHang = id_khachHang;
        this.id_nguoiTao = id_nguoiTao;
        this.ngayTao = ngayTao;
    }
    
    private String ma;
    private int soLuong;
    private int trangThai;
    private double giaBan;
     private int ghiChu;
    private String id_mausac;
    private String id_chatlieu;
    private String id_kichCo;
    private String id_nhanHieu;
    private String id_sanPham;
    private String id_deGiay;
    private String id_daygiay;
    private String id_anh;
    private String id_kieuDang;
    private String id_nhanVien;
    private String id_khachHang;
    private String id_nguoiTao;
    private Date ngayTao;

    public SanPhamChiTiet_Dang(String ma, String ten, double giaBan, String tenmausac, String tenchatlieu) {
        this.ma = ma;
        this.ten = ten;
        this.giaBan = giaBan;
        this.tenmausac = tenmausac;
        this.tenchatlieu = tenchatlieu;
    }
    
    private String ten;
    private String tenmausac;
    private String tenchatlieu;
    private String tenkichCo;
    private String tennhanHieu;
    private String tendeGiay;
    private String tendaygiay;
    private String tenkieuDang;

    public SanPhamChiTiet_Dang() {
    }

    

    public MauSac getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(MauSac tenMauSac) {
        this.tenMauSac = tenMauSac;
    }
    
    

    public SanPhamChiTiet_Dang(String id, String ma, int soLuong, int trangThai, int ghiChu, double giaBan, String id_mausac, String id_chatlieu, String id_kichCo, String id_nhanHieu,String id_sanPham, String id_deGiay, String id_daygiay, String id_kieuDang, String id_nhanVien, String id_khachHang, String id_nguoiTao) {
        this.id = id;
        this.ma = ma;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
        this.giaBan = giaBan;
        this.id_mausac = id_mausac;
        this.id_chatlieu = id_chatlieu;
        this.id_kichCo = id_kichCo;
        this.id_nhanHieu = id_nhanHieu;
        this.id_sanPham = id_sanPham;
        this.id_deGiay = id_deGiay;
        this.id_daygiay = id_daygiay;
        this.id_kieuDang = id_kieuDang;
        this.id_nhanVien = id_nhanVien;
        this.id_khachHang = id_khachHang;
        this.id_nguoiTao = id_nguoiTao;
    }

   
    public SanPhamChiTiet_Dang(String ma, int soLuong, int trangThai, int ghiChu, String id_mausac, String id_kichCo, String id_nhanHieu, String id_sanPham, String id_deGiay, String id_daygiay, String id_kieuDang, String id_nhanVien, String id_khachHang, String id_nguoiTao) {
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
      
    }
    
     public String getId_anh() {
        return id_anh;
    }

    public void setId_anh(String id_anh) {
        this.id_anh = id_anh;
    }
    public SanPhamChiTiet_Dang(String ma, String ten, int soLuong, int trangThai, double giaBan, String tenmausac, String tenchatlieu, String tenkichCo, String tennhanHieu,String id_sanPham, String tendeGiay, String tendaygiay, String tenkieuDang) {
        this.ma = ma;
        this.ten = ten;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.giaBan = giaBan;
        this.tenmausac = tenmausac;
        this.tenchatlieu = tenchatlieu;
        this.tenkichCo = tenkichCo;
        this.tennhanHieu = tennhanHieu;
        this.id_sanPham = id_sanPham;
        this.tendeGiay = tendeGiay;
        this.tendaygiay = tendaygiay;
        this.tenkieuDang = tenkieuDang;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getId_sanPham() {
        return id_sanPham;
    }

    public void setId_sanPham(String id_sanPham) {
        this.id_sanPham = id_sanPham;
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
        return "SanPhamChiTiet{" + "id=" + id + ", stt=" + stt + ", tenMauSac=" + tenMauSac + ", tenChatlieu=" + tenChatlieu + ", tenKichCo=" + tenKichCo + ", tenNhanHieu=" + tenNhanHieu + ", tenDeGiay=" + tenDeGiay + ", tenDaygiay=" + tenDaygiay + ", tenKieuDang=" + tenKieuDang + ", ma=" + ma + ", soLuong=" + soLuong + ", trangThai=" + trangThai + ", giaBan=" + giaBan + ", ghiChu=" + ghiChu + ", id_mausac=" + id_mausac + ", id_chatlieu=" + id_chatlieu + ", id_kichCo=" + id_kichCo + ", id_nhanHieu=" + id_nhanHieu + ", id_sanPham=" + id_sanPham + ", id_deGiay=" + id_deGiay + ", id_daygiay=" + id_daygiay + ", id_anh=" + id_anh + ", id_kieuDang=" + id_kieuDang + ", id_nhanVien=" + id_nhanVien + ", id_khachHang=" + id_khachHang + ", id_nguoiTao=" + id_nguoiTao + ", ngayTao=" + ngayTao + ", ten=" + ten + ", tenmausac=" + tenmausac + ", tenchatlieu=" + tenchatlieu + ", tenkichCo=" + tenkichCo + ", tennhanHieu=" + tennhanHieu + ", tendeGiay=" + tendeGiay + ", tendaygiay=" + tendaygiay + ", tenkieuDang=" + tenkieuDang + '}';
    }

   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

}
