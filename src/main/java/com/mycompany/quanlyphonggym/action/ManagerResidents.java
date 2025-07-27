/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyphonggym.action;

import com.mycompany.quanlyphonggym.entity.GymMemberXML;
import com.mycompany.quanlyphonggym.entity.GymMember;
import com.mycompany.quanlyphonggym.utils.FileUtils;
import com.mycompany.quanlyphonggym.view.GymMemberView;
import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class ManagerResidents 
{
    private static final String RESIDENT_FILE_NAME = "GymMember.xml";
    private List<GymMember> listResidents;
    private GymMemberView residentView;
    
    public ManagerResidents()
    {
        this.listResidents = readListResidents();
        if (listResidents == null) {
            listResidents = new ArrayList<GymMember>();
        }
    }
    
    public List<GymMember> readListResidents() 
    {
        List<GymMember> list = new ArrayList<GymMember>();
        GymMemberXML residentXML = (GymMemberXML)FileUtils.readXMLFile(RESIDENT_FILE_NAME, GymMemberXML.class);
        if (residentXML != null) 
        {
            list = residentXML.getResidents();
        }
        return list;
    }
    
    public void writeListResidents(List<GymMember> residents) 
    {
        GymMemberXML residentXML = new GymMemberXML();
        residentXML.setResidents(residents);
        FileUtils.writeXMLtoFile(RESIDENT_FILE_NAME, residentXML);
    }
    
    public List<GymMember> searchResidentName(String search){
        List<GymMember>temp = new ArrayList<GymMember>();
        for(GymMember person : listResidents){
            if(person.getName().toLowerCase().contains(search.toLowerCase())){
                temp.add(person);
            }
        }
        return temp;
    }
    
    /* Hiển thị listSpecialPersons theo nơi ở */
    public List<GymMember> searchResidentAddress(String search){
        List<GymMember>temp = new ArrayList<GymMember>();
        for(GymMember person : listResidents){
            if(person.getAddress().toLowerCase().contains(search.toLowerCase())){
                temp.add(person);
            }
        }
        return temp;
    }
    
    public List<GymMember> searchResidentIDFamily(String search){
        List<GymMember>temp = new ArrayList<GymMember>();
        for(GymMember person : listResidents){
            if(person.getIDFamily().contains(search)){
                temp.add(person);
            }
        }
        return temp;
    }
     /* Hiển thị listSpecialPersons theo năm sinh */
    public List<GymMember> searchResidentYear(String year) {
        List<GymMember> temp = new ArrayList<>();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

        for (GymMember person : listResidents) {
            // Chuyển đổi ngày sinh thành chuỗi năm
            String personYearStr = yearFormat.format(person.getBirthday());

            // So sánh chuỗi năm với năm tìm kiếm
            if (personYearStr.equals(year)) {
                temp.add(person);
            }
        }

        return temp;
    }
    
    public void add(GymMember resident) 
    {
        int max = 0;
        for (int i=0;i<listResidents.size();i++)
        {
            if(listResidents.get(i).getId()>max) max=listResidents.get(i).getId();
        }
        resident.setId(max+1);
        listResidents.add(resident);
        writeListResidents(listResidents);
    }
    
    /*public boolean validateAdd(Residents resident) {
        try {
            // Kiểm tra số CMT không trùng
            if (!isCMTUnique(resident.getCMT())) {
                throw new IllegalArgumentException("Số CMT đã tồn tại");
            }

            // Kiểm tra vai trò "Chủ hộ" không trùng số hộ khẩu
            if ("Chủ hộ".equals(resident.getRole()) && !isHouseholdUnique(resident.getIDFamily())) {
                throw new IllegalArgumentException("Số hộ khẩu đã tồn tại cho vai trò 'Chủ hộ'");
            }

            return true; // Điều kiện kiểm tra thành công
        } catch (IllegalArgumentException ex) {
            // Bắt ngoại lệ và xử lý thông báo
            showMessage("Lỗi: " + ex.getMessage());
            return false; // Điều kiện kiểm tra không thành công
        }
    }*/
    
    // Hàm kiểm tra số CMT không trùng
    public boolean isCMTUnique(GymMember resident) {
        String cmt=resident.getCMT();
        for (GymMember existingResident : listResidents) {
            if (existingResident.getCMT().equals(cmt)) {
                return false; // Trùng số CMT
            }
        }
        return true; // Số CMT không trùng
    }

    // Hàm kiểm tra số hộ khẩu không trùng cho vai trò "Chủ hộ"
    public boolean isHouseholdUnique(GymMember resident) {
        String IDFamily=resident.getIDFamily();
        String role = resident.getRole();
        for (GymMember existingResident : listResidents) {
            if ("Chủ hộ".equals(role) && existingResident.getIDFamily().equals(IDFamily) && existingResident.getRole().equals(role)) {
                return false; // Trùng số hộ khẩu cho vai trò "Chủ hộ"
            }
        }
        return true; // Số hộ khẩu không trùng cho vai trò "Chủ hộ"
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(residentView, message);
    }
    
    public void edit(GymMember resident) throws ParseException 
    {
        SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
        int size = listResidents.size();
        for (int i = 0; i < size; i++) 
        {
            if (listResidents.get(i).getId() == resident.getId()) 
            {
                listResidents.get(i).setIDFamily(resident.getIDFamily());
                listResidents.get(i).setName(resident.getName());
                listResidents.get(i).setRole(resident.getRole());
                listResidents.get(i).setBirthday(resident.getBirthday());
                listResidents.get(i).setAddress(resident.getAddress());
                listResidents.get(i).setSex(resident.getSex());
                listResidents.get(i).setTypeCMT(resident.getTypeCMT());
                listResidents.get(i).setCMT(resident.getCMT());
                listResidents.get(i).setBirthPlace(resident.getBirthPlace());
                listResidents.get(i).setPhoneNumber(resident.getPhoneNumber());

                writeListResidents(listResidents);
                break;
            }
        }
    }
    
    public boolean delete(GymMember resident) {
    boolean isFound = false;
    for (int i = 0; i < listResidents.size(); i++) {
        // So sánh cả ID và mã hộ khẩu để chắc chắn
        if (listResidents.get(i).getId() == resident.getId()) {
            listResidents.remove(i);
            isFound = true;
            break;
        }
    }
    if (isFound) {
        // Không cần cập nhật lại ID nếu bạn dùng ID tự tăng, chỉ cần ghi lại danh sách
        writeListResidents(listResidents);
        return true;
    }
    return false;
}
    
    public void sortResidentsByName() 
    {
        Collections.sort(listResidents, new Comparator<GymMember>() {
            public int compare(GymMember p1, GymMember p2) {
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
    
    public void sortResidentsByIDFamily() {
        Collections.sort(listResidents, new Comparator<GymMember>() {
            public int compare(GymMember person1, GymMember person2) {
                return person1.getIDFamily().compareTo(person2.getIDFamily());
            }
        });
    }
    
    public void sortResidentsByID() 
    {
        Collections.sort(listResidents, new Comparator<GymMember>() 
        {
            public int compare(GymMember person1, GymMember person2) 
            {
                if (person1.getId() > person2.getId()) 
                {
                    return 1;
                }
                return -1;
            }
        });
    }
    
    public List<GymMember> getListResidents() 
    {
        return this.listResidents;
    }
}
