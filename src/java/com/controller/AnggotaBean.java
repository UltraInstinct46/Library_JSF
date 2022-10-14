package com.controller;

import com.dao.AnggotaDao;
import com.model.pojo.DataAnggota;
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
@ManagedBean(name = "anggotaBean") 
@ViewScoped
public class AnggotaBean implements Serializable{
public AnggotaBean() {}
private List < DataAnggota > anggotasList;
private List < DataAnggota > searchList;
private List < DataAnggota > searchByanggotaIdList;
AnggotaDao anggotaDao = new AnggotaDao();
DataAnggota anggota = new DataAnggota();
DataAnggota newanggota = new DataAnggota();
public List < DataAnggota > getAnggotas() {
    anggotasList = anggotaDao.AllAnggotas();
    int count = anggotasList.size();
    return anggotasList;
}
public int newAnggotaID(int Id) {
    int anggotakId = 0;
    return anggotakId;
}
public void addAnggota() {
    int anggotaId = 0;
    Integer userId = 0;
    userId = anggotaDao.getId();
    newanggota.setIdAnggota(userId);
    Integer Id = newanggota.getIdAnggota();
    anggotaId = newAnggotaID(Id);
    newanggota.setIdAnggota(anggotaId);
    anggotaDao.add(newanggota);
    System.out.println("Member successfully saved.");
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Member successfully saved.");

    RequestContext.getCurrentInstance().showMessageInDialog(message);
    newanggota = new DataAnggota();
}
public void changeAnggota(DataAnggota anggota) {
    this.anggota = anggota;
}
public void UpdateAnggota(DataAnggota anggota) {
    String Title = anggota.getNamaAnggota();
    FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Title", Title);

    RequestContext.getCurrentInstance().showMessageInDialog(message1);
    anggotaDao.update(anggota);
    System.out.println("Anggota Info successfully saved.");
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Anggota updated successfully .");

    RequestContext.getCurrentInstance().showMessageInDialog(message);
    anggota = new DataAnggota();
}
public void deleteAnggota(DataAnggota anggota) {
    String Title = anggota.getNamaAnggota();
    FacesMessage message3 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Item", Title); 
    RequestContext.getCurrentInstance().showMessageInDialog(message3);
    anggotaDao.delete(anggota);
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");

    RequestContext.getCurrentInstance().showMessageInDialog(message);
}
public void searchbyAnggotaId() {
    searchByanggotaIdList =
    anggotaDao.SearchByAnggotaId(anggota.getIdAnggota()+ "");
    int count = searchByanggotaIdList.size();
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));

    RequestContext.getCurrentInstance().showMessageInDialog(message);
}
public DataAnggota getAnggota() {
    return anggota;
}
public void setUser(DataAnggota anggota) {
    this.anggota = anggota;
}
public DataAnggota getNewanggota() {
    return newanggota;
}
public void setNewanggota(DataAnggota newanggota) {
    this.newanggota = newanggota;
}
public List < DataAnggota > getAnggotasList() {
    return anggotasList;
}
public void setAnggotasList(List < DataAnggota > anggotasList) {
    this.anggotasList = anggotasList;
}
public List < DataAnggota > getSearchList() {
    return searchList;
}
public void setSearchList(List < DataAnggota > searchList) {
    this.searchList = searchList;
}
public List < DataAnggota > getSearchByAnggotaIdList() {
    return searchByanggotaIdList;
}
public void setSearchByAnggotaIdList(List < DataAnggota >
    searchByanggotaIdList) {
    this.searchByanggotaIdList = searchByanggotaIdList;
}
public void onRowEdit(RowEditEvent event) {
    FacesMessage msg = new FacesMessage(" Edited Record No", ((DataAnggota) event.getObject()).getIdAnggota() + "");
    FacesContext.getCurrentInstance().addMessage(null, msg);
    DataAnggota editedanggota = (DataAnggota) event.getObject();
    anggotaDao.update(editedanggota);
}
public void onCancel(RowEditEvent event) {
    FacesMessage msg = new FacesMessage("Edit Cancelled");
    FacesContext.getCurrentInstance().addMessage(null, msg);
    anggotasList.remove((DataAnggota) event.getObject());
}


//public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
//        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
//        if (filterText.isEmpty()) {
//            return true;
//        }
//        int filterInt = getInteger(filterText);
//
//        DataAnggota anggotas = (DataAnggota) value;
//        return anggotas.getJudulBuku().toLowerCase().contains(filterText)
//                || anggotas.getPenerbit().toLowerCase().contains(filterText)
//                || anggotas.getPengarang().toLowerCase().contains(filterText)
//                || anggotas.getKategori().toLowerCase().contains(filterText);
//    }
}