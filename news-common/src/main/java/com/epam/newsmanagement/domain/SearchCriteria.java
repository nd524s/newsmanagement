package com.epam.newsmanagement.domain;

import java.util.ArrayList;

/**
 * Created by Никита on 6/3/2016.
 */
public class SearchCriteria {
    private Author author;
    private ArrayList<Tag> tags = new ArrayList<>();

    public SearchCriteria() {
    }

    public SearchCriteria(Author author, ArrayList<Tag> tags) {
        this.author = author;
        this.tags = tags;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }
}
