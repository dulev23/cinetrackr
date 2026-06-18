package com.cinetrackr.dto;

import com.cinetrackr.model.enums.MediaType;
import com.cinetrackr.model.enums.WatchStatus;
import lombok.Data;

@Data
public class UserMediaDTO {
    private String id;
    private String userId;
    private String mediaId;

    private String mediaTitle;
    private String mediaPosterUrl;
    private MediaType mediaType;

    private WatchStatus status;
    private double rating;
}
