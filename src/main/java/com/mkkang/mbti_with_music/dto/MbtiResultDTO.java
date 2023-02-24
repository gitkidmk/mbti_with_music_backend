package com.mkkang.mbti_with_music.dto;

import lombok.Getter;
import java.util.Date;

@Getter
public class MbtiResultDTO {

    private String mbti;

    private double EI;

    private double NS;

    private double TF;

    private double JP;

    private Date time;
}
