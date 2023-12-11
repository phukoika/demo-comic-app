package com.example.entthenbookapp.object;

public class Comic {
    private String name,chap,imgUrl;
    public Comic(){

    };
    public Comic(String name, String rate, String imgUrl) {
        this.name = name;
        this.chap = rate;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChap() {
        return chap;
    }

    public void setChap(String rate) {
        this.chap = rate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
