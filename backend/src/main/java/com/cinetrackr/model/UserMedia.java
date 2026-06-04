package com.cinetrackr.model;

import com.cinetrackr.model.enums.WatchStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_media")
public class UserMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "media_id", nullable = false)
    private Media media;

    private WatchStatus status;
    private double rating;
}