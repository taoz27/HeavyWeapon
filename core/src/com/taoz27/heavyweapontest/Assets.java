package com.taoz27.heavyweapontest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {
    private static final Assets instance=new Assets();

    TextureAtlas tankAtlas;
    public Array<TextureAtlas.AtlasRegion> tankImgs;
    public Texture tankShadow;

    TextureAtlas bulletAtlas;
    public Array<TextureAtlas.AtlasRegion> bulletImgs;

    TextureAtlas planeAtlas;
    public Array<TextureAtlas.AtlasRegion> planeImgs;
    public Texture planeSmoke;
    
    TextureAtlas dumbbombAtlas,ironbombAtlas,fragbombAtlas,fatboyAtlas;
    public Array<Array<TextureAtlas.AtlasRegion>> bombImgs;

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
        tankAtlas = new TextureAtlas(Gdx.files.internal("images/tank/tank_images.atlas"));
        tankImgs=tankAtlas.getRegions();
        tankShadow=new Texture(Gdx.files.internal("images/tank/tankshadow.png"));
        
        planeAtlas = new TextureAtlas(Gdx.files.internal("images/plane/plane_images.atlas"));
        planeImgs=planeAtlas.getRegions();
        planeSmoke=new Texture(Gdx.files.internal("images/plane/smoke.png"));
        
        bulletAtlas=new TextureAtlas(Gdx.files.internal("images/bullet_images.atlas"));
        bulletImgs=bulletAtlas.getRegions();
        
        bombImgs=new Array<Array<TextureAtlas.AtlasRegion>>(5);
        dumbbombAtlas=new TextureAtlas(Gdx.files.internal("images/bombs/dumbbomb.atlas"));
        bombImgs.add(dumbbombAtlas.getRegions());
        ironbombAtlas=new TextureAtlas(Gdx.files.internal("images/bombs/ironbomb.atlas"));
        bombImgs.add(ironbombAtlas.getRegions());
        fragbombAtlas=new TextureAtlas(Gdx.files.internal("images/bombs/fragbomb.atlas"));
        bombImgs.add(fragbombAtlas.getRegions());
        fatboyAtlas=new TextureAtlas(Gdx.files.internal("images/bombs/fatboy.atlas"));
        bombImgs.add(fatboyAtlas.getRegions());

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
        tankShadow.dispose();

        planeAtlas.dispose();
        planeSmoke.dispose();

        bulletAtlas.dispose();

        dumbbombAtlas.dispose();
        ironbombAtlas.dispose();
        fragbombAtlas.dispose();
        fatboyAtlas.dispose();

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
