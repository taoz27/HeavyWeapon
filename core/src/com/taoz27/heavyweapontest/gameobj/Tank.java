package com.taoz27.heavyweapontest.gameobj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.taoz27.heavyweapontest.Assets;
import com.taoz27.heavyweapontest.Config;

import java.util.Iterator;

public class Tank extends AbsGameObj {
    Array<TextureAtlas.AtlasRegion> bodyImgs;
    int curAtlasItem=0;
    Texture shadow;
    float shadowWidth,shadowHeight;
    Array<Bullet> bullets;
    
    float speed;
    int framesPerBullet=10;
    int curFrames=0;

    Enemies target;

    public Tank(){
        super();
        bodyImgs= Assets.getInstance().tankImgs;
        getInfoByAtlas(bodyImgs.get(0));
        shadow=Assets.getInstance().tankShadow;
        shadowWidth=body.width*1.5f;shadowHeight=shadowWidth/4;
        body.x=(Config.getScreenWidth()-body.width)/2;body.y=Config.getGroundHeight();
        speed=Config.getTankSpeed();
        health=Config.getTankHealth();

        bullets=new Array<Bullet>();
    }

    public void setTarget(Enemies enemies){
        this.target =enemies;
    }

    void detectHit(){
        int size=target.planes.size;
        for(int p=0;p<size;p++){
            Plane plane=target.planes.get(p);
            int bsize=plane.bombs.size;
            for (int i=0;i<bsize;i++){
                Bomb bomb=plane.bombs.get(i);
                bomb.hitTarget(this);
            }
        }
    }

    @Override
    void updateBeforeExplosion() {
        if (body.x<0)body.x=0;
        if (body.x+body.width>Config.getScreenWidth())body.x=Config.getScreenWidth()-body.width;

        if(curFrames==0)emitBullet();
        curFrames++;
        curFrames=curFrames%framesPerBullet;

        detectHit();
    }

    @Override
    void updateBeforeRemove() {
        for(Iterator<Bullet> iter=bullets.iterator();iter.hasNext();){
            Bullet b=iter.next();
            if (b.state==State.REMOVABLE){
                iter.remove();
                continue;
            }
            b.update();
        }
    }

    private void emitBullet() {
        Assets.getInstance().tankfire.play();
        Bullet b0=new Bullet(this,body.x+body.width/2,body.y+body.height,90f);
        bullets.add(b0);
        Bullet b1=new Bullet(this,body.x+body.width/2+10,body.y+body.height,75f);
        bullets.add(b1);
        Bullet b2=new Bullet(this,body.x+body.width/2-10,body.y+body.height,105f);
        bullets.add(b2);
    }

    @Override
    void renderOnAlive(SpriteBatch batch) {
        batch.draw(shadow,body.x-(shadowWidth-body.width)/2,body.y-shadowHeight/2,shadowWidth,shadowHeight);

        batch.draw(bodyImgs.get(curAtlasItem),body.x,body.y,body.width,body.height);
        curAtlasItem++;
        curAtlasItem = curAtlasItem >= bodyImgs.size ? 0 : curAtlasItem;
    }

    @Override
    void renderOnExplosion(SpriteBatch batch) {
        state=State.ALIVE;
        health=Config.getTankHealth();
    }

    @Override
    void renderOnDead(SpriteBatch batch) {

    }

    @Override
    void renderOnRemove(SpriteBatch batch) {

    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);

        for(int i=0;i<bullets.size;i++)
            bullets.get(i).render(batch);
    }
}
