package com.cinetrackr.service.impl;

import com.cinetrackr.dto.UserDTO;
import com.cinetrackr.model.User;
import com.cinetrackr.model.exception.ResourceNotFoundException;
import com.cinetrackr.repository.UserRepository;
import com.cinetrackr.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }

    @Override
    public UserDTO register(UserDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username already taken");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        User saved = userRepository.save(user);

        return toDTO(saved);
    }

    @Override
    public UserDTO getById(Long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found"));
        return toDTO(user);
    }

    @Override
    public UserDTO getByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with username: " + username + " not found"));
        return toDTO(user);
    }
}
