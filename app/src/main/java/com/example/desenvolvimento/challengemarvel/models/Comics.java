package com.example.desenvolvimento.challengemarvel.models;

import java.io.Serializable;

/**
 * Created by Desenvolvimento on 27/08/2017.
 */

public class Comics implements Serializable {
    private String title;
    private String description;
    private String modified;
    private String isbn;
    private String pages;

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
