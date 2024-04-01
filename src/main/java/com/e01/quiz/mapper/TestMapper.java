package com.e01.quiz.mapper;

import com.e01.quiz.dto.TestInputDTO;
import com.e01.quiz.dto.TestOutputDTO;
import com.e01.quiz.entity.Test;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TestMapper {
    @Mapping(target = "userId", source = "user.id")
    TestOutputDTO getTestOutputDtoFromTest(Test test);

    List<TestOutputDTO> getTestOutputDtosFromTests(List<Test> tests);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "title", ignore = true)
    @Mapping(target = "startTime", ignore = true)
    @Mapping(target = "questions", ignore = true)
    @Mapping(target = "numberOfLiveParticipant", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "duration", ignore = true)
    @Mapping(target = "code", ignore = true)
    Test getTestFromTestInputDto(TestInputDTO testInputDTO);



}
