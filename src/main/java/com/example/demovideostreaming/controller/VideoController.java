package com.example.demovideostreaming.controller;

import com.example.demovideostreaming.dao.domain.Video;
import com.example.demovideostreaming.dao.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VideoController {
    private final VideoRepository repository;

    @Autowired
    public VideoController(VideoRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public List<Video> allVideos() {
        return repository.findAll();
    }

    @MutationMapping
    public Video publishVideo(@Argument Video video) {
        return repository.save(video);
    }
}
