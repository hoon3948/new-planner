
___
# API
___

## 1. [프로필 관리](https://documenter.getpostman.com/view/51111882/2sBXcBmh4B)
### 회원가입
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
  - Status code
  - Body
### 로그인
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
  - Status code
  - Body

### 프로필 조회
- Request

| Method |    URL    |   Content-Type   | Query Params |
|:------:|:---------:|:----------------:|:------------:|
|  GET   | /profiles | application/json |     name     |
- Response
    - Status code
    - Body
### 프로필 단건 조회
- Request

| Method |          URL          |   Content-Type   | PathVariable |
|:------:|:---------------------:|:----------------:|:------------:|
|  GET   | /profiles/{profileId} | application/json |  profileId   |
- Response
    - Status code
    - Body
### 내 프로필 수정
- Request

| Method |    URL     |   Content-Type   | PathVariable |
|:------:|:----------:|:----------------:|:------------:|
|  PUT   | /myprofile | application/json |      id      |
  - Body
```json
{
    "name":"robo2",
    "email":"robo2@ex.com",
    "password":"4321"
}
```
- Response
    - Status code
    - Body
### 로그아웃
- Request

| Method |   URL   |   Content-Type   |
|:------:|:-------:|:----------------:|
|  POST  | /logout | application/json |
- Response
  - Status code
  - Body
### 회원탈퇴
- Request

| Method |    URL     |   Content-Type   |
|:------:|:----------:|:----------------:|
| DELETE | /myprofile | application/json |
- Response
    - Status code
    - Body

## 2. [일정 관리](https://documenter.getpostman.com/view/51111882/2sBXc7MkQH)
### 나의 일정 생성
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
    - Status code
    - Body
### 일정 전체 조회
- Request

| Method |    URL     |   Content-Type   | Query Params |
|:------:|:----------:|:----------------:|:------------:|
|  GET   | /calenders | application/json |  profileId   |
- Response
    - Status code
    - Body
### 일정 단건 조회
- Request

| Method |           URL           |   Content-Type   | PathVariable |
|:------:|:-----------------------:|:----------------:|:------------:|
|  GET   | /calenders/{calenderId} | application/json |      id      |
- Response
    - Status code
    - Body
### 나의 일정 수정
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
    - Status code
    - Body
### 나의 일정 삭제
- Request

| Method |           URL           |   Content-Type   | PathVariable |
|:------:|:-----------------------:|:----------------:|:------------:|
| DELETE | /calenders/{calenderId} | application/json |      id      |
- Response
    - Status code
    - Body
## 3. [댓글 관리](https://documenter.getpostman.com/view/51111882/2sBXcBmh4C)
### 나의 댓글 등록
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
    - Status code
    - Body
### 댓글 전체 조회 
- Request

| Method |    URL    |   Content-Type   | PathVariable | Query Params |
|:------:|:---------:|:----------------:|:------------:|:------------:|
|  GET   | /comments | application/json |      id      |  profileId   |
- Response
    - Status code
    - Body
### 나의 댓글 수정
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
    - Status code
    - Body
### 나의 댓글 삭제
- Request

| Method |          URL          |   Content-Type   | PathVariable |
|:------:|:---------------------:|:----------------:|:------------:|
| DELETE | /comments/{commentId} | application/json |      id      |
- Response
    - Status code
    - Body

___
# ERD
___

![img.png](img.png)

___
# 과제 진행 사항
___

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


<details>
<summary>접기/펼치기</summary>
내용
</details>
