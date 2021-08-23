package com.spring.school.student;

import com.spring.school.student.domain.Student;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentMapper {

  int insertStudent(Student student);

  int deleteStudent(@Param("academyId") long academyId, @Param("studentId") long studentId);

  int existsStudent(@Param("studentId") long studentId);

  int existsAcademy(@Param("academyId") long academyId);

  List<Student> getStudentList(@Param("academyId") long academyId);

  Student getStudent(@Param("academyId") long academyId, @Param("studentId") long studentId);
}
