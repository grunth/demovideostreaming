package com.example.demovideostreaming.controller;

import com.example.demovideostreaming.dao.domain.Engagement;
import com.example.demovideostreaming.service.EngagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EngagementController {
    private final EngagementService engagementService;

    @Autowired
    public EngagementController(EngagementService engagementService) {
        this.engagementService = engagementService;
    }

    @QueryMapping
    public List<Engagement> allEngagements() {
        return engagementService.findAll();
    }

    @MutationMapping
    public Boolean recordImpression(@Argument Long videoId) {
        return engagementService.recordImpression(videoId);
    }

    @MutationMapping
    public Boolean recordView(@Argument Long videoId) {
        return engagementService.recordView(videoId);
    }
}
