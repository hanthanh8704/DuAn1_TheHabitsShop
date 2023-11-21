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
public class KhachHang {

    private UUID ID;
    private String maKH;
    private int STT;
    private String tenKH;
    private boolean gioiTinh;
    private Date ngaySinh;
    private String sdt;
    private String email;
    private String diaChi;
    private UUID id_NguoiTao;
    private Date ngayTao;

    public KhachHang(int STT) {
        this.STT = STT;
    }

    public KhachHang(UUID ID) {
        this.ID = ID;
    }

    public KhachHang(int STT, String maKH, String tenKH, boolean gioiTinh, Date ngaySinh, String sdt, String email, String diaChi) {
        this.STT = STT;
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
    }

    public KhachHang( String maKH, String tenKH, boolean gioiTinh, Date ngaySinh, String sdt, String email, String diaChi, UUID id_NguoiTao) {

        this.maKH = maKH;

        this.tenKH = tenKH;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
        this.id_NguoiTao = id_NguoiTao;
 
    }

    public KhachHang(String maKH, String tenKH, boolean gioiTinh, Date ngaySinh, String sdt, String email, String diaChi) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
    }

    public KhachHang(String tenKH) {
        this.tenKH = tenKH;
    }
    

    public KhachHang() {
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public UUID getId_NguoiTao() {
        return id_NguoiTao;
    }

    public void setId_NguoiTao(UUID id_NguoiTao) {
        this.id_NguoiTao = id_NguoiTao;
    }

    public String getMaKH() {
        return maKH;
    }

    public UUID getID() {
        return ID;
    }

    public void setID(UUID ID) {
        this.ID = ID;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
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

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
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

}
