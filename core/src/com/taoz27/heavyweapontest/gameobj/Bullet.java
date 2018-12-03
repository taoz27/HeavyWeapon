package com.taoz27.heavyweapontest.gameobj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.taoz27.heavyweapontest.Assets;
import com.taoz27.heavyweapontest.Config;

public class Bullet extends AbsGameObj {
    Tank parent;
    
    Array<TextureAtlas.AtlasRegion> bodyImgs;
    int curItem=104;

    float speed;

    public Bullet(Tank parent,float x,float y,float angle) {
        super();
        this.parent=parent;
        
        bodyImgs= Assets.getInstance().bulletImgs;
        getInfoByAtlas(bodyImgs.get(0));
        body.x=x-body.width/2;body.y=y-body.height/2;

        health= Config.getBulletHealth();

        speed=Config.getBulletSpeed();
        velocity.x=speed* MathUtils.cosDeg(angle);velocity.y=speed*MathUtils.sinDeg(angle);
    }

    @Override
    void updateBeforeExplosion() {
        hitTarget();
    }

    void hitTarget(){
        if (state!=State.ALIVE)return;
        for(int i=0;i<parent.target.planes.size;i++){
            Plane plane=parent.target.planes.get(i);
            int size=plane.bombs.size;
            for(int j=0;j<size;j++){
                Bomb bomb=plane.bombs.get(j);
                if (body.overlaps(bomb.body)){
                    if(bomb.onHit(Config.getBulletDamage())) {
                        state = State.EXPLOSION;
                        return;
                    }
                }
            }

            if (body.y+body.height<Config.getPlaneLowestHeight())continue;
            if (body.overlaps(plane.body)){
                if(plane.onHit(Config.getBulletDamage())) {
                    Assets.getInstance().bullethit.play();
                    state = State.EXPLOSION;
                    return;
                }
            }
        }
    }

    @Override
    void updateVelocity() {
        velocity.y-=Config.getGravity()*0.1f;
    }

    @Override
    void renderOnAlive(SpriteBatch batch) {
        float angle=velocity.angle();
        if(MathUtils.isEqual(angle,0,3)||MathUtils.isEqual(angle,360,3))
            curItem=20;
        else if (MathUtils.isEqual(angle,180,3))
            curItem=0;
        if(MathUtils.isEqual(angle,90,3)||MathUtils.isEqual(angle,270,3))
            curItem=10;
        else{
            if (angle>180)angle-=180;
            curItem=21-(int)(angle*21/180);
        }

        batch.draw(bodyImgs.get(curItem),body.x,body.y,body.width,body.height);
    }

    @Override
    void renderOnExplosion(SpriteBatch batch) {
        state=State.REMOVABLE;
    }

    @Override
    void renderOnDead(SpriteBatch batch) {
        state=State.REMOVABLE;
    }

    @Override
    void renderOnRemove(SpriteBatch batch) {

    }
}
