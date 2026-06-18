package com.cinetrackr.dto;

import com.cinetrackr.model.enums.MediaType;
import lombok.Data;

@Data
public class MediaDTO {
    private String id;
    private String title;
    private MediaType type;
    private Integer releaseYear;
    private String genre;
    private String description;
    private String posterUrl;
}
