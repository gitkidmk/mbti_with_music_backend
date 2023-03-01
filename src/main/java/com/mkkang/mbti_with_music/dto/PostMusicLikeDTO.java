package com.mkkang.mbti_with_music.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PostMusicLikeDTO {
    @Getter
    @Builder
    public static class Request {
        private String music_id;
        private String music_name;
        private String description;
        private String thumbnail;
        private String mbti_name;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private boolean accepted;
    }

}
