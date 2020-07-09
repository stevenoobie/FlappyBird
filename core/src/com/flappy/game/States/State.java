package com.flappy.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    OrthographicCamera camera;
    Vector3 mouse;
    GameStateManager gameStateManager;
    State(GameStateManager gameStateManager){
        this.gameStateManager=gameStateManager;
        mouse=new Vector3();
        camera=new OrthographicCamera();
    }
    public abstract void handleInput();
    public abstract void update(float deltaTime);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void dispose();
}
