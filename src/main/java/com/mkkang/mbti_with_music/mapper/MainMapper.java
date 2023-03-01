package com.mkkang.mbti_with_music.mapper;

import com.mkkang.mbti_with_music.dto.*;

import com.mkkang.mbti_with_music.vo.MusicCardVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainMapper {
    // TODO: 파일 못나누나?
    //TODO: boolean으로 못받나?
    public boolean getMusicExistence(String music_id);

    public int postMusic(PostMusicDTO postMusicDTO);

    public int postResult(PostResultsServiceDTO postResultsServiceDTO);

    public boolean getMusicLikedExistence(GetMusicLikedExistenceServiceDTO getMusicLikedExistenceServiceDTO);

    public boolean postMusicLike(PostMusicLikeServiceDTO postMusicLikeServiceDTO);

    public MusicCardVO[] getMbtiMusic(String mbti_name);

    public int[] getWeight(String question_set);

    public int[] getUnitTotal(String question_set);
}