package com.flappy.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappy.game.States.GameStateManager;
import com.flappy.game.States.MenuState;

public class Flappy extends ApplicationAdapter {
	public static final int HEIGHT=600;
	public static final int WIDTH=420;
	public static final String TITLE="Flappy Bird";
	GameStateManager gameStateManager;
	SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameStateManager=new GameStateManager();
		gameStateManager.push(new MenuState(gameStateManager));
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
