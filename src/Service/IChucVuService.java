/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.ChucVu;
import ViewModel.ChucVuVM;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface IChucVuService {

    public ArrayList<ChucVuVM> getAll();

    public ArrayList<ChucVu> getAllDoMain();

    public void add(ChucVu cv);

    public void update(ChucVu cv);
    
        public ArrayList<ChucVu> search(String MaCV);

}
