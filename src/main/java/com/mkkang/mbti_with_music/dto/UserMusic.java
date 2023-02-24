package com.mkkang.mbti_with_music.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserMusic extends MusicInfo{
    private String mbti_name;
    private String session_id;
}
