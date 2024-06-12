/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModel.MauSac;
import Repository.IMauSacRepos;
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
public class MauSacRepos implements IMauSacRepos{
    DBConnection connection;

    @Override
    public ArrayList<MauSac> getListFormDB() {
        ArrayList<MauSac> listMS = new ArrayList<>();

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT ID,MAMS,TENMS,TRANGTHAIMS FROM MAUSAC")) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MauSac ms = new MauSac();
                ms.setId(rs.getObject(1, UUID.class));
                ms.setMaMS(rs.getString(2));
                ms.setTenMS(rs.getString(3));
                ms.setTrangThaiMS(rs.getInt(4));
                listMS.add(ms);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMS;
    }

    @Override
    public Boolean add(MauSac ms) {
        int check;

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO MAUSAC(MAMS,TENMS,TRANGTHAIMS) Values(?,?,?,?)")) {

            ps.setObject(1, ms.getMaMS());
            ps.setObject(2, ms.getTenMS());
            ps.setObject(3, ms.getTrangThaiMS());

            check = ps.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Boolean update(MauSac ms) {
        int check;

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("UPDATE MAUSAC SET TENMS = ?, TRANGTHAIMS = ? WHERE MAMS = ?")) {

            ps.setObject(1, ms.getTenMS());
            ps.setObject(2, ms.getTrangThaiMS());
            ps.setObject(3, ms.getMaMS());

            check = ps.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<MauSac> search() {
        ArrayList<MauSac> listMS = new ArrayList<>();
        try (Connection con = connection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM MAUSAC WHERE MAMS = ?");){
            ps.executeUpdate();
            
            MauSac ms = new MauSac();
            ps.setObject(1, ms.getMaMS());
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                ms.setId((UUID) rs.getObject(1));
                ms.setMaMS(rs.getString(2));
                listMS.add(ms);
            } 
        } catch (Exception e) {
            e.getMessage();
        }
        return listMS;
    }
}
