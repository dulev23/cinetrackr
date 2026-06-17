package com.cinetrackr.service.impl;

import com.cinetrackr.dto.UserMediaDTO;
import com.cinetrackr.model.Media;
import com.cinetrackr.model.User;
import com.cinetrackr.model.UserMedia;
import com.cinetrackr.model.enums.WatchStatus;
import com.cinetrackr.model.exception.ResourceNotFoundException;
import com.cinetrackr.repository.MediaRepository;
import com.cinetrackr.repository.UserMediaRepository;
import com.cinetrackr.repository.UserRepository;
import com.cinetrackr.service.UserMediaService;
import org.springframework.stereotype.Service;

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

    private UserMediaDTO toDTO(UserMedia um) {
        UserMediaDTO dto = new UserMediaDTO();
        dto.setId(um.getId());
        dto.setUserId(um.getUser().getId());
        dto.setMediaId(um.getMedia().getId());
        dto.setMediaTitle(um.getMedia().getTitle());
        dto.setMediaPosterUrl(um.getMedia().getPosterUrl());
        dto.setMediaType(um.getMedia().getType());
        dto.setStatus(um.getStatus());
        dto.setRating(um.getRating());
        return dto;
    }


    @Override
    public UserMediaDTO add(UserMediaDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Media media = mediaRepository.findById(dto.getMediaId())
                .orElseThrow(() -> new RuntimeException("Media not found"));

        UserMedia existing = userMediaRepository
                .findByUserIdAndMediaId(dto.getUserId(), dto.getMediaId())
                .orElse(null);

        if (existing != null) {
            existing.setStatus(dto.getStatus());
            existing.setRating(dto.getRating());

            return toDTO(userMediaRepository.save(existing));
        }

        UserMedia um = new UserMedia();
        um.setUser(user);
        um.setMedia(media);
        um.setStatus(dto.getStatus());
        um.setRating(dto.getRating());

        return toDTO(userMediaRepository.save(um));
    }

    @Override
    public List<UserMediaDTO> getByUser(Long userId) {
        return this.userMediaRepository.findByUserId(userId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserMediaDTO> getByUserAndStatus(Long userId, WatchStatus status) {
        return this.userMediaRepository.findByUserIdAndStatus(userId, status)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserMediaDTO update(Long id, UserMediaDTO dto) {
        UserMedia userMedia = userMediaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Media with id: " + id + " not found"));

        userMedia.setStatus(dto.getStatus());
        userMedia.setRating(dto.getRating());

        return toDTO(userMediaRepository.save(userMedia));
    }

    @Override
    public void delete(Long id) {
        if (!userMediaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Media with id: " + id + " not found");
        }
        userMediaRepository.deleteById(id);
    }
}
