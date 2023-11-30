/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import javax.swing.*;
import java.awt.*;
import com.raven.classinterface.GenMaTuDong;
import com.raven.classinterface.TableActionEvent;
import com.raven.classmodel.HoaDon;
import com.raven.classmodel.KhachHang;
import com.raven.classmodel.NhanVien;
import com.raven.classmodel.TableActionCellEditor;
import com.raven.classmodel.TableActionCellRender;
import com.raven.main.Main;
import com.raven.reponsitory.DBConnect;
import com.raven.service.GetIdSPCTService11;
import com.raven.service.VoucherService;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.ForkJoinWorkerThread;
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
    private GetIdSPCTService11 getid = new GetIdSPCTService11();
    public int index = -1;
    private int index1 = -1;
    private int index2 = -1;
    private int index3 = -1;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    long count, soTrang, trang = 1;
    long count1, soTrang1, trang1 = 1;
    long count2, soTrang2, trang2 = 1;
    long count3, soTrang3, trang3 = 1;
    public DecimalFormat df = new DecimalFormat("#.###");

    /**
     * Creates new form Voucher
     */
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        Voucher spview = new Voucher();
        jf.add(spview);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
    }

    public Voucher() {
        initComponents();
//        UpdateStatusThread thread = new UpdateStatusThread();
//        thread.start();

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onDelete(int row) {
                if (tblVoicherAll.isEditing()) {
                    tblVoicherAll.getCellEditor().stopCellEditing();
                }
                if (tblVoicherWait.isEditing()) {
                    tblVoicherWait.getCellEditor().stopCellEditing();
                }
                if (tblVoicherStart.isEditing()) {
                    tblVoicherStart.getCellEditor().stopCellEditing();
                }
                if (tblVoicherEnd.isEditing()) {
                    tblVoicherEnd.getCellEditor().stopCellEditing();
                }
                updateTrangThaiKT(row);

            }
        };
        tblVoicherAll.getColumnModel().getColumn(10).setCellRenderer(new TableActionCellRender());
        tblVoicherAll.getColumnModel().getColumn(10).setCellEditor(new TableActionCellEditor(event));
        tblVoicherWait.getColumnModel().getColumn(10).setCellRenderer(new TableActionCellRender());
        tblVoicherWait.getColumnModel().getColumn(10).setCellEditor(new TableActionCellEditor(event));
        tblVoicherStart.getColumnModel().getColumn(10).setCellRenderer(new TableActionCellRender());
        tblVoicherStart.getColumnModel().getColumn(10).setCellEditor(new TableActionCellEditor(event));
        tblVoicherEnd.getColumnModel().getColumn(10).setCellRenderer(new TableActionCellRender());
        tblVoicherEnd.getColumnModel().getColumn(10).setCellEditor(new TableActionCellEditor(event));
        this.AddPleacehoderStyle(txtTimKiem);
        cboLoaiGiamGia.removeAllItems();
        String menu[] = {"%", "VND"};
        for (String item : menu) {
            cboLoaiGiamGia.addItem(item);
            cbbLoaiTim.addItem(item);
        }
        this.fillTable(service.getAll());
        this.fillTable1(service.getAll1(1));
        this.fillTable2(service.getAll1(2));
        this.fillTable3(service.getAll1(3));
        countDB();
        countDB1(1);
        countDB2(2);
        countDB3(3);
        if (count % 5 == 0) {
            soTrang = count / 5;
        } else {
            soTrang = count / 5 + 1;
        }
        this.fillTable(service.loadData(1));
        lblSoTrang.setText("1/" + soTrang);
        lblTrang.setText("1");

        if (count1 % 5 == 0) {
            soTrang1 = count1 / 5;
        } else {
            soTrang1 = count1 / 5 + 1;
        }
        this.fillTable1(service.loadData1(1, 1));
        lblSoTrang1.setText("1/" + soTrang1);
        lblTrang1.setText("1");

        if (count2 % 5 == 0) {
            soTrang2 = count2 / 5;
        } else {
            soTrang2 = count2 / 5 + 1;
        }
        this.fillTable2(service.loadData1(1, 2));
        lblSoTrang2.setText("1/" + soTrang2);
        lblTrang2.setText("1");

        if (count3 % 5 == 0) {
            soTrang3 = count3 / 5;
        } else {
            soTrang3 = count3 / 5 + 1;
        }
        this.fillTable3(service.loadData1(1, 3));
        lblSoTrang3.setText("1/" + soTrang3);
        lblTrang3.setText("1");

        Thread t1 = new Thread() {

            @Override
            public void run() {
                while (true) {
                    try {
//                    updateTrangThai();
                        sleep(60000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        t1.start();
    }

    void updateTrangThaiKT(int row) {

        row = tblVoicherAll.getSelectedRow();

        com.raven.classmodel.Voucher nv = this.readForm2();
        String ma = tblVoicherAll.getValueAt((row), 1).toString();
//        String ma1 = tblVoicherWait.getValueAt((row), 1).toString();
//        String ma2 = tblVoicherStart.getValueAt((row), 1).toString();
//        String ma3 = tblVoicherEnd.getValueAt((row), 1).toString();
        if (service.update2(nv, ma) > 0) {
            JOptionPane.showMessageDialog(this, "Sửa thành công");
            this.fillTable(service.loadData(1));
        }
//        else if (service.update2(nv, ma1) > 0) {
//            JOptionPane.showMessageDialog(this, "Sửa thành công");
//            this.fillTable2(service.loadData1(1, 1));
//        } else if (service.update2(nv, ma2) > 0) {
//            JOptionPane.showMessageDialog(this, "Sửa thành công");
//            this.fillTable2(service.loadData1(1, 2));
//        } else if (service.update2(nv, ma3) > 0) {
//            JOptionPane.showMessageDialog(this, "Sửa thành công");
//            this.fillTable3(service.loadData1(1, 3));
//        }
        this.fillTable(service.loadData(1));
        this.fillTable2(service.loadData1(1, 1));
        this.fillTable2(service.loadData1(1, 2));
        this.fillTable3(service.loadData1(1, 3));
    }

//    @Override
//    public void paint(Graphics g) {
//        // Load data from database
//        List<com.raven.classmodel.Voucher> data = service.getAll();
//
//        // Update UI
//        synchronized (this) {
//            g.drawRect(0, 0, getWidth(), getHeight());
//            for (com.raven.classmodel.Voucher dataItem : data) {
//                g.drawString(dataItem.toString(), 10, 10 + dataItem.hashCode());
//            }
//        }
//    }
//    @Override
//    public void run() {
//        while (true) {
//            // Cập nhật dòng trạng thái tự động
//            updateTrangThai();
//        }
//    }
    public void updateTrangThai() {
        index = tblVoicherAll.getSelectedRow();
        com.raven.classmodel.Voucher nv = this.readForm1();
        for (index = 0; index < tblVoicherAll.getRowCount(); index++) {
            String ma = tblVoicherAll.getValueAt((index), 1).toString();

            if (service.update1(nv, ma) > 0) {
//                JOptionPane.showMessageDialog(this, "Sửa thành công");
                this.fillTable(service.loadData(1));
            }
        }
        this.fillTable(service.loadData(1));
        this.fillTable2(service.loadData1(1, 1));
        this.fillTable2(service.loadData1(1, 2));
        this.fillTable3(service.loadData1(1, 3));

    }

    public void fillTable(List<com.raven.classmodel.Voucher> list) {
        model = (DefaultTableModel) tblVoicherAll.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (com.raven.classmodel.Voucher vc : list) {
            String trangthai = "";
            int trangThai = vc.getTrangThai();
            if (trangThai == 1) {
                trangthai = "Sắp diễn ra";
            } else if (trangThai == 2) {
                trangthai = "Đang diễn ra";
            } else if (trangThai == 3) {
                trangthai = "Đã kết thúc";
            }
            Object[] toDaTaRow = new Object[]{stt++, vc.getMa(), vc.getTen(),
                vc.getGiatrimax(),
                vc.getLoaiGiamGia(),
                vc.getGiatrimin(),
                vc.getSoLuong(), vc.getNgayBatDau(),
                vc.getNgayKetThuc(),
                trangthai, vc.getNgayTao()};

            model.addRow(toDaTaRow);
        }
    }

    public void fillTable1(List<com.raven.classmodel.Voucher> list) {
        model = (DefaultTableModel) tblVoicherWait.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (com.raven.classmodel.Voucher vc : list) {
            String trangthai = "";
            int trangThai = vc.getTrangThai();
            if (trangThai == 1) {
                trangthai = "Sắp diễn ra";
            } else if (trangThai == 2) {
                trangthai = "Đang diễn ra";
            } else if (trangThai == 3) {
                trangthai = "Đã kết thúc";
            }
            Object[] toDaTaRow = new Object[]{stt++, vc.getMa(), vc.getTen(),
                vc.getGiatrimax(),
                vc.getLoaiGiamGia(),
                vc.getGiatrimin(),
                vc.getSoLuong(), vc.getNgayBatDau(),
                vc.getNgayKetThuc(),
                trangthai, vc.getNgayTao()};
            model.addRow(toDaTaRow);
        }
    }

    public void fillTable2(List<com.raven.classmodel.Voucher> list) {
        model = (DefaultTableModel) tblVoicherStart.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (com.raven.classmodel.Voucher vc : list) {
            String trangthai = "";
            int trangThai = vc.getTrangThai();
            if (trangThai == 1) {
                trangthai = "Sắp diễn ra";
            } else if (trangThai == 2) {
                trangthai = "Đang diễn ra";
            } else if (trangThai == 3) {
                trangthai = "Đã kết thúc";
            }
            Object[] toDaTaRow = new Object[]{stt++, vc.getMa(), vc.getTen(),
                vc.getGiatrimax(),
                vc.getLoaiGiamGia(),
                vc.getGiatrimin(),
                vc.getSoLuong(), vc.getNgayBatDau(),
                vc.getNgayKetThuc(),
                trangthai, vc.getNgayTao()};
            model.addRow(toDaTaRow);
        }
    }

    public void fillTable3(List<com.raven.classmodel.Voucher> list) {
        model = (DefaultTableModel) tblVoicherEnd.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (com.raven.classmodel.Voucher vc : list) {
            String trangthai = "";
            int trangThai = vc.getTrangThai();
            if (trangThai == 1) {
                trangthai = "Sắp diễn ra";
            } else if (trangThai == 2) {
                trangthai = "Đang diễn ra";
            } else if (trangThai == 3) {
                trangthai = "Đã kết thúc";
            }
            Object[] toDaTaRow = new Object[]{stt++, vc.getMa(), vc.getTen(),
                vc.getGiatrimax(),
                vc.getLoaiGiamGia(),
                vc.getGiatrimin(),
                vc.getSoLuong(), vc.getNgayBatDau(),
                vc.getNgayKetThuc(),
                trangthai, vc.getNgayTao()};
            model.addRow(toDaTaRow);
        }
    }

    private void showData(int index) {
        com.raven.classmodel.Voucher vc = service.getAll().get(index);
        txtTen.setText(vc.getTen());
        txtMa.setText(vc.getMa());
        cboLoaiGiamGia.setSelectedItem(vc.getLoaiGiamGia());
        txtMucGiam.setText(String.valueOf(NumberFormat.getInstance().format(vc.getGiatrimax())));
        txtGiaTriMin.setText(String.valueOf(NumberFormat.getInstance().format(vc.getGiatrimin())));
        txtSoLuong.setText(String.valueOf(vc.getSoLuong()));
        txtStartDate.setDate(vc.getNgayBatDau());
        txtEndDate.setDate(vc.getNgayKetThuc());
    }

    private void showData1(int index) {
        com.raven.classmodel.Voucher vc = service.getAll1(1).get(index);
        txtTen.setText(vc.getTen());
        txtMa.setText(vc.getMa());
        cboLoaiGiamGia.setSelectedItem(vc.getLoaiGiamGia());
        txtMucGiam.setText(String.valueOf(NumberFormat.getInstance().format(vc.getGiatrimax())));
        txtGiaTriMin.setText(String.valueOf(NumberFormat.getInstance().format(vc.getGiatrimin())));
        txtSoLuong.setText(String.valueOf(vc.getSoLuong()));
        txtStartDate.setDate(vc.getNgayBatDau());
        txtEndDate.setDate(vc.getNgayKetThuc());
    }

    private void showData2(int index) {
        com.raven.classmodel.Voucher vc = service.getAll1(2).get(index);
        txtTen.setText(vc.getTen());
        txtMa.setText(vc.getMa());
        cboLoaiGiamGia.setSelectedItem(vc.getLoaiGiamGia());
        txtMucGiam.setText(String.valueOf(NumberFormat.getInstance().format(vc.getGiatrimax())));
        txtGiaTriMin.setText(String.valueOf(NumberFormat.getInstance().format(vc.getGiatrimin())));
        txtSoLuong.setText(String.valueOf(vc.getSoLuong()));
        txtStartDate.setDate(vc.getNgayBatDau());
        txtEndDate.setDate(vc.getNgayKetThuc());
    }

    private void showData3(int index) {
        com.raven.classmodel.Voucher vc = service.getAll1(3).get(index);
        txtTen.setText(vc.getTen());
        txtMa.setText(vc.getMa());
        cboLoaiGiamGia.setSelectedItem(vc.getLoaiGiamGia());
        txtMucGiam.setText(String.valueOf(NumberFormat.getInstance().format(vc.getGiatrimax())));
        txtGiaTriMin.setText(String.valueOf(NumberFormat.getInstance().format(vc.getGiatrimin())));
        txtSoLuong.setText(String.valueOf(vc.getSoLuong()));
        txtStartDate.setDate(vc.getNgayBatDau());
        txtEndDate.setDate(vc.getNgayKetThuc());
    }

    private SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");

    private com.raven.classmodel.Voucher readForm() {
        NhanVien nv = new NhanVien();
        HoaDon hd = new HoaDon();
        GenMaTuDong genMa = new GenMaTuDong() {
            @Override
            public String maTuDong() {
                int gen = new Random().nextInt(1000000);
                return "VC" + gen;
            }
        };
//        getid.(cbbMauSac.getSelectedItem() + "")
//        getid.getIDChatLieu(cbbChatLieu.getSelectedItem() + "")
        NhanVien nvv = new NhanVien();
        HoaDon hdd = new HoaDon();
        String nnv = null;
        String hhd = null;
        String ma = genMa.maTuDong();
        String ten = txtTen.getText();
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

        if (ngayTao.getDate() >= (StartDate.getDate()) & ngayTao.getDate() < (EndDate.getDate())) {
            trangthai = 2;
        } else if (ngayTao.getDate() >= (EndDate.getDate())) {
            trangthai = 3;
        } else if (ngayTao.getDate() < StartDate.getDate()) {
            trangthai = 1;
        }
        return new com.raven.classmodel.Voucher(ma, ten, mucGiamGia, loaigiam, giatrimin, soLuong, StartDate, EndDate, trangthai, ngayTao);
    }

    private com.raven.classmodel.Voucher readForm1() {
        index = tblVoicherAll.getSelectedRow();
//        java.util.Date StartDate = null;
//        java.util.Date EndDate= null;
        for (index = 0; index < tblVoicherAll.getRowCount(); index++) {
            java.util.Date StartDate = (java.util.Date) tblVoicherAll.getValueAt(index, 7);
            java.util.Date EndDate = (java.util.Date) tblVoicherAll.getValueAt(index, 8);

            java.util.Date ngayTao = new Date();
            int trangthai = 1;
            if (ngayTao.getDate() >= (StartDate.getDate()) & ngayTao.getDate() < (EndDate.getDate())) {
                trangthai = 2;
            } else if (ngayTao.getDate() >= (EndDate.getDate())) {
                trangthai = 3;
            } else if (ngayTao.getDate() < StartDate.getDate()) {
                trangthai = 1;
            }

            return new com.raven.classmodel.Voucher(trangthai);
        }
        return new com.raven.classmodel.Voucher();
    }

    private com.raven.classmodel.Voucher readForm2() {
        java.util.Date StartDate = txtStartDate.getDate();
        java.util.Date EndDate = txtEndDate.getDate();
        java.util.Date ngayTao = new Date();
        return new com.raven.classmodel.Voucher(ngayTao);
    }

    //c1
    private String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(date);
    }

    // Hàm chuyển đổi từ date về string
    private Date parseDate(String ngayThang) {
        // Đối tượng hỗ trợ đọc kiểu dữ liệu ngày tháng
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //String date = DateFormat.
        try {
            return sdf.parse(ngayThang);
        } catch (Exception e) {
            //Nếu lỗi trả về thời điểm hiện tại
            return new Date();
        }
    }

    public void countDB() {
        sql = "Select count(*) from Voucher";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getLong(1);
            }
            con.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void countDB1(int trangthai) {
        sql = "Select count(*) from Voucher where trangthai=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangthai);
            rs = ps.executeQuery();
            while (rs.next()) {
                count1 = rs.getLong(1);
            }
            con.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void countDB2(int trangthai) {
        sql = "Select count(*) from Voucher where trangthai=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangthai);
            rs = ps.executeQuery();
            while (rs.next()) {
                count2 = rs.getLong(1);
            }
            con.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void countDB3(int trangthai) {
        sql = "Select count(*) from Voucher where trangthai=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangthai);
            rs = ps.executeQuery();
            while (rs.next()) {
                count3 = rs.getLong(1);
            }
            con.close();
            ps.close();
            rs.close();
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
        btnTrai = new javax.swing.JButton();
        btnPhai = new javax.swing.JButton();
        lblSoTrang = new javax.swing.JLabel();
        lblTrang = new javax.swing.JLabel();
        btnPhaiPhai = new javax.swing.JButton();
        btnTraiTrai = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblVoicherWait = new javax.swing.JTable();
        btnTraiTrai1 = new javax.swing.JButton();
        btnTrai1 = new javax.swing.JButton();
        lblTrang1 = new javax.swing.JLabel();
        btnPhai1 = new javax.swing.JButton();
        btnPhaiPhai1 = new javax.swing.JButton();
        lblSoTrang1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblVoicherStart = new javax.swing.JTable();
        btnTraiTrai2 = new javax.swing.JButton();
        btnTrai2 = new javax.swing.JButton();
        lblTrang2 = new javax.swing.JLabel();
        btnPhai2 = new javax.swing.JButton();
        btnPhaiPhai2 = new javax.swing.JButton();
        lblSoTrang2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblVoicherEnd = new javax.swing.JTable();
        btnTraiTrai3 = new javax.swing.JButton();
        btnTrai3 = new javax.swing.JButton();
        lblTrang3 = new javax.swing.JLabel();
        btnPhai3 = new javax.swing.JButton();
        btnPhaiPhai3 = new javax.swing.JButton();
        lblSoTrang3 = new javax.swing.JLabel();
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
        jLabel15 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Phiếu Giảm Giá", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblVoicherAll.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên", "Giá trị tối đa", "Loại giảm", "Giá trị tối thiểu", "Số lượng", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái", "Hành động"
            }
        ));
        tblVoicherAll.setRowHeight(25);
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

        btnPhai.setText(">");
        btnPhai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhaiActionPerformed(evt);
            }
        });

        lblSoTrang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSoTrang.setForeground(new java.awt.Color(255, 51, 0));
        lblSoTrang.setText("jLabel13");

        lblTrang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTrang.setForeground(new java.awt.Color(255, 51, 0));
        lblTrang.setText("jLabel14");

        btnPhaiPhai.setText(">>");
        btnPhaiPhai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhaiPhaiActionPerformed(evt);
            }
        });

        btnTraiTrai.setText("<<");
        btnTraiTrai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraiTraiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1045, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnTraiTrai, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTrai, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTrang)
                .addGap(12, 12, 12)
                .addComponent(btnPhai, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPhaiPhai, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSoTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTrai)
                    .addComponent(btnPhai)
                    .addComponent(lblSoTrang)
                    .addComponent(lblTrang)
                    .addComponent(btnPhaiPhai)
                    .addComponent(btnTraiTrai))
                .addGap(0, 41, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tất Cả", jPanel3);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        tblVoicherWait.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên", "Giá trị tối đa", "Loại giảm", "Giá trị tối thiểu", "Số lượng", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái", "Hành động"
            }
        ));
        tblVoicherWait.setRowHeight(25);
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

        lblTrang1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTrang1.setForeground(new java.awt.Color(255, 51, 0));
        lblTrang1.setText("jLabel14");

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

        lblSoTrang1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSoTrang1.setForeground(new java.awt.Color(255, 51, 0));
        lblSoTrang1.setText("jLabel13");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1045, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnTraiTrai1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTrai1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTrang1)
                .addGap(12, 12, 12)
                .addComponent(btnPhai1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPhaiPhai1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSoTrang1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTrai1)
                    .addComponent(btnPhai1)
                    .addComponent(lblSoTrang1)
                    .addComponent(lblTrang1)
                    .addComponent(btnPhaiPhai1)
                    .addComponent(btnTraiTrai1))
                .addGap(0, 41, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sắp Bắt Đầu", jPanel5);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        tblVoicherStart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên", "Giá trị tối đa", "Loại giảm", "Giá trị tối thiểu", "Số lượng", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái", "Hành động"
            }
        ));
        tblVoicherStart.setRowHeight(25);
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

        lblTrang2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTrang2.setForeground(new java.awt.Color(255, 51, 0));
        lblTrang2.setText("jLabel14");

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

        lblSoTrang2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSoTrang2.setForeground(new java.awt.Color(255, 51, 0));
        lblSoTrang2.setText("jLabel13");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1045, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnTraiTrai2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTrai2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTrang2)
                .addGap(12, 12, 12)
                .addComponent(btnPhai2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPhaiPhai2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSoTrang2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTrai2)
                    .addComponent(btnPhai2)
                    .addComponent(lblSoTrang2)
                    .addComponent(lblTrang2)
                    .addComponent(btnPhaiPhai2)
                    .addComponent(btnTraiTrai2))
                .addGap(0, 41, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Đang Bắt Đầu", jPanel7);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        tblVoicherEnd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên", "Giá trị tối đa", "Loại giảm", "Giá trị tối thiểu", "Số lượng", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái", "Hành động"
            }
        ));
        tblVoicherEnd.setRowHeight(25);
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

        lblTrang3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTrang3.setForeground(new java.awt.Color(255, 51, 0));
        lblTrang3.setText("jLabel14");

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

        lblSoTrang3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSoTrang3.setForeground(new java.awt.Color(255, 51, 0));
        lblSoTrang3.setText("jLabel13");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1045, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnTraiTrai3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTrai3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTrang3)
                .addGap(12, 12, 12)
                .addComponent(btnPhai3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPhaiPhai3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSoTrang3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTrai3)
                    .addComponent(btnPhai3)
                    .addComponent(lblSoTrang3)
                    .addComponent(lblTrang3)
                    .addComponent(btnPhaiPhai3)
                    .addComponent(btnTraiTrai3))
                .addGap(0, 41, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Đã Kết Thúc", jPanel9);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Tìm kiếm:");

        txtTimKiem.setText("Tìm kiếm tất cả");
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

        jLabel9.setText("Ngày Bắt Đầu");

        jLabel10.setText("Ngày Kết Thúc");

        txtNgayDau.setDateFormatString("yyyy-MM-dd");

        txtNgayCuoi.setDateFormatString("yyyy-MM-dd");

        cbbLoaiTim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cbbLoaiTim.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLoaiTimItemStateChanged(evt);
            }
        });

        jLabel12.setText("Loại Giảm");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbLoaiTim, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNgayDau, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNgayCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jTabbedPane1)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel12)
                            .addComponent(cbbLoaiTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addComponent(txtNgayCuoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNgayDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã Voicher: ");

        txtMa.setEditable(false);
        txtMa.setBackground(new java.awt.Color(229, 229, 229));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên Voicher:");

        txtTen.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Ngày Bắt Đầu:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ngày Kết Thúc:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Loại Giảm Giá:");

        cboLoaiGiamGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLoaiGiamGia.setPreferredSize(new java.awt.Dimension(64, 26));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Giá trị tối đa");

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

        txtStartDate.setDateFormatString("yyyy-MM-dd");
        txtStartDate.setMinimumSize(new java.awt.Dimension(64, 26));
        txtStartDate.setPreferredSize(new java.awt.Dimension(64, 26));

        txtEndDate.setDateFormatString("yyyy-MM-dd");
        txtEndDate.setMinimumSize(new java.awt.Dimension(64, 26));
        txtEndDate.setPreferredSize(new java.awt.Dimension(64, 26));

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
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(4, 4, 4))
                                .addComponent(jLabel5)
                                .addComponent(jLabel11))
                            .addComponent(jLabel8))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(txtStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(txtEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboLoaiGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMucGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtGiaTriMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDelete)
                    .addComponent(btnUpdate)
                    .addComponent(btnReset))
                .addContainerGap())
        );

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("PHIẾU GIẢM GIÁ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(441, 441, 441))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
Boolean vadidate() {
        if (txtTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên rỗng");
            return false;
        }
        if (txtMucGiam.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mức giảm giá rỗng");
            return false;
        }
        Voucher vc = new Voucher();
        double mucgiam = Double.parseDouble(txtMucGiam.getText());
        if (cboLoaiGiamGia.getSelectedItem().equals("%")) {
            if (mucgiam >= 100) {
                JOptionPane.showMessageDialog(this, "Chỉ được giảm 100% trở xuống");
                return false;
            }
        }
        if (txtSoLuong.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số lượng rỗng");
            return false;
        }
        if (txtStartDate.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu rỗng");
            return false;
        }
        if (txtEndDate.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc rỗng");
            return false;
        }
        java.util.Date StartDate = txtStartDate.getDate();
        java.util.Date EndDate = txtEndDate.getDate();
        java.util.Date ngayTao = new Date();
        if ((StartDate.getDate()) < (ngayTao.getDate())) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải lớn hơn ngày hiện tại");
            return false;
        }
        if ((EndDate.getDate()) < (StartDate.getDate())) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu");
            return false;
        }
        return true;
    }
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        int chose = JOptionPane.showConfirmDialog(this, "Thêm Phiếu Giảm Giá", "Phiếu giảm giá", JOptionPane.YES_NO_OPTION);
        if (chose == JOptionPane.YES_OPTION) {
            if (this.vadidate()) {
                com.raven.classmodel.Voucher nv = this.readForm();
                if (service.getID(nv.getMa()) != null) {
                    JOptionPane.showMessageDialog(this, "ma trung khong them duoc");
                } else {
                    if (service.insert(nv) > 0) {
                        JOptionPane.showMessageDialog(this, "thêm thanh cong");
                        this.fillTable(service.loadData(1));
                        this.fillTable2(service.loadData1(1, 1));
                        this.fillTable2(service.loadData1(1, 2));
                        this.fillTable3(service.loadData1(1, 3));

                    } else {
                        JOptionPane.showMessageDialog(this, "thêm that bai");
                    }
                }
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int chose = JOptionPane.showConfirmDialog(this, "Sửa Phiếu Giảm Giá", "Phiếu giảm giá", JOptionPane.YES_NO_OPTION);
        if (chose == JOptionPane.YES_OPTION) {
            if (this.vadidate()) {
                com.raven.classmodel.Voucher nv = this.readForm();
                index = tblVoicherAll.getSelectedRow();
                index1 = tblVoicherWait.getSelectedRow();
                index2 = tblVoicherStart.getSelectedRow();
                index3 = tblVoicherEnd.getSelectedRow();
                if ((index) == -1) {
                    JOptionPane.showMessageDialog(this, "Bạn chưa chọn");
                } else {
                    String ma = tblVoicherAll.getValueAt((index), 1).toString();
//                    String ma1 = tblVoicherWait.getValueAt((index1), 1).toString();
//                    String ma2 = tblVoicherStart.getValueAt((index2), 1).toString();
//                    String ma3 = tblVoicherEnd.getValueAt((index3), 1).toString();
                    if (service.update(nv, ma) > 0) {
                        JOptionPane.showMessageDialog(this, "Sửa thành công");
                        this.fillTable(service.loadData(1));
                    }
//                    else if (service.update2(nv, ma1) > 0) {
//                        JOptionPane.showMessageDialog(this, "Sửa thành công");
//                        this.fillTable2(service.loadData1(1, 1));
//                    } else if (service.update2(nv, ma2) > 0) {
//                        JOptionPane.showMessageDialog(this, "Sửa thành công");
//                        this.fillTable2(service.loadData1(1, 2));
//                    } else if (service.update2(nv, ma3) > 0) {
//                        JOptionPane.showMessageDialog(this, "Sửa thành công");
//                        this.fillTable3(service.loadData1(1, 3));
//                    }
//                    else {
//                        JOptionPane.showMessageDialog(this, "Sửa thất bại");
//                    }
                    this.fillTable(service.loadData(1));
                    this.fillTable2(service.loadData1(1, 1));
                    this.fillTable2(service.loadData1(1, 2));
                    this.fillTable3(service.loadData1(1, 3));
                }
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int chose = JOptionPane.showConfirmDialog(this, "Xóa Phiếu Giảm Giá", "Phiếu giảm giá", JOptionPane.YES_NO_OPTION);
        if (chose == JOptionPane.YES_OPTION) {
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Moi chon dữ liệu");
            } else {
                int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?", "Xóa", JOptionPane.YES_OPTION);
                if (choice == JOptionPane.YES_OPTION) {

                    index = tblVoicherAll.getSelectedRow();
                    String ma = tblVoicherAll.getValueAt(index, 1).toString();
                    String id = getid.getIDVoucher(ma);
                    if (service.delete(id) > 0) {
                        JOptionPane.showMessageDialog(this, "Xóa thành công");
                        this.fillTable(service.loadData(1));
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
            this.fillTable(service.loadData(1));
            this.fillTable2(service.loadData1(1, 1));
            this.fillTable2(service.loadData1(1, 2));
            this.fillTable3(service.loadData1(1, 3));
        }
    }//GEN-LAST:event_btnDeleteActionPerformed
    private void Reset() {
        txtTen.setText("");
        txtMa.setText("");
        cboLoaiGiamGia.setSelectedItem(0);
        txtMucGiam.setText("");
        txtSoLuong.setText("");
        txtStartDate.setDateFormatString("");
        txtEndDate.setDateFormatString("");
        this.fillTable(service.loadData(1));
        this.fillTable2(service.loadData1(1, 1));
        this.fillTable2(service.loadData1(1, 2));
        this.fillTable3(service.loadData1(1, 3));
    }
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        this.Reset();
    }//GEN-LAST:event_btnResetActionPerformed
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
        if (txtTimKiem.getText().equals("Tìm kiếm tất cả")) {
            txtTimKiem.setText(null);
            txtTimKiem.requestFocus();
            RemovePleacehoderStyle(txtTimKiem);
        }
    }//GEN-LAST:event_txtTimKiemFocusGained

    private void txtTimKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusLost
        // TODO add your handling code here:                                  
        if (txtTimKiem.getText().length() == 0) {
            AddPleacehoderStyle(txtTimKiem);
            txtTimKiem.setText("Tìm kiếm tất cả");

        }
    }//GEN-LAST:event_txtTimKiemFocusLost

    private void cbbLoaiTimItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLoaiTimItemStateChanged
        // TODO add your handling code here:
        String ten;
        if (cbbLoaiTim.getSelectedItem().equals("Tất cả")) {
            ten = cbbLoaiTim.getSelectedItem().toString();
            ten = "%%";
        } else {
            ten = cbbLoaiTim.getSelectedItem().toString();
            ten = "%" + ten + "%";
        }
        if (service.findcbb(ten).size() > 0) {//co sinh vien tim duoc
            this.fillTable(service.findcbb(ten));
        }
    }//GEN-LAST:event_cbbLoaiTimItemStateChanged

    private void tblVoicherEndMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoicherEndMouseClicked
        // TODO add your handling code here:
        index = tblVoicherEnd.getSelectedRow();
        this.showData3(index);
    }//GEN-LAST:event_tblVoicherEndMouseClicked

    private void tblVoicherStartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoicherStartMouseClicked
        // TODO add your handling code here:
        index = tblVoicherStart.getSelectedRow();
        this.showData2(index);
    }//GEN-LAST:event_tblVoicherStartMouseClicked

    private void tblVoicherWaitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoicherWaitMouseClicked
        // TODO add your handling code here:
        index = tblVoicherWait.getSelectedRow();
        this.showData1(index);
    }//GEN-LAST:event_tblVoicherWaitMouseClicked

    private void tblVoicherAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoicherAllMouseClicked
        // TODO add your handling code here:
        index = tblVoicherAll.getSelectedRow();
        this.showData(index);
    }//GEN-LAST:event_tblVoicherAllMouseClicked

    private void btnTraiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraiActionPerformed
        // TODO add your handling code here:
        if (trang > 1) {
            trang--;
            this.fillTable(service.loadData(trang));
            lblTrang.setText("" + trang);
        }
    }//GEN-LAST:event_btnTraiActionPerformed

    private void btnPhaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhaiActionPerformed
        // TODO add your handling code here:
        if (trang < soTrang) {
            trang++;
            this.fillTable(service.loadData(trang));
            lblTrang.setText("" + trang);
        }
    }//GEN-LAST:event_btnPhaiActionPerformed

    private void btnTraiTraiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraiTraiActionPerformed
        // TODO add your handling code here:
        trang = 1;
        this.fillTable(service.loadData(trang));
        lblTrang.setText("" + trang);
        lblSoTrang.setText("1" + "/" + soTrang);
    }//GEN-LAST:event_btnTraiTraiActionPerformed

    private void btnPhaiPhaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhaiPhaiActionPerformed
        // TODO add your handling code here:
        trang = soTrang;
        this.fillTable(service.loadData(trang));
        lblTrang.setText("" + trang);
        lblSoTrang.setText(soTrang + "/" + soTrang);
    }//GEN-LAST:event_btnPhaiPhaiActionPerformed

    private void btnTraiTrai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraiTrai1ActionPerformed
        // TODO add your handling code here:
        trang1 = 1;
        this.fillTable1(service.loadData1(trang1, 1));
        lblTrang1.setText("" + trang1);
        lblSoTrang1.setText("1" + "/" + soTrang1);
    }//GEN-LAST:event_btnTraiTrai1ActionPerformed

    private void btnTrai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrai1ActionPerformed
        // TODO add your handling code here:
        if (trang1 > 1) {
            trang1--;
            this.fillTable1(service.loadData1(trang1, 1));
            lblTrang1.setText("" + trang1);
        }
    }//GEN-LAST:event_btnTrai1ActionPerformed

    private void btnPhai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhai1ActionPerformed
        // TODO add your handling code here:
        if (trang1 < soTrang1) {
            trang1++;
            this.fillTable1(service.loadData1(trang1, 1));
            lblTrang1.setText("" + trang1);
        }
    }//GEN-LAST:event_btnPhai1ActionPerformed

    private void btnPhaiPhai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhaiPhai1ActionPerformed
        // TODO add your handling code here:
        trang1 = soTrang1;
        this.fillTable1(service.loadData1(trang1, 1));
        lblTrang1.setText("" + trang1);
        lblSoTrang1.setText(soTrang1 + "/" + soTrang1);
    }//GEN-LAST:event_btnPhaiPhai1ActionPerformed

    private void btnTraiTrai2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraiTrai2ActionPerformed
        // TODO add your handling code here:
        trang2 = 1;
        this.fillTable2(service.loadData1(trang2, 2));
        lblTrang2.setText("" + trang2);
        lblSoTrang2.setText("1" + "/" + soTrang2);
    }//GEN-LAST:event_btnTraiTrai2ActionPerformed

    private void btnTrai2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrai2ActionPerformed
        // TODO add your handling code here:
        if (trang2 > 1) {
            trang2--;
            this.fillTable2(service.loadData1(trang2, 2));
            lblTrang2.setText("" + trang2);
        }
    }//GEN-LAST:event_btnTrai2ActionPerformed

    private void btnPhai2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhai2ActionPerformed
        // TODO add your handling code here:
        if (trang2 < soTrang2) {
            trang2++;
            this.fillTable2(service.loadData1(trang2, 2));
            lblTrang2.setText("" + trang2);
        }
    }//GEN-LAST:event_btnPhai2ActionPerformed

    private void btnPhaiPhai2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhaiPhai2ActionPerformed
        // TODO add your handling code here:
        trang2 = soTrang2;
        this.fillTable2(service.loadData1(trang2, 2));
        lblTrang2.setText("" + trang2);
        lblSoTrang2.setText(soTrang2 + "/" + soTrang2);
    }//GEN-LAST:event_btnPhaiPhai2ActionPerformed

    private void btnTraiTrai3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraiTrai3ActionPerformed
        // TODO add your handling code here:
        trang3 = 1;
        this.fillTable3(service.loadData1(trang3, 3));
        lblTrang3.setText("" + trang3);
        lblSoTrang3.setText("1" + "/" + soTrang3);
    }//GEN-LAST:event_btnTraiTrai3ActionPerformed

    private void btnTrai3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrai3ActionPerformed
        // TODO add your handling code here:
        if (trang3 > 1) {
            trang3--;
            this.fillTable3(service.loadData1(trang3, 3));
            lblTrang3.setText("" + trang3);
        }
    }//GEN-LAST:event_btnTrai3ActionPerformed

    private void btnPhai3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhai3ActionPerformed
        // TODO add your handling code here:
        if (trang3 < soTrang3) {
            trang3++;
            this.fillTable3(service.loadData1(trang3, 3));
            lblTrang3.setText("" + trang3);
        }
    }//GEN-LAST:event_btnPhai3ActionPerformed

    private void btnPhaiPhai3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhaiPhai3ActionPerformed
        // TODO add your handling code here:
        trang3 = soTrang3;
        this.fillTable3(service.loadData1(trang3, 3));
        lblTrang3.setText("" + trang3);
        lblSoTrang3.setText(soTrang3 + "/" + soTrang3);
    }//GEN-LAST:event_btnPhaiPhai3ActionPerformed


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
    private javax.swing.JComboBox<String> cbbLoaiTim;
    private javax.swing.JComboBox<String> cboLoaiGiamGia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JLabel lblSoTrang;
    private javax.swing.JLabel lblSoTrang1;
    private javax.swing.JLabel lblSoTrang2;
    private javax.swing.JLabel lblSoTrang3;
    private javax.swing.JLabel lblTrang;
    private javax.swing.JLabel lblTrang1;
    private javax.swing.JLabel lblTrang2;
    private javax.swing.JLabel lblTrang3;
    public javax.swing.JTable tblVoicherAll;
    public javax.swing.JTable tblVoicherEnd;
    public javax.swing.JTable tblVoicherStart;
    public javax.swing.JTable tblVoicherWait;
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
