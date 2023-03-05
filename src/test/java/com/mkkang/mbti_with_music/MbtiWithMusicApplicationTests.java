package com.mkkang.mbti_with_music;

import com.mkkang.mbti_with_music.service.MainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MbtiWithMusicApplicationTests {

	@Autowired
	MainService mainService;

	@Test
	void jsonParsingTest() {
		mainService.searchMusic("맘마미아");
	}

}
