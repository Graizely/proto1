package com.graizely.dices.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Table(name = "simulation")
public class DiceRollSimulation extends RepresentationModel<DiceRollSimulation> {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    private int dicesCount;
    private int dicesSides;

    @ElementCollection
    @CollectionTable(name = "simulation_incidence_mapping",
            joinColumns = {@JoinColumn(name = "simulation_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "sum")
    @Column(name = "distribution")
    private Map<Integer, Integer> distribution;

    public DiceRollSimulation(int dicesCount, int dicesSides, @NonNull Map<Integer, Integer> distribution) {
        this.dicesCount = dicesCount;
        this.dicesSides = dicesSides;
        this.distribution = distribution;
    }

}
