/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classmodel.ChucVu;


import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.UUID;

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

        List<ChucVu> listCV = new ArrayList();
        sql = "SELECT id , ma , tencv FROM ChucVu  ";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ChucVu cv = new ChucVu(
                        UUID.fromString(rs.getString(1)),
                        rs.getString(2),
                        rs.getString(3));
                listCV.add(cv);
            }
            return listCV;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

 

}
