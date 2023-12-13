package com.example.demovideostreaming.dao.repository;

import com.example.demovideostreaming.dao.domain.Engagement;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface EngagementRepository extends Neo4jRepository<Engagement, Long> {
}
