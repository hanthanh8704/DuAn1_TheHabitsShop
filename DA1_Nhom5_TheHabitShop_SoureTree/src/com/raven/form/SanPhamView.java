/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.raven.classinterface.GenMaTuDong;
import com.raven.classmodel.ChatLieu;
import com.raven.classmodel.DayGiay;
import com.raven.classmodel.DeGiay;
import com.raven.classmodel.KichCo;
import com.raven.classmodel.KieuDang;
import com.raven.classmodel.MauSac;
import com.raven.classmodel.NhanHieu;
import com.raven.classmodel.SanPham;
import com.raven.classmodel.SanPhamChiTiet;
import com.raven.classmodel.SanPhamChiTietRespose;
import com.raven.main.Main;
import com.raven.reponsitory.ChatLieuRepository;
import com.raven.reponsitory.DBConnect;
import com.raven.reponsitory.DayGiayRepository;
import com.raven.reponsitory.DeGiayRepository;
import com.raven.reponsitory.KichCoRepository;
import com.raven.reponsitory.KieuDangRespository;
import com.raven.reponsitory.MauSacRepository;
import com.raven.reponsitory.NhanHieuRepository;
import com.raven.reponsitory.SanPhamChiTietRepository;
import com.raven.reponsitory.SanPhamRespository;
import com.raven.service.GetIdSPCTService_dat;
import java.awt.Color;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Random;
import java.util.UUID;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.TableRowSorter;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTable;

import javax.swing.JTextField;
import jdk.jfr.consumer.EventStream;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ThanhDat
 */
public class SanPhamView extends javax.swing.JPanel {
///khai báo đủ các trường bên sản phẩm jdialog

    public static String tenSP;
    public static String mauSac;
    public static String chatLieu;
    public static String nhanHieu;
    public static String kichCo;
    public static String dayGiay;
    public static String deGiay;
    public static String maSPCT;
    public static BigDecimal dongia;
    public static int soluong;
    public static String kieudang;
    public static String tenThuocTinh;
    private SanPhamRespository sps = new SanPhamRespository();
    private List<SanPham> listSP = new ArrayList<>();
    private SanPhamChiTietRepository spct = new SanPhamChiTietRepository();
    private GetIdSPCTService_dat getid = new GetIdSPCTService_dat();
    
    private List<SanPhamChiTiet> listSPCT = new ArrayList<>();
    private int index = -1;
    private MauSacRepository ms = new MauSacRepository();
    private List<MauSac> listMS = new ArrayList<>();
    private ChatLieuRepository cl = new ChatLieuRepository();
    private List<ChatLieu> listCL = new ArrayList<>();
    private DeGiayRepository dg = new DeGiayRepository();
    private List<DeGiay> listDG = new ArrayList<>();
    private KichCoRepository kc = new KichCoRepository();
    private List<KichCo> listKC = new ArrayList<>();
    private DayGiayRepository day = new DayGiayRepository();
    private List<DayGiay> listDay = new ArrayList<>();
    private KieuDangRespository kg = new KieuDangRespository();
    private List<KieuDang> listKG = new ArrayList<>();
    private NhanHieuRepository nh = new NhanHieuRepository();
    private List<NhanHieu> listNH = new ArrayList<>();
//    private  SanPhamChiTietRespose spctre = new SanPhamChiTietRespose();
//    private List<SanPhamChiTietRespose> listSPCTRE = new ArrayList<>();
    public static String ma;
//      private JTable tblSPCT;

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    public long count, soTrang, trang = 1;
    long count1, soTrang1, trang1 = 1;

    /**
     * Creates new form SanPham
     */
    public SanPhamView() {
        initComponents();

        listSP = sps.getAll();
        listSPCT = spct.getAll();
        listMS = ms.getAll();
        listCL = cl.getAll();
        listDG = dg.getAll();
        listKC = kc.getAll();
        listDay = day.getAll();
        listNH = nh.getAll();
        listKG = kg.getAll();
        setComBoBoxTenSanPhamChiTiet();
        setComBoBoxTenSanPham();
        setComBoBoxTenMauSac();
        setComBoBoxTenChatLieu();
        setComBoBoxTenNhanHieu();
        setComBoBoxTenDeGiay();
        setComBoBoxTenKieuDang();
        setComBoBoxTenDayGiay();
        setComBoBoxTenKichCo();
//        listSPCTRE.add(spctre);
        loadTableKC(listKC);
        loadTableDG(listDG);
        Loadtable(listSP);
        LoadtableSPCT(listSPCT);
        loadTableAll(listMS);
        loadTableD(listDay);
        loadTableNH(listNH);
        loadTableKG(listKG);
        //       pagination1.setPagegination(1, 5);

        //  getIDMauSac();
//        cbbMauSac.setSelectedIndex(0);
        ///add pleacehoder
        AddPleacehoderStyle(txtTimKiem);
        AddPleacehoderStyle(txtTimKiem1);
        countDb();

        loadTablePhanTrang(listSPCT = spct.selectPhanTrangAll(1));
        countDbSP();
        //  LoadtablePhanTrangSP(listSP= sps.getSelectPhanTrangSP(1));
        if (count % 5 == 0) {
            soTrang = count / 5;
        } else {
            soTrang = count / 5 + 1;
        }

        if (count1 % 5 == 0) {
            soTrang1 = count1 / 5;
        } else {
            soTrang1 = count1 / 5 + 1;
        }
        //loadData(1);
        lbSoTrang.setText("1/" + soTrang);
        lbTrang.setText("1");

    }

    public void refreshTable() {
        // Gọi phương thức để làm mới dữ liệu JTable
        Loadtable(listSP = sps.getAll());
    }

