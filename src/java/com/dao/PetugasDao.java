/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.pojo.DataPetugas;
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

public class PetugasDao {
    private DataPetugas petugas;
    private DataPetugas newpetugas;
    private List<DataPetugas> DaoAllPetugass;
    private List<DataPetugas> DaoSearchPetugasList;
    
    public List<DataPetugas> AllPetugass(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            DaoAllPetugass = session.createCriteria(DataPetugas.class).list();
            int count = DaoAllPetugass.size();
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO,"List Size", Integer.toString(count));
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return DaoAllPetugass;
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
    
    public void add(DataPetugas newpetugas){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            String Id = Integer.toString(newpetugas.getIdPetugas());
            session.beginTransaction();
            session.merge(newpetugas);
            session.flush();
            System.out.println("New user Saved, id : "+ newpetugas.getIdPetugas());
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void delete(DataPetugas petugas){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            String name = petugas.getNamaPetugas();
            session.beginTransaction();
            session.delete(petugas);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void update(DataPetugas petugas){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
        session.beginTransaction();
        session.update(petugas);
        session.flush();
        session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
}