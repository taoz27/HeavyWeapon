package com.taoz27.heavyweapontest;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class AbsGameObj {
    float ratio;

    public Rectangle body;
    public Vector2 velocity;

    int health;
    boolean dead;

    public AbsGameObj(){
        ratio=Config.getScreenRatio();

        body=new Rectangle();
        velocity=new Vector2();

        health=0;
        dead=false;
    }

    void getInfoByAtlas(TextureAtlas.AtlasRegion bodyAtlas){
        body.width=bodyAtlas.originalWidth*ratio;body.height=bodyAtlas.originalHeight*ratio;
    }

    public void update(){
        updateVelocity();
        body.x+=velocity.x;
        body.y+=velocity.y;
    }

    //如果被轰炸目标已死，返回false
    boolean onHit(int damage){
        if (dead)return false;
        health-=damage;
        dead=health<=0?true:false;
        return true;
    }

    abstract void updateVelocity();
    public abstract void render(SpriteBatch batch);
}
