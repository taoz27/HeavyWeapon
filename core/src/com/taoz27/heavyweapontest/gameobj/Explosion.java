package com.taoz27.heavyweapontest.gameobj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.taoz27.heavyweapontest.Assets;

import java.util.Iterator;

public class Explosion {
    static Array<Explosion> explosions=new Array<Explosion>();
    static Array<TextureAtlas.AtlasRegion> bodyImgs= Assets.getInstance().explosionImgs;
    static int lastTime=1;//2*20frames

    boolean dead;
    int curTime=lastTime;
    int curItem=0;
    Rectangle body;

    public Explosion(Rectangle target){
        body=new Rectangle(target);
        if (target.width>target.height) {
            body.x += (target.width - target.height) / 2;
            body.width=target.height;
        }else {
            body.y+=(target.height-target.width)/2;
            body.height=target.width;
        }

        explosions.add(this);
    }

    public static void renderAll(SpriteBatch batch){
        for(Iterator<Explosion> iter=explosions.iterator();iter.hasNext();){
            Explosion e=iter.next();
            if (e.dead){
                iter.remove();
                continue;
            }
            e.render(batch);
        }
    }

    void render(SpriteBatch batch) {
        batch.draw(bodyImgs.get(curItem),body.x,body.y,body.width,body.height);

        if (--curTime==0){
            curTime=lastTime;
            if (++curItem>=bodyImgs.size)
                dead=true;
        }
    }
}
