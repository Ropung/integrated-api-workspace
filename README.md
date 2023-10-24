# 조아라 웹소설 플랫폼 프로젝트

![image](https://github.com/Ropung/integrated-api-workspace/assets/85475762/1bd635ab-2dc0-44ef-b8a8-f9f7fb6d405c)

## 소개
프로젝트는 스프링 부트, MSA, 헥사고날 구조로 이루어져 있습니다.
서비스는 회원 시스템, 도서 및 도서 추천 시스템 2개로 나누어져 있습니다.

## 기간
총 2개월
1차(04.01~28) / 2차(06.01 ~30)

## 프로젝트 설명
- 헥사고날 아키텍처 적용
    
    **비즈니스 로직이 중심이 되어 설계되므로, 도메인 중심(코어)으로 설계 가능합니다.**
    
    코어영역과 외부 인터페이스(어댑터)를 분리하여 요구사항이 변경되더라도 손쉽게 교체가능 합니다. 독립적으로 어댑터를 모듈별로 나누어 변경과 확장에 용이하게 만들었습니다.
    
- 멀티 모듈 적용
    
    공통 모듈과 서비스용 Base모듈로 분리하여 명확한 책임 분배가 가능하며 단 방향으로(코어쪽으로) 설계하여 순환참조를 방지합니다.
    
- 마이바티스 → JPA 변경
    
    SQL을 작성하는 비용을 줄여 작업 속도를 향상시켰습니다.
    
- Domain Model과 Persistence Entity의 구분
    
    **데이터 아키텍처와 애플리케이션 아키텍처 간 간섭을 최소화하도록 설계하였습니다.**
    
    이를 위해 도메인 모델과 Persistence Entity를 구분하고, 중계 계층을 관리했습니다. 데이터베이스의 변경 사항이 중개 계층에서 완화되어 애플리케이션 아키텍처에서 모델이 안전하게 취급하도록 관리합니다.
    
- MSA 적용
    
    회원 서비스, 작품 서비스 분리하여 서비스를 각각 독립적 서버로 실행되어 각각의 서비스는 서로 영향을 주지 않습니다.
    
- Flyway(DataBase Migration Tool) 적용
    
    DDL 책임을 가져가고 Spring에서 관리하여 작업자의 편의성 증가 & 버전관리를 파악하기 쉽게 개선
    
- Domain 을 기준으로 계층분리
    - 추후 DDD 적용을 위해 도메인을 기준으로 수직 수평 계층으로 나누어 관리 및 유지보수 편의성 개선
- 휴먼에러 개선
    
    공유 상수는 리터럴 사용을 삼가고, 열거형과 상수관리 클래스 등을 통해 관리하였습니다.
    협업 시 구성원의 실수를 최소화하고, 디버깅에 용이하며, 열거형은 멤버변수와 메서드를 통한 일관된 작업을 지원할 수 있습니다.
    
- JDK 17적용
    
    record 문법을 사용하여 기존 dto 클레스에 보일러 플레이트를 생략 가능하게 만들어 코드양을 줄여 가독성과 유지보수성을 증가시켰습니다. 
    
- 인증 인가(JWT) 적용
    
    로그인 시 엑세스 토큰(JWT)을 발행하며 토큰 만료 시 리프레시 토큰도 재발행합니다. 
    
- 조회 데이터량 감소
    
    클라이언트에게 응답을 할때 JPA Query Projection으로 DB단에서 프론트에서 필요한 데이터만 응답하여 트래픽을 감소 시켰습니다.
    
- 추천작품 기능
    
    장르와 제목을 기준으로 가중치를 주어 유사한(코사인 유사도 기준) 높은 유사도의 작품들을 추천하여 보여줄 수 있습니다
    
- Simple CQRS 적용


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

각 서버가 실행되어 있다면, RestDoc을 사용하여 json파일을 swager UI API 서버(port: 2999)에서 아래 그림과 같이 읽은 json을 볼 수 있다.

<img width="1221" alt="스크린샷 2023-07-24 오후 2 32 58" src="https://github.com/Ropung/integrated-api-workspace/assets/85475762/9dfe1585-c8c4-470a-9f30-bd88ea0a7bcb">


