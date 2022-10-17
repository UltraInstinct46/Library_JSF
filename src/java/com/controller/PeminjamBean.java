package com.controller;

import com.dao.PeminjamDao;
import com.model.pojo.DataPeminjambuku;
import com.util.HibernateUtil;
import java.io.Serializable;
import static java.lang.Integer.getInteger;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
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
import java.util.stream.Collectors;  
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author killua
 */
@ManagedBean(name = "peminjamBean") 
@ViewScoped
public class PeminjamBean implements Serializable{
public PeminjamBean() {}
private List < DataPeminjambuku > peminjamsList;
private List < DataPeminjambuku > peminjamuserList;
private List < DataPeminjambuku > searchList;
private List < DataPeminjambuku > searchBypeminjamIdList;
PeminjamDao peminjamDao = new PeminjamDao();
DataPeminjambuku peminjam = new DataPeminjambuku();
DataPeminjambuku newpeminjam = new DataPeminjambuku();
public List < DataPeminjambuku > getPeminjams() {
    peminjamsList = peminjamDao.AllPeminjams();
    int count = peminjamsList.size();
    return peminjamsList;
}
public List < DataPeminjambuku > getBorrows(String username) {
    peminjamsList = peminjamDao.Peminjam(username);
    int count = peminjamsList.size();
    return peminjamsList;
}
public int newPeminjamID(int Id) {
    int peminjamkId = 0;
    return peminjamkId;
}
public void addPeminjam() {
    int peminjamId = 0;
    Integer userId = 0;
    userId = peminjamDao.getId();
    newpeminjam.setIdPeminjam(userId);
    Integer Id = newpeminjam.getIdPeminjam();
    peminjamId = newPeminjamID(Id);
    newpeminjam.setIdPeminjam(peminjamId);
    peminjamDao.add(newpeminjam);
    System.out.println("Member successfully saved.");
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Member successfully saved.");

    RequestContext.getCurrentInstance().showMessageInDialog(message);
    newpeminjam = new DataPeminjambuku();
}
public void changePeminjam(DataPeminjambuku peminjam) {
    this.peminjam = peminjam;
}
public void UpdatePeminjam(DataPeminjambuku peminjam) {
    String Title = peminjam.getUsername();
    FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Title", Title);

    RequestContext.getCurrentInstance().showMessageInDialog(message1);
    peminjamDao.update(peminjam);
    System.out.println("Peminjam Info successfully saved.");
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Peminjam updated successfully .");

    RequestContext.getCurrentInstance().showMessageInDialog(message);
    peminjam = new DataPeminjambuku();
}
public void deletePeminjam(DataPeminjambuku peminjam) {
    String Title = peminjam.getUsername();
    FacesMessage message3 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Item", Title); 
    RequestContext.getCurrentInstance().showMessageInDialog(message3);
    peminjamDao.delete(peminjam);
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");

    RequestContext.getCurrentInstance().showMessageInDialog(message);
}
public DataPeminjambuku getPeminjam() {
    return peminjam;
}
public void setUser(DataPeminjambuku peminjam) {
    this.peminjam = peminjam;
}
public DataPeminjambuku getNewpeminjam() {
    return newpeminjam;
}
public void setNewpeminjam(DataPeminjambuku newpeminjam) {
    this.newpeminjam = newpeminjam;
}
public List < DataPeminjambuku > getPeminjamsList() {
    return peminjamsList;
}
public void setPeminjamsList(List < DataPeminjambuku > peminjamsList) {
    this.peminjamsList = peminjamsList;
}
public List < DataPeminjambuku > getSearchList() {
    return searchList;
}
public void setSearchList(List < DataPeminjambuku > searchList) {
    this.searchList = searchList;
}
public List < DataPeminjambuku > getSearchByPeminjamIdList() {
    return searchBypeminjamIdList;
}
public void setSearchByPeminjamIdList(List < DataPeminjambuku >
    searchBypeminjamIdList) {
    this.searchBypeminjamIdList = searchBypeminjamIdList;
}
public void onRowEdit(RowEditEvent event) {
    FacesMessage msg = new FacesMessage(" Edited Record No", ((DataPeminjambuku) event.getObject()).getIdPeminjam() + "");
    FacesContext.getCurrentInstance().addMessage(null, msg);
    DataPeminjambuku editedpeminjam = (DataPeminjambuku) event.getObject();
    peminjamDao.update(editedpeminjam);
}
public void onCancel(RowEditEvent event) {
    FacesMessage msg = new FacesMessage("Edit Cancelled");
    FacesContext.getCurrentInstance().addMessage(null, msg);
    peminjamsList.remove((DataPeminjambuku) event.getObject());
}
    public void pengembalian(int id, int id_buku){
        DataPeminjambuku pengembalian = new DataPeminjambuku();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            LocalDate current = LocalDate.now();
            ZoneId systemTimeZone = ZoneId.systemDefault();
            ZonedDateTime zoneDateTime = current.atStartOfDay(systemTimeZone);
            Date date = Date.from(zoneDateTime.toInstant()); 
            session.beginTransaction();
            pengembalian.setStatus("Sudah Dikembalikan");
            pengembalian.setTglPengembalian(date);
            pengembalian.setIdPeminjam(id);
            pengembalian.setIdBuku(id_buku);
            pengembalian.setTglPinjam(date);
            pengembalian.setUsername("admin1");
            session.update(pengembalian);
            session.flush();
            System.out.println("New user Saved, id : "+ pengembalian.getIdBuku());
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Buku Berhasil Dikembalikan.");

        RequestContext.getCurrentInstance().showMessageInDialog(message);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }

//public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
//        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
//        if (filterText.isEmpty()) {
//            return true;
//        }
//        int filterInt = getInteger(filterText);
//
//        DataPeminjambuku peminjams = (DataPeminjambuku) value;
//        return peminjams.getJudulBuku().toLowerCase().contains(filterText)
//                || peminjams.getPenerbit().toLowerCase().contains(filterText)
//                || peminjams.getPengarang().toLowerCase().contains(filterText)
//                || peminjams.getKategori().toLowerCase().contains(filterText);
//    }
}