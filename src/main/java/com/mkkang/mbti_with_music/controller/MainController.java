package com.mkkang.mbti_with_music.controller;

import com.mkkang.mbti_with_music.dto.*;
import com.mkkang.mbti_with_music.service.MainService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// TODO: SESSIONATTRIBUTES에 대해 사용 안하는 것으로 추정
@RestController
@SessionAttributes("USER")
public class MainController {

    @Autowired
    MainService mainService;

    @RequestMapping(method = RequestMethod.POST, path = "/results")
    public ResponseEntity<PostResultsDTO.Response> postResults(@RequestBody PostResultsDTO.Request request) {
        PostResultsDTO.Response response = mainService.postResults(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/musics/like")
    public ResponseEntity<PostMusicLikeDTO.Response> postLikeMusic(@RequestBody PostMusicLikeDTO.Request request) {
        PostMusicLikeDTO.Response response = mainService.postMusicLike(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // TODO: List<MusicDTO> 대신에 GetSearchMusicDTO.Request로 바꾸면 어떻게되는지
    @RequestMapping(method = RequestMethod.GET, path = "/musics/search")
    public ResponseEntity<GetMusicsSearchDTO.Response> getSearchMusic(@RequestParam(value = "music_name") String music_name) {
        GetMusicsSearchDTO.Response response = mainService.searchMusic(music_name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}