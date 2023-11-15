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
public class HoaDonChiTiet {

    private UUID id;
    private int soLuong;
    private BigDecimal soTienMat;
    private BigDecimal soTienThe;
    private BigDecimal tongTienCuaHDCT;
    private BigDecimal gia_SPCT;
    private String sdt;
    private UUID idKhachHang;
    private UUID idSPCT;
    private UUID hoaDon;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(UUID id, int soLuong, BigDecimal soTienMat, BigDecimal soTienThe, BigDecimal tongTienCuaHDCT, BigDecimal gia_SPCT, String sdt, UUID idKhachHang, UUID idSPCT, UUID hoaDon) {
        this.id = id;
        this.soLuong = soLuong;
        this.soTienMat = soTienMat;
        this.soTienThe = soTienThe;
        this.tongTienCuaHDCT = tongTienCuaHDCT;
        this.gia_SPCT = gia_SPCT;
        this.sdt = sdt;
        this.idKhachHang = idKhachHang;
        this.idSPCT = idSPCT;
        this.hoaDon = hoaDon;
    }

    public HoaDonChiTiet(int soLuong, BigDecimal soTienMat, BigDecimal soTienThe, BigDecimal tongTienCuaHDCT, BigDecimal gia_SPCT, String sdt, UUID idKhachHang, UUID idSPCT, UUID hoaDon) {
        this.soLuong = soLuong;
        this.soTienMat = soTienMat;
        this.soTienThe = soTienThe;
        this.tongTienCuaHDCT = tongTienCuaHDCT;
        this.gia_SPCT = gia_SPCT;
        this.sdt = sdt;
        this.idKhachHang = idKhachHang;
        this.idSPCT = idSPCT;
        this.hoaDon = hoaDon;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getSoTienMat() {
        return soTienMat;
    }

    public void setSoTienMat(BigDecimal soTienMat) {
        this.soTienMat = soTienMat;
    }

    public BigDecimal getSoTienThe() {
        return soTienThe;
    }

    public void setSoTienThe(BigDecimal soTienThe) {
        this.soTienThe = soTienThe;
    }

    public BigDecimal getTongTienCuaHDCT() {
        return tongTienCuaHDCT;
    }

    public void setTongTienCuaHDCT(BigDecimal tongTienCuaHDCT) {
        this.tongTienCuaHDCT = tongTienCuaHDCT;
    }

    public BigDecimal getGia_SPCT() {
        return gia_SPCT;
    }

    public void setGia_SPCT(BigDecimal gia_SPCT) {
        this.gia_SPCT = gia_SPCT;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public UUID getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(UUID idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public UUID getIdSPCT() {
        return idSPCT;
    }

    public void setIdSPCT(UUID idSPCT) {
        this.idSPCT = idSPCT;
    }

    public UUID getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(UUID hoaDon) {
        this.hoaDon = hoaDon;
    }

    public Object[] toDataRow() {
        return new Object[]{
            id,
            soLuong,
            soTienMat,
            soTienThe,
            tongTienCuaHDCT,
            gia_SPCT,
            sdt,
            idKhachHang,
            idSPCT
        };
    }
}
