package com.example.demovideostreaming.dao.domain;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

@Node
@Data
public class Engagement {
    @Id
    @GeneratedValue
    private Long videoId;

    @Property
    private Integer impressions = 0;

    @Property
    private Integer views = 0;

    @Relationship(type = "HAS_VIDEO", direction = Relationship.Direction.INCOMING)
    private Video video;
}
