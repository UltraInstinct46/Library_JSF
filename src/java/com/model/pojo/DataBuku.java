package com.model.pojo;
// Generated Oct 8, 2022 10:06:48 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * DataBuku generated by hbm2java
 */
public class DataBuku  implements java.io.Serializable {


     private Integer idBuku;
     private String judulBuku;
     private String penerbit;
     private String pengarang;
     private int tahunPenerbit;
     private String kategori;
     private int stok;
     private Set<DataPeminjambuku> dataPeminjambukus = new HashSet<DataPeminjambuku>(0);

    public DataBuku() {
    }

	
    public DataBuku(String judulBuku, String penerbit, String pengarang, int tahunPenerbit, String kategori, int stok) {
        this.judulBuku = judulBuku;
        this.penerbit = penerbit;
        this.pengarang = pengarang;
        this.tahunPenerbit = tahunPenerbit;
        this.kategori = kategori;
        this.stok = stok;
    }
    public DataBuku(String judulBuku, String penerbit, String pengarang, int tahunPenerbit, String kategori, int stok, Set<DataPeminjambuku> dataPeminjambukus) {
       this.judulBuku = judulBuku;
       this.penerbit = penerbit;
       this.pengarang = pengarang;
       this.tahunPenerbit = tahunPenerbit;
       this.kategori = kategori;
       this.stok = stok;
       this.dataPeminjambukus = dataPeminjambukus;
    }
   
    public Integer getIdBuku() {
        return this.idBuku;
    }
    
    public void setIdBuku(Integer idBuku) {
        this.idBuku = idBuku;
    }
    public String getJudulBuku() {
        return this.judulBuku;
    }
    
    public void setJudulBuku(String judulBuku) {
        this.judulBuku = judulBuku;
    }
    public String getPenerbit() {
        return this.penerbit;
    }
    
    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }
    public String getPengarang() {
        return this.pengarang;
    }
    
    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }
    public int getTahunPenerbit() {
        return this.tahunPenerbit;
    }
    
    public void setTahunPenerbit(int tahunPenerbit) {
        this.tahunPenerbit = tahunPenerbit;
    }
    public String getKategori() {
        return this.kategori;
    }
    
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    public int getStok() {
        return this.stok;
    }
    
    public void setStok(int stok) {
        this.stok = stok;
    }
    public Set<DataPeminjambuku> getDataPeminjambukus() {
        return this.dataPeminjambukus;
    }
    
    public void setDataPeminjambukus(Set<DataPeminjambuku> dataPeminjambukus) {
        this.dataPeminjambukus = dataPeminjambukus;
    }




}


