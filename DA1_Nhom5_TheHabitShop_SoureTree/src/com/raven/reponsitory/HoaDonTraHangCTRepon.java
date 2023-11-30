/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classmodel.ChiTietSanPham;
import com.raven.classmodel.HoaDonTraHang;
import com.raven.classmodel.HoaDonTraHangCT;
import com.raven.classmodel.KichCo;
import com.raven.classmodel.MauSac;
import com.raven.classmodel.NhanHieu;
import com.raven.classmodel.SanPham;
import com.raven.classmodel.SanPhamChiTiet;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class HoaDonTraHangCTRepon {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<HoaDonTraHangCT> getList() {
        List<HoaDonTraHangCT> listHDTHCT = new ArrayList<>();
        String sql = "SELECT HoaDonTraHang.ma, SanPham.ten AS 'TenSP', NhanHieu.ten AS 'TenNH',"
                + " HoaDonTraHangCT.gia_spkhimuahang, MauSac.ten AS 'TenMS', KichCo.ten AS 'TenKC', HoaDonTraHangCT.soluong, HoaDonTraHang.tienhoantra "
                + "FROM HoaDonTraHang "
                + "JOIN HoaDonTraHangCT ON HoaDonTraHangCT.id_hdth = HoaDonTraHang.id "
                + "JOIN SanPhamChiTiet ON SanPhamChiTiet.id = HoaDonTraHangCT.id_spct "
                + "JOIN SanPham ON SanPham.id = SanPhamChiTiet.id_sanpham "
                + "JOIN NhanHieu ON NhanHieu.id = SanPhamChiTiet.id_nhanhieu "
                + "JOIN MauSac ON MauSac.id = SanPhamChiTiet.id_mausac "
                + "JOIN KichCo ON KichCo.id = SanPhamChiTiet.id_kichco";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String ma = rs.getString("ma");
                String tenSP = rs.getString("TenSP");
                String tenNH = rs.getString("TenNH");
                String tenMS = rs.getString("TenMS");
                String tenKC = rs.getString("TenKC");
                Integer soluong = rs.getInt("soluong");
                BigDecimal gia = rs.getBigDecimal("gia_spkhimuahang");
                BigDecimal tienhoantra = rs.getBigDecimal("tienhoantra");

                SanPham sp = new SanPham();
                sp.setTenSP(tenSP);

                NhanHieu nh = new NhanHieu();
                nh.setTen(tenNH);

                MauSac ms = new MauSac();
                ms.setTenMauSac(tenMS);

                KichCo kc = new KichCo();
                kc.setTenKichCo(tenKC);

                HoaDonTraHang hdth = new HoaDonTraHang();
                hdth.setMa(ma);
                hdth.setTienHoanTra(tienhoantra);

                SanPhamChiTiet ctsp = new SanPhamChiTiet();
                ctsp.setTenSanPham(sp);
                ctsp.setTenNhanHieu(nh);
                ctsp.setTenMauSac(ms);
                ctsp.setTenKichCo(kc);

                HoaDonTraHangCT hdthct = new HoaDonTraHangCT();
                hdthct.setSoluong(soluong);
                hdthct.setGia_spkhimua(gia);
                hdthct.setHdth(hdth);
                hdthct.setCtsp(ctsp);
                listHDTHCT.add(hdthct);
            }
            return listHDTHCT;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDonTraHangCT> searchHDTHCT(String ma) {
        List<HoaDonTraHangCT> listHDTHCT = new ArrayList<>();
        String sql = "SELECT HoaDonTraHang.ma AS 'maHDTH', SanPham.ten AS 'TenSP', NhanHieu.ten AS 'TenNH', HoaDonTraHangCT.gia_spkhimuahang, MauSac.ten AS 'TenMS', KichCo.ten AS 'TenKC', HoaDonTraHangCT.soluong, HoaDonTraHang.tienhoantra "
                + "FROM HoaDonTraHang "
                + "JOIN HoaDonTraHangCT ON HoaDonTraHangCT.id_hdth = HoaDonTraHang.id "
                + "JOIN SanPhamChiTiet ON SanPhamChiTiet.id = HoaDonTraHangCT.id_spct "
                + "JOIN SanPham ON SanPham.id = SanPhamChiTiet.id_sanpham "
                + "JOIN NhanHieu ON NhanHieu.id = SanPhamChiTiet.id_nhanhieu "
                + "JOIN MauSac ON MauSac.id = SanPhamChiTiet.id_mausac "
                + "JOIN KichCo ON KichCo.id = SanPhamChiTiet.id_kichco "
                + "WHERE HoaDonTraHang.ma LIKE ?";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + ma + "%");
            try (ResultSet rs = ps.executeQuery()) {
                String maHDTH = rs.getString("maHDTH");
                String tenSP = rs.getString("TenSP");
                String tenNH = rs.getString("TenNH");
                String tenMS = rs.getString("TenMS");
                String tenKC = rs.getString("TenKC");
                Integer soluong = rs.getInt("soluong");
                BigDecimal gia = rs.getBigDecimal("gia_spkhimuahang");
                BigDecimal tienhoantra = rs.getBigDecimal("tienhoantra");

                SanPham sp = new SanPham();
                sp.setTenSP(tenSP);

                NhanHieu nh = new NhanHieu();
                nh.setTen(tenNH);

                MauSac ms = new MauSac();
                ms.setTenMauSac(tenMS);

                KichCo kc = new KichCo();
                kc.setTenKichCo(tenKC);

                HoaDonTraHang hdth = new HoaDonTraHang();
                hdth.setMa(maHDTH);
                hdth.setTienHoanTra(tienhoantra);

                SanPhamChiTiet ctsp = new SanPhamChiTiet();
                ctsp.setTenSanPham(sp);
                ctsp.setTenNhanHieu(nh);
                ctsp.setTenMauSac(ms);
                ctsp.setTenKichCo(kc);

                HoaDonTraHangCT hdthct = new HoaDonTraHangCT();
                hdthct.setSoluong(soluong);
                hdthct.setGia_spkhimua(gia);
                hdthct.setHdth(hdth);
                hdthct.setCtsp(ctsp);
                listHDTHCT.add(hdthct);
            }
            return listHDTHCT;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDonTraHangCT> getDSTH() {
        List<HoaDonTraHangCT> listHDTHCT = new ArrayList<>();
        String sql = "SELECT HoaDonTraHang.ma, SanPham.ten AS 'TenSP', NhanHieu.ten AS 'TenNH', HoaDonTraHangCT.gia_spkhimuahang, MauSac.ten AS 'TenMS', KichCo.ten AS 'TenKC', HoaDonTraHangCT.soluong, HoaDonTraHang.tienhoantra "
                + "FROM HoaDonTraHang "
                + "JOIN HoaDonTraHangCT ON HoaDonTraHangCT.id_hdth = HoaDonTraHang.id "
                + "JOIN SanPhamChiTiet ON SanPhamChiTiet.id = HoaDonTraHangCT.id_spct "
                + "JOIN SanPham ON SanPham.id = SanPhamChiTiet.id_sanpham "
                + "JOIN NhanHieu ON NhanHieu.id = SanPhamChiTiet.id_nhanhieu "
                + "JOIN MauSac ON MauSac.id = SanPhamChiTiet.id_mausac "
                + "JOIN KichCo ON KichCo.id = SanPhamChiTiet.id_kichco";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String ma = rs.getString("ma");
                String tenSP = rs.getString("TenSP");
                String tenNH = rs.getString("TenNH");
                String tenMS = rs.getString("TenMS");
                String tenKC = rs.getString("TenKC");
                Integer soluong = rs.getInt("soluong");
                BigDecimal gia = rs.getBigDecimal("gia_spkhimuahang");
                BigDecimal tienhoantra = rs.getBigDecimal("tienhoantra");

                SanPham sp = new SanPham();
                sp.setTenSP(tenSP);

                NhanHieu nh = new NhanHieu();
                nh.setTen(tenNH);

                MauSac ms = new MauSac();
                ms.setTenMauSac(tenMS);

                KichCo kc = new KichCo();
                kc.setTenKichCo(tenKC);

                HoaDonTraHang hdth = new HoaDonTraHang();
                hdth.setMa(ma);
                hdth.setTienHoanTra(tienhoantra);

                SanPhamChiTiet ctsp = new SanPhamChiTiet();
                ctsp.setTenSanPham(sp);
                ctsp.setTenNhanHieu(nh);
                ctsp.setTenMauSac(ms);
                ctsp.setTenKichCo(kc);

                HoaDonTraHangCT hdthct = new HoaDonTraHangCT();
                hdthct.setSoluong(soluong);
                hdthct.setGia_spkhimua(gia);
                hdthct.setHdth(hdth);
                hdthct.setCtsp(ctsp);
                listHDTHCT.add(hdthct);
            }
            return listHDTHCT;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int addSanPhamTraHang(HoaDonTraHangCT hdthct) {
        String sql = "INSERT INTO [dbo].[HoaDonTraHangCT]\n"
                + "           ,[ma]\n"
                + "           ,[soluong]\n"
                + "           ,[gia_spkhimuahang]\n"
                + "           ,[id_hdth]\n"
                + "           ,[id_spct]\n"
                + "             VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, hdthct.getMa());
            ps.setInt(2, hdthct.getSoluong());
            ps.setBigDecimal(3, hdthct.getGia_spkhimua()); // Assuming getGia_spkhimua() returns a double
            ps.setObject(4, hdthct.getHdth());  // Assuming getId_hdth() returns an int
            ps.setObject(5, hdthct.getCtsp());  // Assuming getId_spct() returns an int
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getidSPCT(String ma) {
        String idMauSac = "";
        String sql = """
                  String sql = "SELECT id FROM SanPhamChiTiet where ma = ?";
                 """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idMauSac = rs.getString(1);
            }
            return idMauSac;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
