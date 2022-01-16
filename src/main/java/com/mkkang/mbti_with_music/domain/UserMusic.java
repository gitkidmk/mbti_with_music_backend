package com.mkkang.mbti_with_music.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserMusic extends MusicInfo{
    private String mbti_name;
    private String session_id;
}
