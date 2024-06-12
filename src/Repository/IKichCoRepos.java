/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModel.KichCo;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface IKichCoRepos {
    public ArrayList<KichCo> getListFormDB();
    public Boolean add(KichCo kc);
    public Boolean update(KichCo kc);
    public ArrayList<KichCo> search();
}
