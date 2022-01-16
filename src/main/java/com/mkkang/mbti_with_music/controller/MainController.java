package com.mkkang.mbti_with_music.controller;

import com.mkkang.mbti_with_music.domain.MusicInfo;
import com.mkkang.mbti_with_music.domain.UserMusic;
import com.mkkang.mbti_with_music.service.MainService;

import java.util.List;
import java.util.LinkedHashMap;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping(method=RequestMethod.GET, path="/")
    public List<MusicInfo> oneMember() {
        List<MusicInfo> musicResult =  mainService.allMusic();       

        return musicResult;
    }

	@RequestMapping(method=RequestMethod.GET, path="/allMusic")
    public List<MusicInfo> allMember() {
        System.out.println("get...");
        List<MusicInfo> musicResult = mainService.allMusic();

        return musicResult;
    }

	@RequestMapping(method=RequestMethod.GET, path="/music/search")
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
    
    @RequestMapping(method=RequestMethod.POST, path="/music/recommendation")
    public int musicRecommendation(@RequestBody UserMusic userMusic) {
        int musicResult = mainService.musicRecommendation(userMusic);
        return musicResult;
    }
    
    @RequestMapping(method=RequestMethod.POST, path="/music/thumbs-up")
    public int musicThumbsup(@RequestBody UserMusic userMusic) {
        int thumbsupResult = mainService.musicThumbsup(userMusic);
        return thumbsupResult;
    }

    @RequestMapping(method=RequestMethod.POST, path="/mbti-results")
    public List<MusicInfo> mbtiResult(@RequestBody UserMusic userMusic) {
        List<MusicInfo> mbti_results = mainService.mbtiMusic(userMusic);
        return mbti_results;
    }
}