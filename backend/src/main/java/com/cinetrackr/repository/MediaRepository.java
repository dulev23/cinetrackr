package com.cinetrackr.repository;

import com.cinetrackr.model.Media;
import com.cinetrackr.model.enums.MediaType;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;

public interface MediaRepository extends MongoRepository<Media, String> {
    List<Media> findByType(MediaType type);
}