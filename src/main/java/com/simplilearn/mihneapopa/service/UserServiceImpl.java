package com.simplilearn.mihneapopa.service;

import com.simplilearn.mihneapopa.exceptions.UserNotFoundException;
import com.simplilearn.mihneapopa.model.User;
import com.simplilearn.mihneapopa.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) throws UserNotFoundException {
        try {
            Optional<User> user = userRepository.findById(id);
            return user.get();
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        try {
            return userRepository.findByUsername(username);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) throws UserNotFoundException {
        try {
            Optional<User> userOptional = userRepository.findById(user.getId());
            if (!userOptional.isPresent()) {
                throw new UserNotFoundException();
            }

            User existingUser = userOptional.get();
            existingUser.setUsername(user.getUsername());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setRole(user.getRole());
            existingUser.setActive(user.isActive());

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
            user.setPassword(encoder.encode(user.getPassword()));

            return userRepository.save(existingUser);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) throws UserNotFoundException {
        try {
            Optional<User> user = userRepository.findById(id);
            user.ifPresent(value -> userRepository.delete(value));
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found for username: " + username);
        }
        if (user.getUsername() == null || user.getRole() == null) {
            throw new IllegalArgumentException("Username or role cannot be null");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
