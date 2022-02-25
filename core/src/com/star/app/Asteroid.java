package com.star.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import static com.star.app.ScreenManager.SCREEN_HEIGHT;
import static com.star.app.ScreenManager.SCREEN_WIDTH;

public class Asteroid {
    private Texture texture;
    private Vector2 position;
    private Vector2 velocity;
    private int high;
    private int with;
    private float angle;

    public Asteroid() {
        this.texture = new Texture("asteroid.png"); // отрисовка изображения астероида
        this.high = texture.getHeight();
        this.with = texture.getWidth();
        this.position = new Vector2(0, 0);
        this.angle = 0.0f;

        this.velocity = new Vector2(MathUtils.random(-40, -5), 15); // скорость и направление движения астероида

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - with/2, position.y - high/2,
                with/2, high/2, with, high, 1, 1, angle,
                0, 0, with, high, false, false);

    }

    public void update(float dt) {
        position.x += velocity.x * 10 * dt; // проход по оси за секунду
        position.y += velocity.y * 10 * dt;

        if (position.x < -with) {
            position.x = SCREEN_WIDTH + with / 2;
            angle = (float) MathUtils.random(110, 250);
        } else if (position.y > SCREEN_HEIGHT + high) {
            position.y = -high / 2;
            angle = (float) MathUtils.random(20, 160);
        } else if (position.x > SCREEN_WIDTH + with) {
            position.x = -with / 2;
        }

    }

}
