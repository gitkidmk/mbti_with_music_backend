package com.mkkang.mbti_with_music.controller;

import com.mkkang.mbti_with_music.domain.MusicInfo;
import com.mkkang.mbti_with_music.domain.UserMusic;
import com.mkkang.mbti_with_music.domain.UserMBTI;
import com.mkkang.mbti_with_music.service.MainService;

import java.util.List;
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

@RestController
@SessionAttributes("USER")
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public List<MusicInfo> oneMember() {
        List<MusicInfo> musicResult = mainService.allMusic();

        return musicResult;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/allMusic")
    public List<MusicInfo> allMember() {
        List<MusicInfo> musicResult = mainService.allMusic();

        return musicResult;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/music/search")
    public Object searchMusic(@RequestParam(value = "music_name") String music_name) {
        System.out.println("searching music...");
        Object music_result = mainService.searchMusic(music_name);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String result = objectMapper.writeValueAsString(music_result);
            JSONParser jsonResult = new JSONParser(result);
            LinkedHashMap<String, Object> json = jsonResult.object();
            music_result = json.get("items");
        } catch (JsonProcessingException e) {
            System.out.println(e);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return music_result;
    }

    // 없어도 되는 api
    @RequestMapping(method = RequestMethod.POST, path = "/music/recommendation")
    public int musicRecommendation(@RequestBody UserMusic userMusic) {
        int musicResult = mainService.musicRecommendation(userMusic);
        return musicResult;
    }

    // music table에 pass 여부 column필요
//    @ModelAttribute("USER")
    @RequestMapping(method = RequestMethod.POST, path = "/music/thumbs-up")
    public int musicThumbsup(@RequestBody UserMusic userMusic) {
        System.out.println("thumbs-up START");
        int thumbsupResult = mainService.musicThumbsup(userMusic);
        System.out.println("thumbs-up END");
        return thumbsupResult;
    }
    @RequestMapping(method = RequestMethod.POST, path = "/mbti-results")
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