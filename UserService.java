package com.example.crud.service;

import com.example.crud.entity.User;
import com.example.crud.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public String saveUser(User user) {
        Optional<User> existingUser = repo.findByNameAndEmail(user.getName(), user.getEmail());
        if (existingUser.isPresent()) {
            return "exists";
        } else {
            repo.save(user);
            return "added";
        }
    }

    public User getUser(Long id) {
        return repo.findById(id).orElse(null);
    }

    public boolean deleteUser(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = repo.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            repo.save(user);
            return true;
        } else {
            return false;
        }
    }


}
