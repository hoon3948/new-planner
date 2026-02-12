


___

![img.png](img.png)

___

### 1. 필수과제
<details>
<summary>접기/펼치기</summary>

- [x] 일정 CRUD
    <details>
    <summary>접기/펼치기</summary>

    - 일정을 생성, 전체 조회, 단건 조회, 수정, 삭제할 수 있습니다.  
      - `작성 유저명`, `할일 제목`, `할일 내용`, `작성일`, `수정일` 필드  
      - `작성일`, `수정일` 필드는 `JPA Auditing`을 활용합니다.
    </details>

- [x] 유저 CRUD
    <details>
    <summary>접기/펼치기</summary>

    - 유저를 생성, 전체 조회, 단건 조회, 수정, 삭제할 수 있습니다.  
    - 유저는 아래와 같은 필드를 가집니다.  
      - `유저명`, `이메일`, `작성일`, `수정일` 필드  
      - `작성일`, `수정일` 필드는 `JPA Auditing`을 활용합니다.  
    - 연관관계 구현  
      - 일정은 이제 `작성 유저명` 필드 대신 `유저 고유 식별자` 필드를 가집니다.
    </details>

- [x] 회원가입
    <details>
    <summary>접기/펼치기</summary>
      
    - 유저에 비밀번호 필드 추가  
    - 8자 이상이어야함
    </details>
- [x] 로그인
    <details>
    <summary>접기/펼치기</summary>    
      
    - [ ] Cookie 활용
    - [x] Session 활용
    </details>
</details>

### 2. 도전과제
<details>
<summary>접기/펼치기</summary>

- [ ] 다양한 예외처리
    <details>
    <summary>접기/펼치기</summary>
    내용
    </details>
- [ ] 비밀번호 암호화
    <details>
    <summary>접기/펼치기</summary>
    내용
    </details>
- [ ] 댓글 CRUD - 예정
    <details>
    <summary>접기/펼치기</summary>
    내용
    </details>
- [ ] 일정 페이징
    <details>
    <summary>접기/펼치기</summary>
    내용
    </details>
</details>


<details>
<summary>접기/펼치기</summary>
내용
</details>
