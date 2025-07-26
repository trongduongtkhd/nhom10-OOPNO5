package com.mycompany.quanlyphonggym.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PersonalTrainer")
@XmlAccessorType(XmlAccessType.FIELD)
public class PTTrainerXML {
    
    private List<PersonalTrainer> PT;

    public List<PersonalTrainer> getPT() {
        return PT;
    }

    public void setPT(List<PersonalTrainer> pt) {
        this.PT = pt;
    }
}

