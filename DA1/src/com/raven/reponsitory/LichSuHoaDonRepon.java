/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classmodel.ChiTietSanPham;
import com.raven.classmodel.HoaDon;
import com.raven.classmodel.HoaDonChiTiet;
import com.raven.classmodel.KhachHang;
import com.raven.classmodel.LichSuHoaDon;
import com.raven.classmodel.NhanVien;
import com.raven.classmodel.SanPham;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class LichSuHoaDonRepon {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<LichSuHoaDon> getAll() {
        List<LichSuHoaDon> listLSHD = new ArrayList<>();
        String sql = "SELECT HoaDonChiTiet.stt, SanPhamChiTiet.ma, SanPham.ten, SanPhamChiTiet.soluong, "
                + "SanPhamChiTiet.gia, HoaDon.tongtiencuahoadon,"
                + "HoaDon.tinhtrang,LichSuHoaDon.lidohuy FROM HoaDon\n"
                + "JOIN HoaDonChiTiet ON HoaDon.id = HoaDonChiTiet.id_hoadon\n"
                + "JOIN LichSuHoaDon ON HoaDon.id = LichSuHoaDon.id_hoadon\n"
                + "JOIN SanPhamChiTiet ON SanPhamChiTiet.id = HoaDonChiTiet.id_sanphamct\n"
                + "JOIN SanPham ON SanPham.id = SanPhamChiTiet.id_sanpham\n"
                + "JOIN KhuyenMaiChiTiet ON KhuyenMaiChiTiet.id_sanphamchitiet = SanPhamChiTiet.id";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer stt = rs.getInt("stt");
                String maspct = rs.getString("ma");
                String tensp = rs.getString("ten");
                Integer soluong = rs.getInt("soluong");
                BigDecimal gia = rs.getBigDecimal("gia");
                BigDecimal tongtien = rs.getBigDecimal("tongtiencuahoadon");
                Integer tinhtrang = rs.getInt("tinhtrang");
                String lidohuy = rs.getString("lidohuy");

                HoaDon hd = new HoaDon();
                hd.setTongTienHoaDon(tongtien);
                hd.setTinhTrang(tinhtrang);

                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setStt(stt);

                ChiTietSanPham spct = new ChiTietSanPham();
                spct.setMa(maspct);
                spct.setSoLuong(soluong);
                spct.setGia(gia);

                SanPham spham = new SanPham();
                spham.setTen(tensp);

                LichSuHoaDon lshd = new LichSuHoaDon();
                lshd.setHoaDon(hd);
                lshd.setLiDoHuy(lidohuy);
                lshd.setHdct(hdct);
                lshd.setSpct(spct);
                lshd.setSp(spham);
                listLSHD.add(lshd);
            }
            return listLSHD;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDon> getList() {
        List<HoaDon> listHD = new ArrayList<>();
        String sql = "SELECT HoaDon.stt, HoaDon.ma, NhanVien.ten AS 'tenNV', KhachHang.ten AS 'tenKH',"
                + " ngaytaohoadon, tinhtrang FROM HoaDon \n"
                + "JOIN NhanVien ON NhanVien.id = HoaDon.id_nhanvien\n"
                + "JOIN KhachHang ON KhachHang.id = HoaDon.id_khachhang";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer stt = rs.getInt("stt");
                String ma = rs.getString("ma");
                String tennv = rs.getString("tenNV");
                String tenkh = rs.getString("tenKH");
                Date ngaytaohd = rs.getDate("ngaytaohoadon");
                Integer tinhtrang = rs.getInt("tinhtrang");

                KhachHang kh = new KhachHang();
                kh.setTen(tenkh);

                NhanVien nv = new NhanVien();
                nv.setTen(tennv);

                HoaDon hd = new HoaDon();
                hd.setStt(stt);
                hd.setMa(ma);
                hd.setNgaytaohoadon(ngaytaohd);
                hd.setTinhTrang(tinhtrang);
                hd.setIdKhachHang(kh);
                hd.setIdNhanVien(nv);
                listHD.add(hd);
            }
            return listHD;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
