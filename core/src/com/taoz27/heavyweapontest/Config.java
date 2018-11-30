package com.taoz27.heavyweapontest;

public class Config {
    private static final int standScreenHeight =450;
    private static final int standGroundHeight=30;
    private static final int standPlaneLowestHeight=standScreenHeight*5/9;

    private static int screenWidth=950, screenHeight=450;
    private static float screenRatio=1;

    private static final float gravity=0.3f;
    private static final float standTankSpeed=6;
    private static final float standSmallJetSpeed=5;
    private static final float standBulletSpeed=15;
    private static final float standBombSpeed=4;

    private static final int bulletDamage =10;
    private static final int bombDamage =20;

    private static final int tankHealth=300;
    private static final int smallJetHealth=30;
    private static final int bulletHealth=10;
    private static final int bombHealth=30;

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

    public static int getGroundHeight() {
        return (int)(standGroundHeight*screenRatio);
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

    public static float getSmallJetSpeed() {
        return standSmallJetSpeed*screenRatio;
    }

    public static int getPlaneLowestHeight(){
        return (int)(standPlaneLowestHeight*screenRatio);
    }

    public static float getBombSpeed() {
        return standBombSpeed*screenRatio;
    }

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

    public static int getBulletHealth() {
        return bulletHealth;
    }

    public static int getBombHealth() {
        return bombHealth;
    }

    public static void calScreenRatio(){
        screenRatio=screenHeight/ standScreenHeight;
    }
}
