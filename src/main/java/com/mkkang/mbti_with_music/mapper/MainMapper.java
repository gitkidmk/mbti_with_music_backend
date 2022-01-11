package com.mkkang.mbti_with_music.mapper;
 
import java.util.List;

import com.mkkang.mbti_with_music.domain.MusicInfo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MainMapper {
    @Select("select * from music")
    List<MusicInfo> allMusic();
}