package com.star.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.star.app.screen.ScreenManager;

public class StarGame extends Game {
	private SpriteBatch batch;
	private boolean isPaused;


	@Override
	public void create () {
		batch = new SpriteBatch();
		ScreenManager.getInstance().init(this,batch);
		ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.MENU);
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		getScreen().render(dt);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void pause() {
		isPaused = true;
	}
}
