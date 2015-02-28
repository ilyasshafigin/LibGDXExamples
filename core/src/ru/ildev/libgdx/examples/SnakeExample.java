package ru.ildev.libgdx.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import ru.ildev.libgdx.examples.utils.GraphicsExample;

/**
 * @author Ilyas Shafigin
 * @version 1.0
 * @since 27.02.15
 */
public class SnakeExample extends GraphicsExample {

    int snake_segments = 50;
    float[] snake_x = new float[1000];
    float[] snake_y = new float[1000];
    int seg_length = 7;

    int mouse_segments = 10;
    float[] mouse_x;
    float[] mouse_y;
    float mouse_length = 2;
    float[] mouse_pos;

    @Override
    public void init() {
        this.snake_x = new float[this.snake_segments];
        this.snake_y = new float[this.snake_segments];
        for(int i = 0; i < this.snake_segments; i++) {
            this.snake_x[i] = this.width / 2 + i * this.seg_length;
            this.snake_y[i] = this.height / 2;
        }

        this.mouse_x = new float[this.mouse_segments];
        this.mouse_y = new float[this.mouse_segments];
        for(int i = 0; i < this.mouse_segments; i++) {
            this.mouse_x[i] = -10;
            this.mouse_y[i] = -10;
        }
        this.mouse_pos = new float[] {this.width / 2, this.height / 2};
    }

    @Override
    public void update(float dt) {
        this.mouse_pos[0] = Gdx.input.getX();
        this.mouse_pos[1] = this.height - Gdx.input.getY();
    }

    @Override
    public void draw() {
        this.dragMouse(this.mouse_pos[0], this.mouse_pos[1]);
        for(int i = 0; i < this.mouse_segments - 1; i++) {
            this.dragMouse(i + 1, this.mouse_x[i], this.mouse_y[i]);
        }

        this.dragStart(this.mouse_x[0], this.mouse_y[0]);
        for(int i = 0; i < this.snake_segments - 1; i++) {
            this.dragSegment(i + 1, this.snake_x[i], this.snake_y[i]);
        }

        for (int i = 0; i < this.mouse_segments; i++) {
            float rad = 5;
            if (i == 0) rad = 2;
            else if (i == 1) rad = 3;
            else if (i == 2) rad = 2;
            else if (i == 3) rad = 4;
            else if (i == 4) rad = 4;
            else if (i > 8) {
                rad = 2;
            }

            this.renderer.begin(ShapeRenderer.ShapeType.Filled);
            this.renderer.setColor(i%2==0 ? Color.DARK_GRAY : Color.BLACK);
            this.renderer.ellipse(this.mouse_x[i] - rad, this.mouse_y[i] - rad, rad * 2, rad * 2);
            this.renderer.end();
        }

        for (int i = 0; i < this.snake_segments; i++) {
            float rad = 10;
            if (i < 6) {
                rad = 10.0f + 8.0f * (i / 13.0f);
            } else if (i > 0.5f * this.snake_segments) {
                rad = this.seg_length + 20.0f * (this.snake_segments - i) / this.snake_segments;
                if (rad > 10) rad = 10;
            }

            this.renderer.begin(ShapeRenderer.ShapeType.Filled);
            this.renderer.setColor(i%2==0 ? new Color(0xffff9900) : Color.BLACK);
            this.renderer.ellipse(this.snake_x[i]-rad, this.snake_y[i]-rad, rad*2, rad*2);
            this.renderer.end();
        }
    }

    private void dragStart(float xin, float yin) {
        float dx = xin - this.snake_x[0];
        float dy = yin - this.snake_y[0];
        float angle = (float) Math.atan2(dy, dx);
        float dist = (float) Math.sqrt(dx * dx + dy * dy);
        float speed = 10;

        if (dist > 10 * speed) {
        } else if (dist > 2 * speed) {
            speed = dist / 10;
        } else {
            speed = 0;
        }
        this.snake_x[0] = xin - (float) Math.cos(angle) * (dist - speed);
        this.snake_y[0] = yin - (float) Math.sin(angle) * (dist - speed);
    }

    private void dragSegment(int i, float xin, float yin) {
        float dx = xin - this.snake_x[i];
        float dy = yin - this.snake_y[i];
        float angle = (float) Math.atan2(dy, dx);
        float dis = (float) Math.sqrt(dx*dx + dy*dy);
        if (dis == 0) return;
        this.snake_x[i] = xin - (float) Math.cos(angle) * this.seg_length;
        this.snake_y[i] = yin - (float) Math.sin(angle) * this.seg_length;
    }

    private void dragMouse(float xin, float yin) {
        float dx = xin - this.mouse_x[0];
        float dy = yin - this.mouse_y[0];
        float angle = (float) Math.atan2(dy, dx);
        float dist = (float) Math.sqrt(dx * dx + dy * dy);
        float speed = 30;

        if (dist < speed) {
            speed = dist;
        }
        this.mouse_x[0] = xin - (float) Math.cos(angle) * (dist - speed);
        this.mouse_y[0] = yin - (float) Math.sin(angle) * (dist - speed);
    }

    private void dragMouse(int i, float xin, float yin) {
        float dx = xin - this.mouse_x[i];
        float dy = yin - this.mouse_y[i];
        float angle = (float) Math.atan2(dy, dx);
        float dis = (float) Math.sqrt(dx*dx + dy*dy);
        if (dis == 0) return;
        this.mouse_x[i] = xin - (float) Math.cos(angle) * this.mouse_length;
        this.mouse_y[i] = yin - (float) Math.sin(angle) * this.mouse_length;
    }

}
