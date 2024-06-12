/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModel.MauSac;
import ViewModel.MauSacVM;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface IMauSacRepos {
    public ArrayList<MauSac> getListFormDB();
    public Boolean add(MauSac ms);
    public Boolean update(MauSac ms);
    public ArrayList<MauSac> search();
}
