package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Music {
    private String id;
    private String title;
    private String name;
    private String amount_song;
    private String image;
}
