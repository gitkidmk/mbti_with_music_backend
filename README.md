# MBTI_with_music 프로젝트란?
> 자신의 MBTI를 검사하는 사이트는 매우 다양합니다. <br/>
> 하지만 **자신의 MBTI에 맞는 음악을 공유**하는 사이트는 사용해보셨나요? <br/>
> 또한 MBTI **각 항목 특성에 따른 지표**를 통해 같은 ISFJ도 다른 ISFJ로 분석할 수 있습니다.

[mkkang.me](https://mkkang.me)

<p align="center">
<img width="300px" src="./mbti-with-music-demo.gif"/>
</p>

---
MBTI_with_music 프로젝트는 FE(프론트엔드), BE(백엔드), DB로 총 세 가지 앱으로 구성됩니다. <br/>
FE와 BE는 build/master 브랜치에 배포에 사용 가능한 Dockerfile과 docker-compose 리소스가 있습니다. <br/>
또한 GKE에서는 Google Kubernetes Engine에서 사용된 Deployment, Service 리소스를 확인 할 수 있습니다. <br/> <br/>
연관된 프로젝트 링크는 아래와 같습니다. 😎 <br/>
FE: [https://github.com/gitkidmk/mbti_with_music_frontend](https://github.com/gitkidmk/mbti_with_music_frontend) <br/>
BE: [https://github.com/gitkidmk/mbti_with_music_backend](https://github.com/gitkidmk/mbti_with_music_backend) <br/>
DB: [https://github.com/gitkidmk/mbti_with_music_db](https://github.com/gitkidmk/mbti_with_music_db) <br/>
GKE: [https://github.com/gitkidmk/gke-test](https://github.com/gitkidmk/gke-test)
<br/>

_참고: docker-compose를 통해 MBTI_with_music 프로젝트를 구동하고싶다면 폴더구조를 아래와 같이 구성하세요._
```text

├── mbti_with_music_frontend
│   ├── Dockerfile
│   └── docker-compose
│
├── mbti_with_music_backend
│   └── Dockerfile
│
└── mbti_with_music_db
    └── Dockerfile
```

# mbti_with_music_backend
해당 프로젝트는 MBTI_with_music 프로젝트의 BE(백엔드) 프로젝트입니다. <br/>


## 스펙
- SpringBoot
- MyBatis
- Youtube API

## 주의사항
음악 검색에 Youtube API가 사용됩니다. <br/>
해당 API key 값은 따로 발급받아 `src > main > resources` 폴더 하위에 `application-KEY.yml`파일을 생성해, **key:value** 형태로 작성해주세요.
