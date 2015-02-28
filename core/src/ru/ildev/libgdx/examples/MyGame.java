package ru.ildev.libgdx.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.ildev.libgdx.examples.utils.GdxExample;

/**
 * @author Ilyas Shafigin
 * @version 1.0
 * @since 25.02.15
 */
public class MyGame extends GdxExample {

	SpriteBatch batch;
	Texture img;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.img = new Texture("badlogic.jpg");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.batch.begin();
        this.batch.draw(this.img, 0, 0);
        this.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        this.batch.dispose();
        this.img.dispose();
    }
}
