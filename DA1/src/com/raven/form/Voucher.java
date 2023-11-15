/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.raven.reponsitory.DBConnect;
import com.raven.service.VoucherService;
import java.math.BigDecimal;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
/**
 *
 * @author ADMIN
 */
public class Voucher extends javax.swing.JPanel {
private VoucherService service = new VoucherService();
private DefaultTableModel model = new DefaultTableModel();
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
    public Voucher() {
        initComponents();
        cboLoaiGiamGia.removeAllItems();
        String menu[]={"%","Tiền"};
        for(String item: menu){
            cboLoaiGiamGia.addItem(item);
        }
        this.fillTable(service.getAll());
        this.fillTable1(service.getAll1(1));
        this.fillTable2(service.getAll1(2));
        this.fillTable3(service.getAll1(3));
        countDB();
        countDB1(1);
        countDB2(2);
        countDB3(3);
        if(count % 5==0){
            soTrang = count / 5;
        }else{
            soTrang = count / 5 + 1;
        }
        if(count1 % 1==0){
            soTrang1 = count1 / 1;
        }else{
            soTrang1 = count1 / 1 + 1;
        }
        if(count2 % 1==0){
            soTrang2 = count2 / 1;
        }else{
            soTrang2 = count2 / 1 + 1;
        }
        if(count3 % 1==0){
            soTrang3 = count3 / 1;
        }else{
            soTrang3 = count3 / 1 + 1;
        }
        loadData(1);
        loadData1(1,1);
        loadData2(1,2);
        loadData3(1,3);
        lbSoTrang.setText("1/"+soTrang);
        lbTrang.setText("1");
        lbSoTrang1.setText("1/"+soTrang1);
        lbTrang1.setText("1");
        lbSoTrang2.setText("1/"+soTrang2);
        lbTrang2.setText("1");
        lbSoTrang3.setText("1/"+soTrang3);
        lbTrang3.setText("1");
        
        
//        Timer timer = new Timer(1000, e -> {
//            // Cập nhật dòng trạng thái tự động
//            updateTrangThai();
//            updateTrangThai1();
//        });
//
//        // Bắt đầu Timer
//        timer.start();
    }

//    public void updateTrangThai(){
//        com.raven.classmodel.Voucher nv = this.readForm1();
//        Date ngayhientai = new Date();
//            service.update1(nv, ngayhientai);
//            
//                this.fillTable(service.getAll());
//        this.fillTable1(service.getAll1(1));
//        this.fillTable2(service.getAll1(2));
//        this.fillTable3(service.getAll1(3));
//                loadData(1);
//        loadData1(1,1);
//        loadData2(1,2);
//        loadData3(1,3);
//    }
//    public void updateTrangThai1(){
//        com.raven.classmodel.Voucher nv = this.readForm2();
//        Date ngayhientai = new Date();
//            service.update2(nv, ngayhientai);
//                this.fillTable(service.getAll());
//            
//        this.fillTable1(service.getAll1(1));
//        this.fillTable2(service.getAll1(2));
//        this.fillTable3(service.getAll1(3));
//                loadData(1);
//        loadData1(1,1);
//        loadData2(1,2);
//        loadData3(1,3);
//    }
    private void fillTable(List<com.raven.classmodel.Voucher> list){
        model = (DefaultTableModel) tblVoicherAll.getModel();
        model.setColumnCount(0);
        model.addColumn("STT");
        model.addColumn("Mã");
        model.addColumn("Tên");
        model.addColumn("Mức Giảm Giá");
        model.addColumn("Số Lượng");
        model.addColumn("Ngày bắt đầu");
        model.addColumn("Ngày kết thúc");
        model.addColumn("Trạng thái");
        model.addColumn("Ngày tạo");
        model.setRowCount(0);
        for(com.raven.classmodel.Voucher vc: list){
            model.addRow(vc.toDaTaRow());
        }
    }
    private void fillTable1(List<com.raven.classmodel.Voucher> list){
        model = (DefaultTableModel) tblVoicherWait.getModel();
        model.setColumnCount(0);
        model.addColumn("STT");
        model.addColumn("Mã");
        model.addColumn("Tên");
        model.addColumn("Mức Giảm Giá");
        model.addColumn("Số Lượng");
        model.addColumn("Ngày bắt đầu");
        model.addColumn("Ngày kết thúc");
        model.addColumn("Trạng thái");
        model.addColumn("Ngày tạo");
        model.setRowCount(0);
        for(com.raven.classmodel.Voucher vc: list){
            model.addRow(vc.toDaTaRow());
        }
    }
    private void fillTable2(List<com.raven.classmodel.Voucher> list){
        model = (DefaultTableModel) tblVoicherStart.getModel();
        model.setColumnCount(0);
        model.addColumn("STT");
        model.addColumn("Mã");
        model.addColumn("Tên");
        model.addColumn("Mức Giảm Giá");
        model.addColumn("Số Lượng");
        model.addColumn("Ngày bắt đầu");
        model.addColumn("Ngày kết thúc");
        model.addColumn("Trạng thái");
        model.addColumn("Ngày tạo");
        model.setRowCount(0);
        for(com.raven.classmodel.Voucher vc: list){
            model.addRow(vc.toDaTaRow());
        }
    }
    private void fillTable3(List<com.raven.classmodel.Voucher> list){
        model = (DefaultTableModel) tblVoicherEnd.getModel();
        model.setColumnCount(0);
        model.addColumn("STT");
        model.addColumn("Mã");
        model.addColumn("Tên");
        model.addColumn("Mức Giảm Giá");
        model.addColumn("Số Lượng");
        model.addColumn("Ngày bắt đầu");
        model.addColumn("Ngày kết thúc");
        model.addColumn("Trạng thái");
        model.addColumn("Ngày tạo");
        model.setRowCount(0);
        for(com.raven.classmodel.Voucher vc: list){
            model.addRow(vc.toDaTaRow());
        }
    }
    private void showData(int index){
        com.raven.classmodel.Voucher vc = service.getAll().get(index);
        txtMa.setText(vc.getMa());
        txtTen.setText(vc.getTen());
        txtMucGiam.setText(String.valueOf(vc.getMucGiam()));
        txtSoLuong.setText(String.valueOf(vc.getSoLuong()));
        txtStartDate.setText(String.valueOf(vc.getNgayBatDau()));
        txtEndDate.setText(String.valueOf(vc.getNgayKetThuc()));
    }
    private void showData1(int index){
        com.raven.classmodel.Voucher vc = service.getAll1(1).get(index);
        txtMa.setText(vc.getMa());
        txtTen.setText(vc.getTen());
        txtMucGiam.setText(String.valueOf(vc.getMucGiam()));
        txtSoLuong.setText(String.valueOf(vc.getSoLuong()));
        txtStartDate.setText(String.valueOf(vc.getNgayBatDau()));
        txtEndDate.setText(String.valueOf(vc.getNgayKetThuc()));
    }
    private void showData2(int index){
        com.raven.classmodel.Voucher vc = service.getAll1(2).get(index);
        txtMa.setText(vc.getMa());
        txtTen.setText(vc.getTen());
        txtMucGiam.setText(String.valueOf(vc.getMucGiam()));
        txtSoLuong.setText(String.valueOf(vc.getSoLuong()));
        txtStartDate.setText(String.valueOf(vc.getNgayBatDau()));
        txtEndDate.setText(String.valueOf(vc.getNgayKetThuc()));
    }
    private void showData3(int index){
        com.raven.classmodel.Voucher vc = service.getAll1(3).get(index);
        txtMa.setText(vc.getMa());
        txtTen.setText(vc.getTen());
        txtMucGiam.setText(String.valueOf(vc.getMucGiam()));
        txtSoLuong.setText(String.valueOf(vc.getSoLuong()));
        txtStartDate.setText(String.valueOf(vc.getNgayBatDau()));
        txtEndDate.setText(String.valueOf(vc.getNgayKetThuc()));
    }
//c1
    private SimpleDateFormat format = new SimpleDateFormat();
    private String date2String(java.util.Date date){
        return format.format(date);
    }
    private java.util.Date pareDate(String ngayThang){
        try {
            return format.parse(ngayThang);
        } catch (Exception e) {
            return new java.util.Date();
        }
    }
    private SimpleDateFormat formats = new SimpleDateFormat("DD-MM-YYYY");
    
