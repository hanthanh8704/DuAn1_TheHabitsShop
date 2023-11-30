/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classmodel.HinhThucThanhToan;
import com.raven.classmodel.HoaDon;
import com.raven.classmodel.KhachHang;
import com.raven.classmodel.NhanVien;
import com.toedter.calendar.JDateChooser;
import java.math.BigDecimal;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class ThongKeReponsitory {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";

    public List<HoaDon> getAll() {
        List<HoaDon> hoaDonList = new ArrayList<>();
        sql = "SELECT HoaDon.ma AS 'MaHD', NhanVien.ma, KhachHang.ten, KhachHang.sdt,HoaDon.ngaytaohoadon, HoaDon.ngaythanhtoan,\n"
                + "HoaDon.tongtiencuahoadon,HinhThucThanhToan.hinhthuc, HoaDon.tinhtrang, HoaDon.ghichu,\n"
                + "NhanVien.id AS 'idNV', NhanVien.ten, KhachHang.id AS idKH, KhachHang.ten AS tenKH FROM HoaDon\n"
                + "JOIN NhanVien ON HoaDon.id_nhanvien = NhanVien.id\n"
                + "JOIN KhachHang ON HoaDon.id_khachhang = KhachHang.id\n"
                + "JOIN HinhThucThanhToan ON HinhThucThanhToan.id_hoadon = HoaDon.id";
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
                String HTThanhToan = rs.getString("hinhthuc");
                Integer tinhtrang = rs.getInt("tinhtrang");
                String ghichu = rs.getString("ghichu");
                UUID idnv = UUID.fromString(rs.getString("idNV"));
                String manv = rs.getString("ma");
                UUID idkh = UUID.fromString(rs.getString("idKH"));
                String tenkh = rs.getString("ten");
                String sdt = rs.getString("sdt");

                NhanVien nv = new NhanVien();
                nv.setId(idnv);
                nv.setMaNV(manv);
                KhachHang kh = new KhachHang();
                kh.setID(idkh);
                kh.setTenKH(tenkh);
                kh.setSdt(sdt);
                HinhThucThanhToan httt = new HinhThucThanhToan();
                httt.setTen(HTThanhToan);

                HoaDon hoaDon = new HoaDon();
                hoaDon.setMa(mahd);
                hoaDon.setNgaytaohoadon(ngaytaohd);
                hoaDon.setNgaythanhtoan(ngaythanhtoan);
                hoaDon.setTongTienHoaDon(tongtien);
                hoaDon.setHttt(httt);
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

    public BigDecimal getDoanhThuCaNam(int year) {
        String sql = """
                   SELECT SUM(tongtiencuahoadon) FROM HoaDon WHERE YEAR(ngaytaohoadon) =?
                   """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public BigDecimal getDoanhThuTheoThang(int month) {
        String sql = """
                   select SUM(tongtiencuahoadon) from HoaDon WHERE MONTH(ngaytaohoadon)=?
                   """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, month);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public BigDecimal getDoanhThu7NgayGanNhat() {
        String sql = """
                   select SUM(tongtiencuahoadon) from HoaDon where ngaytaohoadon>=DATEADD(DAY,-7,GETDATE())
                   """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public BigDecimal getDoanhThuTheoNgay(int day) {
        String sql = """
                   select SUM(tongtiencuahoadon) from HoaDon WHERE day(ngaytaohoadon)=?
                   """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, day);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public BigDecimal getDoanhThuNgayHienTai(LocalDate toDay) {
        String sql = "SELECT SUM(tongtiencuahoadon) FROM HoaDon WHERE ngaytaohoadon=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Chuyển đổi LocalDate sang java.sql.Date
            java.sql.Date sqlDate = java.sql.Date.valueOf(toDay);
            // Đặt tham số với kiểu java.sql.Date
            ps.setDate(1, sqlDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public BigDecimal getDoanhThuThangNay(String firstDayInMonth, LocalDate today) {
        String sql = """
               select SUM(tongtiencuahoadon) from HoaDon where ngaytaohoadon between ? and ?
               """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, java.sql.Date.valueOf(firstDayInMonth));
            ps.setObject(2, java.sql.Date.valueOf(today));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<Object[]> LocTheoDoanhThuNgay(String from, String to) {
        String sql = "exec pr_LocDoanhThuTheoNgay(?,?)";
        String cols[] = {"Mã Hóa Đơn", "Ngày Tạo Hóa Đơn", "Doanh Thu"};
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setString(1, from);
            ps.setString(2, to);

            try (ResultSet rs = ps.executeQuery()) {
                List<Object[]> list = new ArrayList<>();
                while (rs.next()) {
                    Object[] vals = new Object[cols.length];
                    for (int i = 0; i < cols.length; i++) {
                        vals[i] = rs.getObject(cols[i]);
                    }
                    list.add(vals);
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Object[]> LocTheoDoanhThuThang(Integer selectedMonth) {
        String sql = "exec pr_LocDoanhThuTheoThang(?)";
        String cols[] = {"Mã Hóa Đơn", "Ngày Tạo Hóa Đơn", "Doanh Thu"};
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, selectedMonth);
            try (ResultSet rs = ps.executeQuery()) {
                List<Object[]> list = new ArrayList<>();
                while (rs.next()) {
                    Object[] vals = new Object[cols.length];
                    for (int i = 0; i < cols.length; i++) {
                        vals[i] = rs.getObject(cols[i]);
                    }
                    list.add(vals);
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Object[]> LocTheoDoanhThuNam(Integer selectedYear) {
        String sql = "exec pr_LocDoanhThuTheoThang(?)";
        String cols[] = {"Mã Hóa Đơn", "Ngày Tạo Hóa Đơn", "Doanh Thu"};
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, selectedYear);
            try (ResultSet rs = ps.executeQuery()) {
                List<Object[]> list = new ArrayList<>();
                while (rs.next()) {
                    Object[] vals = new Object[cols.length];
                    for (int i = 0; i < cols.length; i++) {
                        vals[i] = rs.getObject(cols[i]);
                    }
                    list.add(vals);
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDon> findDate(JDateChooser d1, JDateChooser d2) {
        List<HoaDon> listHD = new ArrayList<>();
        String sql = "SELECT HoaDon.ma AS 'MaHD', HoaDon.ngaytaohoadon, HoaDon.tongtiencuahoadon, "
                + "HoaDon.tongtiencuahoadon FROM HoaDon WHERE HoaDon.ngaythanhtoan BETWEEN ? AND ?";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            if (d1.getDate() != null && d2.getDate() != null) {
                ps.setDate(1, new java.sql.Date(d1.getDate().getTime()));
                ps.setDate(2, new java.sql.Date(d2.getDate().getTime()));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String mahd = rs.getString("MaHD");
                    Date ngaytaohd = rs.getDate("ngaytaohoadon");
                    BigDecimal tongtien = rs.getBigDecimal("tongtiencuahoadon");

                    HoaDon hoaDon = new HoaDon();
                    hoaDon.setMa(mahd);
                    hoaDon.setNgaytaohoadon(ngaytaohd);
                    hoaDon.setTongTienHoaDon(tongtien);
                    listHD.add(hoaDon);
                }
                return listHD;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
