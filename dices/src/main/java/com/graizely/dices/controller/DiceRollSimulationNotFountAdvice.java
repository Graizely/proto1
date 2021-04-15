package com.graizely.dices.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DiceRollSimulationNotFountAdvice {

    @ResponseBody
    @ExceptionHandler(DiceRollSimulationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String diceRollSimulationNotFoundHandler(DiceRollSimulationNotFoundException ex) {
        return ex.getMessage();
    }
}
