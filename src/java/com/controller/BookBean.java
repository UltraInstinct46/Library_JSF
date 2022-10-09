package com.controller;

import com.model.pojo.Book;
import com.util.HibernateUtil;
import java.util.ArrayList;
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
    
    public List<Book> SearchBookId(String bookId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Book> daoSearchList = new ArrayList<>();
        try{
            session.beginTransaction();
            Query qu = session.createQuery("From Book U where U.bookId =:bookId");
            qu.setParameter("bookId",bookId);
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
    public void add(Book newbook){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            String Id = Integer.toString(newbook.getId());
            session.beginTransaction();
            session.merge(newbook);
            session.flush();
            System.out.println("New user Saved, id : "+ newbook.getId());
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void delete(Book book){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            String name = book.getTitle();
            session.beginTransaction();
            session.delete(book);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void update(Book book){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
        session.beginTransaction();
        session.update(book);
        session.flush();
        session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
}
