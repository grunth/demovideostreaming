package com.example.demovideostreaming.dao.repository;

import com.example.demovideostreaming.dao.domain.Video;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.List;

@GraphQlRepository
public interface VideoRepository extends Neo4jRepository<Video, Long> {
    List<Video> findAllByDelistedIsFalse();
}
