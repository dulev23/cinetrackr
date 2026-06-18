package com.cinetrackr.repository;

import com.cinetrackr.model.UserMedia;
import com.cinetrackr.model.enums.WatchStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserMediaRepository extends MongoRepository<UserMedia, String> {
    boolean existsByUserIdAndMediaId(String userId, String mediaId);

    List<UserMedia> findByUserId(String userId);

    List<UserMedia> findByUserIdAndStatus(String userId, WatchStatus status);

    Optional<UserMedia> findByUserIdAndMediaId(String userId, String mediaId);
}