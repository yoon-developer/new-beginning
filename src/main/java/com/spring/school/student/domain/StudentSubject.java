package com.spring.school.student.domain;

import com.spring.school.subject.domain.Subject;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class StudentSubject {

  public StudentSubject(long studentId, long subjectId, String score, String createdDate,
      String modifiedDate) {
    this.studentId = studentId;
    this.subjectId = subjectId;
    this.score = score;
    this.createdDate = createdDate;
    this.modifiedDate = modifiedDate;
  }

  private long studentId;

  private long subjectId;

  private String score;

  private String createdDate;

  private String modifiedDate;

  @Getter
  @Builder
  @NoArgsConstructor
  public static class StudentSubjectInfo {

    public StudentSubjectInfo(Subject subject) {
      this.subject = subject;
    }

    private Subject subject;
  }

  @Getter
  @Builder
  @NoArgsConstructor
  public static class StudentScore {

    public StudentScore(Subject subject, int score) {
      this.subject = subject;
      this.score = score;
    }

    private Subject subject;

    private int score;
  }

  @Getter
  @Builder
  @NoArgsConstructor
  public static class AllStudentAvg {

    public AllStudentAvg(Subject subject, int average) {
      this.subject = subject;
      this.average = average;
    }

    private Subject subject;
    private int average;
  }

  @Getter
  @Builder
  @NoArgsConstructor
  public static class StudentAvg {

    public StudentAvg(Student student, int average) {
      this.student = student;
      this.average = average;
    }

    private Student student;
    private int average;
  }
}
