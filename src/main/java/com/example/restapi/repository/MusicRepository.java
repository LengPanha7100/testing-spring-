package com.example.restapi.repository;

import com.example.restapi.model.Music;
import com.example.restapi.model.MusicList;
import com.example.restapi.model.request.MusicRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MusicRepository {

    @Results(id = "musicId" , value = {
            @Result(property = "title" , column = "music_title"),
            @Result(property = "name" , column = "music_name")
    })
    @Select("""
    SELECT * FROM music_db;
    """)
    List<Music> getAllMusic();

    @Select("""
    SELECT  * FROM music_db WHERE id =#{id} ;
    """)
    Music getByIdMusic(Long id);

    @Select("""
        INSERT INTO music_db (music_title, music_name,amount_song, image)
        VALUES(#{music.title}, #{music.name},#{music.amount_song}, #{music.image})
        RETURNING *;
    """)
    @ResultMap("musicId")
    Music createMusic(@Param("music") MusicRequest musicRequest);

    @Delete("""
    DELETE FROM music_db WHERE id =#{id};
    """)
    void deleteById(Long id);

    @Results(id = "musicListId" , value = {
            @Result(property = "musicTitle" , column = "music_title"),
            @Result(property = "musicName" , column = "music_name"),
            @Result(property = "musicType" , column = "music_type")
    })
    @Select("""
    SELECT * FROM musicList_db;
    """)
    List<MusicList> getAllMusicList();

    @Delete("""
    DELETE FROM musicList_db WHERE id =#{id};
    """)
    void deleteMusicListById(Long id);

    @Select("""
    
    INSERT INTO musicList_db (music_title, music_name, image, listener, music_type, duration)
    VALUES(#{music.musicTitle}, #{music.musicName}, #{music.image}, #{music.listener}, #{music_musicType}, #{music.duration}),
    """)
    @ResultMap("musicListId")
    MusicList createMusicList(@Param("music") MusicRequest musicRequest);
}
