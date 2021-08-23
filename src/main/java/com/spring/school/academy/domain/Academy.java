package com.spring.school.academy.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class Academy {

  public Academy(long academyId, String academyName, String description,
      LocalDateTime createdDate, LocalDateTime modifiedDate) {
    this.academyId = academyId;
    this.academyName = academyName;
    this.description = description;
    this.createdDate = createdDate;
    this.modifiedDate = modifiedDate;
  }

  private long academyId;

  private String academyName;

  private String description;

  private LocalDateTime createdDate;

  private LocalDateTime modifiedDate;
}
