package com.spring.school.subject.service;

import com.spring.school.subject.domain.Subject;
import java.util.List;

public interface SubjectService {

  void addSubject(Subject subject);

  void deleteSubject(long subjectId);

  List<Subject> getSubjetctList();

  Subject getSubject(long subjectId);
}
