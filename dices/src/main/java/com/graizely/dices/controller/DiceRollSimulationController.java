package com.graizely.dices.controller;

import com.graizely.dices.DiceRollSimulationModelAssembler;
import com.graizely.dices.entity.DiceRollSimulation;
import com.graizely.dices.repository.DiceRollSimulationRepository;
import com.graizely.dices.services.DiceRollSimulationService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class DiceRollSimulationController {

    // todo: repository already here? Use service!
    private final DiceRollSimulationRepository repository;
    private final DiceRollSimulationModelAssembler assembler;
    private final DiceRollSimulationService service;

    @GetMapping("/simulation")
    public CollectionModel<EntityModel<DiceRollSimulation>> all() {

        List<EntityModel<DiceRollSimulation>> simulations = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(simulations, linkTo(methodOn(DiceRollSimulationController.class).all()).withSelfRel());
    }

    @GetMapping("/total")
    public CollectionModel<EntityModel<DiceRollSimulation>> total(
            @RequestParam(value = "dicesCount", required = false) Integer dices,
            @RequestParam(value = "dicesSides", required = false) Integer sides) {

        List<EntityModel<DiceRollSimulation>> simulations;

        if (dices != null && sides != null) {
            // todo
            simulations = service.getSimulationTotals(dices, sides).stream()
                    .map(assembler::toTotalModel)
                    .collect(Collectors.toList());;
        }
        // todo check single param?
        else {
            simulations = service.getSimulationTotals().stream()
                    .map(assembler::toTotalModel)
                    .collect(Collectors.toList());
        }

        return CollectionModel.of(simulations, linkTo(methodOn(DiceRollSimulationController.class).total(null, null)).withSelfRel());
    }
    /**
     * Assignment 1
     * @param dices
     * @param sides
     * @param rolls
     * @return
     */
    // todo Input validation
    @PostMapping("/simulation")
    EntityModel<DiceRollSimulation> newDiceRollSimulation(
            @RequestParam(value = "dices", defaultValue = "3") int dices,
            @RequestParam(value = "sides", defaultValue = "6") int sides,
            @RequestParam(value = "rolls", defaultValue = "100") int rolls) {

        DiceRollSimulation simulation = service.createSimulation(dices, sides, rolls);

        // todo return from repo bc. of ID? seems not

        return assembler.toModel(simulation); //repository.save(newDiceRollSimulation);
    }

    @GetMapping("/simulation/{id}")
    public EntityModel<DiceRollSimulation> one(@PathVariable Long id) {

        DiceRollSimulation simulation = repository.findById(id)
                .orElseThrow(() -> new DiceRollSimulationNotFoundException(id));

        return assembler.toModel(simulation);
    }

}
