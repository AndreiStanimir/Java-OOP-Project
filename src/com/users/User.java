package com.users;

import java.util.Objects;
import java.util.UUID;

public abstract class User {
    String id;
    String name;
    String password;

    public String getPassword() {
        return password;
    }

    public User(String name, String password) {
        id = UUID.randomUUID().toString();
        this.name = name;
        this.password = password;
    }
    public User(String id, String name, String password) {
        this.id=id;
        this.name = name;
        this.password = password;
    }

//    public User(String name, String password) {
//        this.id = -1;
//        this.name = name;
//        this.password = password;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id ==user.id;
    }

    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

