package com.cinetrackr.repository;

import com.cinetrackr.model.UserMedia;
import com.cinetrackr.model.enums.WatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserMediaRepository extends JpaRepository<UserMedia, Long> {
    boolean existsByUserIdAndMediaId(Long userId, Long mediaId);
    List<UserMedia> findByUserId(Long userId);
    List<UserMedia> findByUserIdAndStatus(Long userId, WatchStatus status);
    Optional<UserMedia> findByUserIdAndMediaId(Long userId, Long mediaId);
}