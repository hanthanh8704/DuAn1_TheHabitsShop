/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classmodel.KichCo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ThanhDat
 */
public class KichCoRepository {

    public ArrayList<KichCo> getAll() {
        String sql = """
                         SELECT [id]
                               ,[ma]
                               ,[ten]
                               ,[id_nguoitao]
                               ,[ngaytao]
                           FROM [dbo].[KichCo]
                        ORDER BY [ngaytao] DESC;
                         """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            ArrayList<KichCo> listMS = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KichCo k = new KichCo(rs.getString("ma"), rs.getString("ten"));
                listMS.add(k);
            }
            return listMS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public KichCo getkichCobyTen(String ten) {
        List<KichCo> listkc = new ArrayList();
        String sql = """
                     SELECT [id]
                                                    ,[ma]
                                                    ,[ten]
                                                    ,[id_nguoitao]
                                                    ,[ngaytao]
                                                FROM [dbo].[KichCo]
                                               WHERE ten like ?
                    """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, ten);
            ps.execute();

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KichCo k = new KichCo(rs.getString("id"), rs.getString("ma"), rs.getString("ten"));
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

    public int AddKC(KichCo kc) {
        String sql = """
                        INSERT INTO [dbo].[KichCo]
                                   ([ma]
                                   ,[ten]
                                  )
                             VALUES
                                   (?,?)
                        """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, kc.getMaKichCo());
            ps.setObject(2, kc.getTenKichCo());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int xoaKC(String ma) {
        String sql = """
                     DELETE FROM [dbo].[KichCo]
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
    
    public int suaKC(KichCo ms, String ma){
          String sql = """
                UPDATE [dbo].[KichCo]
                SET [ma] = ?,
                    [ten] = ?
                WHERE [ma] = ?
                """;
    try {
        Connection con = DBConnect.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setObject(1, ms.getMaKichCo());
        ps.setObject(2, ms.getTenKichCo());
        ps.setObject(3, ma); // Thêm tham số cho điều kiện WHERE
        return ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
    }
}
