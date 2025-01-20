# 목차
- [목차](#--)
  * [🐶 서비스 소개](#---------)
  * [🌐 배포 URL](#------url)
  * [📁 프로젝트 구조](#----------)
  * [💻 기술 스택](#--------)
  * [🔎 시스템 아키텍쳐](#-----------)
  * [📑 ERD](#---erd)
  * [✔️ 기능 소개](#--------)
  * [📚 API](#---api)
  * [📋 Convention](#---convention)

## 🐶 서비스 소개

반려동물 양육 인구 증가에 따라 유기동물 수도 증가하고 있습니다. **PetAndPeople**은 이러한 문제를 해결하기 위해 유기동물 보호 현황을 제공하고, AI를 활용하여 사용자 맞춤형 유기동물을 추천하는 유기동물 종합 플랫폼입니다.

## 🌐 배포 URL

[PetAndPeople](https://petandpeople.site/)

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

## 📑 ERD


## ✔️ 기능 소개

## 📚 API

## 📋 Convention

### Code Convention

### Commit Convention
