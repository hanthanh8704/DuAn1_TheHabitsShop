/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classinterface.SanPhamInf;
import com.raven.classmodel.SanPham;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author ThanhDat
 */
public class SanPhamRespository implements SanPhamInf {

    @Override
    public ArrayList<SanPham> getAll() {

        String sql = """
            SELECT
                     SPCD.[ma],
                     SPCD.[ten],
                     COALESCE(SUM(SPCT.[soluong]), 0) AS soluongchitiet,
                     SPCT.[trangthai] AS trangthai,
                     SPCD.[ngaytao]
                 FROM
                     [dbo].[SanPham] AS SPCD
                 LEFT JOIN
                     [dbo].[SanPhamChiTiet] AS SPCT ON SPCD.[id] = SPCT.[id_sanpham]
                 GROUP BY
                     SPCD.[id],
                     SPCD.[ma],
                     SPCD.[ten],
                     SPCD.[id_nguoitao],
                     SPCD.[ngaytao],
                     SPCT.[trangthai]
                       ORDER BY ngaytao DESC;
             """;

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            ArrayList<SanPham> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString("ma"), rs.getString("ten"), rs.getInt("soluongchitiet"), rs.getInt("trangthai"));
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Phương thức kiểm tra sự tồn tại của tên sản phẩm
public boolean checkSPExistence(String tenSP) {
    String sql = "SELECT COUNT(*) FROM [dbo].[SanPham] WHERE [ten] = ?";
    
    try (Connection con = DBConnect.getConnection(); 
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, tenSP);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Trả về true nếu tên sản phẩm đã tồn tại
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return false; // Trả về false nếu có lỗi xảy ra
}

    public ArrayList<SanPham> getSelectPhanTrangSP(long trang) {

        String sql = "SELECT TOP 5 "
                + "SPCD.[ma], "
                + "SPCD.[ten], "
                + "SPCD.[ngaytao], "
                + "COALESCE(SUM(SPCT.[soluong]), 0) AS soluongchitiet, "
                + "SPCT.[trangthai] AS trangthai "
                + "FROM [dbo].[SanPham] AS SPCD "
                + "LEFT JOIN [dbo].[SanPhamChiTiet] AS SPCT ON SPCD.[id] = SPCT.[id_sanpham] "
                + "WHERE SPCD.[ma] NOT IN (SELECT TOP " + (trang * 5 - 5) + " SPCD.[ma] FROM [DuAn1_Nhom5_TheHatbitShop].[dbo].[SanPham] ORDER BY SPCD.[ngaytao] DESC) "
                + "GROUP BY SPCD.[ma], SPCD.[ten], SPCD.[ngaytao], SPCT.[trangthai] "
                + "ORDER BY SPCD.[ngaytao] DESC";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            ArrayList<SanPham> list = new ArrayList<>();
            while (rs.next()) {
                Timestamp ngayTaoTimestamp = rs.getTimestamp("ngaytao");
            LocalDateTime ngayTao = ngayTaoTimestamp.toLocalDateTime();
                SanPham sp = new SanPham(rs.getString("ma"), rs.getString("ten"), rs.getInt("soluongchitiet"), rs.getInt("trangthai"));

                list.add(sp);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public SanPham getsanPhambyTen(String ten) {
        List<SanPham> listkc = new ArrayList();
        String sql = """
                   SELECT SanPham.[id]
                                                                          ,SanPham.[ma]
                                                                          ,SanPham.[ten]
                                                                          ,SanPham.[id_nguoitao]
                                                                          ,SanPham.[ngaytao]
                                                                    FROM [DuAn1_Nhom5_TheHatbitShop].[dbo].[SanPham] 
                     WHERE ten LIKE ?
                    """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, ten);
            ps.execute();

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham k = new SanPham(rs.getString("id"), rs.getString("ma"), rs.getString("ten"));
                listkc.add(k);
            }
            if (!listkc.isEmpty()) {
                return listkc.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public int AddSP(SanPham sp) {
        String sql = """
                     INSERT INTO [dbo].[SanPham]
                                ([ma]
                                ,[ten]
                               )
                          VALUES
                                (?,?)
                     """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, sp.getMaSP());
            ps.setObject(2, sp.getTenSP());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateSP(SanPham sp, String ma) {
        String sql = """
                    UPDATE [dbo].[SanPham]
                       SET [ma] = ?
                          ,[ten] = ?
                         
                     WHERE ma =?
                    
                    """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, sp.getMaSP());
            ps.setObject(2, sp.getTenSP());
            ps.setObject(3, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

}
