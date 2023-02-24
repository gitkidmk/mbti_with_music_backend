package com.mkkang.mbti_with_music.controller;

import com.mkkang.mbti_with_music.dto.*;
import com.mkkang.mbti_with_music.service.MainService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import java.util.LinkedHashMap;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// TODO: SESSIONATTRIBUTES에 대해 사용 안하는 것으로 추정
@RestController
@SessionAttributes("USER")
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping(method = RequestMethod.GET, path = "/musics")
    public List<MbtiMusicDTO> getAllMusic() {
        List<MbtiMusicDTO> musicList = mainService.getAllMusic();
        return musicList;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/musics/search")
    public List<MusicDTO> searchMusic(@RequestParam(value = "music_name") String music_name) {
        return mainService.searchMusic(music_name);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/musics/like")
    public boolean musicThumbsup(@RequestBody UserMusic userMusic, HttpServletRequest request) {
        boolean thumbsupResult = mainService.musicThumbsup(userMusic);
        return thumbsupResult;
    }
    @RequestMapping(method = RequestMethod.POST, path = "/results")
    public String mbtiResult(@RequestBody UserMBTI userMBTI) {

        UserMBTI mbti_result = mainService.mbtiResult(userMBTI.getQuestion_set(), userMBTI.getAnswer());
        MusicInfo[] mbti_music = mainService.mbtiMusic(mbti_result.getTopResult());

        // Gson gson = new Gson();
        Gson gson = new GsonBuilder().create();

        String result;
        JSONObject result_json = new JSONObject();

        JSONObject mbti_result_json = new JSONObject();

        try {
            mbti_result_json.put("top_result", mbti_result.getTopResult());
            mbti_result_json.put("top_result_detail", mbti_result.getTopResultDetail());
            mbti_result_json.put("all_result", mbti_result.getAllResult());

            JSONArray array = new JSONArray();

            for (MusicInfo m : mbti_music) {
                array.put(gson.toJson(m));
            }

            result_json.put("MBTI_result", mbti_result_json);
            result_json.put("MBTI_music", array);

        } catch (JSONException e) {
            System.out.println(e);
        }

        result = result_json.toString();

        return result;
    }
}