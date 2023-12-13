package com.example.demovideostreaming.dao.domain;

import lombok.Data;

@Data
public class VideoInput {
    private String title;
    private String synopsis;
    private String director;
    private String cast;
    private Integer yearOfRelease;
    private String genre;
    private Integer runningTime;
}
