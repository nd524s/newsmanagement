package com.epam.newsmanagement.domain;

/**
 * Created by Никита on 25.05.2016.
 */
public class Tag extends Entity {
    private Long tagId;
    private String tagName;

    public Tag() {
    }

    public Tag(Long tagId) {
        this.tagId = tagId;
    }

    public Tag(Long tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        if (tagId != null ? !tagId.equals(tag.tagId) : tag.tagId != null) return false;
        return tagName != null ? tagName.equals(tag.tagName) : tag.tagName == null;

    }

    @Override
    public int hashCode() {
        int result = tagId != null ? tagId.hashCode() : 0;
        result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
        return result;
    }
}
