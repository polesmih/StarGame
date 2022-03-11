package com.star.app.game.things;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.star.app.game.GameController;
import com.star.app.game.PickUp;
import com.star.app.game.helpers.Poolable;

public abstract class Thing implements Poolable {

    private GameController gc;
    private int amount;
    private Vector2 position;
    private Vector2 velocity;
    private Circle hitArea;
    private Texture texture;
    private int with;
    private int high;
    private float scale;
    private StringBuilder sb;
    private boolean active;


    public Thing(GameController gc) {
        this.gc = gc;
        this.amount = 0;
        this.position = new Vector2();
        this.velocity = new Vector2();
//        this.texture = texture;
        this.with = texture.getWidth();
        this.high = texture.getHeight();
        this.scale = 0.0f;
        this.sb = new StringBuilder();
        this.active = false;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Circle getHitArea() {
        return hitArea;
    }

    public Texture getTexture() {
        return texture;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }

    public abstract void interact(PickUp pickUp);

    public void update(float dt) {
        position.mulAdd(velocity, dt);

        hitArea.setPosition(position);
    }

    public void activate(float x, float y, float vx, float vy, float scale) {
        position.set(x, y);
        velocity.set(vx, vy);
        hitArea.setPosition(position);
        this.scale = scale;
        hitArea.setRadius(with / 2 * 0.9f);
        active = true;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, with, high);

    }
}
