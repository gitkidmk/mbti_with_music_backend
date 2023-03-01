package com.mkkang.mbti_with_music.dto;

import com.mkkang.mbti_with_music.vo.MbtiAnalysisVO;
import com.mkkang.mbti_with_music.vo.MusicCardVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class PostResultsDTO {
    @Getter
    public class Request {
        private String question_set;
        private int[] answers;
    }

    @Getter
    @Builder
    public class Response {
        private MbtiAnalysisVO mbti;
        private MusicCardVO[] musics;
    }
}
