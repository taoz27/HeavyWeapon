package com.taoz27.heavyweapontest.gameobj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class Enemies{
    Array<Plane> planes;

    int framesPerPlane=60*3;
    int curFrame=0;

    public Enemies(){
        super();
        planes=new Array<Plane>();
    }

    public void update() {
        for(Iterator<Plane> iter = planes.iterator(); iter.hasNext();){
            Plane jet=iter.next();
            if(jet.state== AbsGameObj.State.REMOVABLE){
                iter.remove();
                continue;
            }
            jet.update();
        }

        if (curFrame==0)emitPlane();
        curFrame++;
        curFrame=curFrame%framesPerPlane;
    }

    private void emitPlane() {
        Plane jet=Plane.crtSmallJet();
        planes.add(jet);
        Plane jet1=Plane.crtBlimp();
        planes.add(jet1);
        Plane jet2=Plane.crtBomber();
        planes.add(jet2);
        Plane jet3=Plane.crtDeltaBomber();
        planes.add(jet3);
    }

    public void render(SpriteBatch batch) {
        for(int i=0;i<planes.size;i++)
            planes.get(i).render(batch);
    }
}
