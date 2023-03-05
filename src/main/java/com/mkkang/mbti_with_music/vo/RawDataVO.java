package com.mkkang.mbti_with_music.vo;

import lombok.Getter;

@Getter
public class RawDataVO {
    String kind;
    String etag;
    Id id;
    Snippet snippet;

    @Getter
    public static class Id {
        String kind;
        String videoId;
    }
    @Getter
    public static class Snippet {
        String publishedAt;
        String title;
        String description;
        Thumbnails thumbnails;
        String channelTitle;
        String liveBroadcastContent;
        String publishTime;
    }

// Thumbnails class를 생성하고자 했으나 key 가운데 default 명칭때문에 실패
    @Getter
    public static class Thumbnails {
        Thumbnail high;
    }

    @Getter
    public static class Thumbnail {
        String url;
        int width;
        int height;
    }
}