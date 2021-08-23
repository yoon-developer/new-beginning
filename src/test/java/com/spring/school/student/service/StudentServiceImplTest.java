package com.spring.school.student.service;

import com.spring.school.student.StudentMapper;
import com.spring.school.student.domain.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class StudentServiceImplTest {

  @Autowired
  StudentMapper studentMapper;

  @Test
  @DisplayName("학생 추가")
  public void addStudent() throws Exception {
    //given
    Student student = Student.builder()
        .birthday("20210101")
        .homeAddress("서울시")
        .homePhoneNumber("0123456789")
        .parentPhoneNumber("01012345678")
        .schoolName("Yoon Developer")
        .studentGrade("중1")
        .studentName("홍길동")
        .studentPhoneNumber("01012344567")
        .build();

    //when
    studentMapper.insertStudent(student);

    //then
    Student findStudent = studentMapper.getStudent(student.getAcademyId(), student.getStudentId());

    Assertions.assertEquals(student.getBirthday(), findStudent.getBirthday());
    Assertions.assertEquals(student.getHomeAddress(), findStudent.getHomeAddress());
    Assertions.assertEquals(student.getHomePhoneNumber(), findStudent.getHomePhoneNumber());
    Assertions.assertEquals(student.getParentPhoneNumber(), findStudent.getParentPhoneNumber());
    Assertions.assertEquals(student.getSchoolName(), findStudent.getSchoolName());
    Assertions.assertEquals(student.getStudentGrade(), findStudent.getStudentGrade());
    Assertions.assertEquals(student.getStudentName(), findStudent.getStudentName());
    Assertions.assertEquals(student.getStudentPhoneNumber(), findStudent.getStudentPhoneNumber());
  }

  @Test
  @DisplayName("학생 삭제")
  public void deleteStudent() throws Exception {
    //given
    Student student = Student.builder()
        .birthday("20210101")
        .homeAddress("서울시")
        .homePhoneNumber("0123456789")
        .parentPhoneNumber("01012345678")
        .schoolName("Yoon Developer")
        .studentGrade("중1")
        .studentName("홍길동")
        .studentPhoneNumber("01012344567")
        .build();

    studentMapper.insertStudent(student);

    //when
    studentMapper.deleteStudent(student.getAcademyId(), student.getStudentId());

    //then
    int isStudent = studentMapper.existsStudent(student.getStudentId());

    if (isStudent != 0) {
      throw new RuntimeException("학생이 존재 합니다.");
    }

  }

}