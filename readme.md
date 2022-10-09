JPA Refactoring Project
================

> 👉🏻 Summary <br />
> [기존 사이트](http://www.alittlevanilla.kro.kr/, "link") 테스트 운영을 하며 Java 언어와 관계형 데이터 베이스 간 패러다임 불일치가 불러오는 여러 문제점을 느껴, 이를 개선하기 위해 프로젝트에 JPA를 도입하였습니다. JPA는 연관된 객체를 사용하는 시점에 SQL을 전달할 수 있고, 같은 트랜잭션 내에서 조회할 때 동일성도 보장하기 때문에 사이트 JPA 리팩토링 프로젝트를 수행하였습니다.
<br />

## 프로젝트 구조 <br />

<img width="1353" alt="전체과정" src="https://user-images.githubusercontent.com/68539040/194755672-0559e78e-e674-44bc-88d5-453470fc0b32.png">
 테스트 페이지 화면 입니다.  도메인 분리 작업 100% 완료 후 기존 프론트엔드 화면으로 전환할 예정 입니다.
<br /> <br />

## 프로젝트 주요 관심사
+ 변경과 확장에 유연한 기술을 지향합니다. 
  > + [JPA 사용 이유](https://blog.naver.com/ghdalswl77/222809316027) <br /> 
  > + [Entity에서의 setter 사용 지양](https://blog.naver.com/ghdalswl77/222895556212) <br />
  > + [테스트코드 작성 습관화](https://blog.naver.com/ghdalswl77/222896219248) <br />
  > + 연관관계는 모두 지연 로딩으로 설정 <br />
  > + @ManyToMany 관계 지양 <br />
  > + querydsl 사용 <br />
      <br />

## 개발 환경
+ Java, Mac, IntelliJ Ultimate(Educational License), Lombok, Gradle 7.5
  <br />

## 사용 기술 스택
+ Backend: **Spring Boot 2.7.4**
+ Frontend: 현재 thymeleaf 로 구현되어 있습니다. 도메인 분리 작업 완료 후 React로 전환 예정 입니다.
      <br /><br />


## API 설계 및 진행 상황
### 🙋🏻 회원 정보
| Feature | Request | API | 설명 |
| ------ | -- | -- | -- |
| 회원가입 | POST | /member/join | 일반회원 form DB 전송, 회원가입 중복 방지 |
| 회원수정 | PUT | /member/members/{id} | 회원 정보 수정 |
| 회원목록 | GET/POST | /members | 회원 목록 조회 |
<br />
<br />


### 🛍 상품
| Feature | Request | API | 설명 |
| ------ | -- | -- | ----------- |
| 상품작성 | POST | /product/register | 상품 1개 작성 |
| 상품조회 | GET | /product/read/{idx} | 상품 idx를 통한 상품 1개 조회 |
| 상품삭제 | PATCH | /product/delete/{idx} | 상품 idx를 통한 상품 1개 삭제 |
| 상품수정 | PATCH | /product/update/{idx} | 상품 정보 수정 |
| 상품목록 | GET | /product/list | 상품 목록 조회. 상품명, 판매자, 상품 사진 등 정보 포함. |
<br />
<br />

### 📝 주문
| Feature | Request | API | 설명 |
| ------ | -- | -- | ----------- |
| 상품주문 | POST | /order/create | 상품 주문 |
| 주문조회 | GET | /order/{idx} | 주문 조회 |
| 주문취소 | POST | /order/{orderId}/cancel | 주문 취소 (주문 상태 변경) |
<br />
<br />

### 상품 리뷰
| Feature | Request | API | 설명 |
| ------ | -- | -- | ----------- |
| 리뷰작성 | POST | /reviews/{productId} | 상품에 대한 리뷰 작성 |
| 리뷰목록 | GET | /reviews/{productId}/all | 해당 상품에 대한 리뷰 전체 조회 |
| 리뷰수정 | PUT | /reviews/{productId}/reviewId | 해당 상품에 대한 특정 리뷰 수정 |
| 리뷰삭제 | DEL | /reviews/{productId}/reviewId | 해당 상품에 대한 특정 리뷰 삭제 |

<br />
<br />

<hr />

