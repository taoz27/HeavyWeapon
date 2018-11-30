package com.taoz27.heavyweapontest;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Bullet extends AbsGameObj{
    Tank parent;
    
    Array<TextureAtlas.AtlasRegion> bodyImgs;
    int curItem=104;

    float speed;

    public Bullet(Tank parent,float x,float y,float angle) {
        super();
        this.parent=parent;
        
        bodyImgs=Assets.getInstance().bulletImgs;
        getInfoByAtlas(bodyImgs.get(0));
        body.x=x-body.width/2;body.y=y-body.height/2;

        health=Config.getBulletHealth();

        speed=Config.getBulletSpeed();
        velocity.x=speed* MathUtils.cosDeg(angle);velocity.y=speed*MathUtils.sinDeg(angle);
    }

    @Override
    public void update() {
        super.update();
        if (body.y<Config.getGroundHeight()){
            dead=true;
        }else {
            hitTarget();
        }
    }

    void hitTarget(){
        if (dead)return;
        if (body.y+body.height<Config.getPlaneLowestHeight())return;
        for(int i=0;i<parent.target.planes.size;i++){
            AbsGameObj obj=parent.target.planes.get(i);
            if (body.overlaps(obj.body)){
                if(obj.onHit(Config.getBulletDamage())) {
                    dead = true;
                    return;
                }
            }
        }
    }

    @Override
    void updateVelocity() {
        velocity.y-=Config.getGravity();
    }

    @Override
    public void render(SpriteBatch batch) {
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
//        curItem--;
//        curItem = curItem < 0 ? 104 : curItem;
    }
}
