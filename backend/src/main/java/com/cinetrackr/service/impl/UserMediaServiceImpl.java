package com.cinetrackr.service.impl;

import com.cinetrackr.dto.UserMediaDTO;
import com.cinetrackr.model.Media;
import com.cinetrackr.model.User;
import com.cinetrackr.model.UserMedia;
import com.cinetrackr.model.enums.WatchStatus;
import com.cinetrackr.model.exception.DuplicateEntityException;
import com.cinetrackr.model.exception.ResourceNotFoundException;
import com.cinetrackr.repository.MediaRepository;
import com.cinetrackr.repository.UserMediaRepository;
import com.cinetrackr.repository.UserRepository;
import com.cinetrackr.service.UserMediaService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMediaServiceImpl implements UserMediaService {

    private final UserMediaRepository userMediaRepository;
    private final UserRepository userRepository;
    private final MediaRepository mediaRepository;

    public UserMediaServiceImpl(UserMediaRepository userMediaRepository,
                                UserRepository userRepository,
                                MediaRepository mediaRepository) {
        this.userMediaRepository = userMediaRepository;
        this.userRepository = userRepository;
        this.mediaRepository = mediaRepository;
    }

    @Override
    public UserMediaDTO add(UserMediaDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + dto.getUserId() + " does not exist"));

        Media media = mediaRepository.findById(dto.getMediaId())
                .orElseThrow(() -> new ResourceNotFoundException("Media with id: " + dto.getMediaId() + " does not exist"));

        if (userMediaRepository.existsByUserIdAndMediaId(dto.getUserId(), dto.getMediaId())) {
            throw new DuplicateEntityException("UserMedia does not exist");
        }

        UserMedia userMedia = new UserMedia();
        userMedia.setUserId(user.getId());
        userMedia.setMediaId(media.getId());
        userMedia.setStatus(dto.getStatus());
        userMedia.setRating(dto.getRating());
        userMedia.setAddedAt(LocalDateTime.now());
        userMedia.setUpdatedAt(LocalDateTime.now());

        return toDTO(userMediaRepository.save(userMedia), media);
    }

    @Override
    public List<UserMediaDTO> getByUser(String userId) {
        return userMediaRepository.findByUserId(userId)
                .stream()
                .map(um -> {
                    Media media = mediaRepository.findById(um.getMediaId())
                            .orElse(new Media());
                    return toDTO(um, media);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<UserMediaDTO> getByUserAndStatus(String userId, WatchStatus status) {
        return userMediaRepository.findByUserIdAndStatus(userId, status)
                .stream()
                .map(um -> {
                    Media media = mediaRepository.findById(um.getMediaId())
                            .orElse(new Media());
                    return toDTO(um, media);
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserMediaDTO update(String id, UserMediaDTO dto) {
        UserMedia userMedia = userMediaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserMedia with id: " + id + " does not exist"));

        Media media = mediaRepository.findById(userMedia.getMediaId())
                .orElseThrow(() -> new ResourceNotFoundException("Media with id: " + userMedia.getMediaId() + " does not exist"));

        userMedia.setStatus(dto.getStatus());
        userMedia.setRating(dto.getRating());
        userMedia.setUpdatedAt(LocalDateTime.now());

        return toDTO(userMediaRepository.save(userMedia), media);
    }

    @Override
    public void delete(String id) {
        if (!userMediaRepository.existsById(id)) {
            throw new ResourceNotFoundException("UserMedia with id: " + id + " does not exist");
        }
        userMediaRepository.deleteById(id);
    }

    private UserMediaDTO toDTO(UserMedia um, Media media) {
        UserMediaDTO dto = new UserMediaDTO();
        dto.setId(um.getId());
        dto.setUserId(um.getUserId());
        dto.setMediaId(um.getMediaId());
        dto.setMediaTitle(media.getTitle());
        dto.setMediaPosterUrl(media.getPosterUrl());
        dto.setMediaType(media.getType());
        dto.setStatus(um.getStatus());
        dto.setRating(um.getRating());
        return dto;
    }
}