# 조아라 웹소설 플랫폼 프로젝트

<br>

![webnobel_preview](https://github.com/Ropung/integrated-api-workspace/assets/85475762/0a0c64b4-faf5-40cf-8333-4b29bdff9c0a)

<br>

## 팀 구성
1차 - 노기훈, 장재호
<br>
2차 - 노기훈, 이현종, 오승재, 박승인


## 소개
프로젝트는 스프링 부트, MSA, 헥사고날 구조로 이루어져 있습니다.
서비스는 회원 시스템, 도서 및 도서 추천 시스템 2개로 나누어져 있습니다.

<br>

## 문서(Notion)
https://worried-parrotfish-2f5.notion.site/Team-SES-3-8b84882ff38a4535b575e2015e3161f5?pvs=4

<br>

## 기간
1차(04.01~28) 
<br>
2차(06.01 ~30)

<br>

## 🛠사용 기술

![image](https://github.com/Ropung/integrated-api-workspace/assets/85475762/37a4b51d-b507-4b28-869b-d0e914b750a9)


<br>

## 프로젝트 구조

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

## 프로젝트 API 목록

DOC: https://worried-parrotfish-2f5.notion.site/API-2991db75f39b4435aec0a35f73a7d58a?pvs=4

![image](https://github.com/Ropung/integrated-api-workspace/assets/85475762/7eed775e-f006-4030-a984-4a8d958a7802)

<br>



<br>

# API Workspace 구성

<br>

### 프로젝트 모음 Workspace

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

<br>

### 멀티모듈: 마이크로 서비스 표현

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

<br>

### 멀티 모듈: 헥사고날 아키텍처 + JPA Entity | Domain Model 구분
- 헥사고날 아키텍처 + JPA Entity | Domain Model 구분

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

<br>

## DB 구성

![image](https://github.com/Ropung/integrated-api-workspace/assets/85475762/c8272c8b-1b18-4b4c-878d-c82b0d0f5391)

<br>


## 인프라 구성( 초기 청사진 )

<img width="812" alt="스크린샷 2023-08-26 오후 8 08 23" src="https://github.com/Ropung/integrated-api-workspace/assets/85475762/df109700-915b-410b-a951-967720a3c2a1">

- Redis 구성 미완
- Api Gateway 미완
- S3 구성 미완

<br/>


## 프로세스 실행방법

- 전제 조건
    - [Docker Desktop]() 설치(또는 기타 수단으로 Docker Compose 수행 가능해야 함.)
    - 루트 경로에서 수행
  ```shell
    docker-compose up -d
  ```
    - 도커 컴포즈를 올리고 컨테이너 실행환경에서 Auth서비스와 Book서비스를 실행한다.
    - 추천서비스의 경우 FastApi 환경이 필요하지만 용량이슈로 절감

<br/>

## 통합 Swagger UI(독립형 프로세스)

접속 정보(Local): http://localhost:2999

각 서버가 실행되어 있다면, RestDoc을 사용하여 json파일을 swager UI API 서버(port: 2999)에서 아래 그림과 같이 읽은 json을 볼 수 있다.

<img width="1221" alt="스크린샷 2023-07-24 오후 2 32 58" src="https://github.com/Ropung/integrated-api-workspace/assets/85475762/9dfe1585-c8c4-470a-9f30-bd88ea0a7bcb">

<br/>

`Postman Json 파일`

<details>
<summary> Postman.json </summary>

```json
{
	"info": {
		"_postman_id": "45231524-c085-47a4-8514-b6a33c79a307",
		"name": "Joara_basic API",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "19882406"
	},
	"item": [
		{
			"name": "001. Local Member: 8080",
			"item": [
				{
					"name": "(Auth) Login",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "",
									"type": "string"
								}
							]
						},
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"email\": \"1@naver.com\",\n    \"password\": \"@asdf1234\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/login",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"login"
							]
						}
					},
					"response": []
				},
				{
					"name": "(Auth) Sign Up",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"email\": \"1@naver.com\",\n    \"password\": \"@asdf1234\",\n    \"nickname\": \"노기훈\",\n    \"gender\":\"M\",\n    \"name\": \"노기훈\",\n    \"phone\": \"01095935676\",\n    \"birth\": \"1994-08-27\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/sign-up",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"sign-up"
							]
						}
					},
					"response": []
				},
				{
					"name": "My Info",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64W46riw7ZuIIiwicm9sZXMiOlsiVVNFUiJdLCJpYXQiOjE2ODc5NTUyMTMsImV4cCI6MTY4Nzk1NzAxM30.RQ0v6Hy9TuNRzoLcQbp8mNDLKo2FXmqos1fUuiakltg",
									"type": "string"
								}
							]
						},
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/members/me",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"members",
								"me"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "002. Local Book : 8090",
			"item": [
				{
					"name": "Book",
					"item": [
						{
							"name": "Query",
							"item": [
								{
									"name": "My Books",
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64W46riw7ZuIIiwicm9sZXMiOlsiVVNFUiJdLCJpYXQiOjE2ODc3Njc0NjcsImV4cCI6MTY4Nzc2OTI2N30.fElkiavtqV_9WEdcfDJs8s94dSggxIo-OXPD7pL0_WU",
													"type": "string"
												}
											]
										},
										"method": "GET",
										"header": [],
										"url": {
											"raw": "http://localhost:8090/books/me?page=0",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"me"
											],
											"query": [
												{
													"key": "page",
													"value": "0"
												}
											]
										}
									},
									"response": []
								},
								{
									"name": "One Book",
									"request": {
										"method": "GET",
										"header": [],
										"url": {
											"raw": "http://localhost:8090/books/1",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"1"
											]
										}
									},
									"response": []
								},
								{
									"name": "Genre Books",
									"request": {
										"method": "GET",
										"header": [],
										"url": {
											"raw": "http://localhost:8090/books/genre/1",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"genre",
												"1"
											]
										}
									},
									"response": []
								},
								{
									"name": "Recommend Books",
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64W46riw7ZuIIiwicm9sZXMiOlsiVVNFUiJdLCJpYXQiOjE2ODc5NTM5NDksImV4cCI6MTY4Nzk1NTc0OX0.a2sdmgnl4PHIoe-WywKCtKVuYWn7UKs4kXEGE057R4s",
													"type": "string"
												}
											]
										},
										"method": "GET",
										"header": [],
										"url": {
											"raw": "http://localhost:8090/books/1/recommend",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"1",
												"recommend"
											]
										}
									},
									"response": []
								}
							]
						},
						{
							"name": "Command",
							"item": [
								{
									"name": "Book Create",
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64W46riw7ZuIIiwicm9sZXMiOlsiVVNFUiJdLCJpYXQiOjE2ODc5NTUyMTMsImV4cCI6MTY4Nzk1NzAxM30.RQ0v6Hy9TuNRzoLcQbp8mNDLKo2FXmqos1fUuiakltg",
													"type": "string"
												}
											]
										},
										"method": "POST",
										"header": [],
										"body": {
											"mode": "formdata",
											"formdata": [
												{
													"key": "genreIdList",
													"value": "1,2"
												},
												{
													"key": "title",
													"value": "꺄르르"
												},
												{
													"key": "description",
													"value": "설명 "
												},
												{
													"key": "coverImage",
													"type": "file",
													"src": []
												}
											]
										},
										"url": {
											"raw": "http://localhost:8090/books",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books"
											]
										}
									},
									"response": []
								},
								{
									"name": "Book Update",
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64W46riw7ZuIIiwicm9sZXMiOlsiVVNFUiJdLCJpYXQiOjE2ODgwMzcwMDYsImV4cCI6MTY4ODAzODgwNn0.3kSFYe6IfGa_hBLsXQuUM7maztRNU9dBC2J2iaDa8Y8",
													"type": "string"
												}
											]
										},
										"method": "PUT",
										"header": [],
										"body": {
											"mode": "raw",
											"raw": "{\n    \"bookId\": 1,\n    \"genreIdList\": [1,2,3],\n    \"title\": \"ㅁㄴㅇㅁㄴㅇㄹ\",\n    \"description\": \"ABCDE\"\n   \n}",
											"options": {
												"raw": {
													"language": "json"
												}
											}
										},
										"url": {
											"raw": "http://localhost:8090/books",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books"
											]
										}
									},
									"response": []
								},
								{
									"name": "Book Delete",
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1QG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi6rCV65iQ64qmIiwicm9sZXMiOlsiVVNFUiJdLCJpYXQiOjE2ODc0MDczOTAsImV4cCI6MTY4NzQwOTE5MH0.Oo8X94USxWN_Sjekmby9P58GN1kKmtYV76lbTrL8Qv0",
													"type": "string"
												}
											]
										},
										"method": "DELETE",
										"header": [],
										"body": {
											"mode": "raw",
											"raw": "{\n    \"bookId\": 1\n}",
											"options": {
												"raw": {
													"language": "json"
												}
											}
										},
										"url": {
											"raw": "http://localhost:8090/books/1",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"1"
											]
										}
									},
									"response": []
								}
							]
						}
					]
				},
				{
					"name": "Episode",
					"item": [
						{
							"name": "Query",
							"item": [
								{
									"name": "Episode Query",
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64uJ64Sk7J6EMSIsInJvbGVzIjpbIlVTRVIiXSwiaWF0IjoxNjg3NDM5NzU1LCJleHAiOjE2ODc0NDE1NTV9.qbiB9maStLh5Bp2qEv46P0cf_pxUE9CoM5IdrRdfhz8",
													"type": "string"
												}
											]
										},
										"method": "GET",
										"header": [],
										"url": {
											"raw": "http://localhost:8090/books/1/episode/1",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"1",
												"episode",
												"1"
											]
										}
									},
									"response": []
								}
							]
						},
						{
							"name": "Command",
							"item": [
								{
									"name": "Episode Create",
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64W46riw7ZuIIiwicm9sZXMiOlsiVVNFUiJdLCJpYXQiOjE2ODgwNDI4OTgsImV4cCI6MTY4ODA0NDY5OH0.dxI8zcAfPp5KfdD39fRY7y8LwBkFf6DoIzakqsg4Rjw",
													"type": "string"
												}
											]
										},
										"method": "POST",
										"header": [],
										"body": {
											"mode": "formdata",
											"formdata": [
												{
													"key": "epiTitle",
													"value": "제목 잘 들어갔나?22",
													"type": "text"
												},
												{
													"key": "content",
													"value": "내용내용내용",
													"type": "text"
												},
												{
													"key": "quote",
													"value": "한마디",
													"type": "text"
												},
												{
													"key": "cover",
													"type": "file",
													"src": []
												},
												{
													"key": "epiNum",
													"value": "300",
													"type": "text",
													"disabled": true
												}
											]
										},
										"url": {
											"raw": "http://localhost:8090/books/1/episode",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"1",
												"episode"
											]
										}
									},
									"response": []
								},
								{
									"name": "Episode Update",
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64W46riw7ZuIIiwicm9sZXMiOlsiVVNFUiJdLCJpYXQiOjE2ODgwNTM4MDEsImV4cCI6MTY4ODA1NTYwMX0.gZtbHSTJF54hsEDKujQNp4R4CZLCV5lf6LLJ2oYZQ3E",
													"type": "string"
												}
											]
										},
										"method": "PUT",
										"header": [],
										"body": {
											"mode": "raw",
											"raw": "{\r\n    \"epiTitle\" : \"수정해봉\",\r\n    \"content\" : \"잘래\",\r\n    \"quote\" : \"휴\"\r\n}",
											"options": {
												"raw": {
													"language": "json"
												}
											}
										},
										"url": {
											"raw": "http://localhost:8090/books/1/episode/798e7fab-d57c-4bee-ab08-8c7e31081bc6",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"1",
												"episode",
												"798e7fab-d57c-4bee-ab08-8c7e31081bc6"
											]
										}
									},
									"response": []
								},
								{
									"name": "Episode Delete",
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64W46riw7ZuIIiwicm9sZXMiOlsiVVNFUiJdLCJpYXQiOjE2ODgwNTg3MzcsImV4cCI6MTY4ODA2MDUzN30.ti8OpskxolhmXjyUQ6_3mgGG85CawsbrfYtcyTl9nKo",
													"type": "string"
												}
											]
										},
										"method": "DELETE",
										"header": [],
										"body": {
											"mode": "raw",
											"raw": ""
										},
										"url": {
											"raw": "http://localhost:8090/books/1/episode/07f66e04-45b1-4733-b13d-c23078a7ad1e",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"1",
												"episode",
												"07f66e04-45b1-4733-b13d-c23078a7ad1e"
											]
										}
									},
									"response": []
								}
							]
						}
					]
				},
				{
					"name": "Episode Comment",
					"item": [
						{
							"name": "Query",
							"item": [
								{
									"name": "Comment Query",
									"protocolProfileBehavior": {
										"disableBodyPruning": true
									},
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64W46riw7ZuIIiwicm9sZXMiOlsiVVNFUiJdLCJpYXQiOjE2ODc3NTk4ODIsImV4cCI6MTY4Nzc2MTY4Mn0.l0PlQc8DEGu4ytAawOBkdqzQAFkQPiL3fn1FSCmAlIg",
													"type": "string"
												}
											]
										},
										"method": "GET",
										"header": [],
										"body": {
											"mode": "raw",
											"raw": "{\r\n    \"content\" : \"댓글댓글\"\r\n} ",
											"options": {
												"raw": {
													"language": "json"
												}
											}
										},
										"url": {
											"raw": "http://localhost:8090/books/14/episode/1a684bcf-1aeb-4ddc-961c-b2eaed88450b/comment",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"14",
												"episode",
												"1a684bcf-1aeb-4ddc-961c-b2eaed88450b",
												"comment"
											]
										}
									},
									"response": []
								}
							]
						},
						{
							"name": "Command",
							"item": [
								{
									"name": "Comment Update",
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64uJ64Sk7J6EMSIsInJvbGVzIjpbIlVTRVIiXSwiaWF0IjoxNjg3NjIyNDY0LCJleHAiOjE2ODc2MjQyNjR9.Y4j6MBpoiIk7pKo-tD7mDy1L_ktMk4mnuuZGXuHIPxc",
													"type": "string"
												}
											]
										},
										"method": "PUT",
										"header": [],
										"body": {
											"mode": "raw",
											"raw": "{\r\n    \"content\" : \"수정도 되는 댓글?\"\r\n} ",
											"options": {
												"raw": {
													"language": "json"
												}
											}
										},
										"url": {
											"raw": "http://localhost:8090/books/1/episode/a4b9f7d7-9a31-4a80-9721-5cbfdbe85401/comment/b6550bd1-eb89-49d8-98dc-c1da96f8317e",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"1",
												"episode",
												"a4b9f7d7-9a31-4a80-9721-5cbfdbe85401",
												"comment",
												"b6550bd1-eb89-49d8-98dc-c1da96f8317e"
											]
										}
									},
									"response": []
								},
								{
									"name": "Comment Create",
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64uJ64Sk7J6EIiwicm9sZXMiOlsiVVNFUiJdLCJpYXQiOjE2ODgwMDQ1NjcsImV4cCI6MTY4ODAwNjM2N30.ujnwp2-72NskPnSp-6aRftB4TqFMhYjUWvwH4dFqqxQ",
													"type": "string"
												}
											]
										},
										"method": "POST",
										"header": [],
										"body": {
											"mode": "raw",
											"raw": "{\r\n    \"content\" : \"댓글댓글\"\r\n} ",
											"options": {
												"raw": {
													"language": "json"
												}
											}
										},
										"url": {
											"raw": "http://localhost:8090/books/1/episode/a4b9f7d7-9a31-4a80-9721-5cbfdbe85401/comment",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"1",
												"episode",
												"a4b9f7d7-9a31-4a80-9721-5cbfdbe85401",
												"comment"
											]
										}
									},
									"response": []
								},
								{
									"name": "Comment Delete",
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64uJ64Sk7J6EMSIsInJvbGVzIjpbIlVTRVIiXSwiaWF0IjoxNjg3NjE3ODg2LCJleHAiOjE2ODc2MTk2ODZ9.M3oJ_dk6aTWnzLK-sg75RsSKF-QO_lY0x8hO1Jc3nDU",
													"type": "string"
												}
											]
										},
										"method": "DELETE",
										"header": [],
										"body": {
											"mode": "raw",
											"raw": "{\r\n    \"content\" : \"대댓글 수정@\"\r\n} ",
											"options": {
												"raw": {
													"language": "json"
												}
											}
										},
										"url": {
											"raw": "http://localhost:8090/books/1/episode/a4b9f7d7-9a31-4a80-9721-5cbfdbe85401/comment/f6fb2dc8-52c2-4246-a75b-cf183fc2f28ax",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"1",
												"episode",
												"a4b9f7d7-9a31-4a80-9721-5cbfdbe85401",
												"comment",
												"f6fb2dc8-52c2-4246-a75b-cf183fc2f28ax"
											]
										}
									},
									"response": []
								}
							]
						}
					]
				},
				{
					"name": "Episode Reply",
					"item": [
						{
							"name": "Query",
							"item": [
								{
									"name": "Reply Query",
									"protocolProfileBehavior": {
										"disableBodyPruning": true
									},
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64uJ64Sk7J6EMSIsInJvbGVzIjpbIlVTRVIiXSwiaWF0IjoxNjg3NTk1NzgxLCJleHAiOjE2ODc1OTc1ODF9.08lwI5cTo57WKbLXApjtbOwI-F3KWk2KpcQ_qW7YXtM",
													"type": "string"
												}
											]
										},
										"method": "GET",
										"header": [],
										"body": {
											"mode": "raw",
											"raw": "{\r\n    \"comment\" : \"댓글댓글\"\r\n} ",
											"options": {
												"raw": {
													"language": "json"
												}
											}
										},
										"url": {
											"raw": "http://localhost:8090/books/14/episode/1a684bcf-1aeb-4ddc-961c-b2eaed88450b/comment/7139586f-ce46-40a9-a57d-a1264a7b5134/reply",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"14",
												"episode",
												"1a684bcf-1aeb-4ddc-961c-b2eaed88450b",
												"comment",
												"7139586f-ce46-40a9-a57d-a1264a7b5134",
												"reply"
											]
										}
									},
									"response": []
								}
							]
						},
						{
							"name": "Command",
							"item": [
								{
									"name": "Reply Update",
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64uJ64Sk7J6EMSIsInJvbGVzIjpbIlVTRVIiXSwiaWF0IjoxNjg3NjIyNDY0LCJleHAiOjE2ODc2MjQyNjR9.Y4j6MBpoiIk7pKo-tD7mDy1L_ktMk4mnuuZGXuHIPxc",
													"type": "string"
												}
											]
										},
										"method": "PUT",
										"header": [],
										"body": {
											"mode": "raw",
											"raw": "{\r\n    \"content\" : \"대댓글 수정도?\"\r\n} ",
											"options": {
												"raw": {
													"language": "json"
												}
											}
										},
										"url": {
											"raw": "http://localhost:8090/books/1/episode/a4b9f7d7-9a31-4a80-9721-5cbfdbe85401/comment/b6550bd1-eb89-49d8-98dc-c1da96f8317e/reply/3bd24072-1510-4fb2-9400-af6ffdeb698e",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"1",
												"episode",
												"a4b9f7d7-9a31-4a80-9721-5cbfdbe85401",
												"comment",
												"b6550bd1-eb89-49d8-98dc-c1da96f8317e",
												"reply",
												"3bd24072-1510-4fb2-9400-af6ffdeb698e"
											]
										}
									},
									"response": []
								},
								{
									"name": "Reply Create",
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64W46riw7ZuIIiwicm9sZXMiOlsiVVNFUiJdLCJpYXQiOjE2ODgzODYxNDksImV4cCI6MTY4ODM4Nzk0OX0.949KIs2yo44uGQF7eXFciRm-fQWt9tmCPPQHxCMrXRU",
													"type": "string"
												}
											]
										},
										"method": "POST",
										"header": [],
										"body": {
											"mode": "raw",
											"raw": "{\r\n    \"content\" : \"대 댓글? 작은 댓글은?\"\r\n} ",
											"options": {
												"raw": {
													"language": "json"
												}
											}
										},
										"url": {
											"raw": "http://localhost:8090/books/14/episode/1a684bcf-1aeb-4ddc-961c-b2eaed88450b/comment/7139586f-ce46-40a9-a57d-a1264a7b5134/reply",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"14",
												"episode",
												"1a684bcf-1aeb-4ddc-961c-b2eaed88450b",
												"comment",
												"7139586f-ce46-40a9-a57d-a1264a7b5134",
												"reply"
											]
										}
									},
									"response": []
								},
								{
									"name": "Reply Delete",
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64uJ64Sk7J6EIiwicm9sZXMiOlsiVVNFUiJdLCJpYXQiOjE2ODgwMDQ5NzYsImV4cCI6MTY4ODAwNjc3Nn0.rvISYHNqWVrD_DQzD5c7LE_vkqrpkaRm1LoxvoI_ylw",
													"type": "string"
												}
											]
										},
										"method": "DELETE",
										"header": [],
										"body": {
											"mode": "raw",
											"raw": "{\r\n    \"content\" : \"대댓글 수정@\"\r\n} ",
											"options": {
												"raw": {
													"language": "json"
												}
											}
										},
										"url": {
											"raw": "http://localhost:8090/books/1/episode/a4b9f7d7-9a31-4a80-9721-5cbfdbe85401/comment/b6550bd1-eb89-49d8-98dc-c1da96f8317e/reply/3bd24072-1510-4fb2-9400-af6ffdeb698e",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"1",
												"episode",
												"a4b9f7d7-9a31-4a80-9721-5cbfdbe85401",
												"comment",
												"b6550bd1-eb89-49d8-98dc-c1da96f8317e",
												"reply",
												"3bd24072-1510-4fb2-9400-af6ffdeb698e"
											]
										}
									},
									"response": []
								}
							]
						}
					]
				},
				{
					"name": "Favorite",
					"item": [
						{
							"name": "Query",
							"item": [
								{
									"name": "Favorite Query",
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64W46riw7ZuIIiwicm9sZXMiOlsiVVNFUiJdLCJpYXQiOjE2ODc5MTk4NTAsImV4cCI6MTY4NzkyMTY1MH0.DmHGYo4bqDA1163shF_89HGbckllNq89AEYnBoN0OoE",
													"type": "string"
												}
											]
										},
										"method": "GET",
										"header": [],
										"url": {
											"raw": "http://localhost:8090/books/favorite",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"favorite"
											]
										}
									},
									"response": []
								}
							]
						},
						{
							"name": "Command",
							"item": [
								{
									"name": "Favorite Create",
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64W46riw7ZuIIiwicm9sZXMiOlsiVVNFUiJdLCJpYXQiOjE2ODgwMjYzNjUsImV4cCI6MTY4ODAyODE2NX0.YwgSDJ9k1YrrHWeEAC0YV8gZEFqToOoIk8-FWoIwqBU",
													"type": "string"
												}
											]
										},
										"method": "POST",
										"header": [],
										"body": {
											"mode": "formdata",
											"formdata": [
												{
													"key": "bookId",
													"value": "2",
													"type": "text"
												}
											]
										},
										"url": {
											"raw": "http://localhost:8090/books/favorite",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"favorite"
											]
										}
									},
									"response": []
								},
								{
									"name": "Favorite Delete",
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64W46riw7ZuIIiwicm9sZXMiOlsiVVNFUiJdLCJpYXQiOjE2ODc5MjYxMDEsImV4cCI6MTY4NzkyNzkwMX0.JbMBVp9ucEFfXjYyqRCQBM5pPHim4TMhdmNJDnUwaps",
													"type": "string"
												}
											]
										},
										"method": "DELETE",
										"header": [],
										"body": {
											"mode": "formdata",
											"formdata": [
												{
													"key": "bookId",
													"value": "1",
													"type": "text"
												}
											]
										},
										"url": {
											"raw": "http://localhost:8090/books/favorite",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"favorite"
											]
										}
									},
									"response": []
								}
							]
						}
					]
				},
				{
					"name": "Heart",
					"item": [
						{
							"name": "Command Copy",
							"item": [
								{
									"name": "Heart Create",
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64W47ZKNIiwicm9sZXMiOlsiVVNFUiJdLCJpYXQiOjE2ODc5MTE3ODMsImV4cCI6MTY4NzkxMzU4M30.O4oOtwrQaafYwoTGT0tDwi1jjIQKJE1KoJxp2q2Gasg",
													"type": "string"
												}
											]
										},
										"method": "POST",
										"header": [],
										"body": {
											"mode": "raw",
											"raw": "{\r\n    \"content\" : \"댓글댓dfsafa글\"\r\n} ",
											"options": {
												"raw": {
													"language": "json"
												}
											}
										},
										"url": {
											"raw": "http://localhost:8090/books/1/episode/eaeabaec-d196-4ab7-98af-fbe63115ad47/heart/",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"1",
												"episode",
												"eaeabaec-d196-4ab7-98af-fbe63115ad47",
												"heart",
												""
											]
										}
									},
									"response": []
								},
								{
									"name": "Heart Delete",
									"request": {
										"auth": {
											"type": "bearer",
											"bearer": [
												{
													"key": "token",
													"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi64W47ZKNIiwicm9sZXMiOlsiVVNFUiJdLCJpYXQiOjE2ODc4NDY5NDEsImV4cCI6MTY4Nzg0ODc0MX0.WPUN8z_gutIAKongpvNYiVlqgCpvXu1fVfBYzjD3rkk",
													"type": "string"
												}
											]
										},
										"method": "DELETE",
										"header": [],
										"body": {
											"mode": "formdata",
											"formdata": []
										},
										"url": {
											"raw": "http://localhost:8090/books/1/episode/eaeabaec-d196-4ab7-98af-fbe63115ad47/heart",
											"protocol": "http",
											"host": [
												"localhost"
											],
											"port": "8090",
											"path": [
												"books",
												"1",
												"episode",
												"eaeabaec-d196-4ab7-98af-fbe63115ad47",
												"heart"
											]
										}
									},
									"response": []
								}
							]
						}
					]
				}
			]
		},
		{
			"name": "003. Local board: 8010",
			"item": [
				{
					"name": "Query",
					"item": []
				},
				{
					"name": "Command",
					"item": [
						{
							"name": "New Request",
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1QG5hdmVyLmNvbSIsImF1dGgiOiJVU0VSIiwibmlja25hbWUiOiLqsJXrmJDriqYiLCJleHAiOjE2ODY1Nzc5MTB9.GVsKFY9P718zu2x84LtuvAxpQYqGO3ZHp7xwYf3gJvc",
											"type": "string"
										}
									]
								},
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\n    \"boardTitle\": \"Title\",\n    \"boradContent\": \"Content\"\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8080/boards",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"boards"
									]
								}
							},
							"response": []
						}
					]
				}
			]
		},
		{
			"name": "009. Local test",
			"item": [
				{
					"name": "Upload image",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "formdata",
							"formdata": [
								{
									"key": "file",
									"type": "file",
									"src": []
								}
							]
						},
						"url": {
							"raw": "http://localhost:8080/upload2/image",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"upload2",
								"image"
							]
						}
					},
					"response": []
				}
			]
		}
	]
}
```

</details>


