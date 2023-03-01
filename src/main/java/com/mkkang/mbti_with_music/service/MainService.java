package com.mkkang.mbti_with_music.service;

import com.mkkang.mbti_with_music.dto.*;
import com.mkkang.mbti_with_music.vo.MbtiAnalysisVO;
import com.mkkang.mbti_with_music.vo.MusicCardVO;
import com.mkkang.mbti_with_music.vo.RawDataVO;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.mkkang.mbti_with_music.mapper.MainMapper;

import java.util.*;

import static java.util.stream.Collectors.toMap;

@Service
public class MainService {

    @Autowired
    private MainMapper mainMapper;

    @Value("${key}")
    private String key;

    public GetMusicsSearchDTO.Response searchMusic(String music_name) {
        YouTubeDataDTO rawData = callYoutubeAPI(music_name);
        GetMusicsSearchDTO.Response response = new GetMusicsSearchDTO().new Response(refiningData(rawData));
        return response;
    }

    private YouTubeDataDTO callYoutubeAPI(String music_name) {
        WebClient webClient = WebClient.create("https://www.googleapis.com/youtube/v3/search");
        Mono<YouTubeDataDTO> searchedMusic = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("key", key)
                        .queryParam("part", "snippet")
                        .queryParam("type", "video")
                        .queryParam("videoCategoryId", "10")
                        .queryParam("maxResults", "10")
                        .queryParam("q", music_name)
                        .build())
                .retrieve()
                .bodyToMono(YouTubeDataDTO.class);
        return searchedMusic.block();
    }

    private List<MusicCardVO> refiningData(YouTubeDataDTO rawDataList) throws JSONException {
        List<MusicCardVO> refinedData = new ArrayList<>();

        List<RawDataVO> dataList =  rawDataList.getItems();

        for (RawDataVO data : dataList) {
            MusicCardVO music = MusicCardVO.builder()
                    .music_id(data.getId().getVideoId())
                    .music_name(data.getSnippet().getTitle())
                    .description(data.getSnippet().getDescription())
                    .thumbnail(data.getSnippet().getThumbnails().getHigh().getUrl())
                    .build();
            refinedData.add(music);
        }
        return refinedData;
    }


    public PostMusicLikeDTO.Response postMusicLike(PostMusicLikeDTO.Request request) {
        // session 있으면 패스, session없으면 생성 로직-> session_id 반환
        String session_id = SessionService.checkSession();
        PostMusicLikeServiceDTO postMusicLikeServiceDTO = PostMusicLikeServiceDTO.builder()
                .music_id(request.getMusic_id())
                .mbti_name(request.getMbti_name())
                .session_id(session_id)
                .build();

        boolean musicExist = mainMapper.getMusicExistence(request.getMusic_id());
        if (!musicExist) {
            PostMusicDTO postMusicDTO = PostMusicDTO.builder()
                    .music_id(request.getMusic_id())
                    .music_name(request.getMusic_name())
                    .description(request.getDescription())
                    .thumbnail(request.getThumbnail())
                    .build();
            mainMapper.postMusic(postMusicDTO);
        }

        GetMusicLikedExistenceServiceDTO getMusicLikedExistenceServiceDTO = new GetMusicLikedExistenceServiceDTO(request.getMusic_id(), session_id);
        boolean likeExist = mainMapper.getMusicLikedExistence(getMusicLikedExistenceServiceDTO);
        boolean accepted = false;

        if(!likeExist) {
            accepted = mainMapper.postMusicLike(postMusicLikeServiceDTO);
        }
        PostMusicLikeDTO.Response response = new PostMusicLikeDTO.Response(accepted);

        return response;
    }

    public MusicCardVO[] getMbtiMusics(String mbti_name) {
        MusicCardVO[] results = mainMapper.getMbtiMusic(mbti_name);
        return results;
    }

    public MbtiAnalysisVO postMbtiResult(PostResultsDTO.Request request) {
        String question_set = request.getQuestion_set();
        int[] answers = request.getAnswers();

        int[] weight = mainMapper.getWeight(question_set);
        int[] unit_total = mainMapper.getUnitTotal(question_set);
        String topType = "";

        double E, N, T, J;
        E = 0;
        N = 0;
        T = 0;
        J = 0;

        for (int i = 0; i < answers.length; i++) {
            int val = weight[i] * answers[i];
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

        topType += (E >= 0) ? "E" : "I";
        topType += (N >= 0) ? "N" : "S";
        topType += (T >= 0) ? "T" : "F";
        topType += (J >= 0) ? "J" : "P";

        double[] topTypeDetail = { Math.abs(E), Math.abs(N), Math.abs(T), Math.abs(J) };

        Date time = new Date();

        PostResultsServiceDTO postResultsServiceDTO = PostResultsServiceDTO.builder()
                .mbti(topType)
                .EI(E)
                .NS(N)
                .TF(T)
                .JP(J)
                .time(time)
                .build();

        mainMapper.postResult(postResultsServiceDTO);

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

        MbtiAnalysisVO mbtiAnalysisVO = MbtiAnalysisVO.builder()
                .topType(topType)
                .topTypeDetail(topTypeDetail)
                .entireTypeRough(allResult)
                .build();

        return mbtiAnalysisVO;
    }

    public PostResultsDTO.Response postResults(PostResultsDTO.Request request) {
        MbtiAnalysisVO mbti = postMbtiResult(request);
        MusicCardVO[] musics = getMbtiMusics(mbti.getTopType());
        PostResultsDTO.Response response = PostResultsDTO.Response.builder()
                .mbti(mbti)
                .musics(musics)
                .build();
        return response;
    }

}
