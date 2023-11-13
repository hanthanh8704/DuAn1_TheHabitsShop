/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.service;

import com.raven.classinterface.TheHabitShop;
import com.raven.classmodel.Voicher;
import com.raven.reponsitory.DBconnect;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Ninh Than Thanh
 */
public class VoicherService implements TheHabitShop<Voicher, String>{

    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    String sql = null;
    @Override
    public List<Voicher> getAll() {
        sql = "select ma,ten,mucgiamgia, soluong, ngaybatdau, ngayketthuc, trangthai,ngaytao from Voucher";
        List<Voicher> list  = new ArrayList<>();
        try {
            con= DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Voicher sv = new Voicher(rs.getString(1), rs.getString(2), rs.getBigDecimal(3), rs.getInt(4), rs.getDate(5), rs.getDate(6), rs.getInt(7),rs.getDate(8));
                list.add(sv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Voicher> getAll1(int trangthai) {
        sql = "select ma,ten,mucgiamgia, soluong, ngaybatdau, ngayketthuc, trangthai,ngaytao from Voucher where trangthai = ?";
        List<Voicher> list  = new ArrayList<>();
        try {
            con= DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangthai);
            rs = ps.executeQuery();
            while(rs.next()){
                Voicher sv = new Voicher(rs.getString(1), rs.getString(2), rs.getBigDecimal(3), rs.getInt(4), rs.getDate(5), rs.getDate(6), rs.getInt(7),rs.getDate(8));
                list.add(sv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insert(Voicher entity) {
        sql="INSERT INTO Voucher(ma,ten,mucgiamgia, soluong, ngaybatdau, ngayketthuc,ngaytao) VALUES\n" +
"(?,?,?,?,?,?,?);";
        try {
            con = DBconnect.getConnection();
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
    public int update(Voicher entity, String id) {
        sql="UPDATE Voucher SET ten=?,mucgiamgia=?,soluong=?,ngaybatdau=?,ngayketthuc=?,ngaytao=? WHERE ma =?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, entity.getTen());
            ps.setObject(2, entity.getMucGiam());
            ps.setObject(3, entity.getSoLuong());
            ps.setObject(4, entity.getNgayBatDau());
            ps.setObject(5, entity.getNgayKetThuc());
            ps.setObject(6, entity.getNgayTao());
            ps.setObject(7, entity.getMa());
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
            con = DBconnect.getConnection();
            ps= con.prepareStatement(sql);
            ps.setObject(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Voicher getID(String id) {
     Voicher nv = null;
        sql="select ma,ten,mucgiamgia, soluong, ngaybatdau, ngayketthuc, trangthai,ngaytao from Voucher where ma = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs =ps.executeQuery();
            while(rs.next()){
                nv = new Voicher(rs.getString(1), rs.getString(2), rs.getBigDecimal(3), rs.getInt(4), rs.getDate(5), rs.getDate(6), rs.getInt(7),rs.getDate(8));
            }
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Voicher> getSql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
