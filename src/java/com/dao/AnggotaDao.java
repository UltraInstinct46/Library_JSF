/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.pojo.DataAnggota;
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

public class AnggotaDao {
    private DataAnggota anggota;
    private DataAnggota newanggota;
    private List<DataAnggota> DaoAllAnggotas;
    private List<DataAnggota> DaoSearchAnggotaList;
    
    public List<DataAnggota> AllAnggotas(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            DaoAllAnggotas = session.createCriteria(DataAnggota.class).list();
            int count = DaoAllAnggotas.size();
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO,"List Size", Integer.toString(count));
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return DaoAllAnggotas;
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
    
    public List<DataAnggota> SearchByAnggotaId(String anggotaId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<DataAnggota> daoSearchList = new ArrayList<>();
        try{
            session.beginTransaction();
            Query qu = session.createQuery("From Anggota U where U.anggotaId =:anggotaId");
            qu.setParameter("anggotaId",anggotaId);
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
    public void add(DataAnggota newanggota){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            String Id = Integer.toString(newanggota.getIdAnggota());
            session.beginTransaction();
            session.merge(newanggota);
            session.flush();
            System.out.println("New user Saved, id : "+ newanggota.getIdAnggota());
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void delete(DataAnggota anggota){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            String name = anggota.getNamaAnggota();
            session.beginTransaction();
            session.delete(anggota);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void update(DataAnggota anggota){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
        session.beginTransaction();
        session.update(anggota);
        session.flush();
        session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
}