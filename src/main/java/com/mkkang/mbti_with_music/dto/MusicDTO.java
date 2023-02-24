package com.mkkang.mbti_with_music.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MusicDTO {

    private String music_id;

    private String music_name;

    private String description;

    private String thumbnail;
}
