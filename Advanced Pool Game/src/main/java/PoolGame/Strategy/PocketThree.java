package PoolGame.Strategy;

import PoolGame.Game;
import PoolGame.Items.Ball;
/** Hide the ball once it falls into the pocket three times or spawn it back to its
 * original location. If there is a ball on the original location, hide the ball.
 */
public class PocketThree implements BallPocketStrategy {
    /** The number of times the ball has to fall to be completely disables and hidden from the table*/
    public final int FALL_COUNTER_THRESHOLD = 3;

    public void fallIntoPocket(Game game, Ball ball) {

        ball.incrementFallCounter();
        if (ball.getFallCounter() >= FALL_COUNTER_THRESHOLD) {
            game.addScore(ball.getPoint());
            ball.disable();
        } else {
            game.addScore(ball.getPoint());
            ball.resetPosition();
            for (Ball ballB: game.getPoolTable().getBalls()) {
                if (ball.isColliding(ballB)) {
                    ball.disable();
                }
            }
        }
    }

    @Override
    public int getThresh() {
        return FALL_COUNTER_THRESHOLD;
    }
}
