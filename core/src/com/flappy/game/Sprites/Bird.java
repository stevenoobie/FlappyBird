package com.flappy.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private Vector3 position;
    private Vector3 velocity;
    private static final int Gravity=-10;
    private static final int MOVEMENT=100;
    private Animation animation;
    private Rectangle Player;
    public Bird(int x,int y){
        position=new Vector3(x,y,0);
        velocity=new Vector3(0,0,0);
        Texture texture=new Texture("birdanimation.png");
        animation=new Animation(new TextureRegion(texture),3,0.5f);
        Player=new Rectangle(position.x,position.y,texture.getWidth()/3,texture.getHeight());
    }
    public void update(float dt){
        animation.update(dt);
        if(position.y>0)
         velocity.add(0,Gravity,0);
        velocity.scl(dt);
        position.add(MOVEMENT*dt,velocity.y,0);
        if(position.y<0)
        { position.y=0;

        }
        velocity.scl(1/dt);
        Player.setPosition(position.x,position.y);
    }
    public void jump(){
        velocity.y=200;
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBird() {
        return animation.getTextureRegion();
    }
    public Rectangle getPlayer(){
        return Player;
    }
    public void dispose(){
        animation.getTextureRegion().getTexture().dispose();
        position.x=0;
        position.y=0;
    }
}