    private com.raven.classmodel.Voucher readForm(){
        String ma = txtMa.getText();
        String ten = txtTen.getText();
        Double giamGia = Double.parseDouble(txtMucGiam.getText());
        BigDecimal mucGiamGia = BigDecimal.valueOf(giamGia);
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        java.util.Date StartDate = pareDate(txtStartDate.getText());
//        Date StartDate = formats.parse(txtStartDate.getText());//c2
        java.util.Date EndDate = pareDate(txtEndDate.getText());
        java.util.Date ngayTao = new java.util.Date();
        int trangthai = 1;
        if(ngayTao.equals(StartDate)){
            trangthai = 2;
        }
        if (ngayTao.equals(EndDate)){
            trangthai = 3;
            }
        return new com.raven.classmodel.Voucher(ma, ten, mucGiamGia, soLuong, StartDate, EndDate,trangthai,ngayTao);
    }
    private com.raven.classmodel.Voucher readForm1(){
        int trangthai = 2;
        return new com.raven.classmodel.Voucher(trangthai);
    }
    private com.raven.classmodel.Voucher readForm2(){
        int trangthai = 3;
        return new com.raven.classmodel.Voucher(trangthai);
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
    public void countDB1(int trangthai){
            sql =  "Select count(*) from Voucher where trangthai = ?";
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
            sql =  "Select count(*) from Voucher where trangthai = ?";
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
    public void countDB3(int trangthai){
            sql =  "Select count(*) from Voucher where trangthai = ?";
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
     public void loadData(long trang){
        fillTable(service.getAll());
        model.getDataVector().removeAllElements();
        sql = "select top 5 stt,ma,ten,mucgiamgia, soluong, ngaybatdau, ngayketthuc, trangthai,ngaytao \n" +
"FROM Voucher WHERE ma NOT IN (SELECT TOP "+(trang*5-5)+" ma FROM Voucher)";// WHERE HOTEN NOT IN (SELECT TOP "+(trang*5-5)+" FROM Voucher)
        try {
            con= DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                com.raven.classmodel.Voucher sv = new com.raven.classmodel.Voucher(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getBigDecimal(4), rs.getInt(5), rs.getDate(6), rs.getDate(7), rs.getInt(8),rs.getDate(9));
                model.addRow(sv.toDaTaRow());
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void loadData1(long trang,int trangthai){
        this.fillTable1(service.getAll1(1));
        model.getDataVector().removeAllElements();
        sql = "select top 1 stt,ma,ten,mucgiamgia, soluong, ngaybatdau, ngayketthuc, trangthai,ngaytao \n" +
"FROM Voucher WHERE(ma NOT IN (SELECT TOP "+(trang*1-1)+" ma FROM Voucher)) and trangthai = ?";// WHERE HOTEN NOT IN (SELECT TOP "+(trang*5-5)+" FROM Voucher)
        try {
            con= DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangthai);
            rs = ps.executeQuery();
            while(rs.next()){
                com.raven.classmodel.Voucher sv = new com.raven.classmodel.Voucher(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getBigDecimal(4), rs.getInt(5), rs.getDate(6), rs.getDate(7), rs.getInt(8),rs.getDate(9));
                model.addRow(sv.toDaTaRow());
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void loadData2(long trang,int trangthai){
        this.fillTable2(service.getAll1(2));
        model.getDataVector().removeAllElements();
        sql = "select top 1 stt,ma,ten,mucgiamgia, soluong, ngaybatdau, ngayketthuc, trangthai,ngaytao \n" +
"FROM Voucher WHERE (ma NOT IN (SELECT TOP "+(trang*1-1)+" ma FROM Voucher)) and trangthai = ?";// WHERE HOTEN NOT IN (SELECT TOP "+(trang*5-5)+" FROM Voucher)
        try {
            con= DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangthai);
            rs = ps.executeQuery();
            while(rs.next()){
                com.raven.classmodel.Voucher sv = new com.raven.classmodel.Voucher(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getBigDecimal(4), rs.getInt(5), rs.getDate(6), rs.getDate(7), rs.getInt(8),rs.getDate(9));
                model.addRow(sv.toDaTaRow());
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void loadData3(long trang,int trangthai){
        this.fillTable3(service.getAll1(3));
        model.getDataVector().removeAllElements();
        sql = "select top 1 stt,ma,ten,mucgiamgia, soluong, ngaybatdau, ngayketthuc, trangthai,ngaytao \n" +
"FROM Voucher WHERE (ma NOT IN (SELECT TOP "+(trang*1-1)+" ma FROM Voucher)) and trangthai = ?";// WHERE HOTEN NOT IN (SELECT TOP "+(trang*5-5)+" FROM Voucher)
        try {
            con= DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangthai);
            rs = ps.executeQuery();
            while(rs.next()){
                com.raven.classmodel.Voucher sv = new com.raven.classmodel.Voucher(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getBigDecimal(4), rs.getInt(5), rs.getDate(6), rs.getDate(7), rs.getInt(8),rs.getDate(9));
                model.addRow(sv.toDaTaRow());
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
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
        jPanel4 = new javax.swing.JPanel();
        btnTraiTrai = new javax.swing.JButton();
        btnTrai = new javax.swing.JButton();
        lbTrang = new javax.swing.JLabel();
        btnPhai = new javax.swing.JButton();
        btnPhaiPhai = new javax.swing.JButton();
        lbSoTrang = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblVoicherWait = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        btnTraiTrai1 = new javax.swing.JButton();
        btnTrai1 = new javax.swing.JButton();
        lbTrang1 = new javax.swing.JLabel();
        btnPhai1 = new javax.swing.JButton();
        btnPhaiPhai1 = new javax.swing.JButton();
        lbSoTrang1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblVoicherStart = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        btnTraiTrai2 = new javax.swing.JButton();
        btnTrai2 = new javax.swing.JButton();
        lbTrang2 = new javax.swing.JLabel();
        btnPhai2 = new javax.swing.JButton();
        btnPhaiPhai2 = new javax.swing.JButton();
        lbSoTrang2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblVoicherEnd = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        btnTraiTrai3 = new javax.swing.JButton();
        btnTrai3 = new javax.swing.JButton();
        lbTrang3 = new javax.swing.JLabel();
        btnPhai3 = new javax.swing.JButton();
        btnPhaiPhai3 = new javax.swing.JButton();
        lbSoTrang3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboLoaiGiamGia = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtMucGiam = new javax.swing.JTextField();
        txtStartDate = new javax.swing.JTextField();
        txtEndDate = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Voicher", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

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

        btnTraiTrai.setText("<<");
        btnTraiTrai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraiTraiActionPerformed(evt);
            }
        });

        btnTrai.setText("<");
        btnTrai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraiActionPerformed(evt);
            }
        });

        lbTrang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTrang.setForeground(new java.awt.Color(255, 0, 0));
        lbTrang.setText("jLabel2");

        btnPhai.setText(">");
        btnPhai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhaiActionPerformed(evt);
            }
        });

        btnPhaiPhai.setText(">>");
        btnPhaiPhai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhaiPhaiActionPerformed(evt);
            }
        });

        lbSoTrang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbSoTrang.setForeground(new java.awt.Color(255, 0, 0));
        lbSoTrang.setText("jLabel1");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(136, Short.MAX_VALUE)
                .addComponent(btnTraiTrai, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTrai, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbTrang)
                .addGap(18, 18, 18)
                .addComponent(btnPhai, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPhaiPhai, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbSoTrang)
                .addGap(238, 238, 238))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTraiTrai)
                    .addComponent(btnTrai)
                    .addComponent(btnPhai)
                    .addComponent(btnPhaiPhai)
                    .addComponent(lbSoTrang)
                    .addComponent(lbTrang))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tất Cả", jPanel3);

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

        btnTraiTrai1.setText("<<");
        btnTraiTrai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraiTrai1ActionPerformed(evt);
            }
        });

        btnTrai1.setText("<");
        btnTrai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrai1ActionPerformed(evt);
            }
        });

        lbTrang1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTrang1.setForeground(new java.awt.Color(255, 0, 0));
        lbTrang1.setText("jLabel2");

        btnPhai1.setText(">");
        btnPhai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhai1ActionPerformed(evt);
            }
        });

        btnPhaiPhai1.setText(">>");
        btnPhaiPhai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhaiPhai1ActionPerformed(evt);
            }
        });

        lbSoTrang1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbSoTrang1.setForeground(new java.awt.Color(255, 0, 0));
        lbSoTrang1.setText("jLabel1");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(199, Short.MAX_VALUE)
                .addComponent(btnTraiTrai1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTrai1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbTrang1)
                .addGap(18, 18, 18)
                .addComponent(btnPhai1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPhaiPhai1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbSoTrang1)
                .addGap(175, 175, 175))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTraiTrai1)
                    .addComponent(btnTrai1)
                    .addComponent(btnPhai1)
                    .addComponent(btnPhaiPhai1)
                    .addComponent(lbSoTrang1)
                    .addComponent(lbTrang1))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sắp Bắt Đầu", jPanel5);

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

        btnTraiTrai2.setText("<<");
        btnTraiTrai2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraiTrai2ActionPerformed(evt);
            }
        });

        btnTrai2.setText("<");
        btnTrai2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrai2ActionPerformed(evt);
            }
        });

        lbTrang2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTrang2.setForeground(new java.awt.Color(255, 0, 0));
        lbTrang2.setText("jLabel2");

        btnPhai2.setText(">");
        btnPhai2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhai2ActionPerformed(evt);
            }
        });

        btnPhaiPhai2.setText(">>");
        btnPhaiPhai2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhaiPhai2ActionPerformed(evt);
            }
        });

        lbSoTrang2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbSoTrang2.setForeground(new java.awt.Color(255, 0, 0));
        lbSoTrang2.setText("jLabel1");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(199, Short.MAX_VALUE)
                .addComponent(btnTraiTrai2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTrai2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbTrang2)
                .addGap(18, 18, 18)
                .addComponent(btnPhai2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPhaiPhai2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbSoTrang2)
                .addGap(175, 175, 175))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTraiTrai2)
                    .addComponent(btnTrai2)
                    .addComponent(btnPhai2)
                    .addComponent(btnPhaiPhai2)
                    .addComponent(lbSoTrang2)
                    .addComponent(lbTrang2))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Đang Bắt Đầu", jPanel7);

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

        btnTraiTrai3.setText("<<");
        btnTraiTrai3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraiTrai3ActionPerformed(evt);
            }
        });

        btnTrai3.setText("<");
        btnTrai3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrai3ActionPerformed(evt);
            }
        });

        lbTrang3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTrang3.setForeground(new java.awt.Color(255, 0, 0));
        lbTrang3.setText("jLabel2");

        btnPhai3.setText(">");
        btnPhai3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhai3ActionPerformed(evt);
            }
        });

        btnPhaiPhai3.setText(">>");
        btnPhaiPhai3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhaiPhai3ActionPerformed(evt);
            }
        });

        lbSoTrang3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbSoTrang3.setForeground(new java.awt.Color(255, 0, 0));
        lbSoTrang3.setText("jLabel1");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(199, Short.MAX_VALUE)
                .addComponent(btnTraiTrai3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTrai3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbTrang3)
                .addGap(18, 18, 18)
                .addComponent(btnPhai3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPhaiPhai3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbSoTrang3)
                .addGap(175, 175, 175))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTraiTrai3)
                    .addComponent(btnTrai3)
                    .addComponent(btnPhai3)
                    .addComponent(btnPhaiPhai3)
                    .addComponent(lbSoTrang3)
                    .addComponent(lbTrang3))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Đã Kết Thúc", jPanel9);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tên Voicher: ");

        txtTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Mã Voicher:");

        txtMa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Ngày Bắt Đầu:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ngày Kết Thúc:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Loại Giảm Giá:");

        cboLoaiGiamGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboLoaiGiamGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Mức Giảm Giá:");

        txtMucGiam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtStartDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtEndDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnAdd.setText("ADD");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnReset.setText("RESET");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Số Lượng:");

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cboLoaiGiamGia, javax.swing.GroupLayout.Alignment.LEADING, 0, 166, Short.MAX_VALUE)
                    .addComponent(txtEndDate, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtStartDate, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMa, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTen, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMucGiam)
                    .addComponent(txtSoLuong))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboLoaiGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMucGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnReset))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
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
                loadData(1);
        loadData1(1,1);
        loadData2(1,2);
        loadData3(1,3);
            }else{
                JOptionPane.showMessageDialog(this, "them that bai");
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        com.raven.classmodel.Voucher nv = this.readForm();
        index = tblVoicherAll.getSelectedRow();
        index1 = tblVoicherWait.getSelectedRow();
        index2 = tblVoicherStart.getSelectedRow();
        index3 = tblVoicherEnd.getSelectedRow();
        if((index)==-1){
            JOptionPane.showMessageDialog(this, "ban chua chon");
        }else{
            String ma = tblVoicherAll.getValueAt(index, 0).toString();
            if(service.update(nv, ma)>0){
                JOptionPane.showMessageDialog(this, "update thanh cong");
                this.fillTable(service.getAll());
            }
                this.fillTable1(service.getAll1(1));
                this.fillTable2(service.getAll1(2));
                this.fillTable3(service.getAll1(3));
                loadData(1);
        loadData1(1,1);
        loadData2(1,2);
        loadData3(1,3);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        index = tblVoicherAll.getSelectedRow();
        index1 = tblVoicherWait.getSelectedRow();
        index2 = tblVoicherStart.getSelectedRow();
        index3 = tblVoicherEnd.getSelectedRow();
        if((index)==-1){
            JOptionPane.showMessageDialog(this, "ban chua chon");
        }else{
            String ma = tblVoicherAll.getValueAt(index, 0).toString();
            if(service.delete(ma)>0){
                JOptionPane.showMessageDialog(this, "delete thanh cong");
                this.fillTable(service.getAll());
            }
                this.fillTable1(service.getAll1(1));
                this.fillTable2(service.getAll1(2));
                this.fillTable3(service.getAll1(3));
                loadData(1);
        loadData1(1,1);
        loadData2(1,2);
        loadData3(1,3);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed
private void Reset(){
    txtMa.setText("");
    txtTen.setText("");
    txtMucGiam.setText("");
    txtSoLuong.setText("");
    txtStartDate.setText("");
    txtEndDate.setText("");
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

    private void btnTraiTraiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraiTraiActionPerformed
        // TODO add your handling code here:
        trang=1;
        loadData(trang);
        lbTrang.setText(""+trang);
        lbSoTrang.setText("1"+"/"+soTrang);
    }//GEN-LAST:event_btnTraiTraiActionPerformed

    private void btnTraiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraiActionPerformed
        // TODO add your handling code here:
        if(trang>1){
            trang--;
            loadData(trang);
            lbTrang.setText(""+trang);
            lbSoTrang.setText(trang+"/"+soTrang);
        }
    }//GEN-LAST:event_btnTraiActionPerformed

    private void btnPhaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhaiActionPerformed
        // TODO add your handling code here:
        if(trang<soTrang){
            trang++;
            loadData(trang);
            lbTrang.setText(""+trang);
            lbSoTrang.setText(trang+"/"+soTrang);
        }
    }//GEN-LAST:event_btnPhaiActionPerformed

    private void btnPhaiPhaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhaiPhaiActionPerformed
        // TODO add your handling code here:
        trang=soTrang;
        loadData(trang);
        lbTrang.setText(""+trang);
            lbSoTrang.setText(soTrang+"/"+soTrang);
    }//GEN-LAST:event_btnPhaiPhaiActionPerformed

    private void btnTraiTrai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraiTrai1ActionPerformed
        // TODO add your handling code here:
        trang1=1;
        loadData1(trang1,1);
        lbTrang1.setText(""+trang1);
        lbSoTrang1.setText("1"+"/"+soTrang1);
    }//GEN-LAST:event_btnTraiTrai1ActionPerformed

    private void btnTrai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrai1ActionPerformed
        // TODO add your handling code here:
        if(trang1>1){
            trang1--;
        loadData1(trang1,1);
            lbTrang1.setText(""+trang1);
            lbSoTrang1.setText(trang1+"/"+soTrang1);
        }
    }//GEN-LAST:event_btnTrai1ActionPerformed

    private void btnPhai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhai1ActionPerformed
        // TODO add your handling code here:
        if(trang1<soTrang1){
            trang1++;
        loadData1(trang1,1);
            lbTrang1.setText(""+trang1);
            lbSoTrang1.setText(trang1+"/"+soTrang1);
        }
    }//GEN-LAST:event_btnPhai1ActionPerformed

    private void btnPhaiPhai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhaiPhai1ActionPerformed
        // TODO add your handling code here:
        trang1=soTrang1;
        loadData1(trang1,1);
        lbTrang1.setText(""+trang1);
            lbSoTrang1.setText(soTrang1+"/"+soTrang1);
    }//GEN-LAST:event_btnPhaiPhai1ActionPerformed

    private void btnTraiTrai2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraiTrai2ActionPerformed
        // TODO add your handling code here:
        trang2=1;
        loadData2(trang2,2);
        lbTrang2.setText(""+trang2);
        lbSoTrang2.setText("1"+"/"+soTrang2);
    }//GEN-LAST:event_btnTraiTrai2ActionPerformed

    private void btnTrai2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrai2ActionPerformed
        // TODO add your handling code here:
        if(trang2>1){
            trang2--;
        loadData2(trang2,2);
            lbTrang2.setText(""+trang2);
            lbSoTrang2.setText(trang2+"/"+soTrang2);
        }
    }//GEN-LAST:event_btnTrai2ActionPerformed

    private void btnPhai2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhai2ActionPerformed
        // TODO add your handling code here:
        if(trang2<soTrang2){
            trang2++;
        loadData2(trang2,2);
            lbTrang2.setText(""+trang2);
            lbSoTrang2.setText(trang2+"/"+soTrang2);
        }
    }//GEN-LAST:event_btnPhai2ActionPerformed

    private void btnPhaiPhai2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhaiPhai2ActionPerformed
        // TODO add your handling code here:
        trang2=soTrang2;
        loadData2(trang,2);
        lbTrang2.setText(""+trang2);
            lbSoTrang2.setText(soTrang2+"/"+soTrang2);
    }//GEN-LAST:event_btnPhaiPhai2ActionPerformed

    private void btnTraiTrai3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraiTrai3ActionPerformed
        // TODO add your handling code here:
        trang3=1;
        loadData3(trang3,3);
        lbTrang3.setText(""+trang3);
        lbSoTrang3.setText("1"+"/"+soTrang3);
    }//GEN-LAST:event_btnTraiTrai3ActionPerformed

    private void btnTrai3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrai3ActionPerformed
        // TODO add your handling code here:
        if(trang3>1){
            trang3--;
        loadData3(trang3,3);
            lbTrang3.setText(""+trang3);
            lbSoTrang3.setText(trang3+"/"+soTrang3);
        }
    }//GEN-LAST:event_btnTrai3ActionPerformed

    private void btnPhai3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhai3ActionPerformed
        // TODO add your handling code here:
        if(trang3<soTrang3){
            trang3++;
        loadData3(trang3,3);
            lbTrang3.setText(""+trang3);
            lbSoTrang3.setText(trang3+"/"+soTrang3);
        }
    }//GEN-LAST:event_btnPhai3ActionPerformed

    private void btnPhaiPhai3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhaiPhai3ActionPerformed
        // TODO add your handling code here:
        trang3=soTrang3;
        loadData3(trang3,3);
        lbTrang3.setText(""+trang3);
            lbSoTrang3.setText(soTrang3+"/"+soTrang3);
    }//GEN-LAST:event_btnPhaiPhai3ActionPerformed
