package com.flappy.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.flappy.game.Flappy;

public class MenuState extends State {
    Texture backGround;
    Texture Button;
    com.badlogic.gdx.scenes.scene2d.ui.Button button;
    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        backGround=new Texture("backGround.png");
        Button=new Texture("playButton.png");
        camera.setToOrtho(false, Flappy.WIDTH/2,Flappy.HEIGHT/2);
    }

    @Override
    public void handleInput() {

        if(Gdx.input.justTouched()){
            gameStateManager.set(new PlayState(gameStateManager));
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(backGround,camera.position.x-camera.viewportWidth/2,0,Flappy.WIDTH,Flappy.HEIGHT/2);
        spriteBatch.draw(Button, camera.position.x -Button.getWidth()/2,camera.position.y,100,50
                );
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        backGround.dispose();
        Button.dispose();
    }
}
