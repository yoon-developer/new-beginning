package com.spring.school.academy.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AcademyRequestDto {

  public AcademyRequestDto(String academyName, String description) {
    this.academyName = academyName;
    this.description = description;
  }

  @NotBlank(message = "이름은 공백일 수 없습니다")
  @ApiModelProperty(value = "학원 이름", required = true, example = "Yoon Developer 학원")
  private String academyName;

  @ApiModelProperty(value = "학원 설명")
  private String description;

}
