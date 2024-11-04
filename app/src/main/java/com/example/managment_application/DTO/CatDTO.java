package com.example.managment_application.DTO;

public class CatDTO {
    int id;
    String name;
    public CatDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public String toString (){
        return "ID Cat: " + id + "Name Cat: " + name;
    }

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
}
