package com.cinetrackr.service.impl;

import com.cinetrackr.dto.MediaDTO;
import com.cinetrackr.model.Media;
import com.cinetrackr.model.exception.ResourceNotFoundException;
import com.cinetrackr.repository.MediaRepository;
import com.cinetrackr.service.MediaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;

    public MediaServiceImpl(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    private Media toEntity(MediaDTO dto) {
        Media media = new Media();
        media.setTitle(dto.getTitle());
        media.setType(dto.getType());
        media.setReleaseYear(dto.getReleaseYear());
        media.setGenre(dto.getGenre());
        media.setDescription(dto.getDescription());
        media.setPosterUrl(dto.getPosterUrl());
        return media;
    }

    private MediaDTO toDTO(Media media) {
        MediaDTO dto = new MediaDTO();
        dto.setId(media.getId());
        dto.setTitle(media.getTitle());
        dto.setGenre(media.getGenre());
        dto.setReleaseYear(media.getReleaseYear());
        dto.setDescription(media.getDescription());
        dto.setPosterUrl(media.getPosterUrl());
        dto.setType(media.getType());
        return dto;
    }

    @Override
    public MediaDTO create(MediaDTO dto) {
        Media media = toEntity(dto);
        return toDTO(this.mediaRepository.save(media));
    }

    @Override
    public MediaDTO getById(String id) {
        Media media = this.mediaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Media with id: " + id + " not found"));
        return toDTO(media);
    }

    @Override
    public List<MediaDTO> getAll() {
        return this.mediaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MediaDTO update(String id, MediaDTO dto) {
        Media media = this.mediaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Media with id: " + id + " not found"));

        media.setTitle(dto.getTitle());
        media.setType(dto.getType());
        media.setReleaseYear(dto.getReleaseYear());
        media.setGenre(dto.getGenre());
        media.setDescription(dto.getDescription());
        media.setPosterUrl(dto.getPosterUrl());

        return toDTO(this.mediaRepository.save(media));
    }

    @Override
    public void delete(String id) {
        if (!this.mediaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Media with id: " + id + " not found");
        }
        this.mediaRepository.deleteById(id);
    }
}
