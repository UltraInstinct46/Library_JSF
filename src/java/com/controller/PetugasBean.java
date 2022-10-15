package com.controller;

import com.dao.PetugasDao;
import com.model.pojo.DataPetugas;
import com.util.HibernateUtil;
import java.io.Serializable;
import static java.lang.Integer.getInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author killua
 */
@ManagedBean(name = "petugasBean") 
@ViewScoped
public class PetugasBean implements Serializable{
public PetugasBean() {}
private List < DataPetugas > petugassList;
private List < DataPetugas > searchList;
private List < DataPetugas > searchBypetugasIdList;
PetugasDao petugasDao = new PetugasDao();
DataPetugas petugas = new DataPetugas();
DataPetugas newpetugas = new DataPetugas();
public List < DataPetugas > getPetugass() {
    petugassList = petugasDao.AllPetugass();
    int count = petugassList.size();
    return petugassList;
}
public int newPetugasID(int Id) {
    int petugaskId = 0;
    return petugaskId;
}
public void addPetugas() {
    int petugasId = 0;
    Integer userId = 0;
    userId = petugasDao.getId();
    newpetugas.setIdPetugas(userId);
    Integer Id = newpetugas.getIdPetugas();
    petugasId = newPetugasID(Id);
    newpetugas.setIdPetugas(petugasId);
    petugasDao.add(newpetugas);
    System.out.println("Member successfully saved.");
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Member successfully saved.");

    RequestContext.getCurrentInstance().showMessageInDialog(message);
    newpetugas = new DataPetugas();
}
public void changePetugas(DataPetugas petugas) {
    this.petugas = petugas;
}
public void UpdatePetugas(DataPetugas petugas) {
    String Title = petugas.getNamaPetugas();
    FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Title", Title);

    RequestContext.getCurrentInstance().showMessageInDialog(message1);
    petugasDao.update(petugas);
    System.out.println("Petugas Info successfully saved.");
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Petugas updated successfully .");

    RequestContext.getCurrentInstance().showMessageInDialog(message);
    petugas = new DataPetugas();
}
public void deletePetugas(DataPetugas petugas) {
    String Title = petugas.getNamaPetugas();
    FacesMessage message3 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Item", Title); 
    RequestContext.getCurrentInstance().showMessageInDialog(message3);
    petugasDao.delete(petugas);
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");

    RequestContext.getCurrentInstance().showMessageInDialog(message);
}
public DataPetugas getPetugas() {
    return petugas;
}
public void setUser(DataPetugas petugas) {
    this.petugas = petugas;
}
public DataPetugas getNewpetugas() {
    return newpetugas;
}
public void setNewpetugas(DataPetugas newpetugas) {
    this.newpetugas = newpetugas;
}
public List < DataPetugas > getPetugassList() {
    return petugassList;
}
public void setPetugassList(List < DataPetugas > petugassList) {
    this.petugassList = petugassList;
}
public List < DataPetugas > getSearchList() {
    return searchList;
}
public void setSearchList(List < DataPetugas > searchList) {
    this.searchList = searchList;
}
public List < DataPetugas > getSearchByPetugasIdList() {
    return searchBypetugasIdList;
}
public void setSearchByPetugasIdList(List < DataPetugas >
    searchBypetugasIdList) {
    this.searchBypetugasIdList = searchBypetugasIdList;
}
public void onRowEdit(RowEditEvent event) {
    FacesMessage msg = new FacesMessage(" Edited Record No", ((DataPetugas) event.getObject()).getIdPetugas() + "");
    FacesContext.getCurrentInstance().addMessage(null, msg);
    DataPetugas editedpetugas = (DataPetugas) event.getObject();
    petugasDao.update(editedpetugas);
}
public void onCancel(RowEditEvent event) {
    FacesMessage msg = new FacesMessage("Edit Cancelled");
    FacesContext.getCurrentInstance().addMessage(null, msg);
    petugassList.remove((DataPetugas) event.getObject());
}


//public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
//        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
//        if (filterText.isEmpty()) {
//            return true;
//        }
//        int filterInt = getInteger(filterText);
//
//        DataPetugas petugass = (DataPetugas) value;
//        return petugass.getJudulBuku().toLowerCase().contains(filterText)
//                || petugass.getPenerbit().toLowerCase().contains(filterText)
//                || petugass.getPengarang().toLowerCase().contains(filterText)
//                || petugass.getKategori().toLowerCase().contains(filterText);
//    }
}