package com.friendfinder.service;

import com.friendfinder.model.User;
import com.friendfinder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findFriendsAtDistance(Long userId, int distance) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        Set<User> visited = new HashSet<>();
        Map<User, Integer> userDistances = new HashMap<>();
        Queue<User> queue = new LinkedList<>();

        // Start BFS from the source user
        queue.offer(user);
        visited.add(user);
        userDistances.put(user, 0);

        while (!queue.isEmpty()) {
            User currentUser = queue.poll();
            int currentDistance = userDistances.get(currentUser);

            if (currentDistance < distance) {
                for (User friend : currentUser.getFriends()) {
                    if (!visited.contains(friend)) {
                        queue.offer(friend);
                        visited.add(friend);
                        userDistances.put(friend, currentDistance + 1);
                    }
                }
            }
        }

        // Filter users at the exact distance
        return userDistances.entrySet().stream()
            .filter(entry -> entry.getValue() == distance)
            .map(Map.Entry::getKey)
            .toList();
    }

    @Override
    public List<User> findShortestPath(Long sourceUserId, Long targetUserId) {
        User sourceUser = userRepository.findById(sourceUserId)
            .orElseThrow(() -> new IllegalArgumentException("Source user not found with id: " + sourceUserId));
        User targetUser = userRepository.findById(targetUserId)
            .orElseThrow(() -> new IllegalArgumentException("Target user not found with id: " + targetUserId));

        Map<User, User> parentMap = new HashMap<>();
        Set<User> visited = new HashSet<>();
        Queue<User> queue = new LinkedList<>();

        // Start BFS from the source user
        queue.offer(sourceUser);
        visited.add(sourceUser);

        boolean found = false;
        while (!queue.isEmpty()) {
            User currentUser = queue.poll();

            if (currentUser.equals(targetUser)) {
                found = true;
                break;
            }

            for (User friend : currentUser.getFriends()) {
                if (!visited.contains(friend)) {
                    queue.offer(friend);
                    visited.add(friend);
                    parentMap.put(friend, currentUser);
                }
            }
        }

        if (!found) {
            return List.of(); // No path exists
        }

        // Reconstruct the path
        List<User> path = new ArrayList<>();
        User current = targetUser;
        while (current != null) {
            path.add(current);
            current = parentMap.get(current);
        }
        Collections.reverse(path);
        return path;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void addFriend(Long userId, Long friendId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        User friend = userRepository.findById(friendId)
            .orElseThrow(() -> new IllegalArgumentException("Friend not found with id: " + friendId));

        user.addFriend(friend);
        userRepository.save(user);
    }
}
