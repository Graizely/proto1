package com.graizely.dices.services;

import com.graizely.dices.entity.DiceRollSimulation;
import com.graizely.dices.repository.DiceRollSimulationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DiceRollSimulationService {

    private final DiceRollSimulator simulator;
    private final DiceRollSimulationRepository repository;

    public DiceRollSimulation createSimulation(int dices, int sides, int rolls) {
        DiceRollSimulation simulation = simulator.run(dices, sides, rolls);
        repository.save(simulation);
        return simulation;
    }
}
