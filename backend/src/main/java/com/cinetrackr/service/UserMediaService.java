package com.cinetrackr.service;

import com.cinetrackr.dto.UserMediaDTO;
import com.cinetrackr.model.enums.WatchStatus;

import java.util.List;

public interface UserMediaService {
    UserMediaDTO add(UserMediaDTO dto);
    List<UserMediaDTO> getByUser(Long userId);
    List<UserMediaDTO> getByUserAndStatus(Long userId, WatchStatus status);
    UserMediaDTO update(Long id, UserMediaDTO dto);
    void delete(Long id);
}
