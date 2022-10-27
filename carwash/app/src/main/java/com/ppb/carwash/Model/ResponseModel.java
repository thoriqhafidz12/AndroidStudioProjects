package com.ppb.carwash.Model;

import java.util.List;

public class ResponseModel {
    private int kode;
    private String pesan;
    private List <DataModel> data;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<DataModel> getData() {
        return data;
    }

    public void setData(List<DataModel> data) {
        this.data = data;
    }

    public ResponseModel(int kode, String pesan, List<DataModel> data) {
        this.kode = kode;
        this.pesan = pesan;
        this.data = data;
    }
}
