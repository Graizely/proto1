package com.graizely.dices.services;

import com.graizely.dices.entity.DiceRollSimulation;
import com.graizely.dices.model.Dice;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiceRollSimulator {

    public DiceRollSimulation run(int dices, int sides, int rolls) {

        Map<Integer, Integer> incidence = rollDices(dices, sides, rolls);
        return new DiceRollSimulation(dices, sides, incidence);
    }

    /**
     * @return Map with incidence of sums
     */
    private Map<Integer, Integer> rollDices(int dices, int sides, int rolls) {
        Map<Integer, Integer> result = new HashMap<>();

        Dice dice = new Dice(sides);

        for (int i = 0; i < rolls; i++) {
            int sum = 0;
            for (int j = 0; j < dices; j++) {
                sum += dice.roll();
            }

            result.put(sum, result.containsKey(sum) ? (result.get(sum) + 1) : 1);
        }

        return result;
    }
}
