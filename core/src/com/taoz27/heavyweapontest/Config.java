package com.taoz27.heavyweapontest;

import com.badlogic.gdx.math.MathUtils;

public class Config {
    private static final int standScreenHeight =450;
    private static final int standPlaneLowestHeight=standScreenHeight*5/9;

    private static int screenWidth=950, screenHeight=450;
    private static float screenRatio=1;

    private static final float gravity=0.2f;
    private static final float standTankSpeed=6;
    private static final float standMinPlaneSpeed=2.5f;
    private static final float standMaxPlaneSpeed=7f;
    private static final float standBulletSpeed=10;
    //private static final float standBombSpeed=4;

    private static final int tankFireSpeed=10;
    private static final int smallJetBombSpeed=80;
    private static final int blimpBombSpeed=80;
    private static final int bomberBombSpeed=80;
    private static final int deltaBomberBombSpeed=80;

    private static final int bulletDamage =10;
    private static final int bombDamage =20;

    private static final int tankHealth=300;
    private static final int smallJetHealth=30;
    private static final int blimpHealth=150;
    private static final int bomberHealth=60;
    private static final int deltaBomberHealth=80;

    private static final int bulletHealth=10;
    private static final int bombHealth=10;



    private static final float standBgSpeed=4;
    private static final float standBgGroundHeight=0;
    private static final float standBgGroundHeightScale=60f/60;
    private static final float standBgMountainHeight=20;
    private static final float standBgMountainHeightScale=300f/300;
    private static final float standGroundHeight=60f/2;
    private static final float standBgSkyHeight=90;
    private static final float standBgSkyHeightScale=360f/480;

    public static void setScreenWidth(int screenWidth) {
        Config.screenWidth = screenWidth;
    }

    public static void setScreenHeight(int screenHeight) {
        Config.screenHeight = screenHeight;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    public static float getScreenRatio() {
        return screenRatio;
    }

    public static float getGroundHeight() {
        return standGroundHeight*screenRatio;
    }

    public static float getGravity() {
        return gravity;
    }

    public static float getTankSpeed() {
        return standTankSpeed*screenRatio;
    }

    public static float getBulletSpeed() {
        return standBulletSpeed*screenRatio;
    }

    public static float getPlaneSpeed() {
        return MathUtils.random(standMinPlaneSpeed*screenRatio,standMaxPlaneSpeed*screenRatio);
    }

    public static int getPlaneLowestHeight(){
        return (int)(standPlaneLowestHeight*screenRatio);
    }

//    public static float getBombSpeed() {
//        return standBombSpeed*screenRatio;
//    }

    public static int getBulletDamage() {
        return bulletDamage;
    }

    public static int getBombDamage() {
        return bombDamage;
    }

    public static int getTankHealth() {
        return tankHealth;
    }

    public static int getSmallJetHealth() {
        return smallJetHealth;
    }

    public static int getBlimpHealth() {
        return blimpHealth;
    }

    public static int getBomberHealth() {
        return bomberHealth;
    }

    public static int getDeltaBomberHealth() {
        return deltaBomberHealth;
    }

    public static int getBulletHealth() {
        return bulletHealth;
    }

    public static int getBombHealth() {
        return bombHealth;
    }

    public static int getTankFireSpeed() {
        return tankFireSpeed;
    }

    public static int getSmallJetBombSpeed() {
        return smallJetBombSpeed;
    }

    public static int getBlimpBombSpeed() {
        return blimpBombSpeed;
    }

    public static int getBomberBombSpeed() {
        return bomberBombSpeed;
    }

    public static int getDeltaBomberBombSpeed() {
        return deltaBomberBombSpeed;
    }


    public static float getBgSpeed() {
        return standBgSpeed*screenRatio;
    }

    public static float getBgGroundHeight() {
        return standBgGroundHeight*screenRatio;
    }

    public static float getBgGroundHeightScale() {
        return standBgGroundHeightScale;
    }

    public static float getBgMountainHeight() {
        return standBgMountainHeight*screenRatio;
    }

    public static float getBgMountainHeightScale() {
        return standBgMountainHeightScale;
    }

    public static float getBgSkyHeight() {
        return standBgSkyHeight*screenRatio;
    }

    public static float getBgSkyHeightScale() {
        return standBgSkyHeightScale;
    }


    public static void calScreenRatio(){
        screenRatio=screenHeight/ standScreenHeight;
    }
}
