package com.friendfinder.service;

import com.friendfinder.model.User;
import java.util.List;

public interface FriendService {
    List<User> findFriendsAtDistance(Long userId, int distance);
    List<User> findShortestPath(Long sourceUserId, Long targetUserId);
    void addUser(User user);
    List<User> getAllUsers();
    void addFriend(Long userId, Long friendId);
}
