package com.spring.school.academy.service;

import com.spring.school.academy.AcademyMapper;
import com.spring.school.academy.domain.Academy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class AcademyServiceImplTest {

  @Autowired
  AcademyMapper academyMapper;

  @Test
  @DisplayName("학원 생성")
  public void addAcademy() throws Exception {
    //given
    Academy academy = Academy.builder()
        .academyName("academy")
        .description("description")
        .build();

    //when
    academyMapper.insertAcademy(academy);

    //then
    Academy findAcademy = academyMapper.getAcademy(academy.getAcademyId());

    Assertions.assertEquals(academy.getAcademyName(), findAcademy.getAcademyName());
    Assertions.assertEquals(academy.getDescription(), findAcademy.getDescription());
  }

  @Test
  @DisplayName("학원 삭제")
  public void deleteAcademy() throws Exception {
    //given
    Academy academy = Academy.builder()
        .academyName("academy")
        .description("description")
        .build();

    academyMapper.insertAcademy(academy);

    //when
    academyMapper.deleteAcademy(academy.getAcademyId());

    //then
    int isAcademy = academyMapper.existsAcademy(academy.getAcademyId());

    if (isAcademy != 0) {
      throw new RuntimeException("학원이 존재 합니다.");
    }
  }
}