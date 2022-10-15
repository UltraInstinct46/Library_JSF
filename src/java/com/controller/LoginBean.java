package com.controller;

import com.dao.LoginDao;
import com.model.pojo.MultiuserLogin;
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
@ManagedBean(name = "loginBean") 
@ViewScoped
public class LoginBean implements Serializable{
public LoginBean() {}
private List < MultiuserLogin > loginsList;
private List < MultiuserLogin > searchList;
private List < MultiuserLogin > searchByloginIdList;
LoginDao loginDao = new LoginDao();
MultiuserLogin login = new MultiuserLogin();
MultiuserLogin newlogin = new MultiuserLogin();
public List < MultiuserLogin > getLogins() {
    loginsList = loginDao.AllLogins();
    return loginsList;
}
public int newLoginID(int Id) {
    int loginkId = 0;
    return loginkId;
}
public void addLogin() {
    int loginId = 0;
    Integer userId = 0;
    userId = loginDao.getId();
    newlogin.setIdLogin(userId);
    Integer Id = newlogin.getIdLogin();
    loginId = newLoginID(Id);
    newlogin.setIdLogin(loginId);
    loginDao.add(newlogin);
    System.out.println("Member successfully saved.");
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Member successfully saved.");

    RequestContext.getCurrentInstance().showMessageInDialog(message);
    newlogin = new MultiuserLogin();
}
public void changeLogin(MultiuserLogin login) {
    this.login = login;
}
public void UpdateLogin(MultiuserLogin login) {
    String Title = login.getUsername();
    FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Title", Title);

    RequestContext.getCurrentInstance().showMessageInDialog(message1);
    loginDao.update(login);
    System.out.println("Login Info successfully saved.");
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Login updated successfully .");

    RequestContext.getCurrentInstance().showMessageInDialog(message);
    login = new MultiuserLogin();
}
public void deleteLogin(MultiuserLogin login) {
    String Title = login.getUsername();
    FacesMessage message3 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Item", Title); 
    RequestContext.getCurrentInstance().showMessageInDialog(message3);
    loginDao.delete(login);
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");

    RequestContext.getCurrentInstance().showMessageInDialog(message);
}
public void searchbyLoginId() {
    searchByloginIdList =
    loginDao.SearchByLoginId(login.getIdLogin()+ "");
    int count = searchByloginIdList.size();
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));

    RequestContext.getCurrentInstance().showMessageInDialog(message);
}
public MultiuserLogin getLogin() {
    return login;
}
public void setUser(MultiuserLogin login) {
    this.login = login;
}
public MultiuserLogin getNewlogin() {
    return newlogin;
}
public void setNewlogin(MultiuserLogin newlogin) {
    this.newlogin = newlogin;
}
public List < MultiuserLogin > getLoginsList() {
    return loginsList;
}
public void setLoginsList(List < MultiuserLogin > loginsList) {
    this.loginsList = loginsList;
}
public List < MultiuserLogin > getSearchList() {
    return searchList;
}
public void setSearchList(List < MultiuserLogin > searchList) {
    this.searchList = searchList;
}
public List < MultiuserLogin > getSearchByLoginIdList() {
    return searchByloginIdList;
}
public void setSearchByLoginIdList(List < MultiuserLogin >
    searchByloginIdList) {
    this.searchByloginIdList = searchByloginIdList;
}
public void onRowEdit(RowEditEvent event) {
    FacesMessage msg = new FacesMessage(" Edited Record No", ((MultiuserLogin) event.getObject()).getIdLogin() + "");
    FacesContext.getCurrentInstance().addMessage(null, msg);
    MultiuserLogin editedlogin = (MultiuserLogin) event.getObject();
    loginDao.update(editedlogin);
}
public void onCancel(RowEditEvent event) {
    FacesMessage msg = new FacesMessage("Edit Cancelled");
    FacesContext.getCurrentInstance().addMessage(null, msg);
    loginsList.remove((MultiuserLogin) event.getObject());
}


//public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
//        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
//        if (filterText.isEmpty()) {
//            return true;
//        }
//        int filterInt = getInteger(filterText);
//
//        MultiuserLogin logins = (MultiuserLogin) value;
//        return logins.getJudulBuku().toLowerCase().contains(filterText)
//                || logins.getPenerbit().toLowerCase().contains(filterText)
//                || logins.getPengarang().toLowerCase().contains(filterText)
//                || logins.getKategori().toLowerCase().contains(filterText);
//    }
}