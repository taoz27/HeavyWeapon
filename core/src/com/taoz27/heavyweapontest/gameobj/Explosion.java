package com.taoz27.heavyweapontest.gameobj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.taoz27.heavyweapontest.Assets;
import com.taoz27.heavyweapontest.Config;

public class Explosion {
    Array<TextureAtlas.AtlasRegion> bodyImgs= Assets.getInstance().explosionImgs;
    Texture smoke=Assets.getInstance().planeSmoke;
    Rectangle smokeBody;

    int maxWidth=200;
    int maxSpeed=4;
    boolean end;
    int stop=2;
    int stopi=stop;
    int curItem=0;
    Rectangle body;
    float speed;

    public Explosion(Rectangle target,float speed){
        body=new Rectangle(target);
        if (target.width>target.height) {
            body.x += (target.width - target.height) / 2;
        }else {
            body.y+=(target.height-target.width)/2;
        }
        if (body.width>maxWidth* Config.getScreenRatio())body.width=maxWidth*Config.getScreenRatio();
        body.width=target.height;

        if (speed>0)this.speed=speed>maxWidth?maxSpeed:speed;
        else this.speed=speed<-maxWidth?-maxSpeed:speed;

        smokeBody=new Rectangle(body);

        end=false;
    }

    public void render(SpriteBatch batch) {
        if (end)return;
        smokeBody.width++;smokeBody.height++;
        batch.draw(smoke,smokeBody.x,smokeBody.y,smokeBody.width,smokeBody.height);

        body.x+=speed;
        batch.draw(bodyImgs.get(curItem),body.x,body.y,body.width,body.height);

        stopi--;
        if (stopi==0) {
            if (++curItem >= bodyImgs.size)
                end = true;
            stopi=stop;
        }
    }
}
