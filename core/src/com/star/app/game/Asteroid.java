package com.star.app.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.star.app.game.helpers.Poolable;
import com.star.app.screen.ScreenManager;

public class Asteroid implements Poolable {

    private Texture texture;
    private Vector2 position;
    private Vector2 velocity;
    private float rotAngle;
    private float rotSpeed;
    private int height;
    private int width;
    private int weight;
    private final static int MAX_WEIGHT = 10;
    private final static int MIN_WEIGHT = 1;
    private final static int LOWEST_SPEED = 120;
    private Circle hitArea;
    private float sizeFactor;

    private int imgWidth;
    private int imgHeight;

    private boolean active;

    public Asteroid(int weight) {
        this.weight = weight;
        sizeFactor = (float)weight / MAX_WEIGHT;

        texture = new Texture("asteroid.png");
        imgHeight = texture.getHeight();
        imgWidth = texture.getWidth();
        height = MathUtils.round(imgHeight * sizeFactor);
        width = MathUtils.round(imgWidth * sizeFactor);

        position = new Vector2();
        velocity = new Vector2();
        rotAngle = 0;
        hitArea = new Circle(position, width / 2);

        initialize();
        active = false;

    }

    public Vector2 getPosition() {
        return position;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void activate(float x, float y, float vx, float vy) {
        active = true;
    }

    public void deactivate() {
        active = false;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - imgWidth / 2,
                position.y - imgHeight / 2, imgWidth / 2, imgHeight / 2,
                imgWidth, imgHeight, sizeFactor, sizeFactor, rotAngle, 0, 0,
                imgWidth, imgHeight, false, false);
    }

    public void update(float dt) {
        position.mulAdd(velocity, dt);

        if (position.x < -width) {
            // left
            position.x = ScreenManager.SCREEN_WIDTH + width / 2;
        } else if (position.y > ScreenManager.SCREEN_HEIGHT + height) {
            // top
            position.y = -height / 2;
        } else if (position.x > ScreenManager.SCREEN_WIDTH + width) {
            // right
            position.x = -width / 2;
        } else if (position.y < -height) {
            // down
            position.y = ScreenManager.SCREEN_HEIGHT + height / 2;
        }

        rotAngle = (rotAngle - 2.5f * (0.1f + sizeFactor -1)) % 360;
        hitArea.setPosition(position);
    }

    private void initialize() {
        float dirAngle = 0f;
        switch (MathUtils.random(0, 3)) {
            case 0: // LEFT
                position.x = -width / 2;
                position.y = MathUtils.random(0, ScreenManager.SCREEN_HEIGHT);
                dirAngle = (float)MathUtils.random(-70, 70);
                break;
            case 1: // TOP
                position.x = MathUtils.random(0, ScreenManager.SCREEN_WIDTH);
                position.y = ScreenManager.SCREEN_HEIGHT + height / 2;
                dirAngle = (float)MathUtils.random(200, 340);
                break;
            case 2: // RIGHT
                position.x = ScreenManager.SCREEN_WIDTH + width / 2;
                position.y = MathUtils.random(0, ScreenManager.SCREEN_HEIGHT);
                dirAngle = (float)MathUtils.random(110, 250);
                break;
            case 3: // BOTTOM
                position.x = MathUtils.random(0, ScreenManager.SCREEN_WIDTH);
                position.y = -height / 2;
                dirAngle = (float)MathUtils.random(20, 160);
                break;
            default:
                break;
        }

        velocity.set((float) Math.cos(Math.toRadians(dirAngle)) *
                        LOWEST_SPEED / sizeFactor,
                (float) Math.sin(Math.toRadians(dirAngle)) *
                        LOWEST_SPEED / sizeFactor);
    }

    public boolean isDamage(Vector2 p) {
        boolean result = false;
        if (hitArea.contains(p)) {
            result = true;
        }
        return result;
    }

}
