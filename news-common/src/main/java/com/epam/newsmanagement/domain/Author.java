package com.epam.newsmanagement.domain;

import java.sql.Timestamp;

/**
 * Created by Никита on 25.05.2016.
 */
public class Author extends Entity {
    private Long authorId;
    private String authorName;
    private Timestamp expired;

    public Author() {
    }

    public Author(String authorName, Timestamp expired) {
        this.authorName = authorName;
        this.expired = expired;
    }

    public Author(Long authorId, String authorName, Timestamp expired) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.expired = expired;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Timestamp getExpired() {
        return expired;
    }

    public void setExpired(Timestamp expired) {
        this.expired = expired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;

        Author author = (Author) o;

        if (authorId != null ? !authorId.equals(author.authorId) : author.authorId != null) return false;
        if (authorName != null ? !authorName.equals(author.authorName) : author.authorName != null) return false;
        return expired != null ? expired.equals(author.expired) : author.expired == null;

    }

    @Override
    public int hashCode() {
        int result = authorId != null ? authorId.hashCode() : 0;
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        result = 31 * result + (expired != null ? expired.hashCode() : 0);
        return result;
    }
}
