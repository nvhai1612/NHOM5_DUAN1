/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import DomainModel.ChatLieu;
import Repository.Impl.ChatLieuRepos;
import Service.IChatLieuService;
import ViewModel.ChatLieuVM;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ChatLieuService implements IChatLieuService{
    private ChatLieuRepos ChatLieuRepos = new ChatLieuRepos();

    @Override
    public ArrayList<ChatLieuVM> getAll() {
        ArrayList<ChatLieu> listCL = ChatLieuRepos.getListFormDB();
        ArrayList<ChatLieuVM> listVM = new ArrayList<>();
        for (ChatLieu cl : listCL) {
            ChatLieuVM clvm = new ChatLieuVM(cl.getId(), cl.getMaCL(), cl.getTenCL(), cl.getTrangThaiCL());
            listVM.add(clvm);
        }
        return listVM;
    }

    @Override
    public ArrayList<ChatLieu> getAllDomain() {
        return ChatLieuRepos.getListFormDB();
    }

    @Override
    public void add(ChatLieu cl) {
        ChatLieuRepos.add(cl);
    }

    @Override
    public void update(ChatLieu cl) {
        ChatLieuRepos.update(cl);
    }

    @Override
    public ArrayList<ChatLieu> search(String MaCL) {
        return ChatLieuRepos.search();
    }
    
}
