package com.taoz27.heavyweapontest.gameobj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.taoz27.heavyweapontest.Config;

public abstract class AbsGameObj {
    enum State{ALIVE,EXPLOSION,DEAD,REMOVABLE}
    State state;

    float ratio;

    public Rectangle body;
    public Vector2 velocity;

    int health;

    public AbsGameObj(){
        state=State.ALIVE;
        ratio= Config.getScreenRatio();

        body=new Rectangle();
        velocity=new Vector2();

        health=0;
    }

    void getInfoByAtlas(TextureAtlas.AtlasRegion bodyAtlas){
        body.width=bodyAtlas.originalWidth*ratio;body.height=bodyAtlas.originalHeight*ratio;
    }
    
    public void update(){
        switch (state){
            case ALIVE:
                updateVelocity();
                body.x+=velocity.x;
                body.y+=velocity.y;
                if (isOut())state=State.DEAD;
                updateBeforeExplosion();
            case EXPLOSION:
                updateBeforeDead();
            case DEAD:
                updateBeforeRemove();
        }
    }

    public void render(SpriteBatch batch){
        switch (state){
            case ALIVE:
                renderOnAlive(batch);
                break;
            case EXPLOSION:
                renderOnExplosion(batch);
                break;
            case DEAD:
                renderOnDead(batch);
                break;
            case REMOVABLE:
                renderOnRemove(batch);
                break;
        }
    }

    //如果被轰炸目标已死，返回false
    boolean onHit(int damage){
        if (state!=State.ALIVE)return false;
        health-=damage;
        if (health<=0)state=State.EXPLOSION;
        return true;
    }

    boolean isOut(){
        if (body.x>Config.getScreenWidth()||body.x<-body.width||body.y>Config.getScreenHeight()||body.y<Config.getGroundHeight())
            return true;
        return false;
    }

    void updateBeforeExplosion(){}
    void updateBeforeDead(){}
    void updateBeforeRemove(){}
    
    abstract void renderOnAlive(SpriteBatch batch);
    abstract void renderOnExplosion(SpriteBatch batch);
    abstract void renderOnDead(SpriteBatch batch);
    abstract void renderOnRemove(SpriteBatch batch);
    abstract void updateVelocity();
}
