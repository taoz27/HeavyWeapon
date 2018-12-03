package com.taoz27.heavyweapontest.gameobj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.taoz27.heavyweapontest.Assets;

public class Explosion {
    Array<TextureAtlas.AtlasRegion> bodyImgs= Assets.getInstance().explosionImgs;

    boolean end;
    int curItem=0;
    Rectangle body;
    float speed;

    public Explosion(Rectangle target,float speed){
        body=new Rectangle(target);
        if (target.width>target.height) {
            body.x += (target.width - target.height) / 2;
            body.width=target.height;
        }else {
            body.y+=(target.height-target.width)/2;
            body.height=target.width;
        }
        this.speed=speed;

        end=false;
    }

    public void render(SpriteBatch batch) {
        if (end)return;
        body.x+=speed;
        batch.draw(bodyImgs.get(curItem),body.x,body.y,body.width,body.height);

        if (++curItem>=bodyImgs.size)
            end =true;
    }
}
