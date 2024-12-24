package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.project.dto.User;

@Service
public class LoginOutService {

	// 예시를 위한 하드코딩된 사용자 목록
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User("user1", "password1"));
        users.add(new User("user2", "password2"));
    }

    public User authenticate(String id, String password) {
        for (User user : users) {
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
