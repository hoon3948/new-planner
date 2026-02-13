![java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![mySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)

<img src="https://capsule-render.vercel.app/api?type=waving&color=BDBDC8&height=150&section=header" />

# 일정 관리 프로그램 만들기
<img src="https://capsule-render.vercel.app/api?type=waving&color=BDBDC8&height=150&section=footer" />


___
### 목차
1. API 명세서
2. ERD
3. 과제 진행 사항

___
# API 명세서

_`HttpStatus.NO_CONTENT`: 메세지 출력을 위해서 `HttpStatus.OK`로 변경해서 사용하였음_

## 1. [프로필 관리](https://documenter.getpostman.com/view/51111882/2sBXcBmh4B)
### 회원가입
<details>
<summary>접기/펼치기</summary>

- Request
  
| Method |   URL   |   Content-Type   |
|:------:|:-------:|:----------------:|
|  POST  | /signup | application/json |
  - Body
```json
{
    "name": "robo",
    "email": "robo@ex.com",
    "password": "12345678"
}
```
- Response
  - Status Code: <font color="green"> 201 Created </font>
  - Body
```json
{
  "success": true,
  "data": {
    "name": "robo",
    "message": "회원가입이 완료되었습니다."
  }
}
```
  - Status Code: <font color="red">404 Bad Request</font>
  - Body
```json
{
  "success": false,
  "error": {
    "code": "P001",
    "error": "BAD_REQUEST",
    "message": "이미 존재하는 이메일입니다.",
    "path": "/signup",
    "status": 400,
    "timestamp": "2026-02-13T13:49:24.6242012"
  }
}
```

</details>

### 로그인
<details>
<summary>접기/펼치기</summary>

- Request

| Method |  URL   |   Content-Type   |
|:------:|:------:|:----------------:|
|  POST  | /login | application/json |
  - Body
```json
{
    "email":"robo@ex.com",
    "password":"12345678"
}
```
- Response
  - Status code: <font color="green">200 OK</font>
  - Body
```json
{
  "success": true,
  "data": {
    "message": "로그인 성공!!"
  }
}
```
- Response
  - Status code: <font color="red">404 Not Found</font>
  - Body
```json
{
    "success": false,
    "error": {
        "code": "P003",
        "error": "NOT_FOUND",
        "message": "회원을 찾을 수 없습니다.",
        "path": "/login",
        "status": 404,
        "timestamp": "2026-02-13T13:50:51.7283539"
    }
}
```
  - Status code: <font color="red">401 Unauthorized</font>
  - Body
```json
{
    "success": false,
    "error": {
        "code": "P006",
        "error": "UNAUTHORIZED",
        "message": "비밀번호가 일치하지 않습니다.",
        "path": "/login",
        "status": 401,
        "timestamp": "2026-02-13T13:50:02.4319896"
    }
}
```
</details>

### 프로필 조회
<details>
<summary>접기/펼치기</summary>

- Request

| Method |    URL    |   Content-Type   | Query Params |
|:------:|:---------:|:----------------:|:------------:|
|  GET   | /profiles | application/json |     name     |
- Response
  - Status Code: <font color="green">200 OK</font>
  - Body
```json
{
    "success": true,
    "data": [
        {
            "userId": 1,
            "name": "robo",
            "modifiedAt": "2026-02-13T13:49:01.313483",
            "createdAT": "2026-02-13T13:49:01.313483"
        }
    ],
    "message": "출력 성공!"
}
```
</details>

### 프로필 단건 조회
<details>
<summary>접기/펼치기</summary>

- Request

| Method |          URL          |   Content-Type   | PathVariable |
|:------:|:---------------------:|:----------------:|:------------:|
|  GET   | /profiles/{profileId} | application/json |  profileId   |
- Response
  - Status Code: <font color="green">200 OK</font>
  - Body
```json
{
    "success": true,
    "data": {
        "userId": 1,
        "name": "robo",
        "modifiedAt": "2026-02-13T13:49:01.313483",
        "createdAT": "2026-02-13T13:49:01.313483"
    },
    "message": "출력 성공!"
}
```
  - Status Code: <font color="red">404 Not Found</font>
  - Body
```json
{
  "success": false,
  "error": {
    "code": "P003",
    "error": "NOT_FOUND",
    "message": "회원을 찾을 수 없습니다.",
    "path": "/profiles/2",
    "status": 404,
    "timestamp": "2026-02-13T13:56:43.4267622"
  }
}
```
</details>

### 내 프로필 수정
<details>
<summary>접기/펼치기</summary>

- Request

| Method |    URL     |   Content-Type   | PathVariable |
|:------:|:----------:|:----------------:|:------------:|
|  PUT   | /myprofile | application/json |      id      |
  - Body
```json
{
  "name":"robot",
  "email":"robot@ex.com",
  "password":"432143"
}
```
- Response
  - Status Code: <font color="green">200 OK</font>
  - Body
```json
{
  "success": true,
  "data": {
    "name": "robot",
    "message": "내 프로필 수정이 완료되었습니다."
  }
}
```
</details>

### 로그아웃
<details>
<summary>접기/펼치기</summary>

- Request

| Method |   URL   |   Content-Type   |
|:------:|:-------:|:----------------:|
|  POST  | /logout | application/json |
- Response
  - Status Code: <font color="green">200 OK</font>
  - Body
```json
{
    "success": true,
    "data": {
        "message": "로그아웃 완료"
    }
}
```
  - Status Code: <font color="red">400 Bad Request</font>
  - Body
```json
{
    "success": false,
    "error": {
        "code": "P008",
        "error": "BAD_REQUEST",
        "message": "로그인되어있지않습니다.",
        "path": "/logout",
        "status": 400,
        "timestamp": "2026-02-13T13:57:44.7137019"
    }
}
```
</details>

### 회원탈퇴
<details>
<summary>접기/펼치기</summary>

- Request

| Method |    URL     |   Content-Type   |
|:------:|:----------:|:----------------:|
| DELETE | /myprofile | application/json |
- Response
  - Status Code: <font color="green">200 OK</font>
  - Body
```json
{
    "success": true,
    "data": {
        "message": "회원탈퇴가 완료되었습니다."
    }
}
```
  - Status Code: <font color="red">500 Internal Server Error</font>
  - Body
```json
{
    "success": false,
    "error": {
        "code": "C002",
        "error": "INTERNAL_SERVER_ERROR",
        "message": "알 수 없는 에러가 발생했습니다.",
        "path": "/myprofile",
        "status": 500,
        "timestamp": "2026-02-13T13:58:08.9422232"
    }
}
```
</details>

## 2. [일정 관리](https://documenter.getpostman.com/view/51111882/2sBXc7MkQH)
### 나의 일정 생성
<details>
<summary>접기/펼치기</summary>

- Request

| Method |    URL     |   Content-Type   |
|:------:|:----------:|:----------------:|
|  POST  | /calenders | application/json |
  - Body
```json
{
    "title" : "영화시청",
    "content": "3시에 cgv"
}
```
- Response
  - Status Code: <font color="green">201 Created</font>
  - Body
```json
{
    "success": true,
    "data": {
        "content": "3시에 cgv",
        "message": "일정 생성이 완료되었습니다."
    }
}
```
  - Status Code: <font color="red">404 Not Found</font>
  - Body
```json

```
</details>


### 일정 전체 조회
<details>
<summary>접기/펼치기</summary>

- Request

| Method |    URL     |   Content-Type   | Query Params |
|:------:|:----------:|:----------------:|:------------:|
|  GET   | /calenders | application/json |  profileId   |
- Response
  - Status Code: <font color="green">200 OK</font>
  - Body
```json
{
    "success": true,
    "data": [
        {
            "calenderId": 2,
            "title": "가족 모임",
            "content": "6시에 집으로",
            "profileId": 3,
            "createdAt": "2026-02-13T14:01:36.45683",
            "modifiedAt": "2026-02-13T14:01:36.45683"
        },
        {
            "calenderId": 1,
            "title": "영화시청",
            "content": "3시에 cgv",
            "profileId": 3,
            "createdAt": "2026-02-13T13:59:20.005566",
            "modifiedAt": "2026-02-13T13:59:20.005566"
        }
    ],
    "message": "출력 성공!"
}
```
  - Status Code: <font color="red">404 Not Found</font>
  - Body
```json

```
</details>

### 일정 단건 조회
<details>
<summary>접기/펼치기</summary>

- Request

| Method |           URL           |   Content-Type   | PathVariable |
|:------:|:-----------------------:|:----------------:|:------------:|
|  GET   | /calenders/{calenderId} | application/json |      id      |
- Response
  - Status Code: <font color="green">200 OK</font>
  - Body
```json
{
  "success": true,
  "data": {
    "calenderId": 1,
    "title": "영화시청",
    "content": "3시에 cgv",
    "profileId": 1,
    "createdAt": "2026-02-13T14:34:15.550029",
    "modifiedAt": "2026-02-13T14:34:15.550029",
    "comments": [
      {
        "calenderId": 1,
        "commentId": 1,
        "content": "무슨 영화 보러 가는데",
        "createdAt": "2026-02-13T14:34:19.045405",
        "modifiedAt": "2026-02-13T14:36:04.734084",
        "profileId": 1
      },
      {
        "calenderId": 1,
        "commentId": 2,
        "content": "언제출발할거야?",
        "createdAt": "2026-02-13T14:35:09.173496",
        "modifiedAt": "2026-02-13T14:35:09.173496",
        "profileId": 1
      }
    ]
  },
  "message": "출력 성공!"
}
```
  - Status Code: <font color="red">404 Not Found</font>
  - Body
```json
{
    "success": false,
    "error": {
        "code": "P004",
        "error": "NOT_FOUND",
        "message": "일정을 찾을 수 없습니다.",
        "path": "/calenders/3",
        "status": 404,
        "timestamp": "2026-02-13T14:02:18.7238855"
    }
}
```
</details>

### 나의 일정 수정
<details>
<summary>접기/펼치기</summary>

- Request

| Method |           URL           |   Content-Type   | PathVariable |
|:------:|:-----------------------:|:----------------:|:------------:|
|  PUT   | /calenders/{calenderId} | application/json |      id      |
  - Body
```json
{
    "title" : "영화시청",
    "content" : "2시반에 버스 타고 cgv 가자"
}
```
- Response
  - Status Code: <font color="green">200 OK</font>
  - Body
```json
{
    "success": true,
    "data": {
        "calenderId": 1,
        "message": "일정 수정 성공"
    }
}
```
  - Status Code: <font color="red">404 Not Found</font>
  - Body
```json
{
    "success": false,
    "error": {
        "code": "P004",
        "error": "NOT_FOUND",
        "message": "일정을 찾을 수 없습니다.",
        "path": "/calenders/4",
        "status": 404,
        "timestamp": "2026-02-13T14:03:00.978821"
    }
}
```
</details>

### 나의 일정 삭제
<details>
<summary>접기/펼치기</summary>

- Request

| Method |           URL           |   Content-Type   | PathVariable |
|:------:|:-----------------------:|:----------------:|:------------:|
| DELETE | /calenders/{calenderId} | application/json |      id      |
- Response
  - Status Code: <font color="green">200 OK</font>
  - Body
```json
{
    "success": true,
    "data": {
        "message": "삭제 성공"
    }
}
```
  - Status Code: <font color="red">400 Bad Request</font>
  - Body
```json
{
    "success": false,
    "error": {
        "code": "P007",
        "error": "BAD_REQUEST",
        "message": "사용자가 일치하지 않습니다.",
        "path": "/calenders/2",
        "status": 400,
        "timestamp": "2026-02-13T14:03:53.9754593"
    }
}
```
</details>

## 3. [댓글 관리](https://documenter.getpostman.com/view/51111882/2sBXcBmh4C)
### 나의 댓글 등록
<details>
<summary>접기/펼치기</summary>

- Request

| Method |               URL                |   Content-Type   | PathVariable |
|:------:|:--------------------------------:|:----------------:|:------------:|
|  POST  | /calenders/{calenderId}/comments | application/json |      id      |
  - Body
```json
{
    "content": "같이 가자"
}
```
- Response
  - Status Code: <font color="green">201 Created</font>
  - Body
```json
{
    "success": true,
    "data": {
        "content": "같이 가자",
        "message": "댓글 생성 성공"
    }
}
```
</details>

### 댓글 전체 조회
<details>
<summary>접기/펼치기</summary>

- Request

| Method |    URL    |   Content-Type   | PathVariable | Query Params |
|:------:|:---------:|:----------------:|:------------:|:------------:|
|  GET   | /comments | application/json |      id      |  profileId   |
- Response
  - Status Code: <font color="green">200 OK</font>
  - Body
```json
{
    "success": true,
    "data": [
        {
            "calenderId": 1,
            "commentId": 1,
            "content": "같이 가자",
            "createdAt": "2026-02-13T14:34:19.045405",
            "modifiedAt": "2026-02-13T14:34:19.045405",
            "profileId": 1
        },
        {
            "calenderId": 1,
            "commentId": 2,
            "content": "언제출발할거야?",
            "createdAt": "2026-02-13T14:35:09.173496",
            "modifiedAt": "2026-02-13T14:35:09.173496",
            "profileId": 1
        }
    ],
    "message": "출력 성공!"
}
```
</details>

### 나의 댓글 수정
<details>
<summary>접기/펼치기</summary>

- Request

| Method |          URL           |   Content-Type   | PathVariable |
|:------:|:----------------------:|:----------------:|:------------:|
|  PUT   | /comments/{commentId}  | application/json |      id      |
  - Body
```json
{
    "content": "무슨 영화 보러 가는데"
}
```
- Response
  - Status Code: <font color="green">200 OK</font>
  - Body
```json
{
    "success": true,
    "data": {
        "commentId": 1,
        "message": "댓글 수정 완료"
    }
}
```
</details>

### 나의 댓글 삭제
<details>
<summary>접기/펼치기</summary>

- Request

| Method |          URL          |   Content-Type   | PathVariable |
|:------:|:---------------------:|:----------------:|:------------:|
| DELETE | /comments/{commentId} | application/json |      id      |
- Response
  - Status Code: <font color="green">200 OK</font>
  - Body
```json
{
    "success": true,
    "data": {
        "message": "댓글 삭제 성공"
    }
}
```
</details>

___
# ERD

![img.png](img.png)

___

# 과제 진행 사항

## 1. 필수과제
- [x] 일정 CRUD
  - 일정을 생성, 전체 조회, 단건 조회, 수정, 삭제할 수 있습니다.  
    - `작성 유저명`, `할일 제목`, `할일 내용`, `작성일`, `수정일` 필드  
    - `작성일`, `수정일` 필드는 `JPA Auditing`을 활용합니다.
- [x] 유저 CRUD
  - 유저를 생성, 전체 조회, 단건 조회, 수정, 삭제할 수 있습니다.  
  - 유저는 아래와 같은 필드를 가집니다.  
    - `유저명`, `이메일`, `작성일`, `수정일` 필드  
    - `작성일`, `수정일` 필드는 `JPA Auditing`을 활용합니다.  
  - 연관관계 구현  
    - 일정은 이제 `작성 유저명` 필드 대신 `유저 고유 식별자` 필드를 가집니다.
- [x] 회원가입
  - 유저에 `비밀번호` 필드 추가
  - 8자 이상이어야 합니다.
- [x] 로그인
  - [ ] `Cookie` 활용하여 로그인 기능 구현
  - [x] `Session` 활용하여 로그인 기능 구현
  - **조건**
    - `이메일`과 `비밀번호`를 활용해 로그인 기능을 구현합니다.
    - 필요한 API들에서 세션을 활용합니다.

## 2. 도전과제
- [X] 다양한 예외처리
  - Validation을 활용해 다양한 예외처리를 적용합니다.
    - @RestControllerAdvice를 활용하여 validation 에러 상황을 클라이언트에게 전달합니다.
  - 정해진 예외처리 항목이 있는 것이 아닌 프로젝트를 분석하고 예외사항을 지정해 봅니다.
- [X] 비밀번호 암호화
  - Lv.3에서 추가한 `비밀번호` 필드에 들어가는 비밀번호를 암호화합니다.
    - 암호화를 위한 `PasswordEncoder`를 직접 만들어 사용합니다.
- [X] 댓글 CRUD 
  - 생성한 일정에 댓글을 남길 수 있습니다.
    - 댓글과 일정은 연관 관계를 가집니다.
  - 댓글을 저장, 전체 조회할 수 있습니다.
  - 댓글은 아래와 같은 필드를 가집니다.
    - `댓글 내용`, `작성일`, `수정일`, `유저 고유 식별자`, `일정 고유 식별자` 필드
    - `작성일`, `수정일` 필드는 `JPA Auditing`을 활용하여 적용합니다.
- [ ] 일정 페이징
  - 일정을 Spring Data JPA의 `Pageable`과 `Page` 인터페이스를 활용하여 페이지네이션을 구현
    - `페이지 번호`와 `페이지 크기`를 쿼리 파라미터로 전달하여 요청하는 항목을 나타냅니다.
    - `할일 제목`, `할일 내용`, `댓글 개수`, `일정 작성일`, `일정 수정일`, `일정 작성 유저명` 필드를 조회합니다.
    - 디폴트 `페이지 크기`는 10으로 적용합니다.
  - 일정의 `수정일`을 기준으로 내림차순 정렬합니다.



