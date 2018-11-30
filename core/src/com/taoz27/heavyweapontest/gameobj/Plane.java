package com.taoz27.heavyweapontest.gameobj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.taoz27.heavyweapontest.Assets;
import com.taoz27.heavyweapontest.Config;
import com.taoz27.heavyweapontest.PlaneImage;

public class Plane extends AbsGameObj {
    TextureAtlas.AtlasRegion bodyImg,bodyImgFlip;

    float speed;
    int framesPerBomb;//多少帧放一次炸弹
    int curFrames=0;
    int bombsPerTime;//每次投放多少炸弹
    int curBombs=0;
    int curBombsFrames=0;
    int bombType;

    boolean direction;//方向：true为向右

    Enemies parent;
    Tank target;

    public static Plane crtSmallJet(Enemies parent, Tank tank){
        return new Plane(parent,tank,PlaneImage.smalljet.getValue(),Config.getSmallJetBombSpeed(),Config.getSmallJetHealth());
    }
    public static Plane crtBlimp(Enemies parent, Tank tank){
        return new Plane(parent,tank,PlaneImage.blimp.getValue(),Config.getBlimpBombSpeed(),Config.getBlimpHealth(),5,1);
    }
    public static Plane crtBomber(Enemies parent, Tank tank){
        return new Plane(parent,tank,PlaneImage.bomber.getValue(),Config.getBomberHealth(),Config.getSmallJetHealth());
    }
    public static Plane crtDeltaBomber(Enemies parent, Tank tank){
        return new Plane(parent,tank,PlaneImage.deltabomber.getValue(),Config.getDeltaBomberBombSpeed(),Config.getDeltaBomberHealth());
    }

    private Plane(Enemies parent, Tank tank, int plane, int framesPerBomb, int health){
        this( parent,  tank,  plane, framesPerBomb,  health,1,1);
    }

    private Plane(Enemies parent, Tank tank, int plane,int framesPerBomb, int health,int bombsPerTime, int bombType){
        super();

        bodyImg = Assets.getInstance().planeImgs.get(plane);
        bodyImgFlip=new TextureAtlas.AtlasRegion(bodyImg);bodyImgFlip.flip(true,false);
        getInfoByAtlas(bodyImg);
        //speed=Config.getSmallJetSpeed();
        this.speed=Config.getPlaneSpeed();
        this.framesPerBomb=framesPerBomb;
        //health=Config.getSmallJetHealth();
        this.health=health;
        this.bombsPerTime=bombsPerTime;
        this.bombType=bombType;

        this.direction=MathUtils.randomBoolean();
        body.x=direction?-body.width:Config.getScreenWidth();
        body.y= MathUtils.random(Config.getPlaneLowestHeight()+30,Config.getScreenHeight()-body.height);
        velocity.x=direction?speed:-speed;

        this.parent=parent;
        this.target =tank;
    }

    @Override
    public void update() {
        super.update();

//        if(curFrames==0) emitBomb();
//        curFrames++;
//        curFrames=curFrames%framesPerBomb;
        if (curBombs<bombsPerTime){
            if (curBombsFrames==0) {
                emitBomb();
                curBombs++;
            }
            curBombsFrames++;
            curBombsFrames=curBombsFrames%8;
        }
        curFrames++;
        curFrames=curFrames%framesPerBomb;
        if (curFrames==0)curBombs=0;
    }

    private void emitBomb() {
        Bomb bomb=new Bomb(this,body.x+body.width/2,body.y,direction,speed);
        parent.bombs.add(bomb);
    }

    @Override
    void updateVelocity() {}

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(direction?bodyImg:bodyImgFlip,body.x,body.y,body.width,body.height);
    }
}
