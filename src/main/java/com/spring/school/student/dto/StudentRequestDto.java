package com.spring.school.student.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudentRequestDto {

  public StudentRequestDto(String studentName, String studentGrade, String parentPhoneNumber,
      String schoolName, String birthday, String studentPhoneNumber, String homeAddress,
      String homePhoneNumber) {
    this.studentName = studentName;
    this.studentGrade = studentGrade;
    this.parentPhoneNumber = parentPhoneNumber;
    this.schoolName = schoolName;
    this.birthday = birthday;
    this.studentPhoneNumber = studentPhoneNumber;
    this.homeAddress = homeAddress;
    this.homePhoneNumber = homePhoneNumber;
  }

  @NotBlank(message = "이름은 공백일 수 없습니다")
  @ApiModelProperty(value = "학생 이름", required = true, example = "홍길동")
  private String studentName;

  @NotBlank(message = "학년은 공백일 수 없습니다")
  @ApiModelProperty(value = "학년", required = true, example = "중1")
  private String studentGrade;

  @NotBlank(message = "휴대폰 번호는 공백일 수 없습니다")
  @ApiModelProperty(value = "학부모 휴대폰", required = true, example = "01012345678")
  @Pattern(regexp = "^\\d{10,11}$", message = "휴대폰 번호는 01012345678 형태로 표기하세요")
  private String parentPhoneNumber;

  @ApiModelProperty(value = "학교명", example = "Yoon Developer")
  private String schoolName;

  @ApiModelProperty(value = "학생 생년월일", example = "20210101")
  @Pattern(regexp = "^\\d{8}$", message = "학생 생년월일은 20210101 형태로 표기하세요")
  private String birthday;

  @ApiModelProperty(value = "학생 휴대폰 번호", example = "01012344567")
  @Pattern(regexp = "^\\d{10,11}$", message = "휴대폰 번호는 01012345678 형태로 표기하세요")
  private String studentPhoneNumber;

  @ApiModelProperty(value = "집 주소", example = "서울시")
  private String homeAddress;

  @ApiModelProperty(value = "집 전화", example = "0123456789")
  @Pattern(regexp = "^\\d{10,11}$", message = "전화 번호는 0123456789 형태로 표기하세요 (지역번호 표기)")
  private String homePhoneNumber;
}
