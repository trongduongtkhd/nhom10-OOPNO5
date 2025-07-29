/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quanlyphonggym.QuanLyPhongGym;

import com.mycompany.quanlyphonggym.controller.LoginController;
import com.mycompany.quanlyphonggym.view.LoginView;
import java.awt.EventQueue;
import javax.swing.JFrame;  
public class QuanLyPhongGym 
{
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginView view = new LoginView();
                LoginController controller = new LoginController(view);
                // hiển thị màn hình login
                controller.showLoginView();
            }
        });
    }
}
