JPA Refactoring Project
================

> ππ» Summary <br />
> [κΈ°μ‘΄ μ¬μ΄νΈ](http://www.alittlevanilla.kro.kr/, "link") νμ€νΈ μ΄μμ νλ©° Java μΈμ΄μ κ΄κ³ν λ°μ΄ν° λ² μ΄μ€ κ° ν¨λ¬λ€μ λΆμΌμΉκ° λΆλ¬μ€λ μ¬λ¬ λ¬Έμ μ μ λκ»΄, μ΄λ₯Ό κ°μ νκΈ° μν΄ νλ‘μ νΈμ JPAλ₯Ό λμνμμ΅λλ€. JPAλ μ°κ΄λ κ°μ²΄λ₯Ό μ¬μ©νλ μμ μ SQLμ μ λ¬ν  μ μκ³ , κ°μ νΈλμ­μ λ΄μμ μ‘°νν  λ λμΌμ±λ λ³΄μ₯νκΈ° λλ¬Έμ μ¬μ΄νΈ JPA λ¦¬ν©ν λ§ νλ‘μ νΈλ₯Ό μννμμ΅λλ€.
<br />

## νλ‘μ νΈ κ΅¬μ‘° <br />

<img width="1353" alt="αα₯α«αα¦ααͺαα₯αΌ" src="https://user-images.githubusercontent.com/68539040/194755672-0559e78e-e674-44bc-88d5-453470fc0b32.png">
 νμ€νΈ νμ΄μ§ νλ©΄ μλλ€.  λλ©μΈ λΆλ¦¬ μμ 100% μλ£ ν κΈ°μ‘΄ νλ‘ νΈμλ νλ©΄μΌλ‘ μ νν  μμ  μλλ€.
<br /> <br />

## νλ‘μ νΈ μ£Όμ κ΄μ¬μ¬
+ λ³κ²½κ³Ό νμ₯μ μ μ°ν κΈ°μ μ μ§ν₯ν©λλ€. 
  > + [JPA μ¬μ© μ΄μ ](https://blog.naver.com/ghdalswl77/222809316027) <br /> 
  > + [Entityμμμ setter μ¬μ© μ§μ](https://blog.naver.com/ghdalswl77/222895556212) <br />
  > + [νμ€νΈμ½λ μμ± μ΅κ΄ν](https://blog.naver.com/ghdalswl77/222896219248) <br />
  > + μ°κ΄κ΄κ³λ λͺ¨λ μ§μ° λ‘λ©μΌλ‘ μ€μ  <br />
  > + @ManyToMany κ΄κ³ μ§μ <br />
  > + querydsl μ¬μ© <br />
      <br />

## κ°λ° νκ²½
+ Java, Mac, IntelliJ Ultimate(Educational License), Lombok, Gradle 7.5
  <br />

## μ¬μ© κΈ°μ  μ€ν
+ Backend: **Spring Boot 2.7.4**
+ Frontend: νμ¬ thymeleaf λ‘ κ΅¬νλμ΄ μμ΅λλ€. λλ©μΈ λΆλ¦¬ μμ μλ£ ν Reactλ‘ μ ν μμ  μλλ€.
      <br /><br />


## API μ€κ³ λ° μ§ν μν©
### ππ» νμ μ λ³΄
| Feature | Request | API | μ€λͺ |
| ------ | -- | -- | -- |
| νμκ°μ | POST | /member/join | μΌλ°νμ form DB μ μ‘, νμκ°μ μ€λ³΅ λ°©μ§ |
| νμμμ  | PUT | /member/members/{id} | νμ μ λ³΄ μμ  |
| νμλͺ©λ‘ | GET/POST | /members | νμ λͺ©λ‘ μ‘°ν |
<br />
<br />


### π μν
| Feature | Request | API | μ€λͺ |
| ------ | -- | -- | ----------- |
| μνμμ± | POST | /product/register | μν 1κ° μμ± |
| μνμ‘°ν | GET | /product/read/{idx} | μν idxλ₯Ό ν΅ν μν 1κ° μ‘°ν |
| μνμ­μ  | PATCH | /product/delete/{idx} | μν idxλ₯Ό ν΅ν μν 1κ° μ­μ  |
| μνμμ  | PATCH | /product/update/{idx} | μν μ λ³΄ μμ  |
| μνλͺ©λ‘ | GET | /product/list | μν λͺ©λ‘ μ‘°ν. μνλͺ, νλ§€μ, μν μ¬μ§ λ± μ λ³΄ ν¬ν¨. |
<br />
<br />

### π μ£Όλ¬Έ
| Feature | Request | API | μ€λͺ |
| ------ | -- | -- | ----------- |
| μνμ£Όλ¬Έ | POST | /order/create | μν μ£Όλ¬Έ |
| μ£Όλ¬Έμ‘°ν | GET | /order/{idx} | μ£Όλ¬Έ μ‘°ν |
| μ£Όλ¬Έμ·¨μ | POST | /order/{orderId}/cancel | μ£Όλ¬Έ μ·¨μ (μ£Όλ¬Έ μν λ³κ²½) |
<br />
<br />

### μν λ¦¬λ·°
| Feature | Request | API | μ€λͺ |
| ------ | -- | -- | ----------- |
| λ¦¬λ·°μμ± | POST | /reviews/{productId} | μνμ λν λ¦¬λ·° μμ± |
| λ¦¬λ·°λͺ©λ‘ | GET | /reviews/{productId}/all | ν΄λΉ μνμ λν λ¦¬λ·° μ μ²΄ μ‘°ν |
| λ¦¬λ·°μμ  | PUT | /reviews/{productId}/reviewId | ν΄λΉ μνμ λν νΉμ  λ¦¬λ·° μμ  |
| λ¦¬λ·°μ­μ  | DEL | /reviews/{productId}/reviewId | ν΄λΉ μνμ λν νΉμ  λ¦¬λ·° μ­μ  |

<br />
<br />

<hr />

