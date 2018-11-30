package com.taoz27.heavyweapontest.gameobj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.taoz27.heavyweapontest.Config;

public abstract class AbsGameObj {
    float ratio;

    public Rectangle body;
    public Vector2 velocity;

    int health;
    boolean dead;

    public AbsGameObj(){
        ratio= Config.getScreenRatio();

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
        if (isOut())dead=true;
    }

    //如果被轰炸目标已死，返回false
    boolean onHit(int damage){
        if (dead)return false;
        health-=damage;
        dead=health<=0?true:false;
        return true;
    }

    boolean isOut(){
        if (body.x>Config.getScreenWidth()||body.x<-body.width||body.y>Config.getScreenHeight()||body.y<Config.getGroundHeight())
            return true;
        return false;
    }

    abstract void updateVelocity();
    public abstract void render(SpriteBatch batch);
}
