package com.spring.school.student.service;

import com.spring.school.academy.AcademyMapper;
import com.spring.school.student.StudentMapper;
import com.spring.school.student.StudentSubjectMapper;
import com.spring.school.student.domain.Student;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

  private final StudentMapper studentMapper;
  private final AcademyMapper academyMapper;
  private final StudentSubjectMapper studentSubjectMapper;

  public StudentServiceImpl(StudentMapper studentMapper,
      AcademyMapper academyMapper,
      StudentSubjectMapper studentSubjectMapper) {
    this.studentMapper = studentMapper;
    this.academyMapper = academyMapper;
    this.studentSubjectMapper = studentSubjectMapper;
  }

  @Override
  public Student getStudent(long academyId, long studentId) {
    return studentMapper.getStudent(academyId, studentId);
  }

  @Override
  public List<Student> getStudentList(long academyId) {
    return studentMapper.getStudentList(academyId);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void addStudent(Student student){

    int isAcademy = academyMapper.existsAcademy(student.getAcademyId());

    if (isAcademy == 0) {
      throw new RuntimeException("존재 하지 않는 학원 입니다.");
    }

    studentMapper.insertStudent(student);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void deleteStudent(long academyId,long studentId) {

    int isStudent = studentMapper.existsStudent(studentId);

    if (isStudent == 0) {
      throw new RuntimeException("존재 하지 않는 학원 학생 입니다.");
    }

    int isStudentSubject = studentSubjectMapper.existsStudent(studentId);

    if (isStudentSubject == 1) {
      throw new RuntimeException("학생에 추가된 과목을 삭제해주세요.");
    }

    studentMapper.deleteStudent(academyId, studentId);
  }
}
