/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModel.ChatLieu;
import Repository.IChatLieuRepos;
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
public class ChatLieuRepos implements IChatLieuRepos{
    DBConnection connection;

    @Override
    public ArrayList<ChatLieu> getListFormDB() {
        ArrayList<ChatLieu> listCL = new ArrayList<>();
        
        try (Connection con = connection.getConnection(); 
                PreparedStatement ps = con.prepareStatement("SELECT ID,MACL,TENCL,TRANGTHAICL FROM CHATLIEU")) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ChatLieu cl = new ChatLieu();
                cl.setId(rs.getObject(1, UUID.class));
                cl.setMaCL(rs.getString(2));
                cl.setTenCL(rs.getString(3));
                cl.setTrangThaiCL(rs.getInt(4));
                listCL.add(cl);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCL;
    }

    @Override
    public Boolean add(ChatLieu cl) {
        int check;

        try (Connection con = connection.getConnection(); 
                PreparedStatement ps = con.prepareStatement("INSERT INTO CHATLIEU(MACL,TENCL,TRANGTHAICL) Values(?,?,?)")) {

            ps.setObject(1, cl.getMaCL());
            ps.setObject(2, cl.getTenCL());
            ps.setObject(3, cl.getTrangThaiCL());
            check = ps.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean update(ChatLieu cl) {
        int check;

        try (Connection con = connection.getConnection(); 
                PreparedStatement ps = con.prepareStatement("UPDATE CHATLIEU SET TENCL = ?,TRANGTHAICL = ? WHERE MACL = ?")) {

            ps.setObject(1, cl.getTenCL());
            ps.setObject(2, cl.getTrangThaiCL());
            ps.setObject(3, cl.getMaCL());

            check = ps.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<ChatLieu> search() {
        ArrayList<ChatLieu> listCL = new ArrayList<>();
        try (Connection con = connection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM CHATLIEU WHERE MACL = ?");){
            ps.executeUpdate();
            
            ChatLieu cl = new ChatLieu();
            ps.setObject(1, cl.getMaCL());
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                cl.setId((UUID) rs.getObject(1));
                cl.setMaCL(rs.getString(2));
                listCL.add(cl);
            } 
        } catch (Exception e) {
            e.getMessage();
        }
        return listCL;
    }
}
