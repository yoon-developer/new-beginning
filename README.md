# 기능 개발

## 1. 실행 방법

1. Docker 사용 하여 Mysql 설치 합니다.
```text
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD='dyshin' --name mysql-school mysql
```
2. DDL 참고 하여 Database를 생성 합니다.
3. Application 실행후, http://localhost:8080/swagger-ui.html 접속 하여 API 테스트를 진행 합니다.


## 2. 코드 구조

```text
요청 -> Controller -> Service(interface) -> ServiceImpl -> Mapper(interface)
```

- Swagger를 추가 하여 API 테스트
- Package 구조는 도메인형, 계층형 동시 사용
- ApiErrorResponse 를 사용하여 ErrorResponse 처리
- ExceptionAdvisor 를 사용하여 Exception handling
- RequestDto, ResponseDto, Entity를 분리해서 사용 하여 Entity 변경 방지
- Builder를 적용하여 데이터 불변성을 확보

### 2.1. API 테스트 순서
select a spec: v1
```text
1. 학원 생성 -> 과목 생성 -> 학생 추가 -> 학생에게 과목 추가 -> 학생 과목 점수 변경

2. 학생에게 추가된 과목 삭제 -> 학생 삭제 -> 과목 삭제 -> 학원 삭제
```
![api](https://user-images.githubusercontent.com/74950076/130014162-c7f57d37-1d0a-4590-b802-629898f603e5.PNG)

## 3. Database
### 3.1. ERD
![db_diagram](https://user-images.githubusercontent.com/74950076/129978747-d7d2e482-33a5-4db3-8073-fbcba4c58806.PNG)

### 3.2. DDL
```mysql
CREATE DATABASE school;;

USE school;

CREATE TABLE `academy` (
  `academy_id` int PRIMARY KEY AUTO_INCREMENT COMMENT '학원 ID',
  `academy_name` varchar(20) COMMENT '학원 이름',
  `description` varchar(2048) COMMENT '학원 설명',
  `create_date` datetime COMMENT '생성 시간',
  `modified_date` datetime COMMENT '업데이트 시간'
);

CREATE TABLE `student` (
  `student_id` int PRIMARY KEY AUTO_INCREMENT COMMENT '학생 ID',
  `academy_id` int COMMENT '학원 ID',
  `student_name` varchar(20) COMMENT '학생 이름',
  `student_grade` varchar(30) COMMENT '학년',
  `parent_phone_number` varchar(11) COMMENT '학부모 핸드폰 번호',
  `school_name` varchar(10) COMMENT '학교명',
  `birthday` varchar(8) COMMENT '학생 생년월일',
  `student_phone_number` varchar(11) COMMENT '학생 핸드폰 번호',
  `home_address` varchar(1024) COMMENT '집 주소',
  `home_phone_number` varchar(11) COMMENT '집 전화 번호',
  `create_date` datetime COMMENT '생성 시간',
  `modified_date` datetime COMMENT '업데이트 시간'
);

CREATE TABLE `student_subject` (
  `student_id` int COMMENT '학생 ID',
  `subject_id` int COMMENT '과목 ID',
  `score` varchar(3) COMMENT '점수',
  `create_date` datetime COMMENT '생성 시간',
  `modified_date` datetime COMMENT '업데이트 시간'
);

CREATE TABLE `subject` (
  `subject_id` int PRIMARY KEY AUTO_INCREMENT COMMENT '과목 ID',
  `subject_name` varchar(20) COMMENT '과목 이름',
  `school` varchar(2) COMMENT '학교',
  `subject_grade` varchar(10) COMMENT '학년,학기',
  `publisher` varchar(10) COMMENT '출판사',
  `create_date` datetime COMMENT '생성 시간',
  `modified_date` datetime COMMENT '업데이트 시간'
);

ALTER TABLE `student` ADD FOREIGN KEY (`academy_id`) REFERENCES `academy` (`academy_id`);

ALTER TABLE `student_subject` ADD FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`);

ALTER TABLE `student_subject` ADD FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`);
```
