/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classmodel.ChucVu;
import com.raven.classmodel.NhanVien;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class CheckLogin {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public NhanVien checkLogin(String manv) {
        List<NhanVien> listNV = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = """
                        SELECT NhanVien.ma,matkhau,ChucVu.tencv,NhanVien.id, NhanVien.ten FROM NhanVien
                        JOIN ChucVu ON ChucVu.id = NhanVien.id_chucvu WHERE NhanVien.ma = ?
                     """;

        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, manv);
            rs = ps.executeQuery();

            while (rs.next()) {
                String tennv = rs.getString("ten");
                String ma = rs.getString("ma");
                String matkhau = rs.getString("matkhau");
                String tencv = rs.getString("tencv");
                String id = rs.getString("id");
                UUID uuid = UUID.fromString(id);

                ChucVu cv = new ChucVu();
                cv.setTenCV(tencv);

                NhanVien nv = new NhanVien();
                nv.setId(uuid);
                nv.setMaNV(ma);
                nv.setTenNV(tennv);
                nv.setMatKhau(matkhau);
                nv.setId_chucVu(cv);
                listNV.add(nv);
            }

            return listNV.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
