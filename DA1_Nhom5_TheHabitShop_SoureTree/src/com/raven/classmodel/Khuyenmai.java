/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.classmodel;

import com.raven.model.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author ACER
 */

public class Khuyenmai {
    private UUID id;
    private String ma;
    private String tenCTr;
    private BigDecimal mucGiam;
    private String dieuKien;
    private Date ngayBD;
    private Date ngayKT;
    private Date ngayTao;
    private int trangThai;
//    private KhuyenmaiCT kmct;
//    private KhuyenmaiCT kmct1;
//    private KhuyenmaiCT kmct2;
//    private KhuyenmaiCT kmct3;
//    private KhuyenmaiCT kmct4;
//    private KhuyenmaiCT kmct5;
//    private KhuyenmaiCT kmct6;
//    private KhuyenmaiCT kmct7;

    public Khuyenmai() {
    }

    public Khuyenmai(String ma, String tenCTr, BigDecimal mucGiam, String dieuKien, Date ngayBD, Date ngayKT, Date ngayTao, int trangThai) {
        this.ma = ma;
        this.tenCTr = tenCTr;
        this.mucGiam = mucGiam;
        this.dieuKien = dieuKien;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }

    
    
    public Khuyenmai(String ma, String tenCTr, BigDecimal mucGiam, String dieuKien, Date ngayBD, Date ngayKT, int trangThai) {
        this.ma = ma;
        this.tenCTr = tenCTr;
        this.mucGiam = mucGiam;
        this.dieuKien = dieuKien;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.trangThai = trangThai;
    }

    public Khuyenmai(UUID id, String ma, String tenCTr, BigDecimal mucGiam, String dieuKien, Date ngayBD, Date ngayKT) {
        this.id = id;
        this.ma = ma;
        this.tenCTr = tenCTr;
        this.mucGiam = mucGiam;
        this.dieuKien = dieuKien;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
       
    }

    public Khuyenmai(String ma, String tenCTr, BigDecimal mucGiam, String dieuKien, Date ngayBD, Date ngayKT) {
        this.ma = ma;
        this.tenCTr = tenCTr;
        this.mucGiam = mucGiam;
        this.dieuKien = dieuKien;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
    }

    public Khuyenmai(String ma, String tenCTr, BigDecimal mucGiam, String dieuKien, Date ngayBD, Date ngayKT, Date ngayTao) {
        this.ma = ma;
        this.tenCTr = tenCTr;
        this.mucGiam = mucGiam;
        this.dieuKien = dieuKien;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.ngayTao = ngayTao;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTenCTr() {
        return tenCTr;
    }

    public void setTenCTr(String tenCTr) {
        this.tenCTr = tenCTr;
    }

    public BigDecimal getMucGiam() {
        return mucGiam;
    }

    public void setMucGiam(BigDecimal mucGiam) {
        this.mucGiam = mucGiam;
    }

    public String getDieuKien() {
        return dieuKien;
    }

    public void setDieuKien(String dieuKien) {
        this.dieuKien = dieuKien;
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

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

  
 
    
   
    
    public Object[] toDatarow(){
        return new Object[] {
           this.getMa(),this.getTenCTr(),this.getMucGiam(),this.getDieuKien(),this.getNgayBD(),this.getNgayKT(),this.getNgayTao()
        };
    }
}
