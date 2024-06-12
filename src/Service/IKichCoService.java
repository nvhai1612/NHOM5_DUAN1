/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.KichCo;
import ViewModel.KichCoVM;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface IKichCoService {
    ArrayList<KichCoVM> getAll();
    ArrayList<KichCo> getAllDomain();
    void add(KichCo kc);
    void update(KichCo kc);
    ArrayList<KichCo> search (String MaKC);
}
