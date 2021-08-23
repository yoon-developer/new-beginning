package com.spring.school.academy.dto;

import com.spring.school.academy.domain.Academy;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class AcademyResponseDto {

  public AcademyResponseDto(long academyId, String academyName, String description) {
    this.academyId = academyId;
    this.academyName = academyName;
    this.description = description;
  }

  private long academyId;

  private String academyName;

  private String description;

  public AcademyResponseDto toResponseDto(Academy academy) {
    return AcademyResponseDto.builder()
        .academyId(academy.getAcademyId())
        .academyName(academy.getAcademyName())
        .description(academy.getDescription())
        .build();
  }
}
