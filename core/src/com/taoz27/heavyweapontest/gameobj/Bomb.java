package com.taoz27.heavyweapontest.gameobj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.taoz27.heavyweapontest.Assets;
import com.taoz27.heavyweapontest.Config;

public class Bomb extends AbsGameObj {
    Array<TextureAtlas.AtlasRegion> bodyImgs,bodyImgsFlip;
    int curItem=0;

    float speed;
    boolean direction;
    
    public Bomb(float x, float y,boolean direction,float speed){
        super();
        this.direction=direction;
        
        bodyImgs= Assets.getInstance().bombImgs;
        bodyImgsFlip=new Array<TextureAtlas.AtlasRegion>();
        for(int i=0;i<bodyImgs.size;i++){
            TextureAtlas.AtlasRegion temp=new TextureAtlas.AtlasRegion(bodyImgs.get(i));
            temp.flip(true,false);
            bodyImgsFlip.add(temp);
        }
        getInfoByAtlas(bodyImgs.get(0));
        body.x=x-body.width/2;body.y=y-body.height;//因为炸弹要在飞机正下方紧挨着，但是smallJet中获取不到炸弹的height，所这里不除以2

        health= Config.getBombHealth();

        this.speed=speed;
        velocity.set(direction?speed:-speed,0);
    }

    void hitTarget(Tank target){
        if (state!=State.ALIVE)return;
        if (body.y>target.body.y+target.body.height)return;
        if (body.overlaps(target.body)){
            if(target.onHit(Config.getBombDamage()))
                state=State.EXPLOSION;
        }
    }

    @Override
    void updateVelocity() {
        velocity.y-=Config.getGravity();
    }

    @Override
    void renderOnAlive(SpriteBatch batch) {
        float angle=velocity.angle();
        if (direction) {
            if (MathUtils.isEqual(angle, 0, 3)) angle = 360;
            angle = 360 - angle;
        }else {
            angle=angle-180;
        }
        if(MathUtils.isEqual(angle,90,5))
            curItem=bodyImgs.size-1;
        curItem=(int)(angle*10/90);

        batch.draw(direction?bodyImgs.get(curItem):bodyImgsFlip.get(curItem),body.x,body.y,body.width,body.height);
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
