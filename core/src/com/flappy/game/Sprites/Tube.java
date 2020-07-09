package com.flappy.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Tube {
    private Texture topTube;
    private Texture bottomTube;
    private Vector3 posTopTube;
    private Vector3 posBottomTube;
    private final int GAP=70;
    private final int Lowest_Opening=100;
    Random random;
    private Rectangle TopBounds,BottomBounds;
    public Tube(float x){

        random=new Random();
        topTube=new Texture("topTube.png");
        bottomTube=new Texture("bottomTube.png");
        posTopTube=new Vector3(x,random.nextInt(100)+GAP+Lowest_Opening,0);
        posBottomTube=new Vector3(x,posTopTube.y-GAP-bottomTube.getHeight()-random.nextInt(30),0);
        TopBounds=new Rectangle(posTopTube.x,posTopTube.y,topTube.getWidth(),topTube.getHeight());
        BottomBounds=new Rectangle(posBottomTube.x,posBottomTube.y,bottomTube.getWidth(),bottomTube.getHeight());
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector3 getPosTopTube() {
        return posTopTube;
    }

    public Vector3 getPosBottomTube() {
        return posBottomTube;
    }

    public void Reposition(float x){
        posTopTube=new Vector3(x,random.nextInt(90)+GAP+Lowest_Opening,0);
        posBottomTube=new Vector3(x,posTopTube.y-GAP-bottomTube.getHeight()-random.nextInt(30),0);
        TopBounds.setPosition(posTopTube.x,posTopTube.y);
        TopBounds.setSize(topTube.getWidth(),topTube.getHeight());
        BottomBounds.setSize(bottomTube.getWidth(),bottomTube.getHeight());
        BottomBounds.setPosition(posBottomTube.x,posBottomTube.y);
    }
    public boolean Collides(Rectangle player){

     if(player.overlaps(TopBounds)||player.overlaps(BottomBounds))
     {
         return true;
     }
        return false;
    }
    public Rectangle getTopBounds(){
        return TopBounds;
    }
    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();

    }
}
