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
    private String ma;
    private int soLuong;
    private BigDecimal soTienMat;
    private BigDecimal soTienThe;
    private BigDecimal tongTienCuaHDCT;
    private BigDecimal gia_SPCT;
    private String sdt;
    private KhachHang idKhachHang;
    private SanPhamChiTiet idSPCT;
    private HoaDon hoaDon;
    private UUID id_nguoitao;

    // private String mahdct;
    private String mahd;
    private String maspct;
    private String id_nguoitaoString;
    private String tensp;
    private Integer xoa_tt;

    public HoaDonChiTiet(String ma, int soLuong, String mahd, String maspct, String id_nguoitaoString, Integer xoa_tt) {
        this.ma = ma;
        this.soLuong = soLuong;
        this.mahd = mahd;
        this.maspct = maspct;
        this.id_nguoitaoString = id_nguoitaoString;
        this.xoa_tt = xoa_tt;
    }

    public Integer getXoa_tt() {
        return xoa_tt;
    }

    public void setXoa_tt(Integer xoa_tt) {
        this.xoa_tt = xoa_tt;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getMaspct() {
        return maspct;
    }

    public void setMaspct(String maspct) {
        this.maspct = maspct;
    }

    public String getId_nguoitaoString() {
        return id_nguoitaoString;
    }

    public void setId_nguoitaoString(String id_nguoitaoString) {
        this.id_nguoitaoString = id_nguoitaoString;
    }

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String ma, int soLuong, SanPhamChiTiet idSPCT, HoaDon hoaDon, UUID id_nguoitao) {
        this.ma = ma;
        this.soLuong = soLuong;
        this.idSPCT = idSPCT;
        this.hoaDon = hoaDon;
        this.id_nguoitao = id_nguoitao;
    }

    public HoaDonChiTiet(UUID id, String ma, int soLuong, BigDecimal soTienMat, BigDecimal soTienThe,
            BigDecimal tongTienCuaHDCT, BigDecimal gia_SPCT, String sdt, KhachHang idKhachHang, SanPhamChiTiet idSPCT, HoaDon hoaDon, UUID id_nguoitao) {
        this.id = id;
        this.ma = ma;
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

    public HoaDonChiTiet(String ma, int soLuong, BigDecimal soTienMat, BigDecimal soTienThe, BigDecimal tongTienCuaHDCT,
            BigDecimal gia_SPCT, String sdt, KhachHang idKhachHang, SanPhamChiTiet idSPCT, HoaDon hoaDon) {
        this.ma = ma;
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

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
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

    public KhachHang getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(KhachHang idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public SanPhamChiTiet getIdSPCT() {
        return idSPCT;
    }

    public void setIdSPCT(SanPhamChiTiet idSPCT) {
        this.idSPCT = idSPCT;
    }

    public UUID getId_nguoitao() {
        return id_nguoitao;
    }

    public void setId_nguoitao(UUID id_nguoitao) {
        this.id_nguoitao = id_nguoitao;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }
}
