/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.raven.classinterface.GenMaTuDong;
import com.raven.classmodel.HoaDon;
import com.raven.classmodel.KhachHang;
import com.raven.classmodel.NhanVien;
import com.raven.classmodel.UpdateStatusThread;
import com.raven.main.Main;
import com.raven.reponsitory.DBConnect;
import com.raven.service.VoucherService;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import javax.swing.text.BadLocationException;
/**
 *
 * @author ADMIN
 */
public class Voucher extends javax.swing.JPanel {
private VoucherService service = new VoucherService();
private DefaultTableModel model = new DefaultTableModel();
List<com.raven.classmodel.Voucher> list = service.getAll();
private int index = -1;
private int index1 = -1;
private int index2 = -1;
private int index3 = -1;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    long count,soTrang,trang = 1;
    long count1,soTrang1,trang1 = 1;
    long count2,soTrang2,trang2 = 1;
    long count3,soTrang3,trang3 = 1;
    /**
     * Creates new form Voucher
     */
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        Voucher spview  = new Voucher();
        jf.add(spview);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.pack();
        
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
    }
    public Voucher() {
        initComponents();
        this.AddPleacehoderStyle(txtTen);
        cbbLoaiTim.removeAllItems();
        cboLoaiGiamGia.removeAllItems();
        String menu[]={"%","Money"};
        for(String item: menu){
            cboLoaiGiamGia.addItem(item);
        }
        String menu1[]={"Tất cả","%","Money"};
        for(String item: menu1){
            cbbLoaiTim.addItem(item);
        }
        this.fillTable(service.getAll());
        this.fillTable1(service.getAll1(1));
        this.fillTable2(service.getAll1(2));
        this.fillTable3(service.getAll1(3));
        
        countDB();
        if(count % 10==0){
            soTrang = count / 10;
        }else{
            soTrang = count / 10 + 1;
        }
        loadData(1);
        btnTrang.setText("1");
        
        countDB1(1);
        if(count1 % 10==0){
            soTrang1 = count1 / 10;
        }else{
            soTrang1 = count1 / 10 + 1;
        }
        loadData1(1,1);
        btnTrang1.setText("1");
        
        countDB2(2);
        if(count2 % 10==0){
            soTrang2 = count2 / 10;
        }else{
            soTrang2 = count2 / 10 + 1;
        }
        loadData2(1,2);
        btnTrang2.setText("1");
        
        countDB3(3);
        if(count3 % 10==0){
            soTrang3 = count3 / 10;
        }else{
            soTrang3 = count3 / 10 + 1;
        }
        loadData3(1,3);
        btnTrang3.setText("1");
        init();
        
        
    }
    void init(){
        Main main = new Main();
        main.setTitle("Phiếu Giảm Giá");
//        UpdateStatusThread thread = new UpdateStatusThread();
//        thread.start();
    }
    
    public void countDB(){
            sql =  "Select count(*) from Voucher";
        try {
            con= DBConnect.getConnection();
            ps= con.prepareStatement(sql);
            rs= ps.executeQuery();
            while(rs.next()){
                count =rs.getLong(1);
            }
            con.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadData(long trang){
        fillTable(service.getAll());
        model.getDataVector().removeAllElements();
        sql = "select top 10 ma,ten,loaigiamgia,mucgiamgia, soluong, ngaybatdau, ngayketthuc, trangthai \n" +
"FROM Voucher WHERE ma NOT IN (SELECT TOP "+(trang*10-10)+" ma FROM Voucher) order by ma desc";// WHERE HOTEN NOT IN (SELECT TOP "+(trang*5-5)+" FROM STUDENTS)
        List<Voucher> list  = new ArrayList<>();
        try {
            con= DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                com.raven.classmodel.Voucher sv = new com.raven.classmodel.Voucher();
                 sv = new com.raven.classmodel.Voucher(rs.getString(1), rs.getString(2),rs.getString(3), rs.getBigDecimal(4), rs.getInt(5), rs.getDate(6), rs.getDate(7), rs.getInt(8));
                model.addRow(sv.toDaTaRow());
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void countDB1(int trangthai){
            sql =  "Select count(*) from Voucher where trangthai =?";
        try {
            con= DBConnect.getConnection();
            ps= con.prepareStatement(sql);
            ps.setObject(1, trangthai);
            rs= ps.executeQuery();
            while(rs.next()){
                count1 =rs.getLong(1);
            }
            con.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void countDB2(int trangthai){
            sql =  "Select count(*) from Voucher where trangthai =?";
        try {
            con= DBConnect.getConnection();
            ps= con.prepareStatement(sql);
            ps.setObject(1, trangthai);
            rs= ps.executeQuery();
            while(rs.next()){
                count2 =rs.getLong(1);
            }
            con.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadData1(long trang,int trangthai){
        fillTable1(service.getAll1(1));
        model.getDataVector().removeAllElements();
        sql = "select top 10 ma,ten,loaigiamgia,mucgiamgia, soluong, ngaybatdau, ngayketthuc, trangthai \n" +
"FROM Voucher WHERE ma NOT IN (SELECT TOP "+(trang*10-10)+" ma FROM Voucher) and trangthai =? order by ma desc";// WHERE HOTEN NOT IN (SELECT TOP "+(trang*5-5)+" FROM STUDENTS)
        List<Voucher> list  = new ArrayList<>();
        try {
            con= DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangthai);
            rs = ps.executeQuery();
            while(rs.next()){
                com.raven.classmodel.Voucher sv = new com.raven.classmodel.Voucher();
                 sv = new com.raven.classmodel.Voucher(rs.getString(1), rs.getString(2),rs.getString(3), rs.getBigDecimal(4), rs.getInt(5), rs.getDate(6), rs.getDate(7), rs.getInt(8));
                model.addRow(sv.toDaTaRow());
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadData2(long trang,int trangthai){
        fillTable2(service.getAll1(2));
        model.getDataVector().removeAllElements();
        sql = "select top 10 ma,ten,loaigiamgia,mucgiamgia, soluong, ngaybatdau, ngayketthuc, trangthai \n" +
"FROM Voucher WHERE ma NOT IN (SELECT TOP "+(trang*10-10)+" ma FROM Voucher) and trangthai =? order by ma desc";// WHERE HOTEN NOT IN (SELECT TOP "+(trang*5-5)+" FROM STUDENTS)
        List<Voucher> list  = new ArrayList<>();
        try {
            con= DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangthai);
            rs = ps.executeQuery();
            while(rs.next()){
                com.raven.classmodel.Voucher sv = new com.raven.classmodel.Voucher();
                 sv = new com.raven.classmodel.Voucher(rs.getString(1), rs.getString(2),rs.getString(3), rs.getBigDecimal(4), rs.getInt(5), rs.getDate(6), rs.getDate(7), rs.getInt(8));
                model.addRow(sv.toDaTaRow());
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void countDB3(int trangthai){
            sql =  "Select count(*) from Voucher where trangthai=?";
        try {
            con= DBConnect.getConnection();
            ps= con.prepareStatement(sql);
            ps.setObject(1, trangthai);
            rs= ps.executeQuery();
            while(rs.next()){
                count3 =rs.getLong(1);
            }
            con.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadData3(long trang,int trangthai){
        fillTable3(service.getAll1(3));
        model.getDataVector().removeAllElements();
        sql = "select top 10 ma,ten,loaigiamgia,mucgiamgia, soluong, ngaybatdau, ngayketthuc, trangthai \n" +
"FROM Voucher WHERE ma NOT IN (SELECT TOP "+(trang*10-10)+" ma FROM Voucher) and trangthai=? order by ma desc";// WHERE HOTEN NOT IN (SELECT TOP "+(trang*5-5)+" FROM STUDENTS)
        List<Voucher> list  = new ArrayList<>();
        try {
            con= DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangthai);
            rs = ps.executeQuery();
            while(rs.next()){
                com.raven.classmodel.Voucher sv = new com.raven.classmodel.Voucher();
                 sv = new com.raven.classmodel.Voucher(rs.getString(1), rs.getString(2),rs.getString(3), rs.getBigDecimal(4), rs.getInt(5), rs.getDate(6), rs.getDate(7), rs.getInt(8));
                model.addRow(sv.toDaTaRow());
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updateTrangThai(){
        com.raven.classmodel.Voucher nv = this.readForm1();
        Date ngayhientai = new Date();
            service.update1(nv, ngayhientai);
                this.fillTable(service.getAll());
        this.fillTable1(service.getAll1(1));
        this.fillTable2(service.getAll1(2));
        this.fillTable3(service.getAll1(3));
    }
    public void updateTrangThai1(){
        com.raven.classmodel.Voucher nv = this.readForm2();
        Date ngayhientai = new Date();
            service.update2(nv, ngayhientai);
                this.fillTable(service.getAll());
            
        this.fillTable1(service.getAll1(1));
        this.fillTable2(service.getAll1(2));
        this.fillTable3(service.getAll1(3));
    }
    public void fillTable(List<com.raven.classmodel.Voucher> list){
        model = (DefaultTableModel) tblVoicherAll.getModel();
        model.setColumnCount(0);
        model.addColumn("STT");
        model.addColumn("Mã");
        model.addColumn("Tên");
        model.addColumn("Loại Giảm");
        model.addColumn("Mức Giảm Giá");
        model.addColumn("Số Lượng");
        model.addColumn("Ngày bắt đầu");
        model.addColumn("Ngày kết thúc");
        model.addColumn("Trạng thái");
        model.setRowCount(0);
            int stt =1;
        for(com.raven.classmodel.Voucher vc: list){
            String trangthai = "";
            int trangThai = vc.getTrangThai();
            if (trangThai == 1) {
                trangthai = "Sắp diễn ra";
            } else if (trangThai == 2) {
                trangthai = "Đang diễn ra";
            } else if(trangThai ==3){
                trangthai = "Đã kết thúc";
            }
        Object[] toDaTaRow= new Object[] {stt++,vc.getMa(),vc.getTen(),vc.getLoaiGiamGia(),vc.getMucGiam(),vc.getSoLuong(),vc.getNgayBatDau(),vc.getNgayKetThuc(),trangthai,vc.getNgayTao()};
    
            model.addRow(toDaTaRow);
        }
    }
    public void fillTable1(List<com.raven.classmodel.Voucher> list){
        model = (DefaultTableModel) tblVoicherWait.getModel();
        model.setColumnCount(0);
        model.addColumn("STT");
        model.addColumn("Mã");
        model.addColumn("Tên");
        model.addColumn("Loại Giảm");
        model.addColumn("Mức Giảm Giá");
        model.addColumn("Số Lượng");
        model.addColumn("Ngày bắt đầu");
        model.addColumn("Ngày kết thúc");
        model.addColumn("Trạng thái");
        model.setRowCount(0);
        int stt =1;
        for(com.raven.classmodel.Voucher vc: list){
            String trangthai = "";
            int trangThai = vc.getTrangThai();
            if (trangThai == 1) {
                trangthai = "Sắp diễn ra";
            } else if (trangThai == 2) {
                trangthai = "Đang diễn ra";
            } else if(trangThai ==3){
                trangthai = "Đã kết thúc";
            }
        Object[] toDaTaRow= new Object[] {stt++,vc.getMa(),vc.getTen(),vc.getLoaiGiamGia(),vc.getMucGiam(),vc.getSoLuong(),vc.getNgayBatDau(),vc.getNgayKetThuc(),trangthai,vc.getNgayTao()};
    
            model.addRow(toDaTaRow);
        }
    }
    public void fillTable2(List<com.raven.classmodel.Voucher> list){
        model = (DefaultTableModel) tblVoicherStart.getModel();
        model.setColumnCount(0);
        model.addColumn("STT");
        model.addColumn("Mã");
        model.addColumn("Tên");
        model.addColumn("Loại Giảm");
        model.addColumn("Mức Giảm Giá");
        model.addColumn("Số Lượng");
        model.addColumn("Ngày bắt đầu");
        model.addColumn("Ngày kết thúc");
        model.addColumn("Trạng thái");
        model.setRowCount(0);
        int stt =1;
        for(com.raven.classmodel.Voucher vc: list){
            String trangthai = "";
            int trangThai = vc.getTrangThai();
            if (trangThai == 1) {
                trangthai = "Sắp diễn ra";
            } else if (trangThai == 2) {
                trangthai = "Đang diễn ra";
            } else if(trangThai ==3){
                trangthai = "Đã kết thúc";
            }
        Object[] toDaTaRow= new Object[] {stt++,vc.getMa(),vc.getTen(),vc.getLoaiGiamGia(),vc.getMucGiam(),vc.getSoLuong(),vc.getNgayBatDau(),vc.getNgayKetThuc(),trangthai,vc.getNgayTao()};
    
            model.addRow(toDaTaRow);
        }
    }
    public void fillTable3(List<com.raven.classmodel.Voucher> list){
        model = (DefaultTableModel) tblVoicherEnd.getModel();
        model.setColumnCount(0);
        model.addColumn("STT");
        model.addColumn("Mã");
        model.addColumn("Tên");
        model.addColumn("Loại Giảm");
        model.addColumn("Mức Giảm");
        model.addColumn("Số Lượng");
        model.addColumn("Ngày bắt đầu");
        model.addColumn("Ngày kết thúc");
        model.addColumn("Trạng thái");
        model.setRowCount(0);
        int stt =1;
        for(com.raven.classmodel.Voucher vc: list){
            String trangthai = "";
            int trangThai = vc.getTrangThai();
            if (trangThai == 1) {
                trangthai = "Sắp diễn ra";
            } else if (trangThai == 2) {
                trangthai = "Đang diễn ra";
            } else if(trangThai ==3){
                trangthai = "Đã kết thúc";
            }
        Object[] toDaTaRow= new Object[] {stt++,vc.getMa(),vc.getTen(),vc.getLoaiGiamGia(),vc.getMucGiam(),vc.getSoLuong(),vc.getNgayBatDau(),vc.getNgayKetThuc(),trangthai,vc.getNgayTao()};
    
            model.addRow(toDaTaRow);
        }
    }
    private void showData(int index){
        com.raven.classmodel.Voucher vc = service.getAll().get(index);
        txtTen.setText(vc.getTen());
        txtMa.setText(vc.getMa());
        cboLoaiGiamGia.setSelectedItem(vc.getLoaiGiamGia());
        txtMucGiam.setText(String.valueOf(vc.getMucGiam()));
        txtSoLuong.setText(String.valueOf(vc.getSoLuong()));
        txtStartDate.setDate(vc.getNgayBatDau());
        txtEndDate.setDate(vc.getNgayKetThuc());
    }
    private void showData1(int index){
        com.raven.classmodel.Voucher vc = service.getAll1(1).get(index);
        txtTen.setText(vc.getTen());
        txtMa.setText(vc.getMa());
        cboLoaiGiamGia.setSelectedItem(vc.getLoaiGiamGia());
        txtMucGiam.setText(String.valueOf(vc.getMucGiam()));
        txtSoLuong.setText(String.valueOf(vc.getSoLuong()));
        txtStartDate.setDate(vc.getNgayBatDau());
        txtEndDate.setDate(vc.getNgayKetThuc());
    }
    private void showData2(int index){
        com.raven.classmodel.Voucher vc = service.getAll1(2).get(index);
        txtTen.setText(vc.getTen());
        txtMa.setText(vc.getMa());
        cboLoaiGiamGia.setSelectedItem(vc.getLoaiGiamGia());
        txtMucGiam.setText(String.valueOf(vc.getMucGiam()));
        txtSoLuong.setText(String.valueOf(vc.getSoLuong()));
        txtStartDate.setDate(vc.getNgayBatDau());
        txtEndDate.setDate(vc.getNgayKetThuc());
    }
    private void showData3(int index){
        com.raven.classmodel.Voucher vc = service.getAll1(3).get(index);
        txtTen.setText(vc.getTen());
        txtMa.setText(vc.getMa());
        cboLoaiGiamGia.setSelectedItem(vc.getLoaiGiamGia());
        txtMucGiam.setText(String.valueOf(vc.getMucGiam()));
        txtSoLuong.setText(String.valueOf(vc.getSoLuong()));
        txtStartDate.setDate(vc.getNgayBatDau());
        txtEndDate.setDate(vc.getNgayKetThuc());
    }

    private SimpleDateFormat formats = new SimpleDateFormat("dd-MM-yyyy");
    
    private com.raven.classmodel.Voucher readForm(){
        NhanVien nv = new NhanVien();
        HoaDon hd = new HoaDon();
        GenMaTuDong genMa = new GenMaTuDong() {
            @Override
            public String maTuDong() {
                int gen = new Random().nextInt(1000000);
                return "VC"+gen;
            }
        };
        String ma = genMa.maTuDong();
        String ten = txtMa.getText();
        String loaigiam = cboLoaiGiamGia.getSelectedItem().toString();
        Double giamGia = Double.parseDouble(txtMucGiam.getText());
        Double giatri = Double.parseDouble(txtGiaTriMin.getText());
        BigDecimal mucGiamGia = BigDecimal.valueOf(giamGia);
        BigDecimal giatrimin = BigDecimal.valueOf(giatri);
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        java.util.Date StartDate = txtStartDate.getDate();
        java.util.Date EndDate = txtEndDate.getDate();
        java.util.Date ngayTao = new Date();
        int trangthai = 1;
        
        if(ngayTao.getDate()>=(StartDate.getDate()) & ngayTao.getDate()<(EndDate.getDate())){
            trangthai = 2;
        }
        
        else if (ngayTao.getDate()>=(EndDate.getDate())){
            trangthai = 3;
            }
        else{
            trangthai = 1;
        }
        return new com.raven.classmodel.Voucher(ma, ten,loaigiam, mucGiamGia, giatrimin, soLuong, StartDate, EndDate,trangthai,ngayTao,nv,hd);
    }
    private com.raven.classmodel.Voucher readForm1(){
        int trangthai = 2;
        return new com.raven.classmodel.Voucher(trangthai);
    }
    private com.raven.classmodel.Voucher readForm2(){
        int trangthai = 3;
        return new com.raven.classmodel.Voucher(trangthai);
    }
    
    //c1
     private String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");

        return sdf.format(date);
    }

    // Hàm chuyển đổi từ date về string
    private Date parseDate(String ngayThang) {
        // Đối tượng hỗ trợ đọc kiểu dữ liệu ngày tháng
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        //String date = DateFormat.
        try {
            return sdf.parse(ngayThang);
        } catch (Exception e) {
            //Nếu lỗi trả về thời điểm hiện tại
            return new Date();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel12 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVoicherAll = new javax.swing.JTable();
        btnTrai = new javax.swing.JButton();
        btnTrang = new javax.swing.JButton();
        btnPhai = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblVoicherWait = new javax.swing.JTable();
        btnTrai1 = new javax.swing.JButton();
        btnTrang1 = new javax.swing.JButton();
        btnPhai1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblVoicherStart = new javax.swing.JTable();
        btnTrai2 = new javax.swing.JButton();
        btnTrang2 = new javax.swing.JButton();
        btnPhai2 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblVoicherEnd = new javax.swing.JTable();
        btnTrai3 = new javax.swing.JButton();
        btnTrang3 = new javax.swing.JButton();
        btnPhai3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNgayDau = new com.toedter.calendar.JDateChooser();
        txtNgayCuoi = new com.toedter.calendar.JDateChooser();
        cbbLoaiTim = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboLoaiGiamGia = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtMucGiam = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        txtStartDate = new com.toedter.calendar.JDateChooser();
        txtEndDate = new com.toedter.calendar.JDateChooser();
        txtGiaTriMin = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Phiếu giảm giá", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Phiếu Giảm Giá", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblVoicherAll.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblVoicherAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoicherAllMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblVoicherAll);

        btnTrai.setText("<");
        btnTrai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraiActionPerformed(evt);
            }
        });

        btnTrang.setText("1");

        btnPhai.setText(">");
        btnPhai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1031, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTrai, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPhai, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTrai)
                    .addComponent(btnTrang)
                    .addComponent(btnPhai))
                .addGap(0, 60, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tất Cả", jPanel3);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        tblVoicherWait.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblVoicherWait.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoicherWaitMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblVoicherWait);

        btnTrai1.setText("<");
        btnTrai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrai1ActionPerformed(evt);
            }
        });

        btnTrang1.setText("1");

        btnPhai1.setText(">");
        btnPhai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhai1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1031, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTrai1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTrang1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPhai1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTrai1)
                    .addComponent(btnTrang1)
                    .addComponent(btnPhai1))
                .addGap(0, 69, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sắp Bắt Đầu", jPanel5);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        tblVoicherStart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblVoicherStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoicherStartMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblVoicherStart);

        btnTrai2.setText("<");
        btnTrai2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrai2ActionPerformed(evt);
            }
        });

        btnTrang2.setText("1");

        btnPhai2.setText(">");
        btnPhai2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhai2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1031, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTrai2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTrang2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPhai2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTrai2)
                    .addComponent(btnTrang2)
                    .addComponent(btnPhai2))
                .addGap(0, 69, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Đang Bắt Đầu", jPanel7);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        tblVoicherEnd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblVoicherEnd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoicherEndMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblVoicherEnd);

        btnTrai3.setText("<");
        btnTrai3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrai3ActionPerformed(evt);
            }
        });

        btnTrang3.setText("1");

        btnPhai3.setText(">");
        btnPhai3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhai3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1031, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTrai3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTrang3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPhai3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTrai3)
                    .addComponent(btnTrang3)
                    .addComponent(btnPhai3))
                .addGap(0, 69, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Đã Kết Thúc", jPanel9);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Tìm kiếm:");

        txtTimKiem.setText("Tên sản phẩm");
        txtTimKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKiemFocusLost(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel9.setText("Ngày Bắt đầu");

        jLabel10.setText("Ngày Kết thúc");

        txtNgayDau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNgayCuoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbbLoaiTim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbbLoaiTim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbLoaiTim.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLoaiTimItemStateChanged(evt);
            }
        });

        jLabel12.setText("Loại giảm");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbLoaiTim, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayDau, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNgayCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(cbbLoaiTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addComponent(txtNgayCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel10)
                        .addComponent(txtNgayDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addComponent(jTabbedPane1))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã Voicher: ");

        txtMa.setEditable(false);
        txtMa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên Voicher:");

        txtTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Ngày Bắt Đầu:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ngày Kết Thúc:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Loại Giảm Giá:");

        cboLoaiGiamGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboLoaiGiamGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLoaiGiamGia.setPreferredSize(new java.awt.Dimension(64, 26));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Giá trị tối đa");

        txtMucGiam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReset.setText("Mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Số Lượng:");

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtStartDate.setDateFormatString("dd-MM-yyyy");
        txtStartDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtStartDate.setMinimumSize(new java.awt.Dimension(64, 26));
        txtStartDate.setPreferredSize(new java.awt.Dimension(64, 26));

        txtEndDate.setDateFormatString("dd-MM-yyyy");
        txtEndDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEndDate.setMinimumSize(new java.awt.Dimension(64, 26));
        txtEndDate.setPreferredSize(new java.awt.Dimension(64, 26));

        txtGiaTriMin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Giá trị tối thiểu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(cboLoaiGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMucGiam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtMa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                            .addComponent(txtEndDate, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                            .addComponent(txtStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                            .addComponent(txtGiaTriMin))
                        .addGap(20, 20, 20))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cboLoaiGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtMucGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaTriMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDelete)
                    .addComponent(btnUpdate)
                    .addComponent(btnReset))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
