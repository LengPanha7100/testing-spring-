package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicList {
    private int id;
    private String musicTitle;
    private String musicName;
    private String image;
    private int listener;
    private String musicType;
    private String duration;
}
