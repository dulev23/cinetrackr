package com.cinetrackr.service;

import com.cinetrackr.dto.MediaDTO;

import java.util.List;

public interface MediaService {
    MediaDTO create(MediaDTO dto);
    MediaDTO getById(Long id);
    List<MediaDTO> getAll();
    MediaDTO update(Long id, MediaDTO dto);
    void delete(Long id);
}
