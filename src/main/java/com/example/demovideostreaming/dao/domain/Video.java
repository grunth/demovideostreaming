package com.example.demovideostreaming.dao.domain;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

@Node
@Data
public class Video {

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String title;

    @Property
    private String synopsis;

    @Property
    private String director;

    @Property
    private String cast;

    @Property
    private Integer yearOfRelease;

    @Property
    private String genre;

    @Property
    private Integer runningTime;

    @Property
    private Boolean delisted = false;

    @Relationship(value = "HAS_ENGAGEMENT", direction = Relationship.Direction.OUTGOING)
    private Engagement engagement;
}
