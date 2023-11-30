/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import java.util.Date;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.raven.classinterface.TaoMaTuSinh_inf;
import com.raven.classmodel.ChiTietSanPham;
import com.raven.classmodel.HinhThucThanhToan;
import com.raven.classmodel.HoaDon;
import com.raven.classmodel.HoaDonChiTiet;
import com.raven.classmodel.HoaDonTraHang;
import com.raven.classmodel.HoaDonTraHangCT;
import com.raven.classmodel.KhachHang;
import com.raven.classmodel.LichSuHoaDon;
import com.raven.classmodel.NhanVien;
import com.raven.classmodel.XLogin;
import com.raven.main.LoginDA1;
import com.raven.main.Main;
import com.raven.reponsitory.BanHangReponsitory;
import com.raven.reponsitory.ChiTietSanPhamRepon;
import com.raven.reponsitory.DBConnect;
import com.raven.reponsitory.HoaDonChiTietRepon;
import com.raven.reponsitory.HoaDonRepon;
import com.raven.reponsitory.HoaDonTraHangCTRepon;
import com.raven.reponsitory.HoaDonTraHangRepon;
import com.raven.reponsitory.LichSuHoaDonRepon;
import com.raven.service.GetIdSPCTService;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.sql.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;
import javax.swing.JTextField;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DecimalFormat;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author ADMIN
 */
public class HoaDon_Form extends javax.swing.JPanel implements Runnable, ThreadFactory {

    private HoaDonChiTietRepon reponHDCT = new HoaDonChiTietRepon();
    private HoaDonTraHangCTRepon reponHDTHCT = new HoaDonTraHangCTRepon();
    BanHangReponsitory reponBH = new BanHangReponsitory();
    List<LichSuHoaDon> listLSHD = new ArrayList<>();
    LichSuHoaDonRepon reponLSHD = new LichSuHoaDonRepon();
    HoaDonRepon reponHD = new HoaDonRepon();
    ChiTietSanPhamRepon reponCTSP = new ChiTietSanPhamRepon();
    DefaultTableModel model = new DefaultTableModel();
    HoaDonTraHangRepon reponHDTH = new HoaDonTraHangRepon();
    List<HoaDon> listHD = new ArrayList<>();
    List<HoaDonChiTiet> listHDCT = new ArrayList<>();
    List<ChiTietSanPham> listCTSP = new ArrayList<>();
    List<HoaDonTraHang> listHDTH = new ArrayList<>();
    List<HoaDonTraHangCT> listHDTHCT = new ArrayList<>();
    LichSuHoaDonRepon lshdRepon = new LichSuHoaDonRepon();
    DefaultComboBoxModel<String> dcb1 = new DefaultComboBoxModel<>();
    DefaultTableModel modelHDCT = new DefaultTableModel();
    DefaultTableModel modelHDTHCT = new DefaultTableModel();
    DefaultTableModel modelLSHD = new DefaultTableModel();
    private int index = 1;
    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    private int tranghientai = 0;
    private int tongsoTrang = 1;
    private int count = 1;
    private GetIdSPCTService getidspct = new GetIdSPCTService();
    public static final String pathUnicode = "font\\unicode.ttf";
    private DecimalFormat df = new DecimalFormat("#,###");
    LoginDA1 login = null;

    /**
     * Creates new form HoaDon
     */
    public HoaDon_Form() {
        initComponents();
        //initWebcam();
        modelHDTHCT = (DefaultTableModel) tblHoaDonTraHang.getModel();
        modelHDCT = (DefaultTableModel) tblhoaDonCT.getModel();
        loadTableHoaDon9(reponHD.getAll());
        //PhanTrangHoaDonChiTiet(reponHDCT.getAll());
        //fillTable(reponHDCT.getAll());
        //fillTableHDCT(reponHDCT.getAll());
        dcb1 = (DefaultComboBoxModel<String>) cboTT.getModel();
        loadCBB();
        loadTableCBB(Integer.parseInt((String) dcb1.getSelectedItem()));
        AddPleacehoderStyle(txtTimKiem);
        AddPleacehoderStyle(txt_TimKiem);
        // loadTablelichSuHoaDon(reponLSHD.getListHD());

    }

    private void clearTable() {
        model.setRowCount(0);
    }

    private void updateTable(List<HoaDon> listHD, int start, int end) {
        for (int i = start; i < end; i++) {
            HoaDon hoaDon = listHD.get(i);
            String tinhTrang = getTinhTrangValue(hoaDon.getTinhTrang()); // Thay thế bằng logic thực tế
            model.addRow(new Object[]{
                i + 1,
                hoaDon.getMa(),
                hoaDon.getIdNhanVien().getMaNV(),
                hoaDon.getIdKhachHang().getTenKH(),
                hoaDon.getIdKhachHang().getSdt(),
                date2String(hoaDon.getNgaytaohoadon()),
                date2String(hoaDon.getNgaythanhtoan()),
                NumberFormat.getInstance().format(hoaDon.getTongTienHoaDon()),
                hoaDon.getHttt().getTen(),
                hoaDon.getGhichu(),
                tinhTrang
            });
        }
    }

    private void updateLabels() {
        lblRecordHD.setText(tranghientai + 1 + "/" + tongsoTrang);
        lblSoHoaDon.setText("Số hóa đơn: " + count);
    }

    void loadTableHoaDon9(List<HoaDon> listHD) {
        model = (DefaultTableModel) tblHoaDon.getModel();
        count = listHD.size();

        if (count == 0) {
            tranghientai = 0;
            tongsoTrang = 1;
        } else {
            int rowsPerPage = 1;
            tongsoTrang = (int) Math.ceil((double) count / rowsPerPage);
            tranghientai = Math.max(0, Math.min(tranghientai, tongsoTrang - 1));

            int start = rowsPerPage * tranghientai;
            int end = Math.min(start + rowsPerPage, count);

            clearTable();
            updateTable(listHD, start, end);
        }

        updateLabels();
    }

    private String getTinhTrangValue(int tinhTrangValue) {
        switch (tinhTrangValue) {
            case 0:
                return "Đã Giao Hàng";
            case 1:
                return "Đã Hủy";
            case 2:
                return "Chưa Thanh Toán";
            case 3:
                return "Đã Thanh Toán";
            case 4:
                return "Tất Cả";
            default:
                return "Unknown";
        }
    }

//    // load data phần trả hàng
//    void loadTableThongTinTraHang(List<HoaDonTraHang> listHDTH) {
//        model = (DefaultTableModel) tblThongTinTraHang.getModel();
//        model.setRowCount(0);
//        int stt = 1;
//        for (HoaDonTraHang hdth : listHDTH) {
//            Object[] row = new Object[]{
//                stt++,
//                hdth.getMa(),
//                hdth.getNhanVien().getTen(),
//                hdth.getKhachHang().getTen(),
//                date2String(hdth.getNgayTraHang()),
//                NumberFormat.getInstance().format(hdth.getTienHoanTra()),};
//            model.addRow(row);
//        }
//    }
    void fillTableHDCT(List<HoaDonChiTiet> listHDCT) {
        model = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (HoaDonChiTiet hdct : listHDCT) {
            Object[] row = new Object[]{
                stt++,
                hdct.getIdSPCT().getMa(),
                hdct.getIdSPCT().getTenSanPham().getTenSP(),
                hdct.getSoLuong(),
                NumberFormat.getInstance().format(hdct.getIdSPCT().getGiaBan1()),
                hdct.getIdSPCT().getTenNhanHieu().getTen(),
                hdct.getIdSPCT().getTenMauSac().getTenMauSac(),
                hdct.getIdSPCT().getTenKichCo().getTenKichCo(),
                NumberFormat.getInstance().format(hdct.getHoaDon().getTongTienHoaDon())
            };
            model.addRow(row);
        }
    }

