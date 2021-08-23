package com.spring.school.student.controller;

import com.spring.school.student.domain.StudentSubject;
import com.spring.school.student.domain.StudentSubject.AllStudentAvg;
import com.spring.school.student.domain.StudentSubject.StudentAvg;
import com.spring.school.student.domain.StudentSubject.StudentScore;
import com.spring.school.student.domain.StudentSubject.StudentSubjectInfo;
import com.spring.school.student.dto.StudentSubjectRequestDto;
import com.spring.school.student.dto.StudentSubjectRequestDto.StudentSubjectScoreReqeustDto;
import com.spring.school.student.dto.StudentSubjectResponseDto.AllStudentSubjectAvgResponseDto;
import com.spring.school.student.dto.StudentSubjectResponseDto.StudentSubjectAvgResponseDto;
import com.spring.school.student.dto.StudentSubjectResponseDto.StudentSubjectInfoResponseDto;
import com.spring.school.student.service.StudentSubjectService;
import com.spring.school.student.dto.StudentResponseDto;
import com.spring.school.subject.dto.SubjectResponseDto;
import com.spring.school.student.dto.StudentSubjectResponseDto.StudentScoreSubjectResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "학생 과목 API")
@RestController
@RequestMapping("/api/v1/student")
public class StudentSubjectController {

  private final StudentSubjectService studentSubjectService;

  public StudentSubjectController(StudentSubjectService studentSubjectService) {
    this.studentSubjectService = studentSubjectService;
  }

  @GetMapping("/{studentId}/subject")
  @ApiOperation(value = "학생 과목 전체 조회", notes = "학생의 과목 전체 조회 할 수 있습니다.")
  public ResponseEntity<List<StudentSubjectInfoResponseDto>> getStudentSubject(
      @PathVariable("studentId") @ApiParam(value = "학생 ID", required = true) long studentId) {

    List<StudentSubjectInfo> studentSubjectInfos = studentSubjectService.getStudentSubjectList(studentId);

    List<StudentSubjectInfoResponseDto> studentSubjectInfoResponseDtos = new ArrayList<>();

    for (StudentSubjectInfo studentSubjectInfo : studentSubjectInfos) {
      SubjectResponseDto subjectResponseDto = new SubjectResponseDto()
          .toResponseDto(studentSubjectInfo.getSubject());

      StudentSubjectInfoResponseDto  studentSubjectInfoResponseDto = StudentSubjectInfoResponseDto.builder()
          .subjectResponseDto(subjectResponseDto)
          .build();

      studentSubjectInfoResponseDtos.add(studentSubjectInfoResponseDto);
    }

    return new ResponseEntity<>(studentSubjectInfoResponseDtos, HttpStatus.OK);
  }

  @GetMapping("/{studentId}/subject/{subjectId}/score")
  @ApiOperation(value = "학생 점수 조회", notes = "학생의 점수를 조회 할 수 있습니다.")
  public ResponseEntity<StudentScoreSubjectResponseDto> getStudentSubjectScore(
      @PathVariable("subjectId") @ApiParam(value = "과목 ID", required = true) long subjectId,
      @PathVariable("studentId") @ApiParam(value = "학생 ID", required = true) long studentId) {

    StudentScore studentScore = studentSubjectService.getStudentSubjectScore(subjectId, studentId);

    SubjectResponseDto subjectResponseDto = new SubjectResponseDto()
        .toResponseDto(studentScore.getSubject());

    StudentScoreSubjectResponseDto studentScoreResponseDto = StudentScoreSubjectResponseDto
        .builder()
        .score(studentScore.getScore())
        .subjectResponseDto(subjectResponseDto)
        .build();

    return new ResponseEntity<>(studentScoreResponseDto, HttpStatus.OK);
  }


  @GetMapping("/{studentId}/subject/score")
  @ApiOperation(value = "학생 점수 전체 조회", notes = "학생의 점수를 전체 조회 할 수 있습니다.")
  public ResponseEntity<List<StudentScoreSubjectResponseDto>> getStudentSubjectScoreList(
      @PathVariable("studentId") @ApiParam(value = "학생 ID", required = true) long studentId) {

    List<StudentScore> studentScores = studentSubjectService.getStudentSubjectScoreList(studentId);

    List<StudentScoreSubjectResponseDto> studentScoreResponseDtos = new ArrayList<>();

    for (StudentScore studentScore : studentScores) {
      SubjectResponseDto subjectResponseDto = new SubjectResponseDto()
          .toResponseDto(studentScore.getSubject());

      StudentScoreSubjectResponseDto studentScoreResponseDto = StudentScoreSubjectResponseDto
          .builder()
          .score(studentScore.getScore())
          .subjectResponseDto(subjectResponseDto)
          .build();

      studentScoreResponseDtos.add(studentScoreResponseDto);
    }

    return new ResponseEntity<>(studentScoreResponseDtos, HttpStatus.OK);
  }

