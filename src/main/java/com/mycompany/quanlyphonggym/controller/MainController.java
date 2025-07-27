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
import com.mycompany.quanlyphonggym.view.GymMemberView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author PC
 */
public class MainController 
{
    private LoginView loginView;
    private ManagerView managerView;
    private GymMemberView residentView;
    private MainView mainView;
    
    public MainController(MainView view)
    {
        this.mainView = view;
        view.addChooseSpecialPersonListener(new ChooseSpecialPersonListener());
        view.addChooseResidentsListener(new ChooseResidentListener());
    }
    public void showMainView() 
    {
        mainView.setVisible(true);
    }
    class ChooseSpecialPersonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            managerView = new ManagerView();
            PTController managerController = new PTController(managerView);
            managerController.showManagerView();
            mainView.setVisible(false);
        }
    }
    
    class ChooseResidentListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            residentView = new GymMemberView();
            MemberController residentController = new MemberController(residentView);
            residentController.showManagerView();
            mainView.setVisible(false);
        }
    }
}
