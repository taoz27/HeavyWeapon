package com.taoz27.heavyweapontest.gameobj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.taoz27.heavyweapontest.Assets;
import com.taoz27.heavyweapontest.Config;

import java.util.Iterator;

public class Tank extends AbsGameObj {
    Array<TextureAtlas.AtlasRegion> bodyImgs;
    int curAtlasItem=0;
    
    float speed;
    int framesPerBullet=10;
    int curFrames=0;

    Enemies target;
    Array<Bullet> bullets;

    public Tank(){
        super();
        bodyImgs= Assets.getInstance().tankImgs;
        getInfoByAtlas(bodyImgs.get(0));
        body.x=(Config.getScreenWidth()-body.width)/2;body.y=Config.getGroundHeight();
        speed=Config.getTankSpeed();
        health=Config.getTankHealth();

        bullets=new Array<Bullet>();
    }

    public void setTarget(Enemies enemies){
        this.target =enemies;
    }

    @Override
    public void update() {
        super.update();
        if (body.x<0)body.x=0;
        if (body.x+body.width>Config.getScreenWidth())body.x=Config.getScreenWidth()-body.width;

        for(Iterator<Bullet> iter=bullets.iterator();iter.hasNext();){
            Bullet b=iter.next();
            if (b.dead){
                iter.remove();
                continue;
            }
            b.update();
        }

        if(curFrames==0)emitBullet();
        curFrames++;
        curFrames=curFrames%framesPerBullet;
    }

    private void emitBullet() {
        Bullet b0=new Bullet(this,body.x+body.width/2,body.y+body.height,90f);
        bullets.add(b0);
        Bullet b1=new Bullet(this,body.x+body.width/2+10,body.y+body.height,75f);
        bullets.add(b1);
        Bullet b2=new Bullet(this,body.x+body.width/2-10,body.y+body.height,105f);
        bullets.add(b2);
    }

    @Override
    void updateVelocity() {}

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(bodyImgs.get(curAtlasItem),body.x,body.y,body.width,body.height);
        curAtlasItem++;
        curAtlasItem = curAtlasItem >= bodyImgs.size ? 0 : curAtlasItem;

        for(int i=0;i<bullets.size;i++)
            bullets.get(i).render(batch);
    }
}
