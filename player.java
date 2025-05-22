package com.zelda;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    private Sprite sprite;
    private float speed = 150f;
    private Rectangle hitbox;

    public Player() {
        sprite = new Sprite(new Texture("assets/player.png"));
        sprite.setPosition(100, 100);
        hitbox = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public void update(float deltaTime) {
        float moveX = 0, moveY = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) moveX -= speed * deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) moveX += speed * deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.UP) moveY += speed * deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) moveY -= speed * deltaTime;

        sprite.translate(moveX, moveY);
        hitbox.setPosition(sprite.getX(), sprite.getY());
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public boolean isAttacking() {
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
    }
}
