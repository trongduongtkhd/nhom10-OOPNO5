/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyphonggym.controller;

import com.mycompany.quanlyphonggym.action.PTManagement;
import com.mycompany.quanlyphonggym.entity.PersonalTrainer;
import com.mycompany.quanlyphonggym.view.LoginView;
import com.mycompany.quanlyphonggym.view.MainView;
import com.mycompany.quanlyphonggym.view.ManagerView;
import java.util.List;
//////import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class PTController 
{
    private SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
    private PTManagement PTmanager;
    private ManagerView PTView;
    private LoginView loginView;
    private MainView mainView;  
    public PTController(ManagerView view) 
    {
        this.PTView = view;
        PTmanager = new PTManagement();
        view.addAddPTListener(new AddPTListener());
        view.addEditPTListener(new EditPTListener());
        view.addClearListener(new ClearPTListener());
        view.addDeletePTListener(new DeletePTListener());
        view.addListPTSelectionListener(new ListPTSelectionListener());
        view.addSortByNameListener(new SortPTNameListener());
        //view.addSearchAddressListener(new SearchAddressSpecialPersonViewListener());
        //view.addSearchTypeListener(new SearchTypeSpecialPersonViewListener());
        view.addSearchListener(new SearchPTViewListener());
        view.addSearchDialogListener(new SearchPTListener());
        //view.addSearchDialogListener(new SearchSpecialPersonListener());
        view.addSortByYearListener(new SortPTYearListener());
        view.addSortByIDListener(new SortPT_IDListener());
        view.addSortByOpeningDateListener(new SortPTOpeningDateListener());
        view.addCancelSearchPTListener(new CancelSearchPTListener());
        view.addImagePTListener(new ImagePTListener());
        view.addCancelDialogListener(new CancelDialogSearchPTListener());
        view.addUndoListener(new UndoListener());
        view.addStatisticListener(new StatisticViewListener());
        view.addStatisticTypeListener(new StatisticPTTypeListener());
        view.addStatisticAgeListener(new StatisticPTAgeListener());
        view.addStatisticUnderListener(new StatisticClearListener());
    }

    public void showManagerView() 
    {
       List<PersonalTrainer> PTList =  PTmanager.getListPT();
       PTView.setVisible(true);
       PTView.showPTList( PTList);
       PTView.showCountPTList( PTList);
    }

    class AddPTListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            PersonalTrainer PT = PTView.getPTInput();
            if (PT != null) 
            {
               PTmanager.add(PT);
               PTView.showPT(PT);
               PTView.showPTList( PTmanager.getListPT());
               PTView.showCountPTList( PTmanager.getListPT());
               PTView.showMessage("Thêm thành công!");
            }
        }
    }
    
    class EditPTListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            PersonalTrainer pt = PTView.getPTInput();
            if (pt != null) 
            {
                try {
                    PTmanager.edit(pt);
                } catch (ParseException ex) {
                    Logger.getLogger(PTController.class.getName()).log(Level.SEVERE, null, ex);
                }
               PTView.showPT(pt);
               PTView.showPTList( PTmanager.getListPT());
               PTView.showCountPTList( PTmanager.getListPT());
               PTView.showMessage("Cập nhật thành công!");
            }
        }
    }
    
    class DeletePTListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            PersonalTrainer pt = PTView.getPTInput();
            if (pt != null) 
            {
                PTmanager.delete(pt);
               PTView.clearPTInfo();
               PTView.showPTList( PTmanager.getListPT());
               PTView.showCountPTList( PTmanager.getListPT());
               PTView.showMessage("Xóa thành công!");
            }
        }
    }
    
    
    class ImagePTListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            PTView.PTImage();
        }
    }
    /**
     * Lớp ClearSpecialPersonListener 
     * chứa cài đặt cho sự kiện click button "Clear"
     * 
     * @author viettuts.vn
     */
    class ClearPTListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
           PTView.clearPTInfo();
        }
    }

    /**
     * Lớp SortSpecialPersonGPAListener 
     * chứa cài đặt cho sự kiện click button "Sort By GPA"
     * 
     * @author viettuts.vn
     *
    /**
     * Lớp SortSpecialPersonGPAListener 
     * chứa cài đặt cho sự kiện click button "Sort By Name"
     * 
     * @author viettuts.vn
     */
    class SortPTNameListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
             PTmanager.sortPTByName();
           PTView.showPTList( PTmanager.getListPT());
        }
    }
    
    class SortPTYearListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            PTmanager.sortPTByBirthDay();
            PTView.showPTList( PTmanager.getListPT());
        }
    }
    
    class SortPT_IDListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            PTmanager.sortPTByID();
           PTView.showPTList( PTmanager.getListPT());
        }
    }
    
    class SortPTOpeningDateListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
             PTmanager.sortPTByOpeningDate();
           PTView.showPTList( PTmanager.getListPT());
        }
    }
    
    class SearchPTViewListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            PTView.searchNamePTInfo();
        }
    }
    
    class StatisticViewListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
           PTView.displayStatisticView();
        }
    }
    
    class SearchPTListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            boolean kt = false;
            List<PersonalTrainer> temp = new ArrayList<>();
            int check = PTView.getChooseSelectSearch();
            String search = PTView.validateSearch();
            if(check == 1){
                // Tìm kiếm theo tên
                temp =  PTmanager.searchPTName(search);
            }else if(check == 2){
                // Tìm kiếm theo địa chỉ
                temp =  PTmanager.searchPTAddress(search);
            }else if(check == 3){
                // Tìm kiếm theo loại đối tượng
                temp =  PTmanager.searchPTYear(search);
            }
            if(!temp.isEmpty())PTView.showPTList(temp);
            else PTView.showMessage("Không tìm thấy kết quả!");
        }
    }
    
    class CancelDialogSearchPTListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            PTView.cancelDialogSearchPTInfo();
        }
    }
    
    class CancelSearchPTListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
         PTView.showPTList( PTmanager.getListPT());
         PTView.cancelSearchID_PT();
        }
    }
    
    class UndoListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            mainView = new MainView();
            MainController mainController = new MainController(mainView);
            mainController.showMainView();
           PTView.setVisible(false);
        }
    }
    /**
     * Lớp ListSpecialPersonSelectionListener 
     * chứa cài đặt cho sự kiện chọn specialPerson trong bảng specialPerson
     */
    class ListPTSelectionListener implements ListSelectionListener 
    {
        public void valueChanged(ListSelectionEvent e) 
        {
            try {
                PTView.fillPTFromSelectedRow();
            } catch (ParseException ex) {
                Logger.getLogger(PTController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class StatisticPTTypeListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
          PTView.displayStatisticView();
            //managerSpecialPerson.sortSpecialPersonByID();
           PTView.showStatisticTypePT( PTmanager.getListPT());
        }
    }
    class StatisticPTAgeListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
           PTView.displayStatisticView();
            //managerSpecialPerson.sortSpecialPersonByID();
           PTView.showStatisticAgePT( PTmanager.getListPT());
        }
    }
    
    class StatisticClearListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
           PTView.UnderViewPT();
            //managerSpecialPerson.sortSpecialPersonByID();
            //specialPersonView.showStatisticAgeSpecialPersons(managerSpecialPerson.getListSpecialPersons());
        }
    }
}
