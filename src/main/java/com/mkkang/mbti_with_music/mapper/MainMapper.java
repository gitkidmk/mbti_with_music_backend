package com.mkkang.mbti_with_music.mapper;
 
import java.util.List;

import com.mkkang.mbti_with_music.domain.MusicInfo;
import com.mkkang.mbti_with_music.domain.UserMusic;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainMapper {
    public List<MusicInfo> allMusic();

    public int musicRecommendation(UserMusic userMusic);

    public int musicThumbsup(UserMusic userMusic);

    public MusicInfo[] getMbtiMusic(String mbti_name);

    public int[] getWeight(String question_set);

    public int[] getUnitTotal(String question_set);
}