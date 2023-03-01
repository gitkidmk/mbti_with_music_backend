package com.mkkang.mbti_with_music.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostMusicLikeServiceDTO {
    private String music_id;
    private String mbti_name;
    private String session_id;
}
