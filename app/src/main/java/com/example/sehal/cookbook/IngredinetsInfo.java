package com.example.sehal.cookbook;

/**
 * Created by sehal on 5/16/2015.
 */
public class IngredinetsInfo {
    public String iconid;
    String ingredinetsname;
    String id;
    String title;
    int rating;
    String urlThumbnail;
    String urlReview;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getingredinetsname() {
        return ingredinetsname;
    }

    public void setingredinetsname(String ingredinetsname) {
        this.ingredinetsname = ingredinetsname;
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
