package com.spring.school.student.service;

import com.spring.school.student.domain.Student;
import java.util.List;

public interface StudentService {

  void addStudent(Student student);

  void deleteStudent(long academyId, long studentId);

  List<Student> getStudentList(long academyId);

  Student getStudent(long academyId, long studentId);
}
