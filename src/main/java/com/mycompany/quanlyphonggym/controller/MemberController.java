/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyphonggym.controller;

import com.mycompany.quanlyphonggym.action.ManagerResidents;
import com.mycompany.quanlyphonggym.action.ManagerResidents;
import com.mycompany.quanlyphonggym.entity.GymMember;
import com.mycompany.quanlyphonggym.entity.GymMember;
import com.mycompany.quanlyphonggym.view.LoginView;
import com.mycompany.quanlyphonggym.view.MainView;
import com.mycompany.quanlyphonggym.view.ManagerView;
import com.mycompany.quanlyphonggym.view.GymMemberView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author PC
 */
public class MemberController 
{
    private LoginView loginView;
    private ManagerView managerView;
    private GymMemberView residentView;
    private MainView mainView;
    private ManagerResidents managerResidents;
    
    public MemberController(GymMemberView view)
    {
        this.residentView=view;
        this.managerResidents = new ManagerResidents();
        view.addUndoListener(new UndoListener());
        view.addAddResidentListener(new AddResidentListener());
        view.addListResidentSelectionListener(new ListResidentsSelectionListener());
        view.addEditResidentListener(new EditResidentListener());
        view.addClearListener(new ClearResidentListener());
        view.addDeleteSpecialPersonListener(new DeleteSpecialPersonListener());
        view.addSortSpecialPersonListener(new SortResidentsListener());
        view.addSearchListener(new SearchResidentViewListener());
        //view.addSearchListener(new SearchResidentViewListener());
        view.addSearchDialogListener(new SearchResidentListener());
        view.addCancelSearchResidentListener(new CancelSearchResidentListener());
        view.addCancelDialogListener(new CancelDialogSearchResidentListener());
    }
    
    public void showManagerView() 
    {
        List<GymMember> residentsList = managerResidents.getListResidents();
        residentView.setVisible(true);
        residentView.showListResidents(residentsList);
        residentView.showCountListResidents(residentsList);
        residentView.showStatisticTypeCMT(residentsList);
        residentView.showStatisticIDFamily(residentsList);
    }
    
    class UndoListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            mainView = new MainView();
            MainController mainController = new MainController(mainView);
            mainController.showMainView();
            residentView.setVisible(false);
        }
    }
    
    class AddResidentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            GymMember residents = residentView.getResidentInfo();
            if (residents != null) {
                try {
                    if (!managerResidents.isCMTUnique(residents)) {
                        // Lấy thông báo từ ngoại lệ và hiển thị
                        throw new IllegalArgumentException("Lỗi: Số chứng minh thư đã tồn tại!");
                    }
                    
                    if (!managerResidents.isHouseholdUnique(residents)) {
                        // Lấy thông báo từ ngoại lệ và hiển thị
                        throw new IllegalArgumentException("Lỗi: Đã có chủ hộ đối với số hộ khẩu này");
                    }
                    
                    managerResidents.add(residents);
                    residentView.showResidents(residents);
                    residentView.showListResidents(managerResidents.getListResidents());
                    residentView.showCountListResidents(managerResidents.getListResidents());
                    residentView.showStatisticTypeCMT(managerResidents.getListResidents());
                    residentView.showStatisticIDFamily(managerResidents.getListResidents());
                    residentView.showMessage("Thêm thành công!");
                } catch (IllegalArgumentException ex) {
                    residentView.showMessage(ex.getMessage());
                }
            }
        }
    }
    
    class EditResidentListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            GymMember resident = residentView.getResidentInfo();
            if (resident != null) 
            {
                try {
                    managerResidents.edit(resident);
                } catch (ParseException ex) {
                    Logger.getLogger(PTController.class.getName()).log(Level.SEVERE, null, ex);
                }
                residentView.showResidents(resident);
                residentView.showListResidents(managerResidents.getListResidents());
                residentView.showCountListResidents(managerResidents.getListResidents());
                residentView.showStatisticTypeCMT(managerResidents.getListResidents());
                residentView.showStatisticIDFamily(managerResidents.getListResidents());
                residentView.showMessage("Cập nhật thành công!");
            }
        }
    }
    
class DeleteSpecialPersonListener implements ActionListener 
{
    public void actionPerformed(ActionEvent e) 
    {
        GymMember resident = residentView.getResidentInfo();
        if (resident != null) 
        {
            boolean result = managerResidents.delete(resident);
            if (result) {
                residentView.clearResidentInfo();
                residentView.showListResidents(managerResidents.getListResidents());
                residentView.showCountListResidents(managerResidents.getListResidents());
                residentView.showStatisticTypeCMT(managerResidents.getListResidents());
                residentView.showStatisticIDFamily(managerResidents.getListResidents());
                residentView.showMessage("Xóa thành công!");
            } else {
                residentView.showResidents(resident); // Hiển thị thông tin hội viên ra màn hình
                residentView.showMessage(resident.toString());
            }
        }
    }
}
    class ListResidentsSelectionListener implements ListSelectionListener 
    {
        public void valueChanged(ListSelectionEvent e) 
        {
            List<GymMember> residentsList = managerResidents.getListResidents();
            try {
                residentView.fillResidentFromSelectedRow(residentsList);
                //residentView.fillResidentFromSelectedRow();
                
            } catch (ParseException ex) {
                Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class ClearResidentListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            residentView.clearResidentInfo();
        }
    }
    
    class SortResidentsListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            boolean kt = false;
            List<GymMember> temp = new ArrayList<>();
            int check = residentView.getChooseSelectSort();
            if(check == 1){
                managerResidents.sortResidentsByID();
                residentView.showListResidents(managerResidents.getListResidents());
            }else if(check == 2){
                managerResidents.sortResidentsByName();
                residentView.showListResidents(managerResidents.getListResidents());
            }else if(check == 3){
                managerResidents.sortResidentsByIDFamily();
                residentView.showListResidents(managerResidents.getListResidents());
            } else
                residentView.showMessage("Bạn chưa chọn tiêu chí sắp xếp");
        }
    }
    
    class SearchResidentViewListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            residentView.searchResidentInfo();
        }
    }
    
    class CancelDialogSearchResidentListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            residentView.cancelDialogSearchResidentInfo();
        }
    }
    
    class CancelSearchResidentListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            residentView.showListResidents(managerResidents.getListResidents());
            residentView.cancelSearchResident();
        }
    }
    
    class SearchResidentListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            boolean kt = false;
            List<GymMember> temp = new ArrayList<>();
            int check = residentView.getChooseSelectSearch();
            String search = residentView.validateSearch();
            if(check == 1){
                // Tìm kiếm theo tên
                temp = managerResidents.searchResidentIDFamily(search);
            }else if(check == 2){
                // Tìm kiếm theo địa chỉ
                temp = managerResidents.searchResidentName(search);
            }else if(check == 3){
                // Tìm kiếm theo loại đối tượng
                temp = managerResidents.searchResidentYear(search);
            }else if(check == 4){
                // Tìm kiếm theo loại đối tượng
                temp = managerResidents.searchResidentAddress(search);
            }
            if(!temp.isEmpty())residentView.showListResidents(temp);
            else residentView.showMessage("Không tìm thấy kết quả!");
        }
    }
}
