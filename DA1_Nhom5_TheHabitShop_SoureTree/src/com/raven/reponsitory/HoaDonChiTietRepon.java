/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classinterface.HoaDonChiTietInterface;
import com.raven.classmodel.ChatLieu;
import com.raven.classmodel.ChiTietSanPham;
import com.raven.classmodel.HoaDon;
import com.raven.classmodel.HoaDonChiTiet;
import com.raven.classmodel.KichCo;
import com.raven.classmodel.MauSac;
import com.raven.classmodel.NhanHieu;
import com.raven.classmodel.SanPham;
import com.raven.classmodel.SanPhamChiTiet;
import java.math.BigDecimal;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class HoaDonChiTietRepon {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<HoaDonChiTiet> getHDCT() {
        ArrayList<HoaDonChiTiet> listHDCT = new ArrayList<>();
        String sql = "SELECT SanPhamChiTiet.ma, SanPham.ten,HoaDonChiTiet.soluong,"
                + "HoaDonChiTiet.gia_spct,HoaDon.ma AS 'maHD',\n"
                + "HoaDon.tongtiencuahoadon FROM HoaDon\n"
                + "JOIN HoaDonChiTiet ON HoaDon.id = HoaDonChiTiet.id_hoadon\n"
                + "JOIN SanPhamChiTiet ON SanPhamChiTiet.id = HoaDonChiTiet.id_spct\n"
                + "JOIN SanPham ON SanPham.id = SanPhamChiTiet.id_sanpham\n";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String mahd = rs.getString("ma");
                String mahoadon = rs.getString("maHD");
                String tensp = rs.getString("ten");
                Integer soluong = rs.getInt("soluong");
                BigDecimal gia = rs.getBigDecimal("gia_spct");
                BigDecimal tongtiencuahoadon = rs.getBigDecimal("tongtiencuahoadon");

                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setMa(mahd);
                spct.setGiaBan1(gia);

                SanPham sp = new SanPham();
                sp.setTenSP(tensp);

                HoaDon hd = new HoaDon();
                hd.setMa(mahoadon);
                hd.setTongTienHoaDon(tongtiencuahoadon);

                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setSoLuong(soluong);
                hdct.setIdSPCT(spct);
                hdct.setHoaDon(hd);
                spct.setTenSanPham(sp);
                listHDCT.add(hdct);
            }
            return listHDCT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDonChiTiet> getInHoaDonCT() {
        ArrayList<HoaDonChiTiet> listHDCT = new ArrayList<>();
        String sql = """
                        SELECT 
                        SanPhamChiTiet.ma, 
                        SanPham.ten,
                        HoaDonChiTiet.soluong,
                        HoaDonChiTiet.gia_spct,
                        HoaDon.ma AS 'maHD',
                        MauSac.ten AS 'TenMS',
                        ChatLieu.ten AS 'TenCL',
                        KichCo.ten AS 'TenKC',
                        NhanHieu.ten AS 'TenNH', 
                        HoaDon.tongtiencuahoadon
                        
                        FROM 
                            SanPhamChiTiet
                        JOIN 
                            HoaDonChiTiet ON SanPhamChiTiet.id = HoaDonChiTiet.id_spct
                        JOIN 
                            HoaDon ON HoaDon.id = HoaDonChiTiet.id_hoadon
                        JOIN 
                            NhanHieu ON NhanHieu.id = SanPhamChiTiet.id_nhanhieu
                        JOIN 
                            MauSac ON MauSac.id = SanPhamChiTiet.id_mausac
                        JOIN 
                            KichCo ON KichCo.id = SanPhamChiTiet.id_kichco
                        JOIN 
                            ChatLieu ON ChatLieu.id = SanPhamChiTiet.id_chatlieu
                        JOIN 
                            SanPham ON SanPham.id = SanPhamChiTiet.id_sanpham;
                     """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String tenMS = rs.getString("TenMS");
                String tenCL = rs.getString("TenCL");
                String tenKC = rs.getString("TenKC");
                String tenNH = rs.getString("TenNH");
                String mahd = rs.getString("ma");
                String mahoadon = rs.getString("maHD");
                String tensp = rs.getString("ten");
                Integer soluong = rs.getInt("soluong");
                BigDecimal gia = rs.getBigDecimal("gia_spct");
                BigDecimal tongtiencuahoadon = rs.getBigDecimal("tongtiencuahoadon");

                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setMa(mahd);
                spct.setGiaBan1(gia);

                MauSac ms = new MauSac();
                ms.setTenMauSac(tenMS);

                ChatLieu cl = new ChatLieu();
                cl.setTen(tenCL);

                KichCo kc = new KichCo();
                kc.setTenKichCo(tenKC);

                NhanHieu nh = new NhanHieu();
                nh.setTen(tenNH);

                SanPham sp = new SanPham();
                sp.setTenSP(tensp);

                HoaDon hd = new HoaDon();
                hd.setMa(mahoadon);
                hd.setTongTienHoaDon(tongtiencuahoadon);

                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setSoLuong(soluong);
                hdct.setIdSPCT(spct);
                hdct.setHoaDon(hd);
                spct.setTenSanPham(sp);
                spct.setTenMauSac(ms);
                spct.setTenChatlieu(cl);
                spct.setTenKichCo(kc);
                spct.setTenNhanHieu(nh);
                listHDCT.add(hdct);
            }
            return listHDCT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDonChiTiet> getAll() {
        List<HoaDonChiTiet> listHDCT = new ArrayList<>();
        String sql = "SELECT SanPhamChiTiet.ma,SanPham.ten AS 'tenSP',HoaDonChiTiet.soluong,"
                + "SanPhamChiTiet.giaa,NhanHieu.ten AS 'tenNH',MauSac.ten AS 'tenMS',"
                + "KichCo.ten AS 'tenKC',HoaDon.tongtiencuahoadon FROM HoaDonChiTiet\n"
                + "JOIN HoaDon ON HoaDon.id = HoaDonChiTiet.id_hoadon\n"
                + "JOIN SanPhamChiTiet ON SanPhamChiTiet.id = HoaDonChiTiet.id_spct\n"
                + "JOIN SanPham ON SanPham.id = SanPhamChiTiet.id_sanpham\n"
                + "JOIN NhanHieu ON NhanHieu.id = SanPhamChiTiet.id_nhanhieu\n"
                + "JOIN MauSac ON MauSac.id = SanPhamChiTiet.id_mausac\n"
                + "JOIN KichCo ON KichCo.id = SanPhamChiTiet.id_kichco";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maspct = rs.getString("ma");
                Integer soluong = rs.getInt("soluong");
                String tensp = rs.getString("tenSP");
                String tennh = rs.getString("tenNH");
                String tenms = rs.getString("tenMS");
                String tenkc = rs.getString("tenKC");
                BigDecimal gia = rs.getBigDecimal("giaa");
                BigDecimal tongtiencuahoadon = rs.getBigDecimal("tongtiencuahoadon");

                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setMa(maspct);
                spct.setGiaBan1(gia);

                SanPham sp = new SanPham();
                sp.setTenSP(tensp);

                NhanHieu nh = new NhanHieu();
                nh.setTen(tennh);

                KichCo kc = new KichCo();
                kc.setTenKichCo(tenkc);

                MauSac ms = new MauSac();
                ms.setTenMauSac(tenms);

                HoaDon hd = new HoaDon();
                hd.setTongTienHoaDon(tongtiencuahoadon);

                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setSoLuong(soluong);
                hdct.setGia_SPCT(gia);
                hdct.setIdSPCT(spct);
                hdct.setHoaDon(hd);
                spct.setTenSanPham(sp);
                spct.setTenNhanHieu(nh);
                spct.setTenMauSac(ms);
                spct.setTenKichCo(kc);
                listHDCT.add(hdct);
            }
            return listHDCT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDonChiTiet> searchTraHang(String ma) {
        ArrayList<HoaDonChiTiet> listHDCT = new ArrayList<>();
        String sql = "SELECT SanPhamChiTiet.ma, SanPham.ten,HoaDonChiTiet.soluong,"
                + "HoaDonChiTiet.gia_spct,HoaDon.ma AS 'maHD',\n"
                + "HoaDon.tongtiencuahoadon FROM HoaDon\n"
                + "JOIN HoaDonChiTiet ON HoaDon.id = HoaDonChiTiet.id_hoadon\n"
                + "JOIN SanPhamChiTiet ON SanPhamChiTiet.id = HoaDonChiTiet.id_spct\n"
                + "JOIN SanPham ON SanPham.id = SanPhamChiTiet.id_sanpham WHERE SanPhamChiTiet.ma\n";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                String mahd = rs.getString("ma");
                String mahoadon = rs.getString("maHD");
                String tensp = rs.getString("ten");
                Integer soluong = rs.getInt("soluong");
                BigDecimal gia = rs.getBigDecimal("giaa");
                BigDecimal tongtiencuahoadon = rs.getBigDecimal("tongtiencuahoadon");

                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setMa(mahd);
                spct.setGiaBan1(gia);

                SanPham sp = new SanPham();
                sp.setTenSP(tensp);

                HoaDon hd = new HoaDon();
                hd.setMa(mahoadon);
                hd.setTongTienHoaDon(tongtiencuahoadon);

                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setSoLuong(soluong);
                hdct.setIdSPCT(spct);
                hdct.setHoaDon(hd);
                spct.setTenSanPham(sp);
                listHDCT.add(hdct);
            }
            return listHDCT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updateSoLuongTraHang(String ma, int soluong) {
        String sql = "UPDATE SanPhamChiTiet SET WHERE ma = ?";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, soluong);
            ps.setString(2, ma);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
