package ru.ildev.libgdx.examples;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

import ru.ildev.libgdx.examples.utils.GraphicsExample;

/**
 * @author Ilyas Shafigin
 * @version 1.0
 * @since 27.02.15
 */
public class WaterExample extends GraphicsExample {

    private int md = 6, area = 2, number, offset;
    private int w = 10, hw = this.w / 2;
    private float lev = -30.0f, k1 = 0.06f, k2 = 0.09f;
    private float damping = 0.96f;
    private float mouseAdd = 30.0f, md1 = this.md - 1.0f;
    private float[] value, speed;

    @Override
    protected void init() {
        this.number = this.width / this.w;
        this.offset = this.height / 2;

        this.value = new float[this.number];
        this.speed = new float[this.number];

        for(int i = 0; i < this.number; i++) {
            this.value[i] = this.speed[i] = 0.0f;
        }
    }

    @Override
    protected void update(float dt) {
        for(int i = 0; i < this.number; i++) {
            float v = this.value[i];
            this.speed[i] -= this.k1 * (v - this.lev);

            for(int j = 1; j <= this.area; j++) {
                this.speed[i] += this.k2 * (this.value[Math.max(i - j, 0)] +
                        this.value[Math.min(i + j, this.number - 1)] - 2 * v) / j;
            }
        }

        for(int i = 0; i < this.number; i++) {
            this.value[i] += this.speed[i] *= this.damping;
        }
    }

    @Override
    protected void draw() {
        this.renderer.begin(ShapeRenderer.ShapeType.Line);
        this.renderer.setColor(new Color(0.25f, 0.71f, 0.98f, 1f));
        for(int i = 0; i < this.number - 1; i++) {
            float x1 = this.hw + i * this.w;
            float y1 = this.offset - this.value[i];
            float x2 = this.hw + (i + 1) * this.w;
            float y2 = this.offset - this.value[i + 1];

            this.renderer.line(x1, y1, x2, y2);
        }
        this.renderer.end();
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int newParam) {
        int ix = (MathUtils.clamp(x, 0, this.width - 1) - this.hw) / this.w;
        float m = y > this.offset ? this.mouseAdd : -this.mouseAdd;
        this.speed[ix] += m;

        for(int i = 1; i < this.md; i++) {
            int i2 = ix - i;
            if(i2 >= 0) this.speed[i2] += m * this.herm((this.md1 - i) / this.md1);

            i2 = ix + i;
            if(i2 < this.number) this.speed[i2] += m * this.herm((this.md1 - i) / this.md1);
        }

        return true;
    }

    private float herm(float t) {
        return t * t * (3.0f - 2.0f * t);
    }

}
