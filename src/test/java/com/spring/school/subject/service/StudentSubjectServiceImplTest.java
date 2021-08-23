package com.spring.school.subject.service;

import com.spring.school.student.StudentSubjectMapper;
import com.spring.school.student.domain.StudentSubject;
import com.spring.school.student.domain.StudentSubject.StudentScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class StudentSubjectServiceImplTest {

  @Autowired
  StudentSubjectMapper studentSubjectMapper;

  @Test
  @DisplayName("학생 과목 추가")
  public void addStudentSubject() throws Exception {
    //given
    StudentSubject studentSubject = StudentSubject.builder()
        .studentId(1)
        .subjectId(2)
        .build();

    //when
    studentSubjectMapper.insertStudentSubject(studentSubject);

    //then));

    int isSubjectStudent = studentSubjectMapper
        .existsSubjectStudent(studentSubject.getStudentId(), studentSubject.getSubjectId());

    if (isSubjectStudent != 1) {
      throw new RuntimeException("학생 과목이 존재 하지 않습니다.");
    }

  }

  @Test
  @DisplayName("학생 과목 점수 업데이트")
  public void updateStudentSubjectScore() throws Exception {
    //given
    StudentSubject insertStudentSubject = StudentSubject.builder()
        .studentId(1)
        .subjectId(2)
        .build();

    studentSubjectMapper.insertStudentSubject(insertStudentSubject);

    StudentSubject updateStudentSubject = StudentSubject.builder()
        .studentId(1)
        .subjectId(2)
        .score("100")
        .build();

    //when
    studentSubjectMapper.updateStudentSubjectScore(updateStudentSubject);

    //then
    int isSubjectStudent = studentSubjectMapper
        .existsSubjectStudent(insertStudentSubject.getStudentId(),
            insertStudentSubject.getSubjectId());

    if (isSubjectStudent != 1) {
      throw new RuntimeException("학생 과목이 존재 하지 않습니다.");
    }

    StudentScore findStudentSubjectScore = studentSubjectMapper
        .getStudentSubjectScore(updateStudentSubject.getSubjectId(),
            updateStudentSubject.getStudentId());

    Assertions.assertEquals(Integer.parseInt(updateStudentSubject.getScore()),
        findStudentSubjectScore.getScore());

  }

}