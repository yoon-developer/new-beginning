package com.spring.school.student.dto;

import com.spring.school.subject.dto.SubjectResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudentSubjectResponseDto {

  @Getter
  @Builder
  @NoArgsConstructor
  public static class StudentSubjectInfoResponseDto extends StudentSubjectResponseDto {

    public StudentSubjectInfoResponseDto(
        SubjectResponseDto subjectResponseDto) {
      this.subjectResponseDto = subjectResponseDto;
    }

    private SubjectResponseDto subjectResponseDto;
  }


  @Getter
  @Builder
  @NoArgsConstructor
  public static class StudentScoreSubjectResponseDto extends StudentSubjectResponseDto {

    public StudentScoreSubjectResponseDto(SubjectResponseDto subjectResponseDto, int score) {
      this.subjectResponseDto = subjectResponseDto;
      this.score = score;
    }

    private SubjectResponseDto subjectResponseDto;
    private int score;
  }

  @Getter
  @Builder
  @NoArgsConstructor
  public static class AllStudentSubjectAvgResponseDto extends StudentSubjectResponseDto {

    public AllStudentSubjectAvgResponseDto(SubjectResponseDto subjectResponseDto, int average) {
      this.subjectResponseDto = subjectResponseDto;
      this.average = average;
    }

    private SubjectResponseDto subjectResponseDto;
    private int average;
  }

  @Getter
  @Builder
  @NoArgsConstructor
  public static class StudentSubjectAvgResponseDto extends StudentSubjectResponseDto {

    public StudentSubjectAvgResponseDto(StudentResponseDto studentResponseDto, int average) {
      this.studentResponseDto = studentResponseDto;
      this.average = average;
    }

    private StudentResponseDto studentResponseDto;
    private int average;
  }

}
