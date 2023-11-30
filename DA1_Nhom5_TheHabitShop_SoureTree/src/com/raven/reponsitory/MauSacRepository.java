/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classmodel.MauSac;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author ThanhDat
 */
public class MauSacRepository {

    public ArrayList<MauSac> getAll() {
        String sql = """
                          SELECT [id]
                                                          ,[ma]
                                                          ,[ten]
                                                          ,[id_nguoitao]
                                                          ,[ngaytao]
                                                      FROM [dbo].[MauSac]
                                                      ORDER BY [ngaytao] DESC;
                         """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            ArrayList<MauSac> listMS = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Timestamp ngayTaoTimestamp = rs.getTimestamp("ngaytao");
            LocalDateTime ngayTao = ngayTaoTimestamp.toLocalDateTime();
                MauSac m = new MauSac(rs.getString("id"),rs.getString("ma"), rs.getString("ten"));
                listMS.add(m);
            }
            return listMS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public MauSac getMauSacbyTen(String ten) {
        List<MauSac> listkc = new ArrayList();
        String sql = """
                     SELECT [id]
                                                    ,[ma]
                                                    ,[ten]
                                                    ,[id_nguoitao]
                                                    ,[ngaytao]
                                                FROM [dbo].[MauSac]
                                               WHERE ten like ?
                    """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, ten);
            ps.execute();

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MauSac k = new MauSac(rs.getString("id"),rs.getString("ma"), rs.getString("ten"));
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

    public int AddMS(MauSac ms) {
        String sql = """
                        INSERT INTO [dbo].[MauSac]
                                   ([ma]
                                   ,[ten]
                                  )
                             VALUES
                                   (?,?)
                        """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, ms.getMaMauSac());
            ps.setObject(2, ms.getTenMauSac());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int xoaMS(String ma){
        String sql = """
                     DELETE FROM [dbo].[MauSac]
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
    
    public int suaMS(MauSac ms, String ma){
          String sql = """
                UPDATE [dbo].[MauSac]
                SET [ma] = ?,
                    [ten] = ?
                WHERE [ma] = ?
                """;
    try {
        Connection con = DBConnect.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setObject(1, ms.getMaMauSac());
        ps.setObject(2, ms.getTenMauSac());
        ps.setObject(3, ma); // Thêm tham số cho điều kiện WHERE
        return ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
    }
}
