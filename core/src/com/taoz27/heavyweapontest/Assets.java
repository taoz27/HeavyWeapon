package com.taoz27.heavyweapontest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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

    TextureAtlas bg1Atlas;
    public Array<TextureAtlas.AtlasRegion> bg1Imgs;

    TextureAtlas explosionAtlas;
    public Array<TextureAtlas.AtlasRegion> explosionImgs;

    public BitmapFont font;

    public Sound prepare,bombfall,bullethit,tankfire;
    public Music bgMusic;

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

        bg1Atlas=new TextureAtlas(Gdx.files.internal("images/bg1_images.atlas"));
        bg1Imgs=bg1Atlas.getRegions();

        explosionAtlas=new TextureAtlas(Gdx.files.internal("images/explosion_images.atlas"));
        explosionImgs=explosionAtlas.getRegions();

        font=new BitmapFont();

        prepare=Gdx.audio.newSound(Gdx.files.internal("sound/v_prepare.ogg"));
        bombfall=Gdx.audio.newSound(Gdx.files.internal("sound/bombfall.ogg"));
        bullethit=Gdx.audio.newSound(Gdx.files.internal("sound/bullethit.ogg"));
        tankfire=Gdx.audio.newSound(Gdx.files.internal("sound/tankfire.ogg"));
        bgMusic=Gdx.audio.newMusic(Gdx.files.internal("music/atomictank.mp3"));
        bgMusic.setVolume(0.5f);
        bgMusic.setLooping(true);
    }

    @Override
    public void dispose() {
        tankAtlas.dispose();
        planeAtlas.dispose();
        bulletAtlas.dispose();
        bombAtlas.dispose();

        bg1Atlas.dispose();

        explosionAtlas.dispose();

        font.dispose();

        prepare.dispose();
        bombfall.dispose();
        bullethit.dispose();
        tankfire.dispose();
        if (bgMusic.isPlaying())bgMusic.stop();
        bgMusic.dispose();
    }
}
