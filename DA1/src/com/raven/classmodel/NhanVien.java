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
public class NhanVien {

    private UUID id;
    private int STT;
    private String maNV;
    private String tenNV;
    private ChucVu id_chucVu;
    private boolean gioiTinh;
    private Date ngaySinh;
    private String cccd;
    private String email;
    private String diaChi;
    private String SDT;
    private String matKhau;

    public NhanVien() {
    }

    public NhanVien(String maNV, String matKhau, ChucVu id_chucVu) {
        this.maNV = maNV;
        this.matKhau = matKhau;
        this.id_chucVu = id_chucVu;
    }

    public NhanVien(UUID id, int STT, String maNV, String tenNV, ChucVu id_chucVu, boolean gioiTinh, Date ngaySinh, String cccd, String email, String diaChi, String SDT, String matKhau) {
        this.id = id;
        this.STT = STT;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.id_chucVu = id_chucVu;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.cccd = cccd;
        this.email = email;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.matKhau = matKhau;
    }

    public NhanVien(UUID id, int STT, String maNV, String tenNV, ChucVu id_chucVu, boolean gioiTinh, Date ngaySinh, String cccd, String email, String diaChi, String SDT) {
        this.id = id;
        this.STT = STT;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.id_chucVu = id_chucVu;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.cccd = cccd;
        this.email = email;
        this.diaChi = diaChi;
        this.SDT = SDT;
    }

    public NhanVien(UUID id) {
        this.id = id;
    }

    public NhanVien(int STT, String maNV, String tenNV, ChucVu id_chucVu, boolean gioiTinh, Date ngaySinh, String cccd, String email, String diaChi, String SDT) {
        this.STT = STT;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.id_chucVu = id_chucVu;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.cccd = cccd;
        this.email = email;
        this.diaChi = diaChi;
        this.SDT = SDT;
    }

    public NhanVien(String maNV, String tenNV, ChucVu id_chucVu, boolean gioiTinh, Date ngaySinh, String cccd, String email, String diaChi, String SDT) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.id_chucVu = id_chucVu;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.cccd = cccd;
        this.email = email;
        this.diaChi = diaChi;
        this.SDT = SDT;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
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
        return id_chucVu;
    }

    public void setId_chucVu(ChucVu id_chucVu) {
        this.id_chucVu = id_chucVu;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
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

}
