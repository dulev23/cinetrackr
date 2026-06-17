package com.cinetrackr.web;

import com.cinetrackr.dto.MediaDTO;
import com.cinetrackr.service.MediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {
    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping
    public ResponseEntity<MediaDTO> create(@RequestBody MediaDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(mediaService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(mediaService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<MediaDTO>> getAll(){
        return ResponseEntity.ok(mediaService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MediaDTO> update(@PathVariable Long id,
                                           @RequestBody MediaDTO dto){
        return ResponseEntity.ok(mediaService.update(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MediaDTO> delete(@PathVariable Long id){
        mediaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
