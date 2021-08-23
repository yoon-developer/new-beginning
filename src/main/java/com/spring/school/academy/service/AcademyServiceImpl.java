package com.spring.school.academy.service;

import com.spring.school.academy.domain.Academy;
import com.spring.school.academy.AcademyMapper;
import com.spring.school.student.StudentMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class AcademyServiceImpl implements AcademyService {

  private final AcademyMapper academyMapper;
  private final StudentMapper studentMapper;

  public AcademyServiceImpl(AcademyMapper academyMapper,
      StudentMapper studentMapper) {
    this.academyMapper = academyMapper;
    this.studentMapper = studentMapper;
  }

  @Override
  public Academy getAcademy(long academyId) {
    return academyMapper.getAcademy(academyId);
  }

  @Override
  public List<Academy> getAcademyList() {
    return academyMapper.getAcademyList();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void addAcademy(Academy academy) {
    academyMapper.insertAcademy(academy);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void deleteAcademy(long academyId) {

    int isAcademy = academyMapper.existsAcademy(academyId);

    if (isAcademy == 0) {
      throw new RuntimeException("존재 하지 않는 학원 입니다.");
    }

    int isStudent = studentMapper.existsAcademy(academyId);

    if (isStudent == 1) {
      throw new RuntimeException("학생을 삭제해주세요.");
    }

    academyMapper.deleteAcademy(academyId);
  }


}
