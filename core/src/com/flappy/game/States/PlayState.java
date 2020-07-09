package com.flappy.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.flappy.game.Flappy;
import com.flappy.game.Sprites.Bird;
import com.flappy.game.Sprites.Tube;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class PlayState extends State {
    Bird bird;
    Texture bg;
    Texture ground;
    Vector2 Posground1;
    Vector2 Posground2;
    Random random;
    List<Float>temp;
    int Score;
    List<Tube>tubes;
    Texture texture;
    Texture texture1;
    Texture texture2;
    Texture highscore;
    int highScore;
    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);

        texture=new Texture("badlogic.jpg");
        texture1=new Texture("0.png");
        texture2=new Texture("0.png");
        highscore=new Texture("highscore.png");
        temp=new ArrayList<Float>();
        random=new Random();
        tubes=new ArrayList<Tube>();
        for (int i=0;i<=3;i++){
            float x=(i+1)*(125+52)+random.nextInt(30);
            tubes.add(new Tube(x));
        }

        bird=new Bird(20,200);
        bg=new Texture("backGround.png");
        ground=new Texture("ground.png");
        Posground1=new Vector2(0,-60);
        Posground2=new Vector2(camera.position.x-camera.viewportWidth/2 +ground.getWidth(),-60);
        camera.setToOrtho(false, Flappy.WIDTH/2,Flappy.HEIGHT/2);
        load();
    }

    @Override
    public void handleInput() {
       if(Gdx.input.justTouched()){
           bird.jump();
       }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        updateGround();


        camera.position.x=bird.getPosition().x+80;
        bird.update(deltaTime);

        for (Tube tube:tubes){

            float g=tube.getTopBounds().getX()+tube.getTopBounds().getWidth();
           if(bird.getPlayer().getX()>g&& !temp.contains(g)){
               temp.add(g);
               Score++;


           }

            if((camera.position.x-camera.viewportWidth/2) >(tube.getPosTopTube().x +tube.getTopTube().getWidth())){
                tube.Reposition(tube.getPosTopTube().x +(52+125)*4);
            }
            if(tube.Collides(bird.getPlayer())){
                //TODO:GameOver State

                GameOverState gameOverState=new GameOverState(gameStateManager);
                gameOverState.setScore(Score);
                gameOverState.setHighScore(highScore);
                if(Score>highScore){
                    gameOverState.setNewScore(true);

                    save(Score);
                }

                gameStateManager.set(gameOverState);
                dispose();
                break;

            }
        }

        if(bird.getPosition().y<=ground.getHeight()-60)
        {
            //TODO:GameOver State
            GameOverState gameOverState=new GameOverState(gameStateManager);
            gameOverState.setScore(Score);
            gameOverState.setHighScore(highScore);
            if(Score>highScore){

                gameOverState.setNewScore(true);

                save(Score);
            }
            gameStateManager.set(gameOverState);
            dispose();
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(bg,camera.position.x-camera.viewportWidth/2,0,Flappy.WIDTH,Flappy.HEIGHT/2);
        spriteBatch.draw(bird.getBird(),bird.getPosition().x,bird.getPosition().y);
        for(Tube tube:tubes){

            spriteBatch.draw(tube.getTopTube(),tube.getPosTopTube().x,tube.getPosTopTube().y);
            spriteBatch.draw(tube.getBottomTube(),tube.getPosBottomTube().x,tube.getPosBottomTube().y);
        }
        spriteBatch.draw(ground,Posground1.x,Posground1.y);
        spriteBatch.draw(ground,Posground2.x,Posground2.y);
        spriteBatch.draw(highscore,camera.position.x-camera.viewportWidth/2,camera.position.y+camera.viewportHeight/2-30,50,30);
        Draw(spriteBatch,camera.position.x,camera.position.y+100,Score);
        Drawhigh(spriteBatch,camera.position.x-camera.viewportWidth/2+32,camera.position.y+camera.viewportWidth/2,15,15,highScore);

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        bird.dispose();
        bg.dispose();
        ground.dispose();
        for(Tube tube:tubes)
            tube.dispose();
        texture.dispose();
        texture1.dispose();
        texture2.dispose();


    }
    public void updateGround(){
        if(camera.position.x-camera.viewportWidth/2 >Posground1.x+ground.getWidth())
            Posground1.add(2*ground.getWidth(),0);
        if(camera.position.x-camera.viewportWidth/2>Posground2.x+ground.getWidth())
            Posground2.add(2*ground.getWidth(),0);
    }
    public void Draw(SpriteBatch spriteBatch,float x,float y,int value){

        if(value<10){
        switch (value){
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
        else if(value>=10&&value<100){
            int a=value%10;
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
            int b=value/10;
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
            int temp=value;
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

        if(value<10)
        spriteBatch.draw(texture,x,y);
        else if (value<100&&value>=10)
        {
            spriteBatch.draw(texture,x,y);
            spriteBatch.draw(texture1,x-texture.getWidth(),y);
        }
        else {
            spriteBatch.draw(texture,x,y);
            spriteBatch.draw(texture1,x-texture.getWidth(),y);
            spriteBatch.draw(texture2,x-texture.getWidth()-texture1.getWidth(),y);
        }

    }public void Drawhigh(SpriteBatch spriteBatch,float x,float y,float width,float height,int value){

        if(value<10){
            switch (value){
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
        else if(value>=10&&value<100){
            int a=value%10;
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
            int b=value/10;
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
            int temp=value;
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
        Texture habal=new Texture("1.png");
        if(value<10)
            spriteBatch.draw(texture,x,y,width,height);
        else if (value<100&&value>=10)
        {
            spriteBatch.draw(texture,x,y,width,height);
            spriteBatch.draw(texture1,x-texture.getWidth()+width/2,y,width,height);
        }
        else {
            spriteBatch.draw(texture,x,y,width,height);
            spriteBatch.draw(texture1,x-texture.getWidth()+width/2,y,width,height);
            spriteBatch.draw(texture2,x-texture.getWidth()-texture1.getWidth()+width,y,width,height);
        }

    }
    public void load(){
        Preferences preferences=Gdx.app.getPreferences("Score");


        highScore=preferences.getInteger("int");
    }
    public void save(int value){

        Preferences preferences=Gdx.app.getPreferences("Score");
        preferences.putInteger("int",value);
        preferences.flush();
    }


    }


