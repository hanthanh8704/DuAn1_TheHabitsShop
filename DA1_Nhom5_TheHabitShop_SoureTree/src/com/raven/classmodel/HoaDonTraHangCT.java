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
public class HoaDonTraHangCT {

    private UUID id;
    private String ma;
    private Integer soluong;
    private BigDecimal gia_spkhimua;
    private HoaDonTraHang hdth;
    private SanPhamChiTiet spct;
    private UUID id_nguoitao;
    private Date ngayTao;
    private String idspct;

    public HoaDonTraHangCT() {
    }

    public HoaDonTraHangCT(UUID id, String ma, Integer soluong, BigDecimal gia_spkhimua, HoaDonTraHang hdth, SanPhamChiTiet spct, UUID id_nguoitao, Date ngayTao) {
        this.id = id;
        this.ma = ma;
        this.soluong = soluong;
        this.gia_spkhimua = gia_spkhimua;
        this.hdth = hdth;
        this.spct = spct;
        this.id_nguoitao = id_nguoitao;
        this.ngayTao = ngayTao;
    }

    public HoaDonTraHangCT(String ma, Integer soluong, BigDecimal gia_spkhimua  ) {
        this.ma = ma;
        this.soluong = soluong;
        this.gia_spkhimua = gia_spkhimua;
    }

    public HoaDonTraHangCT(String ma, Integer soluong, BigDecimal gia_spkhimua, HoaDonTraHang hdth, ChiTietSanPham ctsp, UUID id_nguoitao, Date ngayTao) {
        this.ma = ma;
        this.soluong = soluong;
        this.gia_spkhimua = gia_spkhimua;
        this.hdth = hdth;
        this.spct = spct;
        this.id_nguoitao = id_nguoitao;
        this.ngayTao = ngayTao;
    }

    public HoaDonTraHangCT(String ma, Integer soluong, BigDecimal gia_spkhimua, HoaDonTraHang hdth, ChiTietSanPham ctsp) {
        this.ma = ma;
        this.soluong = soluong;
        this.gia_spkhimua = gia_spkhimua;
        this.hdth = hdth;
        this.spct = spct;
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

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public BigDecimal getGia_spkhimua() {
        return gia_spkhimua;
    }

    public void setGia_spkhimua(BigDecimal gia_spkhimua) {
        this.gia_spkhimua = gia_spkhimua;
    }

    public HoaDonTraHang getHdth() {
        return hdth;
    }

    public void setHdth(HoaDonTraHang hdth) {
        this.hdth = hdth;
    }

    public SanPhamChiTiet getCtsp() {
        return spct;
    }

    public void setCtsp(SanPhamChiTiet ctsp) {
        this.spct = ctsp;
    }

    public UUID getId_nguoitao() {
        return id_nguoitao;
    }

    public void setId_nguoitao(UUID id_nguoitao) {
        this.id_nguoitao = id_nguoitao;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

}
