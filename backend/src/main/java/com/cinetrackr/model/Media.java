package com.cinetrackr.model;

import com.cinetrackr.model.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "media")
public class Media {

    @Id
    private String id;

    private String title;
    private String genre;
    private int releaseYear;
    private String description;
    private String posterUrl;
    private MediaType type;
}