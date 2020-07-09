package com.flappy.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappy.game.Flappy;

public class GameOverState extends State {
    Texture backGround;
    Texture Button;
    Texture texture;
    Texture texture1;
    Texture texture2;
    Texture New;
    int Score;
    int highScore;
    boolean newScore;
    GameOverState(GameStateManager gameStateManager) {
        super(gameStateManager);
        texture=new Texture("badlogic.jpg");
        texture1=new Texture("0.png");
        texture2=new Texture("0.png");
        New=new Texture("newscore.png");
        backGround=new Texture("backGround.png");
        Button=new Texture("gameover.png");
        camera.setToOrtho(false, Flappy.WIDTH/2,Flappy.HEIGHT/2);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gameStateManager.set(new PlayState(gameStateManager));
            dispose();
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
        spriteBatch.draw(Button, camera.position.x -Button.getWidth()/2+40,camera.position.y,120,50
        );

        if(newScore) {
            spriteBatch.draw(New, camera.position.x - New.getWidth() +160, camera.position.y - New.getHeight() + 70, 40, 40);
        }
        Draw(spriteBatch,camera.position.x-3,camera.position.y-50);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        backGround.dispose();
        Button.dispose();
        texture1.dispose();
        texture.dispose();
        texture2.dispose();
        New.dispose();
    }
    public void Draw(SpriteBatch spriteBatch,float x,float y){

        if(Score<10){
            switch (Score){
                case 0: texture=new Texture("0.png");break;
                case 1:texture=new Texture("1.png");break;
                case 2: texture=new Texture("2.png");break;
                case 3: texture=new Texture("3.png");break;
                case 4: texture=new Texture("4.png");break;
                case 5: texture=new Texture("5.png");break;
                case 6: texture=new Texture("6.png");break;
                case 7: texture=new Texture("7.png");break;
                case 8: texture=new Texture("8.png");break;
                case 9: texture=new Texture("9.png");break;

            }
        }
        else if(Score>=10&&Score<100){
            int a=Score%10;
            switch (a){
                case 0: texture=new Texture("0.png");break;
                case 1:texture=new Texture("1.png");break;
                case 2: texture=new Texture("2.png");break;
                case 3: texture=new Texture("3.png");break;
                case 4: texture=new Texture("4.png");break;
                case 5: texture=new Texture("5.png");break;
                case 6: texture=new Texture("6.png");break;
                case 7: texture=new Texture("7.png");break;
                case 8: texture=new Texture("8.png");break;
                case 9: texture=new Texture("9.png");break;

            }
            int b=Score/10;
            switch (b){
                case 0: texture1=new Texture("0.png");break;
                case 1:texture1=new Texture("1.png");break;
                case 2: texture1=new Texture("2.png");break;
                case 3: texture1=new Texture("3.png");break;
                case 4: texture1=new Texture("4.png");break;
                case 5: texture1=new Texture("5.png");break;
                case 6: texture1=new Texture("6.png");break;
                case 7: texture1=new Texture("7.png");break;
                case 8: texture1=new Texture("8.png");break;
                case 9: texture1=new Texture("9.png");break;

            }
        }
        else {
            int temp=Score;
            int a=temp%10;
            temp/=10;
            int b=temp%10;
            int c=temp/10;
            switch (a){
                case 0: texture=new Texture("0.png");break;
                case 1:texture=new Texture("1.png");break;
                case 2: texture=new Texture("2.png");break;
                case 3: texture=new Texture("3.png");break;
                case 4: texture=new Texture("4.png");break;
                case 5: texture=new Texture("5.png");break;
                case 6: texture=new Texture("6.png");break;
                case 7: texture=new Texture("7.png");break;
                case 8: texture=new Texture("8.png");break;
                case 9: texture=new Texture("9.png");break;

            }
            switch (b){
                case 0: texture1=new Texture("0.png");break;
                case 1:texture1=new Texture("1.png");break;
                case 2: texture1=new Texture("2.png");break;
                case 3: texture1=new Texture("3.png");break;
                case 4: texture1=new Texture("4.png");break;
                case 5: texture1=new Texture("5.png");break;
                case 6: texture1=new Texture("6.png");break;
                case 7: texture1=new Texture("7.png");break;
                case 8: texture1=new Texture("8.png");break;
                case 9: texture1=new Texture("9.png");break;

            }
            switch (c){
                case 0: texture2=new Texture("0.png");break;
                case 1:texture2=new Texture("1.png");break;
                case 2: texture2=new Texture("2.png");break;
                case 3: texture2=new Texture("3.png");break;
                case 4: texture2=new Texture("4.png");break;
                case 5: texture2=new Texture("5.png");break;
                case 6: texture2=new Texture("6.png");break;
                case 7: texture2=new Texture("7.png");break;
                case 8: texture2=new Texture("8.png");break;
                case 9: texture2=new Texture("9.png");break;

            }
        }
        if(Score<10)
            spriteBatch.draw(texture,x,y);
        else if (Score<100&&Score>=10)
        {
            spriteBatch.draw(texture,x,y);
            spriteBatch.draw(texture1,x-texture.getWidth()-3,y);
        }
        else {
            spriteBatch.draw(texture,x,y);
            spriteBatch.draw(texture1,x-texture.getWidth()-3,y);
            spriteBatch.draw(texture2,x-texture.getWidth()-texture1.getWidth()-3,y);
        }

    }

    public void setScore(int Score){
        this.Score=Score;
    }
    public void setHighScore(int highScore){this.highScore=highScore;}
    public void setNewScore(boolean newScore){this.newScore=newScore;}
    public boolean getNewScore(){return newScore;}
}
