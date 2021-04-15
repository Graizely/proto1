package com.graizely.dices.controller;

import com.graizely.dices.entity.DiceRollSimulation;
import com.graizely.dices.repository.DiceRollSimulationRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class DiceRollSimulationController {

    // todo: repository already here? Use service!
    private final DiceRollSimulationRepository repository;

    DiceRollSimulationController(DiceRollSimulationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/simulations")
    CollectionModel<EntityModel<DiceRollSimulation>> all() {
        List<EntityModel<DiceRollSimulation>> simulations = repository.findAll().stream()
                .map(sim -> EntityModel.of(
                        sim,
                        linkTo(methodOn(DiceRollSimulationController.class).one(sim.getId())).withSelfRel(),
                        linkTo(methodOn(DiceRollSimulationController.class).all()).withRel("simulations")))
                .collect(Collectors.toList());

        return CollectionModel.of(simulations, linkTo(methodOn(DiceRollSimulationController.class).all()).withSelfRel());
    }

    @PostMapping("/simulations")
    DiceRollSimulation newDiceRollSimulation(@RequestBody DiceRollSimulation newDiceRollSimulation) {
        return repository.save(newDiceRollSimulation);
    }

    @GetMapping("/simulations/{id}")
    EntityModel<DiceRollSimulation> one(@PathVariable Long id) {

        DiceRollSimulation simulation = repository.findById(id)
                .orElseThrow(() -> new DiceRollSimulationNotFoundException(id));

        return EntityModel.of(
                simulation,
                linkTo(methodOn(DiceRollSimulationController.class).one(id)).withSelfRel(),
                linkTo(methodOn(DiceRollSimulationController.class).all()).withRel("simulations"));
    }

}
