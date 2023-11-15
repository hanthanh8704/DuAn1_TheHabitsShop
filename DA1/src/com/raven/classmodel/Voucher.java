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
 * @author Ninh Than Thanh
 */
public class Voucher {
    private int id;
    private String ma;
    private String ten;
    private BigDecimal mucGiam;
    private int soLuong;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private int trangThai;
    private Date ngayTao;

    public Voucher() {
    }

    public Voucher(int id, String ma, String ten, BigDecimal mucGiam, int soLuong, Date ngayBatDau, Date ngayKetThuc, int trangThai, Date ngayTao) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.mucGiam = mucGiam;
        this.soLuong = soLuong;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
    }

    public Voucher(String ma, String ten, BigDecimal mucGiam, int soLuong, Date ngayBatDau, Date ngayKetThuc, int trangThai, Date ngayTao) {
        this.ma = ma;
        this.ten = ten;
        this.mucGiam = mucGiam;
        this.soLuong = soLuong;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
    }

    public Voucher(String ma, String ten, BigDecimal mucGiam, int soLuong, Date ngayBatDau, Date ngayKetThuc, Date ngayTao) {
        this.ma = ma;
        this.ten = ten;
        this.mucGiam = mucGiam;
        this.soLuong = soLuong;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.ngayTao = ngayTao;
    }

    public Voucher(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public BigDecimal getMucGiam() {
        return mucGiam;
    }

    public void setMucGiam(BigDecimal mucGiam) {
        this.mucGiam = mucGiam;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
    public Object[] toDaTaRow(){
        return new Object[] {this.getId(),this.getMa(),this.getTen(),this.getMucGiam(),this.getSoLuong(),this.getNgayBatDau(),this.getNgayKetThuc(),this.getTrangThai(),this.getNgayTao()};
    }
}
