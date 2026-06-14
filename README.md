# SPRING

1. [Spring Framework와 핵심 특징](#01-spring-framework와-핵심-특징)
2. [의존성 주입 (DI)](#02-의존성-주입-di)
3. [Spring Boot와 프로젝트 구조](#03-spring-boot와-프로젝트-구조)
4. [Spring MVC](#04-spring-mvc)
5. [REST API](#05-rest-api)
6. [3-tier 아키텍처와 개발 순서](#06-3-tier-아키텍처와-개발-순서)
7. [VO와 DTO](#07-vo와-dto)
8. [MyBatis 연동](#08-mybatis-연동)
9. [AOP](#09-aop)
10. [인증 — 세션 · Spring Security · JWT](#10-인증--세션--spring-security--jwt)
11. [게시판 만들기(threetier v1~v6)](#11-게시판-threetier-v1v6)
12. [Spring Security 심화 (app_day01~05)](#12-spring-security-심화-app_day0105)

---

## 01. Spring Framework와 핵심 특징

### Framework

- **라이브러리**: 개발자가 작성해 놓은 코드 파일.
- **API**: 여러 라이브러리가 모여 있는 패키지(JAR).
- **프레임워크**: 여러 API가 아주 많이 모여 덩치가 커진 것.

개발에 필요한 구조를 이미 코드로 만들어 두었기 때문에, 반쯤 완성된 상태에서 필요한 부분을 조립하는 형태로 개발할 수 있다. → 일정 품질 보장 + 개발 시간 단축.

### Spring Framework — 경량 프레임워크

예전 프레임워크는 너무 복잡하고 방대했지만, Spring은 특정 기능 위주로 간단한 JAR를 선택해 개발할 수 있게 구성되어 있다.

### 핵심 특징

| 특징 | 의미 |
|------|------|
| **POJO 기반 구성** | Plain Old Java Object. 일반적인 자바 객체 구성 방식을 그대로 사용할 수 있다. |
| **AOP 지원** | 반복 코드(보안·로그·트랜잭션 등 횡단 관심사)를 분리해 핵심 로직에 집중. |
| **Transaction 관리** | 어노테이션으로 트랜잭션 영역을 구성, 예외 시 자동 롤백/커밋. |
| **편리한 MVC 구조** | 어노테이션 기반 MVC. |
| **WAS에 비종속** | 전체 실행 없이 기능별 단위 테스트가 용이. |
| **DI** | 객체 간 관계를 외부에서 주입해 구성. |

---

## 02. 의존성 주입 (DI)

### 의존성 (Dependency)

하나의 객체가 다른 객체 없이는 제대로 동작할 수 없는 것. (A가 B 없이 동작 불가 → "A가 B에 의존적")
직접 A 필드에 B 객체를 생성하면 결합성이 단단해져 유연성이 떨어진다.

### 주입 (Injection)

외부에서 객체를 내부로 밀어 넣는 것. 결합성을 느슨하게 하고 유연성을 높인다.

```
의존성        : A →→→→→→ B        (A 필드에 B 객체를 직접 생성)
의존성 주입   : A ↔↔ ? ↔↔ B       (A는 B가 필요하다고 신호 → ?가 B를 생성해 주입)
```

Spring에서는 **`ApplicationContext`가 `?`** 역할을 하며 필요한 객체를 생성·주입한다.
→ 개발자는 객체를 분리해서 생성하고, 객체를 엮는 **wiring** 형태로 개발한다.

- `ApplicationContext`가 관리하는 객체를 **빈(Bean)** 이라 하고, **Spring Container(Bean Container)** 에 저장된다.
- 같은 타입의 빈이 여러 개일 때는 `@Qualifier`로 식별자를 지정해 원하는 객체를 주입받는다.

---

## 03. Spring Boot와 프로젝트 구조

Spring Framework는 초기 설정과 라이브러리 버전 맞추기가 어렵고 오래 걸린다.
**Spring Boot**는 자동 설정과 의존성 관리를 제공해 이 복잡성을 해결한다.

| 경로 | 역할 |
|------|------|
| `src/main/java` | 서버단 JAVA 파일 |
| `src/test/java` | 단위 테스트 |
| `src/main/resources` | 설정 파일 및 뷰 |
| `src/main/resources/static` | css, js, image 등 정적 파일 |
| `src/main/resources/templates` | HTML 파일 |
| `build.gradle` | 라이브러리 관리 |
| `application.yml` | Spring의 모든 설정 |

---

## 04. Spring MVC

Front-Controller 패턴 기반으로 요청을 처리한다.

```
REQUEST → DispatcherServlet → HandlerMapping → HandlerAdapter → Controller
RESPONSE ← View ← ViewResolver ← ┘
```

- `HttpServletRequest` / `HttpServletResponse` 직접 사용을 지양한다.
- 다양한 타입의 파라미터·리턴 타입을 사용할 수 있다.
- GET/POST 등 전송 방식 처리를 **어노테이션**으로 한다. (상속/인터페이스 대신)

---

## 05. REST API

**REST** (Representational State Transfer): URI만으로도 데이터·행위(CRUD) 상태를 이해할 수 있도록 설계하는 규칙.

1. 소문자로 작성한다.
2. 언더바 대신 하이픈(`-`)을 사용한다.
3. URI 마지막에 슬래시를 작성하지 않는다.
4. 계층(포함) 관계는 슬래시로 구분한다.
5. 파일 확장자는 URI에 포함하지 않는다. (Header의 Content-Type 사용)
6. 행위(동사)는 URI에 담지 않고 **HTTP Method**로 표현한다.
7. URI 단어는 복수로 작성한다. (데이터의 집합)

```
http://www.app.com/members/delete/1   (X)
DELETE http://www.app.com/members/1    (O)
```

| HTTP Method | 동작 |
|-------------|------|
| GET | 조회 (Read) |
| POST | 생성 (Create) |
| PUT / PATCH | 수정 (전체 / 일부) |
| DELETE | 삭제 (Delete) |

---

## 06. 3-tier 아키텍처와 개발 순서

Spring 프로젝트는 3-tier 방식으로 설계한다. 각 영역을 독립적으로 설계해, 기술이 바뀌어도 부품처럼 교체할 수 있게 한다.

```
Presentation  ↔  Business  ↔  Persistence  ↔  DBMS
     ↑              ↑             ↑
 Controller      Service      Repository / Mapper
```

- **Presentation Tier (Controller)**: 화면에 보여주는 기술.
- **Business Tier (Service)**: 순수한 비즈니스 로직.
- **Persistence Tier (Mapper/Repository)**: 데이터 보관·사용 방식.

### 개발 순서

```
테이블 확인 → VO·DTO → mapper.xml → Mapper.java → (테스트)
→ DAO(Repository) → Service → (테스트) → Controller → HTML
```

> **레이어별 책임**
> - DAO까지는 **메소드 하나당 SQL 하나**.
> - **트랜잭션은 Service부터** 묶이기 시작한다. (`@Transactional`)
> - 연산은 Controller에서 절대 작성하지 않고 **모두 Service에 작성**한다.

---

## 07. VO와 DTO

입력과 출력에서 역할을 나눠 쓴다.

| | DTO | VO |
|---|-----|-----|
| 역할 | 화면에서 온 데이터 (**입력**) | DB에서 조회한 데이터 (**출력**) |
| 변경 | Setter 있음, 변경 가능 | Setter 없음, 불변 |
| 쓰임 | Controller가 받아 Mapper까지 전달 | 조회 결과를 그대로 신뢰 |

```
insert(UserDTO)                          // 화면 입력을 저장 (변경 가능한 DTO)
selectByEmail(String) → Optional<UserDTO> // 존재 여부만 확인하고 버림 (잠깐 쓰는 용도)
selectUserForLogin(UserDTO) → Optional<UserVO> // 입력은 DTO(검색조건), 출력은 불변 VO
```

- **INSERT**는 VO가 기본이지만, 삽입된 행의 정보(id)가 필요하면 DTO를 쓴다.
- `input` 태그의 `name`은 DTO를 보고 작성한다.

---

## 08. MyBatis 연동

SQL을 XML 매퍼에 분리하고, 자바는 Mapper 인터페이스로 호출한다.

- **결과 타입**: `resultType`이 있는 select는 리턴 타입을 정확히 — 단일 조회는 `Optional`로 감싸고, 여러 개는 `List`로 받는다.
- **enum 매핑**: `TypeHandler` / `Converter`로 DB 문자열 ↔ 자바 enum을 변환한다. (`StatusHandler`, `StringToStatusConverter` 등)
- **동적 쿼리**: 검색·필터에 `<foreach>`, `<if>`, `<choose>`, `<trim>`을 사용한다.
- **1:N 조회**: 한 번에 조회할 테이블이 많고 1:N이면 DTO에 `List`를 선언해 따로 쿼리를 짠다.

---

## 09. AOP

> 내가 작성하지 않은 코드가 실행된다면 AOP 영역에서 실행된 것이다.

핵심 로직(종단 관심사)은 아니지만 반복적으로 필요한 **주변 로직(횡단 관심사)** — 보안·로그·트랜잭션·예외처리 등 — 을 모듈로 분리해 적절한 시점에 주입하는 것.
→ 코드 중복을 줄이고 핵심 로직과 주변 로직을 분리해 관리한다.

### AOP 용어

| 용어 | 의미 | 비유 (연예인=Target, 매니저=Proxy) |
|------|------|------|
| **Aspect** | 무엇을 어디서 할 것인가 (주변 로직 모듈의 묶음) | 매니저가 할 일을 정의한 문서 |
| **Advice** | 어떤 동작을 언제 할 것인가 (실행 코드) | 매니저가 계약서 작성 |
| **Joinpoint** | Advice가 적용될 수 있는 모든 시점 | 가능한 모든 스케줄 |
| **Pointcut** | 실제 작업을 수행할 타겟 필터 | "광고만 하겠다" |

### Joinpoint 종류

`Around`(전 구역), `Before`(시작 직후), `After`(종료 직전), `AfterReturning`(리턴 후), `AfterThrowing`(예외 발생 후).

### 설계 순서

1. 횡단 관심사를 의미하는 **어노테이션** 만들기
2. 어노테이션을 **AOP로 등록**하기
3. 종단 관심사에 등록된 **어노테이션 사용**하기

---

## 10. 인증 — 세션 · Spring Security · JWT

### 세션

- 처음 로그인하면 쿠키에 세션 ID가 없으므로, 세션이 새로 만들어 세션 ID를 발급 → 쿠키에 저장된다.
- 이후 요청은 세션 ID를 전달해 세션에 저장된 것과 비교한다.
- 세션은 반드시 `Request` 객체를 통해 가져오고, **세션에 VO를 담지 않는다.**

### Spring Security

Spring 기반 애플리케이션의 보안을 담당하며 **인증(Authentication)** 과 **인가(Authorization)** 를 처리한다.

| 구성 요소 | 역할 |
|-----------|------|
| **Provider** | JWT 생성·서명·검증 |
| **Filter** | 인증 성공 시 JWT 생성 후 클라이언트에 전달 |
| **UserDetailService** | 사용자 정보 조회 및 인증 처리 |
| **SecurityConfig** | 필터 체인, 인증 방식, 접근 제한, 권한 설정 |

### JWT (JSON Web Token)

서버-클라이언트 간 인증 정보를 안전하게 주고받는 JSON 토큰. 토큰 자체가 인증 수단이라 세션을 강제하지 않는다(**stateless**).

```
JWT = Header + Payload + Signature
```

- **Header**: 토큰 타입·암호화 알고리즘.
- **Payload**: 사용자 정보·만료시간.
- **Signature**: Header+Payload를 시크릿 키로 암호화 → 위변조 방지. 서버가 다시 서명을 만들어 비교, 다르면 거절한다.

**인증 흐름**: ① ID/PW 로그인 → ② 인증 성공 시 JWT 생성·전달 → ③ 이후 요청 헤더에 JWT 포함 → ④ 서버가 JWT 검증 → ⑤ 권한 검사 후 접근 허용.

---

## 11. 게시판 (threetier v1~v6)

3-tier 아키텍처로 설계한 게시판을, 기능을 점진적으로 추가하며 발전시킨 프로젝트.
(`domain` / `dto` / `mapper` / `repository` / `service` / `controller` 계층 분리 + MyBatis + 카카오 OAuth)

| 버전 | 추가 기능 |
|------|-----------|
| **v1** | 게시글 CRUD, 파일 업로드, **페이징** (`Criteria`) |
| **v2** | **더보기 / 무한 스크롤** (비동기 페이징) |
| **v3** | **검색 · 태그 필터** (`Search`, 동적 쿼리) |
| **v4** | 게시글 **수정 / 삭제 / 조회**, 카카오 **로그아웃** |
| **v5** | **이메일 · SMS** 발송 (`MailService`, `SmsService`) |
| **v6** | **REST API · 댓글**, **인터셉터** (`TestInterceptor`, `WebMvcConfig`) |

---

## 12. Spring Security 심화 (app_day01~05)

Spring Security를 단계별로 적용하며 인증·인가 체계를 구축한 프로젝트.

| 단계 | 내용 |
|------|------|
| **day01** | Spring Security 기본 설정 (`SecurityConfig`) |
| **day02** | **JWT 인증/인가** (`AuthenticationFilter`, `JwtTokenProvider`, `CustomUserDetails`) + **Redis** |
| **day03** | **OAuth2 로그인** (`CustomOAuth2UserService`, `OAuth2SuccessHandler`) |
| **day04** | **AWS S3 파일 업로드** (`S3Service`) + **Swagger** API 문서 (`SwaggerConfig`) |
| **day05** | **HTTPS** 적용 + **AOP** 로깅 (`LogAspect`, 커스텀 어노테이션) |
