package com.cinetrackr.service;

import com.cinetrackr.dto.UserDTO;

public interface UserService {
    UserDTO register(UserDTO dto);
    UserDTO getById(String id);
    UserDTO getByUsername(String username);
    UserDTO login(UserDTO dto);
}
