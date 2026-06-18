package com.cinetrackr.service;

import com.cinetrackr.dto.UserMediaDTO;
import com.cinetrackr.model.enums.WatchStatus;

import java.util.List;

public interface UserMediaService {
    UserMediaDTO add(UserMediaDTO dto);
    List<UserMediaDTO> getByUser(String userId);
    List<UserMediaDTO> getByUserAndStatus(String userId, WatchStatus status);
    UserMediaDTO update(String id, UserMediaDTO dto);
    void delete(String id);
}