Boolean vadidate(){
    if(txtTen.getText().isEmpty()){
        JOptionPane.showMessageDialog(this, "Tên rỗng");
        return false;
    }
    if(txtMucGiam.getText().isEmpty()){
        JOptionPane.showMessageDialog(this, "Muc giảm giá rỗng");
        return false;
    }
    Voucher vc = new Voucher();
    double mucgiam = Double.parseDouble(txtMucGiam.getText());
    if(cboLoaiGiamGia.getSelectedItem().equals("%")){
    if(mucgiam>=100){
        JOptionPane.showMessageDialog(this, "Chỉ được giảm 100% trở xuống");
        return false;
    }
    }
    if(txtSoLuong.getText().isEmpty()){
        JOptionPane.showMessageDialog(this, "Số lượng rỗng");
        return false;
    }
    if(txtStartDate.getDate()==null){
        JOptionPane.showMessageDialog(this, "Ngày bắt đầu rỗng");
        return false;
    }
    if(txtEndDate.getDate()==null){
        JOptionPane.showMessageDialog(this, "Ngày kết thúc rỗng");
        return false;
    }
    java.util.Date StartDate = txtStartDate.getDate();
        java.util.Date EndDate = txtEndDate.getDate();
        java.util.Date ngayTao = new Date();
    if((StartDate.getDate())<(ngayTao.getDate())){
        JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải lớn hơn ngày hiện tại");
        return false;
        }
    if((EndDate.getDate())<(StartDate.getDate())){
        JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu");
        return false;
        }
    return true;
}
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        int chose = JOptionPane.showConfirmDialog(this, "Thêm Phiếu Giảm Giá","Phiếu giảm giá",JOptionPane.YES_NO_OPTION);
        if(chose == JOptionPane.YES_OPTION){
            if(this.vadidate()){
            com.raven.classmodel.Voucher nv = this.readForm();
        if(service.getID(nv.getMa())!=null){
            JOptionPane.showMessageDialog(this, "ma trung khong them duoc");
        }else{
            if(service.insert(nv)>0){
                JOptionPane.showMessageDialog(this, "them thanh cong");
                this.fillTable(service.getAll());
                this.fillTable1(service.getAll1(1));
                this.fillTable2(service.getAll1(2));
                this.fillTable3(service.getAll1(3));
                loadData(trang);
                loadData1(trang1,1);
                loadData2(trang2,2);
                loadData3(trang3,3);
                
            }else{
                JOptionPane.showMessageDialog(this, "them that bai");
            }
        }
        }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int chose = JOptionPane.showConfirmDialog(this, "Sửa Phiếu Giảm Giá","Phiếu giảm giá",JOptionPane.YES_NO_OPTION);
        if(chose == JOptionPane.YES_OPTION){
        if(this.vadidate()){
            com.raven.classmodel.Voucher nv = this.readForm();
        index = tblVoicherAll.getSelectedRow();
        index1 = tblVoicherWait.getSelectedRow();
        index2 = tblVoicherStart.getSelectedRow();
        index3 = tblVoicherEnd.getSelectedRow();
        if((index)==-1){
            JOptionPane.showMessageDialog(this, "ban chua chon");
        }else{
            String ma = tblVoicherAll.getValueAt((index), 1).toString();
            if(service.update(nv, ma)>0){
                JOptionPane.showMessageDialog(this, "update thanh cong");
                this.fillTable(service.getAll());
            }else{
                JOptionPane.showMessageDialog(this, "update that bai");
            }
                this.fillTable1(service.getAll1(1));
                this.fillTable2(service.getAll1(2));
                this.fillTable3(service.getAll1(3));
                loadData(trang);
                loadData1(trang1,1);
                loadData2(trang2,2);
                loadData3(trang3,3);
        }
        }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int chose = JOptionPane.showConfirmDialog(this, "Xóa Phiếu Giảm Giá","Phiếu giảm giá",JOptionPane.YES_NO_OPTION);
        if(chose == JOptionPane.YES_OPTION){
            if (index < 0) {
            JOptionPane.showMessageDialog(this, "Moi chon ban can xoa");
        } else {
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?", "Xóa", JOptionPane.YES_OPTION);
            if (choice == JOptionPane.YES_OPTION) {

                index = tblVoicherAll.getSelectedRow();
                String ma = tblVoicherAll.getValueAt(index, 1).toString();
                String id = service.getIdVoucher(ma);
                if (service.delete(id) > 0) {
                    JOptionPane.showMessageDialog(this, "Xóa thanh cong");
                    fillTable(service.getAll());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Xóa Thất Bại");
            }
        }

//        index = tblVoicherAll.getSelectedRow();
//        index1 = tblVoicherWait.getSelectedRow();
//        index2 = tblVoicherStart.getSelectedRow();
//        index3 = tblVoicherEnd.getSelectedRow();
//        if((index)==-1){
//            JOptionPane.showMessageDialog(this, "ban chua chon");
//        }else{
//            String ma = tblVoicherAll.getValueAt(index, 1).toString();
//            if(service.delete(ma)>0){
//                JOptionPane.showMessageDialog(this, "delete thanh cong");
//                this.fillTable(service.getAll());
//            }else{
//                JOptionPane.showMessageDialog(this, "delete that bai");
//            }
//                this.fillTable1(service.getAll1(1));
//                this.fillTable2(service.getAll1(2));
//                this.fillTable3(service.getAll1(3));
//                loadData(trang);
//                loadData1(trang1,1);
//                loadData2(trang2,2);
//                loadData3(trang3,3);
//        }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed
private void Reset(){
    txtTen.setText("");
    txtMa.setText("");
    cboLoaiGiamGia.setSelectedItem(0);
    txtMucGiam.setText("");
    txtSoLuong.setText("");
    txtStartDate.setDateFormatString("");
    txtEndDate.setDateFormatString("");
}
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        this.Reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void tblVoicherAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoicherAllMouseClicked
        // TODO add your handling code here:
        index = tblVoicherAll.getSelectedRow();
        this.showData(index);
    }//GEN-LAST:event_tblVoicherAllMouseClicked

    private void tblVoicherWaitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoicherWaitMouseClicked
        // TODO add your handling code here:
        index = tblVoicherWait.getSelectedRow();
        this.showData1(index);
    }//GEN-LAST:event_tblVoicherWaitMouseClicked

    private void tblVoicherStartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoicherStartMouseClicked
        // TODO add your handling code here:
        index = tblVoicherStart.getSelectedRow();
        this.showData2(index);
    }//GEN-LAST:event_tblVoicherStartMouseClicked

    private void tblVoicherEndMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoicherEndMouseClicked
        // TODO add your handling code here:
        index = tblVoicherEnd.getSelectedRow();
        this.showData3(index);
    }//GEN-LAST:event_tblVoicherEndMouseClicked

    private void btnTraiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraiActionPerformed
        // TODO add your handling code here:
        if(trang>1){
            trang--;
            loadData(trang);
            btnTrang.setText(""+trang);
        }
    }//GEN-LAST:event_btnTraiActionPerformed

    private void btnPhaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhaiActionPerformed
        // TODO add your handling code here:
        if(trang<soTrang){
            trang++;
            loadData(trang);
            btnTrang.setText(""+trang);
        }
    }//GEN-LAST:event_btnPhaiActionPerformed

    private void btnTrai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrai1ActionPerformed
        // TODO add your handling code here:
        if(trang1>1){
            trang1--;
            loadData1(trang1,1);
            btnTrang1.setText(""+trang1);
        }
    }//GEN-LAST:event_btnTrai1ActionPerformed

    private void btnPhai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhai1ActionPerformed
        // TODO add your handling code here:
        if(trang1<soTrang1){
            trang1++;
            loadData1(trang1,1);
            btnTrang1.setText(""+trang1);
        }
    }//GEN-LAST:event_btnPhai1ActionPerformed

    private void btnTrai2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrai2ActionPerformed
        // TODO add your handling code here:
        if(trang2>1){
            trang2--;
            loadData2(trang2,2);
            btnTrang2.setText(""+trang2);
        }
    }//GEN-LAST:event_btnTrai2ActionPerformed

    private void btnPhai2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhai2ActionPerformed
        // TODO add your handling code here:
        if(trang2<soTrang2){
            trang2++;
            loadData2(trang2,2);
            btnTrang2.setText(""+trang2);
        }
    }//GEN-LAST:event_btnPhai2ActionPerformed

    private void btnTrai3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrai3ActionPerformed
        // TODO add your handling code here:
        if(trang3>1){
            trang3--;
            loadData3(trang3,3);
            btnTrang3.setText(""+trang3);
        }
    }//GEN-LAST:event_btnTrai3ActionPerformed

    private void btnPhai3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhai3ActionPerformed
        // TODO add your handling code here:
        if(trang3<soTrang3){
            trang3++;
            loadData3(trang3,3);
            btnTrang3.setText(""+trang3);
        }
    }//GEN-LAST:event_btnPhai3ActionPerformed
