package com.example.retrofit2.pk.model;

public class ProductData {
    private Integer id;
    private String hinh, ten, gia;

    public ProductData(Integer id, String ten, String gia, String hinh) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
        this.hinh = hinh;
    }

    public Integer getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public String getGia() {
        return gia;
    }

    public String getHinh() {
        return hinh;
    }
}
