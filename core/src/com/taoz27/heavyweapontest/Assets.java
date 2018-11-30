package com.taoz27.heavyweapontest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {
    private static final Assets instance=new Assets();

    TextureAtlas tankAtlas,planeAtlas,bulletAtlas,bombAtlas;
    public Array<TextureAtlas.AtlasRegion> tankImgs;
    public Array<TextureAtlas.AtlasRegion> planeImgs;
    public Array<TextureAtlas.AtlasRegion> bulletImgs;
    public Array<TextureAtlas.AtlasRegion> bombImgs;

    public BitmapFont font;

    public static Assets getInstance() {
        if (instance!=null)
            return instance;
        return new Assets();
    }
    
    public Assets(){
        tankAtlas = new TextureAtlas(Gdx.files.internal("images/tank_images.atlas"));
        tankImgs=tankAtlas.getRegions();
        planeAtlas = new TextureAtlas(Gdx.files.internal("images/plane_images.atlas"));
        planeImgs=planeAtlas.getRegions();
        bulletAtlas=new TextureAtlas(Gdx.files.internal("images/bullet_images.atlas"));
        bulletImgs=bulletAtlas.getRegions();
        bombAtlas=new TextureAtlas(Gdx.files.internal("images/bomb_images.atlas"));
        bombImgs=bombAtlas.getRegions();

        font=new BitmapFont();
    }

    @Override
    public void dispose() {
        tankAtlas.dispose();
        planeAtlas.dispose();
        bulletAtlas.dispose();
        bombAtlas.dispose();

        font.dispose();
    }
}
