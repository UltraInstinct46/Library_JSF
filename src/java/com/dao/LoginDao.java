/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.pojo.MultiuserLogin;
import com.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author killua
 */

public class LoginDao {
    private MultiuserLogin login;
    private MultiuserLogin newlogin;
    private List<MultiuserLogin> DaoAllLogins;
    private List<MultiuserLogin> DaoSearchLoginList;
    
    public List<MultiuserLogin> AllLogins(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            DaoAllLogins = session.createCriteria(MultiuserLogin.class).list();
            int count = DaoAllLogins.size();
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO,"List Size", Integer.toString(count));
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return DaoAllLogins;
    }
    public Integer getId(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "select max(U.idBuku) from DataBuku U";
        Query query = session.createQuery(hql);
        List<Integer> results = query.list();
        Integer userId = 1;
        if(results.get(0)!= null){
            userId = results.get(0)+1;
        }
        session.flush();
        session.close();
        return userId;
    }
    
    public List<MultiuserLogin> SearchByLoginId(String loginId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<MultiuserLogin> daoSearchList = new ArrayList<>();
        try{
            session.beginTransaction();
            Query qu = session.createQuery("From Login U where U.loginId =:loginId");
            qu.setParameter("loginId",loginId);
            daoSearchList = qu.list();
            int count = daoSearchList.size();
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        finally{
            session.close();
        }
        return daoSearchList;
    }
    public void add(MultiuserLogin newlogin){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            String Id = Integer.toString(newlogin.getIdLogin());
            session.beginTransaction();
            session.merge(newlogin);
            session.flush();
            System.out.println("New user Saved, id : "+ newlogin.getIdLogin());
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void delete(MultiuserLogin login){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            String name = login.getUsername();
            session.beginTransaction();
            session.delete(login);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void update(MultiuserLogin login){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
        session.beginTransaction();
        session.update(login);
        session.flush();
        session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
}