package com.cinetrackr.service;

import com.cinetrackr.dto.MediaDTO;

import java.util.List;

public interface MediaService {
    MediaDTO create(MediaDTO dto);
    MediaDTO getById(String id);
    List<MediaDTO> getAll();
    MediaDTO update(String id, MediaDTO dto);
    void delete(String id);
}