void search(String str) {
        DefaultTableModel model = (DefaultTableModel) tblVoicherAll.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tblVoicherAll.setRowSorter(trs);
        String searchText = txtTimKiem.getText().trim();
        trs.setRowFilter(RowFilter.regexFilter(str));
    }
public void AddPleacehoderStyle(JTextField textField) {
        Font font = textField.getFont();
        font = font.deriveFont(Font.ITALIC);
        textField.setFont(font);
        textField.setForeground(Color.gray);
    }
    
    public void RemovePleacehoderStyle(JTextField textField) {
        Font font = textField.getFont();
        font = font.deriveFont(Font.PLAIN);
        textField.setFont(font);
        textField.setForeground(Color.black);
    }
    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        String trs = txtTimKiem.getText();
        search(trs);
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtTimKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusGained
        // TODO add your handling code here:
        if (txtTimKiem.getText().equals("Tên sản phẩm")) {
            txtTimKiem.setText(null);
            txtTimKiem.requestFocus();
            RemovePleacehoderStyle(txtTimKiem);
        }
    }//GEN-LAST:event_txtTimKiemFocusGained

    private void txtTimKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusLost
        // TODO add your handling code here:                                  
        if (txtTimKiem.getText().length() == 0) {
            AddPleacehoderStyle(txtTimKiem);
            txtTimKiem.setText("Tên sản phẩm");

        }
    }//GEN-LAST:event_txtTimKiemFocusLost

    private void cbbLoaiTimItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLoaiTimItemStateChanged
        // TODO add your handling code here:
        String ten ;
        if(cbbLoaiTim.getSelectedItem().equals("Tất cả")){
             ten = cbbLoaiTim.getSelectedItem().toString();
        ten ="%"+"%";
        }else{
            ten = cbbLoaiTim.getSelectedItem().toString();
        ten ="%"+ten+"%";
        }
        if(service.findcbb(ten).size()>0){//co sinh vien tim duoc
                this.fillTable(service.findcbb(ten));
            }
    }//GEN-LAST:event_cbbLoaiTimItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnPhai;
    private javax.swing.JButton btnPhai1;
    private javax.swing.JButton btnPhai2;
    private javax.swing.JButton btnPhai3;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTrai;
    private javax.swing.JButton btnTrai1;
    private javax.swing.JButton btnTrai2;
    private javax.swing.JButton btnTrai3;
    private javax.swing.JButton btnTrang;
    private javax.swing.JButton btnTrang1;
    private javax.swing.JButton btnTrang2;
    private javax.swing.JButton btnTrang3;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbLoaiTim;
    private javax.swing.JComboBox<String> cboLoaiGiamGia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTable tblVoicherAll;
    private javax.swing.JTable tblVoicherEnd;
    private javax.swing.JTable tblVoicherStart;
    private javax.swing.JTable tblVoicherWait;
    private com.toedter.calendar.JDateChooser txtEndDate;
    private javax.swing.JTextField txtGiaTriMin;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMucGiam;
    private com.toedter.calendar.JDateChooser txtNgayCuoi;
    private com.toedter.calendar.JDateChooser txtNgayDau;
    private javax.swing.JTextField txtSoLuong;
    private com.toedter.calendar.JDateChooser txtStartDate;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
