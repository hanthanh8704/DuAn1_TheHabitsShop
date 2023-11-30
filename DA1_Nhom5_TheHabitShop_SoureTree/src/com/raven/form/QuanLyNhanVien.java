/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import com.raven.classinterface.TaoMaTuSinh_inf;
import com.raven.classmodel.ChucVu;
import com.raven.classmodel.NhanVien;
import com.raven.classmodel.NhanVienRespose;
import com.raven.main.Main;
import com.raven.reponsitory.ChucVu_DAO;
import com.raven.reponsitory.DBConnect;
import com.raven.service.GetIdSPCTService1;
import com.raven.reponsitory.NhanVien_DAO;
import java.sql.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import javax.mail.Authenticator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author MSI 15
 */
public class QuanLyNhanVien extends javax.swing.JPanel implements Runnable, ThreadFactory {

    private ArrayList<NhanVien> list = new ArrayList<>();
    private NhanVien nv = new NhanVien();

    private NhanVien_DAO daoNV = new NhanVien_DAO();

    private GetIdSPCTService1 getid = new GetIdSPCTService1();
    //private NhanVien_Service serviceNV = new NhanVien_Service();
    private DefaultTableModel model = new DefaultTableModel();
    private ChucVu_DAO daoCV = new ChucVu_DAO();
    private int index = -1;
    private NhanVienRespose nvResPon = new NhanVienRespose();

    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    Connection cn;
    long count, soTrang, trang = 1;
    Statement st;
    ResultSet rs;
    public static String cccd;

    /**
     * Creates new form NewJPanel
     */
    public QuanLyNhanVien() {
        initComponents();
       // initWebcam();
        //  fillComBoBoxTrangThai();
        fillComBoBoxCHUCVU();
        fillTableNVPhanTrang(daoNV.selectAllPhanTrang(1));
        countDb();
        if (count % 5 == 0) {
            soTrang = count / 5;
        } else {
            soTrang = count / 5 + 1;
        }

        lbSoTrang.setText("1/" + soTrang);
        lbTrang.setText("1");

        AddPleacehoderStyle(txtTim);

    }

