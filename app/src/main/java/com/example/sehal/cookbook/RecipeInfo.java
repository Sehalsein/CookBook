package com.example.sehal.cookbook;

/**
 * Created by sehal on 5/4/2015.
 */
public class RecipeInfo {
    public String iconid;
    public String dishname;
    String id;
    String title;
    int rating;
    String urlThumbnail;
    String urlReview;

    public RecipeInfo() {

    }

    public RecipeInfo(String id,
                      String title,
                      int rating,
                      String urlThumbnail
    ) {

        this.id = id;
        this.title = title;
        this.rating = rating;
        this.urlThumbnail = urlThumbnail;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id=id;
    }
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }
    public int getRating(){
        return rating;
    }

    public void setRating(int rating){
        this.rating=rating;
    }
    public String getUrlThumbnail(){
        return urlThumbnail;
    }


    public void setUrlThumbnail(String urlThumbnail){
        this.urlThumbnail=urlThumbnail;
    }

    @Override
    public String toString(){
        return "ID: "+id+"Title "+title;
    }

}
