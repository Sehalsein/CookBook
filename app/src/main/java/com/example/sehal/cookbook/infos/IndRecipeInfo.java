package com.example.sehal.cookbook.infos;

import android.support.v4.app.FragmentActivity;

import java.util.List;

/**
 * Created by sehal on 4/28/2015.
 */
public class IndRecipeInfo {

    public String recipesteppics;
    public String recipestep;
    public String recipestepno;
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

