package com.example.database_contectbook;

public class Contact_Modal {

    int id;
    String name,number;

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

    public Contact_Modal(int id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }


}
