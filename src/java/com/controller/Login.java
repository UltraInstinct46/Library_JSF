/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.pojo.MultiuserLogin;
import com.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



/**
 *
 * @author Roger Simanjuntak
 */
@ManagedBean (name = "login_bean")
@SessionScoped
@Entity
public class Login implements Serializable {
    
    @Id
    private int id;
    public String username;
    private String password;
    public String page,um;
    MultiuserLogin user;
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void checkuser(){
        
        Transaction transaction = null;
        String roles = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = (MultiuserLogin) session.createQuery("FROM MultiuserLogin U WHERE U.username = :userName").setParameter("userName", username)
                .uniqueResult();

            if (user != null && user.getPassword().equals(password)) {
                roles = user.getLoginType();
                System.out.println(username);
                um = username;
                user.setUsername(username);
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                if(roles.equals("Admin")){
                    ec.redirect("index.xhtml");
                }else{
                    ec.redirect("catalogBuku.xhtml");
                }
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        }
    }
    public boolean checkSiswa(){
        return false;
    }
    public boolean checkAdmin(){
        return false;
    }
    
    public Login() {
    }
    
}