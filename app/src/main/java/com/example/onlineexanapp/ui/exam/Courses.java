package com.example.onlineexanapp.ui.exam;

public class Courses {

    private int image;
    private String title;

    public Courses(int image, String title) {
        this.title = title;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
