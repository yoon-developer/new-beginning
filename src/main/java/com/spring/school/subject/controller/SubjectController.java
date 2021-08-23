package com.spring.school.subject.controller;

import com.spring.school.subject.domain.Subject;
import com.spring.school.subject.dto.SubjectRequestDto;
import com.spring.school.subject.dto.SubjectResponseDto;
import com.spring.school.subject.service.SubjectService;
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

@Api(tags = "과목 API")
@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {

  private final SubjectService subjectService;

  public SubjectController(SubjectService subjectService) {
    this.subjectService = subjectService;
  }

  @GetMapping("/{subjectId}")
  @ApiOperation(value = "과목 조회", notes = "과목을 조회 할 수 있습니다.")
  public ResponseEntity<SubjectResponseDto> getSubjectList(
      @PathVariable("subjectId") @ApiParam(value = "과목 ID", required = true) long subjectId) {

    Subject subject = subjectService.getSubject(subjectId);

    SubjectResponseDto subjectResponseDto = new SubjectResponseDto().toResponseDto(subject);

    return ResponseEntity.ok().body(subjectResponseDto);
  }

  @GetMapping
  @ApiOperation(value = "과목 전체 조회", notes = "과목을 전체 조회 할 수 있습니다.")
  public ResponseEntity<List<SubjectResponseDto>> getSubjectList() {

    List<Subject> subjects = subjectService.getSubjetctList();

    List<SubjectResponseDto> subjectResponseDtos = new ArrayList<>();

    for (Subject subject : subjects) {
      SubjectResponseDto subjectResponseDto = new SubjectResponseDto().toResponseDto(subject);

      subjectResponseDtos.add(subjectResponseDto);
    }

    return ResponseEntity.ok().body(subjectResponseDtos);
  }

  @PostMapping
  @ApiOperation(value = "과목 생성", notes = "과목을 생성할 수 있습니다.")
  public ResponseEntity<Long> addSubject(
      @RequestBody @Valid @ApiParam(value = "과목 정보", required = true) SubjectRequestDto subjectRequestDto) {

    Subject subject = Subject.builder()
        .subjectName(subjectRequestDto.getSubjectName())
        .school(subjectRequestDto.getSchool())
        .subjectGrade(subjectRequestDto.getSubjectGrade())
        .publisher(subjectRequestDto.getPublisher())
        .build();

    subjectService.addSubject(subject);

    return ResponseEntity.ok().body(subject.getSubjectId());
  }

  @DeleteMapping("/{subjectId}")
  @ApiOperation(value = "과목 삭제", notes = "과목을 삭제할 수 있습니다.")
  public ResponseEntity<Long> deleteSubject(
      @PathVariable("subjectId") @ApiParam(value = "과목 ID", required = true) long subjectId) {

    subjectService.deleteSubject(subjectId);

    return ResponseEntity.ok().build();
  }


}
