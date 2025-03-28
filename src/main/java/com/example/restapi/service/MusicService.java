package com.example.restapi.service;

import com.example.restapi.model.Music;
import com.example.restapi.model.MusicList;
import com.example.restapi.model.request.MusicRequest;

import java.util.List;

public interface MusicService {
    List<Music> getAllMusic();

    Music getByIdMusic(Long id);

    Music createMusic(MusicRequest musicRequest);

    void deleteMusic(Long id);

    List<MusicList> getAllMusicList();

    void deleteMusicList(Long id);

    MusicList createMusicList(MusicRequest musicRequest);
}
