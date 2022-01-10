package com.mkkang.mbti_with_music.mapper;
 
import org.apache.ibatis.annotations.Select;
 
public interface sampleMapper {
 
    @Select(" select * from music")
    String selectSampleData();
    
}
