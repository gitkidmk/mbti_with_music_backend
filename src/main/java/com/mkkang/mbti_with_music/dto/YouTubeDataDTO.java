package com.mkkang.mbti_with_music.dto;

import com.mkkang.mbti_with_music.vo.RawDataVO;
import lombok.Getter;

import java.util.List;

@Getter
public class YouTubeDataDTO {
    List<RawDataVO> items;
}
