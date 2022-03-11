package com.star.app.game.things;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.star.app.game.GameController;
import com.star.app.game.PickUp;
import com.star.app.game.helpers.ObjectPool;

public class ThingsController extends ObjectPool<Thing> {
    private GameController gc;
    private Texture medBoxTexture;
    private Texture coinsTexture;
    private Texture ammunitionTexture;

    @Override
    protected Thing newObject() {
        return new Thing(gc) {
            @Override
            public void interact(PickUp pickUp) {

            }
        };
    }

    public ThingsController(GameController gc) {
        this.gc = gc;
        this.medBoxTexture = new Texture("");
        this.coinsTexture = new Texture("");
        this.ammunitionTexture = new Texture("");
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            Thing thing = activeList.get(i);
            thing.render(batch);
        }
    }

    public void setup(float x, float y, float vx, float vy, float scale){
        getActiveElement().activate(x, y, vx, vy, scale);
    }

    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
        checkPool();
    }

    private Thing getThing() {
        switch (MathUtils.random(0, 2)) {
            case 0:
                return new MedBox(gc);
            case 1:
                return new Coins(gc);
            case 2:
                return new Ammunition(gc);
            default:
                return null;
        }
    }
}
