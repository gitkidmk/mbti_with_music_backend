#!/bin/bash

echo backend container start...

# 프로젝트 최상단에서 진행
# ./gradlew bootJar
cd /root/backendJar
java -jar mbti_with_music_backend-0.0.1-SNAPSHOT.jar --spring.config.location=./application.yml,./application-KEY.yml

echo backend container end...

tail -f /dev/null
