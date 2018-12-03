package com.taoz27.heavyweapontest;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Backgrounds {
    int stage;

    Array<Bg> bgs;

    public Backgrounds(int stage){
        this.stage=stage;
        crtBgs();
    }

    private void crtBgs(){
        bgs=Bg.stage1();
    }

    public void update() {
        for(int i=0;i<bgs.size;i++)
            bgs.get(i).update();
    }

    public void render(SpriteBatch batch) {
        for(int i=0;i<bgs.size;i++)
            bgs.get(i).render(batch);
    }
}