    void loadTableTraHangCT(List<HoaDonTraHangCT> listHDTHCT) {
        model = (DefaultTableModel) tblHoaDonTraHang.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (HoaDonTraHangCT hdth : listHDTHCT) {
            Object[] row = new Object[]{
                stt++,
                hdth.getHdth().getMa(),
                hdth.getCtsp().getTenSanPham().getTenSP(),
                hdth.getCtsp().getTenNhanHieu().getTen(),
                hdth.getCtsp().getTenMauSac().getTenMauSac(),
                hdth.getCtsp().getTenKichCo().getTenKichCo(),
                hdth.getSoluong(),
                hdth.getGia_spkhimua(),
                NumberFormat.getInstance().format(hdth.getHdth().getTienHoanTra())
            };
            model.addRow(row);
        }
    }

    public void AddPleacehoderStyle(JTextField textField) {
        java.awt.Font font = textField.getFont();
        font = font.deriveFont(Font.ITALIC);
        textField.setFont(font);
        textField.setForeground(Color.gray);
    }

    public void RemovePleacehoderStyle(JTextField textField) {
        java.awt.Font font = textField.getFont();
        font = font.deriveFont(Font.ITALIC);
        textField.setFont(font);
        textField.setForeground(Color.black);
    }

    void loadCBB() {
        for (int i = 0; i <= 3; i++) {
            dcb1.addElement(String.valueOf(i));
        }
    }

//    void loadCBB(){
//        dcb1.addElement(maHDCT);
//    }
    private String date2String(java.util.Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");

        return sdf.format(date);
    }

    // Hàm tự viết để chuyển đổi ngày tháng
    private java.util.Date parseDate(String ngayThang) {
        // Đối tượng hỗ trợ đọc kiểu dữ liệu ngày tháng
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        //String date = DateFormat.
        try {
            return sdf.parse(ngayThang);
        } catch (Exception e) {
            //Nếu lỗi trả về thời điểm hiện tại
            return new java.util.Date();
        }
    }

