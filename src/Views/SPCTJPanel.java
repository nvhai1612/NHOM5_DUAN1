/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views;

import DomainModel.ChatLieu;
import DomainModel.KichCo;
import DomainModel.MauSac;
import DomainModel.SPCT;
import DomainModel.SanPham;
import DomainModel.ThuongHieu;
import Repository.Impl.SPCTRepos;
import Service.Impl.ChatLieuService;
import Service.Impl.KichCoService;
import Service.Impl.MauSacService;
import Service.Impl.SPCTService;
import Service.Impl.SanPhamService;
import Service.Impl.ThuongHieuService;
import ViewModel.ChatLieuVM;
import ViewModel.KichCoVM;
import ViewModel.MauSacVM;
import ViewModel.SPCTVM;
import ViewModel.SanPhamVM;
import ViewModel.ThuongHieuVM;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class SPCTJPanel extends javax.swing.JPanel {

    private SPCTService SPCTService = new SPCTService();
    private MauSacService mauSacService = new MauSacService();
    private ChatLieuService chatLieuService = new ChatLieuService();
    private KichCoService kichCoService = new KichCoService();
    private ThuongHieuService thuongHieuService = new ThuongHieuService();
    private SanPhamService sanPhamService = new SanPhamService();
    private SPCTRepos spctrp = new SPCTRepos();

    /**
     * Creates new form SPCTJPanel
     */
    private DefaultComboBoxModel dcbbtsp;
    private DefaultComboBoxModel dcbbmsp;
    private DefaultComboBoxModel dcbbmcl;
    private DefaultComboBoxModel dcbbmkc;
    private DefaultComboBoxModel dcbbmms;
    private DefaultComboBoxModel dcbbmth;

    public SPCTJPanel() {
        initComponents();
        dcbbmcl = (DefaultComboBoxModel) cbbCL.getModel();
        dcbbmcl.addAll(chatLieuService.getAll());
        for (int i = 0; i < dcbbmcl.getSize(); i++) {
        }

        dcbbmkc = (DefaultComboBoxModel) cbbKC.getModel();
        dcbbmkc.addAll(kichCoService.getAll());
        for (int i = 0; i < dcbbmkc.getSize(); i++) {
        }

        dcbbmms = (DefaultComboBoxModel) cbbMS.getModel();
        dcbbmms.addAll(mauSacService.getAll());
        for (int i = 0; i < dcbbmms.getSize(); i++) {
        }

        dcbbmth = (DefaultComboBoxModel) cbbTH.getModel();
        dcbbmth.addAll(thuongHieuService.getAll());
        for (int i = 0; i < dcbbmth.getSize(); i++) {
        }

        dcbbtsp = (DefaultComboBoxModel) cbbSP.getModel();
        dcbbtsp.addAll(sanPhamService.getAll());
        for (int i = 0; i < dcbbtsp.getSize(); i++) {
        }

        LoadTableSPCT();
        LoadTableSP();
        LoadTableCL();
        LoadTableKC();
        LoadTableMS();
        LoadTableTH();
    }

    private void LoadTableSP() {
        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
        dtm.setRowCount(0);

        ArrayList<SanPhamVM> listSPVM = sanPhamService.getAll();

        for (SanPhamVM spvm : listSPVM) {
            dtm.addRow(new Object[]{
                spvm.getId(),
                spvm.getMaSP(),
                spvm.getTenSP(),
                spvm.getTrangThaiSP() == 1 ? "Còn hàng" : "Hết hàng",});
        }
    }

    public void LoadTableSPCT() {
        DefaultTableModel dtm = (DefaultTableModel) tblSPCT.getModel();
        dtm.setRowCount(0);

        ArrayList<SPCTVM> listSPCTVM = SPCTService.getAll();

        for (SPCTVM spctvm : listSPCTVM) {
            dtm.addRow(new Object[]{
                spctvm.getMaSPCT(),
                spctvm.getTenSP(),
                spctvm.getSoLuongTon(),
                spctvm.getNguoiTao(),
                spctvm.getTrangThaiSPCT() == 1 ? "Còn hàng" : "Hết hàng",
                spctvm.getTenKC(),
                spctvm.getTenMS(),
                spctvm.getTenTH(),
                spctvm.getTenCL(),
                spctvm.getDonGia(),});

            System.out.println(spctvm);
        }
    }

    public void LoadTableCL() {
        DefaultTableModel dtm = (DefaultTableModel) tblChatLieu.getModel();
        dtm.setRowCount(0);
        ArrayList<ChatLieuVM> ListSP = chatLieuService.getAll();

        for (ChatLieuVM clvm : ListSP) {
            dtm.addRow(new Object[]{
                clvm.getId(),
                clvm.getMaCL(),
                clvm.getTenCL(),
                clvm.getTrangThaiCL() == 1 ? "Đang hoạt động" : "Dừng hoạt động"
            });
        }
    }

    public void LoadTableKC() {
        DefaultTableModel dtm = (DefaultTableModel) tblKichCo.getModel();
        dtm.setRowCount(0);
        ArrayList<KichCoVM> Listkc = kichCoService.getAll();

        for (KichCoVM kcvm : Listkc) {
            dtm.addRow(new Object[]{
                kcvm.getId(),
                kcvm.getMaKC(),
                kcvm.getTenKC(),
                kcvm.getTrangThai() == 1 ? "Đang hoạt động" : "Dừng hoạt động"
            });
        }
    }

    public void LoadTableMS() {
        DefaultTableModel dtm = (DefaultTableModel) tblMauSac.getModel();
        dtm.setRowCount(0);
        ArrayList<MauSacVM> Listms = mauSacService.getAll();

        for (MauSacVM msvm : Listms) {
            dtm.addRow(new Object[]{
                msvm.getId(),
                msvm.getMaMS(),
                msvm.getTenMS(),
                msvm.getTrangThai() == 1 ? "Đang hoạt động" : "Dừng hoạt động"
            });
        }
    }

    public void LoadTableTH() {
        DefaultTableModel dtm = (DefaultTableModel) tblThuongHieu.getModel();
        dtm.setRowCount(0);
        ArrayList<ThuongHieuVM> Listth = thuongHieuService.getAll();

        for (ThuongHieuVM thvm : Listth) {
            dtm.addRow(new Object[]{
                thvm.getId(),
                thvm.getMaTH(),
                thvm.getTenTH(),
                thvm.getTrangThaiTH() == 1 ? "Đang hoạt động" : "Dừng hoạt động"
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        SanPhamDialog = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        rdoDangHDSP = new javax.swing.JRadioButton();
        rdoDungHDSP = new javax.swing.JRadioButton();
        btnThemSP = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        btnLamMoiSP = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        txtSearchByMa = new javax.swing.JTextField();
        btnTimSP = new javax.swing.JButton();
        rdoLocDangHDSP = new javax.swing.JRadioButton();
        rdoLocDungHDSP = new javax.swing.JRadioButton();
        btnMoiSP = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        KichCoDialog = new javax.swing.JDialog();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtMaKC = new javax.swing.JTextField();
        txtTenKC = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        rdoDangHDKC = new javax.swing.JRadioButton();
        rdoDungHDKC = new javax.swing.JRadioButton();
        btnThemKC = new javax.swing.JButton();
        btnSuaKC = new javax.swing.JButton();
        btnLMKC = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        txtSearchByTenKC = new javax.swing.JTextField();
        btnTimKC = new javax.swing.JButton();
        rdoLocDangHDKC = new javax.swing.JRadioButton();
        rdoLocDungHDKC = new javax.swing.JRadioButton();
        btnMoiKC = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblKichCo = new javax.swing.JTable();
        MauSacDialog = new javax.swing.JDialog();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtMaMS = new javax.swing.JTextField();
        txtTenMS = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        rdoDangHDMS = new javax.swing.JRadioButton();
        rdoDungHDMS = new javax.swing.JRadioButton();
        btnThemMS = new javax.swing.JButton();
        btnSuaMS = new javax.swing.JButton();
        btnLamMoiMS = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        txtSearchbyTenMS = new javax.swing.JTextField();
        btnTimMS = new javax.swing.JButton();
        rdoLocDangHDMS = new javax.swing.JRadioButton();
        rdoLocDungHDMS = new javax.swing.JRadioButton();
        btnMoiMS = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblMauSac = new javax.swing.JTable();
        ThuongHieuDialog = new javax.swing.JDialog();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtMaTH = new javax.swing.JTextField();
        txtTenTH = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        rdoDangHDTH = new javax.swing.JRadioButton();
        rdoDungHDTH = new javax.swing.JRadioButton();
        btnThemTH = new javax.swing.JButton();
        btnSuaTH = new javax.swing.JButton();
        btnLMTH = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        txtSearchByTenTH = new javax.swing.JTextField();
        btnTimTH = new javax.swing.JButton();
        rdoLocDangHDTH = new javax.swing.JRadioButton();
        rdoLocDungHDTH = new javax.swing.JRadioButton();
        btnMoiTH = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblThuongHieu = new javax.swing.JTable();
        ChatLieuDialog = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtMaCL = new javax.swing.JTextField();
        txtTenCL = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        rdoDangHDCL = new javax.swing.JRadioButton();
        rdoDungHDCL = new javax.swing.JRadioButton();
        btnThemCL = new javax.swing.JButton();
        btnSuaCL = new javax.swing.JButton();
        btnLMCL = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        txtSearchByTenCL = new javax.swing.JTextField();
        btnTimCL = new javax.swing.JButton();
        rdoLocDangHDCL = new javax.swing.JRadioButton();
        rdoLocDungHDCL = new javax.swing.JRadioButton();
        btnMoiCL = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblChatLieu = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        buttonGroup10 = new javax.swing.ButtonGroup();
        buttonGroup11 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaSPCT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbbSP = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtSLTon = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNguoiTao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        rdoConHang = new javax.swing.JRadioButton();
        rdoHetHang = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        cbbKC = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbbMS = new javax.swing.JComboBox<>();
        cbbTH = new javax.swing.JComboBox<>();
        cbbCL = new javax.swing.JComboBox<>();
        txtDonGia = new javax.swing.JTextField();
        btnTenSP = new javax.swing.JButton();
        btnKichCo = new javax.swing.JButton();
        btnMauSac = new javax.swing.JButton();
        btnTH = new javax.swing.JButton();
        btnCL = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSPCT = new javax.swing.JTable();
        txtSearchMaSPCT = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnTimSPCT = new javax.swing.JButton();
        btnThemSPCT = new javax.swing.JButton();
        btnSuaSPCT = new javax.swing.JButton();
        btnXoaSPCT = new javax.swing.JButton();
        btnLamMoiSPCT = new javax.swing.JButton();

        SanPhamDialog.setMinimumSize(new java.awt.Dimension(380, 600));

        jPanel4.setBackground(new java.awt.Color(222, 229, 226));

        jPanel5.setBackground(new java.awt.Color(222, 229, 226));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "QUẢN LÝ SẢN PHẨM", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        jPanel6.setBackground(new java.awt.Color(222, 229, 226));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel12.setText("Mã SP :");

        jLabel13.setText("Tên SP :");

        txtTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSPActionPerformed(evt);
            }
        });

        jLabel14.setText("Trạng thái :");

        buttonGroup2.add(rdoDangHDSP);
        rdoDangHDSP.setText("Còn hàng");

        buttonGroup2.add(rdoDungHDSP);
        rdoDungHDSP.setText("Hết hàng");

        btnThemSP.setText("Thêm");
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        btnSuaSP.setText("Sửa");
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });

        btnLamMoiSP.setText("Làm mới");
        btnLamMoiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                    .addComponent(txtMaSP)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoDangHDSP)
                                .addGap(18, 18, 18)
                                .addComponent(rdoDungHDSP)))
                        .addContainerGap(33, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnThemSP)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaSP)
                        .addGap(27, 27, 27)
                        .addComponent(btnLamMoiSP)
                        .addGap(24, 24, 24))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(rdoDangHDSP)
                    .addComponent(rdoDungHDSP))
                .addGap(8, 8, 8)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoiSP)
                    .addComponent(btnSuaSP)
                    .addComponent(btnThemSP))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(222, 229, 226));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnTimSP.setText("Tìm");

        buttonGroup3.add(rdoLocDangHDSP);
        rdoLocDangHDSP.setText("Đang hoạt động");

        buttonGroup3.add(rdoLocDungHDSP);
        rdoLocDungHDSP.setText("Dừng hoạt động");

        btnMoiSP.setText("Mới");

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Mã SP", "Tên SP", "Trạng thái"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(rdoLocDangHDSP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoLocDungHDSP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnMoiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(txtSearchByMa, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchByMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoLocDangHDSP)
                    .addComponent(rdoLocDungHDSP)
                    .addComponent(btnMoiSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout SanPhamDialogLayout = new javax.swing.GroupLayout(SanPhamDialog.getContentPane());
        SanPhamDialog.getContentPane().setLayout(SanPhamDialogLayout);
        SanPhamDialogLayout.setHorizontalGroup(
            SanPhamDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SanPhamDialogLayout.setVerticalGroup(
            SanPhamDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        KichCoDialog.setMinimumSize(new java.awt.Dimension(380, 600));

        jPanel17.setBackground(new java.awt.Color(222, 229, 226));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "QUẢN LÝ KÍCH CỠ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        jPanel18.setBackground(new java.awt.Color(222, 229, 226));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel24.setText("Mã KC :");

        jLabel25.setText("Tên KC :");

        txtTenKC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKCActionPerformed(evt);
            }
        });

        jLabel26.setText("Trạng thái :");

        buttonGroup4.add(rdoDangHDKC);
        rdoDangHDKC.setText("Đang hoạt động");

        buttonGroup4.add(rdoDungHDKC);
        rdoDungHDKC.setText("Dừng hoạt động");

        btnThemKC.setText("Thêm");
        btnThemKC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKCActionPerformed(evt);
            }
        });

        btnSuaKC.setText("Sửa");
        btnSuaKC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKCActionPerformed(evt);
            }
        });

        btnLMKC.setText("Làm mới");
        btnLMKC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLMKCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenKC)
                            .addComponent(txtMaKC)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoDangHDKC)
                        .addGap(18, 18, 18)
                        .addComponent(rdoDungHDKC))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(btnThemKC)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaKC)
                        .addGap(18, 18, 18)
                        .addComponent(btnLMKC)
                        .addGap(21, 21, 21)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtMaKC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtTenKC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(rdoDangHDKC)
                    .addComponent(rdoDungHDKC))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemKC)
                    .addComponent(btnSuaKC)
                    .addComponent(btnLMKC))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel19.setBackground(new java.awt.Color(222, 229, 226));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnTimKC.setText("Tìm");

        buttonGroup5.add(rdoLocDangHDKC);
        rdoLocDangHDKC.setText("Đang hoạt động");

        buttonGroup5.add(rdoLocDungHDKC);
        rdoLocDungHDKC.setText("Dừng hoạt động");

        btnMoiKC.setText("Mới");

        tblKichCo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Mã KC", "Tên KC", "Trạng thái"
            }
        ));
        tblKichCo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKichCoMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblKichCo);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(rdoLocDangHDKC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoLocDungHDKC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnMoiKC, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(txtSearchByTenKC, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTimKC, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchByTenKC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoLocDangHDKC)
                    .addComponent(rdoLocDungHDKC)
                    .addComponent(btnMoiKC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout KichCoDialogLayout = new javax.swing.GroupLayout(KichCoDialog.getContentPane());
        KichCoDialog.getContentPane().setLayout(KichCoDialogLayout);
        KichCoDialogLayout.setHorizontalGroup(
            KichCoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        KichCoDialogLayout.setVerticalGroup(
            KichCoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        MauSacDialog.setMinimumSize(new java.awt.Dimension(380, 600));

        jPanel14.setBackground(new java.awt.Color(222, 229, 226));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "QUẢN LÝ MÀU SẮC", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        jPanel15.setBackground(new java.awt.Color(222, 229, 226));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel21.setText("Mã MS :");

        jLabel22.setText("Tên MS :");

        txtTenMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenMSActionPerformed(evt);
            }
        });

        jLabel23.setText("Trạng thái :");

        buttonGroup6.add(rdoDangHDMS);
        rdoDangHDMS.setText("Đang hoạt động");

        buttonGroup6.add(rdoDungHDMS);
        rdoDungHDMS.setText("Dừng hoạt động");

        btnThemMS.setText("Thêm");
        btnThemMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMSActionPerformed(evt);
            }
        });

        btnSuaMS.setText("Sửa");
        btnSuaMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaMSActionPerformed(evt);
            }
        });

        btnLamMoiMS.setText("Làm mới");
        btnLamMoiMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiMSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenMS)
                            .addComponent(txtMaMS)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoDangHDMS)
                        .addGap(18, 18, 18)
                        .addComponent(rdoDungHDMS))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(btnThemMS)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaMS)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoiMS)
                        .addGap(21, 21, 21)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtMaMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtTenMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(rdoDangHDMS)
                    .addComponent(rdoDungHDMS))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemMS)
                    .addComponent(btnSuaMS)
                    .addComponent(btnLamMoiMS))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(222, 229, 226));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnTimMS.setText("Tìm");

        buttonGroup7.add(rdoLocDangHDMS);
        rdoLocDangHDMS.setText("Đang hoạt động");

        buttonGroup7.add(rdoLocDungHDMS);
        rdoLocDungHDMS.setText("Dừng hoạt động");

        btnMoiMS.setText("Mới");

        tblMauSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Mã MS", "Tên MS", "Trạng thái"
            }
        ));
        tblMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMauSacMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblMauSac);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(rdoLocDangHDMS)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoLocDungHDMS)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnMoiMS, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(txtSearchbyTenMS, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTimMS, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchbyTenMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimMS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoLocDangHDMS)
                    .addComponent(rdoLocDungHDMS)
                    .addComponent(btnMoiMS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MauSacDialogLayout = new javax.swing.GroupLayout(MauSacDialog.getContentPane());
        MauSacDialog.getContentPane().setLayout(MauSacDialogLayout);
        MauSacDialogLayout.setHorizontalGroup(
            MauSacDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MauSacDialogLayout.setVerticalGroup(
            MauSacDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ThuongHieuDialog.setMinimumSize(new java.awt.Dimension(380, 600));

        jPanel11.setBackground(new java.awt.Color(222, 229, 226));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "QUẢN LÝ THƯƠNG HIỆU", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        jPanel12.setBackground(new java.awt.Color(222, 229, 226));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel18.setText("Mã TH :");

        jLabel19.setText("Tên TH :");

        txtTenTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenTHActionPerformed(evt);
            }
        });

        jLabel20.setText("Trạng thái :");

        buttonGroup8.add(rdoDangHDTH);
        rdoDangHDTH.setText("Đang hoạt động");

        buttonGroup8.add(rdoDungHDTH);
        rdoDungHDTH.setText("Dừng hoạt động");

        btnThemTH.setText("Thêm");
        btnThemTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTHActionPerformed(evt);
            }
        });

        btnSuaTH.setText("Sửa");
        btnSuaTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTHActionPerformed(evt);
            }
        });

        btnLMTH.setText("Làm mới");
        btnLMTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLMTHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenTH)
                            .addComponent(txtMaTH)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoDangHDTH)
                        .addGap(18, 18, 18)
                        .addComponent(rdoDungHDTH))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(btnThemTH)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaTH)
                        .addGap(18, 18, 18)
                        .addComponent(btnLMTH)
                        .addGap(21, 21, 21)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtMaTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtTenTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(rdoDangHDTH)
                    .addComponent(rdoDungHDTH))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemTH)
                    .addComponent(btnSuaTH)
                    .addComponent(btnLMTH))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(222, 229, 226));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnTimTH.setText("Tìm");

        buttonGroup9.add(rdoLocDangHDTH);
        rdoLocDangHDTH.setText("Đang hoạt động");

        buttonGroup9.add(rdoLocDungHDTH);
        rdoLocDungHDTH.setText("Dừng hoạt động");

        btnMoiTH.setText("Mới");

        tblThuongHieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Mã TH", "Tên TH", "Trạng thái"
            }
        ));
        tblThuongHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuongHieuMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblThuongHieu);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(rdoLocDangHDTH)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoLocDungHDTH)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnMoiTH, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(txtSearchByTenTH, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTimTH, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchByTenTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimTH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoLocDangHDTH)
                    .addComponent(rdoLocDungHDTH)
                    .addComponent(btnMoiTH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ThuongHieuDialogLayout = new javax.swing.GroupLayout(ThuongHieuDialog.getContentPane());
        ThuongHieuDialog.getContentPane().setLayout(ThuongHieuDialogLayout);
        ThuongHieuDialogLayout.setHorizontalGroup(
            ThuongHieuDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ThuongHieuDialogLayout.setVerticalGroup(
            ThuongHieuDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ChatLieuDialog.setMinimumSize(new java.awt.Dimension(380, 600));

        jPanel8.setBackground(new java.awt.Color(222, 229, 226));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "QUẢN LÝ CHẤT LIỆU", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        jPanel9.setBackground(new java.awt.Color(222, 229, 226));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel15.setText("Mã CL :");

        jLabel16.setText("Tên CL :");

        txtTenCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenCLActionPerformed(evt);
            }
        });

        jLabel17.setText("Trạng thái :");

        buttonGroup10.add(rdoDangHDCL);
        rdoDangHDCL.setText("Đang hoạt động");

        buttonGroup10.add(rdoDungHDCL);
        rdoDungHDCL.setText("Dừng hoạt động");

        btnThemCL.setText("Thêm");
        btnThemCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCLActionPerformed(evt);
            }
        });

        btnSuaCL.setText("Sửa");
        btnSuaCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCLActionPerformed(evt);
            }
        });

        btnLMCL.setText("Làm mới");
        btnLMCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLMCLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenCL)
                            .addComponent(txtMaCL)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoDangHDCL)
                        .addGap(18, 18, 18)
                        .addComponent(rdoDungHDCL))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(btnThemCL)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaCL)
                        .addGap(18, 18, 18)
                        .addComponent(btnLMCL)
                        .addGap(21, 21, 21)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtMaCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtTenCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(rdoDangHDCL)
                    .addComponent(rdoDungHDCL))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemCL)
                    .addComponent(btnSuaCL)
                    .addComponent(btnLMCL))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(222, 229, 226));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnTimCL.setText("Tìm");

        buttonGroup11.add(rdoLocDangHDCL);
        rdoLocDangHDCL.setText("Đang hoạt động");

        buttonGroup11.add(rdoLocDungHDCL);
        rdoLocDungHDCL.setText("Dừng hoạt động");

        btnMoiCL.setText("Mới");

        tblChatLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Mã CL", "Tên CL", "Trạng thái"
            }
        ));
        tblChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChatLieuMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblChatLieu);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(rdoLocDangHDCL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoLocDungHDCL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnMoiCL, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(txtSearchByTenCL, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTimCL, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchByTenCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimCL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoLocDangHDCL)
                    .addComponent(rdoLocDungHDCL)
                    .addComponent(btnMoiCL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ChatLieuDialogLayout = new javax.swing.GroupLayout(ChatLieuDialog.getContentPane());
        ChatLieuDialog.getContentPane().setLayout(ChatLieuDialogLayout);
        ChatLieuDialogLayout.setHorizontalGroup(
            ChatLieuDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ChatLieuDialogLayout.setVerticalGroup(
            ChatLieuDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        jPanel2.setBackground(new java.awt.Color(222, 231, 227));

        jPanel3.setBackground(new java.awt.Color(222, 231, 227));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SẢN PHẨM CHI TIẾT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        jLabel1.setText("Mã SPCT :");

        txtMaSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPCTActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên SP :");

        cbbSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "chọn.." }));

        jLabel3.setText("SL Tồn :");

        jLabel4.setText("Người Tạo :");

        jLabel5.setText("Trạng thái :");

        buttonGroup1.add(rdoConHang);
        rdoConHang.setText("Còn hàng");

        buttonGroup1.add(rdoHetHang);
        rdoHetHang.setText("Hết hàng");

        jLabel6.setText("Kích cỡ :");

        cbbKC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "chọn..." }));

        jLabel7.setText("Màu sắc :");

        jLabel8.setText("Thương hiệu :");

        jLabel9.setText("Chất liệu :");

        jLabel10.setText("Đơn giá :");

        cbbMS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "chọn..." }));

        cbbTH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "chọn..." }));

        cbbCL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "chọn..." }));

        btnTenSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tshirt.png"))); // NOI18N
        btnTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTenSPActionPerformed(evt);
            }
        });

        btnKichCo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/measurement.png"))); // NOI18N
        btnKichCo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKichCoActionPerformed(evt);
            }
        });

        btnMauSac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/wheel.png"))); // NOI18N
        btnMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMauSacActionPerformed(evt);
            }
        });

        btnTH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home.png"))); // NOI18N
        btnTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTHActionPerformed(evt);
            }
        });

        btnCL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/gift.png"))); // NOI18N
        btnCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(cbbSP, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtNguoiTao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                            .addComponent(txtSLTon, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(rdoConHang)
                                .addGap(18, 18, 18)
                                .addComponent(rdoHetHang))))
                    .addComponent(txtMaSPCT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbbTH, javax.swing.GroupLayout.Alignment.LEADING, 0, 177, Short.MAX_VALUE)
                            .addComponent(cbbMS, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbKC, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbCL, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnKichCo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnMauSac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cbbKC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKichCo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(cbbMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMauSac))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnTenSP)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbbSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cbbTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTH))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCL))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtSLTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(cbbCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoHetHang)
                            .addComponent(jLabel10)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(rdoConHang))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );

        jPanel1.setBackground(new java.awt.Color(222, 231, 227));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        tblSPCT.setBackground(new java.awt.Color(222, 231, 227));
        tblSPCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SPCT", "Tên SP", "SL Tồn", "Người tạo", "Trạng thái", "Kích cỡ", "Màu sắc", "Thương hiệu", "Chất liệu", "Đơn giá"
            }
        ));
        tblSPCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPCTMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSPCT);

        txtSearchMaSPCT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchMaSPCTKeyReleased(evt);
            }
        });

        jLabel11.setText("Tìm theo mã");

        btnTimSPCT.setText("Tìm");
        btnTimSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSPCTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchMaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchMaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(btnTimSPCT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnThemSPCT.setText("Thêm");
        btnThemSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPCTActionPerformed(evt);
            }
        });

        btnSuaSPCT.setText("Sửa");
        btnSuaSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPCTActionPerformed(evt);
            }
        });

        btnXoaSPCT.setText("Xóa");

        btnLamMoiSPCT.setText("Mới");
        btnLamMoiSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiSPCTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemSPCT)
                .addGap(18, 18, 18)
                .addComponent(btnSuaSPCT)
                .addGap(18, 18, 18)
                .addComponent(btnXoaSPCT)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoiSPCT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemSPCT)
                    .addComponent(btnSuaSPCT)
                    .addComponent(btnXoaSPCT)
                    .addComponent(btnLamMoiSPCT))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPCTActionPerformed

    }//GEN-LAST:event_txtMaSPCTActionPerformed

    private void tblSPCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPCTMouseClicked
        int row = tblSPCT.getSelectedRow();
        if (row == -1) {
            return;
        }

        if (Optional.ofNullable(tblSPCT.getValueAt(row, 4)).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được null");
            return;
        }

        String MaSPCT = tblSPCT.getValueAt(row, 0).toString();
        String TenSP = tblSPCT.getValueAt(row, 1).toString();
        String SoLuongTon = tblSPCT.getValueAt(row, 2).toString();
        String NguoiTao = tblSPCT.getValueAt(row, 3).toString();
        String TrangThaiSPCT = tblSPCT.getValueAt(row, 4).toString();
        String TenKC = tblSPCT.getValueAt(row, 5).toString();
        String TenMS = tblSPCT.getValueAt(row, 6).toString();
        String TenTH = tblSPCT.getValueAt(row, 7).toString();
        String TenCL = tblSPCT.getValueAt(row, 8).toString();
        String DonGia = tblSPCT.getValueAt(row, 9).toString();

        txtMaSPCT.setText(MaSPCT);
        dcbbtsp.setSelectedItem(sanPhamService.getAll().stream().filter(i -> i.getTenSP().equalsIgnoreCase(TenSP)).findFirst().get());
        txtSLTon.setText(SoLuongTon);
        txtNguoiTao.setText(NguoiTao);
        if (TrangThaiSPCT.equalsIgnoreCase("Còn hàng")) {
            this.rdoConHang.setSelected(true);
        } else {
            this.rdoHetHang.setSelected(true);
        }
        dcbbmcl.setSelectedItem(chatLieuService.getAll().stream().filter(i -> i.getTenCL().equalsIgnoreCase(TenCL)).findFirst().get());
        dcbbmkc.setSelectedItem(kichCoService.getAll().stream().filter(i -> i.getTenKC().equals(TenKC)).findFirst().get());
        dcbbmms.setSelectedItem(mauSacService.getAll().stream().filter(i -> i.getTenMS().equals(TenMS)).findFirst().get());
        dcbbmth.setSelectedItem(thuongHieuService.getAll().stream().filter(i -> i.getTenTH().equals(TenTH)).findFirst().get());
        txtDonGia.setText(DonGia);
    }//GEN-LAST:event_tblSPCTMouseClicked

    private void txtSearchMaSPCTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchMaSPCTKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchMaSPCTKeyReleased

    private void txtTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSPActionPerformed

    private void txtTenCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenCLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenCLActionPerformed

    private void txtTenTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenTHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenTHActionPerformed

    private void txtTenMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenMSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenMSActionPerformed

    private void txtTenKCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKCActionPerformed

    private void btnTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTenSPActionPerformed
        SanPhamDialog.setVisible(true);
        SanPhamDialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnTenSPActionPerformed

    private void btnKichCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKichCoActionPerformed
        KichCoDialog.setVisible(true);
        KichCoDialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnKichCoActionPerformed

    private void btnMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMauSacActionPerformed
        MauSacDialog.setVisible(true);
        MauSacDialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnMauSacActionPerformed

    private void btnTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTHActionPerformed
        ThuongHieuDialog.setVisible(true);
        ThuongHieuDialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnTHActionPerformed

    private void btnCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCLActionPerformed
        ChatLieuDialog.setVisible(true);
        ChatLieuDialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnCLActionPerformed

    private void btnThemSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPCTActionPerformed
        String MaSPCT = ((SanPhamVM) cbbSP.getSelectedItem()).getMaSP();
        UUID TenSP = ((SanPhamVM) cbbSP.getSelectedItem()).getId();
