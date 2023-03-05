package com.mkkang.mbti_with_music.dto;

import com.mkkang.mbti_with_music.vo.MusicCardVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class GetMusicsSearchDTO {

    @Getter
    @AllArgsConstructor
    public class Response {
        private List<MusicCardVO> musics;
    }
}
