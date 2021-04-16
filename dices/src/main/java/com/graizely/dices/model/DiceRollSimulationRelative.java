package com.graizely.dices.model;

import com.graizely.dices.entity.DiceRollSimulation;
import lombok.Data;

import java.util.Map;
import java.util.stream.Collectors;

@Data
public class DiceRollSimulationRelative {

    public static DiceRollSimulationRelative from (DiceRollSimulation simulation) {
        DiceRollSimulationRelative retVal = new DiceRollSimulationRelative();
        retVal.dicesCount = simulation.getDicesCount();
        retVal.dicesSides = simulation.getDicesSides();

        int totalCount = simulation.getDistribution().values().stream().mapToInt(Integer::intValue).sum();
        Map<Integer, Double> relativeDistribution = simulation.getDistribution().entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> (100 * (double) e.getValue() / totalCount)
                ));

        retVal.distribution = relativeDistribution;
        return retVal;
    }

    private int dicesCount;
    private int dicesSides;
    private Map<Integer, Double> distribution;
}
