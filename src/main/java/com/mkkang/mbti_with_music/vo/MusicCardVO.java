package com.mkkang.mbti_with_music.vo;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class MusicCardVO {
    private String music_id;
    private String music_name;
    private String description;
    private String thumbnail;
    private String mbti_name;
    private int great_count;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicCardVO that = (MusicCardVO) o;
        return great_count == that.great_count && Objects.equals(music_id, that.music_id) && Objects.equals(music_name, that.music_name) && Objects.equals(description, that.description) && Objects.equals(thumbnail, that.thumbnail) && Objects.equals(mbti_name, that.mbti_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(music_id, music_name, description, thumbnail, mbti_name, great_count);
    }
}
