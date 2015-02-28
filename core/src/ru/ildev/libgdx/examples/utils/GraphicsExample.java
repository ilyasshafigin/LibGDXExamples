package ru.ildev.libgdx.examples.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * @author Ilyas Shafigin
 * @version 1.0
 * @since 27.02.15
 */
public class GraphicsExample extends GdxExample implements InputProcessor {

    protected SpriteBatch batch;
    protected ShapeRenderer renderer;
    protected BitmapFont font;

    protected int width;
    protected int height;

    protected Color background;
    protected Color foreground;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.renderer = new ShapeRenderer();

        this.font = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"), false);
        this.font.setColor(Color.BLACK);

        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();

        this.background = Color.WHITE;
        this.foreground = Color.BLACK;

        Gdx.input.setInputProcessor(this);

        this.init();
    }

    @Override
    public void resume () {
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(this.background.r, this.background.g, this.background.g, this.background.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.update(Gdx.graphics.getDeltaTime());
        this.draw();

        this.batch.begin();
        this.font.setColor(this.foreground);
        this.font.draw(this.batch, "fps: " + Gdx.graphics.getFramesPerSecond(), 0, 20);
        this.batch.end();
    }

    @Override
    public void resize (int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void pause () {
    }

    @Override
    public void dispose () {
        this.batch.dispose();
        this.renderer.dispose();
        this.font.dispose();
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int newParam) {
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        return false;
    }

    /**
     *
     */
    protected void init() {

    }

    /**
     *
     */
    protected void draw() {}

    /**
     *
     * @param dt
     */
    protected void update(float dt) {}

}
