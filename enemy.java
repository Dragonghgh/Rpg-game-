package com.zelda;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy {
    private Sprite sprite;
    private float speed = 100f;
    private Rectangle hitbox;

    public Enemy(float x, float y) {
        sprite = new Sprite(new Texture("assets/enemy.png"));
        sprite.setPosition(x, y);
        hitbox = new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
    }

    public void update(float deltaTime, Player player) {
        Vector2 direction = new Vector2(
            player.getHitbox().x - hitbox.x,
            player.getHitbox().y - hitbox.y
        ).nor(); // Normalize to get direction

        sprite.translate(direction.x * speed * deltaTime, direction.y * speed * deltaTime);
        hitbox.setPosition(sprite.getX(), sprite.getY());
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
