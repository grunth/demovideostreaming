package com.example.demovideostreaming.controller;

import com.example.demovideostreaming.dao.domain.Video;
import com.example.demovideostreaming.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VideoController {
    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @QueryMapping
    public List<Video> allVideos() {
        return videoService.findAll();
    }

    @MutationMapping
    public Video publishVideo(@Argument Video video) {
        return videoService.save(video);
    }

    @MutationMapping
    public Boolean delistVideo(@Argument Long id) {
        return videoService.delist(id);
    }
}