//public static void main(String[] args) {
//        JFrame jf = new JFrame();
//        Voucher spview  = new Voucher();
//        jf.add(spview);
//        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        jf.pack();
//        jf.setVisible(true);
//        jf.setLocationRelativeTo(null);
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnPhai;
    private javax.swing.JButton btnPhai1;
    private javax.swing.JButton btnPhai2;
    private javax.swing.JButton btnPhai3;
    private javax.swing.JButton btnPhaiPhai;
    private javax.swing.JButton btnPhaiPhai1;
    private javax.swing.JButton btnPhaiPhai2;
    private javax.swing.JButton btnPhaiPhai3;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTrai;
    private javax.swing.JButton btnTrai1;
    private javax.swing.JButton btnTrai2;
    private javax.swing.JButton btnTrai3;
    private javax.swing.JButton btnTraiTrai;
    private javax.swing.JButton btnTraiTrai1;
    private javax.swing.JButton btnTraiTrai2;
    private javax.swing.JButton btnTraiTrai3;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboLoaiGiamGia;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbSoTrang;
    private javax.swing.JLabel lbSoTrang1;
    private javax.swing.JLabel lbSoTrang2;
    private javax.swing.JLabel lbSoTrang3;
    private javax.swing.JLabel lbTrang;
    private javax.swing.JLabel lbTrang1;
    private javax.swing.JLabel lbTrang2;
    private javax.swing.JLabel lbTrang3;
    private javax.swing.JTable tblVoicherAll;
    private javax.swing.JTable tblVoicherEnd;
    private javax.swing.JTable tblVoicherStart;
    private javax.swing.JTable tblVoicherWait;
    private javax.swing.JTextField txtEndDate;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMucGiam;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtStartDate;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
