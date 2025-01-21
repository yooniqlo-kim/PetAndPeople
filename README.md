# PetAndPeople
## 🐶 서비스 소개

반려동물 양육 인구 증가에 따라 유기동물 수도 증가하고 있습니다. **PetAndPeople**은 이러한 문제를 해결하기 위해 유기동물 보호 현황을 제공하고, AI를 활용하여 사용자 맞춤형 유기동물을 추천하는 유기동물 종합 플랫폼입니다.

## 🌐 배포 URL

[PetAndPeople](https://petandpeople.site/)

## ✅ 주요 기능

### 1. 실종동물 게시판

### 2. 유기동물 보호 현황 제공

### 3. AI 사용자 맞춤 유기동물 추천

### 4. 회원 관리

## 📁 프로젝트 구조

```bash
src/
├── main/
│   ├── java/com/ssafy/petandpeople/
│   │   ├── application/            # 애플리케이션 레이어 (DTO, Converter, Service)
│   │   │   ├── dto/                 
│   │   │   ├── converter/            
│   │   │   ├── service/               
│   │   ├── common/                  # 공통 유틸리티 및 예외 처리
│   │   ├── config/                   # 설정 관련 클래스 (Redis, S3, Web 등)
│   │   ├── domain/                   # 도메인 모델 
│   │   ├── infrastructure/           # 인프라 계층 (Persistence, External API)
│   │   │   ├── persistence/           # JPA 엔티티 및 레포지토리
│   │   │   ├── external/              # 외부 API 호출 클라이언트
│   │   ├── presentation/              # 컨트롤러 및 응답 처리
│   │   ├── PetandpeopleApplication.java  
│   ├── resources/
│   │   ├── application.yaml          # 기본 환경 설정 파일
│   │   ├── application-test.yaml      # 테스트 환경 설정 파일
├── test/
│   ├── java/com/ssafy/petandpeople/
│   │   ├── application/service/       # 서비스 계층의 테스트 코드
│   │   ├── domain/user/               # 도메인 관련 테스트
│   │   ├── PetandpeopleApplicationTests.java 

```

## 💻 기술 스택

### BackEnd

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/jpa-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white"> <img src="https://img.shields.io/badge/swagger-4FC08D?style=for-the-badge&logo=swagger&logoColor=white"> <img src="https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=JUnit5&logoColor=white">

### FrontEnd

<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">

### Infra

<img src="https://camo.githubusercontent.com/a467aa2c79012231e654c43f227a0da26e58ff194e340c20a803c4b735eff74b/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f415753204543322d4646393930303f7374796c653d666f722d7468652d6261646765266c6f676f3d616d617a6f6e656332266c6f676f436f6c6f723d7768697465"> <img src="https://camo.githubusercontent.com/ffeb4b7d2f078c8d968415defd1b83cce8fa7da14e26e03074544640ecaa105e/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f415753205244532d3532374646463f7374796c653d666f722d7468652d6261646765266c6f676f3d616d617a6f6e726473266c6f676f436f6c6f723d7768697465"> <img src="https://img.shields.io/badge/docker-0769AD?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/Amazon%20S3-569A31?style=for-the-badge&logo=Amazon%20S3&logoColor=white"> <img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white"> 

### Tools

<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">  <img src="https://camo.githubusercontent.com/3647bba9752f84cfcb4ec225305451a376726a52123cc7ac2e6f689fde749452/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4e6f74696f6e2d3030303030303f7374796c653d666f722d7468652d6261646765266c6f676f3d4e6f74696f6e266c6f676f436f6c6f723d7768697465"> 

## 🔎 시스템 아키텍쳐

![Web App Reference Architecture.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/f1eae27a-84f4-4085-9b8a-9f3cbc9c9e01/1bccbdb2-449b-408b-af21-a78240a9a502/Web_App_Reference_Architecture.png)

## 📑 ERD

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/f1eae27a-84f4-4085-9b8a-9f3cbc9c9e01/6b161395-983a-4e92-bdf2-a906b0e91d77/image.png)

## 📚 API

### ✅ 정상 응답 처리 (성공 시)

- 본문이 없는 경우 `Api.OK()`

```bash
{
    "result": {
        "resultMessage": "성공"
    },
    "body": null
}
```

- 본문이 있는 경우 `Api.OK(내용)`

