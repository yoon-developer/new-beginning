package com.spring.school.student.service;

import com.spring.school.student.StudentMapper;
import com.spring.school.student.domain.StudentSubject.StudentSubjectInfo;
import com.spring.school.subject.SubjectMapper;
import com.spring.school.student.StudentSubjectMapper;
import com.spring.school.student.domain.StudentSubject;
import com.spring.school.student.domain.StudentSubject.AllStudentAvg;
import com.spring.school.student.domain.StudentSubject.StudentAvg;
import com.spring.school.student.domain.StudentSubject.StudentScore;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class StudentSubjectServiceImpl implements StudentSubjectService {

  private final StudentSubjectMapper studentSubjectMapper;
  private final StudentMapper studentMapper;
  private final SubjectMapper subjectMapper;

  public StudentSubjectServiceImpl(
      StudentSubjectMapper studentSubjectMapper,
      StudentMapper studentMapper, SubjectMapper subjectMapper) {
    this.studentSubjectMapper = studentSubjectMapper;
    this.studentMapper = studentMapper;
    this.subjectMapper = subjectMapper;
  }

  @Override
  public List<StudentSubjectInfo> getStudentSubjectList(long studentId) {
    return studentSubjectMapper.getStudentSubjectList(studentId);
  }

  @Override
  public StudentScore getStudentSubjectScore(long subjectId, long studentId) {
    return studentSubjectMapper.getStudentSubjectScore(subjectId, studentId);
  }

  public List<StudentScore> getStudentSubjectScoreList(long studentId) {
    return studentSubjectMapper.getStudentSubjectScoreList(studentId);
  }

  @Override
  public AllStudentAvg getAllStudentAverage(long subjectId) {
    return studentSubjectMapper.getAllStudentAverage(subjectId);
  }

  @Override
  public StudentAvg getStudentAverage(long studentId) {
    return studentSubjectMapper.getStudentAverage(studentId);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void addStudentSubject(StudentSubject studentSubject) {

    int isStudent = studentMapper.existsStudent(studentSubject.getStudentId());

    if (isStudent == 0) {
      throw new RuntimeException("존재 하지 않는 학생 입니다.");
    }

    int isSubjetct = subjectMapper.existsSubjetct(studentSubject.getSubjectId());

    if (isSubjetct == 0) {
      throw new RuntimeException("존재 하지 않는 과목 입니다.");
    }

    int isSubjectStudent = studentSubjectMapper
        .existsSubjectStudent(studentSubject.getStudentId(), studentSubject.getSubjectId());

    if (isSubjectStudent == 1) {
      throw new RuntimeException("학생에게 과목이 존재 합니다.");
    }

    studentSubjectMapper.insertStudentSubject(studentSubject);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void updateStudentSubjectScore(StudentSubject studentSubject) {

    int isSubjectStudent = studentSubjectMapper
        .existsSubjectStudent(studentSubject.getStudentId(), studentSubject.getSubjectId());

    if (isSubjectStudent == 0) {
      throw new RuntimeException("학생에게 과목이 존재하지 않습니다.");
    }

    studentSubjectMapper.updateStudentSubjectScore(studentSubject);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void deleteStudentSubject(long subjectId, long studentId) {

    int isSubjectStudent = studentSubjectMapper.existsSubjectStudent(studentId, subjectId);

    if (isSubjectStudent == 0) {
      throw new RuntimeException("학생에게 존재 하지 않는 과목 입니다.");
    }

    studentSubjectMapper.deleteStudentSubject(subjectId, studentId);
  }
}
