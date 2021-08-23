package com.spring.school.subject.service;

import com.spring.school.subject.SubjectMapper;
import com.spring.school.subject.domain.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class SubjectServiceImplTest {

  @Autowired
  SubjectMapper subjectMapper;

  @Test
  @DisplayName("과목 생성")
  public void addSubject() throws Exception {
    //given
    Subject subject = Subject.builder()
        .school("중등")
        .subjectGrade("2-1")
        .subjectName("과목1")
        .publisher("Yoon Developer")
        .build();

    //when
    subjectMapper.insertSubject(subject);

    //then
    Subject findSubject = subjectMapper.getSubject(subject.getSubjectId());

    Assertions.assertEquals(subject.getSchool(), findSubject.getSchool());
    Assertions.assertEquals(subject.getSubjectGrade(), findSubject.getSubjectGrade());
    Assertions.assertEquals(subject.getSubjectName(), findSubject.getSubjectName());
    Assertions.assertEquals(subject.getPublisher(), findSubject.getPublisher());

  }

  @Test
  @DisplayName("과목 삭제")
  public void deleteSubjetct() throws Exception {
    //given
    Subject subject = Subject.builder()
        .school("중등")
        .subjectGrade("2-1")
        .subjectName("과목1")
        .publisher("Yoon Developer")
        .build();

    subjectMapper.insertSubject(subject);

    //when
    subjectMapper.deleteSubject(subject.getSubjectId());

    //then
    int isSubject = subjectMapper.existsSubjetct(subject.getSubjectId());

    if (isSubject != 0) {
      throw new RuntimeException("과목이 존재 합니다.");
    }
  }

}