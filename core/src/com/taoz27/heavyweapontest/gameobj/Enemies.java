package com.taoz27.heavyweapontest.gameobj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class Enemies extends AbsGameObj {
    Array<Plane> planes;
    Array<Bomb> bombs;
    Tank target;

    int framesPerPlane=60*1;
    int curFrame=0;

    public Enemies(){
        super();
        planes=new Array<Plane>();
        bombs=new Array<Bomb>();
    }

    public void setTarget(Tank target){
        this.target =target;
    }

    @Override
    public void update() {
        for(Iterator<Plane> iter = planes.iterator(); iter.hasNext();){
            Plane jet=iter.next();
            if(jet.dead){
                iter.remove();
                continue;
            }
            jet.update();
        }

        for(Iterator<Bomb> iter = bombs.iterator(); iter.hasNext();){
            Bomb b=iter.next();
            if (b.dead){
                iter.remove();
                continue;
            }
            b.update();
        }

        if (curFrame==0)emitPlane();
        curFrame++;
        curFrame=curFrame%framesPerPlane;
    }

    private void emitPlane() {
        Plane jet=Plane.crtSmallJet(this,target);
        planes.add(jet);
        Plane jet1=Plane.crtBlimp(this,target);
        planes.add(jet1);
        Plane jet2=Plane.crtBomber(this,target);
        planes.add(jet2);
        Plane jet3=Plane.crtDeltaBomber(this,target);
        planes.add(jet3);
    }

    @Override
    void updateVelocity() {}

    @Override
    public void render(SpriteBatch batch) {
        for(int i=0;i<planes.size;i++)
            planes.get(i).render(batch);

        for(int i=0;i<bombs.size;i++)
            bombs.get(i).render(batch);
    }
}
