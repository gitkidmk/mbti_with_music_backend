package com.mkkang.mbti_with_music.vo;

import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@Builder
public class MbtiAnalysisVO {
    private String topType;

    private double[] topTypeDetail;

    private double[] entireTypeRough;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MbtiAnalysisVO that = (MbtiAnalysisVO) o;
        return Objects.equals(topType, that.topType) && Arrays.equals(topTypeDetail, that.topTypeDetail) && Arrays.equals(entireTypeRough, that.entireTypeRough);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(topType);
        result = 31 * result + Arrays.hashCode(topTypeDetail);
        result = 31 * result + Arrays.hashCode(entireTypeRough);
        return result;
    }
}
