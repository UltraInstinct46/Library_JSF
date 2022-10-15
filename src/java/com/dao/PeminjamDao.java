/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.pojo.DataPeminjambuku;
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

public class PeminjamDao {
    private DataPeminjambuku peminjam;
    private DataPeminjambuku newpeminjam;
    private List<DataPeminjambuku> DaoAllPeminjams;
    private List<DataPeminjambuku> DaoSearchPeminjamList;
    
    public List<DataPeminjambuku> AllPeminjams(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            DaoAllPeminjams = session.createCriteria(DataPeminjambuku.class).list();
            int count = DaoAllPeminjams.size();
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO,"List Size", Integer.toString(count));
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return DaoAllPeminjams;
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
    
    public void add(DataPeminjambuku newpeminjam){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            String Id = Integer.toString(newpeminjam.getIdPeminjam());
            session.beginTransaction();
            session.merge(newpeminjam);
            session.flush();
            System.out.println("New user Saved, id : "+ newpeminjam.getIdPeminjam());
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void delete(DataPeminjambuku peminjam){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            String name = peminjam.getUsername();
            session.beginTransaction();
            session.delete(peminjam);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void update(DataPeminjambuku peminjam){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
        session.beginTransaction();
        session.update(peminjam);
        session.flush();
        session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
}