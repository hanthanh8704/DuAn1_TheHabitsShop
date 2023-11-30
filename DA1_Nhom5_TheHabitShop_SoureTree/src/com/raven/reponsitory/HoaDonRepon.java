/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classinterface.HoaDonInteface;
import com.raven.classmodel.HinhThucThanhToan;
import com.raven.classmodel.HoaDon;
import com.raven.classmodel.HoaDonChiTiet;
import com.raven.classmodel.KhachHang;
import com.raven.classmodel.LichSuHoaDon;
import com.raven.classmodel.NhanVien;
import com.toedter.calendar.JDateChooser;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class HoaDonRepon implements HoaDonInteface {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    long count, soTrang, trang = 1;

    public List<HoaDon> phanTrang(long trang) {
        List<HoaDon> list = new ArrayList<>();
        sql = "SELECT HoaDon.ma AS 'MaHD', NhanVien.ma, KhachHang.ten, KhachHang.sdt, HoaDon.ngaytaohoadon, HoaDon.ngaythanhtoan,\n"
                + "HoaDon.tongtiencuahoadon, HinhThucThanhToan.hinhthuc, HoaDon.trangthai, HoaDon.ghichu,\n"
                + "NhanVien.id AS 'idNV', NhanVien.ten, KhachHang.id AS idKH, KhachHang.ten AS tenKH\n"
                + "FROM HoaDon\n"
                + "JOIN NhanVien ON HoaDon.id_nhanvien = NhanVien.id\n"
                + "JOIN KhachHang ON HoaDon.id_khachhang = KhachHang.id\n"
                + "JOIN HinhThucThanhToan ON HinhThucThanhToan.id_hoadon = HoaDon.id\n"
                + "ORDER BY HoaDon.ma LIMIT 10 OFFSET ?";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, (trang - 1) * 10);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String mahd = rs.getString("MaHD");
                    Date ngaytaohd = rs.getDate("ngaytaohoadon");
                    Date ngaythanhtoan = rs.getDate("ngaythanhtoan");
                    BigDecimal tongtien = rs.getBigDecimal("tongtiencuahoadon");
                    String HTThanhToan = rs.getString("hinhthuc");
                    Integer tinhtrang = rs.getInt("trangthai");
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
                    list.add(hoaDon);
                }
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<HoaDon> getAll() {
        ArrayList<HoaDon> hoaDonList = new ArrayList<>();
        sql = "SELECT HoaDon.ma AS 'MaHD', NhanVien.ma, KhachHang.ten, KhachHang.sdt,HoaDon.ngaytaohoadon, HoaDon.ngaythanhtoan,\n"
                + "HoaDon.tongtiencuahoadon,HinhThucThanhToan.hinhthuc, HoaDon.trangthai, HoaDon.ghichu,\n"
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
                Integer tinhtrang = rs.getInt("trangthai");
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

    public ArrayList<HoaDon> getList(int tinhtrang) {
        ArrayList<HoaDon> hoaDonList = new ArrayList<>();
        sql = "SELECT HoaDon.ma AS 'MaHD', NhanVien.ma,"
                + " KhachHang.ten, KhachHang.sdt, HoaDon.ngaytaohoadon, HoaDon.ngaythanhtoan,\n"
                + "HoaDon.tongtiencuahoadon,HinhThucThanhToan.hinhthuc, HoaDon.trangthai, HoaDon.ghichu,\n"
                + "NhanVien.id AS 'idNV', NhanVien.ten, KhachHang.id AS idKH, KhachHang.ten AS tenKH FROM HoaDon\n"
                + "JOIN NhanVien ON HoaDon.id_nhanvien = NhanVien.id\n"
                + "JOIN KhachHang ON HoaDon.id_khachhang = KhachHang.id\n"
                + "JOIN HinhThucThanhToan ON HinhThucThanhToan.id_hoadon = HoaDon.id WHERE HoaDon.trangthai = ?";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, tinhtrang);
            ps.execute();
            //b3: đọc sữ liệu trả về
            rs = ps.executeQuery();
            while (rs.next()) {
                //UUID id = UUID.fromString(rs.getString(1));
                String mahd = rs.getString("MaHD");
                Date ngaytaohd = rs.getDate("ngaytaohoadon");
                Date ngaythanhtoan = rs.getDate("ngaythanhtoan");
                BigDecimal tongtien = rs.getBigDecimal("tongtiencuahoadon");
                String HTThanhToan = rs.getString("hinhthuc");
                Integer tt = rs.getInt("trangthai");
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
                hoaDon.setTinhTrang(tt);
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

    public ArrayList<HoaDon> mouseClick() {
        ArrayList<HoaDon> hoaDonList = new ArrayList<>();
        sql = "SELECT HoaDon.ma AS 'MaHD', NhanVien.ma,NhanVien.ten AS 'TenNV', KhachHang.ten AS 'TenKH', KhachHang.sdt,HoaDon.diachi,HoaDon.ngaytaohoadon, HoaDon.ngaythanhtoan,\n"
                + "HoaDon.tongtiencuahoadon,HinhThucThanhToan.hinhthuc, HoaDon.trangthai, HoaDon.ghichu,\n"
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
                String tennv = rs.getString("TenNV");
                String HTThanhToan = rs.getString("hinhthuc");
                Integer tinhtrang = rs.getInt("trangthai");
                String ghichu = rs.getString("ghichu");
                UUID idnv = UUID.fromString(rs.getString("idNV"));
                String manv = rs.getString("ma");
                UUID idkh = UUID.fromString(rs.getString("idKH"));
                String tenkh = rs.getString("TenKH");
                String diachi = rs.getString("diachi");
                String sdt = rs.getString("sdt");

                NhanVien nv = new NhanVien();
                nv.setId(idnv);
                nv.setMaNV(manv);
                nv.setTenNV(tennv);
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
                hoaDon.setDiaChi(diachi);
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

    public ArrayList<HoaDon> mouseClickTraHang() {
        ArrayList<HoaDon> listTraHang = new ArrayList<>();
        String sql = "SELECT HoaDon.ma, NhanVien.ten AS 'TenNV', KhachHang.ten AS 'TenKH',HoaDon.diachi,HoaDon.tongtiencuahoadon FROM HoaDon\n"
                + "JOIN KhachHang ON KhachHang.id = HoaDon.id_khachhang\n"
                + "JOIN NhanVien ON HoaDon.id_nhanvien = NhanVien.id";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String mahd = rs.getString("ma");
                BigDecimal tongtien = rs.getBigDecimal("tongtiencuahoadon");
                String tennv = rs.getString("TenNV");
                String diachi = rs.getString("diachi");
                String tenkh = rs.getString("TenKH");
                NhanVien nv = new NhanVien();
                nv.setTenNV(tennv);
                KhachHang kh = new KhachHang();
                kh.setTenKH(tenkh);
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMa(mahd);
                hoaDon.setTongTienHoaDon(tongtien);
                hoaDon.setDiaChi(diachi);
                hoaDon.setIdNhanVien(nv);
                hoaDon.setIdKhachHang(kh);
                listTraHang.add(hoaDon);
            }
            return listTraHang;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDon> findDate(JDateChooser d1, JDateChooser d2) {
        List<HoaDon> listHD = new ArrayList<>();
        String sql = "SELECT HoaDon.ma AS 'MaHD', NhanVien.ma, KhachHang.ten, KhachHang.sdt, HoaDon.ngaytaohoadon, HoaDon.ngaythanhtoan, "
                + "HoaDon.tongtiencuahoadon, HinhThucThanhToan.hinhthuc, HoaDon.trangthai, HoaDon.ghichu, "
                + "NhanVien.id AS 'idNV', NhanVien.ten, KhachHang.id AS idKH, KhachHang.ten AS tenKH "
                + "FROM HoaDon "
                + "JOIN NhanVien ON HoaDon.id_nhanvien = NhanVien.id "
                + "JOIN KhachHang ON HoaDon.id_khachhang = KhachHang.id "
                + "JOIN HinhThucThanhToan ON HinhThucThanhToan.id_hoadon = HoaDon.id "
                + "WHERE HoaDon.ngaythanhtoan BETWEEN ? AND ?";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            if (d1.getDate() != null && d2.getDate() != null) {
                ps.setDate(1, new java.sql.Date(d1.getDate().getTime()));
                ps.setDate(2, new java.sql.Date(d2.getDate().getTime()));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String mahd = rs.getString("MaHD");
                    Date ngaytaohd = rs.getDate("ngaytaohoadon");
                    Date ngaythanhtoan = rs.getDate("ngaythanhtoan");
                    BigDecimal tongtien = rs.getBigDecimal("tongtiencuahoadon");
                    String HTThanhToan = rs.getString("hinhthuc");
                    int tinhtrang = rs.getInt("trangthai");
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
                    listHD.add(hoaDon);
                }
                return listHD;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // lịch sử hóa đơn
    public List<LichSuHoaDon> getListHD() {
        List<LichSuHoaDon> listLSHD = new ArrayList<>();
        String sql = """
    SELECT NhanVien.ma,HoaDon.ngaytaohoadon,LichSuHoaDon.hanhdong FROM LichSuHoaDon
JOIN HoaDon ON LichSuHoaDon.id_hoadon = HoaDon.id
JOIN NhanVien ON NhanVien.id = HoaDon.id_nhanvien
                     """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("ma");
                String hanhdong = rs.getString("hanhdong");
                Date ngaytaohd = rs.getDate("ngaytaohoadon");

                NhanVien nv = new NhanVien();
                nv.setMaNV(ma);

                HoaDon hd = new HoaDon();
                hd.setMa(hanhdong);
                hd.setIdNhanVien(nv);
                hd.setNgaytaohoadon(ngaytaohd);

                LichSuHoaDon lshd = new LichSuHoaDon();
                lshd.setHoaDon(hd);
                hd.setIdNhanVien(nv);
            }
            return listLSHD;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDon> getLichSuHD() {
        List<HoaDon> listHD = new ArrayList<>();
        String sql = "SELECT NhanVien.ma,HoaDon.ngaytaohoadon,HoaDon.trangthai FROM HoaDon\n"
                + "JOIN NhanVien ON NhanVien.id = HoaDon.id_nhanvien";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("ma");
                Date ngayxacnhan = rs.getDate("ngaytaohoadon");
                Integer tinhtrang = rs.getInt("trangthai");

                NhanVien nv = new NhanVien();
                nv.setMaNV(ma);
                HoaDon hd = new HoaDon();
                hd.setNgaytaohoadon(ngayxacnhan);
                hd.setTinhTrang(tinhtrang);
                listHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int addHoaDonCho(HoaDon hd) {
        String sql = """
                     INSERT INTO [dbo].[HoaDon]
                                (
                                [ma]
                                ,[tennguoinhan]
                                ,[diachi]
                                ,[ngayxacnhan]
                                ,[phivc]
                                ,[ngaytaohoadon]
                                ,[ngayvanchuyen]
                                ,[ngaynhanhang]
                                ,[ngaythanhtoan]
                                ,[tongtiencuahoadon]
                                ,[tongtiencuahoadonsaugiamgia]
                                ,[trangthai]
                                ,[ghichu]
                                ,[id_khachhang]
                                ,[id_nhanvien]
                                ,[id_nguoitao]
                                ,[ngaytao])
                          VALUES
                                (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
                     """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, hd.getMa());
            ps.setObject(2, hd.getTenNguoiNhan());
            ps.setObject(3, hd.getDiaChi());
            ps.setObject(4, hd.getNgayXacNhan());
            ps.setObject(5, hd.getPhiVC());
            ps.setObject(6, hd.getNgaytaohoadon());
            ps.setObject(7, hd.getNgayVanChuyen());
            ps.setObject(8, hd.getNgaynhanHang());
            ps.setObject(9, hd.getNgaythanhtoan());
            ps.setObject(10, hd.getTongTienHoaDon());
            ps.setObject(11, hd.getTongTienSauGiamGia());
            ps.setObject(12, hd.getTinhTrang());
            ps.setObject(13, hd.getGhichu());
            ps.setObject(14, hd.getId_khachhang());
            ps.setObject(15, hd.getNhanvien_id());
            ps.setObject(16, hd.getId_nguoiTao());
            ps.setObject(17, hd.getNgayTao());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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
    public List<HoaDon> getListByID(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDon findByMa(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