    public void countDb() {
        try {
            String sql = "Select count(*) from NhanVien inner join ChucVu on NhanVien.id_chucvu= ChucVu.id";
            cn = DBConnect.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                count = rs.getLong(1);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void fillTableNVPhanTrang(List<NhanVien> list) {
        model = (DefaultTableModel) tbNhanVien.getModel();
        model.setRowCount(0);
        int index = 0;
        try {
            for (NhanVien nv : list) {
                index++;
                Object[] row = new Object[]{
                    index,
                    nv.getMaNV(),
                    nv.getTenNV(),
                    nv.getId_chucVu().getTenCV(),
                    nv.getNgaySinh(),
                    nv.hienThiGioiTinh(),
                    nv.getSDT(),
                    nv.getEmail(),
                    nv.getDiaChi(),
                    nv.hienThiTrangThai()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu");
        }
    }

    void fillComBoBoxCHUCVU() {
        DefaultComboBoxModel mol = new DefaultComboBoxModel();
        mol.removeAllElements();
        List< ChucVu> listCD = daoCV.getAll();
        for (ChucVu nv : listCD) {
            mol.addElement(nv.getTenCV());
        }
        cboChucVu.setModel(mol);

    }

//    void fillComBoBoxTrangThai() {
//        DefaultComboBoxModel mol = new DefaultComboBoxModel();
//        jComboBox1.removeAllItems();
//        List< NhanVien> listCD = daoNV.selectAll();
//        for (NhanVien nv : listCD) {
//            jComboBox1.addItem(nv.hienThiTrangThai()+"");
//        }
//
//    }
//    void fillTable(List<NhanVien> list) {
//        model = (DefaultTableModel) tbNhanVien.getModel();
//        model.setRowCount(0);
//        int index = 0;
//        try {
//            for (NhanVien nv : list) {
//                index++;
//                Object[] row = new Object[]{
//                    index,
//                    nv.getMaNV(),
//                    nv.getTenNV(),
//                    nv.getId_chucVu().getTenCV(),
//                    nv.getNgaySinh(),
//                    nv.hienThiGioiTinh(),
//                    nv.getSDT(),
//                    nv.getEmail(),
//                    nv.getDiaChi(),
//                    nv.hienThiTrangThai()
//                };
//                model.addRow(row);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu");
//        }
//    }
    void editNhanVien() {
        try {
            String maKH = (String) tbNhanVien.getValueAt(this.index, 1);
            NhanVien model = daoNV.selectById(maKH);
            if (model != null) {
                this.setFormNhanVien(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void setFormNhanVien(NhanVien nv) {
        txtTenNV.setText(nv.getTenNV());
        txtCCCD.setText(nv.getCccd());
        txtEmail.setText(nv.getEmail());
        txtDienThoai.setText(nv.getSDT());
        txtNgaySinh.setDate(nv.getNgaySinh());
        txtDiaChi.setText(nv.getDiaChi());
        txtMaNV.setText(nv.getMaNV());
        if (nv.getGioiTinh() == 1) {
            rbNam.setSelected(true);
        } else {
            rbNu.setSelected(true);
        }

        if (nv.getId_chucVu().getTenCV().equalsIgnoreCase("Nhân viên")) {
            cboChucVu.setSelectedIndex(0);
        } else {
            cboChucVu.setSelectedIndex(1);
        }

    }

    void setFromNV(int index) {
        NhanVien nv = daoNV.selectAllPhanTrang(trang).get(index);
        txtTenNV.setText(nv.getTenNV());
        txtCCCD.setText(nv.getCccd());
        txtEmail.setText(nv.getEmail());
        txtDienThoai.setText(nv.getSDT());
        txtNgaySinh.setDate(nv.getNgaySinh());
        txtDiaChi.setText(nv.getDiaChi());
        txtMaNV.setText(nv.getMaNV());
        if (nv.getGioiTinh() == 1) {
            rbNam.setSelected(true);
        } else {
            rbNu.setSelected(true);

        }
        //    if (nv.getTrangThai() == 1) {
        //  rbDangLam.setSelected(true);
        //   } else {
        //  rbNghiLam.setSelected(true);
        //    }
        if (nv.getId_chucVu().getTenCV().equalsIgnoreCase("Nhân viên")) {
            cboChucVu.setSelectedIndex(1);
        } else {
            cboChucVu.setSelectedIndex(0);
        }

    }

    void clearFormNhanVien() {
        txtMaNV.setText("");
        txtTenNV.setText("");
        txtCCCD.setText("");
        txtEmail.setText("");
        txtDienThoai.setText("");
        txtNgaySinh.setDate(null);
        txtDiaChi.setText("");
    }

    public static String generateRandomUUID() {
        // Tạo một UUID ngẫu nhiên
        UUID uuid = UUID.randomUUID();

        // Chuyển UUID thành chuỗi và trả về
        return uuid.toString();
    }

    NhanVienRespose getFormNhanVien() {
        TaoMaTuSinh_inf taoMaNhanVien = new TaoMaTuSinh_inf() {
            @Override
            public String maTuSinh() {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                int maTuSinh = new Random().nextInt(1000000);
                return "NV" + maTuSinh;
            }
        };
        NhanVienRespose nv = new NhanVienRespose();
        nv.setMaNV(taoMaNhanVien.maTuSinh());
        nv.setDiaChi(txtDiaChi.getText());
        nv.setCccd(txtCCCD.getText());
        nv.setSDT(txtDienThoai.getText());
        nv.setTenNV(txtTenNV.getText());
        nv.setEmail(txtEmail.getText());
        nv.setNgaySinh(txtNgaySinh.getDate());

        if (rbNam.isSelected()) {
            nv.setGioiTinh(1);
        } else {
            nv.setGioiTinh(0);
        }
        //   if (rbDangLam.isSelected()) {
        //  nv.setTrangThai(1);
        //  } else {
        //     nv.setTrangThai(0);
        //  }
        nv.setId_chucvu(getid.getIDChucVu(cboChucVu.getSelectedItem() + ""));

        return nv;

    }

    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

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

    void setStatus(boolean insertable) {
        txtMaNV.setEditable(insertable);
        btnThem.setEnabled(insertable);
        btnSUa.setEnabled(insertable);
        btnXoa.setEnabled(insertable);
        boolean first = this.index > 0;
        boolean last = this.index < tbNhanVien.getRowCount() - 1;

    }

    boolean test() {
        // String ktra = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@#$%^&+=!])(?=\\\\S+$).{8,12}$";
        // if (txtMa.getText().isEmpty()) {
        //JOptionPane.showMessageDialog(this, "Mã không bỏ trống");

        // return false;
        //   } else if (txtMa.getText().length() > 20) {
        // JOptionPane.showMessageDialog(this, "Mã không được quá 20 kí tự");
        // return false;
        //  }
        if (txtTenNV.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên nhan vien không bỏ trống", "Thông báo", 0);

            return false;
        }
        if (txtNgaySinh.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không bỏ trống", "Thông báo", 0);

            return false;
        } else {
            try {
                Date sn;
                sn = txtNgaySinh.getDate();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Sinh nhật chưa đúng định dạng", "Thông báo", 0);
                return false;
            }
        }
        if (txtCCCD.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "CCCD không để trống", "Thông báo", 0);
            return false;
        } else if (txtCCCD.getText().contains("0-9{12}")) {
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng", "Thông báo", 0);
            return false;
        }

        if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email không để trống", "Thông báo", 0);
            return false;
        } else if (txtEmail.getText().contains("a-zA-Z0-9" + "." + "@" + "a-zA-Z0-9")) {
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng", "Thông báo", 0);
            return false;
        }
        if (txtDienThoai.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điện thoại không để trống", "Thông báo", 0);
        } else {
            try {
                int phone;
                phone = Integer.parseInt(txtDienThoai.getText());
                if (phone < 10) {
                    JOptionPane.showMessageDialog(this, "Số đt không quá 10 số", "Thông báo", 0);
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không đúng định dạng", "Thông báo", 0);
            }
        }
        if (txtDiaChi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không để trống", "Thông báo", 0);
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // </editor-fold>
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        panelThongTin = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSUa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        txtDienThoai = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rbNam = new javax.swing.JRadioButton();
        rbNu = new javax.swing.JRadioButton();
        cboChucVu = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtTim = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnEport = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        btnTaiMau = new javax.swing.JButton();
        tabs = new javax.swing.JTabbedPane();
        panelDangLam = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        lbSoTrang = new javax.swing.JLabel();
        lbLonMax = new javax.swing.JButton();
        lbLon = new javax.swing.JButton();
        lbTrang = new javax.swing.JLabel();
        btnNho = new javax.swing.JButton();
        btnNhoMax = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        panelThongTin.setBackground(new java.awt.Color(255, 255, 255));
        panelThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        panelThongTin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Mã nhân viên:");
        panelThongTin.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 46, -1, -1));

        txtMaNV.setEditable(false);
        panelThongTin.add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 43, 291, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Tên nhân viên:");
        panelThongTin.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 80, -1, -1));
        panelThongTin.add(txtTenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 71, 291, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("SDT:");
        panelThongTin.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(502, 46, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Email:");
        panelThongTin.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(502, 74, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Ngày sinh:");
        panelThongTin.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(502, 117, -1, -1));

        btnThem.setBackground(new java.awt.Color(102, 255, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Hinh/Add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        panelThongTin.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, -1, -1));

        btnSUa.setBackground(new java.awt.Color(102, 255, 255));
        btnSUa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSUa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Hinh/Edit.png"))); // NOI18N
        btnSUa.setText("Sửa");
        btnSUa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSUaActionPerformed(evt);
            }
        });
        panelThongTin.add(btnSUa, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 260, -1, -1));

        btnMoi.setBackground(new java.awt.Color(102, 255, 255));
        btnMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Hinh/Accept.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        panelThongTin.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 260, -1, -1));

        btnXoa.setBackground(new java.awt.Color(102, 255, 255));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Hinh/Delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        panelThongTin.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 260, -1, -1));
        panelThongTin.add(txtDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 43, 260, -1));
        panelThongTin.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 71, 260, -1));

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Giới tính:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Chức vụ");

        buttonGroup1.add(rbNam);
        rbNam.setSelected(true);
        rbNam.setText("Nam");

        buttonGroup1.add(rbNu);
        rbNu.setText("Nữ");

        cboChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rbNam)
                        .addGap(34, 34, 34)
                        .addComponent(rbNu, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cboChucVu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rbNam)
                    .addComponent(rbNu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelThongTin.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 154, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("CCCD:");
        panelThongTin.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 117, -1, -1));
        panelThongTin.add(txtCCCD, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 114, 291, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Địa chỉ:");
        panelThongTin.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(502, 157, -1, -1));
        panelThongTin.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 154, 258, -1));

        jLabel10.setText("Quét QR CCCD");
        panelThongTin.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 20, -1, -1));

        txtNgaySinh.setDateFormatString("dd-MM-yyyy");
        panelThongTin.add(txtNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 114, 260, -1));

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        panelThongTin.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 50, 30, 20));

        jButton1.setText("Quét Mã QR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelThongTin.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 140, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        txtTim.setText("Tìm kiếm theo các trường");
        txtTim.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimFocusLost(evt);
            }
        });
        txtTim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimMouseClicked(evt);
            }
        });
        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Tim kiem");

        btnEport.setBackground(new java.awt.Color(102, 255, 255));
        btnEport.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEport.setText("Export");
        btnEport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEportActionPerformed(evt);
            }
        });

        btnImport.setBackground(new java.awt.Color(102, 255, 255));
        btnImport.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnImport.setText("Import");
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        btnTaiMau.setBackground(new java.awt.Color(102, 255, 255));
        btnTaiMau.setText("Tải mẫu");
        btnTaiMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiMauActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTaiMau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnImport)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(btnEport, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(btnImport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTaiMau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        tabs.setBackground(new java.awt.Color(255, 255, 255));
        tabs.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        panelDangLam.setBackground(new java.awt.Color(255, 255, 255));

        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã NV", "Tên NV", "Chức vụ", "Ngày sinh", "Giới tính", "Điện thoại", "Email", "Địa chỉ", "Trạng thái"
            }
        ));
        tbNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNhanVienMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbNhanVienMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbNhanVien);

        javax.swing.GroupLayout panelDangLamLayout = new javax.swing.GroupLayout(panelDangLam);
        panelDangLam.setLayout(panelDangLamLayout);
        panelDangLamLayout.setHorizontalGroup(
            panelDangLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1126, Short.MAX_VALUE)
        );
        panelDangLamLayout.setVerticalGroup(
            panelDangLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDangLamLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        tabs.addTab("Danh sách nhân viên", panelDangLam);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        lbSoTrang.setText("jLabel14");

        lbLonMax.setText(">>");
        lbLonMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbLonMaxActionPerformed(evt);
            }
        });

        lbLon.setText(">");
        lbLon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbLonActionPerformed(evt);
            }
        });

        lbTrang.setText("jLabel13");

        btnNho.setText("<");
        btnNho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhoActionPerformed(evt);
            }
        });

        btnNhoMax.setText("<<");
        btnNhoMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhoMaxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(394, 394, 394)
                .addComponent(btnNhoMax)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTrang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbLon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbLonMax)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSoTrang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNhoMax)
                        .addComponent(btnNho)
                        .addComponent(lbLon)
                        .addComponent(lbLonMax))
                    .addComponent(lbTrang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbSoTrang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tabs)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        panelThongTin.getAccessibleContext().setAccessibleName("");
        jPanel1.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clearFormNhanVien();
    }//GEN-LAST:event_btnMoiActionPerformed
    public void insert() {
        if (test()) {
            NhanVienRespose nv = this.getFormNhanVien();
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?", "Thêm", JOptionPane.YES_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                try {
                    daoNV.inertNV(nv);
                    this.fillTableNVPhanTrang(daoNV.selectAllPhanTrang(trang));
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ban chon thoát");
            }

        }
    }

    public void SendMail() {
        //NhanVienRespose nv = getFormNhanVien();
        final String username = "nguyenminhduc662004@gmail.com";
        final String password = "ahmm dwgv wfqp uaef";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo đối tượng MimeMessage
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress("nguyenminhduc662004@gmail.com"));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(txtEmail.getText()));
            mimeMessage.setSubject("Tạo tài khoản thành công của CỬA HÀNG BÁN GIÀY Sneaker");
            mimeMessage.setText("Đây là tên tài khoản và mật khẩu của bạn: " + txtTenNV.getText()
                    + "\n"
                    + "\n"
                    + "Tài Khoản: "
                    + txtMaNV.getText()
                    + "\n" + "Mật khẩu: " + nv.getMatKhau()
                    + "\n" + "\n" + "\n" + "Sau khi nhận mật khẩu vui lòng đăng nhập và đổi mật khẩu theo ý của mình!!!!!"
            );

            // Gửi email
            Transport.send(mimeMessage);

        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(this, "Failed to send email: " + ex.getMessage());
        }
    }


    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        insert();
        SendMail();
        this.clearFormNhanVien();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSUaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSUaActionPerformed
        // TODO add your handling code here:
        int index = tbNhanVien.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Moi chon ban can sua");
        } else {
            int chon = JOptionPane.showConfirmDialog(this, "Ban co muon sua hay khong Y/N", "Thong bao", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_OPTION) {
                NhanVienRespose kh = this.getFormNhanVien();
                String ma = txtMaNV.getText();
                if (daoNV.updateNV(kh, ma) > 0) {
                    JOptionPane.showMessageDialog(this, "Sua thành công");
                    this.fillTableNVPhanTrang(daoNV.selectAllPhanTrang(trang));
                    clearFormNhanVien();
                } else {
                    JOptionPane.showMessageDialog(this, "Sua that bai");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ban chon thoat");
            }
        }

    }//GEN-LAST:event_btnSUaActionPerformed


    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Moi chon ban can xoa");
        } else {
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?", "Xóa", JOptionPane.YES_OPTION);
            if (choice == JOptionPane.YES_OPTION) {

                index = tbNhanVien.getSelectedRow();

                String ma = tbNhanVien.getValueAt(index, 1).toString();
                String idHD = daoNV.getIdNhanVien(ma);
                // String idHD = idNV;

                if (daoNV.deleteID(idHD, ma) > 0) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    this.fillTableNVPhanTrang(daoNV.selectAllPhanTrang(trang));
                    clearFormNhanVien();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Xóa Thất Bại");
            }
        }

    }//GEN-LAST:event_btnXoaActionPerformed
    public void XuatFile2() {
        try {
            List<NhanVien> listNV = daoNV.selectAll();
            // Assume you have a class NhanVienDAO to handle data access

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("nhanvien");
            XSSFRow row = null;
            Cell cell = null;

            row = sheet.createRow(2);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Danh Sach Nhan Vien");

            row = sheet.createRow(3);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("MaNV");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("TenNV");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Chucvu");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("NgaySinh");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("GioiTinh");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("CCCD");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("SDT");

            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Email");

            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("DiaChi");

            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue("TrangThai");

            //  listNV = daoNV.selectAll();
            for (int i = 0; i < listNV.size(); i++) {
                row = sheet.createRow(4 + i);

                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i + 1);

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(listNV.get(i).getMaNV());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(listNV.get(i).getTenNV());

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(listNV.get(i).getId_chucVu().getTenCV());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(listNV.get(i).getNgaySinh());

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(listNV.get(i).getGioiTinh());

                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(listNV.get(i).getCccd());

                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue(listNV.get(i).getSDT());

                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue(listNV.get(i).getEmail());

                cell = row.createCell(9, CellType.STRING);
                cell.setCellValue(listNV.get(i).getDiaChi());

                cell = row.createCell(10, CellType.STRING);
                cell.setCellValue(listNV.get(i).getTrangThai());
            }

            File f = new File("D:\\Excel\\NhanVien.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(f);
                workbook.write(fis);
                fis.close();
                JOptionPane.showMessageDialog(this, "In thành công");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi mở file");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi ghi file");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi lấy danh sách hoá đơn");
        }

    }
    private void btnEportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEportActionPerformed
        // TODO add your handling code here:

        XuatFlie();
    }//GEN-LAST:event_btnEportActionPerformed
    public void XuatFlie() {
        try {
            fillTableNVPhanTrang(daoNV.selectAll());
            // Hiển thị hộp thoại để chọn nơi lưu tệp Excel
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                // Đảm bảo tệp có đuôi .xlsx
                if (!saveFile.getName().toLowerCase().endsWith(".xlsx")) {
                    saveFile = new File(saveFile.toString() + ".xlsx");
                }

                // Tạo workbook và sheet
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("nhanvien");

                // Tạo hàng đầu tiên (header) từ tên cột của bảng
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < tbNhanVien.getColumnCount(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(tbNhanVien.getColumnName(i));
                }

                // Sao chép dữ liệu từ bảng vào Excel
                for (int j = 0; j < tbNhanVien.getRowCount(); j++) {
                    Row excelRow = sheet.createRow(j + 1);
                    for (int k = 0; k < tbNhanVien.getColumnCount(); k++) {
                        Cell cell = excelRow.createCell(k);
                        Object cellValue = tbNhanVien.getValueAt(j, k);
                        if (cellValue != null) {
                            cell.setCellValue(cellValue.toString());
                        }
                    }
                }

                // Lưu workbook vào tệp
                try (FileOutputStream out = new FileOutputStream(saveFile)) {
                    workbook.write(out);
                }

                // Đóng workbook
                workbook.close();
            }
            JOptionPane.showMessageDialog(this, "Xuất file thành công");
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ theo ý bạn (hiển thị thông báo, ghi log, v.v.)
        }

    }

    private void tbNhanVienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhanVienMousePressed


    }//GEN-LAST:event_tbNhanVienMousePressed

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimActionPerformed

    private void txtTimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimMouseClicked

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        // TODO add your handling code here:
        String searchString = txtTim.getText();
        serch(searchString);

    }//GEN-LAST:event_txtTimKeyReleased

    private void tbNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhanVienMouseClicked
        // TODO add your handling code here:

        index = tbNhanVien.getSelectedRow();
        this.setFromNV(index);
    }//GEN-LAST:event_tbNhanVienMouseClicked

    private void btnNhoMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhoMaxActionPerformed
        // TODO add your handling code here:
        trang = 1;
        fillTableNVPhanTrang(daoNV.selectAllPhanTrang(trang));
        lbTrang.setText("1");
        lbSoTrang.setText("1/" + soTrang);
    }//GEN-LAST:event_btnNhoMaxActionPerformed

    private void lbLonMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbLonMaxActionPerformed
        // TODO add your handling code here:
        trang = soTrang;
        fillTableNVPhanTrang(daoNV.selectAllPhanTrang(trang));
        lbTrang.setText("" + soTrang);
        lbSoTrang.setText(soTrang + "/" + soTrang);
    }//GEN-LAST:event_lbLonMaxActionPerformed

    private void btnNhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhoActionPerformed
        // TODO add your handling code here:
        if (trang > 1) {
            trang--;
            fillTableNVPhanTrang(daoNV.selectAllPhanTrang(trang));
            lbTrang.setText("" + trang);
            lbSoTrang.setText(trang + "/" + soTrang);
        }
    }//GEN-LAST:event_btnNhoActionPerformed

    private void lbLonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbLonActionPerformed
        // TODO add your handling code here:
        if (trang < soTrang) {
            trang++;
            fillTableNVPhanTrang(daoNV.selectAllPhanTrang(trang));
            lbTrang.setText("" + trang);
            lbSoTrang.setText(trang + "/" + soTrang);
        }
    }//GEN-LAST:event_lbLonActionPerformed

    private void btnTaiMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiMauActionPerformed
        try {
            // TODO: Mã để tải mẫu từ tệp Excel
            // Ví dụ: Hiển thị thông báo thành công
            JOptionPane.showMessageDialog(this, "Mẫu đã được tải thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            JOptionPane.showMessageDialog(this, "Lỗi khi tải mẫu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnTaiMauActionPerformed

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
        // TODO add your handling code here:
        try {
            // TODO: Mã để import dữ liệu từ tệp Excel
            // Ví dụ: Sử dụng Apache POI để đọc Excel

            // Chọn tệp Excel để import
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn tệp Excel");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel files", "xlsx"));

            int userSelection = fileChooser.showOpenDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                importDataFromExcel(selectedFile);
            }
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            JOptionPane.showMessageDialog(this, "Lỗi khi import dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnImportActionPerformed

    private void txtTimFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimFocusGained
        // TODO add your handling code here:
        if (txtTim.getText().equals("Tìm kiếm theo các trường")) {
            txtTim.setText(null);
            txtTim.requestFocus();
            RemovePleacehoderStyle(txtTim);
        }
    }//GEN-LAST:event_txtTimFocusGained

    private void txtTimFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimFocusLost
        // TODO add your handling code here:
        if (txtTim.getText().length() == 0) {
            AddPleacehoderStyle(txtTim);
            txtTim.setText("Tìm kiếm theo các trường");
        }
    }//GEN-LAST:event_txtTimFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        QuetQR();
    }//GEN-LAST:event_jButton1ActionPerformed

    void QuetQR() {
        Main m = new Main();
        new QuetQRSanPhamJDiaLog(m, true).setVisible(true);
    }

    public void serch(String str) {
        model = (DefaultTableModel) this.tbNhanVien.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tbNhanVien.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEport;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNho;
    private javax.swing.JButton btnNhoMax;
    private javax.swing.JButton btnSUa;
    private javax.swing.JButton btnTaiMau;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lbLon;
    private javax.swing.JButton lbLonMax;
    private javax.swing.JLabel lbSoTrang;
    private javax.swing.JLabel lbTrang;
    private javax.swing.JPanel panelDangLam;
    private javax.swing.JPanel panelThongTin;
    private javax.swing.JRadioButton rbNam;
    private javax.swing.JRadioButton rbNu;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDienThoai;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNV;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        jPanel7.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 180));

        executor.execute(this);
    }

    @Override
    public void run() {
        String randomCode = generateRandomCode(12);
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException e) {
                //No result...
            }
            if (result != null) {
                txtCCCD.setText(randomCode);
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    public static String generateRandomCode(int length) {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomNumber = random.nextInt(10); // Sinh số ngẫu nhiên từ 0 đến 9
            codeBuilder.append(randomNumber);
        }

        return codeBuilder.toString();
    }
