/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import java.sql.*;
import com.raven.classmodel.HinhThucThanhToan;

/**
 *
 * @author ADMIN
 */
public class HinhThucThanhToanRepon {

    public int addHTTT(HinhThucThanhToan httt) {
        String sql = """
                     INSERT INTO HinhThucThanhToan([ma],[magiaodich],[hinhthuc],[trangthai],[id_hoadon],[id_thanhtoan],[ngaytao])
                     VALUES(?,?,?,?,?,?,?)
                     """;
        int rowsInserted = 0;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            rowsInserted = ps.executeUpdate();
            ps.setObject(1, httt.getMa());
            ps.setObject(2, httt.getMagd());
            ps.setObject(3, httt.getTen());
            ps.setObject(4, httt.getTrangthai()== 0 ? "Chờ Thanh Toán" : "Đã Thanh Toán");
            ps.setObject(5, httt.getId_hoaDon().getId());
            ps.setObject(6, httt.getId_thanhToan().getId());
            ps.setObject(7, httt.getNgayTao());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsInserted;
    }
}
