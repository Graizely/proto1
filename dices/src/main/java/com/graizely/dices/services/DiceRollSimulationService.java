
package com.graizely.dices.services;

import com.graizely.dices.entity.DiceRollSimulation;
import com.graizely.dices.model.DiceRollSimulationRelative;
import com.graizely.dices.repository.DiceRollSimulationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
@AllArgsConstructor
public class DiceRollSimulationService {

    private final DiceRollSimulator simulator;
    private final DiceRollSimulationRepository repository;

    public DiceRollSimulation createSimulation(int dices, int sides, int rolls) {
        DiceRollSimulation simulation = simulator.run(dices, sides, rolls);
        return repository.save(simulation);
    }

    public DiceRollSimulationRelative getSimulationRelative(int dices, int sides) {
        List<DiceRollSimulation> simulations = getSimulationTotals(dices, sides);
        if (simulations.isEmpty()) {
            return null;
        }

        return DiceRollSimulationRelative.from(simulations.get(0));
    }

    public List<DiceRollSimulation> getSimulationTotals(int dices, int sides) {

        List<Object[]> groupedResult = repository.getGroupedTotal(dices, sides);

        if (groupedResult.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return mapToDiceRollSimulations(groupedResult);
    }

    public List<DiceRollSimulation> getSimulationTotals() {

        List<Object[]> groupedResult = repository.getGroupedTotal();

        if (groupedResult.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return mapToDiceRollSimulations(groupedResult);
    }

    private List<DiceRollSimulation> mapToDiceRollSimulations(List<Object[]> groupedResult) {
        List<DiceRollSimulation> retVal = new ArrayList<>();

        DiceRollSimulation simulation = addSimulation(
                retVal,
                (int)(groupedResult.get(0))[0],
                (int)(groupedResult.get(0))[1]);

        for (Object[] row : groupedResult) {
            int diceCount = (int) row[0];
            int diceSides = (int) row[1];
            int sum = (int) row[2];
            int distribution = ((BigInteger) row[3]).intValue();

            if (simulation.getDicesCount() != diceCount || simulation.getDicesSides() != diceSides) {
                simulation = addSimulation(retVal, diceCount, diceSides);
            }

            simulation.getDistribution().put(sum, distribution);
        }
        return retVal;
    }

    private DiceRollSimulation addSimulation(List<DiceRollSimulation> retVal, int diceCount, int diceSides) {
        DiceRollSimulation simulation;
        simulation = new DiceRollSimulation();
        simulation.setDistribution(new HashMap<>());
        simulation.setDicesCount(diceCount);
        simulation.setDicesSides(diceSides);
        retVal.add(simulation);
        return simulation;
    }

}

