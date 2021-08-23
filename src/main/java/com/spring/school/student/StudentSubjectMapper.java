package com.spring.school.student;

import com.spring.school.student.domain.StudentSubject;
import com.spring.school.student.domain.StudentSubject.AllStudentAvg;
import com.spring.school.student.domain.StudentSubject.StudentAvg;
import com.spring.school.student.domain.StudentSubject.StudentScore;
import com.spring.school.student.domain.StudentSubject.StudentSubjectInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentSubjectMapper {

  int insertStudentSubject(StudentSubject studentSubject);

  List<StudentScore> getStudentSubjectScoreList(@Param("studentId") long studentId);

  int updateStudentSubjectScore(StudentSubject studentSubject);

  StudentAvg getStudentAverage(@Param("studentId") long studentId);

  AllStudentAvg getAllStudentAverage(@Param("subjectId") long subjectId);

  int existsSubjectStudent(@Param("studentId") long studentId, @Param("subjectId") long subjectId);

  int existsSubject(@Param("subjectId") long subjectId);

  int existsStudent(@Param("studentId") long studentId);

  StudentScore getStudentSubjectScore(long subjectId, long studentId);

  List<StudentSubjectInfo> getStudentSubjectList(@Param("studentId") long studentId);

  void deleteStudentSubject(@Param("subjectId") long subjectId, @Param("studentId") long studentId);
}
