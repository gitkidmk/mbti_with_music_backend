FROM openjdk:19-jdk-alpine3.15

COPY build/libs/mbti_with_music_backend-0.0.1-SNAPSHOT.jar /usr/share
COPY src/main/resources/application.yml /usr/share
COPY src/main/resources/application-KEY.yml /usr/share

WORKDIR /usr/share

CMD ["java", "-jar", "mbti_with_music_backend-0.0.1-SNAPSHOT.jar", "--spring.config.location=./application.yml,./application-KEY.yml"]