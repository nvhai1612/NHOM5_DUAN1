/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Buivuhoang
 */
public class ThongKeSP {

    private Connection con = null;       // Biến kết nối với csdl
    private PreparedStatement pr = null; // Chuẩn bị thực hiện lệnh
    private ResultSet rs = null;         // Tập kết quả truy vấn select
    private String sql = null;           // Câu lệnh sql

    public ThongKeSP() {
        con = Utiliti.DBConnection.getConnection();
    }

    public ArrayList<DomainModel.ThongKeSP> getAll() {
        // lấy tất cả dl từ bảng mylove trong sql server
        // đổ vào list
        sql = "select SANPHAM.MASP,SANPHAM.TENSP,SANPHAMCHITIET.SOLUONGTON,CHATLIEU.TENCL,MAUSAC.TENMS,KICHCO.TENKC,SANPHAM.TRANGTHAISP from SANPHAM \n"
                + "INNER JOIN SANPHAMCHITIET ON SANPHAM.ID = SANPHAMCHITIET.ID\n"
                + "INNER JOIN CHATLIEU ON SANPHAM.ID = CHATLIEU.ID\n"
                + "INNER JOIN MAUSAC ON SANPHAM.ID = MAUSAC.ID\n"
                + "INNER JOIN KICHCO ON SANPHAM.ID = KICHCO.ID";

        ArrayList list_ThongKeSP = new ArrayList<>();
        try {
            // Kết nối thành công
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            while (rs.next()) { // chạy từ đầu đến cuối tập rs
                String maSp, tenSp, chatLieu, mauSac, kichThuoc;
                int soLuong;
                boolean trangThai;

                maSp = rs.getString(1);
                tenSp = rs.getString(2);
                soLuong = rs.getInt(3);
                chatLieu = rs.getString(4);
                mauSac = rs.getString(5);
                kichThuoc = rs.getString(6);
                trangThai = rs.getBoolean(7);
                DomainModel.ThongKeSP tkSP = new DomainModel.ThongKeSP(maSp, tenSp, soLuong, chatLieu, mauSac, kichThuoc, trangThai);
                list_ThongKeSP.add(tkSP);
            }// đóng white
            return list_ThongKeSP;

        } catch (Exception e) {
            // Kết nối lỗi
            e.printStackTrace(); // In ra các lỗi
            return null;
        }
    }

}
