package com.cinetrackr.model;

import com.cinetrackr.model.enums.WatchStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "user_media")
public class UserMedia {

    @Id
    private String id;

    private String userId;
    private String mediaId;

    private WatchStatus status;
    private double rating;
    private String notes;

    private LocalDateTime addedAt;
    private LocalDateTime updatedAt;
}