    public void addQRCode(String maSPCT) {
        try {
            // Kiểm tra và tạo thư mục đích nếu nó chưa tồn tại
            String pathName = "C:\\Users\\ADMIN\\Desktop\\Da1_TheHaBits_Nhom5_master\\DA1_Nhom5_TheHabitShop_SoureTree\\src\\com\\raven\\icon\\QRCode";
            File directory = new File(pathName);
            if (!directory.exists()) {
                directory.mkdirs(); // Tạo thư mục nếu nó chưa tồn tại
            }

            // Tạo hình ảnh QR code từ mã SPCT
            ByteArrayOutputStream out = QRCode.from(maSPCT).to(ImageType.PNG).stream();

            // Tạo tên tệp và đường dẫn đầy đủ
            String fileName = maSPCT + ".PNG";
            String fullPath = pathName + fileName;

            // Ghi hình ảnh vào tệp
            try (FileOutputStream fout = new FileOutputStream(new File(fullPath))) {
                fout.write(out.toByteArray());
                fout.flush();
                System.out.println("Đã tạo và lưu hình ảnh QR code: " + fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public void addQRCode() {
//        try {
//            ByteArrayOutputStream out = QRCode.from(maSPCT).to(ImageType.PNG).stream();
//            String f_name = maSPCT;
//            String Path_name = "C:\\Users\\ThanhDat\\OneDrive\\DuAn1\\DA1\\src\\com\\raven\\icon\\qrcode\\ ";
//            FileOutputStream fout = new FileOutputStream(new File(Path_name + (f_name + ".PNG")));
//            fout.write(out.toByteArray());
//            fout.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    public void countDb() {
        try {
            String sql = "Select count(*) from SanPhamChiTiet";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getLong(1);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void countDbSP() {
        try {
            String sql = "Select count(*) from SanPham";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                count1 = rs.getLong(1);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void loadTablePhanTrang(List<SanPhamChiTiet> spctPT) {
//        DefaultTableModel dtm = (DefaultTableModel) tblSPCT.getModel();
//        int STT = (int) ((trang - 1) * 5 + 1);;
//        dtm.setRowCount(0);
//        for (SanPhamChiTiet s : spctPT) {
//            dtm.addRow(new Object[]{
//                STT++,
//                s.getMa(), s.getTen(), s.getTenmausac(), s.getTenkichCo(), s.getTennhanHieu(), s.getTenchatlieu(),
//                s.getTenkieuDang(), s.getTendaygiay(), s.getTendeGiay(), s.getSoLuong(), NumberFormat.getInstance().format(s.getGiaBan()),
//                s.getTrangThai() == 1 ? "Đang Có Hàng" : "Hết Hàng"
//            });
//        }
//    }
    public void loadTablePhanTrang(List<SanPhamChiTiet> spctPT) {
        DefaultTableModel dtm = (DefaultTableModel) tblSPCT.getModel();
        int STT = (int) ((trang - 1) * 5 + 1);
        dtm.setRowCount(0);

        DecimalFormat currencyFormat = new DecimalFormat("#,###");

        for (SanPhamChiTiet s : spctPT) {
            if (s.getGiaBan1() != null) {
                dtm.addRow(new Object[]{
                    STT++,
                    s.getMa(), s.getTen(), s.getTenmausac(), s.getTenkichCo(), s.getTennhanHieu(), s.getTenchatlieu(),
                    s.getTenkieuDang(), s.getTendaygiay(), s.getTendeGiay(), s.getSoLuong(), currencyFormat.format(s.getGiaBan1()),
                    s.getTrangThai() == 1 ? "Đang Có Hàng" : "Hết Hàng"
                });
            }
        }
    }

    public void LoadtablePhanTrangSP(List<SanPham> spsp) {
        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
        int STT = (int) ((trang - 1) * 5 + 1);
        dtm.setRowCount(0);
        for (SanPham s : spsp) {
            dtm.addRow(new Object[]{
                STT++,
                s.getMaSP(), s.getTenSP(), s.getSoLuong(), s.getTrangThai() == 1 ? "Đang Có Hàng" : "Hết Hàng"

            });
        }

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

    public void Loadtable(List<SanPham> sp) {
        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
        int STT = 1;
        dtm.setRowCount(0);
        for (SanPham s : sp) {
            dtm.addRow(new Object[]{
                STT++,
                s.getMaSP(), s.getTenSP(), s.getSoLuong(), s.getTrangThai() == 1 ? "Đang Có Hàng" : "Hết Hàng"

            });
        }

    }

    boolean checkSP() {
        String tenSP = txtTenSP.getText().trim(); // Lấy tên sản phẩm từ JTextField và loại bỏ khoảng trắng ở đầu và cuối

        // Kiểm tra xem tên sản phẩm có rỗng không
        if (tenSP.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm!");
            return false; // Trả về null nếu có lỗi
        }
        // Kiểm tra sự tồn tại của tên sản phẩm trong cơ sở dữ liệu
        if (sps.checkSPExistence(tenSP)) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm đã tồn tại. Vui lòng chọn tên khác!");
            return false;
        }
        return true;
    }

    SanPham readForm() {
        String tenSP = txtTenSP.getText().trim(); // Lấy tên sản phẩm từ JTextField và loại bỏ khoảng trắng ở đầu và cuối

        GenMaTuDong genTD = new GenMaTuDong() {
            @Override
            public String maTuDong() {
                int gen = new Random().nextInt(1000000);
                return "SP" + gen;
            }
        };

        String maSp = genTD.maTuDong();
        return new SanPham(maSp, tenSP);
    }

    public void LoadtableSPCT(List<SanPhamChiTiet> sp) {
        DefaultTableModel dtm = (DefaultTableModel) tblSPCT.getModel();
        int STT = 1;
        dtm.setRowCount(0);

        DecimalFormat currencyFormat = new DecimalFormat("#,### ");

        for (SanPhamChiTiet s : sp) {
            if (s.getGiaBan1() != null) {
                dtm.addRow(new Object[]{
                    STT++,
                    s.getMa(), s.getTen(), s.getTenmausac(), s.getTenkichCo(), s.getTennhanHieu(), s.getTenchatlieu(),
                    s.getTenkieuDang(), s.getTendaygiay(), s.getTendeGiay(), s.getSoLuong(), currencyFormat.format(s.getGiaBan1()),
                    s.getTrangThai() == 1 ? "Đang Có Hàng" : "Hết Hàng"
                });
            }
        }
    }

    private void ShowDataSPCT(int index) {

        txtMaSPCT.setText(tblSPCT.getValueAt(index, 1).toString());
        cbbTenSanPhamCTSP.setSelectedItem(tblSPCT.getValueAt(index, 2).toString());
        cbbMauSac.setSelectedItem(tblSPCT.getValueAt(index, 3).toString());
        cbbKichThuoc.setSelectedItem(tblSPCT.getValueAt(index, 4).toString());
        cbbNhanHieu.setSelectedItem(tblSPCT.getValueAt(index, 5).toString());
        cbbChatLieu.setSelectedItem(tblSPCT.getValueAt(index, 6).toString());
        cbbKieuDang.setSelectedItem(tblSPCT.getValueAt(index, 7).toString());
        cbbDayGiay.setSelectedItem(tblSPCT.getValueAt(index, 8).toString());
        cbbDeGiay.setSelectedItem(tblSPCT.getValueAt(index, 9).toString());
        txtSL.setText(tblSPCT.getValueAt(index, 10).toString());
        txtDonGia.setText(tblSPCT.getValueAt(index, 11).toString());
        //  txtDonGia.setText(String.valueOf(NumberFormat.getInstance().format(vc.getGiatrimax())));
//        if (tblSPCT.getValueAt(index, 12).toString().equalsIgnoreCase("Đang Có Hàng")) {
//            rbCoHang.setSelected(true);
//        } else {
//            rbHetHang.setSelected(true);
//        }
    }

    private void showData(int index) {
        SanPham s = sps.getAll().get(index);
        txtMaSP.setText(s.getMaSP());
        txtTenSP.setText(s.getTenSP());

    }

    public static String generateRandomUUID() {
        // Tạo một UUID ngẫu nhiên
        UUID uuid = UUID.randomUUID();

        // Chuyển UUID thành chuỗi và trả về
        return uuid.toString();
    }

    private boolean validateFormSPCTRE() {
        // Kiểm tra không để trống số lượng
        String slText = txtSL.getText().trim();
        if (slText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng.");
            return false;
        }

        // Kiểm tra không để trống đơn giá
        String donGiaText = txtDonGia.getText().trim();
        if (donGiaText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn giá.");
            return false;
        }

        int soLuong;
        BigDecimal donGia;

        // Kiểm tra và parse số lượng
        try {
            soLuong = Integer.parseInt(slText);

            // Kiểm tra số lượng là số và lớn hơn 0
            if (soLuong < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là một số nguyên lớn hơn 0.");
                return false; // Trả về false nếu có lỗi
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là một số nguyên lớn hơn 0.");
            return false; // Trả về false nếu có lỗi
        }

        // Kiểm tra và parse đơn giá
        try {
            // Loại bỏ dấu chấm từ chuỗi đơn giá
            donGiaText = donGiaText.replace(".", "");

            donGia = new BigDecimal(donGiaText);

            // Kiểm tra đơn giá là số và lớn hơn 0
            if (donGia.signum() <= 0) {
                JOptionPane.showMessageDialog(this, "Đơn giá phải là một số thực lớn hơn 0.");
                return false; // Trả về false nếu có lỗi
            }
        } catch (NumberFormatException | ArithmeticException e) {
            JOptionPane.showMessageDialog(this, "Đơn giá phải là một số thực lớn hơn 0.");
            return false; // Trả về false nếu có lỗi
        }

        int trangThai = (soLuong == 0) ? 0 : 1;

        // Nếu không có lỗi, trả về true và tiếp tục xử lý
        return true;
    }

// Hàm đọc dữ liệu từ form khi đã kiểm tra
    public SanPhamChiTietRespose readFormSPCTRE() {
        GenMaTuDong genTD = new GenMaTuDong() {
            @Override
            public String maTuDong() {
                int gen = new Random().nextInt(1000000);
                return "SPCT" + gen;
            }
        };

        DecimalFormat currencyFormat = new DecimalFormat("#,###");

        String maSp = genTD.maTuDong();
        maSPCT = maSp;

        int soLuong = Integer.parseInt(txtSL.getText().trim());

        // Chuyển đổi đơn giá từ định dạng VND sang kiểu số (BigDecimal)
        BigDecimal donGia = null;
        try {
            donGia = BigDecimal.valueOf(currencyFormat.parse(txtDonGia.getText()).doubleValue());
        } catch (ParseException ex) {
            Logger.getLogger(SanPhamView.class.getName()).log(Level.SEVERE, null, ex);
        }

        int trangThai = (soLuong == 0) ? 0 : 1;

        return new SanPhamChiTietRespose(
                maSp,
                soLuong,
                trangThai,
                donGia,
                getid.getIDMauSac(cbbMauSac.getSelectedItem() + ""),
                getid.getIDChatLieu(cbbChatLieu.getSelectedItem() + ""),
                getid.getIDKichCo(cbbKichThuoc.getSelectedItem() + ""),
                getid.getIDNhanHieu(cbbNhanHieu.getSelectedItem() + ""),
                getid.getIDDeGiay(cbbDeGiay.getSelectedItem() + ""),
                getid.getIDDayGiay(cbbDayGiay.getSelectedItem() + ""),
                getid.getIDKieuDang(cbbKieuDang.getSelectedItem() + ""),
                getid.getIDSanPham(cbbTenSanPhamCTSP.getSelectedItem() + "")
        );
    }

//   //    private void showData(int index){
//        SanPham s = sps.getAll().get(index);
//        txtMaSP.setText(s.getMaSP());
//        txtTenSP.setText(s.getTenSP());
//    }
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        SanPhamView spview = new SanPhamView();
        jf.add(spview);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
    }
///////////////-------------------------MauSac-------------------------//////////

    void loadTableAll(List<MauSac> ms) {
        DefaultTableModel tbModel = (DefaultTableModel) tblTTSP.getModel();
        int STT = 1;
        tbModel.setRowCount(0);
        for (MauSac m : ms) {
            tbModel.addRow(new Object[]{
                STT++,
                m.getMaMauSac(), m.getTenMauSac()
            });
        }
    }

    void showDataMauSac(int index) {
        if (rbMauSac.isSelected()) {
            MauSac mauSac = ms.getAll().get(index);
            txtMaThuocTinh.setText(mauSac.getMaMauSac());
            txtTenThuocTinh.setText(mauSac.getTenMauSac());
        } else if (rbChatLieu.isSelected()) {
            ChatLieu c = cl.getAll().get(index);
            txtMaThuocTinh.setText(c.getMa());
            txtTenThuocTinh.setText(c.getTen());
        } else if (rbDeGiay.isSelected()) {
            DeGiay d = dg.getAll().get(index);
            txtMaThuocTinh.setText(d.getMa());
            txtTenThuocTinh.setText(d.getTen());
        } else if (rbKichC.isSelected()) {
            KichCo c = kc.getAll().get(index);
            txtMaThuocTinh.setText(c.getMaKichCo());
            txtTenThuocTinh.setText(c.getTenKichCo());
        } else if (rbDayGiay.isSelected()) {
            DayGiay c = day.getAll().get(index);
            txtMaThuocTinh.setText(c.getMa());
            txtTenThuocTinh.setText(c.getTen());
        } else if (rbKieuDang.isSelected()) {
            KieuDang c = kg.getAll().get(index);
            txtMaThuocTinh.setText(c.getMa());
            txtTenThuocTinh.setText(c.getTen());
        } else if (rbNhanHieu.isSelected()) {
            NhanHieu c = nh.getAll().get(index);
            txtMaThuocTinh.setText(c.getMa());
            txtTenThuocTinh.setText(c.getTen());
        }

    }

    boolean validateThuoctiinh() {
        String tenThuocTinh = txtTenThuocTinh.getText().trim();

        if (tenThuocTinh.isEmpty()) {
            showMessage("Vui lòng điền đầy đủ thông tin");
            return false;
        }

        if (tenThuocTinh.length() > 50) {
            showMessage("Không được điền lớn hơn 50 ký tự");
            return false;
        }

        if (rbKichC.isSelected()) {
            return validateKichCo(tenThuocTinh);
        }

        // Kiểm tra xem tên thuộc tính đã tồn tại hay chưa
        if (tenThuocTinhExists(tenThuocTinh)) {
            showMessage("Tên thuộc tính đã tồn tại");
            return false;
        }

        return true;
    }

    private boolean validateKichCo(String tenThuocTinh) {
        try {
            // Kiểm tra nếu là thuộc tính "Kích Cỡ" thì phải là số
            int kichCo = Integer.parseInt(tenThuocTinh);
            if (kichCo < 0) {
                showMessage("Kích cỡ phải lớn hơn 0!");
                return false;
            }
        } catch (NumberFormatException e) {
            showMessage("Giá trị của thuộc tính 'Kích Cỡ' phải là số.");
            return false;
        }
        // Kiểm tra xem tên thuộc tính đã tồn tại hay chưa
        if (rbMauSac.isSelected() && ms.equals(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Tên màu sắc đã tồn tại");
            return false;
        } //else if (rbChatLieu.isSelected() && cl.existsCL(tenThuocTinh)) {
//        JOptionPane.showMessageDialog(this, "Tên chất liệu đã tồn tại");
//        return false;
//    } else if (rbDayGiay.isSelected() && day.existsD(tenThuocTinh)) {
//        JOptionPane.showMessageDialog(this, "Tên dây giày đã tồn tại");
//        return false;
//    } else if (rbKichC.isSelected() && kc.existsKC(tenThuocTinh)) {
//        JOptionPane.showMessageDialog(this, "Tên kích cỡ đã tồn tại");
//        return false;
//    } else if (rbNhanHieu.isSelected() && nh.existsNH(tenThuocTinh)) {
//        JOptionPane.showMessageDialog(this, "Tên nhãn hiệu đã tồn tại");
//        return false;
//    } else if (rbDeGiay.isSelected() && dg.existsDG(tenThuocTinh)) {
//        JOptionPane.showMessageDialog(this, "Tên dệt giày đã tồn tại");
//        return false;
//    } else if (rbKieuDang.isSelected() && kg.existsKG(tenThuocTinh)) {
//        JOptionPane.showMessageDialog(this, "Tên kiểu dáng đã tồn tại");
//        return false;
//    }

        return true;
    }

    private boolean tenThuocTinhExists(String tenThuocTinh) {
        List<String> danhSachTenThuocTinh = new ArrayList<>();
        // Thêm các tên thuộc tính hiện có vào danh sách (cần thay thế bằng cách thêm tên từ cơ sở dữ liệu hoặc nơi lưu trữ khác)
        danhSachTenThuocTinh.add("Mausac");
        danhSachTenThuocTinh.add("Chatlieu");
        danhSachTenThuocTinh.add("Daygiay");

        // Kiểm tra xem tên thuộc tính đã tồn tại trong danh sách hay không
        return danhSachTenThuocTinh.contains(tenThuocTinh);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    MauSac readFormMS() {
//        String maTuDong = "MS";
//        int soMaTuDong = tblTTSP.getRowCount() + 1;
//        maTuDong = maTuDong + soMaTuDong;
        GenMaTuDong genTD = new GenMaTuDong() {
            @Override
            public String maTuDong() {
                int gen = new Random().nextInt(1000000);

                return "MS" + gen;
            }
        };
        String maSp = genTD.maTuDong();
        return new MauSac(maSp, txtTenThuocTinh.getText());
    }
    ///////////////-------------------------ChatLieu-------------------------//////////

    void loadTableCL(List<ChatLieu> ms) {
        DefaultTableModel tbModel = (DefaultTableModel) tblTTSP.getModel();
        int STT = 1;
        tbModel.setRowCount(0);
        for (ChatLieu m : ms) {
            tbModel.addRow(new Object[]{
                STT++,
                m.getMa(), m.getTen()
            });
        }
    }

    ChatLieu readFormCL() {
        GenMaTuDong genTD = new GenMaTuDong() {
            @Override
            public String maTuDong() {
                int gen = new Random().nextInt(1000000);

                return "CL" + gen;
            }
        };
        String maSp = genTD.maTuDong();
        return new ChatLieu(maSp, txtTenThuocTinh.getText());
    }

    ///////////////-------------------------DeGiay-------------------------//////////
    void loadTableDG(List<DeGiay> ms) {
        DefaultTableModel tbModel = (DefaultTableModel) tblTTSP.getModel();
        int STT = 1;
        tbModel.setRowCount(0);
        for (DeGiay d : ms) {
            tbModel.addRow(new Object[]{
                STT++,
                d.getMa(), d.getTen()
            });
        }
    }

    DeGiay readFormDG() {
        GenMaTuDong genTD = new GenMaTuDong() {
            @Override
            public String maTuDong() {
                int gen = new Random().nextInt(1000000);

                return "DG" + gen;
            }
        };
        String maSp = genTD.maTuDong();
        return new DeGiay(maSp, txtTenThuocTinh.getText());
    }

    ///////////////-------------------------KichCo-------------------------//////////
    void loadTableKC(List<KichCo> ms) {
        DefaultTableModel tbModel = (DefaultTableModel) tblTTSP.getModel();
        int STT = 1;
        tbModel.setRowCount(0);
        for (KichCo d : ms) {
            tbModel.addRow(new Object[]{
                STT++,
                d.getMaKichCo(), d.getTenKichCo()
            });
        }
    }

    KichCo readFormKC() {
        GenMaTuDong genTD = new GenMaTuDong() {
            @Override
            public String maTuDong() {
                int gen = new Random().nextInt(1000000);

                return "KC" + gen;
            }
        };
        String maSp = genTD.maTuDong();
        return new KichCo(maSp, txtTenThuocTinh.getText());
    }

    ///////////////-------------------------DayGiay-------------------------//////////
    void loadTableD(List<DayGiay> ms) {
        DefaultTableModel tbModel = (DefaultTableModel) tblTTSP.getModel();
        int STT = 1;
        tbModel.setRowCount(0);
        for (DayGiay d : ms) {
            tbModel.addRow(new Object[]{
                STT++,
                d.getMa(), d.getTen()
            });
        }
    }

    DayGiay readFormDayGiay() {
        GenMaTuDong genTD = new GenMaTuDong() {
            @Override
            public String maTuDong() {
                int gen = new Random().nextInt(1000000);

                return "DG_" + gen;
            }
        };
        String maSp = genTD.maTuDong();
        return new DayGiay(maSp, txtTenThuocTinh.getText());
    }

    ///////////////-------------------------KieuDang-------------------------//////////
    void loadTableKG(List<KieuDang> ms) {
        DefaultTableModel tbModel = (DefaultTableModel) tblTTSP.getModel();
        int STT = 1;
        tbModel.setRowCount(0);
        for (KieuDang d : ms) {
            tbModel.addRow(new Object[]{
                STT++,
                d.getMa(), d.getTen()
            });
        }
    }

    KieuDang readFormKG() {
        GenMaTuDong genTD = new GenMaTuDong() {
            @Override
            public String maTuDong() {
                int gen = new Random().nextInt(1000000);

                return "KG" + gen;
            }
        };
        String maSp = genTD.maTuDong();
        return new KieuDang(maSp, txtTenThuocTinh.getText());
    }

    ///////////////-------------------------NhanHieu-------------------------//////////
    void loadTableNH(List<NhanHieu> ms) {
        DefaultTableModel tbModel = (DefaultTableModel) tblTTSP.getModel();
        int STT = 1;
        tbModel.setRowCount(0);
        for (NhanHieu d : ms) {
            tbModel.addRow(new Object[]{
                STT++,
                d.getMa(), d.getTen()
            });
        }
    }

    NhanHieu readFormNH() {
        GenMaTuDong genTD = new GenMaTuDong() {
            @Override
            public String maTuDong() {
                int gen = new Random().nextInt(1000000);

                return "NH" + gen;
            }
        };
        String maSp = genTD.maTuDong();
        return new NhanHieu(maSp, txtTenThuocTinh.getText());
    }

    void kich() {
        if (tblSPCT == null || index < 0 || index >= tblSPCT.getRowCount()) {
            // Check if tblSPCT exists and index is valid
            System.out.println("Error: tblSPCT is null or index is invalid");
            return;
        }

        String tenSanPham = getColumnValueAsString(index, 2);
        SanPhamChiTietJdialog.tenSP = tenSanPham;
        SanPhamView.tenSP = tenSanPham;
        SanPhamView.maSPCT = getColumnValueAsString(index, 1);
        SanPhamView.mauSac = getColumnValueAsString(index, 3);
        SanPhamView.kichCo = getColumnValueAsString(index, 4);
        SanPhamView.nhanHieu = getColumnValueAsString(index, 5);
        SanPhamView.chatLieu = getColumnValueAsString(index, 6);
        SanPhamView.kieudang = getColumnValueAsString(index, 7);
        SanPhamView.dayGiay = getColumnValueAsString(index, 8);
        SanPhamView.deGiay = getColumnValueAsString(index, 9);

        try {
            soluong = Integer.parseInt(getColumnValueAsString(index, 10).trim());
            String dongiaStr = getColumnValueAsString(index, 11).trim().replace(".", "");
            dongia = new BigDecimal(dongiaStr);
        } catch (NumberFormatException e) {
            // Handle parsing errors
            System.out.println("Error: Unable to parse integer or BigDecimal");
            e.printStackTrace(); // Or log the exception
        }

    }

    private String getColumnValueAsString(int rowIndex, int columnIndex) {
        Object value = tblSPCT.getValueAt(rowIndex, columnIndex);
        return value != null ? value.toString() : "";
    }

    private void lamMoi() {
        txtMaSP.setText("");
        txtTenSP.setText("");

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
        buttonGroup2 = new javax.swing.ButtonGroup();
        tabSanPham = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel32 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbbDeGiay = new javax.swing.JComboBox<>();
        cbbKichThuoc = new javax.swing.JComboBox<>();
        cbbChatLieu = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cbbNhanHieu = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        ThemSanPhamChiTiet = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        XoaSPCT = new javax.swing.JButton();
        btnXuatFileExcel = new javax.swing.JButton();
        btnSuaSPCT = new javax.swing.JButton();
        txtMaSPCT = new javax.swing.JTextField();
        txtSL = new javax.swing.JTextField();
        cbbMauSac = new javax.swing.JComboBox<>();
        cbbDayGiay = new javax.swing.JComboBox<>();
        cbbKieuDang = new javax.swing.JComboBox<>();
        txtDonGia = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        cbbTenSanPhamCTSP = new javax.swing.JComboBox<>();
        jButton25 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtTimKiem1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSPCT = new javax.swing.JTable();
        cboAll = new javax.swing.JCheckBox();
        btnTraiTrai = new javax.swing.JButton();
        btnTrai = new javax.swing.JButton();
        lbTrang = new javax.swing.JLabel();
        btnPhai = new javax.swing.JButton();
        btnPhaiPhai = new javax.swing.JButton();
        lbSoTrang = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtMaThuocTinh = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtTenThuocTinh = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTTSP = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        rbMauSac = new javax.swing.JRadioButton();
        rbKichC = new javax.swing.JRadioButton();
        rbChatLieu = new javax.swing.JRadioButton();
        rbDayGiay = new javax.swing.JRadioButton();
        rbDeGiay = new javax.swing.JRadioButton();
        rbNhanHieu = new javax.swing.JRadioButton();
        rbKieuDang = new javax.swing.JRadioButton();
        jPanel13 = new javax.swing.JPanel();
        jButton24 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        btnLamMoiTTSP = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();

        tabSanPham.setBackground(new java.awt.Color(255, 255, 255));
        tabSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản Lý Sản Phẩm"));
        tabSanPham.setPreferredSize(new java.awt.Dimension(1133, 700));
        tabSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabSanPhamMouseClicked(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setPreferredSize(new java.awt.Dimension(1030, 720));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Thông Tin Sản Phẩm");

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel20.setText("Mã Sản Phẩm ");

        txtTenSP.setBorder(null);

        jLabel21.setText("Tên Sản Phẩm");

        txtMaSP.setEditable(false);
        txtMaSP.setBackground(new java.awt.Color(255, 255, 255));
        txtMaSP.setToolTipText("");
        txtMaSP.setBorder(null);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 255));
        jSeparator2.setForeground(new java.awt.Color(51, 51, 255));

        jSeparator3.setForeground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator2)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                    .addComponent(txtTenSP)
                    .addComponent(jSeparator3))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel20))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setText("Danh Sách Sản Phẩm");

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel33.setText("Tìm Kiếm:");

        txtTimKiem.setText("Tên sản phẩm, Mã Sản Phẩm , Số Lượng");
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

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Sản Phẩm ", "Tên Sản Phẩm", "Số Lượng", "Trạng Thái"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(725, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton9.setBackground(new java.awt.Color(153, 204, 255));
        jButton9.setText("Thêm");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        btnSuaSP.setBackground(new java.awt.Color(153, 204, 255));
        btnSuaSP.setText("Sửa");
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(153, 204, 255));
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSuaSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jPanel11Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLamMoi, btnSuaSP, jButton9});

        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jButton9)
                .addGap(28, 28, 28)
                .addComponent(btnSuaSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btnLamMoi)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel32))
                        .addContainerGap(912, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        tabSanPham.addTab("Sản phẩm", jPanel8);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setPreferredSize(new java.awt.Dimension(1030, 720));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Thông Tin Sản Phẩm");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Mã SPCT");

        jLabel4.setText("Tên Sản Phẩm");

        jLabel5.setText("Đơn Giá");

        jLabel6.setText("Số Lượng");

        jLabel8.setText("Màu Sắc");

        jLabel9.setText("Kích Cỡ ");

        jLabel10.setText("Chất Liệu");

        cbbDeGiay.setForeground(new java.awt.Color(255, 255, 255));
        cbbDeGiay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbDeGiay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbDeGiayMouseClicked(evt);
            }
        });

        cbbKichThuoc.setForeground(new java.awt.Color(255, 255, 255));
        cbbKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbKichThuocMouseClicked(evt);
            }
        });
        cbbKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbKichThuocActionPerformed(evt);
            }
        });

        cbbChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbChatLieuMouseClicked(evt);
            }
        });

        jLabel11.setText("Nhãn Hiệu");

        cbbNhanHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbNhanHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbNhanHieuMouseClicked(evt);
            }
        });

        jLabel13.setText("Kiểu Dáng");

        jLabel14.setText("Dây Giày");

        jLabel15.setText("Đế giày");

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        ThemSanPhamChiTiet.setBackground(new java.awt.Color(153, 204, 255));
        ThemSanPhamChiTiet.setText("Thêm");
        ThemSanPhamChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemSanPhamChiTietActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(153, 204, 255));
        jButton4.setText("Mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        XoaSPCT.setBackground(new java.awt.Color(153, 204, 255));
        XoaSPCT.setText("Xoá");
        XoaSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoaSPCTActionPerformed(evt);
            }
        });

        btnXuatFileExcel.setBackground(new java.awt.Color(153, 204, 255));
        btnXuatFileExcel.setText("Xuất file excell");
        btnXuatFileExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileExcelActionPerformed(evt);
            }
        });

        btnSuaSPCT.setBackground(new java.awt.Color(153, 204, 255));
        btnSuaSPCT.setText("Sửa");
        btnSuaSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPCTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXuatFileExcel)
                    .addComponent(XoaSPCT)
                    .addComponent(jButton4)
                    .addComponent(ThemSanPhamChiTiet))
                .addContainerGap())
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSuaSPCT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ThemSanPhamChiTiet, XoaSPCT, btnSuaSPCT, btnXuatFileExcel, jButton4});

        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ThemSanPhamChiTiet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(XoaSPCT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(btnSuaSPCT)
                .addGap(12, 12, 12)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXuatFileExcel)
                .addGap(17, 17, 17))
        );

        txtMaSPCT.setEditable(false);
        txtMaSPCT.setBackground(new java.awt.Color(204, 204, 204));

        cbbMauSac.setForeground(new java.awt.Color(255, 255, 255));
        cbbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbMauSacMouseClicked(evt);
            }
        });
        cbbMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMauSacActionPerformed(evt);
            }
        });

        cbbDayGiay.setForeground(new java.awt.Color(255, 255, 255));
        cbbDayGiay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbDayGiay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbDayGiayMouseClicked(evt);
            }
        });
        cbbDayGiay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbDayGiayActionPerformed(evt);
            }
        });

        cbbKieuDang.setForeground(new java.awt.Color(255, 255, 255));
        cbbKieuDang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbKieuDang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbKieuDangMouseClicked(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(153, 204, 255));
        jButton14.setText("+");
        jButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton14MouseClicked(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(153, 204, 255));
        jButton17.setText("+");
        jButton17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton17MouseClicked(evt);
            }
        });

        jButton18.setBackground(new java.awt.Color(153, 204, 255));
        jButton18.setText("+");
        jButton18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton18MouseClicked(evt);
            }
        });

        jButton19.setBackground(new java.awt.Color(153, 204, 255));
        jButton19.setText("+");
        jButton19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton19MouseClicked(evt);
            }
        });

        jButton21.setBackground(new java.awt.Color(153, 204, 255));
        jButton21.setText("+");
        jButton21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton21MouseClicked(evt);
            }
        });

        jButton22.setBackground(new java.awt.Color(153, 204, 255));
        jButton22.setText("+");
        jButton22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton22MouseClicked(evt);
            }
        });

        jButton23.setBackground(new java.awt.Color(153, 204, 255));
        jButton23.setText("+");
        jButton23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton23MouseClicked(evt);
            }
        });
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        cbbTenSanPhamCTSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbTenSanPhamCTSP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTenSanPhamCTSPItemStateChanged(evt);
            }
        });
        cbbTenSanPhamCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbTenSanPhamCTSPMouseClicked(evt);
            }
        });

        jButton25.setBackground(new java.awt.Color(153, 204, 255));
        jButton25.setText("+");
        jButton25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton25MouseClicked(evt);
            }
        });
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(txtMaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDonGia)
                            .addComponent(cbbDayGiay, 0, 235, Short.MAX_VALUE)
                            .addComponent(cbbKieuDang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSL)
                            .addComponent(cbbTenSanPhamCTSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton21)
                    .addComponent(jButton22)
                    .addComponent(jButton25))
                .addGap(64, 64, 64)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton14))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbbDeGiay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbKichThuoc, javax.swing.GroupLayout.Alignment.LEADING, 0, 218, Short.MAX_VALUE)
                            .addComponent(cbbChatLieu, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbNhanHieu, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton17)
                                    .addComponent(jButton18)
                                    .addComponent(jButton19)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jButton23)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cbbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton17))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton18))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(cbbNhanHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton19))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton23)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbbDeGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15))))
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbbTenSanPhamCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton25))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(jButton21)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(cbbDayGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton22))))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Danh Sách Sản Phẩm");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel16.setText("Tìm Kiếm");

        txtTimKiem1.setText("Màu sắc, Kích cỡ, Nhãn hiệu, Chất liệu, Kiểu dáng, Dây giày, Đế giày, Số Lượng");
        txtTimKiem1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiem1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKiem1FocusLost(evt);
            }
        });
        txtTimKiem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiem1ActionPerformed(evt);
            }
        });
        txtTimKiem1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiem1KeyReleased(evt);
            }
        });

        tblSPCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Màu Sắc", "Kích Cỡ", "Nhãn Hiệu", "Chất Liệu", "Kiểu Dáng", "Dây Giày", "Đế Giày", "Số Lượng", "Đơn Giá", "Trạng Thái", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblSPCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPCTMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblSPCTMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tblSPCT);
        if (tblSPCT.getColumnModel().getColumnCount() > 0) {
            tblSPCT.getColumnModel().getColumn(0).setMinWidth(20);
            tblSPCT.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblSPCT.getColumnModel().getColumn(13).setMinWidth(15);
            tblSPCT.getColumnModel().getColumn(13).setMaxWidth(15);
        }

        cboAll.setText("All");
        cboAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboAll))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1041, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboAll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
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
                .addGap(49, 49, 49))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTraiTrai)
                    .addComponent(btnTrai)
                    .addComponent(btnPhai)
                    .addComponent(btnPhaiPhai)
                    .addComponent(lbSoTrang)
                    .addComponent(lbTrang))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        tabSanPham.addTab("Sản phẩm chi tiết", jPanel3);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel17.setText("Mã Thuộc Tính");

        txtMaThuocTinh.setEditable(false);
        txtMaThuocTinh.setBackground(new java.awt.Color(255, 255, 255));
        txtMaThuocTinh.setBorder(null);
        txtMaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaThuocTinhActionPerformed(evt);
            }
        });

        jLabel22.setText("Tên Thuộc Tính");

        txtTenThuocTinh.setBorder(null);

        jSeparator4.setForeground(new java.awt.Color(102, 102, 255));

        jSeparator5.setForeground(new java.awt.Color(102, 102, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                            .addComponent(jSeparator4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                            .addComponent(jSeparator5))))
                .addGap(48, 48, 48))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(7, 7, 7)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Thiết Lập Thuộc Tính");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        tblTTSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã Thuộc Tính", "Tên Thuộc Tính"
            }
        ));
        tblTTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTTSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTTSP);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Danh Sách Thuộc Tính");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        buttonGroup1.add(rbMauSac);
        rbMauSac.setSelected(true);
        rbMauSac.setText("Màu Sắc");
        rbMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbMauSacMouseClicked(evt);
            }
        });

        buttonGroup1.add(rbKichC);
        rbKichC.setText("Kích Cỡ");
        rbKichC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbKichCMouseClicked(evt);
            }
        });

        buttonGroup1.add(rbChatLieu);
        rbChatLieu.setText("Chất Liệu");
        rbChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbChatLieuMouseClicked(evt);
            }
        });

        buttonGroup1.add(rbDayGiay);
        rbDayGiay.setText("Dây Giày");
        rbDayGiay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbDayGiayMouseClicked(evt);
            }
        });

        buttonGroup1.add(rbDeGiay);
        rbDeGiay.setText("Đế Giày");
        rbDeGiay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbDeGiayMouseClicked(evt);
            }
        });

        buttonGroup1.add(rbNhanHieu);
        rbNhanHieu.setText("Nhãn hiệu");
        rbNhanHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbNhanHieuMouseClicked(evt);
            }
        });
        rbNhanHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNhanHieuActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbKieuDang);
        rbKieuDang.setText("Kiểu dáng");
        rbKieuDang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbKieuDangMouseClicked(evt);
            }
        });
        rbKieuDang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbKieuDangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(rbNhanHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbMauSac)
                            .addComponent(rbKichC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbDayGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbChatLieu))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbDeGiay))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbMauSac)
                    .addComponent(rbChatLieu)
                    .addComponent(rbDeGiay))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbKichC)
                    .addComponent(rbDayGiay)
                    .addComponent(rbKieuDang))
                .addGap(18, 18, 18)
                .addComponent(rbNhanHieu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton24.setBackground(new java.awt.Color(153, 204, 255));
        jButton24.setText("Thêm");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton29.setBackground(new java.awt.Color(153, 204, 255));
        jButton29.setText("Xóa");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        btnLamMoiTTSP.setBackground(new java.awt.Color(153, 204, 255));
        btnLamMoiTTSP.setText("Làm mới");
        btnLamMoiTTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiTTSPActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(153, 204, 255));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSua)
                    .addComponent(jButton29)
                    .addComponent(jButton24)
                    .addComponent(btnLamMoiTTSP))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel13Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLamMoiTTSP, btnSua, jButton24, jButton29});

        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jButton24)
                .addGap(18, 18, 18)
                .addComponent(jButton29)
                .addGap(18, 18, 18)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLamMoiTTSP)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel18))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        tabSanPham.addTab("Thuộc Tính Sản Phẩm", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 1079, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rbNhanHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNhanHieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbNhanHieuActionPerformed

    private void rbKieuDangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbKieuDangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbKieuDangActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
//        index = tblSPCT.getSelectedRow();
//        if (index < 0) {
//            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 sản phẩm để sửa!");
//        } else {
        if (checkSP()) {
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?", "sửa", JOptionPane.YES_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                index = tblSanPham.getSelectedRow();
                String ma = tblSanPham.getValueAt(index, 1).toString();
                sps.updateSP(readForm(), ma);
                Loadtable(sps.getAll());
                LoadtableSPCT(spct.selectPhanTrangAll(trang));
                setComBoBoxTenSanPhamChiTiet();
                loadTableAll(listMS);
                loadTableCL(listCL);
                loadTableD(listDay);
                loadTableDG(listDG);
                loadTableKC(listKC);
                loadTableKG(listKG);
                loadTableNH(listNH);
                JOptionPane.showMessageDialog(this, "Sửa thành công");
            }

        }
    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        index = tblSanPham.getSelectedRow();
        this.showData(index);
        String tenSanPham = tblSanPham.getValueAt(index, 2).toString();
        SanPhamChiTietJdialog.tenSP = tenSanPham;
        tabSanPham.setSelectedIndex(1);

        // kich();
        LoadtableSPCT(spct.getByTenSanPham(tenSanPham));

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblSPCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPCTMouseClicked
        // try {
        index = tblSPCT.getSelectedRow();

        SanPhamView.ma = tblSPCT.getValueAt(index, 1).toString();
        ShowDataSPCT(index);
        String tenSanPham = tblSPCT.getValueAt(index, 2).toString();
        SanPhamChiTietJdialog.tenSP = tenSanPham;
        String maSanPhamChiTiet = tblSPCT.getValueAt(index, 2).toString();
        SanPhamChiTietJdialog.maSPCT = maSanPhamChiTiet;
        //  setComBoBoxTenSanPhamChiTiet();
        kich();
        SPCTJDialog();
