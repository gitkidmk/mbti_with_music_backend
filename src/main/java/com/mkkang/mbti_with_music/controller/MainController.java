package com.mkkang.mbti_with_music.controller;

import java.util.List;

import com.mkkang.mbti_with_music.domain.MusicInfo;
import com.mkkang.mbti_with_music.service.MainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping(method=RequestMethod.GET, path="/")
    public List<MusicInfo> oneMember() {
        System.out.println("get111...");
        List<MusicInfo> musicResult =  mainService.allMusic();       

        return musicResult;
    }

	@RequestMapping(method=RequestMethod.GET, path="/allMusic")
    public List<MusicInfo> allMember() {
        System.out.println("get...");
        List<MusicInfo> musicResult =  mainService.allMusic();       

        return musicResult;
    }
}