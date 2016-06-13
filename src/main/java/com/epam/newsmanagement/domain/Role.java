package com.epam.newsmanagement.domain;

/**
 * Created by Никита on 25.05.2016.
 */
public class Role extends Entity {
    private Long userId;
    private String roleName;

    public Role() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        if (userId != null ? !userId.equals(role.userId) : role.userId != null) return false;
        return roleName != null ? roleName.equals(role.roleName) : role.roleName == null;

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }
}
