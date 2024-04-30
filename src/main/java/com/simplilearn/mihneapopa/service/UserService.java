package com.simplilearn.mihneapopa.service;

import com.simplilearn.mihneapopa.exceptions.UserNotFoundException;
import com.simplilearn.mihneapopa.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User getUserById(Integer id) throws UserNotFoundException;
    User getUserByUsername(String username) throws UserNotFoundException;
    User saveUser(User user);
    User updateUser(User user) throws UserNotFoundException;
    void deleteUser(Integer id) throws UserNotFoundException;
}
