package com.example.android.newsapp;

public class News {

    private String webTitle;
    private String sectionName;
    private String publicationDate;
    private String webUrl;
    private String author;

    // Constructor
    public News(String webTitle, String sectionName, String publicationDate, String webUrl) {
        this.webTitle = webTitle;
        this.sectionName = sectionName;
        this.publicationDate = publicationDate;
        this.webUrl = webUrl;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getWebUrl() { return webUrl; }

    public String getAuthor() {
        return author;
    }

}