    void loadTableHoaDon(ArrayList<HoaDon> listHD) {
        model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        for (HoaDon hoaDon : listHD) {
            String tinhTrang = "";
            int tinhTrangValue = hoaDon.getTinhTrang();
            if (tinhTrangValue == 0) {
                tinhTrang = "Đã Giao Hàng";
            } else if (tinhTrangValue == 1) {
                tinhTrang = "Đã Hủy";
            } else if (tinhTrangValue == 2) {
                tinhTrang = "Chưa Thanh Toán";
            } else {
                tinhTrang = "Đã Thanh Toán";
            }
            Object[] row = new Object[]{
                index++,
                hoaDon.getMa(),
                hoaDon.getIdNhanVien().getMaNV(),
                hoaDon.getIdKhachHang().getTenKH(),
                hoaDon.getIdKhachHang().getSdt(),
                date2String(hoaDon.getNgaytaohoadon()),
                date2String(hoaDon.getNgaythanhtoan()),
                NumberFormat.getInstance().format(hoaDon.getTongTienHoaDon()),
                hoaDon.getHttt().getTen(),
                hoaDon.getGhichu(),
                tinhTrang};
            model.addRow(row);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jSeparator8 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cboTT = new javax.swing.JComboBox<>();
        btnXuatHoaDon = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblhoaDonCT = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        dateNgayThanhToan2 = new com.toedter.calendar.JDateChooser();
        dateNgayThanhToan = new com.toedter.calendar.JDateChooser();
        btnInHoaDon = new javax.swing.JButton();
        btnTaoHoaDon = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        lblRecordHD = new javax.swing.JLabel();
        lblSoHoaDon = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btn_Next = new javax.swing.JButton();
        btn_Last = new javax.swing.JButton();
        btn_First = new javax.swing.JButton();
        lbl_RecordHD = new javax.swing.JLabel();
        lbl_SoHoaDon = new javax.swing.JLabel();
        btn_Back = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblLichSuHoaDon = new javax.swing.JTable();
        btnLoc = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonTraHang = new javax.swing.JTable();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        pnQuetQR = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txt_TimKiem = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaHDTH = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        btnXuatDanhSach = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        btnTraHang = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnQuetQR = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Hóa Đơn"), "Hóa Đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txtTimKiem.setText("Tìm theo các trường của table");
        txtTimKiem.setToolTipText("");
        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });
        txtTimKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKiemFocusLost(evt);
            }
        });
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Mã Nhân Viên", "Tên Khách Hàng", "SDT", "Ngày tạo", "Ngày Thanh Toán", "Tổng Tiền", "Hình Thức", "Ghi chú", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false, false, true, false, false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblHoaDon);
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(0).setMinWidth(40);
            tblHoaDon.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Tìm Kiếm");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Trạng thái");

        cboTT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTTItemStateChanged(evt);
            }
        });
        cboTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTTActionPerformed(evt);
            }
        });

        btnXuatHoaDon.setBackground(new java.awt.Color(102, 204, 0));
        btnXuatHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXuatHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatHoaDon.setText("Xuất Danh Sách");
        btnXuatHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatHoaDonActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn Chi Tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblhoaDonCT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblhoaDonCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Ma CTSP", "Tên SP", "SL", "Giá", "Tổng Tiền "
            }
        ));
        tblhoaDonCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhoaDonCTMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblhoaDonCT);
        if (tblhoaDonCT.getColumnModel().getColumnCount() > 0) {
            tblhoaDonCT.getColumnModel().getColumn(0).setMinWidth(40);
            tblhoaDonCT.getColumnModel().getColumn(0).setMaxWidth(40);
            tblhoaDonCT.getColumnModel().getColumn(1).setMinWidth(80);
            tblhoaDonCT.getColumnModel().getColumn(1).setMaxWidth(80);
            tblhoaDonCT.getColumnModel().getColumn(5).setMinWidth(150);
            tblhoaDonCT.getColumnModel().getColumn(5).setMaxWidth(150);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Từ");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Đến");

        btnInHoaDon.setBackground(new java.awt.Color(153, 153, 255));
        btnInHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnInHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnInHoaDon.setText("In Hóa Đơn");
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });

        btnTaoHoaDon.setBackground(new java.awt.Color(51, 204, 255));
        btnTaoHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTaoHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoHoaDon.setText("Tạo Hóa Đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(153, 204, 255));
        btnLast.setText(">>");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnFirst.setBackground(new java.awt.Color(153, 204, 255));
        btnFirst.setText("<<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        lblRecordHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblRecordHD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRecordHD.setText("Trang");

        lblSoHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSoHoaDon.setText("Số Trang");

        btnBack.setBackground(new java.awt.Color(153, 204, 255));
        btnBack.setText("<");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(153, 204, 255));
        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btn_Next.setBackground(new java.awt.Color(153, 204, 255));
        btn_Next.setText(">");
        btn_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NextActionPerformed(evt);
            }
        });

        btn_Last.setBackground(new java.awt.Color(153, 204, 255));
        btn_Last.setText(">>");
        btn_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LastActionPerformed(evt);
            }
        });

        btn_First.setBackground(new java.awt.Color(153, 204, 255));
        btn_First.setText("<<");
        btn_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FirstActionPerformed(evt);
            }
        });

        lbl_RecordHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_RecordHD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_RecordHD.setText("Trang");

        lbl_SoHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_SoHoaDon.setText("Số Trang");

        btn_Back.setBackground(new java.awt.Color(153, 204, 255));
        btn_Back.setText("<");
        btn_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BackActionPerformed(evt);
            }
        });

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lịch Sử Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblLichSuHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã NV", "Ngày Giờ", "Hành Động"
            }
        ));
        tblLichSuHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichSuHoaDonMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblLichSuHoaDon);
        if (tblLichSuHoaDon.getColumnModel().getColumnCount() > 0) {
            tblLichSuHoaDon.getColumnModel().getColumn(0).setMinWidth(50);
            tblLichSuHoaDon.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        btnLoc.setText("LỌC");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnXuatHoaDon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTaoHoaDon)
                                .addGap(106, 106, 106))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cboTT, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateNgayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateNgayThanhToan2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLoc)
                                .addGap(12, 12, 12)
                                .addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblSoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblRecordHD, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lbl_SoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(btn_First, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_RecordHD, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(112, 112, 112))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                .addComponent(btnXuatHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateNgayThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateNgayThanhToan2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 5, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cboTT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLast)
                    .addComponent(btnNext)
                    .addComponent(lblRecordHD)
                    .addComponent(btnBack)
                    .addComponent(btnFirst)
                    .addComponent(lblSoHoaDon))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Last)
                    .addComponent(btn_Next)
                    .addComponent(lbl_RecordHD)
                    .addComponent(btn_Back)
                    .addComponent(btn_First)
                    .addComponent(lbl_SoHoaDon))
                .addGap(18, 18, 18))
        );

        jTabbedPane1.addTab("Hóa Đơn", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Sản Phẩm Trả Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        tblHoaDonTraHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã CTSP", "Tên SP", "Giá", "Số Lượng", "Nhãn Hiệu", "Màu Sắc", "Size", "Tiền Hoàn"
            }
        ));
        tblHoaDonTraHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonTraHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDonTraHang);
        if (tblHoaDonTraHang.getColumnModel().getColumnCount() > 0) {
            tblHoaDonTraHang.getColumnModel().getColumn(0).setMinWidth(50);
            tblHoaDonTraHang.getColumnModel().getColumn(0).setMaxWidth(50);
            tblHoaDonTraHang.getColumnModel().getColumn(7).setMinWidth(40);
            tblHoaDonTraHang.getColumnModel().getColumn(7).setMaxWidth(40);
        }

        jCheckBox1.setText("All");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox1)
                .addGap(14, 14, 14))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn Chi Tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        tblHoaDonChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã CTSP", "Tên SP", "SL", "Giá", "Nhãn Hiệu", "Màu Sắc", "Size", "Tổng Tiền "
            }
        ));
        tblHoaDonChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChiTietMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblHoaDonChiTiet);
        if (tblHoaDonChiTiet.getColumnModel().getColumnCount() > 0) {
            tblHoaDonChiTiet.getColumnModel().getColumn(0).setMinWidth(40);
            tblHoaDonChiTiet.getColumnModel().getColumn(0).setMaxWidth(40);
            tblHoaDonChiTiet.getColumnModel().getColumn(1).setMinWidth(80);
            tblHoaDonChiTiet.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quét QR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        pnQuetQR.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnQuetQR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(pnQuetQR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel24.setText("Tìm Kiếm Theo Mã ");

        txt_TimKiem.setText("Mã");
        txt_TimKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_TimKiemFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_TimKiemFocusLost(evt);
            }
        });
        txt_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TimKiemActionPerformed(evt);
            }
        });
        txt_TimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_TimKiemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_TimKiemKeyReleased(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Trả Hàng"));

        jLabel2.setText("Mã Hóa Đơn");

        jLabel4.setText("Tên KH");

        txtMaHDTH.setEditable(false);
        txtMaHDTH.setBackground(new java.awt.Color(255, 255, 255));
        txtMaHDTH.setBorder(null);

        jLabel5.setText("Địa Chỉ ");

        txtTenKH.setEditable(false);
        txtTenKH.setBackground(new java.awt.Color(255, 255, 255));
        txtTenKH.setBorder(null);

        jLabel9.setText("SDT");

        txtDiaChi.setEditable(false);
        txtDiaChi.setBackground(new java.awt.Color(255, 255, 255));
        txtDiaChi.setBorder(null);

        jLabel13.setText("Tổng Tiền");

        txtSDT.setEditable(false);
        txtSDT.setBackground(new java.awt.Color(255, 255, 255));
        txtSDT.setBorder(null);

        txtTongTien.setBorder(null);

        jSeparator2.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtMaHDTH, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator2))))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator4)
                                .addComponent(txtSDT)
                                .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                                .addComponent(jSeparator5))))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtMaHDTH, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnXuatDanhSach.setText("Xuất Hóa Đơn");
        btnXuatDanhSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatDanhSachActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thanh Toán", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel6.setText("Tên KH");

        jTextField1.setEditable(false);
        jTextField1.setBorder(null);

        jLabel7.setText("Tổng Tiền Gốc Của Hóa Đơn");

        jLabel8.setText("Tổng Tiền Của Sản Phẩm Trả Hàng");

        jLabel11.setText("Tiền Thừa");

        jTextField2.setEditable(false);
        jTextField2.setBorder(null);

        jTextField3.setEditable(false);
        jTextField3.setBorder(null);

        jTextField4.setBorder(null);

        jLabel12.setText("Ghi Chú");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        btnTraHang.setText("Trả Hàng");
        btnTraHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator7)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator6)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jSeparator10, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(btnTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jButton2.setText("Trả Tất Cả");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnQuetQR.setText("Quét Mã QR");
        btnQuetQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuetQRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(btnQuetQR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(btnXuatDanhSach)
                .addGap(43, 43, 43)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnQuetQR, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnXuatDanhSach)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 23, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Trả Hàng", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(this, "Bạn có đồng ý in hóa đơn không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int fileChooserResult = fileChooser.showSaveDialog(this);

            if (fileChooserResult == JFileChooser.APPROVE_OPTION) {
                File selectedDirectory = fileChooser.getSelectedFile();
                String outputPath = selectedDirectory.getAbsolutePath() + "/july.pdf";
                Document document = new Document();
                try {
                    PdfWriter.getInstance(document, new FileOutputStream(outputPath));
                    document.open();
                    Font titleFont = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
                    Paragraph title = new Paragraph("The Habits Shop", titleFont);
                    title.setAlignment(Element.ALIGN_CENTER);
                    title.setSpacingAfter(20);
                    document.add(title);
                    DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
                    int selectedRow = tblHoaDon.getSelectedRow();
                    if (selectedRow != -1) {
                        String[] columnNames = {"STT", "Mã Hóa Đơn", "Mã Nhân Viên", "Tên Khách Hàng", "SDT",
                            "Ngày Tạo", "Ngày Thanh Toán", "Tổng Tiền", "Hình Thức", "Trạng Thái", "Ghi Chú"};
                        for (int i = 0; i < columnNames.length; i++) {
                            String columnName = columnNames[i];
                            String cellValue = model.getValueAt(selectedRow, i).toString();

                            Font infoFont = new Font(Font.FontFamily.HELVETICA, 15, Font.NORMAL);
                            Paragraph invoiceInfo = new Paragraph();
                            invoiceInfo.add(new Phrase(columnName + " :   ", infoFont));
                            invoiceInfo.add(new Phrase(cellValue, infoFont));
                            invoiceInfo.setSpacingAfter(11);
                            document.add(invoiceInfo);
                        }
                    }
                    JOptionPane.showMessageDialog(this, "In hóa đơn thành công");
                } catch (FileNotFoundException | DocumentException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi khi in hóa đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } finally {
                    document.close();
                }
            } else {
                // Người dùng không chọn thư mục
                JOptionPane.showMessageDialog(this, "Đã hủy in", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            // Người dùng không đồng ý in
            JOptionPane.showMessageDialog(this, "Đã hủy in", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnInHoaDonActionPerformed

    private void tblhoaDonCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhoaDonCTMouseClicked

    }//GEN-LAST:event_tblhoaDonCTMouseClicked

    private void btnXuatHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatHoaDonActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Bạn có đồng xuất danh sách?", "Xác nhận",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                ArrayList<HoaDon> listHD = reponHD.getAll();
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Hóa Đơn");
                XSSFSheet sheet2 = workbook.createSheet("Hóa Đơn Chi Tiết");
                XSSFSheet sheet3 = workbook.createSheet("Lịch Sử Hóa Đơn");
                XSSFRow row = null;
                XSSFRow row2 = null;
                XSSFRow row3 = null;
                Cell cell = null;
                Cell cell2 = null;
                Cell cell3 = null;

                // Xuất phần hóa đơn
                row = sheet.createRow(0);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("STT");

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("MaHD");

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("MaNV");

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("TenKH");

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("SDT");

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue("NgayTaoHoaDon");

                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue("NgayThanhToan");

                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue("TongTien");

                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue("HinhThuc");

                cell = row.createCell(9, CellType.STRING);
                cell.setCellValue("TrangThai");

                cell = row.createCell(10, CellType.STRING);
                cell.setCellValue("GhiChu");

                // Xuất phần hóa đơn chi tiết
                row2 = sheet2.createRow(0);
                cell2 = row2.createCell(0, CellType.STRING);
                cell2.setCellValue("STT");

                cell2 = row2.createCell(1, CellType.STRING);
                cell2.setCellValue("MaCTSP");

                cell2 = row2.createCell(2, CellType.STRING);
                cell2.setCellValue("Tên SP");

                cell2 = row2.createCell(3, CellType.STRING);
                cell2.setCellValue("Số Lượng");

                cell2 = row2.createCell(4, CellType.STRING);
                cell2.setCellValue("Màu Sắc");

                cell2 = row2.createCell(5, CellType.STRING);
                cell2.setCellValue("Nhãn Hiệu");

                cell2 = row2.createCell(6, CellType.STRING);
                cell2.setCellValue("Chất Liệu");

                cell2 = row2.createCell(7, CellType.STRING);
                cell2.setCellValue("Kích Cỡ");

                cell2 = row2.createCell(8, CellType.STRING);
                cell2.setCellValue("Giá");

                cell2 = row2.createCell(9, CellType.STRING);
                cell2.setCellValue("Tổng Tiền Của Hóa Đơn");

                // Xuất phần lịch sử 
                row3 = sheet3.createRow(0);
                cell3 = row3.createCell(0, CellType.STRING);
                cell3.setCellValue("STT");

                cell3 = row3.createCell(1, CellType.STRING);
                cell3.setCellValue("Mã NV");

                cell3 = row3.createCell(2, CellType.STRING);
                cell3.setCellValue("Ngày Giờ");

                cell3 = row3.createCell(3, CellType.STRING);
                cell3.setCellValue("Hành Động");

                for (int i = 0; i < listHD.size(); i++) {
                    HoaDon hoaDon = listHD.get(i);
                    row = sheet.createRow(i + 1);

                    cell = row.createCell(0, CellType.NUMERIC);
                    cell.setCellValue(i + 1);

                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue(hoaDon.getMa());

                    cell = row.createCell(2, CellType.STRING);
                    cell.setCellValue(hoaDon.getIdNhanVien().getMaNV());

                    cell = row.createCell(3, CellType.STRING);
                    cell.setCellValue(hoaDon.getIdKhachHang().getTenKH());

                    cell = row.createCell(4, CellType.STRING);
                    cell.setCellValue(hoaDon.getIdKhachHang().getSdt());

                    cell = row.createCell(5, CellType.STRING);
                    cell.setCellValue(date2String(hoaDon.getNgaytaohoadon()));

                    cell = row.createCell(6, CellType.STRING);
                    cell.setCellValue(date2String(hoaDon.getNgaythanhtoan()));

                    cell = row.createCell(7, CellType.NUMERIC);
                    cell.setCellValue(NumberFormat.getInstance().format(hoaDon.getTongTienHoaDon().doubleValue()));

                    cell = row.createCell(8, CellType.STRING);
                    cell.setCellValue(hoaDon.getHttt().getTen());

                    cell = row.createCell(9, CellType.STRING);
                    cell.setCellValue(hoaDon.getTinhTrang());

                    cell = row.createCell(10, CellType.STRING);
                    cell.setCellValue(hoaDon.getGhichu());
                }

                List<HoaDonChiTiet> listHDCT = reponHDCT.getInHoaDonCT();
                for (int i = 0; i < listHDCT.size(); i++) {
                    HoaDonChiTiet hoaDonCT = listHDCT.get(i);
                    row = sheet2.createRow(i + 1);

                    cell2 = row2.createCell(0, CellType.NUMERIC);
                    cell2.setCellValue(i + 1);

                    cell2 = row2.createCell(1, CellType.STRING);
                    cell2.setCellValue(hoaDonCT.getIdSPCT().getMa());

                    cell2 = row2.createCell(2, CellType.STRING);
                    cell2.setCellValue(hoaDonCT.getIdSPCT().getTenSanPham().getTenSP());

                    cell2 = row2.createCell(3, CellType.NUMERIC);
                    cell2.setCellValue(hoaDonCT.getSoLuong());

                    cell2 = row2.createCell(4, CellType.NUMERIC);
                    cell2.setCellValue(hoaDonCT.getIdSPCT().getTenMauSac().getTenMauSac());

                    cell2 = row2.createCell(5, CellType.NUMERIC);
                    cell2.setCellValue(hoaDonCT.getIdSPCT().getTenNhanHieu().getTen());

                    cell2 = row2.createCell(6, CellType.NUMERIC);
                    cell2.setCellValue(hoaDonCT.getIdSPCT().getTenChatlieu().getTen());

                    cell2 = row2.createCell(7, CellType.NUMERIC);
                    cell2.setCellValue(hoaDonCT.getIdSPCT().getTenKichCo().getTenKichCo());

                    cell2 = row2.createCell(8, CellType.NUMERIC);
                    cell2.setCellValue(NumberFormat.getInstance().format(hoaDonCT.getIdSPCT().getGiaBan1().doubleValue()));

                    cell2 = row2.createCell(9, CellType.NUMERIC);
                    cell2.setCellValue(hoaDonCT.getHoaDon().getTongTienHoaDon().doubleValue());

                }

                List<LichSuHoaDon> listLSHD = reponLSHD.getListHD();
                for (int i = 0; i < listLSHD.size(); i++) {
                    LichSuHoaDon lshd = listLSHD.get(i);
                    row = sheet3.createRow(i + 1);

                    cell = row.createCell(0, CellType.NUMERIC);
                    cell.setCellValue(i + 1);

                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue(lshd.getNhanVien().getMaNV());

                    cell = row.createCell(2, CellType.STRING);
                    cell.setCellValue(lshd.getHoaDon().getNgaytaohoadon());

                    cell = row.createCell(3, CellType.STRING);
                    cell.setCellValue(lshd.getHanhdong());
                }
                FileOutputStream fileOut = new FileOutputStream("D:\\Excel\\HoaDon.xlsx");
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();
                JOptionPane.showMessageDialog(this, "Xuất danh sách thành công");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            // Xử lý khi người dùng không đồng ý thêm
            JOptionPane.showMessageDialog(this, "Đã hủy xuất danh sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnXuatHoaDonActionPerformed

    void PhanTrangHoaDonChiTiet(List<HoaDonChiTiet> listHDCT) {
        modelHDCT = (DefaultTableModel) tblhoaDonCT.getModel();
        modelHDCT.setRowCount(0);
        count = listHDCT.size();
        if (count == 0) {
            tranghientai = 0;
            tongsoTrang = 1;
            lbl_SoHoaDon.setText("Số hóa đơn: " + count);
            lbl_RecordHD.setText(tranghientai + 1 + "/" + tongsoTrang);
            return;
        }

        int rowsPerPage = 1;

        tongsoTrang = (int) Math.ceil((double) count / rowsPerPage);

        // Ensure tranghientai is within valid bounds
        tranghientai = Math.max(0, Math.min(tranghientai, tongsoTrang - 1));

        int n = rowsPerPage * tranghientai;
        int m = Math.min(n + rowsPerPage, count);

        for (int i = n; i < m; i++) {
            HoaDonChiTiet hdct = listHDCT.get(i);
            modelHDCT.addRow(new Object[]{
                i + 1,
                hdct.getIdSPCT().getMa(),
                hdct.getIdSPCT().getTenSanPham().getTenSP(),
                hdct.getSoLuong(),
                NumberFormat.getInstance().format(hdct.getIdSPCT().getGiaBan1()),
                NumberFormat.getInstance().format(hdct.getHoaDon().getTongTienHoaDon())
            });
        }

        lbl_RecordHD.setText(tranghientai + 1 + "/" + tongsoTrang);
        lbl_SoHoaDon.setText("Số hóa đơn: " + count);
    }

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        listHD = reponHD.getAll();
        int rowIndex = tblHoaDon.getSelectedRow();
        if (rowIndex >= 0 && rowIndex < listHD.size()) {
            HoaDon hd = listHD.get(rowIndex);
            listHDCT = reponHDCT.getHDCT();
            modelHDCT.setRowCount(0);
            int stt = 1; // Initialize the row number
            for (HoaDonChiTiet hdct : listHDCT) {
                if (hdct.getHoaDon().getMa().equals(hd.getMa())) {
                    Object[] row = new Object[]{
                        stt++,
                        hdct.getIdSPCT().getMa(),
                        hdct.getIdSPCT().getTenSanPham().getTenSP(),
                        hdct.getSoLuong(),
                        NumberFormat.getInstance().format(hdct.getIdSPCT().getGiaBan1()),
                        NumberFormat.getInstance().format(hdct.getHoaDon().getTongTienHoaDon())
                    };
                    modelHDCT.addRow(row);
                }
            }
            loadTablelichSuHoaDon(reponLSHD.getListHD());
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    void loadTablelichSuHoaDon(List<LichSuHoaDon> listLSHD) {
        listHD = reponHD.getAll();
        int rowIndex = tblHoaDon.getSelectedRow();
        if (rowIndex >= 0 && rowIndex < listHD.size()) {
            HoaDon hd = listHD.get(rowIndex);
            listLSHD = reponLSHD.getListHD();
            modelLSHD = (DefaultTableModel) tblLichSuHoaDon.getModel();
            modelLSHD.setRowCount(0);
            int st = 1;
            for (LichSuHoaDon lshd : listLSHD) {
                if (lshd.getHoaDon().getMa().equals(hd.getMa())) {
                    Object[] trow = new Object[]{
                        st++,
                        lshd.getNhanVien().getMaNV(),
                        date2String(lshd.getHoaDon().getNgaytaohoadon()),
                        lshd.getHanhdong()
                    };
                    modelLSHD.addRow(trow);
                }
            }
        }
    }

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:'
        String tk = txtTimKiem.getText();
        search(tk);
    }//GEN-LAST:event_txtTimKiemKeyReleased
    void search(String str) {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tblHoaDon.setRowSorter(trs);
        String searchText = txtTimKiem.getText().trim();
        trs.setRowFilter(RowFilter.regexFilter(str));
    }
    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusLost
        // TODO add your handling code here:
        if (txtTimKiem.getText().length() == 0) {
            AddPleacehoderStyle(txtTimKiem);
            txtTimKiem.setText("Tìm theo các trường của table");
        }
    }//GEN-LAST:event_txtTimKiemFocusLost

    private void txtTimKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusGained
        // TODO add your handling code here:
        if (txtTimKiem.getText().equals("Tìm theo các trường của table")) {
            txtTimKiem.setText(null);
            txtTimKiem.requestFocus();
            RemovePleacehoderStyle(txtTimKiem);
        }
    }//GEN-LAST:event_txtTimKiemFocusGained

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void txt_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TimKiemActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_TimKiemActionPerformed

    void tk(String str) {
        DefaultTableModel model = (DefaultTableModel) tblHoaDonTraHang.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tblHoaDonTraHang.setRowSorter(trs);
        String searchText = txt_TimKiem.getText().trim();
        trs.setRowFilter(new RowFilter<DefaultTableModel, Object>() {
            @Override
            public boolean include(RowFilter.Entry<? extends DefaultTableModel, ? extends Object> entry) {
                for (int i = 0; i < entry.getValueCount(); i++) {
                    if (entry.getStringValue(i).toLowerCase().contains(searchText.toLowerCase())) {
                        return false;
                    }
                }
                return true;
            }
        });
    }

    private void txt_TimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TimKiemKeyReleased
        // TODO add your handling code here
    }//GEN-LAST:event_txt_TimKiemKeyReleased

    void tkiem(String str) {
        DefaultTableModel model = (DefaultTableModel) tblHoaDonTraHang.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tblHoaDonTraHang.setRowSorter(trs);
        String searchText = txt_TimKiem.getText().trim();
        trs.setRowFilter(RowFilter.regexFilter(str));
    }

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        // TODO add your handling code here:
        if (reponBH.check5truong() <= 5) {

            int option = JOptionPane.showConfirmDialog(this, "Bạn có đồng ý thêm?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                HoaDon hd = readFormHoaDon();
                reponHD.addHoaDonCho(hd);
                this.showForm();
            } else {
                // Xử lý khi người dùng không đồng ý thêm
                JOptionPane.showMessageDialog(this, "Đã hủy thêm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Đã vượt quá số lượng hóa đơn chờ!!");
        }
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    public void showForm() {
        removeAll();
        setLayout(new BorderLayout());
        add(new BanHangForm());
        repaint();
        revalidate();
    }

    private void txt_TimKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_TimKiemFocusGained
        // TODO add your handling code here:
        if (txt_TimKiem.getText().equals("Mã")) {
            txt_TimKiem.setText(null);
            txt_TimKiem.requestFocus();
            RemovePleacehoderStyle(txt_TimKiem);
        }
    }//GEN-LAST:event_txt_TimKiemFocusGained

    private void txt_TimKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_TimKiemFocusLost
        // TODO add your handling code here:
        if (txt_TimKiem.getText().length() == 0) {
            AddPleacehoderStyle(txt_TimKiem);
            txt_TimKiem.setText("Mã");
        }
    }//GEN-LAST:event_txt_TimKiemFocusLost

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        tranghientai = tongsoTrang - 1;
        loadTableHoaDon9(listHD);
        lblRecordHD.setText(tranghientai + 1 + "/" + tongsoTrang);
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        tranghientai = 0;
        loadTableHoaDon9(listHD);
        lblRecordHD.setText(tranghientai + 1 + "/" + tongsoTrang);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        tranghientai--;
        if (tranghientai < 0) {
            tranghientai = 0;
        }
        loadTableHoaDon9(listHD);
        lblRecordHD.setText(tranghientai + 1 + "/" + tongsoTrang);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        tranghientai++;
        System.out.println(tranghientai);
        if (tranghientai > tongsoTrang - 1) {
            tranghientai = tongsoTrang - 1;
        }
        loadTableHoaDon9(listHD);
        lblRecordHD.setText(tranghientai + 1 + "/" + tongsoTrang);
    }//GEN-LAST:event_btnNextActionPerformed

    private void tblLichSuHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichSuHoaDonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblLichSuHoaDonMouseClicked

    private void btn_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NextActionPerformed
        // TODO add your handling code here:
        tranghientai++;
        System.out.println(tranghientai);
        if (tranghientai > tongsoTrang - 1) {
            tranghientai = tongsoTrang - 1;
        }
        PhanTrangHoaDonChiTiet(listHDCT);
        lbl_RecordHD.setText(tranghientai + 1 + "/" + tongsoTrang);
    }//GEN-LAST:event_btn_NextActionPerformed

    private void btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LastActionPerformed
        // TODO add your handling code here:
        tranghientai = tongsoTrang - 1;
        PhanTrangHoaDonChiTiet(listHDCT);
        lbl_RecordHD.setText(tranghientai + 1 + "/" + tongsoTrang);
    }//GEN-LAST:event_btn_LastActionPerformed

    private void btn_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FirstActionPerformed
        // TODO add your handling code here:
        tranghientai = 0;
        PhanTrangHoaDonChiTiet(listHDCT);
        lbl_RecordHD.setText(tranghientai + 1 + "/" + tongsoTrang);
    }//GEN-LAST:event_btn_FirstActionPerformed

    private void btn_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BackActionPerformed
        // TODO add your handling code here:
        tranghientai--;
        if (tranghientai < 0) {
            tranghientai = 0;
        }
        PhanTrangHoaDonChiTiet(listHDCT);
        lbl_RecordHD.setText(tranghientai + 1 + "/" + tongsoTrang);
    }//GEN-LAST:event_btn_BackActionPerformed

    void loadTableTraHang(List<HoaDonTraHangCT> listHDTHCT) {
        model = (DefaultTableModel) tblHoaDonTraHang.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (HoaDonTraHangCT hdth : listHDTHCT) {
            Object[] row = new Object[]{
                stt++,
                hdth.getHdth().getMa(),
                hdth.getCtsp().getTenSanPham().getTenSP(),
                hdth.getCtsp().getTenNhanHieu().getTen(),
                hdth.getCtsp().getTenMauSac().getTenMauSac(),
                hdth.getCtsp().getTenKichCo().getTenKichCo(),
                hdth.getSoluong(),
                hdth.getGia_spkhimua(),
                NumberFormat.getInstance().format(hdth.getHdth().getTienHoanTra())
            };
            model.addRow(row);
        }
    }

    private void btnTraHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTraHangActionPerformed

    HoaDonTraHangCT readForm() {
//        int row = tblHoaDonTraHang.getSelectedRow();
//        String ma = tblHoaDonTraHang.getValueAt(row, 1).toString();
//        String ten = tblHoaDonTraHang.getValueAt(row, 2).toString();
//        String ma = tblHoaDonTraHang.getValueAt(row, 3).toString();
//        String ma = tblHoaDonTraHang.getValueAt(row, 4).toString();
//        String ma = tblHoaDonTraHang.getValueAt(row, 5).toString();
//        String soLuong = tblHoaDonTraHang.getValueAt(row, 6).toString();
        return new HoaDonTraHangCT();
    }

    private void tblHoaDonTraHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonTraHangMouseClicked
        // TODO add your handling code here:
        HoaDonTraHangCT reponTH = this.readForm();
        DefaultTableModel modelHDTH = (DefaultTableModel) tblHoaDonTraHang.getModel();

        int row = tblHoaDonTraHang.getSelectedRow();
        String ma = modelHDTH.getValueAt(row, 1).toString();
        String soluong = modelHDTH.getValueAt(row, 6).toString();

        String choice = JOptionPane.showInputDialog(this, "Mời bạn nhập số lượng trả hàng", "Trả hàng", JOptionPane.YES_NO_OPTION);
        if (choice != null && choice.equalsIgnoreCase(JOptionPane.YES_OPTION + "")) {
            String newSoluong = JOptionPane.showInputDialog(this, "Nhập số lượng mới:", soluong);
            if (newSoluong != null && !newSoluong.isEmpty()) {
                modelHDTH.setValueAt(newSoluong, row, 6);
                // Update the quantity in the database
//                if (reponHDTHCT.updateSoLuongTraHang(ma, Integer.parseInt(newSoluong)) > 0) {
//                    JOptionPane.showMessageDialog(this, "Sửa thành công");
//                    // Reload the table DanhSachTraHang
//                    loadTableDanhSachTraHang(reponHDTHCT.getDSTH());
//                } else {
//                    JOptionPane.showMessageDialog(this, "Sửa thất bại");
//                }
            } else {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Hủy trả hàng");
        }
    }//GEN-LAST:event_tblHoaDonTraHangMouseClicked

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        if (dateNgayThanhToan.getDateFormatString().toString().trim().isEmpty() || dateNgayThanhToan2.getDateFormatString().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ ngày và giờ");
            return;
        } else {
            List<HoaDon> list = reponHD.findDate(dateNgayThanhToan, dateNgayThanhToan2);
            DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
            model.setRowCount(0);
            int stt = 1;
            for (HoaDon hoaDon : list) {
                String tinhTrang = "";
                int tinhTrangValue = hoaDon.getTinhTrang();
                if (tinhTrangValue == 0) {
                    tinhTrang = "Đã Giao Hàng";
                } else if (tinhTrangValue == 1) {
                    tinhTrang = "Đã Hủy";
                } else if (tinhTrangValue == 2) {
                    tinhTrang = "Chưa Thanh Toán";
                } else {
                    tinhTrang = "Đã Thanh Toán";
                }
                Object[] row = new Object[]{
                    stt++,
                    hoaDon.getMa(),
                    hoaDon.getIdNhanVien().getMaNV(),
                    hoaDon.getIdKhachHang().getTenKH(),
                    hoaDon.getIdKhachHang().getSdt(),
                    hoaDon.getNgaytaohoadon(),
                    hoaDon.getNgaythanhtoan(),
                    hoaDon.getTongTienHoaDon(),
                    hoaDon.getHttt().getTen(),
                    hoaDon.getGhichu(),
                    tinhTrang,};
                model.addRow(row);
            }
        }        // TODO add your handling code here:

    }//GEN-LAST:event_btnLocActionPerformed

    private HoaDonTraHangCT getFormData() {
        int row = tblHoaDonChiTiet.getSelectedRow();
        String ma = tblHoaDonChiTiet.getValueAt(row, 1).toString();
        int soluong = Integer.parseInt(tblHoaDonChiTiet.getValueAt(row, 3).toString());
        //String idspct = getidspct.getIdSanPhamChiTiet(ma);
        BigDecimal gia = BigDecimal.valueOf(Double.parseDouble(tblHoaDonChiTiet.getValueAt(row, 4).toString()));
        return new HoaDonTraHangCT(ma, soluong, gia);
    }

    private void tblHoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChiTietMouseClicked
        // TODO add your handling code here:
        int choice = Integer.parseInt(JOptionPane.showInputDialog(this, "Nhập số lượng muốn trả hàng",
                "Thông Báo", JOptionPane.YES_NO_OPTION));
        int chiTietRow = tblHoaDonChiTiet.getSelectedRow();
        // Kiểm tra xem có hàng nào được chọn không
        if (chiTietRow != -1) {
            int traHangRow = tblHoaDonTraHang.getRowCount(); // Assuming you have a DefaultTableModel for tblHoaDonTraHang
            int soluongHoaDon = (int) tblHoaDonChiTiet.getValueAt(chiTietRow, 3);
            BigDecimal gia = (BigDecimal) tblHoaDonChiTiet.getValueAt(chiTietRow, 4);
            String maSanPham = (String) tblHoaDonChiTiet.getValueAt(chiTietRow, 1);

            if (choice > 0 && choice <= soluongHoaDon) {
                int soluongTraHang = soluongHoaDon - choice;
                // Update tblHoaDonChiTiet
                tblHoaDonChiTiet.setValueAt(soluongTraHang, chiTietRow, 3);
                // Create an object to pass to addSanPhamTraHang
                HoaDonTraHangCT hdthct = new HoaDonTraHangCT();

                Object[] traHangData = {
                    maSanPham,
                    choice
                };
                DefaultTableModel modelDanhSachTraHang = (DefaultTableModel) tblHoaDonTraHang.getModel();
                modelDanhSachTraHang.addRow(traHangData);
                try {
                    // Perform database updates
                    if (reponHDCT.updateSoLuongTraHang(maSanPham, soluongTraHang) > 0
                            & reponHDTHCT.addSanPhamTraHang(getFormData()) > 0) {
                        JOptionPane.showMessageDialog(this, "Sửa thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa thất bại");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chọn một hàng để xử lý");
        }

    }//GEN-LAST:event_tblHoaDonChiTietMouseClicked

//    void readForm() {
//        int row = tblHoaDonChiTiet.getSelectedColumn();
//        String ma = tblHoaDonChiTiet.getValueAt(row, 1).toString();
//        String tensp = tblHoaDonChiTiet.getValueAt(row, 2).toString();
//        String tennh = tblHoaDonChiTiet.getValueAt(row, 3).toString();
//        String tenms = tblHoaDonChiTiet.getValueAt(row, 4).toString();
//        String tenkc = tblHoaDonChiTiet.getValueAt(row, 5).toString();
//        String soluong = tblHoaDonChiTiet.getValueAt(row, 6).toString();
//        String gia = tblHoaDonChiTiet.getValueAt(row, 7).toString();
//        String tongtien = tblHoaDonChiTiet.getValueAt(row, 8).toString();
//
//    }

    private void btnQuetQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuetQRActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnQuetQRActionPerformed

    // fill table trả hàng
    void fillTable(List<HoaDonChiTiet> listHDCT) {
        model = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        int stt = tblHoaDonChiTiet.getSelectedRow();
        model.setRowCount(0);
        stt += 1;
        for (HoaDonChiTiet hdct : listHDCT) {
            Object[] row = new Object[]{
                stt += 1,
                hdct.getIdSPCT().getMa(),
                hdct.getIdSPCT().getTenSanPham(),
                hdct.getSoLuong(),
                hdct.getGia_SPCT(),
                hdct.getIdSPCT().getTenNhanHieu().getTen(),
                hdct.getIdSPCT().getTenMauSac().getTenMauSac(),
                hdct.getIdSPCT().getTenKichCo().getTenKichCo(),
                hdct.getHoaDon().getTongTienHoaDon(),};
            model.addRow(row);
        }
    }

    private void txt_TimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TimKiemKeyPressed
        // TODO add your handling code here:
        DefaultTableModel modelHDTraHang = new DefaultTableModel();
        modelHDTraHang = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        modelHDTraHang.setRowCount(0);
        listHDCT = reponHDCT.getAll();
        String searchText = txt_TimKiem.getText().trim().toLowerCase(); // Lấy và chuẩn hóa dữ liệu nhập vào
        int stt = 1; // Khởi tạo số thứ tự từ 1
        for (HoaDonChiTiet hdct : listHDCT) {
            // So sánh ID sản phẩm với từ khóa tìm kiếm
            if (hdct.getIdSPCT().getMa().toLowerCase().contains(searchText)) {
                Object[] row = new Object[]{
                    stt++,
                    hdct.getIdSPCT().getMa(),
                    hdct.getIdSPCT().getTenSanPham().getTenSP(),
                    hdct.getSoLuong(),
                    NumberFormat.getInstance().format(hdct.getGia_SPCT()),
                    hdct.getIdSPCT().getTenNhanHieu().getTen(),
                    hdct.getIdSPCT().getTenMauSac().getTenMauSac(),
                    hdct.getIdSPCT().getTenKichCo().getTenKichCo(),
                    NumberFormat.getInstance().format(hdct.getHoaDon().getTongTienHoaDon()),};
                modelHDTraHang.addRow(row);
            }
        }
        // Đặt model mới cho bảng HoaDonTraHang để cập nhật dữ liệu
        //tblHoaDonTraHang.setModel(modelHDTraHang);
        this.showDataTraHang(index);
    }//GEN-LAST:event_txt_TimKiemKeyPressed

    void showDataTraHang(int index) {
        HoaDon hd = reponHD.getAll().get(index);
        txtMaHDTH.setText(hd.getMa());
        txtTenKH.setText(hd.getIdKhachHang().getTenKH());
        txtDiaChi.setText(hd.getIdKhachHang().getDiaChi());
        txtSDT.setText(hd.getIdKhachHang().getSdt());
        txtTongTien.setText(hd.getTongTienHoaDon().toString());
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton2ActionPerformed

    private void cboTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTTActionPerformed

    void loadTableCBB(int trangthai) {
        ArrayList<HoaDon> listHD = reponHD.getList(trangthai);
        model.setRowCount(0);
        int stt = 1;
        for (HoaDon hoaDon : listHD) {
            String tinhTrang;
            int tinhTrangValue = hoaDon.getTinhTrang();
            if (tinhTrangValue == 1) {
                tinhTrang = "Đã Giao Hàng";
            } else if (tinhTrangValue == 2) {
                tinhTrang = "Đã Hủy";
            } else if (tinhTrangValue == 3) {
                tinhTrang = "Chưa Thanh Toán";
            } else {
                tinhTrang = "Đã Thanh Toán";
            }
            Object[] row = new Object[]{
                stt++,
                hoaDon.getMa(),
                hoaDon.getIdNhanVien().getMaNV(),
                hoaDon.getIdKhachHang().getTenKH(),
                hoaDon.getIdKhachHang().getSdt(),
                hoaDon.getNgaytaohoadon(),
                hoaDon.getNgaythanhtoan(),
                hoaDon.getTongTienHoaDon(),
                hoaDon.getHttt().getTen(),
                hoaDon.getGhichu(),
                hoaDon.getTinhTrang(),};
            model.addRow(row);
        }
    }

    private void cboTTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTTItemStateChanged
        // TODO add your handling code here:
        ArrayList<HoaDon> listHD = reponHD.getList(Integer.parseInt((String) dcb1.getSelectedItem()));
        model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (HoaDon hoaDon : listHD) {
            String tinhTrang;
            int tinhTrangValue = hoaDon.getTinhTrang();
            if (tinhTrangValue == 1) {
                tinhTrang = "Đã Giao Hàng";
            } else if (tinhTrangValue == 2) {
                tinhTrang = "Đã Hủy";
            } else if (tinhTrangValue == 3) {
                tinhTrang = "Chưa Thanh Toán";
            } else {
                tinhTrang = "Đã Thanh Toán";
            }
            Object[] row = new Object[]{
                stt++,
                hoaDon.getMa(),
                hoaDon.getIdNhanVien().getMaNV(),
                hoaDon.getIdKhachHang().getTenKH(),
                hoaDon.getIdKhachHang().getSdt(),
                hoaDon.getNgaytaohoadon(),
                hoaDon.getNgaythanhtoan(),
                hoaDon.getTongTienHoaDon(),
                hoaDon.getHttt().getTen(),
                hoaDon.getGhichu(),
                tinhTrang,};
            model.addRow(row);
        }
        loadTableCBB(Integer.parseInt((String) dcb1.getSelectedItem()));
    }//GEN-LAST:event_cboTTItemStateChanged

    private void btnXuatDanhSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatDanhSachActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(this, "Bạn có đồng xuất danh sách?", "Xác nhận",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet2 = workbook.createSheet("Hóa Đơn Chi Tiết");
                XSSFRow row2 = null;
                Cell cell2 = null;
                // Xuất phần hóa đơn chi tiết
                row2 = sheet2.createRow(0);
                cell2 = row2.createCell(0, CellType.STRING);
                cell2.setCellValue("STT");

                cell2 = row2.createCell(1, CellType.STRING);
                cell2.setCellValue("Mã CTSP");

                cell2 = row2.createCell(2, CellType.STRING);
                cell2.setCellValue("Tên SP");

                cell2 = row2.createCell(3, CellType.STRING);
                cell2.setCellValue("Số Lượng");

                cell2 = row2.createCell(4, CellType.STRING);
                cell2.setCellValue("Màu Sắc");

                cell2 = row2.createCell(5, CellType.STRING);
                cell2.setCellValue("Nhãn Hiệu");

                cell2 = row2.createCell(6, CellType.STRING);
                cell2.setCellValue("Chất Liệu");

                cell2 = row2.createCell(7, CellType.STRING);
                cell2.setCellValue("Kích Cỡ");

                cell2 = row2.createCell(8, CellType.STRING);
                cell2.setCellValue("Giá");

                cell2 = row2.createCell(9, CellType.STRING);
                cell2.setCellValue("Tổng Tiền Của Hóa Đơn");

                List<HoaDonChiTiet> listHDCT = reponHDCT.getInHoaDonCT();
                for (int i = 0; i < listHDCT.size(); i++) {
                    HoaDonChiTiet hoaDonCT = listHDCT.get(i);
                    row2 = sheet2.createRow(i + 1);

                    cell2 = row2.createCell(0, CellType.NUMERIC);
                    cell2.setCellValue(i + 1);

                    cell2 = row2.createCell(1, CellType.STRING);
                    cell2.setCellValue(hoaDonCT.getIdSPCT().getMa());

                    cell2 = row2.createCell(2, CellType.STRING);
                    cell2.setCellValue(hoaDonCT.getIdSPCT().getTenSanPham().getTenSP());

                    cell2 = row2.createCell(3, CellType.NUMERIC);
                    cell2.setCellValue(hoaDonCT.getSoLuong());

                    cell2 = row2.createCell(4, CellType.NUMERIC);
                    cell2.setCellValue(hoaDonCT.getIdSPCT().getTenMauSac().getTenMauSac());

                    cell2 = row2.createCell(5, CellType.NUMERIC);
                    cell2.setCellValue(hoaDonCT.getIdSPCT().getTenNhanHieu().getTen());

                    cell2 = row2.createCell(6, CellType.NUMERIC);
                    cell2.setCellValue(hoaDonCT.getIdSPCT().getTenChatlieu().getTen());

                    cell2 = row2.createCell(7, CellType.NUMERIC);
                    cell2.setCellValue(hoaDonCT.getIdSPCT().getTenKichCo().getTenKichCo());

                    cell2 = row2.createCell(8, CellType.NUMERIC);
                    cell2.setCellValue(NumberFormat.getInstance().format(hoaDonCT.getIdSPCT().getGiaBan1().doubleValue()));

                    cell2 = row2.createCell(9, CellType.NUMERIC);
                    cell2.setCellValue(hoaDonCT.getHoaDon().getTongTienHoaDon().doubleValue());

                }
                FileOutputStream fileOut = new FileOutputStream("D:\\Excel\\HoaDon.xlsx");
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();
                JOptionPane.showMessageDialog(this, "Xuất danh sách thành công");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            // Xử lý khi người dùng không đồng ý thêm
            JOptionPane.showMessageDialog(this, "Đã hủy xuất danh sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnXuatDanhSachActionPerformed

    void showData(int index) {
        //        HoaDonTraHang hd = reponHDTH.mouseClickTraHang().get(index);
        //        txtMaHDNgoai.setText(hd.getMa());
        //        txtTenKHNgoai.setText(hd.getKhachHang().getTen());
        //        txtDiaChiNgoai.setText(hd.getKhachHang().getDiachi());
        //        txtThanhTienNgoai.setText(String.valueOf(hd.getTienHoanTra()));
    }

    public void ChuyenTrang(long trang) {
        loadTableHoaDon(reponHD.getAll());
        model.getDataVector().removeAllElements();
        sql = "select top 5 * HoaDon.ma AS 'MaHD', NhanVien.ma, KhachHang.ten, KhachHang.sdt,HoaDon.ngaytaohoadon, HoaDon.ngaythanhtoan,\n"
                + "HoaDon.tongtiencuahoadon,HinhThucThanhToan.hinhthuc, HoaDon.tinhtrang, HoaDon.ghichu,\n"
                + "NhanVien.id AS 'idNV', NhanVien.ten, KhachHang.id AS idKH, KhachHang.ten AS tenKH FROM HoaDon WHERE MaHD NOT IN (SELECT TOP " + (trang * 5 - 5) + " MaHD FROM HoaDon\n"
                + "JOIN NhanVien ON HoaDon.id_nhanvien = NhanVien.id\n"
                + "JOIN KhachHang ON HoaDon.id_khachhang = KhachHang.id\n"
                + "JOIN HinhThucThanhToan ON HinhThucThanhToan.id_hoadon = HoaDon.id";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            List<HoaDon> hoaDonList = new ArrayList<>(); // Danh sách HoaDon

            while (rs.next()) {
                // Các dòng mã tạo đối tượng HoaDon từ ResultSet
                String mahd = rs.getString("MaHD");
                Date ngaytaohd = rs.getDate("ngaytaohoadon");
                Date ngaythanhtoan = rs.getDate("ngaythanhtoan");
                BigDecimal tongtien = rs.getBigDecimal("tongtiencuahoadon");
                String HTThanhToan = rs.getString("hinhthuc");
                Integer tinhtrang = rs.getInt("tinhtrang");
                String ghichu = rs.getString("ghichu");
                UUID idnv = UUID.fromString(rs.getString("idNV"));
                String manv = rs.getString("ma");
                UUID idkh = UUID.fromString(rs.getString("idKH"));
                String tenkh = rs.getString("ten");
                String sdt = rs.getString("sdt");

                NhanVien nv = new NhanVien();
                nv.setId(idnv);
                nv.setMaNV(manv);
                KhachHang kh = new KhachHang();
                kh.setID(idkh);
                kh.setTenKH(tenkh);
                kh.setSdt(sdt);
                HinhThucThanhToan httt = new HinhThucThanhToan();
                httt.setTen(HTThanhToan);

                HoaDon hoaDon = new HoaDon();
                hoaDon.setMa(mahd);
                hoaDon.setNgaytaohoadon(ngaytaohd);
                hoaDon.setNgaythanhtoan(ngaythanhtoan);
                hoaDon.setTongTienHoaDon(tongtien);
                hoaDon.setHttt(httt);
                hoaDon.setTinhTrang(tinhtrang);
                hoaDon.setGhichu(ghichu);
                hoaDon.setIdNhanVien(nv);
                hoaDon.setIdKhachHang(kh);
                hoaDonList.add(hoaDon);
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void HoaDonJDialog() {
        Main m = new Main();
        new HoaDonTraHangJDiaLog(m, true).setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnQuetQR;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnTraHang;
    private javax.swing.JButton btnXuatDanhSach;
    private javax.swing.JButton btnXuatHoaDon;
    private javax.swing.JButton btn_Back;
    private javax.swing.JButton btn_First;
    private javax.swing.JButton btn_Last;
    private javax.swing.JButton btn_Next;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboTT;
    private com.toedter.calendar.JDateChooser dateNgayThanhToan;
    private com.toedter.calendar.JDateChooser dateNgayThanhToan2;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel lblRecordHD;
    private javax.swing.JLabel lblSoHoaDon;
    private javax.swing.JLabel lbl_RecordHD;
    private javax.swing.JLabel lbl_SoHoaDon;
    private javax.swing.JPanel pnQuetQR;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTable tblHoaDonTraHang;
    private javax.swing.JTable tblLichSuHoaDon;
    private javax.swing.JTable tblhoaDonCT;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaHDTH;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables

    public void addQRCode(String maHDCT) {
        try {
            // kiểm tra và tạo thư mục đích nếu nó chưa tồn tại
            String path = "";
            File dic = new File(path);
            if (!dic.exists()) {
                dic.mkdirs();
            }
            ByteArrayOutputStream out = QRCode.from(maHDCT).to(ImageType.PNG).stream();

            String fileName = maHDCT + ".PNG";
            String fullPath = path + fileName;
            // Ghi hình ảnh vào tệp
            try (FileOutputStream fout = new FileOutputStream(new File(fullPath))) {
                fout.write(out.toByteArray());
                fout.flush();
                System.out.println("Đã tạo và lưu hình ảnh QR code : " + fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void loadTableHoaDonChiTietTH() {

    }

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        webcam.setViewSize(size);
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        pnQuetQR.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 150));
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
                txtMaHDTH.setText(randomCode);
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

    void saveHistory() {
        String path = " ";
        try {
            FileWriter writer = new FileWriter(path);
            for (HoaDon hoaDon : listHD) {
                writer.write(hoaDon.toString());
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            return;
        }
    }

    Date homnay() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formatdate = sdf.format(now);
        return now;
    }

    TaoMaTuSinh_inf genMaHD = new TaoMaTuSinh_inf() {
        @Override
        public String maTuSinh() {
            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            int maTuSinh = new Random().nextInt(1000000);
            return "HD" + maTuSinh;
        }
    };

    HoaDon readFormHoaDon() {
        KhachHang kh = new KhachHang();
        String id = "069E3C6B-9DD1-4081-B992-E90E08DE5A34";
        UUID uuid = UUID.fromString(id);
        kh.setID(uuid);
        return new HoaDon(
                genMaHD.maTuSinh(),
                "",
                "",
                homnay(),
                BigDecimal.valueOf(0),
                homnay(),
                homnay(),
                homnay(),
                homnay(),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(0),
                0,
                "",
                id,
                XLogin.user.getId(),
                XLogin.user.getId(),
                homnay(),
                0
        );
    }
}
