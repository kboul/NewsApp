package com.example.android.newsapp;

public class News {

    private String webTitle;
    private String sectionName;
    private String author;
    private String datePublished;

    // Constructor
    public News(String webTitle, String sectionName) {
        this.webTitle = webTitle;
        this.sectionName = sectionName;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getAuthor() {
        return author;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getDatePublished() {
        return datePublished;
    }
}
