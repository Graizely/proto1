package com.graizely.dices.controller;

import com.graizely.dices.DiceRollSimulationModelAssembler;
import com.graizely.dices.entity.DiceRollSimulation;
import com.graizely.dices.repository.DiceRollSimulationRepository;
import com.graizely.dices.services.DiceRollSimulator;
import lombok.NonNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class DiceRollSimulationController {

    // todo: repository already here? Use service!
    private final DiceRollSimulationRepository repository;
    private final DiceRollSimulator simulator;
    private final DiceRollSimulationModelAssembler assembler;

    DiceRollSimulationController(
            @NonNull DiceRollSimulationRepository repository,
            @NonNull DiceRollSimulator simulator,
            @NonNull DiceRollSimulationModelAssembler assembler) {

        this.repository = repository;
        this.simulator = simulator;
        this.assembler = assembler;
    }

    @GetMapping("/simulations")
    public CollectionModel<EntityModel<DiceRollSimulation>> all() {

//        var s1 = simulator.run(3,6, 100);
//        List<EntityModel<DiceRollSimulation>> simulations = Arrays.asList(s1).stream()
//                .map(assembler::toModel)
//                .collect(Collectors.toList());

        List<EntityModel<DiceRollSimulation>> simulations = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(simulations, linkTo(methodOn(DiceRollSimulationController.class).all()).withSelfRel());
    }

    @PostMapping("/simulations")
    DiceRollSimulation newDiceRollSimulation(@RequestBody DiceRollSimulation newDiceRollSimulation) {
        return repository.save(newDiceRollSimulation);
    }

    @GetMapping("/simulations/{id}")
    public EntityModel<DiceRollSimulation> one(@PathVariable Long id) {

        DiceRollSimulation simulation = repository.findById(id)
                .orElseThrow(() -> new DiceRollSimulationNotFoundException(id));

        return assembler.toModel(simulation);
    }

}
