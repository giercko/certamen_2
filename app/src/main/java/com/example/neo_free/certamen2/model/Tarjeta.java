package com.example.neo_free.certamen2;

/**
 * Created by neo_free on 02/11/2016.
 */

public class Tarjeta {
    private String title;
    private String description;
    private String update;
    private String url;

    public Tarjeta(String n_title, String n_description, String n_update, String n_url){
        this.title = n_title;
        this.description = n_description;
        this.update = n_update;
        this.url = n_url;

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUpdate() {
        return update;
    }

    public String getUrl() {
        return url;
    }
}
