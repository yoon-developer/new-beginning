package com.spring.school.subject.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubjectRequestDto {

  public SubjectRequestDto(String subjectName, String school, String subjectGrade, String publisher) {
    this.subjectName = subjectName;
    this.school = school;
    this.subjectGrade = subjectGrade;
    this.publisher = publisher;
  }

  @NotBlank(message = "과목명은 공백일 수 없습니다")
  @ApiModelProperty(value = "과목명", required = true, example = "과목1")
  private String subjectName;

  @NotBlank(message = "학교명은 공백일 수 없습니다")
  @ApiModelProperty(value = "학교명", required = true, example = "중등")
  private String school;

  @NotBlank(message = "학년,학기 / 과목 은 공백일 수 없습니다")
  @ApiModelProperty(value = "학년,학기 / 과목", required = true, example = "2-1")
  private String subjectGrade;

  @ApiModelProperty(value = "출판사", example = "Yoon Developer")
  private String publisher;
}
