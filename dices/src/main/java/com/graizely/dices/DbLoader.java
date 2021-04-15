package com.graizely.dices;

import com.graizely.dices.entity.DiceRollSimulation;
import com.graizely.dices.repository.DiceRollSimulationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
class DbLoader {

    @Bean
    CommandLineRunner initDatabase(DiceRollSimulationRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new DiceRollSimulation(3, 6, 100)));
            log.info("Preloading " + repository.save(new DiceRollSimulation(5, 4, 100)));
        };

    }

}
