package com.graizely.dices;

import com.google.common.collect.Lists;
import com.graizely.dices.controller.DiceRollSimulationController;
import com.graizely.dices.entity.DiceRollSimulation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DiceRollSimulationModelAssembler
        implements RepresentationModelAssembler<DiceRollSimulation, EntityModel<DiceRollSimulation>> {

    @Override
    public EntityModel<DiceRollSimulation> toModel(DiceRollSimulation simulation) {
        return EntityModel.of(simulation,
                linkTo(methodOn(DiceRollSimulationController.class).one(simulation.getId())).withSelfRel(),
                linkTo(methodOn(DiceRollSimulationController.class).all()).withRel("simulations"));
    }

    @Override
    public CollectionModel<EntityModel<DiceRollSimulation>> toCollectionModel(Iterable<? extends DiceRollSimulation> simulations) {
        List<EntityModel<DiceRollSimulation>> list = Lists.newArrayList(simulations)
                                                        .stream()
                                                        .map(this::toModel)
                                                        .collect(Collectors.toList());

        return CollectionModel.of(list, linkTo(methodOn(DiceRollSimulationController.class).all()).withSelfRel());
    }
}
