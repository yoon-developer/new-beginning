package com.spring.school.student.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudentSubjectRequestDto {

  public StudentSubjectRequestDto(long subjectId) {
    this.subjectId = subjectId;
  }

  @ApiModelProperty(value = "과목 ID", required = true, example = "1111")
  private long subjectId;

  @Getter
  @NoArgsConstructor
  public static class StudentSubjectScoreReqeustDto extends StudentSubjectRequestDto {

    public StudentSubjectScoreReqeustDto(long subjectId, String score) {
      super(subjectId);
      this.score = score;
    }

    @ApiModelProperty(value = "과목 점수", example = "100")
    @Pattern(regexp = "^[1-9][0-9]?$|^100|^0$", message = "점수는 null 또는 0 ~ 100 사이로 입력하세요")
    private String score;
  }
}
