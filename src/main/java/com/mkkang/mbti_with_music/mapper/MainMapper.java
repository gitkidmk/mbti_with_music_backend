package com.mkkang.mbti_with_music.mapper;
 
import java.util.List;

import com.mkkang.mbti_with_music.domain.MusicInfo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainMapper {
    List<MusicInfo> allMusic();
}