  @GetMapping("/{studentId}/subject/average")
  @ApiOperation(value = "학생 평균 점수 조회", notes = "학생의 평균 점수를 조회 할 수 있습니다.")
  public ResponseEntity<StudentSubjectAvgResponseDto> getStudentSubjectAvg(
      @PathVariable("studentId") @ApiParam(value = "학생 ID", required = true) long studentId) {

    StudentAvg studentAvg = studentSubjectService
        .getStudentAverage(studentId);

    StudentResponseDto studentResponseDto = new StudentResponseDto()
        .toResponseDto(studentAvg.getStudent());

    StudentSubjectAvgResponseDto studentAvgResponseDto = StudentSubjectAvgResponseDto
        .builder()
        .studentResponseDto(studentResponseDto)
        .average(studentAvg.getAverage())
        .build();

    return new ResponseEntity<>(studentAvgResponseDto, HttpStatus.OK);
  }

  @GetMapping("/subject/{subjectId}/average")
  @ApiOperation(value = "전체 학생 특정 과목 평균 점수 조회", notes = "전체 학생의 특정 과목 평균 점수를 조회 할 수 있습니다.")
  public ResponseEntity<AllStudentSubjectAvgResponseDto> getAllStudentSubjectAvg(
      @PathVariable("subjectId") @ApiParam(value = "과목 ID", required = true) long subjectId) {

    AllStudentAvg allStudentAvg = studentSubjectService
        .getAllStudentAverage(subjectId);

    SubjectResponseDto subjectResponseDto = new SubjectResponseDto()
        .toResponseDto(allStudentAvg.getSubject());

    AllStudentSubjectAvgResponseDto allStudentAvgResponseDto = AllStudentSubjectAvgResponseDto
        .builder()
        .subjectResponseDto(subjectResponseDto)
        .average(allStudentAvg.getAverage())
        .build();

    return new ResponseEntity<>(allStudentAvgResponseDto, HttpStatus.OK);
  }

  @PostMapping("/{studentId}")
  @ApiOperation(value = "학생 과목 추가", notes = "학생에게 과목을 추가 할 수 있습니다.")
  public ResponseEntity<Void> addStudentSubject(
      @PathVariable("studentId") @ApiParam(value = "학생 ID", required = true, example = "1111") long studentId,
      @RequestBody @Valid @ApiParam(value = "학생에게 추가할 과목 정보", required = true) StudentSubjectRequestDto studentSubjectRequestDto) {

    StudentSubject studentSubject = StudentSubject.builder()
        .subjectId(studentSubjectRequestDto.getSubjectId())
        .studentId(studentId)
        .score(null)
        .build();

    studentSubjectService.addStudentSubject(studentSubject);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/{studentId}")
  @ApiOperation(value = "학생 과목 점수 변경", notes = "학생의 과목 점수를 변경 할 수 있습니다.")
  public ResponseEntity<StudentSubjectScoreReqeustDto> changeStudentSubjectScore(
      @PathVariable("studentId") @ApiParam(value = "학생 ID", required = true, example = "1111") long studentId,
      @RequestBody @Valid @ApiParam(value = "학생의 과목 정보", required = true) StudentSubjectRequestDto.StudentSubjectScoreReqeustDto studentScoreReqeustDto) {

    StudentSubject studentSubject = StudentSubject
        .builder()
        .studentId(studentId)
        .subjectId(studentScoreReqeustDto.getSubjectId())
        .score(studentScoreReqeustDto.getScore())
        .build();

    studentSubjectService.updateStudentSubjectScore(studentSubject);

    return new ResponseEntity<>(studentScoreReqeustDto, HttpStatus.OK);
  }

  @DeleteMapping("/{studentId}/subject/{subjectId}")
  @ApiOperation(value = "학생 과목 삭제", notes = "학생의 과목을 삭제 할 수 있습니다.")
  public ResponseEntity<Void> deleteStudentSubject(
      @PathVariable("studentId") @ApiParam(value = "학생 ID", required = true) long studentId,
      @PathVariable("subjectId") @ApiParam(value = "과목 ID", required = true) long subjectId) {

    studentSubjectService.deleteStudentSubject(subjectId, studentId);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
