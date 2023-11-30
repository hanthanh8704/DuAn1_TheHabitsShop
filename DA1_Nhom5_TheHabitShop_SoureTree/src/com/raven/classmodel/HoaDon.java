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
public class HoaDon {

    private UUID id;
    private String ma;
    private String tenNguoiNhan;
    private String diaChi;
    private Date ngayXacNhan;
    private BigDecimal phiVC;
    private Date ngayVanChuyen;
    private Date ngaytaohoadon;
    private Date ngaynhanHang;
    private Date ngaythanhtoan;
    private BigDecimal tongTienHoaDon;
    private BigDecimal tongTienSauGiamGia;
    private Integer tinhTrang;
    private String ghichu;
    private KhachHang idKhachHang;
    private NhanVien idNhanVien;
    private String id_khachhang;
    private UUID nhanvien_id;
    private String tennhanvien;
    private int xoa_tt;

    public String getTennhanvien() {
        return tennhanvien;
    }

    public void setTennhanvien(String tennhanvien) {
        this.tennhanvien = tennhanvien;
    }

    public String getId_nhanvien() {
        return id_nhanvien;
    }

    public void setId_nhanvien(String id_nhanvien) {
        this.id_nhanvien = id_nhanvien;
    }

    public UUID getNhanvien_id() {
        return nhanvien_id;
    }

    public void setNhanvien_id(UUID nhanvien_id) {
        this.nhanvien_id = nhanvien_id;
    }
    private String id_nhanvien;
    private HinhThucThanhToan httt;
    private ThanhToan thanhToan;
    private UUID id_nguoiTao;
    private Date ngayTao;

    public int getXoa_tt() {
        return xoa_tt;
    }

    public void setXoa_tt(int xoa_tt) {
        this.xoa_tt = xoa_tt;
    }

    public HoaDon(String ma, String tenNguoiNhan, String diaChi, Date ngayXacNhan, BigDecimal phiVC, Date ngayVanChuyen, Date ngaytaohoadon, Date ngaynhanHang, Date ngaythanhtoan, BigDecimal tongTienHoaDon, BigDecimal tongTienSauGiamGia, Integer tinhTrang, String ghichu, String id_khachhang, UUID nhanvien_id, UUID id_nguoiTao, Date ngayTao, int xoa_tt) {
        this.ma = ma;
        this.tenNguoiNhan = tenNguoiNhan;
        this.diaChi = diaChi;
        this.ngayXacNhan = ngayXacNhan;
        this.phiVC = phiVC;
        this.ngayVanChuyen = ngayVanChuyen;
        this.ngaytaohoadon = ngaytaohoadon;
        this.ngaynhanHang = ngaynhanHang;
        this.ngaythanhtoan = ngaythanhtoan;
        this.tongTienHoaDon = tongTienHoaDon;
        this.tongTienSauGiamGia = tongTienSauGiamGia;
        this.tinhTrang = tinhTrang;
        this.ghichu = ghichu;
        this.id_khachhang = id_khachhang;
        this.nhanvien_id = nhanvien_id;
        this.id_nguoiTao = id_nguoiTao;
        this.ngayTao = ngayTao;
        this.xoa_tt = xoa_tt;
    }

    public String getId_khachhang() {
        return id_khachhang;
    }

    public void setId_khachhang(String id_khachhang) {
        this.id_khachhang = id_khachhang;
    }

    public HinhThucThanhToan getHttt() {
        return httt;
    }

    public void setHttt(HinhThucThanhToan httt) {
        this.httt = httt;
    }

    public ThanhToan getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(ThanhToan thanhToan) {
        this.thanhToan = thanhToan;
    }

    public BigDecimal getTongTienHoaDon() {
        return tongTienHoaDon;
    }

    public void setTongTienHoaDon(BigDecimal tongTienHoaDon) {
        this.tongTienHoaDon = tongTienHoaDon;
    }

    public BigDecimal getTongTienSauGiamGia() {
        return tongTienSauGiamGia;
    }

    public void setTongTienSauGiamGia(BigDecimal tongTienSauGiamGia) {
        this.tongTienSauGiamGia = tongTienSauGiamGia;
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

    public HoaDon() {
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public Date getNgaytaohoadon() {
        return ngaytaohoadon;
    }

    public void setNgaytaohoadon(Date ngaytaohoadon) {
        this.ngaytaohoadon = ngaytaohoadon;
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

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgayXacNhan() {
        return ngayXacNhan;
    }

    public void setNgayXacNhan(Date ngayXacNhan) {
        this.ngayXacNhan = ngayXacNhan;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "tinhTrang=" + tinhTrang + '}';
    }

    public BigDecimal getPhiVC() {
        return phiVC;
    }

    public void setPhiVC(BigDecimal phiVC) {
        this.phiVC = phiVC;
    }

    public Date getNgayVanChuyen() {
        return ngayVanChuyen;
    }

    public void setNgayVanChuyen(Date ngayVanChuyen) {
        this.ngayVanChuyen = ngayVanChuyen;
    }

    public Date getNgaynhanHang() {
        return ngaynhanHang;
    }

    public void setNgaynhanHang(Date ngaynhanHang) {
        this.ngaynhanHang = ngaynhanHang;
    }

    public Date getNgaythanhtoan() {
        return ngaythanhtoan;
    }

    public void setNgaythanhtoan(Date ngaythanhtoan) {
        this.ngaythanhtoan = ngaythanhtoan;
    }

    public Integer getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Integer tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public KhachHang getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(KhachHang idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public NhanVien getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(NhanVien idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String tinhTrangHoaDon() {
        if (tinhTrang == 0) {
            return "Tất Cả";
        } else if (tinhTrang == 1) {
            return "Đã Thanh Toán";
        } else if (tinhTrang == 2) {
            return "Đã Hủy";
        } else if (tinhTrang == 3) {
            return "Đang Chờ Thanh Toán";
        } else {
            return "Chưa Thanh Toán";
        }
    }
//KH

    public HoaDon(String ma, Date ngaythanhtoan, BigDecimal tongTienHoaDon, Integer tinhTrang, String tenNN, String diaChi) {

        this.ma = ma;
        this.ngaythanhtoan = ngaythanhtoan;
        this.tongTienHoaDon = tongTienHoaDon;
        this.tinhTrang = tinhTrang;
        this.tenNguoiNhan = tenNN;
        this.diaChi = diaChi;
    }

}
