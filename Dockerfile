FROM dockermkkang/mbti_backend:0.2

COPY build/libs/mbti_with_music_backend-0.0.1-SNAPSHOT.jar /root/backendJar/
COPY src/main/resources/application.yml /root/backendJar/
COPY src/main/resources/application-KEY.yml /root/backendJar/

# git clone을 해와야 한다?


COPY start-back.sh /


# RUN chmod +x /start-back.sh

ENTRYPOINT ["sh", "/start-back.sh"]