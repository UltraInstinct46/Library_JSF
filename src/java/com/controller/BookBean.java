package com.controller;

import com.model.pojo.Book;
import com.util.HibernateUtil;
import java.util.List;
import javax.faces.application.FacesMessage;
import org.hibernate.Query;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author killua
 */
public class BookBean {
    private Book book;
    private Book newbook;
    private List<Book> DaoAllBooks;
    private List<Book> DaoSearchBookList;
    
    public List<Book> AllBooks(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            DaoAllBooks = session.createCriteria(Book.class).list();
            int count = DaoAllBooks.size();
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO,"List Size", Integer.toString(count));
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return DaoAllBooks;
    }
    public Integer getId(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "select max(U.id) from book U";
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
}
