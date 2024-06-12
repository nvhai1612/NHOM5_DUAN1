/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import DomainModel.ChucVu;
import Repository.Impl.ChucVuRepos;
import Service.IChucVuService;
import ViewModel.ChucVuVM;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ChucVuService implements IChucVuService{
    private  ChucVuRepos cvRepo = new  ChucVuRepos();
    
    @Override
    public ArrayList<ChucVuVM> getAll() {
        ArrayList<ChucVu> listCV = cvRepo.getListFormDB();
        ArrayList<ChucVuVM> listCVVM = new ArrayList<>();
        for (ChucVu cv : listCV) {
            ChucVuVM cvvm = new ChucVuVM(cv.getId(), cv.getMaCV(), cv.getTenCV());
            listCVVM.add(cvvm);
        }
        return listCVVM;
    }

    @Override
    public ArrayList<ChucVu> getAllDoMain() {
    return cvRepo.getListFormDB();
    }

    @Override
    public void add(ChucVu cv) {
    cvRepo.add(cv);
    }

    @Override
    public void update(ChucVu cv) {
    cvRepo.add(cv);
    }
    
}
