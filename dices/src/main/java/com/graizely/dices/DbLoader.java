package com.graizely.dices;

import com.graizely.dices.repository.DiceRollSimulationRepository;
import com.graizely.dices.services.DiceRollSimulationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
class DbLoader {

    private final DiceRollSimulationService simulatorService;

    DbLoader(DiceRollSimulationService simulatorService) {
        this.simulatorService = simulatorService;
    }

    @Bean
    CommandLineRunner initDatabase(DiceRollSimulationRepository repository) {
        return args -> {
            log.info("Preloading " + simulatorService.createSimulation(3, 6, 100));
            log.info("Preloading " + simulatorService.createSimulation(5, 4, 100));
        };

    }

}
