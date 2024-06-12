/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModel.ThuongHieu;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface IThuongHieuRepos {
    public ArrayList<ThuongHieu> getListFormDB();
    public Boolean add(ThuongHieu th);
    public Boolean update(ThuongHieu th);
    public ArrayList<ThuongHieu> search();
}
