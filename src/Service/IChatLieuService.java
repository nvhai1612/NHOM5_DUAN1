/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.ChatLieu;
import ViewModel.ChatLieuVM;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface IChatLieuService {
    ArrayList<ChatLieuVM> getAll();
    ArrayList<ChatLieu> getAllDomain();
    void add(ChatLieu cl);
    void update(ChatLieu cl);
    ArrayList<ChatLieu> search (String MaCL);
}
