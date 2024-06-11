/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import DomainModel.KhachHang;
import Repository.Impl.KhachHangRepos;
import Service.IKhachHangService;
import ViewModel.KhachHangVM;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class KhachHangService implements IKhachHangService{
        KhachHangRepos khachHangRepos = new KhachHangRepos();
    @Override
    public ArrayList<KhachHangVM> getAll() {
         ArrayList<KhachHang> listKH = khachHangRepos.getAllKhachHang();
        ArrayList<KhachHangVM> list = new ArrayList<>();
        for (KhachHang kh : listKH) {
            KhachHangVM khvm = new KhachHangVM(kh.getId(), kh.getMaKH(), kh.getTenKH(), kh.getNgaySinh(),kh.getGioiTinh(),kh.getSdt(),kh.getDiaChi());
            list.add(khvm);
        }
        return list;
    }

    @Override
    public ArrayList<KhachHang> getAllDomain() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void add(KhachHang kh) {
        khachHangRepos.add(kh);
    }
    

    @Override
    public void update(KhachHang kh) {
        khachHangRepos.update(kh);
    }

    @Override
    public ArrayList<KhachHang> search(String ma) {
         ArrayList<KhachHang> listKH = khachHangRepos.search(ma);
         return listKH;
    }

     
        
   

    
    
    

    
    
}
