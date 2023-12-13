package com.example.demovideostreaming;

import com.example.demovideostreaming.dao.domain.Engagement;
import com.example.demovideostreaming.dao.domain.Video;
import com.example.demovideostreaming.dao.repository.EngagementRepository;
import com.example.demovideostreaming.dao.repository.VideoRepository;
import com.example.demovideostreaming.service.EngagementService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static graphql.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class EngagementServiceTest {

    @Mock
    private EngagementRepository engagementRepository;

    @Mock
    private VideoRepository videoRepository;

    @InjectMocks
    private EngagementService engagementService;

    @Test
    void testRecordImpression() {
        Long videoId = 1L;
        Video video = new Video();
        video.setId(videoId);

        when(videoRepository.findById(videoId)).thenReturn(Optional.of(video));
        when(engagementRepository.findByVideoId(videoId)).thenReturn(Optional.empty());
        when(engagementRepository.save(any(Engagement.class))).thenReturn(new Engagement());

        boolean result = engagementService.recordImpression(videoId);

        assertTrue(result);

        verify(videoRepository, times(1)).findById(videoId);
        verify(engagementRepository, times(1)).findByVideoId(videoId);
        verify(engagementRepository, times(1)).save(any(Engagement.class));
    }
}
