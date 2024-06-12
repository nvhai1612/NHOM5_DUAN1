/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.MauSac;
import ViewModel.MauSacVM;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface IMauSacService {
    ArrayList<MauSacVM> getAll();
    ArrayList<MauSac> getAllDomain();
    void add(MauSac ms);
    void update(MauSac ms);
    ArrayList<MauSac> search (String MaMS);
}
