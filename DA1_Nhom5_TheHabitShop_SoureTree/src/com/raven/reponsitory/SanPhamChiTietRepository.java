/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classinterface.SanPhamCTInf;
import com.raven.classmodel.SanPhamChiTiet;
import com.raven.classmodel.SanPhamChiTietRespose;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ThanhDat
 */
public class SanPhamChiTietRepository implements SanPhamCTInf {

    @Override
    public ArrayList<SanPhamChiTiet> getAll() {
        String sql = """
         SELECT  
                    SanPhamChiTiet.[ma],
                    SanPham.ten AS tenSP,
                    [soluong],
                    [giaa],
                    [trangthai],
                    mausac.ten AS tenMauSac,
                    ChatLieu.ten AS tenChatLieu,
                    KichCo.ten AS tenKichCo,
                    NhanHieu.ten AS tenNhanHieu,
                    [id_sanpham],
                    DeGiay.ten AS tenDeGiay,
                    DayGiay.ten AS tenDayGiay,
                    KieuDang.ten AS tenKieuDang,
                    SanPhamChiTiet.ngaytao AS ngayTao
                FROM [DuAn1_Nhom5_TheHatbitShop].[dbo].[SanPhamChiTiet] 
                JOIN SanPham ON SanPham.id = SanPhamChiTiet.id_sanpham
                JOIN MauSac ON MauSac.id = SanPhamChiTiet.id_mausac
                JOIN ChatLieu ON ChatLieu.id = SanPhamChiTiet.id_chatlieu
                JOIN KichCo ON KichCo.id = SanPhamChiTiet.id_kichco
                JOIN NhanHieu ON NhanHieu.id = SanPhamChiTiet.id_nhanhieu
                JOIN Degiay ON Degiay.id = SanPhamChiTiet.id_degiay
                JOIN Daygiay ON Daygiay.id = SanPhamChiTiet.id_daygiay
                JOIN KieuDang ON KieuDang.id = SanPhamChiTiet.id_kieudang
                ORDER BY ngayTao DESC;
    """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            ArrayList<SanPhamChiTiet> list = new ArrayList<>();
            while (rs.next()) {
                SanPhamChiTiet sp = new SanPhamChiTiet(
                        rs.getString("ma"),
                        rs.getString("tenSP"),
                        rs.getInt("soluong"),
                        rs.getInt("trangthai"),
                        rs.getBigDecimal("giaa"),
                        rs.getString("tenMauSac"),
                        rs.getString("tenChatLieu"),
                        rs.getString("tenKichCo"),
                        rs.getString("tenNhanHieu"),
                        rs.getString("id_sanpham"),
                        rs.getString("tenDeGiay"),
                        rs.getString("tenDayGiay"),
                        rs.getString("tenKieuDang")
                );
                list.add(sp);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
  public ArrayList<SanPhamChiTiet> selectPhanTrangAll(long trang) {
    String sql ="SELECT TOP 5 " +
"SanPhamChiTiet.ma, " +
"SanPham.ten AS tenSP, " +
"soluong, " +
"giaa, " +
"trangthai, " +
"mausac.ten AS tenMauSac, " +
"ChatLieu.ten AS tenChatLieu, " +
"KichCo.ten AS tenKichCo, " +
"NhanHieu.ten AS tenNhanHieu, " +
"id_sanpham, " +
"DeGiay.ten AS tenDeGiay, " +
"DayGiay.ten AS tenDayGiay, " +
"KieuDang.ten AS tenKieuDang, " +
"SanPhamChiTiet.ngaytao AS ngayTao " +
"FROM SanPhamChiTiet " +  // Sửa ở đây, không cần thêm phần [DuAn1_Nhom5_TheHatbitShop].[dbo.
"JOIN SanPham ON SanPham.id = SanPhamChiTiet.id_sanpham " +
"JOIN MauSac ON MauSac.id = SanPhamChiTiet.id_mausac " +
"JOIN ChatLieu ON ChatLieu.id = SanPhamChiTiet.id_chatlieu " +
"JOIN KichCo ON KichCo.id = SanPhamChiTiet.id_kichco " +
"JOIN NhanHieu ON NhanHieu.id = SanPhamChiTiet.id_nhanhieu " +
"JOIN Degiay ON Degiay.id = SanPhamChiTiet.id_degiay " +
"JOIN Daygiay ON Daygiay.id = SanPhamChiTiet.id_daygiay " +
"JOIN KieuDang ON KieuDang.id = SanPhamChiTiet.id_kieudang " +
"WHERE SanPhamChiTiet.ma NOT IN " +
"(SELECT TOP " + (trang * 5 - 5) + " SanPhamChiTiet.ma FROM SanPhamChiTiet ORDER BY ngayTao DESC) " +
"ORDER BY SanPhamChiTiet.ngayTao DESC";
;

    try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

        ArrayList<SanPhamChiTiet> list = new ArrayList<>();
        while (rs.next()) {
            Timestamp ngayTaoTimestamp = rs.getTimestamp("ngayTao");
            LocalDateTime ngayTao = ngayTaoTimestamp.toLocalDateTime();
            SanPhamChiTiet sp = new SanPhamChiTiet(
                    rs.getString("ma"),
                    rs.getString("tenSP"),
                    rs.getInt("soluong"),
                    rs.getInt("trangthai"),
                    rs.getBigDecimal("giaa"),
                    rs.getString("tenMauSac"),
                    rs.getString("tenChatLieu"),
                    rs.getString("tenKichCo"),
                    rs.getString("tenNhanHieu"),
                    rs.getString("id_sanpham"),
                    rs.getString("tenDeGiay"),
                    rs.getString("tenDayGiay"),
                    rs.getString("tenKieuDang")
            );
            list.add(sp);
        }
        return list;

    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}




    
    

    public ArrayList<SanPhamChiTiet> getByMaSanPham(String tenSanPham) {
        String sql = """
                 SELECT  SanPhamChiTiet.[id]
                          ,SanPhamChiTiet.[ma]
                          ,SanPham.ten AS tenSP
                          ,SanPhamChiTiet.[soluong]
                          ,SanPhamChiTiet.[giaa]
                          ,SanPhamChiTiet.[trangthai]
                          ,mausac.ten AS tenMauSac
                          ,ChatLieu.ten AS tenChatLieu
                          ,KichCo.ten AS tenKichCo
                          ,NhanHieu.ten AS tenNhanHieu
                          ,SanPhamChiTiet.[id_sanpham]
                          ,DeGiay.ten AS tenDeGiay
                          ,DayGiay.ten AS tenDayGiay
                          ,KieuDang.ten AS tenKieuDang
                  FROM [DuAn1_Nhom5_TheHatbitShop].[dbo].[SanPhamChiTiet] 
                  JOIN SanPham ON SanPham.id = SanPhamChiTiet.id_sanpham
                  JOIN MauSac ON MauSac.id = SanPhamChiTiet.id_mausac
                  JOIN ChatLieu ON ChatLieu.id = SanPhamChiTiet.id_chatlieu
                  JOIN KichCo ON KichCo.id = SanPhamChiTiet.id_kichco
                  JOIN NhanHieu ON NhanHieu.id = SanPhamChiTiet.id_nhanhieu
                  JOIN Degiay ON Degiay.id = SanPhamChiTiet.id_degiay
                  JOIN Daygiay ON Daygiay.id = SanPhamChiTiet.id_daygiay
                  JOIN KieuDang ON KieuDang.id = SanPhamChiTiet.id_kieudang
                  WHERE SanPham.ten LIKE ?
                 """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + tenSanPham + "%"); // Sử dụng "%" để tìm kiếm theo mẫu chứa
            ArrayList<SanPhamChiTiet> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet sp = new SanPhamChiTiet(
                        rs.getString("ma"),
                        rs.getString("tenSP"),
                        rs.getInt("soluong"),
                        rs.getInt("trangthai"),
                        rs.getBigDecimal("giaa"),
                        rs.getString("tenMauSac"),
                        rs.getString("tenChatLieu"),
                        rs.getString("tenKichCo"),
                        rs.getString("tenNhanHieu"),
                        rs.getString("id_sanpham"),
                        rs.getString("tenDeGiay"),
                        rs.getString("tenDayGiay"),
                        rs.getString("tenKieuDang")
                );
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
     public ArrayList<SanPhamChiTiet> getByTenSanPham(String tenSanPham) {
        String sql = """
                 SELECT  SanPhamChiTiet.[id]
                          ,SanPhamChiTiet.[ma]
                          ,SanPham.ten AS tenSP
                          ,SanPhamChiTiet.[soluong]
                          ,SanPhamChiTiet.[giaa]
                          ,SanPhamChiTiet.[trangthai]
                          ,mausac.ten AS tenMauSac
                          ,ChatLieu.ten AS tenChatLieu
                          ,KichCo.ten AS tenKichCo
                          ,NhanHieu.ten AS tenNhanHieu
                          ,SanPhamChiTiet.[id_sanpham]
                          ,DeGiay.ten AS tenDeGiay
                          ,DayGiay.ten AS tenDayGiay
                          ,KieuDang.ten AS tenKieuDang
                  FROM [DuAn1_Nhom5_TheHatbitShop].[dbo].[SanPhamChiTiet] 
                  JOIN SanPham ON SanPham.id = SanPhamChiTiet.id_sanpham
                  JOIN MauSac ON MauSac.id = SanPhamChiTiet.id_mausac
                  JOIN ChatLieu ON ChatLieu.id = SanPhamChiTiet.id_chatlieu
                  JOIN KichCo ON KichCo.id = SanPhamChiTiet.id_kichco
                  JOIN NhanHieu ON NhanHieu.id = SanPhamChiTiet.id_nhanhieu
                  JOIN Degiay ON Degiay.id = SanPhamChiTiet.id_degiay
                  JOIN Daygiay ON Daygiay.id = SanPhamChiTiet.id_daygiay
                  JOIN KieuDang ON KieuDang.id = SanPhamChiTiet.id_kieudang
                  WHERE SanPham.ten LIKE ?
                 """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1,  tenSanPham ); // Sử dụng "%" để tìm kiếm theo mẫu chứa
            ArrayList<SanPhamChiTiet> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet sp = new SanPhamChiTiet(
                        rs.getString("ma"),
                        rs.getString("tenSP"),
                        rs.getInt("soluong"),
                        rs.getInt("trangthai"),
                        rs.getBigDecimal("giaa"),
                        rs.getString("tenMauSac"),
                        rs.getString("tenChatLieu"),
                        rs.getString("tenKichCo"),
                        rs.getString("tenNhanHieu"),
                        rs.getString("id_sanpham"),
                        rs.getString("tenDeGiay"),
                        rs.getString("tenDayGiay"),
                        rs.getString("tenKieuDang")
                );
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getidMauSac(String mauSac) {
        String idMauSac = "";
        String sql = """
                 SELECT  id from MauSac where ten = ?
                 """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, mauSac);

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

    public String getidChatLieuc(String chatlieu) {
        String idchatlieu = "";
        String sql = """
                 SELECT  id from ChatLieu where ten = ?
                 """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, chatlieu);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idchatlieu = rs.getString(1);
            }
            return idchatlieu;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String getiKichCo(String kichco) {
        String idkichco = "";
        String sql = """
                 SELECT  id from KichCo where ten = ?
                 """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, kichco);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idkichco = rs.getString(1);
            }
            return idkichco;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String getidNhanHieu(String nhanhieu) {
        String idnhanhieu = "";
        String sql = """
                 SELECT  id from NhanHieu where ten = ?
                 """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, nhanhieu);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idnhanhieu = rs.getString(1);
            }
            return idnhanhieu;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String getidDeGiay(String degiay) {
        String iddegiay = "";
        String sql = """
                 SELECT  id from DeGiay where ten = ?
                 """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, degiay);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                iddegiay = rs.getString(1);
            }
            return iddegiay;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String getidDayGiay(String daygiay) {
        String iddaygiay = "";
        String sql = """
                 SELECT  id from DayGiay where ten = ?
                 """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, daygiay);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                iddaygiay = rs.getString(1);
            }
            return iddaygiay;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String getidSanPham(String sanpham) {
        String idsanpham = "";
        String sql = """
                 SELECT  id from SanPham where ten = ?
                 """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, sanpham);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idsanpham = rs.getString(1);
            }
            return idsanpham;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String getidKieuDang(String kieudang) {
        String idkieudang = "";
        String sql = """
                 SELECT  id from KieuDang where ten = ?
                 """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, kieudang);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idkieudang = rs.getString(1);
            }
            return idkieudang;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

