package com.cinetrackr.repository;

import com.cinetrackr.model.Media;
import com.cinetrackr.model.enums.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {
    List<Media> findByType(MediaType type);
}