package com.example.managers;

import com.example.pojos.User;
import com.example.helpers.JsonReader;
import java.util.List;

public class UserManager {
    private User user;
    
    public void setUser(String userName){
        try {
            this.user = loadUserByName(userName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public User getUser() {
        if (user == null) {
            throw new IllegalStateException("No current user set");
        }
        return user;
    }
    
    public List<User> getAllUsers() throws Exception {
        return JsonReader.getAllUsers();
    }
    
    private User loadUserByName(String userName) throws Exception {
        return getAllUsers().stream()
            .filter(u -> u.getName().equalsIgnoreCase(userName))
            .findFirst()
            .orElseThrow(() -> new Exception("User not found: " + userName));
    }
}
