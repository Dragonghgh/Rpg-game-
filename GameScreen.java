package com.zelda;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {
    private SpriteBatch batch;
    private Player player;
    private List<Enemy> enemies;
    private List<Wall> walls;

    @Override
    public void show() {
        batch = new SpriteBatch();
        player = new Player();
        enemies = new ArrayList<>();
        walls = new ArrayList<>();

        // Add some enemies
        enemies.add(new Enemy(300, 300));

        // Add walls (simple map)
        for (int i = 0; i < 10; i++) {
            walls.add(new Wall(i * 32, 0)); // Bottom wall
            walls.add(new Wall(i * 32, 500)); // Top wall
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update
        player.update(delta);
        for (Enemy enemy : enemies) {
            enemy.update(delta, player);
        }

        // Check collisions
        for (Wall wall : walls) {
            if (player.getHitbox().overlaps(wall.getHitbox())) {
                // Simple collision response (stop player)
                player.getHitbox().x = player.getHitbox().x < wall.getHitbox().x ?
                    wall.getHitbox().x - player.getHitbox().width :
                    wall.getHitbox().x + wall.getHitbox().width;
            }
        }

        // Attack logic
        if (player.isAttacking()) {
            Rectangle swordArea = new Rectangle(
                player.getHitbox().x,
                player.getHitbox().y,
                player.getHitbox().width + 20,
                player.getHitbox().height + 20
            );
            enemies.removeIf(enemy -> swordArea.overlaps(enemy.getHitbox()));
        }

        // Render
        batch.begin();
        for (Wall wall : walls) wall.render(batch);
        player.render(batch);
        for (Enemy enemy : enemies) enemy.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
