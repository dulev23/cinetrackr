package com.cinetrackr.model;

import com.cinetrackr.model.enums.MediaType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "media")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    private String title;
    private String genre;
    private int releaseYear;
    private String description;
    private String posterUrl;

    @Enumerated(EnumType.STRING)
    private MediaType type;

    @JsonIgnore
    @OneToMany(mappedBy = "media")
    private List<UserMedia> userMediaList;
}
