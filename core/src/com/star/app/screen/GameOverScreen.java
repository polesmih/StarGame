package com.star.app.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.StringBuilder;
import com.star.app.game.Background;
import com.star.app.game.Hero;
import com.star.app.screen.utils.Assets;

public class GameOverScreen extends AbstractScreen {
    private BitmapFont font72;
    private BitmapFont font24;
    private Stage stage;
    private Hero deadHero;
    private StringBuilder stringBuilder;
    private Background background;

    public GameOverScreen (SpriteBatch batch) {
        super(batch);
        this.stringBuilder = new StringBuilder();
    }

    public void setDeadHero(Hero deadHero) {
        this.deadHero = deadHero;
    }

    @Override
    public void show() {
        this.background = new Background(null);
        this.stage = new Stage(ScreenManager.getInstance().getViewport(), batch);
        this.font72 = Assets.getInstance().getAssetManager().get("fonts/font72.ttf");
        this.font24 = Assets.getInstance().getAssetManager().get("fonts/font24.ttf");
    }

    public void update(float dt) {
        background.update(dt);
        if (Gdx.input.justTouched()) {
            ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.MENU);
        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        ScreenUtils.clear(0.0f, 0.0f, 0.2f, 1);
        batch.begin();
        background.render(batch);
        font72.draw(batch, "Game Over", 0, 600, ScreenManager.SCREEN_WIDTH, Align.center, false);
        stringBuilder.clear();
        stringBuilder.append("Score Hero: ").append(deadHero.getScore()).append("\n");
        font24.draw(batch, "Return to menu", 0, 40, ScreenManager.SCREEN_WIDTH, Align.center, false);
        batch.end();

    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
