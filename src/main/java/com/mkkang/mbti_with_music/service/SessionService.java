package com.mkkang.mbti_with_music.service;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SessionService {

    public String generateAuthToken() {
        String token = null;
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            secureRandom
                    .setSeed(secureRandom.generateSeed(128));
            token = new String(
                    digest.digest(
                            (secureRandom.nextLong() + "")
                                    .getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return token;
    }


    public static String checkSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        HttpSession httpSession = request.getSession();
        String session_id = httpSession.getId();
        return session_id;
    }
}
