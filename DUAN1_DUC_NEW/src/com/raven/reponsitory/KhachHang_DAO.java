/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classmodel.HoaDon;
import com.raven.classmodel.KhachHang;
import com.raven.service.DBConnect;
import com.raven.service.EduSysDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author MSI 15
 */
public class KhachHang_DAO extends EduSysDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    @Override
    public void insert(Object entity) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    public boolean inertKH(KhachHang nh) {
        sql = "insert into KhachHang(ma,ten,gioitinh,ngaysinh,sdt,email,diachi,id_nguoitao) values(?,?,?,?,?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            String uniqueMa = UUID.randomUUID().toString();
            ps.setObject(1, nh.getMaKH());
            ps.setObject(2, nh.getTenKH());
            ps.setObject(3, nh.isGioiTinh());
            ps.setObject(4, new java.sql.Date(nh.getNgaySinh().getTime()));
            ps.setObject(5, nh.getSdt());
            ps.setObject(6, nh.getEmail());
            ps.setObject(7, nh.getDiaChi());
            ps.setObject(8, nh.getId_NguoiTao());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }

    public KhachHang checkMa(String ma) {
        sql = "select ma , ten , gioitinh,ngaysinh,sdt,email,diachi from KhachHang where ma=? ";
        KhachHang kh = null;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }
            return kh;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int updateKH(KhachHang kh, String ma) {
        sql = "update KhachHang set   ten=? , gioitinh =?, ngaysinh=?,sdt=? , email=? ,diachi =? where ma=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, kh.getTenKH());
            ps.setObject(2, kh.isGioiTinh());
            ps.setObject(3, kh.getNgaySinh());
            ps.setObject(4, kh.getSdt());
            ps.setObject(5, kh.getEmail());
            ps.setObject(6, kh.getDiaChi());
            ps.setObject(7, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void delete(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int deleteKH(String ma) {
        sql = "delete from KhachHang where ma=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<KhachHang> selectAll() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        ArrayList<KhachHang> list = new ArrayList<>();
        sql = "select ma , ten , gioitinh,ngaysinh,sdt,email,diachi from KhachHang";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang nv = new KhachHang(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDon> selectAllHoaDon() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        ArrayList<HoaDon> list = new ArrayList();
        sql = "select  HoaDon.ma,HoaDon.ngaythanhtoan,HoaDon.tongtiencuahoadon,"
                + "HoaDon.tinhtrang,HoaDon.tennguoinhan ,HoaDon.diachi from HoaDon  inner join KhachHang on KhachHang.id=HoaDon.id_khachhang";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                //  KhachHang kh = new KhachHang(rs.getString(5));
                HoaDon hd = new HoaDon(rs.getString(1), rs.getDate(2),
                        rs.getBigDecimal(3), rs.getInt(4),
                        rs.getString(5), rs.getString(6));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public KhachHang selectById(String maKH) {
        sql = "select ma , ten , gioitinh,ngaysinh,sdt,email,diachi from KhachHang where ma =?";

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maKH);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7));

                return kh;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<KhachHang> timTen(String ten) {
        sql = "select ma , ten , gioitinh,ngaysinh,sdt,email,diachi from KhachHang where ten like ?";
        KhachHang kh = null;
        List<KhachHang> listNH = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + ten + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7));

                listNH.add(kh);
            }
            return listNH;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<KhachHang> getAllCBO() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        ArrayList<KhachHang> list = new ArrayList<>();
        sql = "select ma , ten , gioitinh,ngaysinh,sdt,email,diachi from KhachHang  ";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<KhachHang> timTheoGioiTinh(Boolean gioiTinh) {
        sql = "select ma , ten , gioitinh,ngaysinh,sdt,email,diachi from KhachHang where gioitinh = ?";
        KhachHang kh = null;
        List<KhachHang> listNH = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, gioiTinh);
            rs = ps.executeQuery();
            while (rs.next()) {
                kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7));

                listNH.add(kh);
            }
            return listNH;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object selectById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object> selectBySql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
