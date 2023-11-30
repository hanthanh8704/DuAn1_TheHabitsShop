/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.classmodel;

/**
 *
 * @author ThanhDat
 */
public class SanPham {
    String id;
    private String maSP; 
    private String tenSP; 
    private int soLuong;
    private int trangThai;

    public SanPham() {
    }

    public SanPham(String id, String maSP, String tenSP, int soLuong) {
        this.id = id;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
    }
     public SanPham(String id, String maSP, String tenSP) {
        this.id = id;
        this.maSP = maSP;
        this.tenSP = tenSP;
    
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    

    public SanPham(String maSP, String tenSP) {
        this.maSP = maSP;
        this.tenSP = tenSP;
    }

    
    public SanPham(String maSP, String tenSP, int soLuong, int trangThai) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "SanPham{" + "id=" + id + ", maSP=" + maSP + ", tenSP=" + tenSP + ", soLuong=" + soLuong + ", trangThai=" + trangThai + '}';
    }

    
    
    
}
