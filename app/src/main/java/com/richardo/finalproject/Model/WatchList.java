package com.richardo.finalproject.Model;

public class WatchList {
    private String name;
    private String status;
    private int posterimg;

    public WatchList(String name, String status, int posterimg) {
        this.name = name;
        this.status = status;
        this.posterimg = posterimg;
    }

    public WatchList() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPosterimg() {
        return posterimg;
    }

    public void setPosterimg(int posterimg) {
        this.posterimg = posterimg;
    }
}
