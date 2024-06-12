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
public class ThongKeHD {
    
    private Connection con = null;       // Biến kết nối với csdl
    private PreparedStatement pr = null; // Chuẩn bị thực hiện lệnh
    private ResultSet rs = null;         // Tập kết quả truy vấn select
    private String sql = null;           // Câu lệnh sql

    public ThongKeHD() {
        con = Utiliti.DBConnection.getConnection();
    }
    
    public ArrayList<DomainModel.ThongKeHD> getAll() {
        // lấy tất cả dl từ bảng mylove trong sql server
        // đổ vào list
        sql = "select HOADON.MAHD,KHACHHANG.TENKH,KHACHHANG.SDT,HOADON.NGAYTAO,HOADON.TONGTIEN from HOADON\n"
                + "INNER JOIN KHACHHANG ON HOADON.ID = KHACHHANG.ID;";
        
        ArrayList list_TKHD = new ArrayList<>();
        try {
            // Kết nối thành công
            con = Utiliti.DBConnection.getConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            while (rs.next()) { // chạy từ đầu đến cuối tập rs
                String maHD, tenKH, ngayTao;
                int sdt;
                float tongTien;
                boolean trangThai;
                
                maHD = rs.getString(1);
                tenKH = rs.getString(2);
                sdt = rs.getInt(3);
                ngayTao = rs.getString(4);
                tongTien = rs.getFloat(5);
                trangThai = rs.getBoolean(6);
                
                DomainModel.ThongKeHD tkHD = new DomainModel.ThongKeHD(maHD, tenKH, sdt, ngayTao, tongTien);
                list_TKHD.add(tkHD);
            }// đóng white
            return list_TKHD;
            
        } catch (Exception e) {
            // Kết nối lỗi
            e.printStackTrace(); // In ra các lỗi
            return null;
        }
    }
    
    public ArrayList<DomainModel.ThongKeHD> timKiem(String tenCanTim) {
        sql = "";
        ArrayList list_Mylove = new ArrayList<>();
        try {
            // Kết nối thành công
            con = Utiliti.DBConnection.getConnection();
            pr = con.prepareStatement(sql);
            pr.setObject(1, '%' + tenCanTim + '%');
            rs = pr.executeQuery();
            while (rs.next()) { // chạy từ đầu đến cuối tập rs
                String maHD, tenKH, ngayTao;
                int sdt;
                float tongTien;
                boolean trangThai;
                
                maHD = rs.getString(1);
                tenKH = rs.getString(2);
                sdt = rs.getInt(3);
                ngayTao = rs.getString(4);
                tongTien = rs.getFloat(5);
                trangThai = rs.getBoolean(6);
                DomainModel.ThongKeHD ml = new DomainModel.ThongKeHD(maHD, tenKH, sdt, ngayTao, tongTien);
                list_Mylove.add(ml);
            }// đóng white
            return list_Mylove;
            
        } catch (Exception e) {
            // Kết nối lỗi
            e.printStackTrace(); // In ra các lỗi
            return null;
        }
    }
    
}
