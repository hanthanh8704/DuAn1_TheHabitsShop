/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classmodel.ChatLieu;
import com.raven.classmodel.ChiTietSanPham;
import com.raven.classmodel.DayGiay;
import com.raven.classmodel.DeGiay;
import com.raven.classmodel.HoaDon;
import com.raven.classmodel.HoaDonChiTiet;
import com.raven.classmodel.KichCo;
import com.raven.classmodel.KieuDang;
import com.raven.classmodel.MauSac;
import com.raven.classmodel.NhanHieu;
import com.raven.classmodel.SanPham;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class ChiTietSanPhamRepon {

 //   Connection conn = null;
 //   PreparedStatement ps = null;
 //   ResultSet rs = null;

//    public List<ChiTietSanPham> getAll() {
//        List<ChiTietSanPham> listCTSP = new ArrayList<>();
//        String sql = """
//                     SELECT SanPhamChiTiet.ma,SanPhamChiTiet.gia,SanPham.ten,SanPhamChiTiet.soluong,
//                     SanPhamChiTiet.id_mausac, MauSac.id AS 'ID_MauSac', MauSac.ten AS 'Ten_MauSac', 
//                     SanPhamChiTiet.id_chatlieu, ChatLieu.id AS 'ID_ChatLieu', ChatLieu.ten AS 'Ten_ChatLieu',
//                     SanPhamChiTiet.id_kichco, KichCo.id AS 'ID_KichCo', KichCo.ten AS 'Ten_KichCo', 
//                     SanPhamChiTiet.id_nhanhieu, NhanHieu.id AS 'ID_NhanHieu', NhanHieu.ten AS 'Ten_NhanHieu',
//                     SanPhamChiTiet.id_sanpham,SanPham.id AS 'ID_SanPham', SanPham.ten AS 'Ten_SanPham',
//                     SanPhamChiTiet.id_degiay,DeGiay.id AS 'ID_DeGiay', DeGiay.ten AS 'Ten_DeGiay',
//                     SanPhamChiTiet.id_daygiay,DayGiay.id AS 'ID_DayGiay', DayGiay.ten AS 'Ten_DayGiay',
//                     SanPhamChiTiet.id_anh,HinhAnh.id AS 'ID_HinhAnh', HinhAnh.ten AS 'Ten_HinhAnh',
//                     SanPhamChiTiet.id_kieudang,KieuDang.id AS 'ID_KieuDang', KieuDang.ten AS 'Ten_KieuDang'
//                     FROM SanPhamChiTiet
//                     JOIN MauSac ON MauSac.id = SanPhamChiTiet.id_mausac
//                     JOIN ChatLieu ON ChatLieu.id = SanPhamChiTiet.id_chatlieu
//                     JOIN KichCo ON KichCo.id = SanPhamChiTiet.id_kichco
//                     JOIN NhanHieu ON NhanHieu.id = SanPhamChiTiet.id_nhanhieu
//                     JOIN SanPham ON SanPham.id = SanPhamChiTiet.id_sanpham
//                     JOIN DeGiay ON DeGiay.id = SanPhamChiTiet.id_degiay
//                     JOIN DayGiay ON DayGiay.id = SanPhamChiTiet.id_daygiay
//                     JOIN HinhAnh ON HinhAnh.id = SanPhamChiTiet.id_anh
//                     JOIN KieuDang ON KieuDang.id = SanPhamChiTiet.id_kieudang;""";
//        try {
//            conn = DBConnect.getConnection();
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                String ma = rs.getString("ma");
//                String ten = rs.getString("ten");
//                BigDecimal gia = rs.getBigDecimal("gia");
//                Integer soluong = rs.getInt("soluong");
//                UUID id_mau = UUID.fromString(rs.getString("ID_MauSac"));
//                String tenmau = rs.getString("Ten_MauSac");
//                UUID id_chatlieu = UUID.fromString(rs.getString("ID_ChatLieu"));
//                String tenchatlieu = rs.getString("Ten_ChatLieu");
//                UUID id_kichco = UUID.fromString(rs.getString("ID_KichCo"));
//                String tenkickco = rs.getString("Ten_KichCo");
//                UUID id_nhanhieu = UUID.fromString(rs.getString("ID_NhanHieu"));
//                String tennhanhieu = rs.getString("Ten_NhanHieu");
//                UUID id_sanpham = UUID.fromString(rs.getString("ID_SanPham"));
//                String tensanpham = rs.getString("Ten_SanPham");
//                UUID id_degiay = UUID.fromString(rs.getString("ID_DeGiay"));
//                String tendegiay = rs.getString("Ten_DeGiay");
//                UUID id_dayday = UUID.fromString(rs.getString("ID_DayGiay"));
//                String tendaygiay = rs.getString("Ten_DayGiay");
//                UUID id_hinhanh = UUID.fromString(rs.getString("ID_HinhAnh"));
//                String tenhinhanh = rs.getString("Ten_HinhAnh");
//                UUID idkieudang = UUID.fromString(rs.getString("ID_KieuDang"));
//                String tenkieudang = rs.getString("Ten_KieuDang");
//
//                MauSac ms = new MauSac();
//                ms.setId(id_mau);
//                ms.setTen(tenmau);
//
//                ChatLieu cl = new ChatLieu();
//                cl.setId(id_chatlieu);
//                cl.setTen(tenchatlieu);
//
//                KichCo ks = new KichCo();
//                ks.setId(id_kichco);
//                ks.setTen(tenkickco);
//
//                NhanHieu nh = new NhanHieu();
//                nh.setId(id_nhanhieu);
//                nh.setTen(tennhanhieu);
//
//                SanPham spham = new SanPham();
//                spham.setId(id_sanpham);
//                spham.setTen(tensanpham);
//
//                DeGiay degiay = new DeGiay();
//                degiay.setId(id_degiay);
//                degiay.setTen(tendegiay);
//
//                DayGiay daygiay = new DayGiay();
//                daygiay.setId(id_dayday);
//                daygiay.setTen(tendaygiay);
//
//                KieuDang kg = new KieuDang();
//                kg.setId(idkieudang);
//                kg.setTen(tenkieudang);
//
//                ChiTietSanPham ctsp = new ChiTietSanPham();
//                ctsp.setMa(ma);
//                ctsp.setTen(ten);
//                ctsp.setId_sanPham(spham);
//                ctsp.setSoLuong(soluong);
//                ctsp.setGia(gia);
//                ctsp.setId_mausac(ms);
//                ctsp.setId_kichCo(ks);
//                ctsp.setId_nhanHieu(nh);
//                ctsp.setId_chatlieu(cl);
//                ctsp.setId_deGiay(degiay);
//                ctsp.setId_daygiay(daygiay);
//                ctsp.setId_kieuDang(kg);
//                listCTSP.add(ctsp);
//            }
//            return listCTSP;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null; // Add a return statement here in case of exception
//        }
//    }
//}

//    public List<ChiTietSanPham> getList() {
//        List<HoaDonChiTiet> listHDCT = new ArrayList<>();
//        String sql
//                = "SELECT HoaDonChiTiet.stt, SanPhamChiTiet.ma, SanPham.ten AS 'tenSP',"
//                + " NhanHieu.ten AS 'TenNH' , MauSac.ten 'TenMS',KichCo.ten AS 'TenKC',\n"
//                + "SanPhamChiTiet.soluong,SanPhamChiTiet.gia,"
//                + "HoaDon.tongtiencuahoadon ,HoaDon.tongtiensaukhigiamgia FROM SanPhamChiTiet\n"
//                + "JOIN HoaDonChiTiet ON HoaDonChiTiet.id_sanphamct = SanPhamChiTiet.id\n"
//                + "JOIN SanPham ON SanPham.id = SanPhamChiTiet.id_sanpham\n"
//                + "JOIN NhanHieu ON NhanHieu.id = SanPhamChiTiet.id_nhanhieu\n"
//                + "JOIN MauSac ON MauSac.id = SanPhamChiTiet.id_mausac\n"
//                + "JOIN KichCo ON KichCo.id = SanPhamChiTiet.id_kichco\n"
//                + "JOIN HoaDon ON HoaDon.id = HoaDonChiTiet.id_hoadon";
//        try {
//            conn = DBConnect.getConnection();
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Integer stt = rs.getInt("STT");
//                String ma = rs.getString("ma");
//                String tensp = rs.getString("TenSP");
//                BigDecimal gia = rs.getBigDecimal("gia");
//                Integer soluong = rs.getInt("soluong");
//                String tennh = rs.getString("TenNH");
//                String tenms = rs.getString("TenMS");
//                String tenkc = rs.getString("TenKC");
//                BigDecimal tongtienhoadon = rs.getBigDecimal("tongtiencuahoadon");
//                BigDecimal tongtiensaugiamgia = rs.getBigDecimal("tingtiensaukhigiamgia");
//
//                HoaDon hd = new HoaDon();
//                hd.setTongTienHoaDon(tongtienhoadon);
//                hd.setTongTienSauGiamGia(tongtiensaugiamgia);
//
//                ChiTietSanPham ctsp = new ChiTietSanPham();
//                ctsp.setMa(ma);
//                ctsp.setGia(gia);
//                ctsp.setSoLuong(soluong);
//
//                MauSac ms = new MauSac();
//                ms.setTen(tenms);
//
//                KichCo kc = new KichCo();
//                kc.setTen(tenkc);
//
//                NhanHieu nh = new NhanHieu();
//                nh.setTen(tennh);
//
//                SanPham spham = new SanPham();
//                spham.setTen(tensp);
//
//                listHDCT.add(hdct);
//            }
//            return listHDCT;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
    //}
}
