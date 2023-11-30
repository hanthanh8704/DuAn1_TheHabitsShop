/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classmodel.DayGiay;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ThanhDat
 */
public class DayGiayRepository {

    public ArrayList<DayGiay> getAll() {
        String sql = """
                         SELECT [id]
                               ,[ma]
                               ,[ten]
                               ,[id_nguoitao]
                               ,[ngaytao]
                           FROM [dbo].[DayGiay]
                          ORDER BY [ngaytao] DESC;
                         """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            ArrayList<DayGiay> listMS = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DayGiay k = new DayGiay(rs.getString("ma"), rs.getString("ten"));
                listMS.add(k);
            }
            return listMS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public DayGiay getDayGiaybyTen(String ten) {
        List<DayGiay> listdg = new ArrayList();
        String sql = """
                     SELECT [id]
                                                    ,[ma]
                                                    ,[ten]
                                                    ,[id_nguoitao]
                                                    ,[ngaytao]
                                                FROM [dbo].[DayGiay]
                                               WHERE ten like ?
                    """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, ten);
            ps.execute();

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DayGiay k = new DayGiay(rs.getString("id"), rs.getString("ma"), rs.getString("ten"));
                listdg.add(k);
            }
            if (!listdg.isEmpty()) {
                return listdg.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public int AddDay(DayGiay d) {
        String sql = """
                        INSERT INTO [dbo].[DayGiay]
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

    public int xoaD(String ma) {
        String sql = """
                  DELETE FROM [dbo].[DayGiay]
                                 WHERE ma = ?
                     """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }
    
    public int suaDayGiay(DayGiay ms, String ma){
          String sql = """
                UPDATE [dbo].[DayGiay]
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
