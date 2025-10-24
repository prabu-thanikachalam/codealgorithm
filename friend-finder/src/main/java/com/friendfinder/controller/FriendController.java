package com.friendfinder.controller;

import com.friendfinder.model.User;
import com.friendfinder.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @GetMapping("/{userId}/distance/{distance}")
    public ResponseEntity<List<User>> getFriendsAtDistance(
            @PathVariable Long userId,
            @PathVariable int distance) {
        return ResponseEntity.ok(friendService.findFriendsAtDistance(userId, distance));
    }

    @GetMapping("/path")
    public ResponseEntity<List<User>> getShortestPath(
            @RequestParam Long sourceUserId,
            @RequestParam Long targetUserId) {
        return ResponseEntity.ok(friendService.findShortestPath(sourceUserId, targetUserId));
    }

    @PostMapping("/user")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        friendService.addUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(friendService.getAllUsers());
    }

    @PostMapping("/{userId}/friend/{friendId}")
    public ResponseEntity<Void> addFriend(
            @PathVariable Long userId,
            @PathVariable Long friendId) {
        friendService.addFriend(userId, friendId);
        return ResponseEntity.ok().build();
    }
}
