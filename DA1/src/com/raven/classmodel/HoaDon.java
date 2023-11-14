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
public class HoaDon {

    private UUID id;
    private String ma;
    private String tenNguoiNhan;
    private String diaChi;
    private Date ngayXacNhan;
    private BigDecimal phiVC;
    private Date ngayVanChuyen;
    private Date ngaytaohoadon;
    private Date ngaynhanHang;
    private Date ngaythanhtoan;
    private BigDecimal tongTien;
    private Integer tinhTrang;
    private String ghichu;
    private KhachHang idKhachHang;
    private NhanVien idNhanVien;

    public HoaDon() {
    }

    public HoaDon(UUID id, String ma, String tenNguoiNhan, String diaChi, Date ngayXacNhan, BigDecimal phiVC, Date ngayVanChuyen, Date ngaytaohoadon, Date ngaynhanHang, Date ngaythanhtoan, BigDecimal tongTien, Integer tinhTrang, String ghichu, KhachHang idKhachHang, NhanVien idNhanVien) {
        this.id = id;
        this.ma = ma;
        this.tenNguoiNhan = tenNguoiNhan;
        this.diaChi = diaChi;
        this.ngayXacNhan = ngayXacNhan;
        this.phiVC = phiVC;
        this.ngayVanChuyen = ngayVanChuyen;
        this.ngaytaohoadon = ngaytaohoadon;
        this.ngaynhanHang = ngaynhanHang;
        this.ngaythanhtoan = ngaythanhtoan;
        this.tongTien = tongTien;
        this.tinhTrang = tinhTrang;
        this.ghichu = ghichu;
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
    }

    public HoaDon(String ma, String tenNguoiNhan, String diaChi, Date ngayXacNhan, BigDecimal phiVC, Date ngayVanChuyen, Date ngaytaohoadon, Date ngaynhanHang, Date ngaythanhtoan, BigDecimal tongTien, Integer tinhTrang, String ghichu, KhachHang idKhachHang, NhanVien idNhanVien) {
        this.ma = ma;
        this.tenNguoiNhan = tenNguoiNhan;
        this.diaChi = diaChi;
        this.ngayXacNhan = ngayXacNhan;
        this.phiVC = phiVC;
        this.ngayVanChuyen = ngayVanChuyen;
        this.ngaytaohoadon = ngaytaohoadon;
        this.ngaynhanHang = ngaynhanHang;
        this.ngaythanhtoan = ngaythanhtoan;
        this.tongTien = tongTien;
        this.tinhTrang = tinhTrang;
        this.ghichu = ghichu;
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public Date getNgaytaohoadon() {
        return ngaytaohoadon;
    }

    public void setNgaytaohoadon(Date ngaytaohoadon) {
        this.ngaytaohoadon = ngaytaohoadon;
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

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgayXacNhan() {
        return ngayXacNhan;
    }

    public void setNgayXacNhan(Date ngayXacNhan) {
        this.ngayXacNhan = ngayXacNhan;
    }

    public BigDecimal getPhiVC() {
        return phiVC;
    }

    public void setPhiVC(BigDecimal phiVC) {
        this.phiVC = phiVC;
    }

    public Date getNgayVanChuyen() {
        return ngayVanChuyen;
    }

    public void setNgayVanChuyen(Date ngayVanChuyen) {
        this.ngayVanChuyen = ngayVanChuyen;
    }

    public Date getNgaynhanHang() {
        return ngaynhanHang;
    }

    public void setNgaynhanHang(Date ngaynhanHang) {
        this.ngaynhanHang = ngaynhanHang;
    }

    public Date getNgaythanhtoan() {
        return ngaythanhtoan;
    }

    public void setNgaythanhtoan(Date ngaythanhtoan) {
        this.ngaythanhtoan = ngaythanhtoan;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public Integer getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Integer tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public KhachHang getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(KhachHang idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public NhanVien getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(NhanVien idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public Object[] toDataRow() {
        return new Object[]{
            String.valueOf(id),
            ma,
            tenNguoiNhan,
            diaChi,
            ngayXacNhan,
            phiVC,
            ngaytaohoadon,
            ngayVanChuyen,
            ngaynhanHang,
            ngaythanhtoan,
            tongTien,
            tinhTrang,
            idKhachHang,
            idNhanVien
        };
    }
}
