package com.example.android.newsapp;

public class News {

    private String webTitle;
    private String sectionName;
    private String webUrl;
    private String author;
    private String datePublished;

    // Constructor
    public News(String webTitle, String sectionName, String webUrl) {
        this.webTitle = webTitle;
        this.sectionName = sectionName;
        this.webUrl = webUrl;
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

    public String getWebUrl() {
        return webUrl;
    }

    public String getDatePublished() {
        return datePublished;
    }
}
