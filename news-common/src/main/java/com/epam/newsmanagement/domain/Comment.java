package com.epam.newsmanagement.domain;

import java.sql.Timestamp;

/**
 * Created by Никита on 6/1/2016.
 */
public class Comment extends Entity {
    private Long commentId;
    private Long newsId;
    private String commentText;
    private Timestamp creationDate;

    public Comment() {
    }

    public Comment(Long newsId, String commentText, Timestamp creationDate) {
        this.newsId = newsId;
        this.commentText = commentText;
        this.creationDate = creationDate;
    }

    public Comment(Long commentId, Long newsId, String commentText, Timestamp creationDate) {
        this.commentId = commentId;
        this.newsId = newsId;
        this.commentText = commentText;
        this.creationDate = creationDate;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (commentId != null ? !commentId.equals(comment.commentId) : comment.commentId != null) return false;
        if (newsId != null ? !newsId.equals(comment.newsId) : comment.newsId != null) return false;
        if (commentText != null ? !commentText.equals(comment.commentText) : comment.commentText != null) return false;
        return creationDate != null ? creationDate.equals(comment.creationDate) : comment.creationDate == null;

    }

    @Override
    public int hashCode() {
        int result = commentId != null ? commentId.hashCode() : 0;
        result = 31 * result + (newsId != null ? newsId.hashCode() : 0);
        result = 31 * result + (commentText != null ? commentText.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }
}
