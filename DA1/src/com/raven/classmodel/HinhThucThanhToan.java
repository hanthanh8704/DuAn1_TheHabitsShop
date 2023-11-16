/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.classmodel;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class HinhThucThanhToan {

    private UUID ID;
    private Integer stt;
    private String magd;
    private String ten;
    private HoaDon id_hoaDon;
    private ThanhToan id_thanhToan;
    private UUID id_nguoiTao;
    private Date ngayTao;

    public HinhThucThanhToan() {
    }

    public HinhThucThanhToan(UUID ID, Integer stt, String magd, String ten, HoaDon id_hoaDon, ThanhToan id_thanhToan, UUID id_nguoiTao, Date ngayTao) {
        this.ID = ID;
        this.stt = stt;
        this.magd = magd;
        this.ten = ten;
        this.id_hoaDon = id_hoaDon;
        this.id_thanhToan = id_thanhToan;
        this.id_nguoiTao = id_nguoiTao;
        this.ngayTao = ngayTao;
    }

    public HinhThucThanhToan(Integer stt, String magd, String ten, HoaDon id_hoaDon, ThanhToan id_thanhToan, UUID id_nguoiTao, Date ngayTao) {
        this.stt = stt;
        this.magd = magd;
        this.ten = ten;
        this.id_hoaDon = id_hoaDon;
        this.id_thanhToan = id_thanhToan;
        this.id_nguoiTao = id_nguoiTao;
        this.ngayTao = ngayTao;
    }

    public HinhThucThanhToan(Integer stt, String magd, String ten, HoaDon id_hoaDon, ThanhToan id_thanhToan) {
        this.stt = stt;
        this.magd = magd;
        this.ten = ten;
        this.id_hoaDon = id_hoaDon;
        this.id_thanhToan = id_thanhToan;
    }

    public UUID getID() {
        return ID;
    }

    public void setID(UUID ID) {
        this.ID = ID;
    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public String getMagd() {
        return magd;
    }

    public void setMagd(String magd) {
        this.magd = magd;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public HoaDon getId_hoaDon() {
        return id_hoaDon;
    }

    public void setId_hoaDon(HoaDon id_hoaDon) {
        this.id_hoaDon = id_hoaDon;
    }

    public ThanhToan getId_thanhToan() {
        return id_thanhToan;
    }

    public void setId_thanhToan(ThanhToan id_thanhToan) {
        this.id_thanhToan = id_thanhToan;
    }

    public UUID getId_nguoiTao() {
        return id_nguoiTao;
    }

    public void setId_nguoiTao(UUID id_nguoiTao) {
        this.id_nguoiTao = id_nguoiTao;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

}
