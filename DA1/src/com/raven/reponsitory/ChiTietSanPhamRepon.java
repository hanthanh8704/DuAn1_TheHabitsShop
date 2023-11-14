/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classmodel.ChatLieu;
import com.raven.classmodel.ChiTietSanPham;
import com.raven.classmodel.DayGiay;
import com.raven.classmodel.DeGiay;
import com.raven.classmodel.HinhAnh;
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

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<ChiTietSanPham> getAll() {
        ArrayList<ChiTietSanPham> listCTSP = new ArrayList<>();
        String sql = "SELECT SanPhamChiTiet.STT AS 'ST', SanPhamChiTiet.ma AS 'MaCTSP',SanPhamChiTiet.soluong,SanPhamChiTiet.gia\n"
                + "SanPhamChiTiet.id_mausac, MauSac.id AS 'ID_MauSac', MauSac.ten AS 'Ten_MauSac', \n"
                + "SanPhamChiTiet.id_chatlieu, ChatLieu.id AS 'ID_ChatLieu', ChatLieu.ten AS 'Ten_ChatLieu',\n"
                + "SanPhamChiTiet.id_kichco, KichCo.id AS 'ID_KichCo', KichCo.ten AS 'Ten_KichCo', \n"
                + "SanPhamChiTiet.id_nhanhieu, NhanHieu.id AS 'ID_NhanHieu', NhanHieu.ten AS 'Ten_NhanHieu',\n"
                + "SanPhamChiTiet.id_sanpham,SanPham.id AS 'ID_SanPham', SanPham.ten AS 'Ten_SanPham',\n"
                + "SanPhamChiTiet.id_degiay,DeGiay.id AS 'ID_DeGiay', DeGiay.ten AS 'Ten_DeGiay',\n"
                + "SanPhamChiTiet.id_daygiay,DayGiay.id AS 'ID_DayGiay', DayGiay.ten AS 'Ten_DayGiay',\n"
                + "SanPhamChiTiet.id_anh,HinhAnh.id AS 'ID_HinhAnh', HinhAnh.ten AS 'Ten_HinhAnh',\n"
                + "SanPhamChiTiet.id_kieudang,KieuDang.id AS 'ID_KieuDang', KieuDang.ten AS 'Ten_KieuDang'\n"
                + "FROM SanPhamChiTiet\n"
                + "JOIN MauSac ON MauSac.id = SanPhamChiTiet.id_mausac\n"
                + "JOIN ChatLieu ON ChatLieu.id = SanPhamChiTiet.id_chatlieu\n"
                + "JOIN KichCo ON KichCo.id = SanPhamChiTiet.id_kichco\n"
                + "JOIN NhanHieu ON NhanHieu.id = SanPhamChiTiet.id_nhanhieu\n"
                + "JOIN SanPham ON SanPham.id = SanPhamChiTiet.id_sanpham\n"
                + "JOIN DeGiay ON DeGiay.id = SanPhamChiTiet.id_degiay\n"
                + "JOIN DayGiay ON DayGiay.id = SanPhamChiTiet.id_daygiay\n"
                + "JOIN HinhAnh ON HinhAnh.id = SanPhamChiTiet.id_anh\n"
                + "JOIN KieuDang ON KieuDang.id = SanPhamChiTiet.id_kieudang;";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer stt = rs.getInt("STT");
                String ma = rs.getString("ma");
                BigDecimal gia = rs.getBigDecimal("gia");
                Integer soluong = rs.getInt("soluong");
                UUID id_mau = UUID.fromString(rs.getString("ID_MauSac"));
                String tenmau = rs.getString("Ten_MauSac");
                UUID id_chatlieu = UUID.fromString(rs.getString("ID_ChatLieu"));
                String tenchatlieu = rs.getString("Ten_ChatLieu");
                UUID id_kichco = UUID.fromString(rs.getString("ID_KichCo"));
                String tenkickco = rs.getString("Ten_KichCo");
                UUID id_nhanhieu = UUID.fromString(rs.getString("ID_NhanHieu"));
                String tennhanhieu = rs.getString("Ten_NhanHieu");
                UUID id_sanpham = UUID.fromString(rs.getString("ID_SanPham"));
                String tensanpham = rs.getString("Ten_SanPham");
                UUID id_degiay = UUID.fromString(rs.getString("ID_DeGiay"));
                String tendegiay = rs.getString("Ten_DeGiay");
                UUID id_dayday = UUID.fromString(rs.getString("ID_DayGiay"));
                String tendaygiay = rs.getString("Ten_DayGiay");
                UUID id_hinhanh = UUID.fromString(rs.getString("ID_HinhAnh"));
                String tenhinhanh = rs.getString("Ten_HinhAnh");
                UUID idkieudang = UUID.fromString(rs.getString("ID_KieuDang"));
                String tenkieudang = rs.getString("Ten_KieuDang");

                MauSac ms = new MauSac();
                ms.setId(id_mau);
                ms.setTen(tenmau);
                ChatLieu cl = new ChatLieu();
                cl.setId(id_chatlieu);
                cl.setTen(tenchatlieu);
                KichCo ks = new KichCo();
                ks.setId(id_kichco);
                ks.setTen(tenkickco);
                NhanHieu nh = new NhanHieu();
                nh.setId(id_nhanhieu);
                nh.setTen(tennhanhieu);
                SanPham spham = new SanPham();
                spham.setId(id_sanpham);
                spham.setTen(tensanpham);
                DeGiay degiay = new DeGiay();
                degiay.setId(id_degiay);
                degiay.setTen(tendegiay);
                DayGiay daygiay = new DayGiay();
                daygiay.setId(id_dayday);
                daygiay.setTen(tendaygiay);
                HinhAnh ha = new HinhAnh();
                ha.setId(id_hinhanh);
                ha.setTen(tenhinhanh);
                KieuDang kg = new KieuDang();
                kg.setId(idkieudang);
                kg.setTen(tenkieudang);

                ChiTietSanPham ctsp = new ChiTietSanPham();
                ctsp.setStt(stt);
                ctsp.setMa(ma);
                ctsp.setId_sanPham(spham);
                ctsp.setSoLuong(soluong);
                ctsp.setGia(gia);
                ctsp.setId_mausac(ms);
                ctsp.setId_kichCo(ks);
                ctsp.setId_nhanHieu(nh);
                ctsp.setId_kichCo(ks);
                ctsp.setId_chatlieu(cl);
                ctsp.setId_deGiay(degiay);
                ctsp.setId_daygiay(daygiay);
                ctsp.setId_hinhanh(ha);
                ctsp.setId_kieuDang(kg);
                listCTSP.add(ctsp);
            }
            return listCTSP;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Add a return statement here in case of exception
        }
    }
}
