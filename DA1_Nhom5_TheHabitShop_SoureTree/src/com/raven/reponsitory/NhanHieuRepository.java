/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classmodel.NhanHieu;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ThanhDat
 */
public class NhanHieuRepository {
     public ArrayList<NhanHieu> getAll() {
        String sql = """
                         SELECT [id]
                               ,[ma]
                               ,[ten]
                               ,[id_nguoitao]
                               ,[ngaytao]
                           FROM [dbo].[NhanHieu]
                          ORDER BY [ngaytao] DESC;
                         """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            ArrayList<NhanHieu> listMS = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanHieu k = new NhanHieu(rs.getString("id"),rs.getString("ma"), rs.getString("ten"));
                listMS.add(k);
            }
            return listMS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

     public NhanHieu getNhanHieubyTen(String ten) {
        List<NhanHieu> listkc = new ArrayList();
        String sql = """
                     SELECT [id]
                                                    ,[ma]
                                                    ,[ten]
                                                    ,[id_nguoitao]
                                                    ,[ngaytao]
                                                FROM [dbo].[NhanHieu]
                                               WHERE ten like ?
                    """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, ten);
            ps.execute();

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanHieu k = new NhanHieu(rs.getString("ma"), rs.getString("ten"));
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
    public int AddNH(NhanHieu d) {
        String sql = """
                        INSERT INTO [dbo].[NhanHieu]
                                   ([ma]
                                   ,[ten]
                                  )
                             VALUES
                                   (?,?)
                        """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, d.getMa());
            ps.setObject(2, d.getTen());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int xoaNH(String ma) {
        String sql = """
                     DELETE FROM [dbo].[NhanHieu]
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
    
    public int suaNH(NhanHieu ms, String ma){
          String sql = """
                UPDATE [dbo].[NhanHieu]
                SET [ma] = ?,
                    [ten] = ?
                WHERE [ma] = ?
                """;
    try {
        Connection con = DBConnect.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setObject(1, ms.getMa());
        ps.setObject(2, ms.getTen());
        ps.setObject(3, ma); // Thêm tham số cho điều kiện WHERE
        return ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
    }
}
