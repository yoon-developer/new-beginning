package com.spring.school.subject.dto;

import com.spring.school.subject.domain.Subject;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class SubjectResponseDto {

  public SubjectResponseDto(long subjectId, String subjectName, String school,
      String subjectGrade, String publisher) {
    this.subjectId = subjectId;
    this.subjectName = subjectName;
    this.school = school;
    this.subjectGrade = subjectGrade;
    this.publisher = publisher;
  }

  private long subjectId;

  private String subjectName;

  private String school;

  private String subjectGrade;

  private String publisher;

  public SubjectResponseDto toResponseDto(Subject subject) {
    return SubjectResponseDto.builder()
        .subjectId(subject.getSubjectId())
        .subjectName(subject.getSubjectName())
        .school(subject.getSchool())
        .subjectGrade(subject.getSubjectGrade())
        .publisher(subject.getPublisher())
        .build();
  }
}

