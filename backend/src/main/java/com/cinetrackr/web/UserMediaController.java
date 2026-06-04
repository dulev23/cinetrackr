package com.cinetrackr.web;

import com.cinetrackr.dto.UserMediaDTO;
import com.cinetrackr.model.enums.WatchStatus;
import com.cinetrackr.service.MediaService;
import com.cinetrackr.service.UserMediaService;
import com.cinetrackr.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-media")
public class UserMediaController {
    private final UserService userService;
    private final MediaService mediaService;
    private final UserMediaService userMediaService;

    public UserMediaController(UserService userService,
                               MediaService mediaService,
                               UserMediaService userMediaService) {
        this.userService = userService;
        this.mediaService = mediaService;
        this.userMediaService = userMediaService;
    }

    @PostMapping
    public ResponseEntity<UserMediaDTO> add(@RequestBody UserMediaDTO dto) {
        return ResponseEntity.ok(userMediaService.add(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserMediaDTO>> getByUser(@PathVariable Long userId,
                                                        @RequestParam(required = false) WatchStatus status) {
        if (status != null) {
            return ResponseEntity.ok(userMediaService.getByUserAndStatus(userId, status));
        }
        return ResponseEntity.ok(userMediaService.getByUser(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserMediaDTO> update(@PathVariable Long id,
                                               @RequestBody UserMediaDTO dto) {
        return ResponseEntity.ok(userMediaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserMediaDTO> delete(@PathVariable Long id) {
        userMediaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
