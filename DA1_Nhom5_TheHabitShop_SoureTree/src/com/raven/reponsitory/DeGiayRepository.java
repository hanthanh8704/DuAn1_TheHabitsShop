/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;


import com.raven.classmodel.DayGiay;
import com.raven.classmodel.DeGiay;
import com.raven.classmodel.KichCo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ThanhDat
 */
public class DeGiayRepository {
   public ArrayList<DeGiay> getAll() {
        String sql = """
                         SELECT [id]
                               ,[ma]
                               ,[ten]
                               ,[id_nguoitao]
                               ,[ngaytao]
                           FROM [dbo].[DeGiay]
                         ORDER BY [ngaytao] DESC;
                         """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            ArrayList<DeGiay> listMS = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DeGiay m = new DeGiay(rs.getString("ma"), rs.getString("ten"));
                listMS.add(m);
            }
            return listMS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
   
   public DeGiay getdeGiaybyTen(String ten) {
        List<DeGiay> listkc = new ArrayList();
        String sql = """
                     SELECT [id]
                                                    ,[ma]
                                                    ,[ten]
                                                    ,[id_nguoitao]
                                                    ,[ngaytao]
                                                FROM [dbo].[DeGiay]
                                               WHERE ten like ?
                    """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, ten);
            ps.execute();

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DeGiay k = new DeGiay(rs.getString("id"),rs.getString("ma"), rs.getString("ten"));
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

    public int AddDG(DeGiay dg) {
        String sql = """
                        INSERT INTO [dbo].[DeGiay]
                                   ([ma]
                                   ,[ten]
                                  )
                             VALUES
                                   (?,?)
                        """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, dg.getMa());
            ps.setObject(2, dg.getTen());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int xoaDG(String ma){
        String sql = """
                     DELETE FROM [dbo].[DeGiay]
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
    
    public int suaDeGiay(DeGiay ms, String ma){
          String sql = """
                UPDATE [dbo].[DeGiay]
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
