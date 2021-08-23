package com.spring.school.subject;

import com.spring.school.subject.domain.Subject;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubjectMapper {

  int insertSubject(Subject subject);

  int deleteSubject(long subjectId);

  int existsSubjetct(long subjectId);

  List<Subject> getSubjetctList();

  Subject getSubject(long subjectId);
}
