package com.graizely.dices.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class DiceRollSimulation {
    private @Id @GeneratedValue Long id;
    private int dicesCount;
    private int dicesSides;
    private int rollsCount;

    public DiceRollSimulation() {
    }

    public DiceRollSimulation(int dicesCount, int dicesSides, int rollsCount) {
        this.dicesCount = dicesCount;
        this.dicesSides = dicesSides;
        this.rollsCount = rollsCount;
    }

    public Long getId() {
        return id;
    }

    public int getDicesCount() {
        return dicesCount;
    }

    public int getRollsCount() {
        return rollsCount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDicesCount(int dicesCount) {
        this.dicesCount = dicesCount;
    }

    public void setRollsCount(int rollsCount) {
        this.rollsCount = rollsCount;
    }

    public int getDicesSides() {
        return dicesSides;
    }

    public void setDicesSides(int dicesSides) {
        this.dicesSides = dicesSides;
    }
    @Override
    public String toString() {
        return "DiceRollSimulation{" +
                "id=" + id +
                ", dicesCount=" + dicesCount +
                ", rollsCount=" + rollsCount +
                ", dicesSides=" + dicesSides +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiceRollSimulation)) return false;
        DiceRollSimulation that = (DiceRollSimulation) o;
        return dicesCount == that.dicesCount && dicesSides == that.dicesSides && rollsCount == that.rollsCount && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dicesCount, dicesSides, rollsCount);
    }

}
