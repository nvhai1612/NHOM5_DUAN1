/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModel.SanPham;
import Repository.ISanPhamRepos;
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
public class SanPhamRepos implements ISanPhamRepos{
    DBConnection connection;

    @Override
    public ArrayList<SanPham> getListFormDB() {
        ArrayList<SanPham> listSP = new ArrayList<>();

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT ID,MASP,TENSP,TRANGTHAISP FROM SANPHAM")) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getObject(1, UUID.class));
                sp.setMaSP(rs.getString(2));
                sp.setTenSP(rs.getString(3));
                sp.setTrangThaiSP(rs.getInt(4));
                listSP.add(sp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSP;
    }

    @Override
    public UUID add(SanPham sp) {
        int check;
        System.out.println(sp);
        System.out.println(sp.getMaSP());
        System.out.println(sp.getTenSP());
        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO SANPHAM(MASP,TENSP,TRANGTHAISP) Values(?,?,?)")) {

            ps.setObject(1, sp.getMaSP());
            ps.setObject(2, sp.getTenSP());
            ps.setObject(3, sp.getTrangThaiSP());

            check = ps.executeUpdate();
            System.out.println(SelectSPByTen(sp.getTenSP()));
            con.close();
            return SelectSPByTen(sp.getTenSP());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SelectSPByTen(sp.getTenSP());
    }
    
    public UUID SelectSPByTen(String TenSP) {

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT ID FROM SANPHAM WHERE TENSP = ?")) {
            ps.setObject(1, TenSP);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getObject(1, UUID.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean update(SanPham sp) {
        int check;
        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("UPDATE SANPHAM SET TENSP = ?, TRANGTHAISP = ? WHERE MASP = ?")) {


            ps.setObject(1, sp.getTenSP());
            ps.setObject(2, sp.getTrangThaiSP());
            ps.setObject(3, sp.getMaSP());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<SanPham> search() {
        ArrayList<SanPham> listSP = new ArrayList<>();
        try (Connection con = connection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM SANPHAM WHERE MASP = ?");){
            ps.executeUpdate();
            
            SanPham sp = new SanPham();
            ps.setObject(1, sp.getMaSP());
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                sp.setId((UUID) rs.getObject(1));
                sp.setMaSP(rs.getString(2));
                listSP.add(sp);
            } 
        } catch (Exception e) {
            e.getMessage();
        }
        return listSP;
    }
}