```bash
# 예시 ) 사용자 정보를 조회하는 경우
{
    "result": {
        "resultMessage": "성공"
    },
    "body": {
        "userId": 123,
        "email": "ssafy@ssafy.com",
        "nickname": "ssafy"
    }
}
```

### 🚨 에러 응답 흐름

- 클라이언트 오류

```bash
{
    "result": {
        "resultMessage": "가입되지 않은 사용자입니다. 다시 확인해주세요."
    },
    "body": null
}

```

- 서버 내부 오류 (일관된 메세지 반환)

```bash
{
    "result": {
        "resultMessage": "일시적인 서버에러가 발생했습니다. 잠시 후 다시 시도해주세요."
    },
    "body": null
}

```

### 1️⃣ User

| 기능 | API | Http Method | 설명 | Response |
| --- | --- | --- | --- | --- |
| 회원가입 | /api/user/sign-up | POST | 사용자 회원가입 | Api.OK() |
| 로그인 | /api/user/login | POST | 사용자 로그인 처리 후 세션 처리 | Api.OK() |
| 로그아웃 | /api/user/logout | POST | 로그아웃 | Api.OK() |
| 사용자 상세 정보 | /api/user/detail | POST | 로그인된 사용자의 상세 정보 조회 | Api.OK(userDto) |

### 2️⃣ Email

| 기능 | API | Http Method | 설명 | Response |
| --- | --- | --- | --- | --- |
| 이메일 인증 코드 전송 | /api/email/send | POST | 사용자의 이메일로 인증코드 전송 | Api.OK() |
| 이메일 인증 코드 검증 | /api/email/verify | POST | 사용자가 입력한 입력코드가 유효한지 검증 | Api.OK() |

### 3️⃣ AbandonedAnimal

| 기능 | API | Http Method | 설명 | Response |
| --- | --- | --- | --- | --- |
| 유기동물 보호 데이터 조회 | /api/adoption/data | GET | 유기동물 보호 데이터를 페이지 단위로 조회 | Api.OK(adoptionData) |

### 4️⃣ Post

| 기능 | API | Http Method | 설명 | Response |
| --- | --- | --- | --- | --- |
| 게시글 생성 | /api/post/create | POST | 게시물 생성 | Api.OK() |
| 게시글 수정 | /api/post/update/{postKey} | POST | 특정 게시물 수정 | Api.OK() |
| 게시물 상세 조회 | /api/post/select/detail/{postKey} | GET | 특정 게시물 상세 정보 반환 | Api.OK(PostDto) |
| 특정 사용자 게시글 조회 | /api/post/select/user | GET | 현재 로그인한 사용자가 작성한 게시물 조회 | Api.OK(List<PostDto>) |
| 모든 게시글 조회 | /api/post/select/all | GET | 모든 게시글 조회 | Api.OK(List<PostDto>) |
| 게시글 삭제 | /api/post/delete/{postKey} | GET | 특정 게시글 삭제 | Api.OK() |

### 5️⃣ AI

| 기능 | API | Http Method | 설명 | Response |
| --- | --- | --- | --- | --- |
| 유기동물 추천 | /api/ai/recommend | POST | 사용자 선호도 기반으로 유기동물 추천 | Api.OK(String) |

## 📋 Convention

### Code Convention

- 클린코드와 객체 지향 프로그래밍을 위해 객체 지향 생활 체조 규칙 도입

```bash
규칙 1: 한 메서드에 오직 한 단계의 들여쓰기(indent)만 한다.

규칙 2: else 예약어를 쓰지 않는다.

규칙 3: 모든 원시값과 문자열을 포장한다.

규칙 4: 한 줄에 점을 하나만 찍는다.

규칙 5: 줄여쓰지 않는다(축약 금지).

규칙 6: 모든 엔티티를 작게 유지한다.

규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.

규칙 8: 게터/세터/프로퍼티를 가능하면 사용하지 않는다.
```

### Commit Convention

| Type | Subject |
| --- | --- |
| feat | 새로운 기능 추가 |
| fix | 버그 수정 |
| docs | 문서 수정 |
| style | 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우 |
| refactor | 코드 리팩토링 |
| test | 테스트 코드, 리팩토링 테스트 코드 추가 |
| chore | 빌드 업무 수정, 패키지 매니저 수정, production code와 무관한 부분들 (.gitignore, build.gradle 등) |
| comment | 주석 추가 및 변경 |
| remove | 파일, 폴더 삭제 |
| rename | 파일, 폴더명 수정 |
