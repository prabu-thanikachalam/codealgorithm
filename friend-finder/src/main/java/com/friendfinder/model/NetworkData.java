package com.friendfinder.model;

import java.util.List;

public class NetworkData {
    private List<User> users;

    public NetworkData() {
    }

    public NetworkData(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
