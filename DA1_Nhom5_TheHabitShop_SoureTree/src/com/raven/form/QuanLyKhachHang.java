/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.raven.classinterface.TaoMaTuSinh_inf;
import com.raven.classmodel.HoaDon;
import com.raven.classmodel.KhachHang;
import com.raven.classmodel.NhanVien;
import com.raven.reponsitory.DBConnect;
import com.raven.reponsitory.KhachHang_DAO;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.text.ParseException;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author MSI 15
 */
public class QuanLyKhachHang extends javax.swing.JPanel {

    private ArrayList<KhachHang> list = new ArrayList<>();
    private KhachHang nv = new KhachHang();
    private KhachHang_DAO daoKH = new KhachHang_DAO();
    private DefaultTableModel model = new DefaultTableModel();
    private int index = 0;
    Connection cn;
    long count, soTrang, trang = 1;
    Statement st;
    ResultSet rs;

    /**
     * Creates new form QuanLyKhachHang
     */
    public QuanLyKhachHang() {
        initComponents();
        // this.fillTableKhachHang(daoKH.selectAll());
        this.fillTableHoaDon();
        //fillComboBoxKH();
        fillTableKhachHangPhanTrang(daoKH.selectAllPhanTrang(1));
        countDb();
        if (count % 5 == 0) {
            soTrang = count / 5;
        } else {
            soTrang = count / 5 + 1;
        }

        lbSoTrang.setText("1/" + soTrang);
        lbTrang.setText("1");
        AddPleacehoderStyle(txtTim);
        AddPleacehoderStyle(txtTimHoaDon);
    }

