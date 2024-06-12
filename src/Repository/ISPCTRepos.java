/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModel.SPCT;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface ISPCTRepos {
    public ArrayList<SPCT> getListFormDB();
    public Boolean add(SPCT spct);
    public Boolean update(SPCT spct);
    public Boolean delete(String id);
    public ArrayList<SPCT> search();
}
