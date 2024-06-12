/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.SanPham;
import ViewModel.SanPhamVM;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface ISanPhamService {
    ArrayList<SanPhamVM> getAll();
    ArrayList<SanPham> getAllDomain();
    void add(SanPham sp);
    void update(SanPham sp);
    ArrayList<SanPham> search (String MaSP);
}
