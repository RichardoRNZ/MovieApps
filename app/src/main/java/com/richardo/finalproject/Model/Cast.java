package com.richardo.finalproject.Model;

public class Cast {

    String name;
    int img_link;
    private String description;

    public Cast(String name, int img_link) {
        this.name = name;
        this.img_link = img_link;
    }

    public Cast(String name, int img_link, String description) {
        this.name = name;
        this.img_link = img_link;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg_link() {
        return img_link;
    }

    public void setImg_link(int img_link) {
        this.img_link = img_link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
