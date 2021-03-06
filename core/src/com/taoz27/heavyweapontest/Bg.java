package com.taoz27.heavyweapontest;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;


public class Bg{
    TextureAtlas.AtlasRegion bodyImg;

    Rectangle body;
    float speed,ratio;
    int num;//一张图片无法覆盖整个背景，需要多张图片
    
    public static Array<Bg> stage1(){
        Array<Bg> bgs=new Array<Bg>();
        bgs.add(sky());
        bgs.add(mountain2());
        bgs.add(mountain1());
        bgs.add(ground());

        return bgs;
    }
    
    public static Bg sky(){
        return new Bg(Assets.getInstance().bg1Imgs.get(0),0, Config.getBgSkyHeight(),0.25f,Config.getBgSkyHeightScale());
    }
    
    public static Bg mountain2(){
        return new Bg(Assets.getInstance().bg1Imgs.get(1),0, Config.getBgMountainHeight(),0.4f,Config.getBgMountainHeightScale());
    }
    
    public static Bg mountain1(){
        return new Bg(Assets.getInstance().bg1Imgs.get(2),0, Config.getBgMountainHeight(),0.5f,Config.getBgMountainHeightScale());
    }
    
    public static Bg ground(){
        return new Bg(Assets.getInstance().bg1Imgs.get(3),0, Config.getBgGroundHeight(),1f,Config.getBgGroundHeightScale());
    }
    
    private Bg(TextureAtlas.AtlasRegion region,float x, float y, float speedScale,float scale){
        bodyImg=region;
        ratio=Config.getScreenRatio();
        body=new Rectangle();
        body.width=bodyImg.originalWidth*ratio*scale;body.height=bodyImg.originalHeight*ratio*scale;
        num= (int)(Config.getScreenWidth()/body.width)+2;

        body.x=x;body.y=y;

        speed=Config.getBgSpeed()*speedScale;
    }

    public void update() {
        body.x-=speed;
        if (body.x<-body.width)body.x+=body.width;
    }

    public void render(SpriteBatch batch) {
        for(int i=0;i<num;i++){
            batch.draw(bodyImg,body.x+body.width*i,body.y,body.width,body.height);
        }
    }
}
