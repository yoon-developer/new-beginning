package com.spring.school.subject.service;

import com.spring.school.student.StudentSubjectMapper;
import com.spring.school.subject.SubjectMapper;
import com.spring.school.subject.domain.Subject;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class SubjectServiceImpl implements SubjectService {

  private final SubjectMapper subjectMapper;
  private final StudentSubjectMapper studentSubjectMapper;

  public SubjectServiceImpl(SubjectMapper subjectMapper,
      StudentSubjectMapper studentSubjectMapper) {
    this.subjectMapper = subjectMapper;
    this.studentSubjectMapper = studentSubjectMapper;
  }

  @Override
  public Subject getSubject(long subjectId) {
    return subjectMapper.getSubject(subjectId);
  }

  @Override
  public List<Subject> getSubjetctList() {
    return subjectMapper.getSubjetctList();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void addSubject(Subject subject) {
      subjectMapper.insertSubject(subject);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void deleteSubject(long subjectId) {

    int isSubjetct = subjectMapper.existsSubjetct(subjectId);

    if (isSubjetct == 0) {
      throw new RuntimeException("존재 하지 않는 과목 입니다.");
    }

    int isSubject = studentSubjectMapper.existsSubject(subjectId);

    if (isSubject == 1) {
      throw new RuntimeException("학생에 추가된 과목을 삭제해주세요.");
    }

    subjectMapper.deleteSubject(subjectId);
  }
}