//        SanPhamChiTiet spct = tblSPCT(tenSanPham);
//
//        // Hiển thị chi tiết sản phẩm trong một JFrame mới
//        SanPhamChiTietJdialog chiTietFrame = new SanPhamChiTietJdialog(spct);
//        chiTietFrame.setTitle("Chi Tiết Sản Phẩm - " + tenSanPham);
//        chiTietFrame.setVisible(true);
//        
//        } catch (Exception e) {
//        }


    }//GEN-LAST:event_tblSPCTMouseClicked

    private void tblTTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTTSPMouseClicked
        index = tblTTSP.getSelectedRow();
        showDataMauSac(index);
    }//GEN-LAST:event_tblTTSPMouseClicked

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        if (validateThuoctiinh()) {
            int confirmResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không ?", "thêm", JOptionPane.YES_NO_OPTION);

            if (confirmResult == JOptionPane.YES_OPTION) {
                if (rbMauSac.isSelected()) {
                    ms.AddMS(readFormMS());
                    listMS = ms.getAll();
                    setComBoBoxTenMauSac();
                    loadTableAll(listMS);
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                } else if (rbChatLieu.isSelected()) {
                    cl.AddMS(readFormCL());
                    listCL = cl.getAll();
                    setComBoBoxTenChatLieu();
                    loadTableCL(listCL);
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                } else if (rbDayGiay.isSelected()) {
                    day.AddDay(readFormDayGiay());
                    listDay = day.getAll();
                    setComBoBoxTenDayGiay();
                    loadTableD(listDay);
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                } else if (rbKichC.isSelected()) {
                    kc.AddKC(readFormKC());
                    listKC = kc.getAll();
                    setComBoBoxTenKichCo();
                    loadTableKC(listKC);
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                } else if (rbNhanHieu.isSelected()) {
                    nh.AddNH(readFormNH());
                    listNH = nh.getAll();
                    setComBoBoxTenNhanHieu();
                    loadTableNH(listNH);
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                } else if (rbDeGiay.isSelected()) {
                    dg.AddDG(readFormDG());
                    listDG = dg.getAll();
                    setComBoBoxTenDeGiay();
                    loadTableDG(listDG);
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                } else if (rbKieuDang.isSelected()) {
                    kg.AddKG(readFormKG());
                    listKG = kg.getAll();
                    setComBoBoxTenKieuDang();
                    loadTableKG(listKG);
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                }
            }
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void rbChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbChatLieuMouseClicked
        loadTableCL(listCL);
    }//GEN-LAST:event_rbChatLieuMouseClicked

    private void rbMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbMauSacMouseClicked
      if(rbMauSac.isSelected()){
        loadTableAll(listMS);
    }
       
    }//GEN-LAST:event_rbMauSacMouseClicked

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        int index = tblTTSP.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 sản phẩm để xóa!");
            return; // Thoát khỏi phương thức nếu không có mục nào được chọn.
        }

        int confirmResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không ?", "Xóa", JOptionPane.YES_NO_OPTION);

        if (confirmResult == JOptionPane.YES_OPTION) {
            int row = tblTTSP.getSelectedRow();
            String ma = tblTTSP.getValueAt(row, 1).toString();

            if (rbMauSac.isSelected()) {
                ms.xoaMS(ma);
                listMS = ms.getAll();
                setComBoBoxTenMauSac();
                loadTableAll(listMS);
            } else if (rbChatLieu.isSelected()) {
                cl.xoaCL(ma);
                listCL = cl.getAll();
                setComBoBoxTenChatLieu();
                loadTableCL(listCL);
            } else if (rbDayGiay.isSelected()) {
                day.xoaD(ma);
                listDay = day.getAll();
                setComBoBoxTenDayGiay();
                loadTableD(listDay);
            } else if (rbKichC.isSelected()) {
                kc.xoaKC(ma);
                listKC = kc.getAll();
                setComBoBoxTenKichCo();
                loadTableKC(listKC);
            } else if (rbNhanHieu.isSelected()) {
                nh.xoaNH(ma);
                listNH = nh.getAll();
                setComBoBoxTenNhanHieu();
                loadTableNH(listNH);
            } else if (rbDeGiay.isSelected()) {
                dg.xoaDG(ma);
                listDG = dg.getAll();
                setComBoBoxTenDeGiay();
                loadTableDG(listDG);
            } else if (rbKieuDang.isSelected()) {
                kg.xoaKG(ma);
                listKG = kg.getAll();
                setComBoBoxTenKieuDang();
                loadTableKG(listKG);
            }

            JOptionPane.showMessageDialog(this, "Xóa thành công");
        }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void rbDeGiayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbDeGiayMouseClicked
        loadTableDG(listDG);
    }//GEN-LAST:event_rbDeGiayMouseClicked

    private void rbKichCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbKichCMouseClicked
        loadTableKC(listKC);
    }//GEN-LAST:event_rbKichCMouseClicked

    private void rbDayGiayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbDayGiayMouseClicked
        loadTableD(listDay);
    }//GEN-LAST:event_rbDayGiayMouseClicked

    private void rbNhanHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbNhanHieuMouseClicked
        loadTableNH(listNH);
    }//GEN-LAST:event_rbNhanHieuMouseClicked

    private void rbKieuDangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbKieuDangMouseClicked
        loadTableKG(listKG);
    }//GEN-LAST:event_rbKieuDangMouseClicked

    private void txtMaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaThuocTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaThuocTinhActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton23ActionPerformed


    private void ThemSanPhamChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemSanPhamChiTietActionPerformed
        if (validateFormSPCTRE()) {

            int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?", "Thêm", JOptionPane.YES_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                SanPhamChiTietRespose spctre = this.readFormSPCTRE();

                if (spct.AddSP(spctre) != 0) {
                    ///   listSPCT = spct.getAll();
                    listSPCT = spct.selectPhanTrangAll(trang);
                    //       LoadtableSPCT(listSPCT);
                    loadTablePhanTrang(listSPCT);
                    Loadtable(sps.getAll());
                    loadTableAll(listMS);
                    loadTableCL(listCL);
                    loadTableD(listDay);
                    loadTableDG(listDG);
                    loadTableKC(listKC);
                    loadTableKG(listKG);
                    loadTableNH(listNH);
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    addQRCode(maSPCT);
                }
            }
        }
    }//GEN-LAST:event_ThemSanPhamChiTietActionPerformed

    private void cbbTenSanPhamCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbTenSanPhamCTSPMouseClicked
        //  setComBoBoxTenSanPhamChiTiet();
        setComBoBoxTenSanPham();
    }//GEN-LAST:event_cbbTenSanPhamCTSPMouseClicked

    private void cbbTenSanPhamCTSPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTenSanPhamCTSPItemStateChanged

    }//GEN-LAST:event_cbbTenSanPhamCTSPItemStateChanged

    private void XoaSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XoaSPCTActionPerformed
        index = tblSPCT.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 sản phẩm để xóa!");
        } else {

            int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?", "Xóa", JOptionPane.YES_OPTION);
            if (choice == JOptionPane.YES_OPTION) {

                String ma = tblSPCT.getValueAt(index, 1).toString();
                spct.xoaSP(ma);
                LoadtableSPCT(spct.selectPhanTrangAll(trang));
                Loadtable(sps.getAll());
                JOptionPane.showMessageDialog(this, "Xóa thành công");
            }
        }
    }//GEN-LAST:event_XoaSPCTActionPerformed


    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (checkSP()) {

            int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?", "Thêm", JOptionPane.YES_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                if (sps.AddSP(readForm()) != 0) {
                    listSP = sps.getAll();
                    Loadtable(listSP);
                    LoadtableSPCT(spct.selectPhanTrangAll(trang));
                    setComBoBoxTenSanPham();
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                }
            }
        }


    }//GEN-LAST:event_jButton9ActionPerformed


    private void cbbMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMauSacActionPerformed

