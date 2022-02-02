package com.mkkang.mbti_with_music.domain;

import org.springframework.stereotype.Repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Repository
public class UserMBTI {
    @NonNull
    private String topResult;
    
    @NonNull
    private double[] topResultDetail;
    // 크기가 정해진 array 사용하면 될듯
    @NonNull
    private double[] allResult;

    private String question_set;

    private int[] answer;

}
