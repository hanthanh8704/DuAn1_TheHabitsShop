/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.classmodel;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author MSI 15
 */
public class NhanVienRespose {

    private UUID id;
    private String maNV;
    private String tenNV;
    private String id_chucvu;
    private Date ngaySinh;
    private String cccd;
    private String email;
    private String diaChi;
    private String SDT;
    private int gioiTinh;
    private int trangThai = 1;
    private String matKhau = "khongbiet123";

    public NhanVienRespose() {
    }

    public NhanVienRespose(UUID id, String maNV, String tenNV, String id_chucvu, Date ngaySinh, String cccd, String email, String diaChi, String SDT, int gioiTinh, int trangThai, String matKhau) {
        this.id = id;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.id_chucvu = id_chucvu;
        this.ngaySinh = ngaySinh;
        this.cccd = cccd;
        this.email = email;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
        this.matKhau = matKhau;
    }

    public NhanVienRespose(UUID id, String maNV, String tenNV, String id_chucvu, Date ngaySinh, String cccd, String email, String diaChi, String SDT, int gioiTinh) {
        this.id = id;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.id_chucvu = id_chucvu;
        this.ngaySinh = ngaySinh;
        this.cccd = cccd;
        this.email = email;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.gioiTinh = gioiTinh;
    }

    public NhanVienRespose(String maNV, String tenNV, Date ngaySinh, String cccd, String email, String diaChi, String SDT, int gioiTinh) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.ngaySinh = ngaySinh;
        this.cccd = cccd;
        this.email = email;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.gioiTinh = gioiTinh;
    }

    public NhanVienRespose(String maNV, String tenNV, String id_chucvu, Date ngaySinh, String cccd, String email, String diaChi, String SDT, int gioiTinh, int trangthai) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.id_chucvu = id_chucvu;
        this.ngaySinh = ngaySinh;
        this.cccd = cccd;
        this.email = email;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangthai;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getId_chucvu() {
        return id_chucvu;
    }

    public void setId_chucvu(String id_chucvu) {
        this.id_chucvu = id_chucvu;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

}
