package com.mkkang.mbti_with_music.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.mkkang.mbti_with_music.domain.MusicInfo;
import com.mkkang.mbti_with_music.mapper.MainMapper;



import java.util.List;

@Service
public class MainService {

    @Autowired
    private MainMapper mainMapper;

    public List<MusicInfo> allMusic(){
        List<MusicInfo> musicList = mainMapper.allMusic();
        return musicList;
    }
    
}
