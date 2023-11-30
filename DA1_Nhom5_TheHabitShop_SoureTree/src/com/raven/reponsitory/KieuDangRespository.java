/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classmodel.KieuDang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ThanhDat
 */
public class KieuDangRespository {
       public ArrayList<KieuDang> getAll() {
        String sql = """
                         SELECT [id]
                               ,[ma]
                               ,[ten]
                               ,[id_nguoitao]
                               ,[ngaytao]
                           FROM [dbo].[KieuDang]
                         ORDER BY [ngaytao] DESC;
                         """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            ArrayList<KieuDang> listMS = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KieuDang k = new KieuDang(rs.getString("ma"), rs.getString("ten"));
                listMS.add(k);
            }
            return listMS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
       
       public KieuDang getKieuDangbyTen(String ten) {
        List<KieuDang> listkc = new ArrayList();
        String sql = """
                     SELECT [id]
                                                    ,[ma]
                                                    ,[ten]
                                                    ,[id_nguoitao]
                                                    ,[ngaytao]
                                                FROM [dbo].[KieuDang]
                                               WHERE ten like ?
                    """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, ten);
            ps.execute();

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KieuDang k = new KieuDang(rs.getString("id"),rs.getString("ma"), rs.getString("ten"));
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

    public int AddKG(KieuDang d) {
        String sql = """
                        INSERT INTO [dbo].[KieuDang]
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

    public int xoaKG(String ma) {
        String sql = """
                     DELETE FROM [dbo].[KieuDang]
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
    
    public int suaKG(KieuDang ms, String ma){
          String sql = """
                UPDATE [dbo].[KieuDang]
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
