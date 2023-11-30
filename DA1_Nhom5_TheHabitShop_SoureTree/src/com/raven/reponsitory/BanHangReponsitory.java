/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classmodel.ChatLieu;
import com.raven.classmodel.HinhThucThanhToan;
import com.raven.classmodel.HoaDon;
import com.raven.classmodel.HoaDonChiTiet;
import com.raven.classmodel.KhachHang;
import com.raven.classmodel.KichCo;
import com.raven.classmodel.MauSac;
import com.raven.classmodel.NhanHieu;
import com.raven.classmodel.NhanVien;
import com.raven.classmodel.SanPham;
import com.raven.classmodel.SanPhamChiTiet;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class BanHangReponsitory {

    public List<SanPhamChiTiet> getAllSP() {
        String sql = """
                     SELECT SanPhamChiTiet.ma,SanPham.ten,soluong,SanPhamChiTiet.giaa,MauSac.ten AS 'TenMS',ChatLieu.ten AS 'TenCL',KichCo.ten AS 'TenKC',NhanHieu.ten AS 'TenNH', SanPhamChiTiet.ngaytao AS 'NgayTao' FROM SanPhamChiTiet
                     JOIN SanPham ON SanPham.id = SanPhamChiTiet.id_sanpham
                     JOIN MauSac ON MauSac.id = SanPhamChiTiet.id_mausac
                      JOIN ChatLieu ON ChatLieu.id = SanPhamChiTiet.id_chatlieu
                          JOIN KichCo ON KichCo.id = SanPhamChiTiet.id_kichco
                            JOIN NhanHieu ON NhanHieu.id = SanPhamChiTiet.id_nhanhieu
                     	   ORDER BY NgayTao DESC
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            ArrayList<SanPhamChiTiet> list = new ArrayList<>();
            while (rs.next()) {
                String ma = rs.getString(1);
                String tensp = rs.getString(2);
                Integer soluong = rs.getInt(3);
                BigDecimal gia = rs.getBigDecimal(4);
                String tenms = rs.getString(5);
                String tencl = rs.getString(6);
                String tenkc = rs.getString(7);
                String tennh = rs.getString(8);

                SanPham sp = new SanPham();
                sp.setTenSP(tensp);

                MauSac ms = new MauSac();
                ms.setTenMauSac(tenms);

                ChatLieu cl = new ChatLieu();
                cl.setTen(tencl);

                KichCo kc = new KichCo();
                kc.setTenKichCo(tenkc);

                NhanHieu nh = new NhanHieu();
                nh.setTen(tennh);

                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setTenSanPham(sp);
                spct.setTenMauSac(ms);
                spct.setTenChatlieu(cl);
                spct.setTenNhanHieu(nh);
                spct.setTenKichCo(kc);
                spct.setMa(ma);
                spct.setSoLuong(soluong);
                spct.setGiaBan1(gia);
                list.add(spct);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

//    public List<HoaDon> getAllHD() {
//        List<HoaDon> listHD = new ArrayList<>();
//        String sql = """
//                        SELECT HoaDon.ma,HoaDon.ngaytaohoadon, NhanVien.ten AS 'tenNV', KhachHang.ten AS 'tenKH',
//                        HoaDon.trangthai FROM HoaDon JOIN HoaDonChiTiet ON HoaDonChiTiet.id_hoadon = HoaDon.id
//                        JOIN NhanVien ON NhanVien.id = HoaDon.id_nhanvien
//                        JOIN KhachHang ON KhachHang.id = HoaDon.id_khachhang WHERE HoaDon.trangthai = 0
//                     """;
//
//        try {
//            conn = DBConnect.getConnection();
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                //UUID id = UUID.fromString(rs.getString(1));
//                String mahd = rs.getString("ma");
//                Date ngaytaohd = rs.getDate("ngaytaohoadon");
//                Integer tinhtrang = rs.getInt("trangthai");
//                String tennv = rs.getString("tenNV");
//                String tenkh = rs.getString("tenKH");
//
//                NhanVien nv = new NhanVien();
//                nv.setTenNV(tennv);
//                KhachHang kh = new KhachHang();
//                kh.setTenKH(tenkh);
//
//                HoaDon hoaDon = new HoaDon();
//                hoaDon.setMa(mahd);
//                hoaDon.setNgaytaohoadon(ngaytaohd);
//                hoaDon.setTinhTrang(tinhtrang);
//                hoaDon.setIdNhanVien(nv);
//                hoaDon.setIdKhachHang(kh);
//                listHD.add(hoaDon);
//            }
//            return listHD;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public int addHoaDon(HoaDon hd) {
//        String sql = """
//                 INSERT INTO [dbo].[HoaDon]
//                            ([ma]
//                            ,[trangthai]
//                            ,[id_nhanvien]
//                            ,[ngaytao]
//                           )
//                      VALUES
//                            (?,?,?,?,?)
//                 """;
//
//        int rowsInserted = 0;
//
//        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setObject(1, hd.getMa());
//            ps.setObject(2, hd.getTinhTrang());
//            ps.setObject(3, hd.getIdNhanVien1());
//            ps.setObject(4, hd.getNgayTao());
//
//            rowsInserted = ps.executeUpdate();
//        } catch (SQLException e) {
//
//            System.err.println("Lỗi khi thêm hóa đơn: " + e.getMessage());
//        }
//
//        return rowsInserted;
//    }
    public String getidNhanVien(String nhanVien) {
        String idMauSac = "";
        String sql = """
                 SELECT  id from NhanVien where ten = ?
                 """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, nhanVien);

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

    public List<HoaDon> getAllHD() {
        List<HoaDon> listHD = new ArrayList<>();
        String sql = """
SELECT[HoaDon]. [id]
                                                    ,[HoaDon].[ma]
                                                    ,[tennguoinhan]
                                                    ,[HoaDon].[diachi]
                                                    ,[ngayxacnhan]
                                                    ,[phivc]
                                                    ,[ngaytaohoadon]
                                                    ,[ngayvanchuyen]
                                                    ,[ngaynhanhang]
                                                    ,[ngaythanhtoan]
                                                    ,[tongtiencuahoadon]
                                                    ,[tongtiencuahoadonsaugiamgia]
                                                    ,[HoaDon].[trangthai]
                                                    ,[ghichu]
                                                    ,[id_khachhang]
                                                    ,[id_nhanvien]
                                                    ,[HoaDon].[id_nguoitao]
                                                    ,[HoaDon].[ngaytao]
                                                    ,[HoaDon].[xoa_trangthai],
                       							 NhanVien.ten AS 'TenNV'
                                                FROM [dbo].[HoaDon]
                                             JOIN NhanVien ON NhanVien.id = HoaDon.id_nhanvien
                                              WHERE [HoaDon].trangthai = 0
                    """;

        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String mahd = rs.getString("ma");
                Date ngaytaohd = rs.getDate("ngaytao");
                Integer tinhtrang = rs.getInt("trangthai");
                String tennv = rs.getString("TenNV");
                NhanVien nv = new NhanVien();
                nv.setMaNV(mahd);

                HoaDon hoaDon = new HoaDon();
                hoaDon.setMa(mahd);
                hoaDon.setTennhanvien(tennv);
                hoaDon.setNgayTao(ngaytaohd);
                hoaDon.setTinhTrang(tinhtrang);
                listHD.add(hoaDon);
            }
            return listHD;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public HoaDon selectTop1DESC(){
//        String sql = """
//                        select top 1 * from hoaDon order by HoaDon.id desc
//                     """;
//         try(Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
//            ResultSet rs=ps.executeQuery();
//             while (rs.next()) {                 
//                 
//             }
//    }
    public int themSPGioHang(HoaDonChiTiet hdct) {
        String sql = "{CALL themSPGioHang(?,?,?,?,?)}";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            if (hdct != null) {
                ps.setObject(1, hdct.getMa());
                ps.setObject(2, hdct.getMahd());
                ps.setObject(3, hdct.getMaspct());
                ps.setObject(4, hdct.getId_nguoitaoString());
                ps.setObject(5, hdct.getSoLuong());
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int check5truong() {
        int sotruong = 0;
        String sql = """
                  select count (*) as soTruong from HoaDon where trangthai = 0
                     """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                sotruong = rs.getInt("soTruong");
            }
            return sotruong;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

    public String selectIdHDByMaHD(String maHD) {
        String id = "";
        String sql = """
                     select id from HoaDon where ma = ?
                     """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, maHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getString("id");

            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String selectIDSPCTByMaSPCT(String IDSPCT) {
        String id = "";
        String sql = """
                     select id from SanPhamChiTiet where ma = ?
                     """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, IDSPCT);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getString("id");

            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // gio hang
    public List<HoaDonChiTiet> selectHDCTByMaHD(String mahdct) {
        List<HoaDonChiTiet> listHDCT = new ArrayList<>();
        String sql = """
                        select SanPhamChiTiet.ma , SanPham.ten, HoaDonChiTiet.soluong, SanPhamChiTiet.giaa, HoaDonChiTiet.tongtiencuahdct from HoaDonChiTiet
                        JOIN SanPhamChiTiet On SanPhamChiTiet.id = HoaDonChiTiet.id_spct
                        JOIN HoaDon ON HoaDon.id = HoaDonChiTiet.id_hoadon
                        JOIN SanPham ON SanPham.id = SanPhamChiTiet.id_sanpham WHERE HoaDon.ma = ? AND HoaDonChiTiet.xoa_trangthai = 0
                     """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, mahdct);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setMaspct(rs.getString("ma"));
                hdct.setTensp(rs.getString("ten"));
                hdct.setSoLuong(rs.getInt("soluong"));
                hdct.setGia_SPCT(rs.getBigDecimal("giaa"));
                hdct.setTongTienCuaHDCT(rs.getBigDecimal("tongtiencuahdct"));
                listHDCT.add(hdct);
            }
            return listHDCT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // huy hoa hoa don co hang
    public int huyHoaDon(String hd_id, String hdct_id, String spct_id) {
        String sql = """
                        {CALL huyHoaDonCoHang(?,?,?)}
                     """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, hd_id);
            ps.setObject(2, hdct_id);
            ps.setObject(3, spct_id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // huy hoa dong khong co hang
    public int huyHoaDonKhongCoHang(String hd_id) {
        String sql = """
                        {CALL huyHoaDonKhongCoHang(?)}
                     """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, hd_id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String selecIDHoaDonCTByMaHoaDonAndMaSanPhamCT(String id, String id_spct) {
        String idmahd = "";
        String sql = """
                     select id from HoaDonChiTiet where id_hoadon = ? AND id_spct = ? AND xoa_trangthai = 0
                     """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            ps.setObject(2, id_spct);
            rs = ps.executeQuery();
            while (rs.next()) {
                idmahd = rs.getString("id");
            }
            System.out.println(id);
            System.out.println(idmahd);
            return idmahd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int xoaHoaDonChiTiet(String mahdct, String maspct) {
        String sql = """
                        {CALL xoaSanPhamGioHang(?,?)}
                     """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, mahdct);
            ps.setObject(2, maspct);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int suaHoaDonChiTiet(String mahdct, String maspct, String mahd,
            Integer soluongthaydoi) {
        String sql = """
                        {CALL suaSanPhamGioHang(?,?,?,?)}
                     """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, mahdct);
            ps.setObject(2, maspct);
            ps.setObject(3, mahd);
            ps.setObject(4, soluongthaydoi);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int selectSoLuongByHoaDonChiTietID(String mahdct) {
        int soluong = 0;
        String sql = """
                        SELECT soluong FROM HoaDonChiTiet WHERE id = ?
                     """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, mahdct);
            rs = ps.executeQuery();
            while (rs.next()) {
                soluong = rs.getInt("soluong");
            }
            return soluong;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int selectSoLuongBySanPhamChiTiet(String id_spct, String id_hdct) {
        int soluong = 0;
        String sql = """
                        SELECT SUM(SanPhamChiTiet.soluong + HoaDonChiTiet.soluong) AS 'SoLuongSanPham' FROM SanPhamChiTiet JOIN
                        HoaDonChiTiet ON HoaDonChiTiet.id_spct = SanPhamChiTiet.id
                        WHERE id_spct = ?
                        AND HoaDonChiTiet.id = ?
                     """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id_spct);
            ps.setObject(2, id_hdct);
            rs = ps.executeQuery();
            while (rs.next()) {
                soluong = rs.getInt("SoLuongSanPham");
            }
            return soluong;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
