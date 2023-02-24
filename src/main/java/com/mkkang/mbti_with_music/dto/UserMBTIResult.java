package com.mkkang.mbti_with_music.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserMBTIResult {
    private String mbti;

    private double EI;

    private double NS;

    private double TF;

    private double JP;

    private Date time;
}
