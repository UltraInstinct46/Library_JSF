/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    private String username;
    private String password;

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

    public boolean checkuser(){
        
        try {
            
            SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();
            Session session= sessionFactory.openSession();
            session.beginTransaction();
            
            Query query=session.createQuery("from Login where username=:username and password=:password");
            query.setString("username", username);
            query.setString("password", password);
            List list= query.list();
            String test = list.get(0).toString();
            if (list.size()==1) {
                    return true;
            }else{
                return false;
            }
            
            
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        
    }
    
    
    public Login() {
    }
    
}