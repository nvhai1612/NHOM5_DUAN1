/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views;

import DomainModel.KhuyenMai;
import DomainModel.SPCT;
import Service.Impl.KhuyenMaiService;
import Service.Impl.NhanVienService;
import Service.Impl.SPCTService;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class KhuyenMaiJPanel extends javax.swing.JPanel {

    ArrayList<KhuyenMai> list = new ArrayList<>();
    SPCTService seviec2 = new SPCTService();
    KhuyenMaiService seviec = new KhuyenMaiService();
    NhanVienService nvseviec = new NhanVienService();

    DefaultTableModel dtm;

    /**
     * Creates new form KhuyenMaiJPanel
     */
    public KhuyenMaiJPanel() {
        initComponents();
        LoadTable();
    }

    private void LoadTable() {
        DefaultTableModel dtm = (DefaultTableModel) btnbangkm.getModel();
        dtm.setRowCount(0);
        ArrayList<khuyenMaiVM> listSPVM = seviec.getAll();

        for (KhuyenMaiVM x : listSPVM) {
            dtm.addRow(new Object[]{
                x.getMaKM(),
                x.getTenKM(),
                x.getMucGiamGia(),
                x.getThoiGianBatDau(),
                x.getThoiGianKetThuc(),
                x.getSoLuong(),
                x.getTrangThai() == 1 ? "Hoạt động" : "Dừng Hoạt động",}
            );
        }
    }

    public boolean checkvailidate() {
        if (txtma.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "bạn cần nhập mã");
            txtma.requestFocus();
            return false;
        }
        if (txttenchuongtrinh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "bạn cần nhập tên");
            txttenchuongtrinh.requestFocus();
            return false;
        }
        if (txtmuckm.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "bạn cần nhập mức giảm giá");
            txtmuckm.requestFocus();
            return false;
        }
        if (txtngaybd.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "bạn cần nhập thời gian bắt đâuuf");
            txtngaybd.requestFocus();
            return false;
        }
        if (txtngaykt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "bạn cần nhập thời gian kết thúc");
            txtngaykt.requestFocus();
            return false;

        }
        if (txtsoluong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "bạn cần nhập số lượng");
            txtsoluong.requestFocus();
            return false;

        }
        return true;
    }

    public void lammoi() {
        txtma.setText("");
        txttenchuongtrinh.setText("");
        txtmuckm.setText("");
        txtngaybd.setText("");
        txtngaykt.setText("");
        txtsoluong.setText("");

    }

    private void showSPKM(String maSPCT) {
        List<SPCT> list = seviec.getSPCT(maSPCT);
        DefaultTableModel tableModel1HoaDon = (DefaultTableModel) btngiosp.getModel();
        tableModel1HoaDon.setRowCount(0);
        for (SPCT sp : list) {
            tableModel1HoaDon.addRow(new Object[]{
                sp.getMaSPCT(),
                sp.getTenSP(),
                sp.getTenKC(),
                sp.getTenMS(),
                sp.getTenCL(), sp.getTenTH(), sp.getDonGia(),
                sp.getKM()

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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        btngiosp = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtseach = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        btnbangkm = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        rdohoatdong = new javax.swing.JRadioButton();
        rdongunghoatdong = new javax.swing.JRadioButton();
        txtma = new javax.swing.JTextField();
        txttenchuongtrinh = new javax.swing.JTextField();
        txtmuckm = new javax.swing.JTextField();
        txtngaybd = new javax.swing.JTextField();
        txtngaykt = new javax.swing.JTextField();
        txtsoluong = new javax.swing.JTextField();
        btnadd = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btnlammoi = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(222, 231, 227));

        jPanel2.setBackground(new java.awt.Color(222, 231, 227));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "QUẢN LÝ KHUYẾN MẠI", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(222, 231, 227));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        btngiosp.setBackground(new java.awt.Color(222, 231, 227));
        btngiosp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "KC", "MS", "CL", "TH", "Đơn giá", "KM"
            }
        ));
        jScrollPane1.setViewportView(btngiosp);

        jPanel6.setBackground(new java.awt.Color(222, 231, 227));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton3.setText("Tìm");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton3))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(222, 231, 227));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách khuyến mại"));

        jPanel5.setBackground(new java.awt.Color(222, 231, 227));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm khuyến mại", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jButton1.setText("Tìm kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtseach, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtseach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        btnbangkm.setBackground(new java.awt.Color(222, 231, 227));
        btnbangkm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã KM", "Tên KM", "Mức KM", "Bắt đầu", "Kết thúc", "Số lượng", "Trạng thái"
            }
        ));
        btnbangkm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnbangkmMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(btnbangkm);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(222, 231, 227));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khuyến mại"));

        jLabel1.setText("Mã khuyến mại :");

        jLabel2.setText("Tên chương trình :");

        jLabel3.setText("Mức khuyến mại (%) :");

        jLabel4.setText("Thời gian bắt đầu :");

        jLabel5.setText("Thời gian kết thúc :");

        jLabel6.setText("Số lượng :");

        buttonGroup1.add(rdohoatdong);
        rdohoatdong.setSelected(true);
        rdohoatdong.setText("Đang hoạt động");

        buttonGroup1.add(rdongunghoatdong);
        rdongunghoatdong.setText("Ngưng hoạt động");

        btnadd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnadd.setText("Lưu");
        btnadd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnaddMouseClicked(evt);
            }
        });
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btnupdate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnupdate.setText("Cập nhật");
        btnupdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnupdateMouseClicked(evt);
            }
        });
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btnlammoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnlammoi.setText("Làm mới");
        btnlammoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnlammoiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnadd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtma)
                            .addComponent(txttenchuongtrinh)
                            .addComponent(txtmuckm)
                            .addComponent(txtngaybd)
                            .addComponent(txtngaykt)
                            .addComponent(txtsoluong)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnlammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(rdohoatdong)
                                .addGap(18, 18, 18)
                                .addComponent(rdongunghoatdong)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txttenchuongtrinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtmuckm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtngaybd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtngaykt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdohoatdong)
                    .addComponent(rdongunghoatdong))
                .addGap(18, 18, 18)
                .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnupdate, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(btnlammoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ArrayList<KhuyenMaiVM> km = seviec.getAll();
        String TimKiem = this.txtseach.getText();
        int index = -1;
        for (KhuyenMaiVM listkm : km) {
            if (TimKiem.equalsIgnoreCase(listkm.getMaKM())) {
                index = km.indexOf(listkm);
            }
        }
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy !");
            return;
        }
        KhuyenMai km1 = new KhuyenMai();
        txtma.setText(km.get(index).getMaKM());
        txttenchuongtrinh.setText(km.get(index).getTenKM());
        txtmuckm.setText(String.valueOf(km.get(index).getMucGiamGia()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        txtngaybd.setText(sdf.format(km.get(index).getThoiGianBatDau()));
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
        txtngaykt.setText(sdt.format(km.get(index).getThoiGianKetThuc()));
        txtsoluong.setText(String.valueOf(km.get(index).getSoLuong()));
        if (km1.getTrangThai() == 1) {
            rdohoatdong.setSelected(true);
        } else {
            rdongunghoatdong.setSelected(false);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnaddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnaddMouseClicked


    }//GEN-LAST:event_btnaddMouseClicked

    private void btnupdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnupdateMouseClicked

    }//GEN-LAST:event_btnupdateMouseClicked

    private void btnlammoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlammoiMouseClicked
        lammoi();
    }//GEN-LAST:event_btnlammoiMouseClicked

    private void btnbangkmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbangkmMouseClicked
        int row = btnbangkm.getSelectedRow();
        if (row == -1) {
            return;
        }
        txtma.setText(btnbangkm.getValueAt(row, 0).toString());
        txttenchuongtrinh.setText(btnbangkm.getValueAt(row, 1).toString());
        txtmuckm.setText(btnbangkm.getValueAt(row, 2).toString());
        txtngaybd.setText(btnbangkm.getValueAt(row, 3).toString());
        txtngaykt.setText(btnbangkm.getValueAt(row, 4).toString());
        txtsoluong.setText(btnbangkm.getValueAt(row, 5).toString());

        String t = btnbangkm.getValueAt(row, 6).toString();

        if (t.equals("Hoat dong")) {
            rdohoatdong.setSelected(true);
        } else {
            rdongunghoatdong.setSelected(true);
        }
        String maSP = btnbangkm.getValueAt(row, 0).toString();
        showSPKM(maSP);
    }//GEN-LAST:event_btnbangkmMouseClicked

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        if (!checkvailidate()) {
            return;

        }
        String makm = txtma.getText();
        String tensp = txttenchuongtrinh.getText();
        String mucgiangia = txtmuckm.getText();
        int TrangThai = rdohoatdong.isSelected() == true ? 1 : 0;
        Date ngaybatdau = Date.valueOf(txtngaybd.getText());
        Date ngayketthuc = Date.valueOf(txtngaykt.getText());
        String soluong = txtsoluong.getText();

        if (seviec.existsBymakm(makm)) {
            JOptionPane.showMessageDialog(this, "Ma KM đã tồn tại, vui lòng chọn mã khác.");
            txtma.requestFocus();
            return;
        }
        KhuyenMai km = new KhuyenMai();
        km.setMaKM(makm);
        km.setTenKM(tensp);
        km.setMucGiamGia(Float.valueOf(mucgiangia));
        km.setThoiGianBatDau(ngaybatdau);
        km.setThoiGianKetThuc(ngayketthuc);
        km.setTrangThai(TrangThai);
        km.setSoLuong(Integer.valueOf(soluong));
        this.seviec.add(km);

        this.LoadTable();
        lammoi();
    }//GEN-LAST:event_btnaddActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        int row = btnbangkm.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một Khuyen mai để sửa.");
            return;
        }
        if (!checkvailidate()) {
            return;
        }
        String makm = txtma.getText();
        String tensp = txttenchuongtrinh.getText();
        String mucgiangia = txtmuckm.getText();
        int TrangThai = rdohoatdong.isSelected() == true ? 1 : 0;
        Date ngaybatdau = Date.valueOf(txtngaybd.getText());
        Date ngayketthuc = Date.valueOf(txtngaykt.getText());
        String soluong = txtsoluong.getText();

        KhuyenMai km = new KhuyenMai();
        km.setMaKM(makm);
        km.setTenKM(tensp);
        km.setMucGiamGia(Float.valueOf(mucgiangia));
        km.setThoiGianBatDau(ngaybatdau);
        km.setThoiGianKetThuc(ngayketthuc);
        km.setTrangThai(TrangThai);
        km.setSoLuong(Integer.valueOf(soluong));
        this.seviec.update(km);
        this.LoadTable();
        lammoi();
    }//GEN-LAST:event_btnupdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JTable btnbangkm;
    private javax.swing.JTable btngiosp;
    private javax.swing.JButton btnlammoi;
    private javax.swing.JButton btnupdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdohoatdong;
    private javax.swing.JRadioButton rdongunghoatdong;
    private javax.swing.JTextField txtma;
    private javax.swing.JTextField txtmuckm;
    private javax.swing.JTextField txtngaybd;
    private javax.swing.JTextField txtngaykt;
    private javax.swing.JTextField txtseach;
    private javax.swing.JTextField txtsoluong;
    private javax.swing.JTextField txttenchuongtrinh;
    // End of variables declaration//GEN-END:variables
}
