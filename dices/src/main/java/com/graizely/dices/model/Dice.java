package com.graizely.dices.model;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    private int sides;

    public Dice(int sides) {
        this.sides = sides;
    }

    public int roll() {
        return ThreadLocalRandom.current().nextInt(1, sides + 1);
    }
}
