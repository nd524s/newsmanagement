package com.epam.newsmanagement.domain;

import java.util.ArrayList;

/**
 * Created by Никита on 7/4/2016.
 */
public class NewsTransferObject {
    private String title;
    private String shortText;
    private String fullText;
    private String author;
    private String[] tags;

    public NewsTransferObject() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}
