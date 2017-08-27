package com.example.desenvolvimento.challengemarvel;

import java.io.Serializable;

/**
 * Created by Desenvolvimento on 25/08/2017.
 */

public class Character implements Serializable {
    private String name;
    private String description;
    private String modified;
    private String amountComics;
    private String amountEvents;
    private String imageUrl;

    public String getAmountComics() {
        return amountComics;
    }

    public void setAmountComics(String amountComics) {
        this.amountComics = amountComics;
    }

    public String getAmountEvents() {
        return amountEvents;
    }

    public void setAmountEvents(String amountEvents) {
        this.amountEvents = amountEvents;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
