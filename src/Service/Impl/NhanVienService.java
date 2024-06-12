/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import DomainModel.NhanVien;
import Repository.Impl.NhanVienRepos;
import Service.INhanVienService;
import ViewModel.NhanVienVM;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author Admin
 */
public class NhanVienService implements INhanVienService{
    private NhanVienRepos nvRepo = new NhanVienRepos();
    @Override
    public ArrayList<NhanVienVM> getAll() {
        ArrayList<NhanVien> listNV = nvRepo.getListFormDB();
        ArrayList<NhanVienVM> listNVVM = new ArrayList<>();
        for (NhanVien nv : listNV) {
            NhanVienVM nvvm = new NhanVienVM(nv.getId(),nv.getMaNV(), nv.getTenNV(), nv.getCCCD(), nv.getDiaChi(),
                    nv.getSDT(), nv.getEmail(), nvRepo.SelectById(nv.getIdCV()), nv.getGioiTinh() , nv.getTrangThaiNV(),nv.getNgaySinh());
            listNVVM.add(nvvm);
        }
        return listNVVM;
    }

    @Override
    public ArrayList<NhanVien> getAllDoMain() {
       return nvRepo.getListFormDB();
    }

    @Override
    public void add(NhanVien nv) {
        nvRepo.add(nv);
    }

    @Override
    public void update(NhanVien nv) {
        nvRepo.update(nv);
    }

    @Override
    public ArrayList<NhanVien> search(String MaNV) {
    return nvRepo.search(MaNV);
    }

    @Override
    public Boolean existsByMaNV(String MaNV) {
        return nvRepo.existsByMaNV(MaNV);
    }

    @Override
    public Optional<NhanVien> CheckLogin(String Email, String MatKhau) {
    return nvRepo.CheckLogin(Email, MatKhau);
    }
    
}
