package com.example.demovideostreaming;

import com.example.demovideostreaming.dao.domain.Engagement;
import com.example.demovideostreaming.dao.domain.Video;
import com.example.demovideostreaming.dao.domain.VideoInput;
import com.example.demovideostreaming.dao.repository.EngagementRepository;
import com.example.demovideostreaming.dao.repository.VideoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class VideoStreamingIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private EngagementRepository engagementRepository;

    @Test
    void testPublishVideoAndRecordView() throws Exception {
        VideoInput videoInput = new VideoInput();
        videoInput.setTitle("Test Video");
        videoInput.setDirector("Test Director");

        mockMvc.perform(post("/graphql")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"query\":\"mutation { publishVideo(video: " + objectMapper.writeValueAsString(videoInput) + ") { id } }\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.publishVideo.id").exists());

        Video video = videoRepository.findAll().get(0);

        mockMvc.perform(post("/graphql")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"query\":\"mutation { recordView(videoId: " + video.getId() + ") }\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.recordView").value(true));

        Engagement engagement = engagementRepository.findByVideoId(video.getId()).orElse(null);
        assertNotNull(engagement);
        assertEquals(1, engagement.getViews().intValue());
    }
}
