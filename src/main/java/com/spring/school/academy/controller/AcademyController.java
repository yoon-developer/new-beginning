package com.spring.school.academy.controller;

import com.spring.school.academy.domain.Academy;
import com.spring.school.academy.dto.AcademyRequestDto;
import com.spring.school.academy.dto.AcademyResponseDto;
import com.spring.school.academy.service.AcademyService;
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

@Api(tags = "학원 API")
@RestController
@RequestMapping("/api/v1/academy")
public class AcademyController {

  private final AcademyService academyService;

  public AcademyController(AcademyService academyService) {
    this.academyService = academyService;
  }

  @GetMapping("/{academyId}")
  @ApiOperation(value = "학원 조회", notes = "학원을 조회를 할 수 있습니다.")
  public ResponseEntity<AcademyResponseDto> getAcademy(
      @PathVariable("academyId") @ApiParam(value = "학원 ID", required = true, defaultValue = "0") long academyId) {

    Academy academy = academyService.getAcademy(academyId);

    AcademyResponseDto academyResponseDto = AcademyResponseDto.builder()
        .academyId(academy.getAcademyId())
        .academyName(academy.getAcademyName())
        .description(academy.getDescription())
        .build();

    return ResponseEntity.ok().body(academyResponseDto);
  }

  @GetMapping
  @ApiOperation(value = "학원 전체 조회", notes = "학원 조회를 할 수 있습니다.")
  public ResponseEntity<List<AcademyResponseDto>> getAcademyList() {

    List<Academy> academyList = academyService.getAcademyList();

    List<AcademyResponseDto> academyResponseDtos = new ArrayList<>();
    for (Academy academy : academyList) {
      AcademyResponseDto academyResponseDto = new AcademyResponseDto().toResponseDto(academy);

      academyResponseDtos.add(academyResponseDto);
    }

    return ResponseEntity.ok().body(academyResponseDtos);
  }

  @PostMapping
  @ApiOperation(value = "학원 생성", notes = "학원을 생성할 수 있습니다.")
  public ResponseEntity<Long> addAcademy(
      @RequestBody @Valid @ApiParam(value = "학원 정보", required = true) AcademyRequestDto academyRequestDto) {

    Academy academy = Academy.builder()
        .academyName(academyRequestDto.getAcademyName())
        .description(academyRequestDto.getDescription())
        .build();

    academyService.addAcademy(academy);

    return ResponseEntity.ok().body(academy.getAcademyId());
  }

  @DeleteMapping("/{academyId}")
  @ApiOperation(value = "학원 삭제", notes = "학원을 삭제할 수 있습니다.")
  public ResponseEntity<Void> deleteAcademy(
      @PathVariable("academyId") @ApiParam(value = "학원 ID", required = true, defaultValue = "0") long academyId) {

    academyService.deleteAcademy(academyId);

    return ResponseEntity.ok().build();
  }
}
