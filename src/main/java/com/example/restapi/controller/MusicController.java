package com.example.restapi.controller;

import com.example.restapi.model.APIResponse;
import com.example.restapi.model.Music;
import com.example.restapi.model.MusicList;
import com.example.restapi.model.request.MusicRequest;
import com.example.restapi.service.MusicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/music")
@AllArgsConstructor
public class MusicController {
     private MusicService musicService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Music>>> getAllMusic() {
         List<Music> music = musicService.getAllMusic();
         APIResponse<List<Music>> apiResponse = APIResponse.<List<Music>>builder()
                 .message("Get all music success")
                 .status(HttpStatus.OK)
                 .payload(music)
                 .build();
         return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Music>> getMusicById(@PathVariable Long id) {
        Music music = musicService.getByIdMusic(id);
        APIResponse<Music> apiResponse = APIResponse.<Music>builder()
                .message("Get music by id success")
                .status(HttpStatus.OK)
                .payload(music)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Music>> createMusic(@RequestBody MusicRequest musicRequest) {
        Music music = musicService.createMusic(musicRequest);
        APIResponse<Music> musicAPIResponse = APIResponse.<Music>builder()
                .message("Create music success")
                .status(HttpStatus.OK)
                .payload(music)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(musicAPIResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Music>> deleteMusic(@PathVariable Long id) {
        musicService.deleteMusic(id);
        APIResponse<Music> apiResponse = APIResponse.<Music>builder()
                .message("Delete music by id success")
                .status(HttpStatus.OK)
                .payload(null)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/musicList")
    public ResponseEntity<APIResponse<List<MusicList>>> getAllMusicList() {
        List<MusicList> music = musicService.getAllMusicList();
        APIResponse<List<MusicList>> apiResponse = APIResponse.<List<MusicList>>builder()
                .message("Get all music list success")
                .status(HttpStatus.OK)
                .payload(music)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/musicList/{id}")
    public ResponseEntity<APIResponse<MusicList>> deleteMusicList(@PathVariable Long id) {
        musicService.deleteMusicList(id);
        APIResponse<MusicList> apiResponse = APIResponse.<MusicList>builder()
                .message("Delete music list by id success")
                .status(HttpStatus.OK)
                .payload(null)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/musicList")
    public ResponseEntity<APIResponse<MusicList>> createMusicList(@RequestBody MusicRequest musicRequest) {
        MusicList music = musicService.createMusicList(musicRequest);
        APIResponse<MusicList> musicAPIResponse = APIResponse.<MusicList>builder()
                .message("Create music list success")
                .status(HttpStatus.OK)
                .payload(music)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(musicAPIResponse);
    }
}
