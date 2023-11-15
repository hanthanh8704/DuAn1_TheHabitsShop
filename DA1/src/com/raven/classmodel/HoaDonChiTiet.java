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

    private int stt;
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
    private UUID id_nguoitao;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int stt, int soLuong, BigDecimal soTienMat, BigDecimal soTienThe, BigDecimal tongTienCuaHDCT, BigDecimal gia_SPCT, String sdt, UUID idKhachHang, UUID idSPCT, UUID hoaDon, UUID id_nguoitao) {
        this.stt = stt;
        this.soLuong = soLuong;
        this.soTienMat = soTienMat;
        this.soTienThe = soTienThe;
        this.tongTienCuaHDCT = tongTienCuaHDCT;
        this.gia_SPCT = gia_SPCT;
        this.sdt = sdt;
        this.idKhachHang = idKhachHang;
        this.idSPCT = idSPCT;
        this.hoaDon = hoaDon;
        this.id_nguoitao = id_nguoitao;
    }

    public UUID getId_nguoitao() {
        return id_nguoitao;
    }

    public void setId_nguoitao(UUID id_nguoitao) {
        this.id_nguoitao = id_nguoitao;
    }

    public HoaDonChiTiet(int stt, UUID id, int soLuong, BigDecimal soTienMat, BigDecimal soTienThe, BigDecimal tongTienCuaHDCT, BigDecimal gia_SPCT, String sdt, UUID idKhachHang, UUID idSPCT, UUID hoaDon, UUID id_nguoitao) {
        this.stt = stt;
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
        this.id_nguoitao = id_nguoitao;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
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
