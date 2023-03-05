package com.mkkang.mbti_with_music.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostMusicDTO {
    private String music_id;
    private String music_name;
    private String description;
    private String thumbnail;
}
