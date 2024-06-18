/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.KhuyenMai;

import DomainModel.SanPham;

import ViewModel.SanPhamVM;
import ViewModel.khuyeMaiVM;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IkhuyenMaiSeviec {
     ArrayList<khuyeMaiVM> getAll();
    ArrayList<KhuyenMai> getAllDomain();
    void add(KhuyenMai km);
    void update(KhuyenMai km);
    ArrayList<KhuyenMai> search (String tenkm);
    public Boolean existsBymakm(String makm);
}

