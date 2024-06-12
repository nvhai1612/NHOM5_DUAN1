/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.SPCT;
import ViewModel.SPCTVM;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface ISPCTService {
    ArrayList<SPCTVM> getAll();
    ArrayList<SPCT>getAllDoMain();
    void add(SPCT ctsp);
    void update(SPCT ctsp);
    ArrayList<SPCT> search (String MaCTSP);
}