//    @Override
    public int AddSP(SanPhamChiTietRespose spct) {
        String sql = """
      INSERT INTO [dbo].[SanPhamChiTiet]
                              ([ma]
                              ,[soluong]
                              ,[trangthai]
                              ,[giaa]
                              ,[id_mausac]
                              ,[id_chatlieu]
                              ,[id_kichco]
                              ,[id_nhanhieu]
                              ,[id_sanpham]
                              ,[id_degiay]
                              ,[id_daygiay]
                              ,[id_kieudang]
                              )
                        VALUES
                              (?,?,?,?,?,?,?,?,?,?,?,?)""";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            // Thiết lập giá trị cho các tham số
            ps.setObject(1, spct.getMa());
            ps.setObject(2, spct.getSoluong());
            ps.setObject(3, spct.getTrangthai());
            ps.setObject(4, spct.getGiaBan());
            ps.setObject(5, UUID.fromString(spct.getIdmauSac()));
            ps.setObject(6, UUID.fromString(spct.getIdchatLieu()));
            ps.setObject(7, UUID.fromString(spct.getIdkichCo()));
            ps.setObject(8, UUID.fromString(spct.getIdnhanHieu()));
            ps.setObject(9, UUID.fromString(spct.getIdsanpham()));
            ps.setObject(10, UUID.fromString(spct.getDeGiay()));
            ps.setObject(11, UUID.fromString(spct.getDayGiay()));
            ps.setObject(12, UUID.fromString(spct.getKieuDang()));

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
              return 0;

        }
      
    }

    @Override
    public int updateSP(SanPhamChiTietRespose spct, String ma) {
        String sql = """
      UPDATE [dbo].[SanPhamChiTiet]
      SET
          [ma] = ?,
          [soluong] = ?,
          [trangthai] = ?,
          [giaa] = ?,
          [id_mausac] = ?,
          [id_chatlieu] = ?,
          [id_kichco] = ?,
          [id_nhanhieu] = ?,
          [id_sanpham] = ?,
          [id_degiay] = ?,
          [id_daygiay] = ?,
          [id_kieudang] = ?
      WHERE [ma] = ?
      """;

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            // Thiết lập giá trị cho các tham số
            ps.setObject(1, spct.getMa());
            ps.setObject(2, spct.getSoluong());
            ps.setObject(3, spct.getTrangthai());
            ps.setObject(4, spct.getGiaBan());
            ps.setObject(5, UUID.fromString(spct.getIdmauSac()));
            ps.setObject(6, UUID.fromString(spct.getIdchatLieu()));
            ps.setObject(7, UUID.fromString(spct.getIdkichCo()));
            ps.setObject(8, UUID.fromString(spct.getIdnhanHieu()));
            ps.setObject(9, UUID.fromString(spct.getIdsanpham()));
            ps.setObject(10, UUID.fromString(spct.getDeGiay()));
            ps.setObject(11, UUID.fromString(spct.getDayGiay()));
            ps.setObject(12, UUID.fromString(spct.getKieuDang()));

            // Thiết lập mã sản phẩm chi tiết để xác định dòng cần cập nhật
            ps.setObject(13, ma);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int xoaSP(String ma) {
        String sql = """
                   
                DELETE FROM [dbo].[SanPhamChiTiet]
      WHERE ma = ?
                    """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
//    public List<SanPhamChiTiet> LocMauSac(){
//        String sql =
//    }

}
