package com.cinetrackr.dto;

import com.cinetrackr.model.enums.MediaType;
import com.cinetrackr.model.enums.WatchStatus;
import lombok.Data;

@Data
public class UserMediaDTO {
    private Long id;
    private Long userId;
    private Long mediaId;

    private String mediaTitle;
    private String mediaPosterUrl;
    private MediaType mediaType;

    private WatchStatus status;
    private double rating;
}