//        UUID TenSP = spctrp.SelectSPByTen(txtTenSP.getText());
        String SoLuongTon = txtSLTon.getText();
        if (SoLuongTon.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập số lượng tồn!");
            return;
        }
        String NguoiTao = txtNguoiTao.getText();
        int TrangThai = rdoConHang.isSelected() == true ? 1 : 0;
        UUID TenCL = ((ChatLieuVM) cbbCL.getSelectedItem()).getId();
        UUID TenKC = ((KichCoVM) cbbKC.getSelectedItem()).getId();
        UUID TenMS = ((MauSacVM) cbbMS.getSelectedItem()).getId();
        UUID TenTH = ((ThuongHieuVM) cbbTH.getSelectedItem()).getId();
        String DonGia = txtDonGia.getText();
        if (DonGia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập đơn giá!");
            return;
        }

        SPCT spct = new SPCT();
        spct.setMaSPCT(MaSPCT);
        spct.setIdSP(TenSP);
        spct.setSoLuongTon(Integer.valueOf(SoLuongTon));
        spct.setNguoiTao(NguoiTao);
        spct.setTrangThaiSPCT(TrangThai);
        spct.setIdCL(TenCL);
        spct.setIdKC(TenKC);
        spct.setIdMS(TenMS);
        spct.setIdTH(TenTH);
        spct.setDonGia(Float.valueOf(DonGia));

        this.SPCTService.add(spct);
        LamMoiSPCT();
        this.LoadTableSPCT();
    }//GEN-LAST:event_btnThemSPCTActionPerformed

    private void btnLamMoiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiSPActionPerformed
        LamMoiSP();
    }//GEN-LAST:event_btnLamMoiSPActionPerformed

    private void btnSuaSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPCTActionPerformed
        int selectedRow = tblSPCT.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm chi tiết để sửa!");
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "Xác nhận sửa!");
        if (check == JOptionPane.YES_OPTION) {
            String MaSPCT = txtMaSPCT.getText();
            UUID TenSP = ((SanPhamVM) cbbSP.getSelectedItem()).getId();
            String SoLuongTon = txtSLTon.getText();
            String NguoiTao = txtNguoiTao.getText();
            int TrangThai = rdoConHang.isSelected() == true ? 1 : 0;
            UUID TenCL = ((ChatLieuVM) cbbCL.getSelectedItem()).getId();
            UUID TenKC = ((KichCoVM) cbbKC.getSelectedItem()).getId();
            UUID TenMS = ((MauSacVM) cbbMS.getSelectedItem()).getId();
            UUID TenTH = ((ThuongHieuVM) cbbTH.getSelectedItem()).getId();
            String DonGia = txtDonGia.getText();

            SPCT spct = new SPCT();
            spct.setMaSPCT(MaSPCT);
            spct.setIdSP(TenSP);
            spct.setSoLuongTon(Integer.valueOf(SoLuongTon));
            spct.setNguoiTao(NguoiTao);
            spct.setTrangThaiSPCT(TrangThai);
            spct.setIdCL(TenCL);
            spct.setIdKC(TenKC);
            spct.setIdMS(TenMS);
            spct.setIdTH(TenTH);
            spct.setDonGia(Float.valueOf(DonGia));

            this.SPCTService.update(spct);
            LamMoiSPCT();
            this.LoadTableSPCT();
            JOptionPane.showMessageDialog(this, "Sủa thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Hủy sửa sản phẩm chi tiết!");
            return;
        }
    }//GEN-LAST:event_btnSuaSPCTActionPerformed

    private void btnTimSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSPCTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimSPCTActionPerformed

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        String MaSP = txtMaSP.getText();
        if (MaSP.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống mã!");
            return;
        }
        String TenSP = txtTenSP.getText();
        if (TenSP.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống tên sản Phẩm!");
            return;
        }
        int TrangThai = rdoDangHDSP.isSelected() == true ? 1 : 0;

        SanPham sp = new SanPham();
        sp.setMaSP(MaSP);
        sp.setTenSP(TenSP);
        sp.setTrangThaiSP(TrangThai);

        this.sanPhamService.add(sp);
        LamMoiSP();
        this.LoadTableSP();
    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
        int selectedRow = tblSanPham.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để sửa!");
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "Xác nhận sửa!");
        if (check == JOptionPane.YES_OPTION) {
            String MaSP = txtMaSP.getText();
            String TenSP = txtTenSP.getText();
            int TrangThai = rdoDangHDSP.isSelected() == true ? 1 : 0;

            SanPham sp = new SanPham();
            sp.setMaSP(MaSP);
            sp.setTenSP(TenSP);
            sp.setTrangThaiSP(TrangThai);

            this.sanPhamService.update(sp);
            LamMoiSP();
            this.LoadTableSP();
            JOptionPane.showMessageDialog(this, "Sủa thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Hủy sửa sản phẩm");
            return;
        }
    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int row = tblSanPham.getSelectedRow();
        if (row == -1) {
            return;
        }

        String MaSP = tblSanPham.getValueAt(row, 1).toString();
        String TenSP = tblSanPham.getValueAt(row, 2).toString();
        String TrangThai = tblSanPham.getValueAt(row, 3).toString();

        txtMaSP.setText(MaSP);
        txtTenSP.setText(TenSP);
        if (TrangThai.equalsIgnoreCase("Còn hàng")) {
            this.rdoDangHDSP.setSelected(true);
        } else {
            this.rdoDungHDSP.setSelected(true);
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnThemKCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKCActionPerformed
        String MaKC = txtMaKC.getText();
        if (MaKC.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống mã!");
            return;
        }
        String TenKC = txtMaKC.getText();
        if (MaKC.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống tên!");
            return;
        }
        int TrangThaiKC = rdoDangHDKC.isSelected() == true ? 1 : 0;

        KichCo kc = new KichCo();
        kc.setMaKC(MaKC);
        kc.setTenKC(TenKC);
        kc.setTrangThaiKC(TrangThaiKC);

        kichCoService.add(kc);
        LamMoiKC();
        LoadTableKC();
    }//GEN-LAST:event_btnThemKCActionPerformed

    private void btnSuaKCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKCActionPerformed
        int selectedRow = tblKichCo.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một kích cỡ để sửa!");
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "Xác nhận sửa!");
        if (check == JOptionPane.YES_OPTION) {
            String MaKC = txtMaKC.getText();
            String TenKC = txtMaKC.getText();
            int TrangThaiKC = rdoDangHDKC.isSelected() == true ? 1 : 0;

            KichCo kc = new KichCo();
            kc.setMaKC(MaKC);
            kc.setTenKC(TenKC);
            kc.setTrangThaiKC(TrangThaiKC);

            kichCoService.add(kc);
            LamMoiKC();
            LoadTableKC();
            JOptionPane.showMessageDialog(this, "Sửa thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Hủy sửa kích cỡ!");
        }
    }//GEN-LAST:event_btnSuaKCActionPerformed

    private void btnLMKCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLMKCActionPerformed
        LamMoiKC();
    }//GEN-LAST:event_btnLMKCActionPerformed

    private void tblKichCoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKichCoMouseClicked
        int row = tblKichCo.getSelectedRow();
        if (row == -1) {
            return;
        }

        String MaKC = tblKichCo.getValueAt(row, 1).toString();
        String TenKC = tblKichCo.getValueAt(row, 2).toString();
        String TrangThai = tblKichCo.getValueAt(row, 3).toString();

        txtMaKC.setText(MaKC);
        txtTenKC.setText(TenKC);
        if (TrangThai.equalsIgnoreCase("Đang Hoạt Động")) {
            this.rdoDangHDKC.setSelected(true);
        } else {
            this.rdoDungHDKC.setSelected(true);
        }
    }//GEN-LAST:event_tblKichCoMouseClicked

    private void btnThemMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMSActionPerformed
        String MaMS = txtMaMS.getText();
        if (MaMS.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống mã!");
            return;
        }
        String TenMS = txtMaMS.getText();
        if (TenMS.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống mã!");
            return;
        }
        int TrangThaiMS = rdoDangHDMS.isSelected() == true ? 1 : 0;

        MauSac ms = new MauSac();
        ms.setMaMS(MaMS);
        ms.setTenMS(TenMS);
        ms.setTrangThaiMS(TrangThaiMS);

        mauSacService.add(ms);
        LamMoiMS();
        LoadTableMS();
    }//GEN-LAST:event_btnThemMSActionPerformed

    private void btnSuaMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaMSActionPerformed
        int selectedRow = tblMauSac.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một màu sắc để sửa!");
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "Xác nhận sửa!");
        if (check == JOptionPane.YES_OPTION) {
            String MaMS = txtMaMS.getText();
            String TenMS = txtTenMS.getText();
            int TrangThaiMS = rdoDangHDMS.isSelected() == true ? 1 : 0;

            MauSac ms = new MauSac();
            ms.setMaMS(MaMS);
            ms.setTenMS(TenMS);
            ms.setTrangThaiMS(TrangThaiMS);

            mauSacService.update(ms);
            LamMoiMS();
            LoadTableMS();
            JOptionPane.showMessageDialog(this, "Sửa thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Hủy sửa màu sắc!");
        }
    }//GEN-LAST:event_btnSuaMSActionPerformed

    private void btnLamMoiMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiMSActionPerformed
        LamMoiMS();
    }//GEN-LAST:event_btnLamMoiMSActionPerformed

    private void tblMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauSacMouseClicked
        int row = tblMauSac.getSelectedRow();
        if (row == -1) {
            return;
        }

        String MaMS = tblMauSac.getValueAt(row, 1).toString();
        String TenMS = tblMauSac.getValueAt(row, 2).toString();
        String TrangThai = tblMauSac.getValueAt(row, 3).toString();

        txtMaMS.setText(MaMS);
        txtTenMS.setText(TenMS);
        if (TrangThai.equalsIgnoreCase("Đang hoạt động")) {
            this.rdoDangHDMS.setSelected(true);
        } else {
            this.rdoDungHDMS.setSelected(true);
        }
    }//GEN-LAST:event_tblMauSacMouseClicked

    private void btnThemTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTHActionPerformed
        String MaTH = txtMaTH.getText();
        if (MaTH.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống mã!");
            return;
        }
        String TenTH = txtTenTH.getText();
        if (TenTH.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống mã!");
            return;
        }
        int TrangThaiTH = rdoDangHDTH.isSelected() == true ? 1 : 0;

        ThuongHieu th = new ThuongHieu();
        th.setMaTH(MaTH);
        th.setTenTH(TenTH);
        th.setTrangThaiTH(TrangThaiTH);

        thuongHieuService.add(th);
        LamMoiTH();
        LoadTableTH();
        JOptionPane.showMessageDialog(this, "Thêm mới thành công!");
    }//GEN-LAST:event_btnThemTHActionPerformed

    private void btnSuaTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTHActionPerformed
        int selectedRow = tblThuongHieu.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một màu sắc để sửa!");
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "Xác nhận sửa!");
        if (check == JOptionPane.YES_OPTION) {
            String MaTH = txtMaTH.getText();
            String TenTH = txtTenTH.getText();
            int TrangThaiKC = rdoDangHDTH.isSelected() == true ? 1 : 0;

            ThuongHieu th = new ThuongHieu();
            th.setMaTH(MaTH);
            th.setTenTH(TenTH);
            th.setTrangThaiTH(TrangThaiKC);

            thuongHieuService.update(th);
            LamMoiTH();
            LoadTableTH();
            JOptionPane.showMessageDialog(this, "Sửa thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Hủy sửa thương hiệu!");
        }
    }//GEN-LAST:event_btnSuaTHActionPerformed

    private void btnLMTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLMTHActionPerformed
        LamMoiTH();
    }//GEN-LAST:event_btnLMTHActionPerformed

    private void tblThuongHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuongHieuMouseClicked
        int row = tblThuongHieu.getSelectedRow();
        if (row == -1) {
            return;
        }

        String MaTH = tblThuongHieu.getValueAt(row, 1).toString();
        String TenTH = tblThuongHieu.getValueAt(row, 2).toString();
        String TrangThai = tblThuongHieu.getValueAt(row, 3).toString();

        txtMaTH.setText(MaTH);
        txtTenTH.setText(TenTH);
        if (TrangThai.equalsIgnoreCase("Đang Hoạt Động")) {
            this.rdoDangHDTH.setSelected(true);
        } else {
            this.rdoDungHDTH.setSelected(true);
        }
    }//GEN-LAST:event_tblThuongHieuMouseClicked

    private void btnLamMoiSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiSPCTActionPerformed
        LamMoiSPCT();
    }//GEN-LAST:event_btnLamMoiSPCTActionPerformed

    private void tblChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChatLieuMouseClicked
        int row = tblChatLieu.getSelectedRow();
        if (row == -1) {
            return;
        }

        String MaCL = tblChatLieu.getValueAt(row, 1).toString();
        String TenCL = tblChatLieu.getValueAt(row, 2).toString();
        String TrangThai = tblChatLieu.getValueAt(row, 3).toString();

        txtMaTH.setText(MaCL);
        txtTenTH.setText(TenCL);
        if (TrangThai.equalsIgnoreCase("Đang Hoạt Động")) {
            this.rdoDangHDCL.setSelected(true);
        } else {
            this.rdoDungHDCL.setSelected(true);
        }
    }//GEN-LAST:event_tblChatLieuMouseClicked

    private void btnThemCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCLActionPerformed
        String MaCL = txtMaCL.getText();
        if (MaCL.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống mã!");
            return;
        }
        String TenCL = txtTenCL.getText();
        if (TenCL.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống mã!");
            return;
        }
        int TrangThaiCL = rdoDangHDCL.isSelected() == true ? 1 : 0;

        ChatLieu cl = new ChatLieu();
        cl.setMaCL(MaCL);
        cl.setTenCL(TenCL);
        cl.setTrangThaiCL(TrangThaiCL);

        chatLieuService.add(cl);
        LamMoiCL();
        LoadTableCL();
        JOptionPane.showMessageDialog(this, "Thêm thành công!");
    }//GEN-LAST:event_btnThemCLActionPerformed

    private void btnSuaCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCLActionPerformed
        int selectedRow = tblChatLieu.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một màu sắc để sửa!");
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "Xác nhận sửa!");
        if (check == JOptionPane.YES_OPTION) {
            String MaCL = txtMaCL.getText();
            String TenCL = txtTenCL.getText();
            int TrangThaiCL = rdoDangHDCL.isSelected() == true ? 1 : 0;

            ChatLieu cl = new ChatLieu();
            cl.setMaCL(MaCL);
            cl.setTenCL(TenCL);
            cl.setTrangThaiCL(TrangThaiCL);

            chatLieuService.update(cl);
            LamMoiCL();
            LoadTableCL();
            JOptionPane.showMessageDialog(this, "Sửa thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Hủy sửa chất !");
        }
    }//GEN-LAST:event_btnSuaCLActionPerformed

    private void btnLMCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLMCLActionPerformed
        LamMoiCL();
    }//GEN-LAST:event_btnLMCLActionPerformed

    public void LamMoiSPCT() {
        txtMaSPCT.setText("");
        cbbSP.setSelectedIndex(0);
        txtSLTon.setText("0");
        cbbCL.setSelectedIndex(0);
        cbbKC.setSelectedIndex(0);
        cbbMS.setSelectedIndex(0);
        txtNguoiTao.setText("0");
        cbbTH.setSelectedIndex(0);
        rdoConHang.setSelected(true);
        txtDonGia.setText("0");
    }

    public void LamMoiSP() {
        txtMaSP.setText("");
        txtTenSP.setText("");
        rdoDangHDSP.setSelected(true);
    }

    public void LamMoiCL() {
        txtMaCL.setText("");
        txtTenCL.setText("");
        rdoDangHDCL.setSelected(true);
    }

    public void LamMoiKC() {
        txtMaKC.setText("");
        txtTenKC.setText("");
        rdoDangHDKC.setSelected(true);
    }

    public void LamMoiMS() {
        txtMaMS.setText("");
        txtTenMS.setText("");
        rdoDangHDMS.setSelected(true);
    }

    public void LamMoiTH() {
        txtMaTH.setText("");
        txtTenTH.setText("");
        rdoDangHDTH.setSelected(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog ChatLieuDialog;
    private javax.swing.JDialog KichCoDialog;
    private javax.swing.JDialog MauSacDialog;
    private javax.swing.JDialog SanPhamDialog;
    private javax.swing.JDialog ThuongHieuDialog;
    private javax.swing.JButton btnCL;
    private javax.swing.JButton btnKichCo;
    private javax.swing.JButton btnLMCL;
    private javax.swing.JButton btnLMKC;
    private javax.swing.JButton btnLMTH;
    private javax.swing.JButton btnLamMoiMS;
    private javax.swing.JButton btnLamMoiSP;
    private javax.swing.JButton btnLamMoiSPCT;
    private javax.swing.JButton btnMauSac;
    private javax.swing.JButton btnMoiCL;
    private javax.swing.JButton btnMoiKC;
    private javax.swing.JButton btnMoiMS;
    private javax.swing.JButton btnMoiSP;
    private javax.swing.JButton btnMoiTH;
    private javax.swing.JButton btnSuaCL;
    private javax.swing.JButton btnSuaKC;
    private javax.swing.JButton btnSuaMS;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnSuaSPCT;
    private javax.swing.JButton btnSuaTH;
    private javax.swing.JButton btnTH;
    private javax.swing.JButton btnTenSP;
    private javax.swing.JButton btnThemCL;
    private javax.swing.JButton btnThemKC;
    private javax.swing.JButton btnThemMS;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnThemSPCT;
    private javax.swing.JButton btnThemTH;
    private javax.swing.JButton btnTimCL;
    private javax.swing.JButton btnTimKC;
    private javax.swing.JButton btnTimMS;
    private javax.swing.JButton btnTimSP;
    private javax.swing.JButton btnTimSPCT;
    private javax.swing.JButton btnTimTH;
    private javax.swing.JButton btnXoaSPCT;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup10;
    private javax.swing.ButtonGroup buttonGroup11;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
    private javax.swing.JComboBox<String> cbbCL;
    private javax.swing.JComboBox<String> cbbKC;
    private javax.swing.JComboBox<String> cbbMS;
    private javax.swing.JComboBox<String> cbbSP;
    private javax.swing.JComboBox<String> cbbTH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton rdoConHang;
    private javax.swing.JRadioButton rdoDangHDCL;
    private javax.swing.JRadioButton rdoDangHDKC;
    private javax.swing.JRadioButton rdoDangHDMS;
    private javax.swing.JRadioButton rdoDangHDSP;
    private javax.swing.JRadioButton rdoDangHDTH;
    private javax.swing.JRadioButton rdoDungHDCL;
    private javax.swing.JRadioButton rdoDungHDKC;
    private javax.swing.JRadioButton rdoDungHDMS;
    private javax.swing.JRadioButton rdoDungHDSP;
    private javax.swing.JRadioButton rdoDungHDTH;
    private javax.swing.JRadioButton rdoHetHang;
    private javax.swing.JRadioButton rdoLocDangHDCL;
    private javax.swing.JRadioButton rdoLocDangHDKC;
    private javax.swing.JRadioButton rdoLocDangHDMS;
    private javax.swing.JRadioButton rdoLocDangHDSP;
    private javax.swing.JRadioButton rdoLocDangHDTH;
    private javax.swing.JRadioButton rdoLocDungHDCL;
    private javax.swing.JRadioButton rdoLocDungHDKC;
    private javax.swing.JRadioButton rdoLocDungHDMS;
    private javax.swing.JRadioButton rdoLocDungHDSP;
    private javax.swing.JRadioButton rdoLocDungHDTH;
    private javax.swing.JTable tblChatLieu;
    private javax.swing.JTable tblKichCo;
    private javax.swing.JTable tblMauSac;
    private javax.swing.JTable tblSPCT;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblThuongHieu;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaCL;
    private javax.swing.JTextField txtMaKC;
    private javax.swing.JTextField txtMaMS;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMaSPCT;
    private javax.swing.JTextField txtMaTH;
    private javax.swing.JTextField txtNguoiTao;
    private javax.swing.JTextField txtSLTon;
    private javax.swing.JTextField txtSearchByMa;
    private javax.swing.JTextField txtSearchByTenCL;
    private javax.swing.JTextField txtSearchByTenKC;
    private javax.swing.JTextField txtSearchByTenTH;
    private javax.swing.JTextField txtSearchMaSPCT;
    private javax.swing.JTextField txtSearchbyTenMS;
    private javax.swing.JTextField txtTenCL;
    private javax.swing.JTextField txtTenKC;
    private javax.swing.JTextField txtTenMS;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenTH;
    // End of variables declaration//GEN-END:variables
}
