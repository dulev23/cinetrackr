package com.cinetrackr.config;

import com.cinetrackr.model.Media;
import com.cinetrackr.model.enums.MediaType;
import com.cinetrackr.repository.MediaRepository;
import com.cinetrackr.service.MediaService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final MediaRepository mediaRepository;

    public DataInitializer(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @PostConstruct
    public void initData() {
        if (mediaRepository.count() > 0) return;

        mediaRepository.save(new Media(0, "The Shawshank Redemption", "Drama", 1994, "Two imprisoned men bond over years, finding solace and eventual redemption.", "https://www.movieposters.com/cdn/shop/products/26b09636b0ee91f08bea4ec4941fc63c_93098e4c-caa8-4c6f-b618-c10fbd03fb9f.jpg?v=1762497218&width=1680", MediaType.MOVIES, null));
        mediaRepository.save(new Media(0, "The Godfather", "Crime", 1972, "The aging patriarch of an organized crime dynasty transfers control to his son.", "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg", MediaType.MOVIES, null));
        mediaRepository.save(new Media(0, "Inception", "Sci-Fi", 2010, "A thief who steals corporate secrets through dream-sharing technology.", "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg", MediaType.MOVIES, null));
        mediaRepository.save(new Media(0, "Interstellar", "Sci-Fi", 2014, "A team of explorers travel through a wormhole to ensure humanity's survival.", "https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg", MediaType.MOVIES, null));
        mediaRepository.save(new Media(0, "The Dark Knight", "Action", 2008, "Batman faces the Joker, a criminal mastermind who wants to plunge Gotham into anarchy.", "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_SX300.jpg", MediaType.MOVIES, null));
        mediaRepository.save(new Media(0, "Parasite", "Thriller", 2019, "A poor family schemes to become employed by a wealthy family.", "https://m.media-amazon.com/images/M/MV5BYWZjMjk3ZTItODQ2ZC00NTY5LWE0ZDYtZTI3MjcwN2Q5NTVkXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_SX300.jpg", MediaType.MOVIES, null));
        mediaRepository.save(new Media(0, "Oppenheimer", "Drama", 2023, "The story of J. Robert Oppenheimer and the development of the atomic bomb.", "https://m.media-amazon.com/images/M/MV5BMDBmYTZjNjUtN2M1MS00MTQ2LTk2ODgtNzc2M2QyZGE5NTVjXkEyXkFqcGdeQXVyNzAwMjU2MTY@._V1_SX300.jpg", MediaType.MOVIES, null));

        mediaRepository.save(new Media(0, "Breaking Bad", "Crime", 2008, "A high school chemistry teacher turned methamphetamine manufacturer.", "https://m.media-amazon.com/images/I/812BhXzap+L._AC_SY879_.jpg", MediaType.SERIES, null));
        mediaRepository.save(new Media(0, "Chernobyl", "Drama", 2019, "The true story of one of the worst man-made catastrophes in history.", "https://i.ebayimg.com/images/g/8gYAAeSwPu5ometh/s-l1600.webp", MediaType.SERIES, null));
        mediaRepository.save(new Media(0, "Severance", "Sci-Fi", 2022, "Workers undergo a procedure that severs their memories between work and personal life.", "https://media.themoviedb.org/t/p/w440_and_h660_face/lFf6LLrQjYldcZItzOkGmMMigP7.jpg", MediaType.SERIES, null));
        mediaRepository.save(new Media(0, "The Last of Us", "Drama", 2023, "A smuggler and a teenage girl traverse a post-apocalyptic United States.", "https://m.media-amazon.com/images/M/MV5BZGUzYTI3M2EtZmM0Yy00NGUyLWI4ODEtN2Q3ZGJlYzhhZjU3XkEyXkFqcGdeQXVyNTM0OTY1OQ@@._V1_SX300.jpg", MediaType.SERIES, null));
        mediaRepository.save(new Media(0, "Shogun", "Drama", 2024, "A shipwrecked English sailor finds himself embroiled in a feudal Japanese power struggle.", "https://preview.redd.it/the-shogun-series-poster-looks-epic-v0-z029mpnywigb1.jpg?width=1080&crop=smart&auto=webp&s=e15e2c21e0705f096e51f765e5cfe842d45d7803", MediaType.SERIES, null));
    }
}