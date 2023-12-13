package com.example.demovideostreaming.service;

import com.example.demovideostreaming.dao.domain.Engagement;
import com.example.demovideostreaming.dao.domain.Video;
import com.example.demovideostreaming.dao.repository.EngagementRepository;
import com.example.demovideostreaming.dao.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngagementService {

    private final EngagementRepository engagementRepository;

    private final VideoRepository videoRepository;

    @Autowired
    public EngagementService(EngagementRepository engagementRepository, VideoRepository videoRepository) {
        this.engagementRepository = engagementRepository;
        this.videoRepository = videoRepository;
    }

    public Boolean recordImpression(Long videoId) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found with ID: " + videoId));

        Engagement engagement = getOrCreateEngagement(video);
        engagement.setImpressions(engagement.getImpressions() + 1);
        engagementRepository.save(engagement);
        return true;
    }

    public Boolean recordView(Long videoId) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found with ID: " + videoId));

        Engagement engagement = getOrCreateEngagement(video);
        engagement.setViews(engagement.getViews() + 1);
        engagementRepository.save(engagement);
        return true;
    }

    private Engagement getOrCreateEngagement(Video video) {
        return engagementRepository.findByVideoId(video.getId())
                .orElseGet(() -> {
                    Engagement newEngagement = new Engagement();
                    newEngagement.setVideo(video);
                    return engagementRepository.save(newEngagement);
                });
    }

    public List<Engagement> findAll() {
        return engagementRepository.findAll();
    }
}
