package com.spring.school.subject.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class Subject {

  public Subject(long subjectId, String subjectName, String school, String subjectGrade,
      String publisher, LocalDateTime createdDate) {
    this.subjectId = subjectId;
    this.subjectName = subjectName;
    this.school = school;
    this.subjectGrade = subjectGrade;
    this.publisher = publisher;
    this.createdDate = createdDate;
  }

  private long subjectId;

  private String subjectName;

  private String school;

  private String subjectGrade;

  private String publisher;

  private LocalDateTime createdDate;

}

