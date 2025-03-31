package com.example.restapi.service.serviceImp;

import com.example.restapi.exception.BadRequestException;
import com.example.restapi.model.Music;
import com.example.restapi.model.MusicList;
import com.example.restapi.model.request.MusicRequest;
import com.example.restapi.repository.MusicRepository;
import com.example.restapi.service.MusicService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MusicServiceImp implements MusicService {

    private MusicRepository musicRepository;

    @Override
    public List<Music> getAllMusic() {
        return musicRepository.getAllMusic();
    }

    @Override
    public Music getByIdMusic(Long id) {
        Music music = musicRepository.getByIdMusic(id);
        if(music == null){
            throw new BadRequestException("Get music by id " +id+ " is null");
        }
        return music;
    }

    @Override
    public Music createMusic(MusicRequest musicRequest) {
        return musicRepository.createMusic(musicRequest);
    }

    @Override
    public void deleteMusic(Long id) {
        getByIdMusic(id);
        musicRepository.deleteById(id);
    }

    @Override
    public List<MusicList> getAllMusicList() {
        return musicRepository.getAllMusicList();
    }

    @Override
    public void deleteMusicList(Long id) {
       musicRepository.deleteMusicListById(id);
    }

    @Override
    public MusicList createMusicList(MusicRequest musicRequest) {
        return musicRepository.createMusicList(musicRequest);
    }
}
