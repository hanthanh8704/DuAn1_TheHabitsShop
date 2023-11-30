/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;

import com.raven.classmodel.ChucVu;

import com.raven.classmodel.NhanVien;

import com.raven.classmodel.NhanVienRespose;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author MSI 15
 */
public class NhanVien_DAO  {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public void insert(Object entity) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getidChucVu(String chucVu) {
        String idCV = "";
        sql = """
                 SELECT  id from ChucVu where tencv = ?
                 """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, chucVu);

            rs = ps.executeQuery();
            while (rs.next()) {
                chucVu = rs.getString(1);
            }
            return chucVu;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void inertNV(NhanVienRespose nv) {
        sql = " insert into NhanVien(ma,ten,ngaysinh,diachi,"
                + "gioitinh,cccd,email,sdt,matkhau,id_chucvu,trangthai) values(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, nv.getMaNV());
            ps.setObject(2, nv.getTenNV());
            ps.setObject(3, new java.sql.Date(nv.getNgaySinh().getTime()));
            ps.setObject(4, nv.getDiaChi());
            ps.setObject(5, nv.getGioiTinh());
            ps.setObject(6, nv.getCccd());
            ps.setObject(7, nv.getEmail());
            ps.setObject(8, nv.getSDT());
            ps.setObject(9, nv.getMatKhau());
            ps.setObject(10, UUID.fromString(nv.getId_chucvu()));
            ps.setObject(11, nv.getTrangThai());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

   
    public void update(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int updateNV(NhanVienRespose nv, String maNV) {
        sql = "update NhanVien set ten=?,ngaysinh=?,diachi=?,gioitinh=?,cccd=?,email=?,sdt=?,trangthai=? where ma=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, nv.getTenNV());
            ps.setObject(2, nv.getNgaySinh());
            ps.setObject(3, nv.getDiaChi());
            ps.setObject(4, nv.getGioiTinh());
            ps.setObject(5, nv.getCccd());
            ps.setObject(6, nv.getEmail());
            ps.setObject(7, nv.getSDT());
            ps.setObject(8, nv.getTrangThai());
            ps.setObject(9, maNV
            );
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

   
    public void delete(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int deleteID(String idHD , String ma) {
        sql = " delete from HoaDonTraHang where id_nhanvien = ?  \n"
                + " delete from NhanVien where ma=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, idHD);
            ps.setObject(2, ma);
            //ps.setString(2, idNV);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getIdNV_HoaDonTraHang(String ma) {
        // NhanVien nv = null;
        sql = "select id from NhanVien where ma=? ";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);

            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(UUID.fromString(rs.getString(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            //return null;
        }
        return null;
    }

    public String getIdNhanVien(String ma) {
        // NhanVien nv = null;
        sql = "select id from NhanVien where ma=? ";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);

            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(UUID.fromString(rs.getString(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            //return null;
        }
        return null;
    }

    public List<NhanVien> selectAll() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        ArrayList<NhanVien> list = new ArrayList<>();
        sql = "select  NhanVien.ma, NhanVien.ten , ChucVu.tencv ,ngaysinh,cccd ,email,diachi,sdt ,gioitinh,trangthai "
                + " from NhanVien join ChucVu on NhanVien.id_chucvu = ChucVu.id";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChucVu cv = new ChucVu(rs.getString(3));
                NhanVien nv = new NhanVien(rs.getString(1), rs.getString(2), cv,
                        rs.getDate(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getInt(9),
                        rs.getInt(10));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<NhanVien> selectAllPhanTrang(long trang) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        List<NhanVien> list = new ArrayList<>();
        sql = "select top 5  NhanVien.ma , ten , ChucVu.tencv,ngaysinh,cccd,email,diachi,sdt , gioitinh,trangthai"
                + " from NhanVien join ChucVu on NhanVien.id_chucvu = ChucVu.id where NhanVien.ma not in "
                + "(select top  " + (trang * 5 - 5) + " NhanVien.ma from NhanVien )order by NhanVien.ngaytao desc ";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChucVu cv = new ChucVu(rs.getString(3));
                NhanVien nv = new NhanVien(rs.getString(1), rs.getString(2), cv,
                        rs.getDate(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getInt(9),
                        rs.getInt(10));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List selectBySql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public NhanVien selectByIdNhanVien(String ten) {
        NhanVien nv = null;
        sql = "select  NhanVien.ma, NhanVien.ten , ChucVu.tencv ,ngaysinh,cccd ,email,\n"
                + "diachi,sdt ,gioitinh,trangthai \n"
                + "from NhanVien join ChucVu on NhanVien.id_chucvu = ChucVu.id where NhanVien.ten like ?";
        List<NhanVien> listNH = new ArrayList<>();
        try {

            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + ten + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ChucVu cv = new ChucVu(rs.getString(3));
                nv = new NhanVien(rs.getString(1), rs.getString(2), cv,
                        rs.getDate(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getInt(9),
                        rs.getInt(10));

                listNH.add(nv);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }

    public Object selectById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<NhanVien> timGioiTinh(boolean gioiTinh) {
        sql = "	select stt, ma ,ten,gioitinh,ngaysinh,diachi,sdt,cccd,email from NhanVien where gioitinh = ? ";
        NhanVien kh = null;
        List<NhanVien> listNH = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + gioiTinh + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                //kh = new NhanVien(rs.getInt(1), rs.getString(sql), sql, gioiTinh, ngaySinh, sql, sql, sql, sql)
                listNH.add(kh);
            }
            return listNH;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public NhanVien selectById(String maNV) {
        ArrayList<NhanVien> list = new ArrayList<>();
        sql = "select  NhanVien.ma, NhanVien.ten , ChucVu.tencv ,ngaysinh,cccd ,email,diachi,sdt ,gioitinh,trangthai "
                + " from NhanVien join ChucVu on NhanVien.id_chucvu = ChucVu.id where  NhanVien.ma=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maNV);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChucVu cv = new ChucVu(rs.getString(3));
                NhanVien nv = new NhanVien(rs.getString(1), rs.getString(2), cv,
                        rs.getDate(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getInt(9),
                        rs.getInt(10));
                list.add(nv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
//            while(rs.next()){
//                ChucVu cv = new ChucVu(rs.getString(3));
//                 l = new NhanVien(rs.getString(1), rs.getString(2), cv);
////                ls.add(l);
//            }
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
