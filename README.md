# 조아라 웹소설 플랫폼 프로젝트

<br>

![webnobel_preview](https://github.com/Ropung/integrated-api-workspace/assets/85475762/0a0c64b4-faf5-40cf-8333-4b29bdff9c0a)

<br>

## 소개
프로젝트는 스프링 부트, MSA, 헥사고날 구조로 이루어져 있습니다.
서비스는 회원 시스템, 도서 및 도서 추천 시스템 2개로 나누어져 있습니다.

<br>

## 기간
총 2개월
1차(04.01~28) / 2차(06.01 ~30)

<br>

## 프로젝트 설명

- **Docker Compose**로 모든 팀원이 쉽게 로컬 환경 통일
- **Flyway**를 통한 DDL 형상관리
- **도메인 모델/Persistence Entity 구분**
- 멀티 모듈 프로젝트를 통한 **공통 모듈 관리**
- **멀티 모듈 심화**: 어댑터를 모듈로 깔끔하게 구분
- **JDK 17 적용**
- **JWT 인증** 액세스 토큰은 프론트, 리프레시 토큰은 HTTP Only 쿠키와 Redis Repository에 보존
- **JPA Repository**를 통한 영속성 데이터 관리
- **Redis CRUD Repository**를 통한 휘발성 데이터 관리
- **Global Exception Handler**를 통한 예외 처리
- **유지보수 친화**적인 **ErrorCode / Exception** 확장
(복잡도를 낮추면서도 확장성을 높이기 위한 노력)

<br>

# Project Document

`Detail`: 프로젝트 진행중 컨벤션 및 API 담당목록 및 프론트 담당 URL 문서

https://worried-parrotfish-2f5.notion.site/Team-SES-3-8b84882ff38a4535b575e2015e3161f5?pvs=4

<br>

# API Workspace 구성

## 프로젝트 모음 Workspace
```
integrated-api-workspace
├── fast-api-server (project) 
├── springboot-root (project) 
├── ...
└── docker-compose.yml
    ├── postgresql 14
    ├── redis 7
    └── swagger-ui-program
```

## 멀티모듈: 마이크로 서비스 표현

```
springboot-root (project)
├── common
├── base-modules
│   ├── joara-jwt-parser                (utility module)
│   ├── joara-file-uploader             (utility module)
│   │
│   ├── joara-global-exception-handler  (AOP module)        - for each service modules(using spring web)
│   ├── joara-integrated-flyway-core    (core module)       - for each service modules(using DB)
│   └── joara-jpa-base                  (support module)    - for jpa modules(jpa adapter)
│
├── authentication-service  (service module)  - 8080
├── book-service            (service module)  - 8090
└── board-service           (service module)  - 미완(8010)
```

##멀티 모듈: 헥사고날 아키텍처 + JPA Entity | Domain Model 구분
헥사고날 아키텍처 + JPA Entity | Domain Model 구분

```
springboot-root (project)
├── ... (common, base modules, ...)
│
├── authentication-service  (service module)
│   ├── driving
│   │   ├── web-adapter                          (api controllers), (driving adapter) 일반 사용자에게 API 제공
│   │   └── internal-api-adapter                 (api controllers), (driving adapter) 다른 마이크로서비스에 API 제공
│   ├── driven
│   │   ├── rdb-adapter                          (jpa repository, domain repository impl), (driven adapter)
│   │   └── redis-adapter                        (redis repository), (driven adapter)
│   │
│   ├── application (ports)
│   │   ├── domain
│   │   ├── read-models
│   │   └── src (이하 패키지들)
│   │       ├── config
│   │       ├── exception
│   │       ├── properties
│   │       ├── domain repositories
│   │       ├── service                          (service impl)
│   │       ├── usecase                          (service interface) - 세분화된 서비스 인터페이스. web-adapter가 이를 구현.
│   │       └── utils
│   │
│   ├── AuthenticationServiceApplication.java
│   └── application.yml
│
└── book-service            (service module)
    ├── driven
    │   ├── ...
    │   │
    │   └── joara-member-client     (client module) 다른 마이크로서비스의 API를 이용(주로 내부 API)
    ├── application
    │   ├── ...
    │   └── clients/MemberQueryPort.java    (client port file) (interface) 다른 도메인의 정보를 조회하는 인터페이스 예시
    │
    ├── BookServiceApplication.java
    └── application.yml
```



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

각 서버가 실행되어 있다면, RestDoc을 사용하여 json파일을 swager UI API 서버(port: 2999)에서 아래 그림과 같이 읽은 json을 볼 수 있다.

<img width="1221" alt="스크린샷 2023-07-24 오후 2 32 58" src="https://github.com/Ropung/integrated-api-workspace/assets/85475762/9dfe1585-c8c4-470a-9f30-bd88ea0a7bcb">


