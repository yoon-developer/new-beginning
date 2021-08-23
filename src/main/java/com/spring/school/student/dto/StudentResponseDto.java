package com.spring.school.student.dto;

import com.spring.school.student.domain.Student;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class StudentResponseDto {

  public StudentResponseDto(long studentId, long academyId, String studentName,
      String studentGrade, String parentPhoneNumber, String schoolName, String birthday,
      String studentPhoneNumber, String homeAddress, String homePhoneNumber) {
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

  public StudentResponseDto toResponseDto(Student student) {
    return StudentResponseDto.builder()
        .studentId(student.getStudentId())
        .academyId(student.getAcademyId())
        .studentName(student.getStudentName())
        .studentGrade(student.getStudentGrade())
        .parentPhoneNumber(student.getParentPhoneNumber())
        .schoolName(student.getSchoolName())
        .birthday(student.getBirthday())
        .studentPhoneNumber(student.getStudentPhoneNumber())
        .homeAddress(student.getHomeAddress())
        .homePhoneNumber(student.getHomePhoneNumber())
        .build();
  }
}
