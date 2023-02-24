package com.mkkang.mbti_with_music.dto;

import lombok.Getter;

@Getter
public class MbtiAnalysisDTO {

    private mbti mbti;

    private MusicDTO[] musics;

    static class mbti {

        private String topType;

        private double[] topTypeDetail;

        private double[] entireTypeRough;

    }
}
