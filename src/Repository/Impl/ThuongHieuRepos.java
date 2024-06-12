/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModel.ThuongHieu;
import Repository.IThuongHieuRepos;
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
public class ThuongHieuRepos implements IThuongHieuRepos{
    DBConnection connection;

    @Override
    public ArrayList<ThuongHieu> getListFormDB() {
ArrayList<ThuongHieu> listTH = new ArrayList<>();

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT ID,MATH,TENTH,TRANGTHAITH FROM THUONGHIEU")) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ThuongHieu th = new ThuongHieu();
                th.setId(rs.getObject(1, UUID.class));
                th.setMaTH(rs.getString(2));
                th.setTenTH(rs.getString(3));
                th.setTrangThaiTH(rs.getInt(4));
                listTH.add(th);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listTH;
    }

    @Override
    public Boolean add(ThuongHieu th) {
        int check;

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO THUONGHIEU(MATH,TENTH,TRANGTHAITH) Values(?,?,?)")) {

            ps.setObject(1, th.getMaTH());
            ps.setObject(2, th.getTenTH());
            ps.setObject(3, th.getTrangThaiTH());

            check = ps.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Boolean update(ThuongHieu th) {
        int check;

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("UPDATE THUONGHIEU SET TENTH = ?, TRANGTHAITH = ? WHERE MATH = ?")) {

            ps.setObject(1, th.getTenTH());
            ps.setObject(2, th.getTrangThaiTH());
            ps.setObject(3, th.getMaTH());

            check = ps.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<ThuongHieu> search() {
        ArrayList<ThuongHieu> listTH = new ArrayList<>();
        try (Connection con = connection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM THUONGHIEU WHERE MATH = ?");){
            ps.executeUpdate();
            
            ThuongHieu th = new ThuongHieu();
            ps.setObject(1, th.getMaTH());
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                th.setId((UUID) rs.getObject(1));
                th.setMaTH(rs.getString(2));
                listTH.add(th);
            } 
        } catch (Exception e) {
            e.getMessage();
        }
        return listTH;
    }
}
