package com.taoz27.heavyweapontest;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class SmallJet extends AbsGameObj {
    TextureAtlas.AtlasRegion bodyImg;

    float speed;
    int framesPerBomb=60;
    int curFrames=0;

    Enemies parent;
    Tank target;

    public SmallJet(Enemies parent,Tank tank){
        super();

        bodyImg =Assets.getInstance().smallJetImg;
        getInfoByAtlas(bodyImg);
        body.x=-body.width;
        body.y= MathUtils.random(Config.getPlaneLowestHeight()+30,Config.getScreenHeight()-body.height);
        speed=Config.getSmallJetSpeed();
        velocity.x=speed;
        health=Config.getSmallJetHealth();

        this.parent=parent;
        this.target =tank;
    }

    @Override
    public void update() {
        super.update();
        if (body.x>Config.getScreenWidth()){
            dead=true;
        }

        if(curFrames==0) emitBomb();
        curFrames++;
        curFrames=curFrames%framesPerBomb;
    }

    private void emitBomb() {
        Bomb bomb=new Bomb(this,body.x+body.width/2,body.y);
        parent.bombs.add(bomb);
    }

    @Override
    void updateVelocity() {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(bodyImg,body.x,body.y,body.width,body.height);
    }
}
