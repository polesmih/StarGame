package com.star.app.game.things;

import com.badlogic.gdx.math.MathUtils;
import com.star.app.game.GameController;
import com.star.app.game.PickUp;

public class MedBox extends Thing {
    private static final int MULTIPLIER = 10;

    public MedBox(GameController gc) {
        super(gc);
    }

    @Override
    public void interact(PickUp pickUp) {
        pickUp.medication(getAmount());

    }

    public void setup () {
        super.setAmount(MathUtils.random(1, 3) * MULTIPLIER);

    }

    public void activate (float x, float y, float vx, float vy, float scale) {
        setup();
        super.activate(x, y, vx, vy, scale);
    }
}