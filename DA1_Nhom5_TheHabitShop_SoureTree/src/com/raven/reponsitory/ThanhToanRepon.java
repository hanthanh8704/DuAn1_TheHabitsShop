/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import java.sql.*;
import com.raven.classmodel.ThanhToan;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ThanhToanRepon {

//    public List<ThanhToan> getAll(){
//        String sql = "";
//    }
    
    public int addTT(ThanhToan tt) {
        String sql = """
                     INSERT INTO ThanhToan([ma],[tongtien],[magiaodich],[trangthai],[ngaytao])
                     VALUES(?,?,?,?,?,?,?)
                     """;
        int rowsInserted = 0;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            rowsInserted = ps.executeUpdate();
            ps.setObject(1, tt.getMa());
            ps.setObject(2, tt.getTongtien());
            ps.setObject(3, tt.getMagd());
            ps.setObject(4, tt.getTrangthai() == 0 ? "Chờ Thanh Toán" : "Đã Thanh Toán");
            ps.setObject(5, tt.getNgaytao());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsInserted;
    }
}
