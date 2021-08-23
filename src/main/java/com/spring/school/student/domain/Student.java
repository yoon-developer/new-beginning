package com.spring.school.student.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class Student {

  public Student(long studentId, long academyId, String studentName,
      String studentGrade, String parentPhoneNumber, String schoolName, String birthday,
      String studentPhoneNumber, String homeAddress, String homePhoneNumber,
      LocalDateTime createdDate, LocalDateTime modifiedDate) {
    this.studentId = studentId;
    this.academyId = academyId;
    this.studentName = studentName;
    this.studentGrade = studentGrade;
    this.parentPhoneNumber = parentPhoneNumber;
    this.schoolName = schoolName;
    this.birthday = birthday;
    this.studentPhoneNumber = studentPhoneNumber;
    this.homeAddress = homeAddress;
    this.homePhoneNumber = homePhoneNumber;
    this.createdDate = createdDate;
    this.modifiedDate = modifiedDate;
  }

  private long studentId;

  private long academyId;

  private String studentName;

  private String studentGrade;

  private String parentPhoneNumber;

  private String schoolName;

  private String birthday;

  private String studentPhoneNumber;

  private String homeAddress;

  private String homePhoneNumber;

  private LocalDateTime createdDate;

  private LocalDateTime modifiedDate;
}
