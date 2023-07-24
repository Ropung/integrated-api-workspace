# API Workspace

- [Spring Boot API Servers (as a multi-modules project)](./springboot-root)
    - [인증 서버](./springboot-root/authentication-service): 회원가입, 로그인(JWT 발급 -
      일반/소셜로그인), 프로필 CRUD
    - [작품 서버](./springboot-root/book-service)
        - 작품/회차 CRUD
- [Fast API]()
    - uvicorn main:app --reload
    - ...

---

# 통합 Swagger UI(독립형 프로세스)

<img width="1221" alt="스크린샷 2023-07-24 오후 2 32 58" src="https://github.com/Ropung/integrated-api-workspace/assets/85475762/9dfe1585-c8c4-470a-9f30-bd88ea0a7bcb">


(Purpose)

## 프로세스 실행

- 전제 조건
    - [Docker Desktop]() 설치(또는 기타 수단으로 Docker Compose 수행 가능해야 함.)
- 실행: 루트 경로에서 수행
  ```shell
    docker-compose up -d
  ```

이제 각 서버가 실행되어 있다면, API 문서를 받아 올 수 있다.

접속 정보(Local): http://localhost:2999
