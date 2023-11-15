/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classmodel.HoaDon;
import com.raven.classmodel.KhachHang;
import com.raven.classmodel.NhanVien;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.sql.*;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class HoaDonRepon {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public ArrayList<HoaDon> getAll() {
        ArrayList<HoaDon> hoaDonList = new ArrayList<>();
        sql = "SELECT HoaDon.id, NhanVien.ma, KhachHang.ten, HoaDon.ngaytaohoadon, HoaDon.ngaythanhtoan,"
                + " HoaDon.tongtiencuahoadon, HoaDon.tinhtrang, HoaDon.ghichu, "
                + "NhanVien.id AS idNV, NhanVien.ten, KhachHang.id AS idKH, KhachHang.ten AS tenKH FROM HoaDon\n"
                + "LEFT JOIN NhanVien ON HoaDon.id_nhanvien = NhanVien.id\n"
                + "LEFT JOIN KhachHang ON HoaDon.id_khachhang = KhachHang.id";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString(1));
                UUID idhd = UUID.fromString(rs.getString("id"));
                Date ngaytaohd = rs.getDate("ngaytaohoadon");
                Date ngaythanhtoan = rs.getDate("ngaythanhtoan");
                BigDecimal tongtien = rs.getBigDecimal("tongtiencuahoadon");
                Integer tinhtrang = rs.getInt("tinhtrang");
                String ghichu = rs.getString("ghichu");
                UUID idnv = UUID.fromString(rs.getString("idNV"));
                String manv = rs.getString("ma");
                String idkh = rs.getString("idKH");
                String tenkh = rs.getString("ten");

                NhanVien nv = new NhanVien();
                nv.setId(idnv);
                nv.setMaNV(manv);
                KhachHang kh = new KhachHang();
                kh.setMaKH(idkh);
                kh.setTenKH(tenkh);

                HoaDon hoaDon = new HoaDon();
                hoaDon.setId(idhd);
                hoaDon.setNgaytaohoadon(ngaytaohd);
                hoaDon.setNgaythanhtoan(ngaythanhtoan);
                hoaDon.setTongTien(tongtien);
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
}
