package com.mkkang.mbti_with_music.mapper;

import java.util.List;

import com.mkkang.mbti_with_music.dto.*;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainMapper {
    // TODO: 파일 못나누나?
    public List<MbtiMusicDTO> getAllMusic();

    public int musicRecommendation(UserMusic userMusic);

    //TODO: boolean으로 못받나?
    public boolean isMusicExist(String music_id);

    public int insertNewMusic(UserMusic userMusic);

    public int insertMbtiResult(UserMBTIResult userMBTIResult);

    public boolean isSessionMusicExist(UserMusic userMusic);

    public boolean musicThumbsup(UserMusic userMusic);

    public MusicInfo[] getMbtiMusic(String mbti_name);

    public int[] getWeight(String question_set);

    public int[] getUnitTotal(String question_set);
}