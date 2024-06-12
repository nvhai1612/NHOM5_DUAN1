/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import DomainModel.KichCo;
import Repository.Impl.KichCoRepos;
import Service.IKichCoService;
import ViewModel.KichCoVM;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class KichCoService implements IKichCoService{
    private KichCoRepos KichCoRepos = new KichCoRepos();

    @Override
    public ArrayList<KichCoVM> getAll() {
        ArrayList<KichCo> listKC = KichCoRepos.getListFormDB();
        ArrayList<KichCoVM> listVM = new ArrayList<>();
        for (KichCo cv : listKC) {
            KichCoVM kcvm = new KichCoVM(cv.getId(), cv.getMaKC(), cv.getTenKC(), cv.getTrangThaiKC());
            listVM.add(kcvm);
        }
        return listVM;
    }

    @Override
    public ArrayList<KichCo> getAllDomain() {
        return KichCoRepos.getListFormDB();
    }

    @Override
    public void add(KichCo kc) {
        KichCoRepos.add(kc);
    }

    @Override
    public void update(KichCo kc) {
        KichCoRepos.update(kc);
    }

    @Override
    public ArrayList<KichCo> search(String MaKC) {
        return KichCoRepos.search();
    }
    
}
