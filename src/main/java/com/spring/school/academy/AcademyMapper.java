package com.spring.school.academy;

import com.spring.school.academy.domain.Academy;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AcademyMapper {

  List<Academy> getAcademyList();

  int insertAcademy(Academy academy);

  int deleteAcademy(@Param("academyId") long academyId);;

  int existsAcademy(@Param("academyId")long academyId);

  Academy getAcademy(@Param("academyId") long academyId);
}
