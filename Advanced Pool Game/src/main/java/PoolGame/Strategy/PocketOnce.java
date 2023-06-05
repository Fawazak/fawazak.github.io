package PoolGame.Strategy;

import PoolGame.Game;
import PoolGame.Items.Ball;

/** Hide the ball once it falls into the pocket */
public class PocketOnce implements BallPocketStrategy {
    /** The number of times the ball has to fall to be completely disables and hidden from the table*/
    public final int FALL_COUNTER_THRESHOLD = 1;
    public void fallIntoPocket(Game game, Ball ball) {
        ball.incrementFallCounter();

        if (ball.getFallCounter() >= FALL_COUNTER_THRESHOLD) {
            game.in = true;
            game.addScore(ball.getPoint());
            ball.disable();


        }


    }

    @Override
    public int getThresh() {
        return FALL_COUNTER_THRESHOLD;
    }
}
