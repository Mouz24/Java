package com.example.api;

import com.example.api.Models.InitialNumber;
import com.example.api.Service.NumberRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NumberControllerTest {

    @ParameterizedTest
    @MethodSource ("readSource")
     void contextLoads(InitialNumber number) {
        //Arrange
        var result = 11;

        NumberRepository _repository = new NumberRepository();

        //Act
        var ConvertionResult = _repository.Convert(number);

        //Assert
        assertTrue(number != null);

        assertTrue(ConvertionResult == result);
    }

    private static Stream<Arguments> readSource() {
        return Stream.of(
                Arguments.of(new InitialNumber(3)),
                Arguments.of(new InitialNumber(3)));
    }
}