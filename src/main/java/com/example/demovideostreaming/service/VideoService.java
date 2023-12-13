package com.example.demovideostreaming.service;

import com.example.demovideostreaming.dao.domain.Engagement;
import com.example.demovideostreaming.dao.domain.Video;
import com.example.demovideostreaming.dao.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {
    private final VideoRepository repository;

    @Autowired
    public VideoService(VideoRepository repository) {
        this.repository = repository;
    }

    public List<Video> findAll() {
        return repository.findAllByDelistedIsFalse();
    }

    public Video save(Video video) {
        Engagement engagement = new Engagement();
        engagement.setVideo(video);
        video.setEngagement(engagement);
        return repository.save(video);
    }

    public Boolean delist(Long id) {
        return repository.findById(id).map(video -> {
            video.setDelisted(true);
            return repository.save(video).getDelisted();
        }).orElseThrow(() -> new RuntimeException("Video not found with ID: " + id));
    }
}
