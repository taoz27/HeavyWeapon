package com.taoz27.heavyweapontest;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Bomb extends AbsGameObj {
    Array<TextureAtlas.AtlasRegion> bodyImgs;
    int curItem=0;

    float speed;

    SmallJet parent;
    
    public Bomb(SmallJet parent,float x,float y){
        super();
        this.parent=parent;
        
        bodyImgs=Assets.getInstance().bombImgs;
        getInfoByAtlas(bodyImgs.get(0));
        body.x=x-body.width/2;body.y=y-body.height;//因为炸弹要在飞机正下方紧挨着，但是smallJet中获取不到炸弹的height，所这里不除以2

        health=Config.getBombHealth();

        speed=Config.getBombSpeed();
        velocity.set(speed,0);
    }

    @Override
    public void update() {
        super.update();
        if (body.y<Config.getGroundHeight()){
            dead=true;
        }else{
            hitTarget();
        }
    }

    void hitTarget(){
        if (dead)return;
        if (body.y>parent.target.body.y+parent.target.body.height)return;
        if (body.overlaps(parent.target.body)){
            if(parent.target.onHit(Config.getBombDamage()))
                dead=true;
        }
    }

    @Override
    void updateVelocity() {
        velocity.y-=Config.getGravity();
    }

    @Override
    public void render(SpriteBatch batch) {
        float angle=velocity.angle();
        if (MathUtils.isEqual(angle,0,3))angle=360;
        angle=360-angle;
        if(MathUtils.isEqual(angle,90,5))
            curItem=bodyImgs.size-1;
        curItem=(int)(angle*10/90);

        batch.draw(bodyImgs.get(curItem),body.x,body.y,body.width,body.height);
    }
}
