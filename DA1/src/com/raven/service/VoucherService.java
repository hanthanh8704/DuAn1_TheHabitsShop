/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.service;

import com.raven.classinterface.TheHabitShop;
import com.raven.classmodel.Voucher;
import com.raven.reponsitory.DBConnect;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;
/**
 *
 * @author Ninh Than Thanh
 */
public class VoucherService implements TheHabitShop<Voucher, String>{

    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    String sql = null;
    @Override
    public List<Voucher> getAll() {
        sql = "Select stt,ma,ten,mucgiamgia, soluong, ngaybatdau, ngayketthuc, trangthai,ngaytao \n" +
"FROM Voucher";
        List<Voucher> list  = new ArrayList<>();
        try {
            con= DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Voucher sv = new Voucher(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getBigDecimal(4), rs.getInt(5), rs.getDate(6), rs.getDate(7), rs.getInt(8),rs.getDate(9));
                list.add(sv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Voucher> getAll1(int trangthai) {
        sql = "select stt,ma,ten,mucgiamgia, soluong, ngaybatdau, ngayketthuc, trangthai,ngaytao from Voucher where trangthai = ?";
        List<Voucher> list  = new ArrayList<>();
        try {
            con= DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangthai);
            rs = ps.executeQuery();
            while(rs.next()){
                Voucher sv = new Voucher(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getBigDecimal(4), rs.getInt(5), rs.getDate(6), rs.getDate(7), rs.getInt(8),rs.getDate(9));
                list.add(sv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insert(Voucher entity) {
        sql="INSERT INTO Voucher(ma,ten,mucgiamgia, soluong, ngaybatdau, ngayketthuc,ngaytao) VALUES\n" +
"(?,?,?,?,?,?,?);";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, entity.getMa());
            ps.setObject(2, entity.getTen());
            ps.setObject(3, entity.getMucGiam());
            ps.setObject(4, entity.getSoLuong());
            ps.setObject(5, entity.getNgayBatDau());
            ps.setObject(6, entity.getNgayKetThuc());
            ps.setObject(7, entity.getNgayTao());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(Voucher entity, String id) {
        sql="UPDATE Voucher SET ten=?,mucgiamgia=?,soluong=?,ngaybatdau=?,ngayketthuc=?,ngaytao=?,trangthai =? WHERE ma =?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, entity.getTen());
            ps.setObject(2, entity.getMucGiam());
            ps.setObject(3, entity.getSoLuong());
            ps.setObject(4, entity.getNgayBatDau());
            ps.setObject(5, entity.getNgayKetThuc());
            ps.setObject(6, entity.getNgayTao());
            ps.setObject(7, entity.getTrangThai());
            ps.setObject(8, entity.getMa());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int update1(Voucher entity, Date ngayHienTai) {
        sql="UPDATE Voucher SET trangthai =? WHERE ngaybatdau=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, entity.getTrangThai());
            ps.setObject(2, ngayHienTai);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int update2(Voucher entity, Date ngayHienTai) {
        sql="UPDATE Voucher SET trangthai =? WHERE ngayketthuc =?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, entity.getTrangThai());
            ps.setObject(2, ngayHienTai);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(String id) {
     sql="DELETE FROM Voucher WHERE ma = ?";
        try {
            con = DBConnect.getConnection();
            ps= con.prepareStatement(sql);
            ps.setObject(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Voucher getID(String id) {
     Voucher nv = null;
        sql="select ma,ten,mucgiamgia, soluong, ngaybatdau, ngayketthuc, trangthai,ngaytao from Voucher where ma = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs =ps.executeQuery();
            while(rs.next()){
                nv = new Voucher(rs.getString(1), rs.getString(2), rs.getBigDecimal(3), rs.getInt(4), rs.getDate(5), rs.getDate(6), rs.getInt(7),rs.getDate(8));
            }
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Voucher> getSql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