//        String mauSac = cbbMauSac.getSelectedItem() + "";
//        if (mauSac != null) {
//            getIDMauSac(mauSac);
//
//        }

    }//GEN-LAST:event_cbbMauSacActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        LoadtableSPCT(spct.getAll());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbbKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbKichThuocActionPerformed


    }//GEN-LAST:event_cbbKichThuocActionPerformed

    private void cbbDayGiayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDayGiayActionPerformed
        String daygiay = cbbDayGiay.getSelectedItem() + "";
        if (daygiay != null) {
            getid.getIDDayGiay(daygiay);
        }
    }//GEN-LAST:event_cbbDayGiayActionPerformed

    private void jButton22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton22MouseClicked
        ThemNhanThuocTinh.tenThuocTinh = "Daygiay";
        themnhanh();
        loadTableD(listDay);
    }//GEN-LAST:event_jButton22MouseClicked

    private void jButton14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseClicked
        ThemNhanThuocTinh.tenThuocTinh = "Mausac";
        themnhanh();
        loadTableAll(listMS);
    }//GEN-LAST:event_jButton14MouseClicked

    private void jButton17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton17MouseClicked
        ThemNhanThuocTinh.tenThuocTinh = "Kichco";
        themnhanh();
        loadTableKC(listKC);
    }//GEN-LAST:event_jButton17MouseClicked

    private void jButton18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MouseClicked
        ThemNhanThuocTinh.tenThuocTinh = "Chatlieu";
        themnhanh();
        loadTableCL(listCL);
    }//GEN-LAST:event_jButton18MouseClicked

    private void jButton19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MouseClicked
        ThemNhanThuocTinh.tenThuocTinh = "Nhanhieu";
        themnhanh();
        loadTableNH(listNH);
    }//GEN-LAST:event_jButton19MouseClicked

    private void jButton23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton23MouseClicked
        ThemNhanThuocTinh.tenThuocTinh = "Degiay";
        themnhanh();
        loadTableDG(listDG);
    }//GEN-LAST:event_jButton23MouseClicked

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tblSanPham.setRowSorter(trs);
        String searchText = txtTimKiem.getText().trim();
        trs.setRowFilter(new RowFilter<DefaultTableModel, Object>() {
            @Override
            public boolean include(RowFilter.Entry<? extends DefaultTableModel, ? extends Object> entry) {
                for (int i = 0; i < entry.getValueCount(); i++) {
                    if (entry.getStringValue(i).toLowerCase().contains(searchText.toLowerCase())) {
                        return true;
                    }
                }
                return false;
            }
        });
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void tabSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabSanPhamMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabSanPhamMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        lamMoi();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtTimKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusGained
        if (txtTimKiem.getText().equals("Tên sản phẩm, Mã Sản Phẩm , Số Lượng")) {
            txtTimKiem.setText(null);
            txtTimKiem.requestFocus();
            RemovePleacehoderStyle(txtTimKiem);
        }
    }//GEN-LAST:event_txtTimKiemFocusGained

    private void txtTimKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusLost
        if (txtTimKiem.getText().length() == 0) {
            AddPleacehoderStyle(txtTimKiem);
            txtTimKiem.setText("Tên sản phẩm, Mã Sản Phẩm , Số Lượng");

        }
    }//GEN-LAST:event_txtTimKiemFocusLost

    private void btnSuaSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPCTActionPerformed
        index = tblSPCT.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 sản phẩm để sửa!");
        } else {

            if (validateFormSPCTRE()) {

                int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?", "Sửa", JOptionPane.YES_OPTION);
                if (choice == JOptionPane.YES_OPTION) {

                    String ma = tblSPCT.getValueAt(index, 1).toString();
                    spct.updateSP(readFormSPCTRE(), ma);
                    LoadtableSPCT(spct.selectPhanTrangAll(trang));
                    Loadtable(sps.getAll());
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                    addQRCode(maSPCT);
                }
            }
        }
    }//GEN-LAST:event_btnSuaSPCTActionPerformed

    private void btnLamMoiTTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiTTSPActionPerformed
        txtMaThuocTinh.setText("");
        txtTenThuocTinh.setText("");
    }//GEN-LAST:event_btnLamMoiTTSPActionPerformed

    private void cbbMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbMauSacMouseClicked

        setComBoBoxTenMauSac();
        loadTableAll(listMS = ms.getAll());
    }//GEN-LAST:event_cbbMauSacMouseClicked

    private void cbbKieuDangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbKieuDangMouseClicked
        // TODO add your handling code here:
        kg.getAll();
        setComBoBoxTenKieuDang();
        loadTableKG(listKG = kg.getAll());
    }//GEN-LAST:event_cbbKieuDangMouseClicked

    private void cbbDayGiayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbDayGiayMouseClicked
        setComBoBoxTenDayGiay();
        loadTableD(listDay = day.getAll());
    }//GEN-LAST:event_cbbDayGiayMouseClicked

    private void cbbKichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbKichThuocMouseClicked
        // TODO add your handling code here:
        setComBoBoxTenKichCo();
        loadTableKC(listKC = kc.getAll());
    }//GEN-LAST:event_cbbKichThuocMouseClicked

    private void cbbChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbChatLieuMouseClicked
        // TODO add your handling code here:
        setComBoBoxTenChatLieu();
        loadTableCL(listCL = cl.getAll());
    }//GEN-LAST:event_cbbChatLieuMouseClicked

    private void cbbNhanHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbNhanHieuMouseClicked
        // TODO add your handling code here:
        setComBoBoxTenNhanHieu();
        loadTableNH(listNH = nh.getAll());
    }//GEN-LAST:event_cbbNhanHieuMouseClicked

    private void cbbDeGiayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbDeGiayMouseClicked
        // TODO add your handling code here:
        setComBoBoxTenDeGiay();
        loadTableDG(listDG = dg.getAll());
    }//GEN-LAST:event_cbbDeGiayMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int index = tblTTSP.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 sản phẩm để sửa!");
            return; // Thoát khỏi phương thức nếu không có mục nào được chọn.
        }

        int confirmResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không ?", "Sửa", JOptionPane.YES_NO_OPTION);

        if (confirmResult == JOptionPane.YES_OPTION) {
            int row = tblTTSP.getSelectedRow();
            String ma = tblTTSP.getValueAt(row, 1).toString();

            if (rbMauSac.isSelected()) {
                ms.suaMS(readFormMS(), ma);
                listMS = ms.getAll();
                setComBoBoxTenMauSac();
                loadTableAll(listMS);
            } else if (rbChatLieu.isSelected()) {
                cl.suaChatLieu(readFormCL(), ma);
                listCL = cl.getAll();
                setComBoBoxTenChatLieu();
                loadTableCL(listCL);
            } else if (rbDayGiay.isSelected()) {
                day.suaDayGiay(readFormDayGiay(), ma);
                listDay = day.getAll();
                setComBoBoxTenDayGiay();
                loadTableD(listDay);
            } else if (rbKichC.isSelected()) {
                kc.suaKC(readFormKC(), ma);
                listKC = kc.getAll();
                setComBoBoxTenKichCo();
                loadTableKC(listKC);
            } else if (rbNhanHieu.isSelected()) {
                nh.suaNH(readFormNH(), ma);
                listNH = nh.getAll();
                setComBoBoxTenNhanHieu();
                loadTableNH(listNH);
            } else if (rbDeGiay.isSelected()) {
                dg.suaDeGiay(readFormDG(), ma);
                listDG = dg.getAll();
                setComBoBoxTenDeGiay();
                loadTableDG(listDG);
            } else if (rbKieuDang.isSelected()) {
                kg.suaKG(readFormKG(), ma);
                listKG = kg.getAll();
                setComBoBoxTenKieuDang();
                loadTableKG(listKG);
            }

            JOptionPane.showMessageDialog(this, "Sửa thành công");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtTimKiem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiem1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblSPCT.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tblSPCT.setRowSorter(trs);
        String searchText = txtTimKiem1.getText().trim();
        trs.setRowFilter(new RowFilter<DefaultTableModel, Object>() {
            @Override
            public boolean include(RowFilter.Entry<? extends DefaultTableModel, ? extends Object> entry) {
                for (int i = 0; i < entry.getValueCount(); i++) {
                    if (entry.getStringValue(i).toLowerCase().contains(searchText.toLowerCase())) {
                        return true;
                    }
                }
                return false;
            }
        });
    }//GEN-LAST:event_txtTimKiem1ActionPerformed

    private void txtTimKiem1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiem1FocusLost
        if (txtTimKiem1.getText().length() == 0) {
            AddPleacehoderStyle(txtTimKiem1);
            txtTimKiem1.setText("Màu sắc, Kích cỡ, Nhãn hiệu, Chất liệu, Kiểu dáng, Dây giày, Đế giày, Số Lượng");

        }
    }//GEN-LAST:event_txtTimKiem1FocusLost

    private void txtTimKiem1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiem1FocusGained
        if (txtTimKiem1.getText().equals("Màu sắc, Kích cỡ, Nhãn hiệu, Chất liệu, Kiểu dáng, Dây giày, Đế giày, Số Lượng")) {
            txtTimKiem1.setText(null);
            txtTimKiem1.requestFocus();
            RemovePleacehoderStyle(txtTimKiem1);
        }
    }//GEN-LAST:event_txtTimKiem1FocusGained
