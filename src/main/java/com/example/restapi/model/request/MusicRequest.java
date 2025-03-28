package com.example.restapi.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicRequest {
    private String title;
    private String name;
    private String amount_song;
    private String image;
}
