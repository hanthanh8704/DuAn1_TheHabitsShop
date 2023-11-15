/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classinterface.HoaDonInteface;
import com.raven.classmodel.HoaDon;
import com.raven.classmodel.HoaDonChiTiet;
import com.raven.classmodel.KhachHang;
import com.raven.classmodel.NhanVien;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class HoaDonRepon implements HoaDonInteface{

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public ArrayList<HoaDon> getAll() {
        ArrayList<HoaDon> hoaDonList = new ArrayList<>();
        sql = "SELECT HoaDon.ma AS 'MaHD', NhanVien.ma, KhachHang.ten, HoaDon.ngaytaohoadon, HoaDon.ngaythanhtoan,"
                + " HoaDon.tongtiencuahoadon, HoaDon.tinhtrang, HoaDon.ghichu, "
                + "NhanVien.id AS idNV, NhanVien.ten, KhachHang.id AS idKH, KhachHang.ten AS tenKH FROM HoaDon\n"
                + "LEFT JOIN NhanVien ON HoaDon.id_nhanvien = NhanVien.id\n"
                + "LEFT JOIN KhachHang ON HoaDon.id_khachhang = KhachHang.id";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                //UUID id = UUID.fromString(rs.getString(1));
                String mahd = rs.getString("MaHD");
                Date ngaytaohd = rs.getDate("ngaytaohoadon");
                Date ngaythanhtoan = rs.getDate("ngaythanhtoan");
                BigDecimal tongtien = rs.getBigDecimal("tongtiencuahoadon");
                Integer tinhtrang = rs.getInt("tinhtrang");
                String ghichu = rs.getString("ghichu");
                UUID idnv = UUID.fromString(rs.getString("idNV"));
                String manv = rs.getString("ma");
                UUID idkh = UUID.fromString(rs.getString("idKH"));
                String tenkh = rs.getString("ten");

                NhanVien nv = new NhanVien();
                nv.setId(idnv);
                nv.setMa(manv);
                KhachHang kh = new KhachHang();
                kh.setId(idkh);
                kh.setTen(tenkh);

                HoaDon hoaDon = new HoaDon();
                hoaDon.setMa(mahd);
                hoaDon.setNgaytaohoadon(ngaytaohd);
                hoaDon.setNgaythanhtoan(ngaythanhtoan);
                hoaDon.setTongTienHoaDon(tongtien);
                hoaDon.setTinhTrang(tinhtrang);
                hoaDon.setGhichu(ghichu);
                hoaDon.setIdNhanVien(nv);
                hoaDon.setIdKhachHang(kh);
                hoaDonList.add(hoaDon);
            }
            return hoaDonList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<String> getListMa() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getTenKHById(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getMucGiamById(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getTenNVKHById(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDonChiTiet> getListById(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDon> getListByHTTT(UUID id) {
        String sql = "";
        return null;
    }

    @Override
    public List<HoaDon> getListByMa(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDon> getListByID(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
