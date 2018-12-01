package com.taoz27.heavyweapontest.gameobj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.taoz27.heavyweapontest.Assets;
import com.taoz27.heavyweapontest.Config;

public class Backgrounds extends AbsGameObj {
    int stage;

    Array<Bg> bgs;

    public Backgrounds(int stage){
        this.stage=stage;
        crtBgs();
    }

    private void crtBgs(){
        bgs=Bg.stage1();
    }

    @Override
    public void update() {
        for(int i=0;i<bgs.size;i++)
            bgs.get(i).update();
    }

    @Override
    void updateVelocity() {}

    @Override
    public void render(SpriteBatch batch) {
        for(int i=0;i<bgs.size;i++)
            bgs.get(i).render(batch);
    }
}
