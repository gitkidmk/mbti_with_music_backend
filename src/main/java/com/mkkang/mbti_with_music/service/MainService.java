package com.mkkang.mbti_with_music.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mkkang.mbti_with_music.dto.*;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.mkkang.mbti_with_music.mapper.MainMapper;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static java.util.stream.Collectors.toMap;

@Service
public class MainService {

    @Autowired
    private MainMapper mainMapper;

    UserMBTI userMBTI = new UserMBTI();

    UserMBTIResult userMBTIResult = new UserMBTIResult();

    @Value("${key}")
    private String key;

    public String generateAuthToken() {
        String token = null;
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            secureRandom
                    .setSeed(secureRandom.generateSeed(128));
            token = new String(
                    digest.digest(
                            (secureRandom.nextLong() + "")
                                    .getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return token;
    }

    
     public String checkSession() {
         HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                 .getRequestAttributes()).getRequest();
         HttpSession httpSession = request.getSession();
         String session_id = httpSession.getId();
         return session_id;
     }

    public List<MbtiMusicDTO> getAllMusic() {
        List<MbtiMusicDTO> musicList = mainMapper.getAllMusic();
        return musicList;
    }

    public Object callYoutubeAPI(String music_name) {
        WebClient webClient = WebClient.create("https://www.googleapis.com/youtube/v3/search");
        Mono<Object> searchedMusic = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("key", key)
                        .queryParam("part", "snippet")
                        .queryParam("type", "video")
                        .queryParam("videoCategoryId", "10")
                        .queryParam("maxResults", "10")
                        .queryParam("q", music_name)
                        .build())
                .retrieve()
                .bodyToMono(Object.class);
        return searchedMusic.block();
    }

    public List<MusicDTO> refiningData(Object rawData) throws JSONException {

        List<MusicDTO> refinedData = new ArrayList<>();

        String jsonInString = new Gson().toJson(rawData);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonInString);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
            rawData = jsonObject.get("items");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        System.out.println("jsonObject = " + jsonObject);
        System.out.println("rawData = " + rawData);
        System.out.println("rawData.getClass() = " + rawData.getClass());
        System.out.println("rawData.getClass().isArray() = " + rawData.getClass().isArray());


        // TODO: convert Object to List
        Gson gson = new Gson();


