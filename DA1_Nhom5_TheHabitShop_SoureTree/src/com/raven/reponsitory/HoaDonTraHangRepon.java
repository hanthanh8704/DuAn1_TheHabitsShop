/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classmodel.HoaDonTraHang;
import com.raven.classmodel.KhachHang;
import com.raven.classmodel.NhanVien;
import com.toedter.calendar.JDateChooser;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class HoaDonTraHangRepon {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<HoaDonTraHang> getAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<HoaDonTraHang> Listhdth = new ArrayList<>();
        String sql = "SELECT HoaDonTraHang.ma,NhanVien.ten AS 'TenNV',HoaDonTraHang.ngaytrahang,"
                + "KhachHang.ten AS 'TenKH',HoaDonTraHang.tienhoantra,NhanVien.id AS 'idNV',KhachHang.id AS 'idKH' FROM HoaDonTraHang\n"
                + "JOIN NhanVien ON NhanVien.id = HoaDonTraHang.id_nhanvien\n"
                + "JOIN KhachHang ON KhachHang.id = HoaDonTraHang.id_khachhang";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("ma");
                UUID idnv = UUID.fromString(rs.getString("idNV"));
                UUID idkh = UUID.fromString(rs.getString("idKH"));
                String tenNV = rs.getString("tenKH");
                Date ngaytrahang = rs.getDate("ngaytrahang");
                String tenKH = rs.getString("tenNV");
                BigDecimal tienhoantra = rs.getBigDecimal("tienhoantra");

                NhanVien nv = new NhanVien();
                nv.setId(idnv);
                nv.setTenNV(tenNV);

                KhachHang kh = new KhachHang();
                kh.setID(idkh);
                kh.setTenKH(tenKH);

                HoaDonTraHang hdth = new HoaDonTraHang();
                hdth.setMa(ma);
                hdth.setKhachHang(kh);
                hdth.setNhanVien(nv);
                hdth.setNgayTraHang(ngaytrahang);
                hdth.setTienHoanTra(tienhoantra);
                Listhdth.add(hdth);
            }
            return Listhdth;
        } catch (Exception e) {
        }
        return null;

    }

    public List<HoaDonTraHang> findDateHDTH(JDateChooser d1, JDateChooser d2) {
        List<HoaDonTraHang> listHDTH = new ArrayList<>();
        String sql = "SELECT HoaDonTraHang.ma, NhanVien.ten AS 'TenNV', HoaDonTraHang.ngaytrahang AS 'ngaytra', "
                + "KhachHang.ten AS 'TenKH', HoaDonTraHang.tienhoantra, NhanVien.id AS 'idNV', "
                + "KhachHang.id AS 'idKH' "
                + "FROM HoaDonTraHang "
                + "JOIN NhanVien ON NhanVien.id = HoaDonTraHang.id_nhanvien "
                + "JOIN KhachHang ON KhachHang.id = HoaDonTraHang.id_khachhang "
                + "WHERE HoaDonTraHang.ngaytrahang BETWEEN ? AND ?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            if (d1.getDate() != null && d2.getDate() != null) {
                ps.setDate(1, new java.sql.Date(d1.getDate().getTime()));
                ps.setDate(2, new java.sql.Date(d2.getDate().getTime()));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String ma = rs.getString("ma");
                    UUID idnv = UUID.fromString(rs.getString("idNV"));
                    UUID idkh = UUID.fromString(rs.getString("idKH"));
                    String tenNV = rs.getString("TenNV"); // Sửa thành "TenNV"
                    Date ngaytrahang = rs.getDate("ngaytra");
                    String tenKH = rs.getString("TenKH"); // Sửa thành "TenKH"
                    BigDecimal tienhoantra = rs.getBigDecimal("tienhoantra");

                    NhanVien nv = new NhanVien();
                    nv.setId(idnv);
                    nv.setTenNV(tenNV);

                    KhachHang kh = new KhachHang();
                    kh.setID(idkh);
                    kh.setTenKH(tenKH);

                    HoaDonTraHang hdth = new HoaDonTraHang();
                    hdth.setMa(ma);
                    hdth.setKhachHang(kh);
                    hdth.setNhanVien(nv);
                    hdth.setNgayTraHang(ngaytrahang);
                    hdth.setTienHoanTra(tienhoantra);
                    listHDTH.add(hdth);
                }
            }
            return listHDTH;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<HoaDonTraHang> mouseClickTraHang() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<HoaDonTraHang> Listhdth = new ArrayList<>();
        String sql = "SELECT HoaDonTraHang.ma, KhachHang.ten AS 'TenKH',KhachHang.diachi, HoaDonTraHang.tienhoantra,KhachHang.id AS 'idKH' "
                + "FROM HoaDonTraHang "
                + "JOIN KhachHang ON KhachHang.id = HoaDonTraHang.id_khachhang";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("ma");
                UUID idkh = UUID.fromString(rs.getString("idKH"));
                String tenKH = rs.getString("tenKH");
                String diachi = rs.getString("diachi");
                BigDecimal tienhoantra = rs.getBigDecimal("tienhoantra");

                KhachHang kh = new KhachHang();
                kh.setID(idkh);
                kh.setTenKH(tenKH);
                kh.setDiaChi(diachi);

                HoaDonTraHang hdth = new HoaDonTraHang();
                hdth.setMa(ma);
                hdth.setKhachHang(kh);
                hdth.setTienHoanTra(tienhoantra);
                Listhdth.add(hdth);
            }
            return Listhdth;
        } catch (Exception e) {
        }
        return null;
    }

//    public List<HoaDonTraHang> getThongTinTH() {
//        List<HoaDonTraHang> Listhdth = new ArrayList<>();
//        String sql = "SELECT HoaDonTraHang.ma,NhanVien.ten AS 'tenNV',KhachHang.ten AS 'tenKH',"
//                + "HoaDonTraHang.ngaytrahang,HoaDonTraHang.tienhoantra FROM HoaDonTraHang\n"
//                + "JOIN NhanVien ON NhanVien.id = HoaDonTraHang.id_nhanvien\n"
//                + "JOIN KhachHang ON KhachHang.id = HoaDonTraHang.id_khachhang";
//        try {
//            conn = DBConnect.getConnection();
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                String ma = rs.getString("ma");
//                UUID idnv = UUID.fromString(rs.getString("idNV"));
//                UUID idkh = UUID.fromString(rs.getString("idKH"));
//                String tenNV = rs.getString("tenKH");
//                Date ngaytrahang = rs.getDate("ngaytrahang");
//                String tenKH = rs.getString("tenNV");
//                BigDecimal tienhoantra = rs.getBigDecimal("tienhoantra");
//
//                NhanVien nv = new NhanVien();
//                nv.setId(idnv);
//                nv.setTen(tenNV);
//
//                KhachHang kh = new KhachHang();
//                kh.setId(idkh);
//                kh.setTen(tenKH);
//
//                HoaDonTraHang hdth = new HoaDonTraHang();
//                hdth.setMa(ma);
//                hdth.setKhachHang(kh);
//                hdth.setNhanVien(nv);
//                hdth.setNgayTraHang(ngaytrahang);
//                hdth.setTienHoanTra(tienhoantra);
//                Listhdth.add(hdth);
//            }
//            return Listhdth;
//        } catch (Exception e) {
//        }
//        return null;
//    }

}
