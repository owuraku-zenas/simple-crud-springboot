package com.example.assessment.service;

import com.example.assessment.entity.User;
import com.example.assessment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User saveUser(User user) {
        return repository.save(user);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUser(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Boolean deleteUser(Integer id) {
        repository.deleteById(id);
        return TRUE;
    }

    public User updateUser(User user) {
        return repository.save(user);
    }
}
