package com.graizely.dices.controller;

public class DiceRollSimulationNotFoundException extends RuntimeException {
    public DiceRollSimulationNotFoundException(Long id) {
        super("Could not find simulation " + id);
    }
}
