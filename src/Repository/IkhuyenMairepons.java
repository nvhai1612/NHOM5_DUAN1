/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModel.KhuyenMai;
import DomainModel.ThuongHieu;
import ViewModel.KhuyenMaiVM;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public interface IkhuyenMairepons {
 public ArrayList<KhuyenMai> getListFormDB();
    public Boolean add(KhuyenMai km);
    public Boolean update(KhuyenMai KM);
    public ArrayList<KhuyenMai> search();
     public Boolean existsBymakm(String makm);
    
}