//Tải mẫu and Import

    private void importDataFromExcel(File file) throws Exception {
        try (FileInputStream fis = new FileInputStream(file); Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                // TODO: Xử lý dữ liệu từ mỗi ô trong tệp Excel
                String email = cellIterator.next().toString(); // Giả sử cột đầu tiên chứa địa chỉ email
                String subject = cellIterator.next().toString(); // Giả sử cột thứ hai chứa tiêu đề email
                String message = cellIterator.next().toString(); // Giả sử cột thứ ba chứa nội dung email

                // Gửi email
                sendEmail(email, subject, message);
            }
        }
    }

    private void sendEmail(String to, String subject, String body) {
        // TODO: Cấu hình thông tin máy chủ email, người gửi, mật khẩu, v.v.
        String host = "smtp.gmail.com";
        String username = "nguyenminhduc662004@gmail.com";
        String password = "ahmm dwgv wfqp uaef";
        int port = 587;

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo đối tượng MimeMessage
            Message message = new MimeMessage(session);

            // Đặt thông tin người gửi và người nhận
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            // Gửi email
            Transport.send(message);

            // Hiển thị thông báo gửi email thành công (có thể sử dụng JOptionPane)
            System.out.println("Email sent successfully to: " + to);
        } catch (MessagingException e) {
            // Xử lý lỗi nếu có
            e.printStackTrace();
            // Hiển thị thông báo lỗi (có thể sử dụng JOptionPane)
            System.err.println("Error sending email to: " + to + " - " + e.getMessage());
        }
    }
// Tỉm kiếm anả

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
}
