package com.example.sehal.cookbook.infos;

/**
 * Created by sehal on 5/25/2015.
 */
public class SHInfo {
    public String iconid;
    public String dishname;
    String id;
    String title;
    int rating;
    String urlThumbnail;
    String urlReview;

    public SHInfo() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUrlThumbnail() {
        return urlThumbnail;
    }


    public void setUrlThumbnail(String urlThumbnail) {
        this.urlThumbnail = urlThumbnail;
    }

    @Override
    public String toString() {
        return "ID: " + id + "Title " + title;
    }
}
