/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyphonggym.action;

import com.mycompany.quanlyphonggym.entity.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class CheckLogin {
private static List<User> users = new ArrayList<>();
   
   static{
       users.add(new User("duong", "1"));
         users.add(new User("bac", "2"));
           users.add(new User("huy", "3"));
   }
   public static boolean checkUser(User user){
       for(User u :users){
           if(u.getUserName().equals(user.getUserName()) && user.getPassword().equals(user.getPassword())){
               return true;
           }
       }
          return false ;
//>>>>>>> a5551f4 (login)
    }
}
