package com.destiny.pos.Model;

public class Model {
    String id,deskripsi,tanggal,tipe,kategori,jumlah,jenis,keterangan;

    public Model() {

    }
    public Model(String id,String deskripsi,String tanggal,String tipe,String kategori,String jumlah,String jenis,String keterangan){
        this.id=id;
        this.deskripsi=deskripsi;
        this.tanggal=tanggal;
        this.tipe=tipe;
        this.kategori=kategori;
        this.jumlah=jumlah;
        this.jenis=jenis;
        this.keterangan=keterangan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
