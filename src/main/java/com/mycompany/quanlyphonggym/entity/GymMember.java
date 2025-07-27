/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyphonggym.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC
 */

@XmlRootElement(name = "GymMembers")
@XmlAccessorType(XmlAccessType.FIELD)
public class GymMember extends Person
{
    private String IDMember;
    private String sex;
    private String GoiTap;
    private String birthPlace;
    private String typeCMT;
    private String CMT;
    private String phoneNumber;
    
    public GymMember()
    {
        this.CMT="";
        this.typeCMT="<none>";
        this.phoneNumber="";
    }
    
    public GymMember(int id, String name, Date birthday, String address, String IDFamily, String sex, String role, String birthPlace, String typeCMT, String CMT, String phoneNumber)
    {
        super(id,name,birthday,address);
        SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
        this.IDMember = IDFamily;
        // them
        this.CMT = CMT; 
        // them
        this.sex = sex;
        this.GoiTap = role;
        this.birthPlace = birthPlace;
        this.typeCMT = typeCMT;
        this.phoneNumber = phoneNumber;
    }
    
    public String getIDFamily()
    {
        return this.IDMember;
    }
    
    public void setIDFamily(String number)
    {
        this.IDMember = number;
    }
    
    public String getSex()
    {
        return this.sex;
    }
    
    public void setSex(String _sex)
    {
        this.sex=_sex;
    }
    
    public String getRole()
    {
        return this.GoiTap;
    }
    
    public void setRole(String _role)
    {
        this.GoiTap=_role;
    }
    
    public String getBirthPlace()
    {
        return this.birthPlace;
    }
    
    public void setBirthPlace(String _birthPlace)
    {
        this.birthPlace=_birthPlace;
    }
    
    public String getTypeCMT()
    {
        return this.typeCMT;
    }
    
    public void setTypeCMT(String typeCMT)
    {
        this.typeCMT = typeCMT;
    }
    
    public String getCMT()
    {
        return this.CMT;
    }
    
    public void setCMT(String cmt)
    {
        this.CMT=cmt;
    }
    
    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    public String getGoitap(){
    return this.IDMember;
    }
            
    public String toString()
    {
        return "ID: "+this.getId()+"\n"+
               "Họ tên: "+this.getName()+"\n"+
               "Ngày sinh: "+new SimpleDateFormat("dd/MM/yyyy").format(this.getBirthday())+"\n"+
               "Địa chỉ: "+this.getAddress()+"\n"+
               "Số hộ khẩu: "+this.IDMember+"\n"+
               "Vai trò: "+this.GoiTap+"\n"+
               "Nơi sinh: "+this.birthPlace+"\n"+
               "Loại CMT: "+this.typeCMT+"\n"+
               "Số CMT: "+this.CMT+"\n"+
               "Số điện thoại: "+this.phoneNumber;
    }
}
