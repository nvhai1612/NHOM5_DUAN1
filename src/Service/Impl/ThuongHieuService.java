/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import DomainModel.ThuongHieu;
import Repository.Impl.ThuongHieuRepos;
import Service.IThuongHieuService;
import ViewModel.ThuongHieuVM;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ThuongHieuService implements IThuongHieuService{
    private ThuongHieuRepos thuongHieuRepos = new ThuongHieuRepos();

    @Override
    public ArrayList<ThuongHieuVM> getAll() {
        ArrayList<ThuongHieu> listTH = thuongHieuRepos.getListFormDB();
        ArrayList<ThuongHieuVM> listVM = new ArrayList<>();
        for (ThuongHieu th : listTH) {
            ThuongHieuVM thvm = new ThuongHieuVM(th.getId(), th.getMaTH(), th.getTenTH(), th.getTrangThaiTH());
            listVM.add(thvm);
        }
        return listVM;
    }

    @Override
    public ArrayList<ThuongHieu> getAllDomain() {
        return thuongHieuRepos.getListFormDB();
    }

    @Override
    public void add(ThuongHieu th) {
        thuongHieuRepos.add(th);
    }

    @Override
    public void update(ThuongHieu th) {
        thuongHieuRepos.update(th);
    }

    @Override
    public ArrayList<ThuongHieu> search(String MaTH) {
        return thuongHieuRepos.search();
    }
    
}
