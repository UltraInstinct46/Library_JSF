package com.model.pojo;
// Generated Oct 8, 2022 10:06:48 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * DataPeminjambuku generated by hbm2java
 */
public class DataPeminjambuku  implements java.io.Serializable {


     private Integer idPeminjam;
     private DataBuku dataBuku;
     private String username;
     private String status;
     private Date tglPinjam;
     private Date tglPengembalian;
     private Integer idBuku;

    public Integer getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(Integer idBuku) {
        this.idBuku = idBuku;
    }
    public DataPeminjambuku() {
    }

	
    public DataPeminjambuku(DataBuku dataBuku, String username, String status, Date tglPinjam) {
        this.dataBuku = dataBuku;
        this.username = username;
        this.status = status;
        this.tglPinjam = tglPinjam;
    }
        public DataPeminjambuku(Integer idBuku, String username, String status, Date tglPinjam) {
        this.idBuku = idBuku;
        this.username = username;
        this.status = status;
        this.tglPinjam = tglPinjam;
    }
    public DataPeminjambuku(DataBuku dataBuku, String username, String status, Date tglPinjam, Date tglPengembalian) {
       this.dataBuku = dataBuku;
       this.username = username;
       this.status = status;
       this.tglPinjam = tglPinjam;
       this.tglPengembalian = tglPengembalian;
    }
   
    public Integer getIdPeminjam() {
        return this.idPeminjam;
    }
    
    public void setIdPeminjam(Integer idPeminjam) {
        this.idPeminjam = idPeminjam;
    }
    public DataBuku getDataBuku() {
        return this.dataBuku;
    }
    
    public void setDataBuku(DataBuku dataBuku) {
        this.dataBuku = dataBuku;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getTglPinjam() {
        return this.tglPinjam;
    }
    
    public void setTglPinjam(Date tglPinjam) {
        this.tglPinjam = tglPinjam;
    }
    public Date getTglPengembalian() {
        return this.tglPengembalian;
    }
    
    public void setTglPengembalian(Date tglPengembalian) {
        this.tglPengembalian = tglPengembalian;
    }




}


