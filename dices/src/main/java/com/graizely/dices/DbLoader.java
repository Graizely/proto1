package com.graizely.dices;

import com.graizely.dices.repository.DiceRollSimulationRepository;
import com.graizely.dices.services.DiceRollSimulationService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Log4j2
@AllArgsConstructor
class DbLoader {

    private final DiceRollSimulationService simulatorService;
    private final DiceRollSimulationRepository repository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            log.info("Preloading " + simulatorService.createSimulation(3, 6, 100));
            log.info("Preloading " + simulatorService.createSimulation(3, 6, 200));
            log.info("Preloading " + simulatorService.createSimulation(5, 4, 100));

            List<Object[]> totals = repository.getGroupedTotal();
            log.info("total: " + totals);
        };

    }

}
