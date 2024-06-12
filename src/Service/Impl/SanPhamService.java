/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import DomainModel.SanPham;
import Repository.Impl.SanPhamRepos;
import Service.ISanPhamService;
import ViewModel.SanPhamVM;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class SanPhamService implements ISanPhamService{
    private SanPhamRepos SanPhamRepos = new SanPhamRepos();

    @Override
    public ArrayList<SanPhamVM> getAll() {
        ArrayList<SanPham> listSP = SanPhamRepos.getListFormDB();
        ArrayList<SanPhamVM> listVM = new ArrayList<>();
        for (SanPham sp : listSP) {
            SanPhamVM spvm = new SanPhamVM(sp.getId(), sp.getMaSP(), sp.getTenSP(), sp.getTrangThaiSP());
            listVM.add(spvm);
        }
        return listVM;
    }

    @Override
    public ArrayList<SanPham> getAllDomain() {
        return SanPhamRepos.getListFormDB();
    }

    @Override
    public void add(SanPham sp) {
        SanPhamRepos.add(sp);
    }

    @Override
    public void update(SanPham sp) {
        SanPhamRepos.update(sp);
    }

    @Override
    public ArrayList<SanPham> search(String MaSP) {
        return SanPhamRepos.search();
    }
    
}
