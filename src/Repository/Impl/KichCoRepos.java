/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModel.KichCo;
import Repository.IKichCoRepos;
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
public class KichCoRepos implements IKichCoRepos{
    DBConnection connection;

    @Override
    public ArrayList<KichCo> getListFormDB() {
        ArrayList<KichCo> listKC = new ArrayList<>();

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT ID,MAKC,TENKC,TRANGTHAIKC FROM KICHCO")) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                KichCo kc = new KichCo();
                kc.setId(rs.getObject(1, UUID.class));
                kc.setMaKC(rs.getString(2));
                kc.setTenKC(rs.getString(3));
                kc.setTrangThaiKC(rs.getInt(4));
                listKC.add(kc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKC;
    }

    @Override
    public Boolean add(KichCo kc) {
        int check;

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO KICHCO(MAKC,TENKC,TRANGTHAIKC) Values(?,?,?,?)")) {

            ps.setObject(1, kc.getMaKC());
            ps.setObject(2, kc.getTenKC());
            ps.setObject(3, kc.getTrangThaiKC());

            check = ps.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Boolean update(KichCo kc) {
        int check;

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("UPDATE KICHCO SET TENKC = ?, TRANGTHAIKC = ? WHERE MAKC = ?")) {

            ps.setObject(1, kc.getTenKC());
            ps.setObject(2, kc.getTrangThaiKC());
            ps.setObject(3, kc.getMaKC());

            check = ps.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<KichCo> search() {
        ArrayList<KichCo> listKC = new ArrayList<>();
        try (Connection con = connection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM KICHCO WHERE MAKC = ?");){
            ps.executeUpdate();
            
            KichCo kc = new KichCo();
            ps.setObject(1, kc.getMaKC());
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                kc.setId((UUID) rs.getObject(1));
                kc.setMaKC(rs.getString(2));
                listKC.add(kc);
            } 
        } catch (Exception e) {
            e.getMessage();
        }
        return listKC;
    }
}
