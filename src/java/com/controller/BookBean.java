/*
* To change this license header, choose License Headers in Project 
Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.controller;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List; 
import com.dao.BookDao; 
import com.model.pojo.Book; 
import java.io.Serializable; 
import org.primefaces.event.RowEditEvent; 
import javax.faces.context.FacesContext; 
import javax.faces.application.FacesMessage; 
import org.primefaces.context.RequestContext; 
/**
*
* @author FACULTY
*/
@ManagedBean(name = "bookBean") 
@ViewScoped
public class BookBean implements Serializable {
 /**
 * Creates a new instance of BookBean
 */
 public BookBean() {
 }
 
 
 private List < Book > booksList; 
 private List < Book > searchList; 
 private List < Book > searchBybookIdList; 
 BookDao bookDao = new BookDao(); 
 Book book = new Book(); 
 Book newbook = new Book(); 
 public List < Book > getBooks() 
 { 
 booksList = bookDao.AllBooks(); 
 int count = booksList.size(); 
 return booksList; 
 } 
 public String newBookID(int Id)
 { 
 String bookId=null; 
 if(Id<=9)
 {
 bookId= "B000"+Id;
 }
 else if(Id<=99)
 {
 bookId= "B00"+Id;
 }
 else if(Id<=999)
 {
 bookId= "B0"+Id;
 }
 else
 {
 bookId= "B"+Id;
 } 
 return bookId;
 }
 public void addBook() 
 { 
 String bookId=null;
 Integer userId = 0; 
 userId = bookDao.getId(); 
 newbook.setId(userId); 
 Integer Id = newbook.getId();
 bookId=newBookID(Id);
 newbook.setBookId(bookId); 
 bookDao.add(newbook); 
 System.out.println("Book successfully saved."); 
 FacesMessage message = new 
FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Book successfully saved."); 
 
RequestContext.getCurrentInstance().showMessageInDialog(message); 
 newbook = new Book(); 
 } 
 public void changeBook(Book book) 
 { 
 this.book = book; 
 } 
 public void UpdateBook(Book book) 
 { 
 String Title = book.getTitle(); 
 FacesMessage message1 = new 
FacesMessage(FacesMessage.SEVERITY_INFO, "Title", Title); 
 
RequestContext.getCurrentInstance().showMessageInDialog(message1); 
 bookDao.update(book); 
 System.out.println("Book Info successfully saved."); 
 FacesMessage message = new 
FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Book updated successfully ."); 
 
RequestContext.getCurrentInstance().showMessageInDialog(message); 
 book = new Book(); 
 } 
 public void deleteBook(Book book) 
 { 
 String Title = book.getTitle(); 
 FacesMessage message3= new 
FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Item",Title); 
 // 
RequestContext.getCurrentInstance().showMessageInDialog(message3); 
 bookDao.delete(book); 
 FacesMessage message = new 
FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully"); 
 
RequestContext.getCurrentInstance().showMessageInDialog(message); 
 } 
 public void searchbyBookId() 
 { 
 searchBybookIdList = 
bookDao.SearchByBookId(book.getBookId()); 
 int count = searchBybookIdList.size(); 
 FacesMessage message = new 
FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count)); 
 
RequestContext.getCurrentInstance().showMessageInDialog(message); 
 } 
 public Book getBook() 
 { 
 return book; 
 } 
 public void setUser(Book book) 
 { 
 this.book = book; 
 } 
 public Book getNewbook() 
 { 
 return newbook; 
 } 
 public void setNewbook(Book newbook) 
 { 
 this.newbook = newbook; 
 } 
 public List < Book > getBooksList() 
 { 
 return booksList; 
 } 
 public void setBooksList(List < Book > booksList) 
 { 
 this.booksList = booksList; 
 } 
 public List < Book > getSearchList() 
 { 
 return searchList; 
 } 
 public void setSearchList(List < Book > searchList) 
 { 
 this.searchList = searchList; 
 } 
 public List < Book > getSearchByBookIdList() 
 { 
 return searchBybookIdList; 
 } 
 public void setSearchByBookIdList(List < Book > 
searchBybookIdList) 
 { 
 this.searchBybookIdList = searchBybookIdList; 
 } 
 public void onRowEdit(RowEditEvent event) 
 { 
 FacesMessage msg = new FacesMessage(" Edited Record No",((Book) event.getObject()).getBookId()); 
 FacesContext.getCurrentInstance().addMessage(null, msg); 
 Book editedbook = (Book) event.getObject(); 
 bookDao.update(editedbook); 
 } 
 public void onCancel(RowEditEvent event) 
 { 
 FacesMessage msg = new FacesMessage("Edit Cancelled"); 
 FacesContext.getCurrentInstance().addMessage(null, msg); 
 booksList.remove((Book) event.getObject()); 
 } 
 
}