package com.mkkang.mbti_with_music.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;

import com.mkkang.mbti_with_music.domain.MusicInfo;
import com.mkkang.mbti_with_music.domain.UserMusic;
import com.mkkang.mbti_with_music.mapper.MainMapper;



import java.util.List;

@Service
public class MainService {

    @Autowired
    private MainMapper mainMapper;

    public List<MusicInfo> allMusic() {
        List<MusicInfo> musicList = mainMapper.allMusic();
        return musicList;
    }
    
    public Object searchMusic(String music_name) {
        WebClient webClient = WebClient.create("https://www.googleapis.com/youtube/v3/search");
        Mono<Object> searchedMusic = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("key", "AIzaSyAgsa32-SWrq_cLaBsAUe_1IeFa3gqVDTs")
                        .queryParam("part", "snippet")
                        .queryParam("type", "video")
                        .queryParam("videoCategoryId", "10")
                        .queryParam("q", music_name)
                        .build())
                .retrieve()
                .bodyToMono(Object.class);
        //TODO: 결과값 정제해서 보내기
        return searchedMusic.block();
    }
    
    public int musicRecommendation(UserMusic userMusic) {      
        return mainMapper.musicRecommendation(userMusic);
    }

    public int musicThumbsup(UserMusic userMusic) {
        return mainMapper.musicThumbsup(userMusic);
    }
    
    public List<MusicInfo> mbtiMusic(UserMusic userMusic) {
        List<MusicInfo> results = mainMapper.getMbtiMusic(userMusic.getMbti_name());
        return results;
    }
    
}
