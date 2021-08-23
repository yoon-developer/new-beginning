package com.spring.school.student.service;

import com.spring.school.student.domain.StudentSubject;

import com.spring.school.student.domain.StudentSubject.AllStudentAvg;
import com.spring.school.student.domain.StudentSubject.StudentAvg;
import com.spring.school.student.domain.StudentSubject.StudentScore;
import com.spring.school.student.domain.StudentSubject.StudentSubjectInfo;
import java.util.List;

public interface StudentSubjectService {

  void addStudentSubject(StudentSubject studentSubject);

  List<StudentSubject.StudentScore> getStudentSubjectScoreList(long studentId);

  AllStudentAvg getAllStudentAverage(long subjectId);

  StudentAvg getStudentAverage(long studentId);

  void updateStudentSubjectScore(StudentSubject studentSubject);

  StudentScore getStudentSubjectScore(long subjectId, long studentId);

  List<StudentSubjectInfo> getStudentSubjectList(long studentId);

  void deleteStudentSubject(long subjectId, long studentId);
}
