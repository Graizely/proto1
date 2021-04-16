package com.graizely.dices.repository;

import com.graizely.dices.entity.DiceRollSimulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiceRollSimulationRepository extends JpaRepository<DiceRollSimulation, Long> {


    @Query(value = "SELECT s.dices_count, s.dices_sides, i.sum, sum(i.distribution) from simulation s\n" +
            "LEFT JOIN simulation_incidence_mapping i ON s.id = i.simulation_id\n" +
            "GROUP BY  s.dices_count, s.dices_sides, i.sum\n" +
            "ORDER BY  s.dices_count, s.dices_sides, i.sum", nativeQuery = true)
    List<Object[]> getGroupedTotal();


    @Query(value = "SELECT s.dices_count, s.dices_sides, i.sum, sum(i.distribution) from simulation s\n" +
            "LEFT JOIN simulation_incidence_mapping i ON s.id = i.simulation_id\n" +
            "GROUP BY  s.dices_count, s.dices_sides, i.sum\n" +
            "HAVING s.dices_count = :dicesCount AND s.dices_sides = :dicesSides\n" +
            "ORDER BY  s.dices_count, s.dices_sides, i.sum\n", nativeQuery = true)
    List<Object[]> getGroupedTotal(
            @Param("dicesCount") Integer dicesCount,
            @Param("dicesSides") Integer dicesSides);
}
