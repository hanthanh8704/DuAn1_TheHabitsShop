/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classmodel.ChucVu;
import com.raven.service.DBConnect;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author MSI 15
 */
public class ChucVu_DAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<ChucVu> getAll() {

        List<ChucVu> listCV = new ArrayList<>();
        sql = "SELECT tencv FROM ChucVu";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ChucVu cv = new ChucVu(rs.getString(1));
                listCV.add(cv);
            }
            return listCV;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ChucVu selectById(String tenCV) {
        List<ChucVu> listCV = new ArrayList<>();
        sql = "SELECT tencv FROM ChucVu where tencv =?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, tenCV);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChucVu cv = new ChucVu(rs.getString(1));
                listCV.add(cv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}
