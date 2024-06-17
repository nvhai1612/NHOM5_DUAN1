/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import DomainModel.HoaDon;
import DomainModel.KhachHang;
import DomainModel.SPCT;
import Repository.Impl.HoaDonRepos;
import Repository.Impl.SPCTRepos;
import Service.Impl.ChatLieuService;
import Service.Impl.HoaDonService;
import Service.Impl.KhachHangService;
import Service.Impl.KichCoService;
import Service.Impl.MauSacService;
import Service.Impl.SPCTService;
import Service.Impl.SanPhamService;
import Service.Impl.ThuongHieuService;
import Utiliti.SessionData;
import ViewModel.HoaDonVM;
import ViewModel.KhachHangVM;
import ViewModel.SPCTVM;
import java.awt.CardLayout;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class BanHangJFrame extends javax.swing.JFrame {

    private SPCTService SPCTService = new SPCTService();
    private MauSacService mauSacService = new MauSacService();
    private ChatLieuService chatLieuService = new ChatLieuService();
    private KichCoService kichCoService = new KichCoService();
    private ThuongHieuService nSXService = new ThuongHieuService();
    private SanPhamService sanPhamService = new SanPhamService();
    private HoaDonRepos hoaDonRes = new HoaDonRepos();
    private HoaDonService hoaDonService = new HoaDonService();
    private ArrayList<SPCTVM> listSPCT = new ArrayList<>();
    private DefaultTableModel model = new DefaultTableModel();
    private ArrayList<HoaDonVM> listHDVM = new ArrayList<>();
    private SPCTRepos spctrp = new SPCTRepos();
    private KhachHangService khs = new KhachHangService();

    CardLayout card;

    DefaultTableModel dtmsp = new DefaultTableModel();
    DefaultTableModel dtmgh = new DefaultTableModel();
    DefaultTableModel dtmhd = new DefaultTableModel();
    DefaultTableModel tableModel;
    DefaultTableModel tableModel1HoaDon;
    Map<String, Integer> Sps = new HashMap<>();

    KhachHangService service = new KhachHangService();
    DefaultTableModel tableModelHoaDon;
    int index = -1;

    /**
     * Creates new form BanHangJFrame
     */
    public BanHangJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        LoadTableHoaDon();
        LoadTableSanPham();
        LoadTableGioHang();
        loadTableKhachHang();
    }

    public BanHangJFrame(String MaNV) {
        initComponents();
        card = (CardLayout) pnlCards.getLayout();
        card.show(pnlCards, "pnlBanHang");
        setLocationRelativeTo(null);
        LoadTableHoaDon();
//        ctspjp.LoadTable();
        LoadTableSanPham();
        LoadTableGioHang();
        txtNguoiDung.setText(MaNV);
    }

    private void LoadTableSanPham() {
        dtmsp = (DefaultTableModel) tblDanhSachSP.getModel();
        dtmsp.setRowCount(0);

        ArrayList<SPCTVM> listCTSPVM = SPCTService.getAllBanHang();
        for (SPCTVM ctspvm : listCTSPVM) {
            dtmsp.addRow(new Object[]{
                ctspvm.getMaSPCT(),
                ctspvm.getTenSP(),
                ctspvm.getTenCL(),
                ctspvm.getTenKC(),
                ctspvm.getTenMS(),
                ctspvm.getTenTH(),
                ctspvm.getSoLuongTon(),
                new BigDecimal(ctspvm.getDonGia()),});
        }
    }

    private void LoadTableGioHang() {
        dtmgh = (DefaultTableModel) tblGioHang.getModel();
        dtmgh.setRowCount(0);

        ArrayList<SPCTVM> listSPCTVM = listSPCT;

        for (SPCTVM ctspvm : listSPCTVM) {
            dtmgh.addRow(new Object[]{
                ctspvm.getMaSPCT(),
                ctspvm.getTenSP(),
                ctspvm.getSoLuongTon(),
                ctspvm.getDonGia(),
                new BigDecimal(ctspvm.getSoLuongTon() * ctspvm.getDonGia()),});
        }
    }

    private void LoadTableHoaDon() {
        dtmhd = (DefaultTableModel) tblHoaDon.getModel();
        dtmhd.setRowCount(0);
        hoaDonService.getAll().stream().forEach(System.out::print);
//.isEmpty() ? hoaDonService.getAllHoaDon() : hoaDonService.getAll()
        ArrayList<HoaDonVM> listHDVM = hoaDonService.getAll();
        for (HoaDonVM hdvm : listHDVM) {
            dtmhd.addRow(new Object[]{
                hdvm.getMaHD(),
                hdvm.getTenNV(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(hdvm.getNgayTao()),
                hdvm.getTrangThaiHD() == 0 ? "Chờ thanh toán" : "Đã thanh toán"
            });
        }
    }

    private void LoadHoaDonCho(String MaHD) {
        dtmgh = (DefaultTableModel) tblGioHang.getModel();
        dtmgh.setRowCount(0);

        ArrayList<SPCT> listHDC = hoaDonService.HoaDonCho(MaHD);
        for (SPCT spct : listHDC) {
            dtmgh.addRow(new Object[]{
                spct.getMaSPCT(),
                spct.getTenSP(),
                spct.getSoLuongTon(),
                spct.getDonGia(),
                new BigDecimal(spct.getDonGia() * spct.getSoLuongTon()),});
        }
        TinhTien();
    }

    public void reset() {
        txtTongTien.setText("0");
        txtTienThua.setText("0");
        txtCanThanhToan.setText("0");
        txtTienKhachDua.setText("0");
        cbbPTTT.setSelectedItem(0);
        txtTenKH.setText("Khách lẻ");
        txtSDTKH.setText("");
        dtmgh = (DefaultTableModel) tblGioHang.getModel();
        dtmgh.setRowCount(0);
    }

    public void TinhTien() {
        float CanThanhToan = 0;
        float ThanhTien = 0;
        float Khuyenmai = 0;
        int Tienkhachdua = 0;
        float Tienthua = 0;
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            ThanhTien += Float.valueOf(tblGioHang.getValueAt(i, 4).toString());
            CanThanhToan = ThanhTien - Khuyenmai;
            Tienthua = Tienkhachdua - CanThanhToan;
        }

        txtTongTien.setText(String.valueOf(ThanhTien));
        cbbKhuyenMai.setSelectedItem(Khuyenmai);
        txtCanThanhToan.setText(String.valueOf(CanThanhToan));
        txtTienKhachDua.setText(String.valueOf(Tienkhachdua));
        txtTienThua.setText(String.valueOf(Tienthua));
    }

    public void setTenNhanVien(String TenNV) {
        txtNguoiDung.setText(TenNV);
    }

    private void loadTableKhachHang() {
        tableModel = (DefaultTableModel) tblKhachHang.getModel();
        tableModel.setRowCount(0);
        ArrayList<KhachHangVM> list = service.getAll();
        for (KhachHangVM kh : list) {
            tableModel.addRow(new Object[]{
                kh.getMaKH(),
                kh.getTenKH(),
                kh.getNgaySinh(),
                kh.getGioiTinh(),
                kh.getSdt(),
                kh.getDiaChi()
            });
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

        jLabel55 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        txtSDTKH = new javax.swing.JTextField();
        btnChonKH = new javax.swing.JButton();
        jSeparator34 = new javax.swing.JSeparator();
        KhachHangDialog = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        txtTenKH2 = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        taDiaChi = new javax.swing.JTextArea();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblHoaDonKH = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        txtTongTien1 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtTimKiem1 = new javax.swing.JTextField();
        btnTimKH = new javax.swing.JButton();
        btnThemKH = new javax.swing.JButton();
        cbbChonKH2 = new javax.swing.JButton();
        btnSuaKH = new javax.swing.JButton();
        btnLamMoiLKH = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pnlCards = new javax.swing.JPanel();
        pnlBanHang = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnSuaGH = new javax.swing.JButton();
        btnXoaSanPham = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDanhSachSP = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        txtTenKH1 = new javax.swing.JTextField();
        jSeparator29 = new javax.swing.JSeparator();
        jLabel58 = new javax.swing.JLabel();
        txtSDTKH1 = new javax.swing.JTextField();
        jSeparator30 = new javax.swing.JSeparator();
        btnChonKH1 = new javax.swing.JButton();
        jSeparator35 = new javax.swing.JSeparator();
        jLabel59 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        txtCanThanhToan = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        cbbPTTT = new javax.swing.JComboBox<>();
        jLabel63 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JLabel();
        jSeparator33 = new javax.swing.JSeparator();
        btnTaoHoaDon = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        cbbKhuyenMai = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnBanHang = new javax.swing.JButton();
        btnLichSu = new javax.swing.JButton();
        btnSanPham = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();
        btnKhuyenMai = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        btnDoiMatKhau = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        txtNguoiDung = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jLabel55.setText("Tên KH");

        txtTenKH.setBackground(new java.awt.Color(222, 231, 227));
        txtTenKH.setText("Vãng lai");
        txtTenKH.setBorder(null);

        jLabel56.setText("SĐT");

        txtSDTKH.setBackground(new java.awt.Color(222, 231, 227));
        txtSDTKH.setBorder(null);

        btnChonKH.setText("Chọn KH");
        btnChonKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKHActionPerformed(evt);
            }
        });

        KhachHangDialog.setMinimumSize(new java.awt.Dimension(880, 620));

        jPanel7.setBackground(new java.awt.Color(222, 231, 227));
        jPanel7.setMaximumSize(new java.awt.Dimension(825, 520));
        jPanel7.setMinimumSize(new java.awt.Dimension(825, 520));

        jPanel8.setBackground(new java.awt.Color(222, 231, 227));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khách hàng"));

        jLabel8.setText("Mã KH");

        jLabel9.setText("Tên KH");

        jLabel13.setText("Ngày sinh");

        jLabel14.setText("SDT");

        jLabel15.setText("Địa chỉ");

        taDiaChi.setColumns(15);
        taDiaChi.setRows(5);
        jScrollPane4.setViewportView(taDiaChi);

        jLabel16.setText("Giới tính");

        rdoNam.setText("Nam");

        rdoNu.setText("Nữ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel13)
                    .addComponent(jLabel16))
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(txtTenKH2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgaySinh))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(rdoNam)
                        .addGap(18, 18, 18)
                        .addComponent(rdoNu)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtTenKH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdoNam)
                                .addComponent(rdoNu))))))
        );

        jPanel9.setBackground(new java.awt.Color(222, 231, 227));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách khách hàng"));

        tblKhachHang.setBackground(new java.awt.Color(222, 231, 227));
        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Tên KH", "Ngày sinh", "Giới Tính", "SDT", "Địa chỉ"
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked2(evt);
            }
        });
        jScrollPane5.setViewportView(tblKhachHang);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(222, 231, 227));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Lịch sử giao dịch"));

        tblHoaDonKH.setBackground(new java.awt.Color(222, 231, 227));
        tblHoaDonKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HD", "Ngày tạo", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblHoaDonKH);

        jLabel17.setText("Tổng:");

        txtTongTien1.setText("0");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTongTien1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtTongTien1))
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(222, 231, 227));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc và tìm kiếm"));

        jLabel18.setText("Tìm kiếm theo mã");

        txtTimKiem1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiem1KeyReleased2(evt);
            }
        });

        btnTimKH.setText("Tìm");
        btnTimKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnTimKH)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKH))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        btnThemKH.setText("Thêm");
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });

        cbbChonKH2.setText("Chọn");
        cbbChonKH2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbChonKH2ActionPerformed(evt);
            }
        });

        btnSuaKH.setText("Sửa");
        btnSuaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKHActionPerformed2(evt);
            }
        });

        btnLamMoiLKH.setText("Làm mới ");
        btnLamMoiLKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiLKHActionPerformed2(evt);
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
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnThemKH)
                        .addGap(18, 18, 18)
                        .addComponent(cbbChonKH2)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaKH)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoiLKH, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbChonKH2)
                        .addComponent(btnSuaKH)
                        .addComponent(btnLamMoiLKH))
                    .addComponent(btnThemKH))
                .addContainerGap())
        );

        javax.swing.GroupLayout KhachHangDialogLayout = new javax.swing.GroupLayout(KhachHangDialog.getContentPane());
        KhachHangDialog.getContentPane().setLayout(KhachHangDialogLayout);
        KhachHangDialogLayout.setHorizontalGroup(
            KhachHangDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KhachHangDialogLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        KhachHangDialogLayout.setVerticalGroup(
            KhachHangDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(153, 194, 211));
        jPanel2.setMaximumSize(new java.awt.Dimension(1000, 600));
        jPanel2.setMinimumSize(new java.awt.Dimension(1000, 600));

        pnlCards.setBackground(new java.awt.Color(222, 231, 227));
        pnlCards.setMaximumSize(new java.awt.Dimension(825, 520));
        pnlCards.setLayout(new java.awt.CardLayout());

        pnlBanHang.setBackground(new java.awt.Color(222, 231, 227));
        pnlBanHang.setMaximumSize(new java.awt.Dimension(825, 520));
        pnlBanHang.setMinimumSize(new java.awt.Dimension(825, 520));

        jPanel3.setBackground(new java.awt.Color(222, 231, 227));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

        tblGioHang.setBackground(new java.awt.Color(222, 231, 227));
        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGioHang);

        btnSuaGH.setText("Sửa giỏ hàng");
        btnSuaGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaGHActionPerformed(evt);
            }
        });

        btnXoaSanPham.setText("Xoá sản phẩm");
        btnXoaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoaSanPham)
                .addGap(61, 61, 61)
                .addComponent(btnSuaGH)
                .addGap(160, 160, 160))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaGH)
                    .addComponent(btnXoaSanPham)))
        );

        jPanel4.setBackground(new java.awt.Color(222, 231, 227));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hoá đơn"));

        tblHoaDon.setBackground(new java.awt.Color(222, 231, 227));
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã HĐ", "Tên NV", "Ngày tạo", "Trạng thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(222, 231, 227));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        tblDanhSachSP.setBackground(new java.awt.Color(222, 231, 227));
        tblDanhSachSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Chất liệu", "Kích cỡ", "Màu sắc", "Thương hiệu", "Số lượng", "Đơn giá"
            }
        ));
        tblDanhSachSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachSPMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDanhSachSP);

        jLabel27.setText("Tìm theo mã");

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        btnThem.setText("+");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTim)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(222, 231, 227));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Tạo hóa đơn"));

        jLabel57.setText("Tên KH");

        txtTenKH1.setBackground(new java.awt.Color(222, 231, 227));
        txtTenKH1.setText("Khách lẻ");
        txtTenKH1.setBorder(null);

        jLabel58.setText("SĐT");

        txtSDTKH1.setBackground(new java.awt.Color(222, 231, 227));
        txtSDTKH1.setBorder(null);

        btnChonKH1.setText("Chọn KH");
        btnChonKH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKH1ActionPerformed(evt);
            }
        });

        jLabel59.setText("Tổng tiền: ");

        txtTongTien.setText("0");

        jLabel60.setText("Khuyến Mãi (%):");

        jLabel61.setText("Cần thanh toán:");

        txtCanThanhToan.setText("0");

        jLabel62.setText("Hình thức");

        cbbPTTT.setBackground(new java.awt.Color(222, 231, 227));
        cbbPTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "chọn...", "Tiền mặt", "Quét mã" }));

        jLabel63.setText("Tiền khách đưa:");

        txtTienKhachDua.setBackground(new java.awt.Color(222, 231, 227));
        txtTienKhachDua.setBorder(null);
        txtTienKhachDua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKhachDuaCaretUpdate(evt);
            }
        });

        jLabel64.setText("Tiền thừa:");

        txtTienThua.setText("0");

        btnTaoHoaDon.setText("Tạo HD");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        btnHuy.setText("Huỷ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/mua.png"))); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        cbbKhuyenMai.setBackground(new java.awt.Color(222, 231, 227));
        cbbKhuyenMai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn..." }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator35))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel63)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTienKhachDua))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel64)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jSeparator33)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel60)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbbKhuyenMai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel62)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbbPTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel61)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtCanThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel59)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                                        .addComponent(btnChonKH1)
                                                        .addGap(44, 44, 44)))))
                                        .addGap(0, 5, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(17, 17, 17))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel58)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSDTKH1)
                                    .addComponent(jSeparator30)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator29)
                                    .addComponent(txtTenKH1))))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(txtTenKH1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator29, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(txtSDTKH1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator30, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChonKH1)
                .addGap(12, 12, 12)
                .addComponent(jSeparator35, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(txtTongTien))
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(cbbKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(txtCanThanhToan))
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(cbbPTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel63)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(txtTienThua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator33, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBanHangLayout = new javax.swing.GroupLayout(pnlBanHang);
        pnlBanHang.setLayout(pnlBanHangLayout);
        pnlBanHangLayout.setHorizontalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBanHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlBanHangLayout.setVerticalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBanHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlBanHangLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnlCards.add(pnlBanHang, "pnlBanHang");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Tên shop");

        btnBanHang.setBackground(new java.awt.Color(153, 194, 211));
        btnBanHang.setText("Bán hàng");
        btnBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangActionPerformed(evt);
            }
        });

        btnLichSu.setBackground(new java.awt.Color(153, 194, 211));
        btnLichSu.setText("Lịch sử");
        btnLichSu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLichSuActionPerformed(evt);
            }
        });

        btnSanPham.setBackground(new java.awt.Color(153, 194, 211));
        btnSanPham.setText("Sản phẩm");
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        btnKhachHang.setBackground(new java.awt.Color(153, 194, 211));
        btnKhachHang.setText("Khách hàng");
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });

        btnKhuyenMai.setBackground(new java.awt.Color(153, 194, 211));
        btnKhuyenMai.setText("Khuyến mại");
        btnKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhuyenMaiActionPerformed(evt);
            }
        });

        btnNhanVien.setBackground(new java.awt.Color(153, 194, 211));
        btnNhanVien.setText("Nhân viên");
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });

        btnThongKe.setBackground(new java.awt.Color(153, 194, 211));
        btnThongKe.setText("Thống kê");
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        btnDoiMatKhau.setBackground(new java.awt.Color(153, 194, 211));
        btnDoiMatKhau.setText("Đổi mật khẩu");
        btnDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMatKhauActionPerformed(evt);
            }
        });

        btnDangXuat.setBackground(new java.awt.Color(153, 194, 211));
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        jLabel30.setText("Người dùng:");

        txtNguoiDung.setText("nv03");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/shop.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnKhuyenMai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThongKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDoiMatKhau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnDangXuat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(37, 37, 37)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnlCards, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(txtNguoiDung))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCards, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1007, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 612, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangActionPerformed
        try {
            pnlCards.removeAll();
            pnlCards.add(pnlBanHang);
            pnlCards.repaint();
            pnlCards.revalidate();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnBanHangActionPerformed

    private void btnLichSuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLichSuActionPerformed
        try {
            pnlCards.removeAll();
            pnlCards.add(new LichSuJPanel());
            pnlCards.repaint();
            pnlCards.revalidate();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnLichSuActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        try {
            pnlCards.removeAll();
            pnlCards.add(new SPCTJPanel());
            pnlCards.repaint();
            pnlCards.revalidate();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        try {
            pnlCards.removeAll();
            pnlCards.add(new KhachHangJPanel());
            pnlCards.repaint();
            pnlCards.revalidate();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhuyenMaiActionPerformed
        try {
            pnlCards.removeAll();
            pnlCards.add(new KhuyenMaiJPanel());
            pnlCards.repaint();
            pnlCards.revalidate();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnKhuyenMaiActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        try {
            pnlCards.removeAll();
            pnlCards.add(new NhanVienJPanel());
            pnlCards.repaint();
            pnlCards.revalidate();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        try {
            pnlCards.removeAll();
            pnlCards.add(new ThongKeJPanel());
            pnlCards.repaint();
            pnlCards.revalidate();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauActionPerformed
        try {
            pnlCards.removeAll();
            pnlCards.add(new DoiMatKhauJPanel());
            pnlCards.repaint();
            pnlCards.revalidate();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnDoiMatKhauActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        try {
            this.dispose();
            new LoginJFrame().setVisible(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked

    }//GEN-LAST:event_tblGioHangMouseClicked

    private void btnSuaGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaGHActionPerformed
        int selectedRow = tblGioHang.getSelectedRow();
        int rowSP = tblDanhSachSP.getSelectedRow();
        int RowMaHD = tblHoaDon.getSelectedRow();
        String NhapSLNew = JOptionPane.showInputDialog(this, "Nhập số lượng mới:");
        String MaHDCanUpdate = tblHoaDon.getValueAt(RowMaHD, 0).toString();
        String LaySLTon = tblDanhSachSP.getValueAt(rowSP, 6).toString();
        String MaSPCanUpdate = tblGioHang.getValueAt(selectedRow, 0).toString();
        Integer SLTon = SPCTService.getAll().stream().filter(i -> i.getMaSPCT().equalsIgnoreCase(MaSPCanUpdate)).findFirst().get().getSoLuongTon();
        if ((Integer.parseInt(LaySLTon) - Integer.parseInt(NhapSLNew)) == 0) {
            spctrp.updateTrangThaiSP(MaSPCanUpdate, 0);
        } else if ((Integer.parseInt(LaySLTon) < Integer.parseInt(NhapSLNew))) {
            JOptionPane.showMessageDialog(this, "Số lượng tồn không đủ.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        if (selectedRow != -1) {
            hoaDonService.UpdateSPGH(MaHDCanUpdate, MaSPCanUpdate, Integer.parseInt(NhapSLNew), SLTon);
        } else {
            // Hiển thị thông báo nếu không có sản phẩm nào được chọn
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm trong giỏ hàng để sửa số lượng.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        LoadTableSanPham();
        LoadHoaDonCho(MaHDCanUpdate);
    }//GEN-LAST:event_btnSuaGHActionPerformed

    private void btnXoaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSanPhamActionPerformed
        int selectedRow = tblGioHang.getSelectedRow();
        int rowHD = tblHoaDon.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "chọn 1 sản phẩm trong giỏ hàng để xoá");
            return;
        }
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "chọn hoá đơn bạn muốn xoá sản phẩm");
            return;
        }
        String MaHD = tblHoaDon.getValueAt(rowHD, 0).toString();
        if (selectedRow != -1) {
            String MaSPCanXoa = tblGioHang.getValueAt(selectedRow, 0).toString();
            String SLGH = tblGioHang.getValueAt(selectedRow, 2).toString();
            Integer SLTon = SPCTService.getAll().stream().filter(i -> i.getMaSPCT().equalsIgnoreCase(MaSPCanXoa)).findFirst().get().getSoLuongTon();
            hoaDonService.DeleteSPGH(MaHD, MaSPCanXoa, Integer.parseInt(SLGH), SLTon);
        } else {
            // Hiển thị thông báo nếu không có sản phẩm nào được chọn
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm trong giỏ hàng để xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        LoadHoaDonCho(MaHD);
        LoadTableSanPham();
    }//GEN-LAST:event_btnXoaSanPhamActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int row = tblHoaDon.getSelectedRow();
        if (row == -1) {
            return;
        }

        String MaHD = tblHoaDon.getValueAt(row, 0).toString();

        hoaDonService.HoaDonCho(MaHD);
        LoadHoaDonCho(MaHD);
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void tblDanhSachSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachSPMouseClicked

    }//GEN-LAST:event_tblDanhSachSPMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased

    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed

    }//GEN-LAST:event_btnTimActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        int rowSP = tblDanhSachSP.getSelectedRow();
        int rowHD = tblHoaDon.getSelectedRow();
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn hóa đơn để thêm sản phẩm!");
            return;
        }
        SPCTVM SPCTVM = new SPCTVM();
        String MaSPCT = tblDanhSachSP.getValueAt(tblDanhSachSP.getSelectedRow(), 0).toString();
        SPCT spct = spctrp.searchbyMaSp(tblDanhSachSP.getValueAt(rowSP, 0).toString());
        boolean check = false;
        int row = tblHoaDon.getSelectedRow();
        String MaHD = tblHoaDon.getValueAt(row, 0).toString();
        if (rowSP != -1) {
            String TenSP = tblDanhSachSP.getValueAt(tblDanhSachSP.getSelectedRow(), 1).toString();
            float DonGia = Float.parseFloat(tblDanhSachSP.getValueAt(tblDanhSachSP.getSelectedRow(), 7).toString());
            // Hiển thị hộp thoại nhập số lượng
            String NhapSL = JOptionPane.showInputDialog(this, "Nhập số lượng:", "Nhập số lượng", JOptionPane.QUESTION_MESSAGE);
            String LaySLTon = tblDanhSachSP.getValueAt(rowSP, 6).toString();
            if ((Integer.parseInt(LaySLTon) - Integer.parseInt(NhapSL)) == 0) {
                spctrp.updateTrangThaiSP(MaSPCT, 0);
            }
            System.out.println(NhapSL.length());
            if (NhapSL.trim().length() != 0) {
                try {
                    int soLuong = Integer.parseInt(NhapSL);
                    if (soLuong > spct.getSoLuongTon()) {
                        JOptionPane.showMessageDialog(this, "Số lượng sản phẩm trong kho không đủ", "Thao tác không thành công", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (soLuong == 0) {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập lại số lượng", "Thao tác không thành công", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (soLuong > 0) {
                        // Thực hiện thêm sản phẩm vào giỏ hàng
                        SPCTVM.setMaSPCT(MaSPCT);
                        SPCTVM.setTenSP(TenSP);
                        if (hoaDonRes.findMaaHDCtBySpct(MaSPCT, MaHD) != null) {
                            SPCTVM.setSoLuongTon(soLuong + hoaDonRes.findHdctByMaHdct(MaSPCT, MaHD));
                        } else {
                            SPCTVM.setSoLuongTon(soLuong);
                        }
                        SPCTVM.setDonGia(DonGia);
                        check = UpdateLaiGioHang(MaSPCT, TenSP, soLuong, DonGia, MaHD);
                        UpdateLaiSanpham(MaSPCT, soLuong);
                    } else {
                        JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ.");
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng là một số nguyên dương.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để thêm vào giỏ hàng.");
            return;
        }

        if (!check) {

            Sps.put(tblGioHang.getValueAt(tblGioHang.getRowCount() - 1, 0).toString(), Integer.parseInt(tblGioHang.getValueAt(tblGioHang.getRowCount() - 1, 2).toString()));
        }

        String TenNV = "hai";
        String TenKH = txtTenKH.getText();
        int TrangThaiHD = 1;

        HoaDon hd = new HoaDon();
        hd.setMaHD(MaHD);
        hd.setTenNV(TenNV);
        hd.setTenKH(TenKH);
        hd.setTrangThaiHD(TrangThaiHD);
        listSPCT.add(SPCTVM);

        hoaDonService.add(hd, Sps);

        Sps.clear();
        LoadHoaDonCho(MaHD);
        LoadTableSanPham();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnChonKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKHActionPerformed
//        KhachHangDialog.setVisible(true);
//        KhachHangDialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnChonKHActionPerformed

    private void txtTienKhachDuaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienKhachDuaCaretUpdate
        try {
            Double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText().trim());
            Double CanThanhToan = Double.parseDouble(txtCanThanhToan.getText());
            Double TienThua = tienKhachDua - CanThanhToan;
            DecimalFormat format = new DecimalFormat("#,###");
            txtTienThua.setText(String.valueOf(format.format(TienThua)));

        } catch (Exception e) {

        }
    }//GEN-LAST:event_txtTienKhachDuaCaretUpdate

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        //      0: trang thai cho, 1: trang thai thanh cong, 2: trang thai huy
        SessionData.maHD = new Random().nextInt(10000) + "";
        hoaDonRes.add(new HoaDon(SessionData.maHD, 0, new Date()));
        LoadTableHoaDon();
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        int selectedRow = tblGioHang.getSelectedRow();
        int rowHD = tblHoaDon.getSelectedRow();
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "chọn hoá đơn bạn muốn hủy");
            return;
        }
        String MaHD = tblHoaDon.getValueAt(rowHD, 0).toString();
        String LyDoHuy = JOptionPane.showInputDialog(this, "Nhập lý do hủy:");
        if (LyDoHuy.trim().length() != 0) {
            if (tblGioHang.getRowCount() != 0) {
                for (int i = 0; i < tblGioHang.getRowCount(); i++) {
                    String MaSPCT = tblGioHang.getValueAt(i, 0).toString();
                    Integer SL = Integer.parseInt(tblGioHang.getValueAt(i, 2).toString());
                    Integer SLTon = SPCTService.getAll().stream().filter(j -> j.getMaSPCT().equalsIgnoreCase(MaSPCT)).findFirst().get().getSoLuongTon();
                    hoaDonService.HuyThanhToan(MaHD, LyDoHuy, SL, SLTon, MaSPCT);
                }
            } else {
                hoaDonService.HuyThanhToan(MaHD, LyDoHuy, null, null, null);
            }
        }
        reset();
        LoadTableHoaDon();
        LoadTableSanPham();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        int row = tblHoaDon.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn hoá đơn bạn muốn thanh toán");
            return;
        }

        if (txtTienKhachDua.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tiền khách đưa không được để trống");
            return;
        }

        if (tblGioHang.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Hóa đơn chưa có sản phẩm");
            return;
        }

        try {
            if (Double.parseDouble(txtTienKhachDua.getText()) < Double.parseDouble(txtCanThanhToan.getText())) {
                JOptionPane.showMessageDialog(this, "Tiền khách đưa chưa đủ");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tiền khách đưa không hợp lệ");
            return;
        }

        for (int j = 0; j < tblGioHang.getRowCount(); j++) {
            Sps.put(tblGioHang.getValueAt(j, 0).toString(), Integer.parseInt(tblGioHang.getValueAt(j, 2).toString()));
        }

        String MaHD = tblHoaDon.getValueAt(row, 0).toString();
        String TongTien = txtCanThanhToan.getText().trim();
        int TrangThaiHD = 1;

        HoaDon hd = new HoaDon();
        hd.setMaHD(MaHD);
        hd.setTongTien(Float.valueOf(TongTien));
        hd.setTrangThaiHD(TrangThaiHD);

        hoaDonService.updateTrangThaiHoaDon(MaHD, TrangThaiHD, Float.valueOf(TongTien), MaHD);
        LoadTableHoaDon();
        reset();
        JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công!");
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed

    }//GEN-LAST:event_btnThemKHActionPerformed

    private void btnSuaKHActionPerformed2(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKHActionPerformed2
        KhachHang kh = new KhachHang();
        // Lấy thông tin từ giao diện và thiết lập các giá trị cho đối tượng KhachHang
        kh.setMaKH(txtMaKH.getText());
        kh.setTenKH(txtTenKH.getText());

        kh.setNgaySinh(txtNgaySinh.getText());
        kh.setGioiTinh(rdoNam.isSelected() == true ? 1 : 0);
        kh.setSdt(txtSdt.getText());
        kh.setDiaChi(taDiaChi.getText());

        service.update(kh);
        loadTableKhachHang();
        xoa();
    }//GEN-LAST:event_btnSuaKHActionPerformed2

    private void btnLamMoiLKHActionPerformed2(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiLKHActionPerformed2

    }//GEN-LAST:event_btnLamMoiLKHActionPerformed2

    private void tblKhachHangMouseClicked2(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked2
        index = tblKhachHang.getSelectedRow();
        showTable(index);
        if (index != -1) {
            String maKH = tblKhachHang.getValueAt(index, 0).toString(); // Lấy mã khách hàng từ bảng
            showHoaDonHistory(maKH); // Gọi hàm để hiển thị lịch sử hóa đơn của khách hàng được chọn
        }
    }//GEN-LAST:event_tblKhachHangMouseClicked2

    private void txtTimKiem1KeyReleased2(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiem1KeyReleased2
        String keyword = txtTimKiem.getText().trim();
        if (keyword != null) {
            tableModel = (DefaultTableModel) tblKhachHang.getModel();
            tableModel.setRowCount(0);
            ArrayList<KhachHang> list = service.search(keyword);
            for (KhachHang kh : list) {
                tableModel.addRow(new Object[]{
                    kh.getMaKH(),
                    kh.getTenKH(),
                    kh.getNgaySinh(),
                    kh.getGioiTinh(),
                    kh.getSdt(),
                    kh.getDiaChi()
                });
            }
        }
    }//GEN-LAST:event_txtTimKiem1KeyReleased2

    private void btnTimKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKHActionPerformed

    private void btnChonKH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKH1ActionPerformed
        KhachHangDialog.setVisible(true);
        KhachHangDialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnChonKH1ActionPerformed

    private void cbbChonKH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbChonKH2ActionPerformed
        // TODO add your handling code here:
            SessionData.sdtKH = new KhachHangVM(tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 0).toString()
                , tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 1).toString()
                , tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 4).toString());
            
            txtTenKH1.setText(SessionData.sdtKH.getTenKH());
            txtSDTKH1.setText(SessionData.sdtKH.getSdt());
            KhachHangDialog.setVisible(false);
    }//GEN-LAST:event_cbbChonKH2ActionPerformed
    public Boolean UpdateLaiGioHang(String MaSP, String TenSP, int SoLuong, float DonGia, String mahd) {
        if (tblGioHang.getRowCount() == 0) {
            dtmgh.addRow(new Object[]{
                MaSP,
                TenSP,
                SoLuong,
                DonGia,
                new BigDecimal(SoLuong * DonGia)
            });
            return false;
        }

        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            String MaSPGH = tblGioHang.getValueAt(i, 0).toString();
            if (MaSPGH.equals(MaSP)) {

                int SoLuongGH = Integer.parseInt(tblGioHang.getValueAt(i, 2).toString());
                dtmgh.setValueAt((SoLuongGH + SoLuong), i, 2);
                float donGia = Float.parseFloat(tblGioHang.getValueAt(i, 3).toString());
                dtmgh.setValueAt((SoLuongGH + SoLuong) * donGia, i, 4);
                hoaDonRes.updateSlHdCT(MaSPGH, Integer.parseInt(dtmgh.getValueAt(i, 2).toString()),
                        Double.parseDouble(dtmgh.getValueAt(i, 3).toString()), mahd);

                return true; // exit the method after updating the existing row
            }
        }

        // If the product is not found, add a new row
        dtmgh.addRow(new Object[]{
            MaSP,
            TenSP,
            SoLuong,
            DonGia,
            SoLuong * DonGia
        });
        return false;
    }

    public Boolean UpdateLaiSanpham(String MaCTSP, int SoLuong) {
        for (int i = 0; i < tblDanhSachSP.getRowCount(); i++) {
            String MaSP = tblDanhSachSP.getValueAt(i, 0).toString();
            if (MaSP.equals(MaCTSP)) {
                int SoLuongSP = Integer.parseInt(tblDanhSachSP.getValueAt(i, 6).toString());
                tblDanhSachSP.setValueAt(SoLuongSP - SoLuong, i, 6);
                new SPCTRepos().updateSL(MaSP, SoLuongSP - SoLuong);
                break;
            }
        }
        return true;
    }

    public Boolean UpdateSoLuongTrongGioHang(String MaSP) {
        int SoLuongCu = 0; // Số lượng hiện tại của sản phẩm trong giỏ hàng

        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            String MaSPTrongGioHang = tblGioHang.getValueAt(i, 0).toString();
            if (MaSPTrongGioHang.equals(MaSP)) {
                SoLuongCu = Integer.parseInt(tblGioHang.getValueAt(i, 2).toString()); // Lấy số lượng hiện tại
                String NhapSLNew = JOptionPane.showInputDialog(this, "Nhập số lượng mới:");

                if (NhapSLNew != null && !NhapSLNew.isEmpty()) {
                    try {
                        int SoLuongMoi = Integer.parseInt(NhapSLNew);
                        if (SoLuongMoi > 0) {
                            // Cập nhật số lượng mới trong bảng giỏ hàng
                            tblGioHang.setValueAt(SoLuongMoi, i, 2);

                            // Gọi phương thức cập nhật lại bảng sản phẩm với mã và số lượng đã lưu trữ
                            UpdateSauSua(MaSP, SoLuongCu, SoLuongMoi);
                        } else {
                            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ.");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng là một số nguyên dương.");
                    }
                }
                break; // Thoát sau khi sửa số lượng
            }
        }
        return true;
    }

    public void UpdateSauSua(String MaCTSP, int SoLuongCu, int SoLuongMoi) {
        for (int i = 0; i < tblDanhSachSP.getRowCount(); i++) {
            String maSP = tblDanhSachSP.getValueAt(i, 0).toString();
            if (maSP.equals(MaCTSP)) {
                int SoLuongCon = Integer.parseInt(tblDanhSachSP.getValueAt(i, 6).toString());
                int SoLuongCapNhat = SoLuongCon + (SoLuongCu - SoLuongMoi);
                tblDanhSachSP.setValueAt(SoLuongCapNhat, i, 6);
                break;
            }
        }
    }

    public void XoaSPGioHang(String MaSP) {
        int SoLuongXoa = 0; // Số lượng sẽ được lưu trữ từ bảng giỏ hàng

        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            String MaSPTrongGioHang = tblGioHang.getValueAt(i, 0).toString();
            if (MaSPTrongGioHang.equals(MaSP)) {
                SoLuongXoa = Integer.parseInt(tblGioHang.getValueAt(i, 2).toString()); // Lưu trữ số lượng
                dtmgh.removeRow(i);
                break; // Thoát sau khi xóa hàng
            }
        }

        // Gọi phương thức cập nhật lại bảng sản phẩm với mã và số lượng đã lưu trữ
        Updatekhixoa(MaSP, SoLuongXoa);
    }

    public void Updatekhixoa(String MaCTSP, int SoLuongSP) {
        for (int i = 0; i < tblDanhSachSP.getRowCount(); i++) {
            String MaSP = tblDanhSachSP.getValueAt(i, 0).toString();
            if (MaSP.equals(MaCTSP)) {
                int SoLuongCon = Integer.parseInt(tblDanhSachSP.getValueAt(i, 6).toString());
                tblDanhSachSP.setValueAt(SoLuongSP + SoLuongCon, i, 6);
                break;
            }
        }

    }

    private void showTable(int index) {
        KhachHangVM kh = service.getAll().get(index);
        txtMaKH.setText(kh.getMaKH());
        txtTenKH.setText(kh.getTenKH());
        txtNgaySinh.setText(kh.getNgaySinh());
        System.out.println("GioiTinh: " + kh.getGioiTinh());
        if (kh.getGioiTinh().equals(1)) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtSdt.setText(kh.getSdt());
        taDiaChi.setText(kh.getDiaChi());
    }

    private void showHoaDonHistory(String maKH) {
        List<HoaDon> list = service.getLichSu(maKH);
        tableModel1HoaDon = (DefaultTableModel) tblHoaDonKH.getModel();
        tableModel1HoaDon.setRowCount(0);
        for (HoaDon hoaDon : list) {
            tableModel1HoaDon.addRow(new Object[]{
                hoaDon.getMaHD(),
                hoaDon.getNgayTao(),
                hoaDon.getTongTien()
            });
        }

    }

    void xoa() {
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtNgaySinh.setText("");
        txtSdt.setText("");
        rdoNam.isSelected();
        taDiaChi.setText("");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BanHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BanHangJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog KhachHangDialog;
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnChonKH;
    private javax.swing.JButton btnChonKH1;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnDoiMatKhau;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnKhuyenMai;
    private javax.swing.JButton btnLamMoiLKH;
    private javax.swing.JButton btnLichSu;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnSuaGH;
    private javax.swing.JButton btnSuaKH;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnTimKH;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.JButton cbbChonKH2;
    private javax.swing.JComboBox<String> cbbKhuyenMai;
    private javax.swing.JComboBox<String> cbbPTTT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    public javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator29;
    private javax.swing.JSeparator jSeparator30;
    private javax.swing.JSeparator jSeparator33;
    private javax.swing.JSeparator jSeparator34;
    private javax.swing.JSeparator jSeparator35;
    private javax.swing.JPanel pnlBanHang;
    private javax.swing.JPanel pnlCards;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTextArea taDiaChi;
    private javax.swing.JTable tblDanhSachSP;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonKH;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JLabel txtCanThanhToan;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JLabel txtNguoiDung;
    private javax.swing.JTextField txtSDTKH;
    private javax.swing.JTextField txtSDTKH1;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenKH1;
    private javax.swing.JTextField txtTenKH2;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JLabel txtTienThua;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiem1;
    private javax.swing.JLabel txtTongTien;
    private javax.swing.JLabel txtTongTien1;
    // End of variables declaration//GEN-END:variables
}
