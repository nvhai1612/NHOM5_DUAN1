/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModel.ChucVu;
import DomainModel.NhanVien;
import Repository.IChucVuRepos;
import Utiliti.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public class ChucVuRepos implements IChucVuRepos {

    private DBConnection connection;

    @Override
    public ArrayList<ChucVu> getListFormDB() {
        ArrayList<ChucVu> listCV = new ArrayList<>();

        try (Connection conn = connection.getConnection(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM CHUCVU"); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {

                ChucVu cv = new ChucVu();
                cv.setId(rs.getObject(1, UUID.class));
                cv.setMaCV(rs.getString(2));
                cv.setTenCV(rs.getString(3));
                listCV.add(cv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listCV;
    }

    @Override
    public Boolean add(ChucVu cv) {
        int check ;
        try (Connection con = connection.getConnection()) {
            String sql = "INSERT INTO CHUCVU (MACV,TENCV) VALUES  (?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, cv.getMaCV());
            ps.setObject(2, cv.getTenCV());
            check= ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean update(ChucVu cv) {
        int check;
        try (Connection con = connection.getConnection()) {
            String sql = "UPDATE CHUCVU SET TENCV=? WHERE MACV=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, cv.getMaCV());
            ps.setObject(2, cv.getTenCV()); 
            check=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<ChucVu> search(String MaCV) {
        ArrayList<ChucVu> chucVuList = new ArrayList<>();
        try (Connection con = connection.getConnection()) {
            String sql = "SELECT * FROM ChucVu WHERE MACV = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, MaCV);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UUID id = UUID.fromString(rs.getString("id"));
                String maCV = rs.getString("MACV");
                String tenCV = rs.getString("TenCV");
                ChucVu chucVu = new ChucVu();
                chucVuList.add(chucVu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chucVuList;
    }
       

    @Override
    public ArrayList<NhanVien> searchbyCV(String TenCV) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
