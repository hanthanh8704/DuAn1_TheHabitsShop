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
public class ThanhToan {

    private UUID id;
    private String ma;
    private BigDecimal thanhtoan;
    private String magd;
    private Integer trangthai;
    private String ghichu;
    private UUID id_nguoitao;
    private BigDecimal tongtien;
    private Date ngaytao;

    public ThanhToan() {
    }

    public ThanhToan(UUID id, String ma, BigDecimal thanhtoan, String magd, Integer trangthai, String ghichu, UUID id_nguoitao, BigDecimal tongtien, Date ngaytao) {
        this.id = id;
        this.ma = ma;
        this.thanhtoan = thanhtoan;
        this.magd = magd;
        this.trangthai = trangthai;
        this.ghichu = ghichu;
        this.id_nguoitao = id_nguoitao;
        this.tongtien = tongtien;
        this.ngaytao = ngaytao;
    }

    public BigDecimal getTongtien() {
        return tongtien;
    }

    public void setTongtien(BigDecimal tongtien) {
        this.tongtien = tongtien;
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

    public BigDecimal getThanhtoan() {
        return thanhtoan;
    }

    public void setThanhtoan(BigDecimal thanhtoan) {
        this.thanhtoan = thanhtoan;
    }

    public String getMagd() {
        return magd;
    }

    public void setMagd(String magd) {
        this.magd = magd;
    }

    public Integer getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(Integer trangthai) {
        this.trangthai = trangthai;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public UUID getId_nguoitao() {
        return id_nguoitao;
    }

    public void setId_nguoitao(UUID id_nguoitao) {
        this.id_nguoitao = id_nguoitao;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

}
