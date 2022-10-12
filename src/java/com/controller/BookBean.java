package com.controller;

import com.dao.BookDao;
import com.model.pojo.DataBuku;
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
@ManagedBean(name = "bookBean") 
@ViewScoped
public class BookBean implements Serializable{
public BookBean() {}
private List < DataBuku > booksList;
private List < DataBuku > searchList;
private List < DataBuku > searchBybookIdList;
BookDao bookDao = new BookDao();
DataBuku book = new DataBuku();
DataBuku newbook = new DataBuku();
public List < DataBuku > getBooks() {
    booksList = bookDao.AllBooks();
    int count = booksList.size();
    return booksList;
}
public int newBookID(int Id) {
    int bookId = 0;
    return bookId;
}
public void addBook() {
    int bookId = 0;
    Integer userId = 0;
    userId = bookDao.getId();
    newbook.setIdBuku(userId);
    Integer Id = newbook.getIdBuku();
    bookId = newBookID(Id);
    newbook.setIdBuku(bookId);
    bookDao.add(newbook);
    System.out.println("Book successfully saved.");
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Book successfully saved.");

    RequestContext.getCurrentInstance().showMessageInDialog(message);
    newbook = new DataBuku();
}
public void changeBook(DataBuku book) {
    this.book = book;
}
public void UpdateBook(DataBuku book) {
    String Title = book.getJudulBuku();
    FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Title", Title);

    RequestContext.getCurrentInstance().showMessageInDialog(message1);
    bookDao.update(book);
    System.out.println("Book Info successfully saved.");
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Book updated successfully .");

    RequestContext.getCurrentInstance().showMessageInDialog(message);
    book = new DataBuku();
}
public void deleteBook(DataBuku book) {
    String Title = book.getJudulBuku();
    FacesMessage message3 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Item", Title); 
    RequestContext.getCurrentInstance().showMessageInDialog(message3);
    bookDao.delete(book);
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");

    RequestContext.getCurrentInstance().showMessageInDialog(message);
}
public void searchbyBookId() {
    searchBybookIdList =
    bookDao.SearchByBookId(book.getIdBuku() + "");
    int count = searchBybookIdList.size();
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));

    RequestContext.getCurrentInstance().showMessageInDialog(message);
}
public DataBuku getBook() {
    return book;
}
public void setUser(DataBuku book) {
    this.book = book;
}
public DataBuku getNewbook() {
    return newbook;
}
public void setNewbook(DataBuku newbook) {
    this.newbook = newbook;
}
public List < DataBuku > getBooksList() {
    return booksList;
}
public void setBooksList(List < DataBuku > booksList) {
    this.booksList = booksList;
}
public List < DataBuku > getSearchList() {
    return searchList;
}
public void setSearchList(List < DataBuku > searchList) {
    this.searchList = searchList;
}
public List < DataBuku > getSearchByBookIdList() {
    return searchBybookIdList;
}
public void setSearchByBookIdList(List < DataBuku >
    searchBybookIdList) {
    this.searchBybookIdList = searchBybookIdList;
}
public void onRowEdit(RowEditEvent event) {
    FacesMessage msg = new FacesMessage(" Edited Record No", ((DataBuku) event.getObject()).getIdBuku() + "");
    FacesContext.getCurrentInstance().addMessage(null, msg);
    DataBuku editedbook = (DataBuku) event.getObject();
    bookDao.update(editedbook);
}
public void onCancel(RowEditEvent event) {
    FacesMessage msg = new FacesMessage("Edit Cancelled");
    FacesContext.getCurrentInstance().addMessage(null, msg);
    booksList.remove((DataBuku) event.getObject());
}


public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText.isEmpty()) {
            return true;
        }
        int filterInt = getInteger(filterText);

        DataBuku books = (DataBuku) value;
        return books.getJudulBuku().toLowerCase().contains(filterText)
                || books.getPenerbit().toLowerCase().contains(filterText)
                || books.getPengarang().toLowerCase().contains(filterText)
                || books.getKategori().toLowerCase().contains(filterText);
    }
}