# 조아라 웹소설 플랫폼 프로젝트

### 기업 과제(요구사항)

<img width="470" alt="스크린샷 2023-08-26 오후 8 22 42" src="https://github.com/Ropung/integrated-api-workspace/assets/85475762/edf07f2e-e5b3-4158-9974-6a9caad75620">



# Project Document

`Detail`: 프로젝트 진행중 컨벤션 및 API 담당목록 및 프론트 담당 URL 문서

https://worried-parrotfish-2f5.notion.site/Team-SES-3-8b84882ff38a4535b575e2015e3161f5?pvs=4

`미리보기`:

![webnobel_preview](https://github.com/Ropung/integrated-api-workspace/assets/85475762/0a0c64b4-faf5-40cf-8333-4b29bdff9c0a)

<br/>

# API Workspace 구성

`모듈 구조 및 파일구조`:

<img width="826" alt="스크린샷 2023-08-26 오후 8 11 36" src="https://github.com/Ropung/integrated-api-workspace/assets/85475762/542f3db0-a037-4094-beeb-4a00c3e1754d">

<img width="528" alt="스크린샷 2023-08-26 오후 8 15 16" src="https://github.com/Ropung/integrated-api-workspace/assets/85475762/65e16cea-80b3-42f8-9030-ca2ef50c7268">

<img width="983" alt="스크린샷 2023-08-26 오후 8 18 02" src="https://github.com/Ropung/integrated-api-workspace/assets/85475762/60bfe6ad-3419-4f45-b331-25de92a101ff">

<img width="947" alt="스크린샷 2023-08-26 오후 8 18 08" src="https://github.com/Ropung/integrated-api-workspace/assets/85475762/f24fbf42-7429-4b11-b683-df7741dc9103">


## 인프라 구성( 초기 청사진 )

<img width="812" alt="스크린샷 2023-08-26 오후 8 08 23" src="https://github.com/Ropung/integrated-api-workspace/assets/85475762/df109700-915b-410b-a951-967720a3c2a1">



## 프로세스 실행

- 전제 조건
    - [Docker Desktop]() 설치(또는 기타 수단으로 Docker Compose 수행 가능해야 함.)
- 실행: 루트 경로에서 수행
  ```shell
    docker-compose up -d
  ```
- 도커 컴포즈를 올리고 컨테이너 실행환경에서 Auth서비스와 Book서비스를 실행한다.

<br/>

# 통합 Swagger UI(독립형 프로세스)

접속 정보(Local): http://localhost:2999

각 서버가 실행되어 있다면, RestDoc을 사용하여 json파일을 swager UI API에서 읽어 아래 그림과 같이 볼 수 있다.

<img width="1221" alt="스크린샷 2023-07-24 오후 2 32 58" src="https://github.com/Ropung/integrated-api-workspace/assets/85475762/9dfe1585-c8c4-470a-9f30-bd88ea0a7bcb">


