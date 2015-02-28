package ru.ildev.libgdx.examples;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import ru.ildev.libgdx.examples.utils.GraphicsExample;

/**
 * @author Ilyas Shafigin
 * @version 1.0
 * @since 27.02.15
 */
public class SphereExample extends GraphicsExample {

    private float step = 15.0f; // ms
    private float time = 0.0f; // ms
    private float t = 0.0f;

    public void init() {
        this.background = Color.BLACK;
        this.foreground = Color.WHITE;
    }

    @Override
    public void update(float dt) {
        this.time += dt*1000;
    }

    @Override
    public void draw() {
        int w = this.width;
        int h = this.height;
        int hw = w / 2;
        int hh = h / 2;

        if(this.time >= this.step) {
            this.t += 0.1;
            this.time -= this.step;
        }

        this.renderer.begin(ShapeRenderer.ShapeType.Point);
        this.renderer.setColor(new Color(0xffff9900));

        // @author Hakim El Hattab | http://hakim.se
        int i = 10000;
        while(i-- > 0) {
            float r = (float) ((hw + hh) * (Math.cos((this.t + i) * (0.05f + Math.sin(this.t * 0.00002f) / Math.PI * 0.2f)) / Math.PI));
            float x = (float) Math.sin(i) * r + hw;
            float y = (float) Math.cos(i) * r + hh;
            // -
            this.renderer.point(x, y, 0);
            // -
        }
        // -------
        this.renderer.end();
    }

}
