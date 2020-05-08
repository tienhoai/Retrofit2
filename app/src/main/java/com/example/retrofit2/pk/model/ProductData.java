package com.example.retrofit2.pk.model;

public class ProductData {
    private Integer id;
    private String hinh, ten, gia;

    public ProductData(Integer id, String hinh, String ten, String gia) {
        this.id = id;
        this.hinh = hinh;
        this.ten = ten;
        this.gia = gia;
    }

    public Integer getId() {
        return id;
    }

    public String getHinh() {
        return hinh;
    }

    public String getTen() {
        return ten;
    }

    public String getGia() {
        return gia;
    }
}
