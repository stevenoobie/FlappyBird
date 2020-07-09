package com.flappy.game.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;


public class Animation {
   List<TextureRegion> frames;
   int frame;
   float currentTime;
   float maxFrameTime;
   int frameCount;

   public Animation(TextureRegion region,int frameCount,float cycleTime){
       frames=new ArrayList<TextureRegion>();
       int frameWidth=region.getRegionWidth()/frameCount;
       for(int i=0;i<frameCount;i++){
           frames.add(new TextureRegion(region,i*frameWidth,0,frameWidth,region.getRegionHeight()));
       }
       maxFrameTime=cycleTime/frameCount;
       frame=0;
       this.frameCount=frameCount;
   }
   public void update(float dt){
       currentTime+=dt;
       if(currentTime>maxFrameTime){
           {frame++;
           currentTime=0;}
       }
       if(frame==frameCount)
           frame=0;
   }
   public TextureRegion getTextureRegion(){
       return frames.get(frame);
   }
}
