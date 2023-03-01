package com.mkkang.mbti_with_music.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetMusicLikedExistenceDTO {
    private String music_id;
    private String session_id;
}
