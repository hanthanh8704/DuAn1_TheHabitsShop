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
public class NhanVien {

    private UUID id;
    private String maNV;
    private String tenNV = "Lê Mỹ Nga";
    private ChucVu tenCV;
    private Date ngaySinh;
    private String cccd;
    private String email;
    private String diaChi;
    private String SDT;
    private int gioiTinh;
    private int trangThai = 1;
    private String matKhau = "khongbiet123";
    private String id_nguoiTao;

    public NhanVien(UUID id) {
        this.id = id;
    }

    public NhanVien(String maNV, String tenNV, ChucVu id_chucVu, Date ngaySinh, String cccd, String email, String diaChi, String SDT, int gioiTinh, int trangThai) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tenCV = id_chucVu;
        this.ngaySinh = ngaySinh;
        this.cccd = cccd;
        this.email = email;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
    }

    public NhanVien(UUID id, String maNV, String tenNV, ChucVu id_chucVu, Date ngaySinh, String cccd, String email, String diaChi, String SDT, int gioiTinh) {
        this.id = id;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tenCV = id_chucVu;
        this.ngaySinh = ngaySinh;
        this.cccd = cccd;
        this.email = email;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.gioiTinh = gioiTinh;
    }

    public NhanVien(UUID id, String maNV, String tenNV, ChucVu id_chucVu, Date ngaySinh, String cccd, String email, String diaChi, String SDT, int gioiTinh, String id_nguoiTao) {
        this.id = id;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tenCV = id_chucVu;
        this.ngaySinh = ngaySinh;
        this.cccd = cccd;
        this.email = email;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.gioiTinh = gioiTinh;
        this.id_nguoiTao = id_nguoiTao;
    }

    public NhanVien() {
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

    public ChucVu getId_chucVu() {
        return tenCV;
    }

    public void setId_chucVu(ChucVu id_chucVu) {
        this.tenCV = id_chucVu;
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

    public String getId_nguoiTao() {
        return id_nguoiTao;
    }

    public void setId_nguoiTao(String id_nguoiTao) {
        this.id_nguoiTao = id_nguoiTao;
    }

    public String hienThiGioiTinh() {
        if (gioiTinh == 1) {
            return "Nam";
        } else {
            return "Nữ";
        }
    }

    public String hienThiTrangThai() {
        if (trangThai == 1) {
            return "Đang làm";
        } else {
            return "Nghỉ làm";
        }
    }

  

  
}
