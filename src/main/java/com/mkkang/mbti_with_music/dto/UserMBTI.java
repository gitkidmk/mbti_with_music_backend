package com.mkkang.mbti_with_music.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserMBTI {
    private String topResult;

    private double[] topResultDetail;

    private double[] allResult;

    private String question_set;

    private int[] answer;

}
