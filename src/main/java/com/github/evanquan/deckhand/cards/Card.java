package com.github.evanquan.deckhand.cards;

import java.io.File;

public class Card {

    private String name;
    private String description;
    private File image;

    Card(String name, String description, File image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public File getImage() {
        return image;
    }
}