/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyphonggym.action;

import com.mycompany.quanlyphonggym.entity.PersonalTrainer;
import com.mycompany.quanlyphonggym.entity.PTTrainerXML;
import com.mycompany.quanlyphonggym.utils.FileUtils;
import com.mycompany.quanlyphonggym.view.ManagerView;
import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

/**
 *
 * @author PC
 */
public class PTManagement 
{
    private static final String PT_FILE_NAME = "PersonTrainer.xml";
    private List<PersonalTrainer> PTlist;
    public PTManagement() {
        this.PTlist= readPTList();
        if (PTlist == null) {
            PTlist = new ArrayList<PersonalTrainer>();
        }
    }
    /**
     * Lưu các đối tượng SpecialPerson vào file SpecialPerson.xml
     * 
     * @param specialPersons
     */
    public void writeListPT(List<PersonalTrainer> pt) 
    {
        PTTrainerXML PTTrainerXML = new PTTrainerXML();
       PTTrainerXML.setPT(pt);
        FileUtils.writeXMLtoFile(PT_FILE_NAME, PTTrainerXML);
    }

    /**
     * Đọc các đối tượng SpecialPerson từ file SpecialPerson.xml
     * 
     * @return list SpecialPerson
     */
    public List<PersonalTrainer> readPTList() 
    {
        List<PersonalTrainer> list = new ArrayList<PersonalTrainer>();
        PTTrainerXML PTTrainerXML = (PTTrainerXML) FileUtils.readXMLFile(PT_FILE_NAME, PTTrainerXML.class);
        if (PTTrainerXML!= null) 
        {
            list =PTTrainerXML.getPT();
        }
        return list;
    }
    
    /* Hiển thị listSpecialPersons theo tên */
    public List<PersonalTrainer> searchPTName(String search){
        List<PersonalTrainer>temp = new ArrayList<PersonalTrainer>();
        for(PersonalTrainer person :PTlist){
            if(person.getName().toLowerCase().contains(search.toLowerCase())){
                temp.add(person);
            }
        }
        return temp;
    }  
    /* Hiển thị listSpecialPersons theo nơi ở */
    public List<PersonalTrainer> searchPTAddress(String search){
        List<PersonalTrainer>temp = new ArrayList<PersonalTrainer>();
        for(PersonalTrainer person : PTlist){
            if(person.getAddress().toLowerCase().contains(search.toLowerCase())){
                temp.add(person);
            }
        }
        return temp;
    }
     /* Hiển thị listSpecialPersons theo năm sinh */
    public List<PersonalTrainer> searchPTYear(String year) {
        List<PersonalTrainer> temp = new ArrayList<>();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

        for (PersonalTrainer person : PTlist) {
            // Chuyển đổi ngày sinh thành chuỗi năm
            String personYearStr = yearFormat.format(person.getBirthday());

            // So sánh chuỗi năm với năm tìm kiếm
            if (personYearStr.equals(year)) {
                temp.add(person);
            }
        }

        return temp;
    }  
    /**
     * thêm SpecialPerson vào listSpecialPersons và lưu listSpecialPersons vào file
     * 
     * @param SpecialPerson
     */
    public void add(PersonalTrainer PT) 
    {
        int max = 0;
        for (int i=0;i<PTlist.size();i++)
        {
            if(PTlist.get(i).getId()>max) max=PTlist.get(i).getId();
        }
        PT.setId(max+1);
        PTlist.add(PT);
         writeListPT(PTlist);
    }

    /**
     * cập nhật SpecialPerson vào listSpecialPersons và lưu listSpecialPersons vào file
     * 
     * @param SpecialPerson
     */
    public void edit(PersonalTrainer pt ) throws ParseException 
    {
        SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
        int size = PTlist.size();
        for (int i = 0; i < size; i++) 
        {
            if (PTlist.get(i).getId() ==  pt.getId()) 
            {
               PTlist.get(i).setName( pt.getName());
               PTlist.get(i).setBirthday( pt.getBirthday());
               PTlist.get(i).setAddress( pt.getAddress());
                PTlist.get(i).setOpeningDate( pt.getOpeningDate());
               PTlist.get(i).setType( pt.getType());
               PTlist.get(i).setImage( pt.getImage());
               writeListPT(PTlist);
                break;
            }
        }
    }

    /**
     * xóa SpecialPerson từ listSpecialPersons và lưu listSpecialPersons vào file
     * 
     * @param SpecialPerson
     */
    
    public void image(PersonalTrainer PT) 
    {
        
    }
      
    public boolean delete(PersonalTrainer PT) {
         boolean isFound = false;
        int size = PTlist.size();
        for (int i = 0; i < size; i++) 
        {
            if (PTlist.get(i).getId() ==  PT.getId()) 
            {
                 PT = PTlist.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) 
        {
           PTlist.remove( PT);
            writeListPT(PTlist);
            return true;
        }
        return false;
    }

    
    
    /**
     * sắp xếp danh sách SpecialPerson theo name theo tứ tự tăng dần
     */
    
    
    public void sortPTByName() 
    {
        Collections.sort(PTlist, new Comparator<PersonalTrainer>() {
            public int compare(PersonalTrainer p1, PersonalTrainer p2) {
                Collator collator = Collator.getInstance(new Locale("vi", "VN"));
                // So sánh tên
                int result = collator.compare(p1.getLastName(), p2.getLastName());
                if (result == 0) {
                    // Nếu tên bằng nhau, so sánh họ lót
                    result = collator.compare(p1.getFirstName(), p2.getFirstName());
                }
                return result;
            }
        });
        //Collections.sort(listSpecialPersons, (p1, p2) -> collator.compare(p1.getLastName(), p2.getLastName()));
    }
    
    public void sortPTByID() 
    {
        Collections.sort(PTlist, new Comparator<PersonalTrainer>() 
        {
            public int compare(PersonalTrainer PT1, PersonalTrainer PT2) 
            {
                if (PT1.getId() > PT2.getId()) 
                {
                    return 1;
                }
                return -1;
            }
        });
    }
    
    public void sortPTByOpeningDate() 
    {
        Collections.sort(PTlist, new Comparator<PersonalTrainer>() 
        {
            public int compare(PersonalTrainer PT1, PersonalTrainer PT2) 
            {
                return PT1.getOpeningDate().compareTo(PT2.getOpeningDate());
            }
        });
    }
    
    /**
     * sắp xếp danh sách SpecialPerson theo năm sinh theo tứ tự tăng dần
     */
    public void sortPTByBirthDay() {
        Collections.sort(PTlist, new Comparator<PersonalTrainer>() {
            public int compare(PersonalTrainer person1, PersonalTrainer person2) {
                return person1.getBirthday().compareTo(person2.getBirthday());
            }
        });
    }

    public List<PersonalTrainer>getListPT() 
    {
        return PTlist;
    }

    public void setListPT(List<PersonalTrainer> listPT) 
    {
        this.PTlist = listPT;
    }
}