    public void countDb() {
        try {
            String sql = "Select count(*) from KhachHang";
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
            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void fillTableKhachHangPhanTrang(List<KhachHang> list) {
        model = (DefaultTableModel) tbKhachHang.getModel();
        model.setRowCount(0);
        int index = 0;
        try {
            for (KhachHang kh : list) {
                index++;
                Object[] row = new Object[]{
                    index,
                    kh.getMaKH(),
                    kh.getTenKH(),
                    kh.isGioiTinh() ? "Nữ" : "Nam",
                    kh.getNgaySinh(),
                    kh.getSdt(),
                    kh.getEmail(),
                    kh.getDiaChi(),};
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu");
        }
    }

//    void fillTableKhachHang(List<KhachHang> list) {
//        model = (DefaultTableModel) tbKhachHang.getModel();
//        model.setRowCount(0);
//        int index = 0;
//        try {
//            for (KhachHang kh : list) {
//                index++;
//                Object[] row = new Object[]{
//                    index,
//                    kh.getMaKH(),
//                    kh.getTenKH(),
//                    kh.isGioiTinh() ? "Nam" : "Nữ",
//                    kh.getNgaySinh(),
//                    kh.getSdt(),
//                    kh.getEmail(),
//                    kh.getDiaChi(),};
//                model.addRow(row);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu");
//        }
//    }
    void fillTableHoaDon() {
        model = (DefaultTableModel) tbHoaDon.getModel();
        model.setRowCount(0);
        List<HoaDon> list = daoKH.selectAllHoaDon();
        int index = 0;
        try {
            for (HoaDon kh : list) {
                index++;
                Object[] row = new Object[]{
                    index,
                    kh.getTenNguoiNhan(),
                    kh.getMa(),
                    kh.getDiaChi(),
                    kh.getNgaythanhtoan(),
                    NumberFormat.getInstance().format(kh.getTongTienHoaDon()),
                    kh.tinhTrangHoaDon(),};
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu");
        }
    }

    // } else {
    // Handle the case when the list is null
    // JOptionPane.showMessageDialog(this, "Lỗi trống danh sách Mối Quan Hệ");
    //}
    void showData(int index) {
        KhachHang kh = daoKH.selectAllPhanTrang(trang).get(index);
        txtMa.setText(kh.getMaKH());
        txtTen.setText(kh.getTenKH());
        txtNgaySinh.setDate(kh.getNgaySinh());

        if (kh.isGioiTinh()) {
            rbNu.setSelected(true);
        } else {
            rbNam.setSelected(true);
        }
        txtSDT.setText(kh.getSdt());
        txtEmail.setText(kh.getEmail());
        txtDiaChi.setText(kh.getDiaChi());
    }

    private KhachHang getFormData() throws ParseException {
        //Tạo mã nhân viên
        TaoMaTuSinh_inf taoMaNhanVien = new TaoMaTuSinh_inf() {
            @Override
            public String maTuSinh() {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                int gen = new Random().nextInt(1000000);
                return "MaKH" + gen;
            }
        };
        String maKH = taoMaNhanVien.maTuSinh();
        String ten = txtTen.getText();
        String sdt = txtSDT.getText();
        String email = txtEmail.getText();
        String diaChi = txtDiaChi.getText();
        boolean gioiTinh = rbNu.isSelected();
        Date ngaySinh = txtNgaySinh.getDate();
        UUID id_nguoiTao = null;

        return new KhachHang(maKH, ten, gioiTinh, ngaySinh, sdt, email, diaChi, id_nguoiTao);
    }

    void edit() {
        try {
            String maKH = (String) tbKhachHang.getValueAt(this.index, 1);
            KhachHang model = daoKH.selectById(maKH);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void clear() {
        this.setModel(new KhachHang());
        this.setStatus(true);
    }

    void setModel(KhachHang nv) {

        txtMa.setText(nv.getMaKH());
        txtTen.setText(nv.getTenKH());
        txtSDT.setText(nv.getSdt());
        txtEmail.setText(nv.getEmail());
        txtNgaySinh.setDate(nv.getNgaySinh());
        txtDiaChi.setText(nv.getDiaChi());
        rbNam.setSelected(nv.isGioiTinh());
        rbNu.setSelected(!nv.isGioiTinh());

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

    KhachHang getModel() {
        KhachHang kh = new KhachHang();

        String ma = txtMa.getText();
        String ten = txtTen.getText();
        String sdt = txtSDT.getText();
        String email = txtEmail.getText();
        String diaChi = txtDiaChi.getText();
        boolean gioiTinh = rbNu.isSelected();
        Date ngaySinh = txtNgaySinh.getDate();
        // Date ngaySinh = null;
        //try {
        // ngaySinh = format.parse();
        //  } catch (ParseException e) {
        //e.printStackTrace();
        // }

        return new KhachHang(ma, ten, gioiTinh, ngaySinh, sdt, email, diaChi);
    }

    private KhachHang getForm() {
        KhachHang kh = new KhachHang();
        kh.setMaKH(txtMa.getText());
        kh.setTenKH(txtTen.getText());
        kh.setNgaySinh(txtNgaySinh.getDate());
        kh.setGioiTinh(rbNu.isSelected());
        kh.setSdt(txtSDT.getText());
        kh.setEmail(txtEmail.getText());
        kh.setDiaChi(txtDiaChi.getText());

        return kh;
    }

    void setStatus(boolean insertable) {
        txtMa.setEditable(insertable);
        btnThem.setEnabled(insertable);
        btnSUa.setEnabled(insertable);
        btnXoa.setEnabled(insertable);
        boolean first = this.index > 0;
        boolean last = this.index < tbKhachHang.getRowCount() - 1;

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
        if (txtTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên không bỏ trống");

            return false;
        }
        if (txtNgaySinh.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không bỏ trống");

            return false;
        } else {
            try {
                Date sn;
                sn = txtNgaySinh.getDate();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Sinh nhật chưa đúng định dạng");
                return false;
            }
        }
        if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email không để trống");
            return false;
        } else if (txtEmail.getText().contains("a-zA-Z0-9" + "." + "@" + "a-zA-Z0-9")) {
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng");
            return false;
        }
        if (txtSDT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điện thoại không để trống");
        } else {
            try {
                int phone;
                phone = Integer.parseInt(txtSDT.getText());
                if (phone < 10) {
                    JOptionPane.showMessageDialog(this, "Số đt không quá 10 số");
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không đúng định dạng");
            }
        }
        if (txtDiaChi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không để trống");
            return false;
        }
        return true;
    }

//
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        rbNam = new javax.swing.JRadioButton();
        rbNu = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSUa = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        txtDiaChi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbKhachHang = new javax.swing.JTable();
        txtTim = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnNhoMax = new javax.swing.JButton();
        btnNho = new javax.swing.JButton();
        lbTrang = new javax.swing.JLabel();
        btnLon = new javax.swing.JButton();
        btnLonMax = new javax.swing.JButton();
        lbSoTrang = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();
        txtTimHoaDon = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Mã khách hàng:");

        txtMa.setEditable(false);
        txtMa.setEnabled(false);
        txtMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tên khách hàng:");

        txtTen.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Giới tính:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("SDT:");

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbNam);
        rbNam.setSelected(true);
        rbNam.setText("Nam");

        buttonGroup1.add(rbNu);
        rbNu.setText("Nữ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Email:");

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Địa chỉ:");

        btnThem.setBackground(new java.awt.Color(102, 255, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Hinh/Add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSUa.setBackground(new java.awt.Color(102, 255, 255));
        btnSUa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSUa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Hinh/Edit.png"))); // NOI18N
        btnSUa.setText("Sửa");
        btnSUa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSUaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(102, 255, 255));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Hinh/Delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setBackground(new java.awt.Color(102, 255, 255));
        btnMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Hinh/Accept.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Ngày sinh:");

        txtNgaySinh.setDateFormatString("dd_MM_yyyy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbNam)
                        .addGap(18, 18, 18)
                        .addComponent(rbNu))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7))
                        .addGap(72, 72, 72)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                            .addComponent(txtMa)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(87, 87, 87)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSDT)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(32, 32, 32))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXoa)
                            .addComponent(btnSUa))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)))
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(rbNam)
                        .addComponent(rbNu))
                    .addComponent(btnSUa)
                    .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoa)
                    .addComponent(btnMoi))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tbKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã KH", "Tên KH", "Giới tính", "Ngày sinh", "SĐT", "Email", "Địa chỉ"
            }
        ));
        tbKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhachHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbKhachHang);

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

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Tim kiem");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btnNhoMax.setText("<<");
        btnNhoMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhoMaxActionPerformed(evt);
            }
        });

        btnNho.setText("<");
        btnNho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhoActionPerformed(evt);
            }
        });

        lbTrang.setText("jLabel8");

        btnLon.setText(">");
        btnLon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLonActionPerformed(evt);
            }
        });

        btnLonMax.setText(">>");
        btnLonMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLonMaxActionPerformed(evt);
            }
        });

        lbSoTrang.setText("jLabel11");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNhoMax)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTrang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLonMax)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbSoTrang)
                .addGap(285, 285, 285))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNhoMax)
                    .addComponent(btnNho)
                    .addComponent(lbTrang)
                    .addComponent(btnLon)
                    .addComponent(btnLonMax)
                    .addComponent(lbSoTrang))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 934, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông tin cá nhân", jPanel3);

        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên Khách hàng", "Mã HD", "Địa chỉ", "Ngày giao dịch", "Tổng tiền", "Trạng thái"
            }
        ));
        tbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbHoaDonMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(tbHoaDon);

        txtTimHoaDon.setText("Tìm kiếm theo các trường");
        txtTimHoaDon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimHoaDonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimHoaDonFocusLost(evt);
            }
        });
        txtTimHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimHoaDonActionPerformed(evt);
            }
        });
        txtTimHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimHoaDonKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Lịch sử giao dịch", jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaActionPerformed

    private void tbKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhachHangMouseClicked
        // TODO add your handling code here:
        index = tbKhachHang.getSelectedRow();
        this.showData(index);
    }//GEN-LAST:event_tbKhachHangMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        List<KhachHang> list = new ArrayList();
        if (test()) {
            int chon = JOptionPane.showConfirmDialog(this, "Ban co muon them hay khong Y/N", "Thong bao", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_OPTION) {
                try {
                    boolean addNhanVien = daoKH.inertKH(getFormData());
                    list = daoKH.selectAll();
                    fillTableKhachHangPhanTrang(daoKH.selectAllPhanTrang(trang));
                    clear();
                    if (addNhanVien) {
                        JOptionPane.showMessageDialog(this, "Thêm thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm thất bại");
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ban chon thoat");
            }

        }


    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSUaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSUaActionPerformed
        // TODO add your handling code here:
        int index = tbKhachHang.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Moi chon ban can sua");
        } else {
            int chon = JOptionPane.showConfirmDialog(this, "Ban co muon sua hay khong Y/N", "Thong bao", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_OPTION) {
                KhachHang kh = this.getForm();
                String ma = txtMa.getText();
                if (daoKH.updateKH(kh, ma) > 0) {
                    JOptionPane.showMessageDialog(this, "Sua thành công");
                    fillTableKhachHangPhanTrang(daoKH.selectAllPhanTrang(trang));
                    clear();
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
        int index = tbKhachHang.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Moi chon ban can xoa");
        } else {
            int chon = JOptionPane.showConfirmDialog(this, "Ban co muon xoa hay khong Y/N", "Thong bao", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_OPTION) {
                String ma = tbKhachHang.getValueAt(index, 1).toString();
                if (daoKH.deleteKH(ma) > 0) {
                    JOptionPane.showMessageDialog(this, "Xoa thành công");
                    fillTableKhachHangPhanTrang(daoKH.selectAllPhanTrang(trang));
                    clear();
                } else {
                    JOptionPane.showMessageDialog(this, "Xoa that bai");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ban chon thoat");
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void txtTimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimMouseClicked

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        // TODO add your handling code here:
        String searchString = txtTim.getText();
        serch(searchString);
    }//GEN-LAST:event_txtTimKeyReleased

    private void txtTimHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimHoaDonActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimHoaDonActionPerformed

    private void txtTimHoaDonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimHoaDonKeyReleased
        // TODO add your handling code here:
        String searchString = txtTimHoaDon.getText();
        serchHD(searchString);
    }//GEN-LAST:event_txtTimHoaDonKeyReleased

    private void tbHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tbHoaDonMousePressed

    private void btnNhoMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhoMaxActionPerformed
        // TODO add your handling code here:
        trang = 1;
        fillTableKhachHangPhanTrang(daoKH.selectAllPhanTrang(trang));
        lbTrang.setText("1");
        lbSoTrang.setText("1/" + soTrang);
    }//GEN-LAST:event_btnNhoMaxActionPerformed

    private void btnLonMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLonMaxActionPerformed
        // TODO add your handling code here:
        trang = soTrang;
        fillTableKhachHangPhanTrang(daoKH.selectAllPhanTrang(trang));
        lbTrang.setText("" + soTrang);
        lbSoTrang.setText(soTrang + "/" + soTrang);
    }//GEN-LAST:event_btnLonMaxActionPerformed

    private void btnNhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhoActionPerformed
        // TODO add your handling code here:
        if (trang > 1) {
            trang--;
            fillTableKhachHangPhanTrang(daoKH.selectAllPhanTrang(trang));
            lbTrang.setText("" + trang);
            lbSoTrang.setText(trang + "/" + soTrang);
        }
    }//GEN-LAST:event_btnNhoActionPerformed

    private void btnLonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLonActionPerformed
        // TODO add your handling code here:
        if (trang < soTrang) {
            trang++;
            fillTableKhachHangPhanTrang(daoKH.selectAllPhanTrang(trang));
            lbTrang.setText("" + trang);
            lbSoTrang.setText(trang + "/" + soTrang);
        }
    }//GEN-LAST:event_btnLonActionPerformed

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

    private void txtTimHoaDonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimHoaDonFocusGained
        // TODO add your handling code here:

        if (txtTimHoaDon.getText().equals("Tìm kiếm theo các trường")) {
            txtTimHoaDon.setText(null);
            txtTimHoaDon.requestFocus();
            RemovePleacehoderStyle(txtTimHoaDon);
        }
    }//GEN-LAST:event_txtTimHoaDonFocusGained

    private void txtTimHoaDonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimHoaDonFocusLost
        // TODO add your handling code here:
        if (txtTimHoaDon.getText().length() == 0) {
            AddPleacehoderStyle(txtTimHoaDon);
            txtTimHoaDon.setText("Tìm kiếm theo các trường");
        }
    }//GEN-LAST:event_txtTimHoaDonFocusLost
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

    public void serch(String str) {
        model = (DefaultTableModel) this.tbKhachHang.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tbKhachHang.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }

    public void serchHD(String str) {
        model = (DefaultTableModel) this.tbHoaDon.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tbHoaDon.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLon;
    private javax.swing.JButton btnLonMax;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNho;
    private javax.swing.JButton btnNhoMax;
    private javax.swing.JButton btnSUa;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbSoTrang;
    private javax.swing.JLabel lbTrang;
    private javax.swing.JRadioButton rbNam;
    private javax.swing.JRadioButton rbNu;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTable tbKhachHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMa;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTimHoaDon;
    // End of variables declaration//GEN-END:variables

}