        List tmpData = new JSONArray(rawData).toList();
        for (Object data : tmpData) {
            System.out.println("data = " + data);
            String jsonString = new Gson().toJson(data);
            System.out.println("jsonString = " + jsonString);
            JSONObject jsonObj = null;
            try {
                jsonObj = new JSONObject(jsonString);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            System.out.println("jsonObj = " + jsonObj);

            MusicDTO music = null;
            try {
                music = MusicDTO.builder()
                        .music_id(jsonObj.getJSONObject("id").get("videoId").toString())
                        .music_name(jsonObj.getJSONObject("snippet").get("title").toString())
                        .description(jsonObj.getJSONObject("snippet").get("description").toString())
                        .thumbnail(jsonObj.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("high").get("url").toString())
                        .build();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            System.out.println("music = " + music);

            refinedData.add(music);
        }

        return refinedData;
    }

    public List<MusicDTO> searchMusic(String music_name) {
        Object rawData = callYoutubeAPI(music_name);
        return refiningData(rawData);
    }

    public boolean musicThumbsup(UserMusic userMusic) {
        // session 있으면 패스, session없으면 생성 로직-> session_id 반환
        String session_id = this.checkSession();
        userMusic.setSession_id(session_id);

        boolean musicExist = mainMapper.isMusicExist(userMusic.getMusic_id());
        if (!musicExist) {
            mainMapper.insertNewMusic(userMusic);
        }

        boolean likeExist = mainMapper.isSessionMusicExist(userMusic);
        if(likeExist) {
            return false;
        }
        return mainMapper.musicThumbsup(userMusic);
    }

    public MusicInfo[] mbtiMusic(String MBTI_name) {
        MusicInfo[] results = mainMapper.getMbtiMusic(MBTI_name);
        for (MusicInfo r : results) {
            r.getMusic_id();
        }
        return results;
    }

    public UserMBTI mbtiResult(String question_set, int[] answer) {
        // 특정 set에 대한 문항 정보 가져오기
        // weight[], 4가지 unit에 대한 summation
        // UserMBTI userMBTI

        int[] weight = mainMapper.getWeight(question_set);
        int[] unit_total = mainMapper.getUnitTotal(question_set);
        String topResult = "";

        double E, N, T, J;
        E = 0;
        N = 0;
        T = 0;
        J = 0;

        for (int i = 0; i < answer.length; i++) {
            int val = weight[i] * answer[i];
            if (i % 4 == 0) {
                E += val;
            } else if (i % 4 == 1) {
                N += val;
            } else if (i % 4 == 2) {
                T += val;
            } else if (i % 4 == 3) {
                J += val;
            }
        }

        E = E / unit_total[0];
        N = N / unit_total[1];
        T = T / unit_total[2];
        J = J / unit_total[3];

        topResult += (E >= 0) ? "E" : "I";
        topResult += (N >= 0) ? "N" : "S";
        topResult += (T >= 0) ? "T" : "F";
        topResult += (J >= 0) ? "J" : "P";

        double[] topResultDetail = { Math.abs(E), Math.abs(N), Math.abs(T), Math.abs(J) };

        Date time = new Date();

        userMBTIResult.setMbti(topResult);
        userMBTIResult.setEI(E);
        userMBTIResult.setNS(N);
        userMBTIResult.setTF(T);
        userMBTIResult.setJP(J);
        userMBTIResult.setTime(time);

        mainMapper.insertMbtiResult(userMBTIResult);

        /*
         * ENTJ ++++
         * ENTP +++-
         * ENFJ ++-+
         * ENFP ++--
         * ESTJ +-++
         * ESTP +-+-
         * ESFJ +--+
         * ESFP +---
         * ISFP ----
         * ISFJ ---+
         * ISTP --+-
         * ISTJ --++
         * INFP -+--
         * INFJ -+-+
         * INTP -++-
         * INTJ -+++
         */

        double ENTJ = (+E + N + T + J + 4) / 8 * 100;
        double ENTP = (+E + N + T - J + 4) / 8 * 100;
        double ENFJ = (+E + N - T + J + 4) / 8 * 100;
        double ENFP = (+E + N - T - J + 4) / 8 * 100;
        double ESTJ = (+E - N + T + J + 4) / 8 * 100;
        double ESTP = (+E - N + T - J + 4) / 8 * 100;
        double ESFJ = (+E - N - T + J + 4) / 8 * 100;
        double ESFP = (+E - N - T - J + 4) / 8 * 100;
        double ISFP = (-E - N - T - J + 4) / 8 * 100;
        double ISFJ = (-E - N - T + J + 4) / 8 * 100;
        double ISTP = (-E - N + T - J + 4) / 8 * 100;
        double ISTJ = (-E - N + T + J + 4) / 8 * 100;
        double INFP = (-E + N - T - J + 4) / 8 * 100;
        double INFJ = (-E + N - T + J + 4) / 8 * 100;
        double INTP = (-E + N + T - J + 4) / 8 * 100;
        double INTJ = (-E + N + T + J + 4) / 8 * 100;

        double[] allResult = {
                ENTJ,
                ENTP,
                ENFJ,
                ENFP,
                ESTJ,
                ESTP,
                ESFJ,
                ESFP,
                ISFP,
                ISFJ,
                ISTP,
                ISTJ,
                INFP,
                INFJ,
                INTP,
                INTJ
        };

        userMBTI.setTopResult(topResult);
        userMBTI.setAllResult(allResult);
        userMBTI.setTopResultDetail(topResultDetail);

        return userMBTI;
    }

}
