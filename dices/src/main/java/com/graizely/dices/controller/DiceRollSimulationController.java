package com.graizely.dices.controller;

import com.graizely.dices.entity.DiceRollSimulation;
import com.graizely.dices.repository.DiceRollSimulationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiceRollSimulationController {

    // todo: repository already here?
    private final DiceRollSimulationRepository repository;

    DiceRollSimulationController(DiceRollSimulationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/simulations")
    List<DiceRollSimulation> all() {
        return repository.findAll();
    }

    @PostMapping("/simulations")
    DiceRollSimulation newDiceRollSimulation(@RequestBody DiceRollSimulation newDiceRollSimulation) {
        return repository.save(newDiceRollSimulation);
    }

    @GetMapping("/simulations/{id}")
    DiceRollSimulation one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new DiceRollSimulationNotFoundException(id));
    }

}
