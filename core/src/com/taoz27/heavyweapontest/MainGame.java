package com.taoz27.heavyweapontest;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.taoz27.heavyweapontest.gameobj.*;

public class MainGame extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera camera;

	Tank tank;
	Enemies enemies;
	Backgrounds bg;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		Config.setScreenWidth(Gdx.graphics.getWidth());Config.setScreenHeight(Gdx.graphics.getHeight());
		Config.calScreenRatio();
		camera.setToOrtho(false, Config.getScreenWidth(), Config.getScreenHeight());

		tank=new Tank();
		enemies=new Enemies();
		tank.setTarget(enemies);
		bg =new Backgrounds(1);

		Assets.getInstance().bgMusic.play();
		Assets.getInstance().prepare.play();
	}

	Vector3 touchPos=new Vector3();
	boolean isClickLeft(){
		if (touchPos.x<Config.getScreenWidth()/2)return true;
		return false;
	}
	boolean isClickRight(){
		if (touchPos.x>Config.getScreenWidth()/2)return true;
		return false;
	}
	
	void tankControl(){
		if(!Gdx.input.isTouched()&&Gdx.app.getType() != Application.ApplicationType.Desktop){
			tank.velocity.x=0;
			return;
		}
		if (Gdx.input.isTouched()) {
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			if (isClickLeft()&&isClickRight())tank.velocity.x=0;
			else if (isClickLeft())tank.velocity.x=-Config.getTankSpeed();
			else if (isClickRight())tank.velocity.x=Config.getTankSpeed();
			else tank.velocity.x=0;
		}else {
			if (Gdx.app.getType() != Application.ApplicationType.Desktop) return;
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) tank.velocity.x = 0;
			else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) tank.velocity.x = -Config.getTankSpeed();
			else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) tank.velocity.x = Config.getTankSpeed();
			else tank.velocity.x = 0;
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

		tankControl();
		tank.update();
		enemies.update();
		bg.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

        bg.render(batch);
		tank.render(batch);
		enemies.render(batch);


		int fps= Gdx.graphics.getFramesPerSecond();
		BitmapFont fpsFont=Assets.getInstance().font;
		if (fps>=45)fpsFont.setColor(0,1,0,1);
		else if(fps>=30)fpsFont.setColor(1,1,0,1);
		else fpsFont.setColor(1,0,0,1);
		fpsFont.draw(batch,"FPS:"+fps,0,445*Config.getScreenRatio());
		fpsFont.draw(batch,"SCORE:"+Config.score,0,430*Config.getScreenRatio());

		batch.end();

	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false,width,height);
	}

	@Override
	public void dispose () {
		batch.dispose();
		Assets.getInstance().dispose();
	}
}
