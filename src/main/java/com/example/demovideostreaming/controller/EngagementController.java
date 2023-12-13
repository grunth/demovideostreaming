package com.example.demovideostreaming.controller;

import com.example.demovideostreaming.dao.domain.Engagement;
import com.example.demovideostreaming.dao.repository.EngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EngagementController {
    private final EngagementRepository repository;

    @Autowired
    public EngagementController(EngagementRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public List<Engagement> allEngagements() {
        return repository.findAll();
    }

}
