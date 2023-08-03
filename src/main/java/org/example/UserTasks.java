package org.example;

import java.util.Objects;

public class UserTasks {
    private int userId;
    private int id;
    private String title;
    private String completed;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTasks userTasks = (UserTasks) o;
        return userId == userTasks.userId && id == userTasks.id && Objects.equals(title, userTasks.title) && Objects.equals(completed, userTasks.completed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, id, title, completed);
    }

    @Override
    public String toString() {
        return "UserTasks{" +
                "userId=" + userId +
                ", id=" + id +
                ", titele='" + title + '\'' +
                ", completed='" + completed + '\'' +
                '}';
    }
}
