/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import DomainModel.MauSac;
import Repository.Impl.MauSacRepos;
import Service.IMauSacService;
import ViewModel.MauSacVM;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class MauSacService implements IMauSacService{
    private MauSacRepos MauSacRepos = new MauSacRepos();

    @Override
    public ArrayList<MauSacVM> getAll() {
        ArrayList<MauSac> listMS = MauSacRepos.getListFormDB();
        ArrayList<MauSacVM> listVM = new ArrayList<>();
        for (MauSac ms : listMS) {
            MauSacVM msvm = new MauSacVM(ms.getId(), ms.getMaMS(), ms.getTenMS(), ms.getTrangThaiMS());
            listVM.add(msvm);
        }
        return listVM;
    }

    @Override
    public ArrayList<MauSac> getAllDomain() {
        return MauSacRepos.getListFormDB();
    }

    @Override
    public void add(MauSac ms) {
        MauSacRepos.add(ms);
    }

    @Override
    public void update(MauSac ms) {
        MauSacRepos.update(ms);
    }

    @Override
    public ArrayList<MauSac> search(String MaMS) {
        return MauSacRepos.search();
    }
    
}
