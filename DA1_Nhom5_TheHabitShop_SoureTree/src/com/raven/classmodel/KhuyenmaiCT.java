/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.classmodel;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ACER
 */
public class KhuyenmaiCT {
    private String ma;
    private String dieuKien;
    private BigDecimal mucGiam;
    private Date ngayBD;
    private Date ngayKT;
    private String id_Khuyenmai;
    private String id_SPCT;
    private String id_NguoiTao;
    private Date ngayTao;

    public KhuyenmaiCT() {
    }

    public KhuyenmaiCT(String ma, String dieuKien, BigDecimal mucGiam, Date ngayBD, Date ngayKT, String id_Khuyenmai, String id_SPCT, String id_NguoiTao, Date ngayTao) {
        this.ma = ma;
        this.dieuKien = dieuKien;
        this.mucGiam = mucGiam;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.id_Khuyenmai = id_Khuyenmai;
        this.id_SPCT = id_SPCT;
        this.id_NguoiTao = id_NguoiTao;
        this.ngayTao = ngayTao;
    }

    public KhuyenmaiCT(String ma, String dieuKien, BigDecimal mucGiam, Date ngayBD, Date ngayKT, String id_Khuyenmai, String id_SPCT, Date ngayTao) {
        this.ma = ma;
        this.dieuKien = dieuKien;
        this.mucGiam = mucGiam;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.id_Khuyenmai = id_Khuyenmai;
        this.id_SPCT = id_SPCT;
        this.ngayTao = ngayTao;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getDieuKien() {
        return dieuKien;
    }

    public void setDieuKien(String dieuKien) {
        this.dieuKien = dieuKien;
    }

    public BigDecimal getMucGiam() {
        return mucGiam;
    }

    public void setMucGiam(BigDecimal mucGiam) {
        this.mucGiam = mucGiam;
    }

    public Date getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(Date ngayBD) {
        this.ngayBD = ngayBD;
    }

    public Date getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(Date ngayKT) {
        this.ngayKT = ngayKT;
    }

    public String getId_Khuyenmai() {
        return id_Khuyenmai;
    }

    public void setId_Khuyenmai(String id_Khuyenmai) {
        this.id_Khuyenmai = id_Khuyenmai;
    }

    public String getId_SPCT() {
        return id_SPCT;
    }

    public void setId_SPCT(String id_SPCT) {
        this.id_SPCT = id_SPCT;
    }

    public String getId_NguoiTao() {
        return id_NguoiTao;
    }

    public void setId_NguoiTao(String id_NguoiTao) {
        this.id_NguoiTao = id_NguoiTao;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
    
    
    
}
