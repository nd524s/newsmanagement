package com.epam.newsmanagement.domain;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Никита on 26.05.2016.
 */
public class News extends Entity {
    private Long newsId;
    private String title;
    private String shortText;
    private String fullText;
    private Timestamp creationDate;
    private Timestamp modificationDate;
    private ArrayList<Author> authors = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();
    private ArrayList<Tag> tags = new ArrayList<>();

    public News() {
    }

    public News(String title, String shortText, String fullText, Timestamp creationDate, Timestamp modificationDate) {
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public News(Long newsId, String title, String shortText, String fullText, Timestamp creationDate, Timestamp modificationDate, ArrayList<Author> authors, ArrayList<Comment> comments, ArrayList<Tag> tags) {
        this.newsId = newsId;
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.authors = authors;
        this.comments = comments;
        this.tags = tags;
    }

    public News(Long newsId, String title, String shortText, String fullText, Timestamp creationDate, Timestamp modificationDate) {
        this.newsId = newsId;
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public News(String title, String shortText, String fullText, Timestamp creationDate, Timestamp modificationDate, ArrayList<Author> authors, ArrayList<Tag> tags) {
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.authors = authors;
        this.tags = tags;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
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

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Timestamp modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;

        News news = (News) o;

        if (newsId != null ? !newsId.equals(news.newsId) : news.newsId != null) return false;
        if (title != null ? !title.equals(news.title) : news.title != null) return false;
        if (shortText != null ? !shortText.equals(news.shortText) : news.shortText != null) return false;
        if (fullText != null ? !fullText.equals(news.fullText) : news.fullText != null) return false;
        if (creationDate != null ? !creationDate.equals(news.creationDate) : news.creationDate != null) return false;
        if (modificationDate != null ? !modificationDate.equals(news.modificationDate) : news.modificationDate != null)
            return false;
        if (authors != null ? !authors.equals(news.authors) : news.authors != null) return false;
        if (comments != null ? !comments.equals(news.comments) : news.comments != null) return false;
        return tags != null ? tags.equals(news.tags) : news.tags == null;

    }

    @Override
    public int hashCode() {
        int result = newsId != null ? newsId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (shortText != null ? shortText.hashCode() : 0);
        result = 31 * result + (fullText != null ? fullText.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (modificationDate != null ? modificationDate.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }
}
