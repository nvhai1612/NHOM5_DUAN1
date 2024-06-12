/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModel.HoaDon;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public interface IHoaDonRepos {
    public ArrayList<HoaDon> getListFormDB();
    public Boolean add(HoaDon hd);
    public Boolean update(HoaDon hd);
    public Boolean delete(UUID id);
}
