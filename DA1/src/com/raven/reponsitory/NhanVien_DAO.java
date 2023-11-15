/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classinterface.EduSysDAO;
import com.raven.classmodel.ChucVu;
import com.raven.classmodel.NhanVien;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author MSI 15
 */
public class NhanVien_DAO extends EduSysDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public void insert(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<NhanVien> selectAll() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        ArrayList<NhanVien> list = new ArrayList<>();
        sql = "SELECT stt, NhanVien.ma, NhanVien.ten,"
                + " ChucVu.tencv, gioitinh, ngaysinh, cccd, email, diachi , sdt FROM NhanVien\n"
                + "JOIN ChucVu ON ChucVu.id = NhanVien.id_chucvu";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChucVu cv = new ChucVu(rs.getString(4));
                NhanVien nv = new NhanVien(rs.getInt(1), rs.getString(2), rs.getString(3), cv, rs.getBoolean(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List selectBySql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public NhanVien selectById(String maNV) {
         sql = "SELECT stt, NhanVien.ma, NhanVien.ten,"
                + " ChucVu.tencv, gioitinh, ngaysinh, cccd, email, diachi , sdt FROM NhanVien\n"
                + "JOIN ChucVu ON ChucVu.id = NhanVien.id_chucvu where NhanVien.ma=?";
             try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maNV);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChucVu cv = new ChucVu(rs.getString(4));
                NhanVien nv = new NhanVien(rs.getInt(1), rs.getString(2), rs.getString(3), cv, rs.getBoolean(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
              
                return nv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    
}

@Override
public Object selectById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

public NhanVien getUser(String user, String pass){
        sql="SELECT  NhanVien.ma, NhanVien.matkhau,"
                + " ChucVu.tencv FROM NhanVien\n"
                + "JOIN ChucVu ON ChucVu.id = NhanVien.id_chucvu where NhanVien.ma=? and NhanVien.matkhau=?";
        NhanVien l = null;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, user);
            ps.setObject(2, pass);
            rs = ps.executeQuery();
            while(rs.next()){
                ChucVu cv = new ChucVu(rs.getString(3));
                 l = new NhanVien(rs.getString(1), rs.getString(2), cv);
//                ls.add(l);
            }
            return l;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean checkLogin(String username, String pass){
        NhanVien lo = getUser(username,pass);
        if(lo!=null){
            if(lo.getMatKhau().equals(pass)){
                return true;
            }
        }
        return false;
    }
}