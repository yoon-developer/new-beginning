package com.spring.school.academy.service;

import com.spring.school.academy.domain.Academy;
import java.util.List;

public interface AcademyService {

  List<Academy> getAcademyList();

  void addAcademy(Academy academy);

  void deleteAcademy(long academyId);

  Academy getAcademy(long academyId);
}
