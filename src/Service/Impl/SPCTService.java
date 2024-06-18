/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import DomainModel.SPCT;
import Repository.Impl.SPCTRepos;
import Service.ISPCTService;
import ViewModel.SPCTVM;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class SPCTService implements ISPCTService{
    private SPCTRepos SPCTRepos = new SPCTRepos();

    @Override
    public ArrayList<SPCTVM> getAll() {
        ArrayList<SPCT> listCTSP = SPCTRepos.getListFormDB();
        ArrayList<SPCTVM> listCTSPVM = new ArrayList<>();
        for (SPCT spct : listCTSP) {
            SPCTVM spctvm = new SPCTVM(spct.getId(), spct.getMaSPCT(),
                    SPCTRepos.SelectMSById(spct.getIdMS()),SPCTRepos.SelectCLById(spct.getIdCL()),
                    SPCTRepos.SelectTHById(spct.getIdTH()),SPCTRepos.SelectKCById(spct.getIdKC()),
                    spct.getTenSP(), spct.getNguoiTao(), spct.getSoLuongTon(), 
                    spct.getTrangThaiSPCT(), spct.getDonGia());
            listCTSPVM.add(spctvm);
        }
        return listCTSPVM;
    }

    @Override
    public ArrayList<SPCT> getAllDoMain() {
        return SPCTRepos.getListFormDB();
    }

    @Override
    public void add(SPCT ctsp) {
        SPCTRepos.add(ctsp);
    }

    @Override
    public void update(SPCT ctsp) {
        SPCTRepos.update(ctsp);
    }

    @Override
    public ArrayList<SPCT> search(String MaCTSP) {
        ArrayList<SPCT> ListSPCT = SPCTRepos.search(MaCTSP);
         return ListSPCT;
    }
    
    public ArrayList<SPCTVM> getAllBanHang() {
        ArrayList<SPCT> listCTSP = SPCTRepos.getListForm();
        ArrayList<SPCTVM> listCTSPVM = new ArrayList<>();
        for (SPCT spct : listCTSP) {
            SPCTVM spctvm = new SPCTVM(spct.getId(), spct.getMaSPCT(),
                    SPCTRepos.SelectMSById(spct.getIdMS()),SPCTRepos.SelectCLById(spct.getIdCL()),
                    SPCTRepos.SelectTHById(spct.getIdTH()),SPCTRepos.SelectKCById(spct.getIdKC()),
                    spct.getTenSP(), spct.getNguoiTao(), spct.getSoLuongTon(), 
                    spct.getTrangThaiSPCT(), spct.getDonGia());
            listCTSPVM.add(spctvm);
        }
        return listCTSPVM;
    }
}
