package com.example.cs205proj;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class EnemyShootState extends BaseState {

    private int currentAnimation = 0;
    private final Enemy enemy;
    private final Animation[] animations;
    private final Rect spriteRect;

    public EnemyShootState(Enemy enemy, EnemyFrames enemyFrames) {
        this.enemy = enemy;
        this.spriteRect = enemyFrames.getSpriteRect();

        Animation[] shootingAnimation = {new Animation(enemyFrames.rightShootFrames, true, 200), new Animation(enemyFrames.leftShootFrames, true, 200)};
        this.animations = shootingAnimation;
    }

    public void update(long deltaTime) {
        if (enemy.getDirection().equals("left")) {
            currentAnimation = 1;
        }

        if (animations[currentAnimation].timesPlayed > 0) {
            animations[currentAnimation].timesPlayed = 0;
            enemy.enemyStateMachine.changeState("walk");
        }

        animations[currentAnimation].update(deltaTime);
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(animations[currentAnimation].getCurrentFrame(), spriteRect, new Rect(enemy.getX(), enemy.getY(), enemy.getX() + enemy.getWidth(), enemy.getY() + enemy.getHeight()), paint);
    }
}
