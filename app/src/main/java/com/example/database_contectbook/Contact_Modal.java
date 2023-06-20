package com.example.database_contectbook;

import android.widget.ImageView;

public class Contact_Modal {

    int id;
    String name,number;
    String imagepath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImgpath() {
        return imagepath;
    }

    public void setImgpath(String imgpath) {
        this.imagepath = imgpath;
    }

    public Contact_Modal(int id, String name, String number, String imagepath) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.imagepath = imagepath;
    }
}
