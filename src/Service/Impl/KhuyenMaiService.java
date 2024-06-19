/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import DomainModel.KhuyenMai;
import DomainModel.SPCT;
import DomainModel.SanPham;

import Repository.Impl.KhuyenMaiRepons;
import Repository.Impl.SanPhamRepos;
import Service.IkhuyenMaiSeviec;
import ViewModel.khuyeMaiVM;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhuyenMaiService implements IkhuyenMaiSeviec{
    KhuyenMaiRepons respon = new KhuyenMaiRepons();

    @Override
    public ArrayList<khuyeMaiVM> getAll() {
         ArrayList<KhuyenMai> listSP = respon.getListFormDB();
        ArrayList<khuyeMaiVM> listVM = new ArrayList<>();
        for (KhuyenMai sp : listSP) {
            khuyeMaiVM spvm = new khuyeMaiVM(sp.getIdKM(), sp.getIDHD(), sp.getMaKM(), sp.getTenKM(),sp.getMucGiamGia(),sp.getThoiGianBatDau(),sp.getThoiGianKetThuc(),sp.getTrangThai(),sp.getSoLuong());
            listVM.add(spvm);
        }
        return listVM;
    }

    @Override
    public ArrayList<KhuyenMai> getAllDomain() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public ArrayList<khuyeMaiVM> getAllKMHieuLuc() {
         ArrayList<KhuyenMai> listSP = respon.getListHieuLuc();
        ArrayList<khuyeMaiVM> listVM = new ArrayList<>();
        for (KhuyenMai sp : listSP) {
            khuyeMaiVM spvm = new khuyeMaiVM(sp.getIdKM(), sp.getIDHD(), sp.getMaKM(), sp.getTenKM(),sp.getMucGiamGia(),sp.getThoiGianBatDau(),sp.getThoiGianKetThuc(),sp.getTrangThai(),sp.getSoLuong());
            listVM.add(spvm);
        }
        return listVM;
    }

  
    @Override
    public void update(KhuyenMai km) {
        respon.update(km);
    }

    @Override
    public void add(KhuyenMai km) {
        respon.add(km);
    }



    @Override
    public ArrayList<KhuyenMai> search(String makm) {
       respon.search();
       return null;
    }

    
//     }
    public List<SPCT>getSPCT(String maSPCT){
        return respon.getTransactionHistoryByCustomer(maSPCT);
    }

    @Override
    public Boolean existsBymakm(String makm) {
        return respon.existsBymakm(makm);
    }
            
   

  

    
    
}
