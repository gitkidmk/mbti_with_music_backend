package com.mkkang.mbti_with_music.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public class PostResultsServiceDTO {

    private String mbti;

    private double EI;

    private double NS;

    private double TF;

    private double JP;

    private Date time;
}
