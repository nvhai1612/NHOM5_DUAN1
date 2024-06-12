/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.ThuongHieu;
import ViewModel.ThuongHieuVM;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface IThuongHieuService {
    ArrayList<ThuongHieuVM> getAll();
    ArrayList<ThuongHieu> getAllDomain();
    void add(ThuongHieu th);
    void update(ThuongHieu th);
    ArrayList<ThuongHieu> search (String MaTH);
}
