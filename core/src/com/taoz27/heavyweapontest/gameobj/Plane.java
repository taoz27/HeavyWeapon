package com.taoz27.heavyweapontest.gameobj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.taoz27.heavyweapontest.Assets;
import com.taoz27.heavyweapontest.Config;

import java.util.Iterator;

public class Plane extends AbsGameObj {
    TextureAtlas.AtlasRegion bodyImg,bodyImgFlip;
    Array<Bomb> bombs=new Array<Bomb>();
    Explosion explosion;

    float speed;
    int framesPerBomb;//多少帧放一次炸弹
    int curFrames=0;
    int bombsPerTime;//每次投放多少炸弹
    int curBombs=0;
    int curBombsFrames=0;
    int bombType;

    boolean direction;//方向：true为向右

    public static Plane crtBigBomber(){
        return new Plane(PlaneType.bigbomber.getValue(),Config.getSmallJetBombSpeed(),Config.getSmallJetHealth(),1,1);
    }
    public static Plane crtBlimp(){
        return new Plane(PlaneType.blimp.getValue(),Config.getBlimpBombSpeed(),Config.getBlimpHealth(),5,0);
    }
    public static Plane crtBomber(){
        return new Plane(PlaneType.bomber.getValue(),Config.getBomberHealth(),Config.getSmallJetHealth(),3,0);
    }
    public static Plane crtDeflector(){
        return new Plane(PlaneType.deflector.getValue(),Config.getSmallJetBombSpeed(),Config.getSmallJetHealth());
    }
    public static Plane crtDeltaBomber(){
        return new Plane(PlaneType.deltabomber.getValue(),Config.getDeltaBomberBombSpeed(),Config.getDeltaBomberHealth(),1,2);
    }
    public static Plane crtDeltaJet(){
        return new Plane(PlaneType.deltajet.getValue(),Config.getSmallJetBombSpeed(),Config.getSmallJetHealth());
    }
    public static Plane crtFatBomber(){
        return new Plane(PlaneType.fatbomber.getValue(),Config.getSmallJetBombSpeed(),Config.getSmallJetHealth(),1,3);
    }
    public static Plane crtSmallJet(){
        return new Plane(PlaneType.smalljet.getValue(),Config.getSmallJetBombSpeed(),Config.getSmallJetHealth());
    }
    public static Plane crtSuperBomber(){
        return new Plane(PlaneType.superbomber.getValue(),Config.getSmallJetBombSpeed(),Config.getSmallJetHealth(),10,1);
    }

    private Plane(int plane, int framesPerBomb, int health){
        this( plane, framesPerBomb,  health,1,0);
    }

    private Plane(int plane,int framesPerBomb, int health,int bombsPerTime, int bombType){
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
    }

    @Override
    void updateBeforeExplosion() {
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

    @Override
    void updateBeforeRemove() {
        for(Iterator<Bomb> iter = bombs.iterator(); iter.hasNext();){
            Bomb b=iter.next();
            if (b.state==State.REMOVABLE){
                iter.remove();
                continue;
            }
            b.update();
        }
    }

    @Override
    boolean onHit(int damage) {
        boolean res=super.onHit(damage);
        if (res&&state==State.EXPLOSION){
            explosion=new Explosion(body,velocity.x);
            Config.score+=MathUtils.random(10000,20000);
        }
        return res;
    }

    private void emitBomb() {
        Assets.getInstance().bombfall.play();
        Bomb bomb=new Bomb(body.x+body.width/2,body.y,direction,speed,bombType);
        bombs.add(bomb);
    }

    @Override
    void updateVelocity() {}

    @Override
    void renderOnAlive(SpriteBatch batch) {
        batch.draw(direction?bodyImg:bodyImgFlip,body.x,body.y,body.width,body.height);
    }

    @Override
    void renderOnExplosion(SpriteBatch batch) {
        //if (explosion==null)explosion=new Explosion(body,velocity.x);
        if (explosion.end) state=State.DEAD;
        else explosion.render(batch);
    }

    @Override
    void renderOnDead(SpriteBatch batch) {
        if (bombs.size==0)state=State.REMOVABLE;
    }

    @Override
    void renderOnRemove(SpriteBatch batch) {

    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);

        for(int i=0;i<bombs.size;i++)
            bombs.get(i).render(batch);
    }
}

enum PlaneType {
    bigbomber(0),
    blimp(1),
    bomber(2),
    deflector(3),
    deltabomber(4),
    deltajet(5),
    fatbomber(6),
    jetfighter(7),
    smalljet(8),
    superbomber(9);

    int value;
    PlaneType(int value){
        this.value=value;
    }

    public int getValue(){
        return value;
    }
}