//
//    void search(String str) {
//        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
//        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
//        tblSanPham.setRowSorter(trs);
//        String searchText = txtTimKiem.getText().trim();
//        trs.setRowFilter(RowFilter.regexFilter(str));
//    }

    void search2(String str) {
        DefaultTableModel model = (DefaultTableModel) tblSPCT.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tblSPCT.setRowSorter(trs);
        String searchText = txtTimKiem1.getText().trim();
        trs.setRowFilter(RowFilter.regexFilter(str));
    }
    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
//        String tk = txtTimKiem.getText();
//        search(tk);
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtTimKiem1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiem1KeyReleased
        String tk = txtTimKiem1.getText();
        search2(tk);
    }//GEN-LAST:event_txtTimKiem1KeyReleased
    boolean selected = false;
    private void cboAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAllActionPerformed
        listSPCT = spct.selectPhanTrangAll(trang);
        if (!selected) {
            for (int i = 0; i < listSPCT.size(); i++) {
                tblSPCT.setValueAt(true, i, 13);
            }
            selected = true;
        } else {
            for (int i = 0; i < listSPCT.size(); i++) {
                tblSPCT.setValueAt(false, i, 13);
            }
            selected = false;
        }

    }//GEN-LAST:event_cboAllActionPerformed

    private void btnTraiTraiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraiTraiActionPerformed
        // TODO add your handling code here:
        trang = 1;
        loadTablePhanTrang(spct.selectPhanTrangAll(trang));
        lbTrang.setText("1");
        lbSoTrang.setText("1/" + soTrang);
    }//GEN-LAST:event_btnTraiTraiActionPerformed

    private void btnTraiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraiActionPerformed
        if (trang > 1) {
            trang--;
            loadTablePhanTrang(spct.selectPhanTrangAll(trang));
            lbTrang.setText("" + trang);
            lbSoTrang.setText(trang + "/" + soTrang);
        }
    }//GEN-LAST:event_btnTraiActionPerformed

    private void btnPhaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhaiActionPerformed
        // TODO add your handling code here:
        if (trang < soTrang) {
            trang++;
            loadTablePhanTrang(spct.selectPhanTrangAll(trang));
            lbTrang.setText("" + trang);
            lbSoTrang.setText(trang + "/" + soTrang);
          
        }
    }//GEN-LAST:event_btnPhaiActionPerformed

    private void btnPhaiPhaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhaiPhaiActionPerformed
        trang = soTrang;
        loadTablePhanTrang(spct.selectPhanTrangAll(trang));
        lbTrang.setText("" + soTrang);
        lbSoTrang.setText(soTrang + "/" + soTrang);
    }//GEN-LAST:event_btnPhaiPhaiActionPerformed

    private void btnXuatFileExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileExcelActionPerformed
        LoadtableSPCT(spct.getAll());
        int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất file Excel không?", "Xuất", JOptionPane.YES_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
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
                    try (Workbook workbook = new XSSFWorkbook(); FileOutputStream out = new FileOutputStream(saveFile)) {

                        Sheet sheet = workbook.createSheet("sanpham");

                        // Tạo hàng đầu tiên (header) từ tên cột của bảng
                        Row headerRow = sheet.createRow(0);
                        for (int i = 0; i < tblSPCT.getColumnCount(); i++) {
                            Cell cell = headerRow.createCell(i);
                            cell.setCellValue(tblSPCT.getColumnName(i));
                        }

                        // Sao chép dữ liệu từ bảng vào Excel
                        for (int j = 0; j < tblSPCT.getRowCount(); j++) {
                            Row excelRow = sheet.createRow(j + 1);
                            for (int k = 0; k < tblSPCT.getColumnCount(); k++) {
                                Cell cell = excelRow.createCell(k);
                                Object cellValue = tblSPCT.getValueAt(j, k);
                                if (cellValue != null) {
                                    cell.setCellValue(cellValue.toString());
                                }
                            }
                        }

                        // Lưu workbook vào tệp
                        workbook.write(out);
                        EventStream.openFile(saveFile.toPath());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Xuất File Thành Công!");
        }
    }//GEN-LAST:event_btnXuatFileExcelActionPerformed

    private void jButton25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton25MouseClicked
        ThemnhanTenSP dialof = new ThemnhanTenSP((Frame) SwingUtilities.getWindowAncestor(this), selected);
        dialof.setVisible(true);
    }//GEN-LAST:event_jButton25MouseClicked

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed

    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MouseClicked
        ThemNhanThuocTinh.tenThuocTinh = "Kieudang";
        themnhanh();
        loadTableKG(listKG);
    }//GEN-LAST:event_jButton21MouseClicked

    private void tblSPCTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPCTMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSPCTMouseEntered

    public static String generateContentForSPCT(SanPhamChiTiet spct) {
        // Tạo nội dung cho mã QR code từ thông tin SPCT
        // Điều này có thể thay đổi tùy thuộc vào yêu cầu của bạn
        return "Mã SPCT: " + spct.getMa() + ", Tên SPCT: " + spct.getTen();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ThemSanPhamChiTiet;
    private javax.swing.JButton XoaSPCT;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoiTTSP;
    private javax.swing.JButton btnPhai;
    private javax.swing.JButton btnPhaiPhai;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnSuaSPCT;
    private javax.swing.JButton btnTrai;
    private javax.swing.JButton btnTraiTrai;
    private javax.swing.JButton btnXuatFileExcel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbDayGiay;
    private javax.swing.JComboBox<String> cbbDeGiay;
    private javax.swing.JComboBox<String> cbbKichThuoc;
    private javax.swing.JComboBox<String> cbbKieuDang;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbNhanHieu;
    private javax.swing.JComboBox<String> cbbTenSanPhamCTSP;
    private javax.swing.JCheckBox cboAll;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lbSoTrang;
    private javax.swing.JLabel lbTrang;
    private javax.swing.JRadioButton rbChatLieu;
    private javax.swing.JRadioButton rbDayGiay;
    private javax.swing.JRadioButton rbDeGiay;
    private javax.swing.JRadioButton rbKichC;
    private javax.swing.JRadioButton rbKieuDang;
    private javax.swing.JRadioButton rbMauSac;
    private javax.swing.JRadioButton rbNhanHieu;
    private javax.swing.JTabbedPane tabSanPham;
    private javax.swing.JTable tblSPCT;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblTTSP;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMaSPCT;
    private javax.swing.JTextField txtMaThuocTinh;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenThuocTinh;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiem1;
    // End of variables declaration//GEN-END:variables

    void SPCTJDialog() {
        Main m = new Main();
        new SanPhamChiTietJdialog(m, true).setVisible(true);
    }

    void themnhanh() {
        ThemNhanThuocTinh dialog = new ThemNhanThuocTinh((Frame) SwingUtilities.getWindowAncestor(this), true);
        dialog.setVisible(true);
    }

    void setComBoBoxTenSanPham() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cbbTenSanPhamCTSP.getModel();
        boxModel.removeAllElements();
        for (SanPham sp : sps.getAll()) {
            boxModel.addElement(sp.getTenSP());

        }
    }

    void setComBoBoxTenSanPhamChiTiet() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cbbTenSanPhamCTSP.getModel();
        boxModel.removeAllElements();
        for (SanPhamChiTiet sp : spct.getAll()) {
            boxModel.addElement(sp.getTen());

        }
    }

    public void setComBoBoxTenMauSac() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cbbMauSac.getModel();
        boxModel.removeAllElements();
        for (MauSac sp : ms.getAll()) {
            boxModel.addElement(sp.getTenMauSac());

        }
    }

    void setComBoBoxTenKichCo() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cbbKichThuoc.getModel();
        boxModel.removeAllElements();
        for (KichCo sp : kc.getAll()) {
            boxModel.addElement(sp.getTenKichCo());

        }
    }

    void setComBoBoxTenKieuDang() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cbbKieuDang.getModel();
        boxModel.removeAllElements();
        for (KieuDang sp : kg.getAll()) {
            boxModel.addElement(sp.getTen());

        }
    }

    void setComBoBoxTenDayGiay() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cbbDayGiay.getModel();
        boxModel.removeAllElements();
        for (DayGiay sp : day.getAll()) {
            boxModel.addElement(sp.getTen());

        }
    }

    void setComBoBoxTenDeGiay() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cbbDeGiay.getModel();
        boxModel.removeAllElements();
        for (DeGiay sp : dg.getAll()) {
            boxModel.addElement(sp.getTen());

        }
    }

    void setComBoBoxTenChatLieu() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cbbChatLieu.getModel();
        boxModel.removeAllElements();
        for (ChatLieu sp : cl.getAll()) {
            boxModel.addElement(sp.getTen());

        }
    }

    void setComBoBoxTenNhanHieu() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cbbNhanHieu.getModel();
        boxModel.removeAllElements();
        for (NhanHieu sp : nh.getAll()) {
            boxModel.addElement(sp.getTen());

        }
    }

}
