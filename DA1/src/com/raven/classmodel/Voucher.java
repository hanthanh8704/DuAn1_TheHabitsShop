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
    private UUID id;
    private String ma;
    private String ten;
    private String loaiGiamGia;
    private BigDecimal giatrimax;
    private BigDecimal giatrimin;
    private int soLuong;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private int trangThai;
    private Date ngayTao;
    private NhanVien nv;
    private HoaDon hd;
    private String id_nv;
    private String id_hd;

    public Voucher() {
    }

    public Voucher(UUID id, String ma, String ten, String loaiGiamGia, BigDecimal giatrimax, BigDecimal giatrimin, int soLuong, Date ngayBatDau, Date ngayKetThuc, int trangThai, String id_nv, String id_hd) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.loaiGiamGia = loaiGiamGia;
        this.giatrimax = giatrimax;
        this.giatrimin = giatrimin;
        this.soLuong = soLuong;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
        this.id_nv = id_nv;
        this.id_hd = id_hd;
    }

    public Voucher(String ma, String ten, String loaiGiamGia, BigDecimal giatrimax, BigDecimal giatrimin, int soLuong, Date ngayBatDau, Date ngayKetThuc, int trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.loaiGiamGia = loaiGiamGia;
        this.giatrimax = giatrimax;
        this.giatrimin = giatrimin;
        this.soLuong = soLuong;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
    }
    
    public Voucher(UUID id, String ma, String ten, String loaiGiamGia, BigDecimal giatrimax, BigDecimal giatrimin, int soLuong, Date ngayBatDau, Date ngayKetThuc, int trangThai, NhanVien nv, HoaDon hd) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.loaiGiamGia = loaiGiamGia;
        this.giatrimax = giatrimax;
        this.giatrimin = giatrimin;
        this.soLuong = soLuong;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
        this.nv = nv;
        this.hd = hd;
    }

    public Voucher(String ma, String ten, String loaiGiamGia, BigDecimal giatrimax, BigDecimal giatrimin, int soLuong, Date ngayBatDau, Date ngayKetThuc, int trangThai, NhanVien nv, HoaDon hd) {
        this.ma = ma;
        this.ten = ten;
        this.loaiGiamGia = loaiGiamGia;
        this.giatrimax = giatrimax;
        this.giatrimin = giatrimin;
        this.soLuong = soLuong;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
        this.nv = nv;
        this.hd = hd;
    }

    public Voucher(String ma, String ten, String loaiGiamGia, BigDecimal giatrimax, BigDecimal giatrimin, int soLuong, Date ngayBatDau, Date ngayKetThuc, int trangThai, String id_nv, String id_hd) {
        this.ma = ma;
        this.ten = ten;
        this.loaiGiamGia = loaiGiamGia;
        this.giatrimax = giatrimax;
        this.giatrimin = giatrimin;
        this.soLuong = soLuong;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
        this.id_nv = id_nv;
        this.id_hd = id_hd;
    }

    public Voucher(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getId_nv() {
        return id_nv;
    }

    public void setId_nv(String id_nv) {
        this.id_nv = id_nv;
    }

    public String getId_hd() {
        return id_hd;
    }

    public void setId_hd(String id_hd) {
        this.id_hd = id_hd;
    }

    public HoaDon getHd() {
        return hd;
    }

    public void setHd(HoaDon hd) {
        this.hd = hd;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
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

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public BigDecimal getGiatrimax() {
        return giatrimax;
    }

    public void setGiatrimax(BigDecimal giatrimax) {
        this.giatrimax = giatrimax;
    }

    public BigDecimal getGiatrimin() {
        return giatrimin;
    }

    public void setGiatrimin(BigDecimal giatrimin) {
        this.giatrimin = giatrimin;
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

    public String getLoaiGiamGia() {
        return loaiGiamGia;
    }

    public void setLoaiGiamGia(String loaiGiamGia) {
        this.loaiGiamGia = loaiGiamGia;
    }
    
    public Object[] toDaTaRow(){
        String trangthai = "";
            int trangThai = this.getTrangThai();
            if (trangThai == 1) {
                trangthai = "Sắp diễn ra";
            } else if (trangThai == 2) {
                trangthai = "Đang diễn ra";
            } else if(trangThai ==3){
                trangthai = "Đã kết thúc";
            }
            int stt =0;
            stt++;
        return new Object[] {stt++,this.getMa(),this.getTen(),this.getLoaiGiamGia(),this.getGiatrimax(),this.getGiatrimin(),this.getSoLuong(),this.getNgayBatDau(),this.getNgayKetThuc(),trangthai,this.getNgayTao()};
    }
}
