package com.cinetrackr.service;

import com.cinetrackr.dto.UserDTO;
import com.cinetrackr.model.User;
import com.cinetrackr.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    UserDTO register(UserDTO dto);
    UserDTO getById(Long id);
    UserDTO getByUsername(String username);
}
