package com.spring.school.student.controller;

import com.spring.school.student.domain.Student;
import com.spring.school.student.dto.StudentRequestDto;
import com.spring.school.student.dto.StudentResponseDto;
import com.spring.school.student.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "학생 API")
@RestController
@RequestMapping("/api/v1/academy")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping("/{academyId}/student/{studentId}")
  @ApiOperation(value = "학원 학생 조회", notes = "학원 학생을 조회할 수 있습니다.")
  public ResponseEntity<StudentResponseDto> getStudentList(
      @PathVariable("academyId") @ApiParam(value = "학원 ID", required = true, example = "1") long academyId,
      @PathVariable("studentId") @ApiParam(value = "학생 ID", required = true) long studentId) {

    Student student = studentService.getStudent(academyId, studentId);

    StudentResponseDto studentResponseDto = new StudentResponseDto().toResponseDto(student);


    return ResponseEntity.ok().body(studentResponseDto);
  }

  @GetMapping("/{academyId}/student")
  @ApiOperation(value = "학원 학생 전체 조회", notes = "학원 학생을 전체 조회할 수 있습니다.")
  public ResponseEntity<List<StudentResponseDto>> getStudentList(
      @PathVariable("academyId") @ApiParam(value = "학원 ID", required = true, example = "1") long academyId) {

    List<Student> students = studentService.getStudentList(academyId);

    List<StudentResponseDto> studentResponseDtos = new ArrayList<>();

    for (Student student : students) {
      StudentResponseDto studentResponseDto = new StudentResponseDto().toResponseDto(student);

      studentResponseDtos.add(studentResponseDto);
    }

    return ResponseEntity.ok().body(studentResponseDtos);
  }


  @PostMapping("/{academyId}/student")
  @ApiOperation(value = "학생 추가", notes = "학생을 추가할 수 있습니다.")
  public ResponseEntity<Long> addStudent(
      @PathVariable("academyId") @ApiParam(value = "학원 ID", required = true, example = "1") long academyId,
      @RequestBody @Valid @ApiParam(value = "학생 정보", required = true) StudentRequestDto studentRequestDto) {

    Student student = Student.builder()
        .academyId(academyId)
        .studentName(studentRequestDto.getStudentName())
        .studentGrade(studentRequestDto.getStudentGrade())
        .parentPhoneNumber(studentRequestDto.getParentPhoneNumber())
        .schoolName(studentRequestDto.getSchoolName())
        .birthday(studentRequestDto.getBirthday())
        .studentPhoneNumber(studentRequestDto.getStudentPhoneNumber())
        .homeAddress(studentRequestDto.getHomeAddress())
        .homePhoneNumber(studentRequestDto.getHomePhoneNumber())
        .build();

    studentService.addStudent(student);

    return ResponseEntity.ok().body(student.getStudentId());
  }

  @DeleteMapping("/{academyId}/student/{studentId}")
  @ApiOperation(value = "학생 삭제", notes = "학생을 삭제할 수 있습니다.")
  public ResponseEntity<Void> deleteStudent(
      @PathVariable("academyId") @ApiParam(value = "학원 ID", required = true, example = "1") long academyId,
      @PathVariable("studentId") @ApiParam(value = "학생 ID", required = true) long studentId) {

    studentService.deleteStudent(academyId, studentId);

    return ResponseEntity.ok().build();
  }

}
