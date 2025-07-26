/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyphonggym.entity;
import com.mycompany.quanlyphonggym.utils.FileUtils;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC
 */

@XmlRootElement(name = "PersonTrainer")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonalTrainer extends Person implements Serializable
{
    private static final long serialVersionUID = 1L;
   // private int id;
  //  private String name;
  //  private int year;
  //  private String address;
    private Date OpeningDate;
    private String type;
    private byte[] picture;
    private String shift; 
    //private SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
    public PersonalTrainer() 
    {
        
    }
    public PersonalTrainer(int id, String name, Date birthday, String address, String OpeningDate, String type,String shift, byte[] image) throws ParseException 
    {
        super();
        SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
        this.id = id;
        this.name = name;
        this.birthday = birthday;   
        this.address = address;   
        this.OpeningDate = fDate.parse(OpeningDate);
        this.type=type;
          this.shift = shift;
        this.picture=image;
    }  

    public String getShift() {
    return shift;
}
  public void setShift(String shift) {
    this.shift = shift;
} 

    public Date getOpeningDate() 
    {
        return this.OpeningDate;
    }

    public void setOpeningDate(Date OpeningDate) 
    {
        //SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
        this.OpeningDate = OpeningDate;
    }
    
    public String getType() 
    {
        return this.type;
    }

    public void setType(String type) 
    {
        this.type = type;
    }
    public byte[] getImage()
    {
        return picture;
    }
    
    public void setImage(byte[] image)
    {
        this.picture=image;
    }
}
