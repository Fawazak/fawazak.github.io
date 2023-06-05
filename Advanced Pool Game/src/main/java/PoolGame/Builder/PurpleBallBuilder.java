package PoolGame.Builder;

import PoolGame.Items.Ball;
import PoolGame.Items.Ball.BallType;
import PoolGame.Strategy.BallPocketStrategy;
import javafx.scene.control.Button;

/** Builder for blue ball */
public class PurpleBallBuilder implements BallBuilder {

    private Ball ball;
    private BallType ballType = null;
    private BallPocketStrategy action = null;
    private int point;
    private Button button;


    /** Initialise the builder and start a new build */
    public PurpleBallBuilder() {
        this.reset();
    }

    /**
     * Initialise a builder with the ball type and action for the new build
     * @param type The ball type the builder will build
     * @param action The action that the ball have when it falls into a pocket
     * @param point The point that each ball has
     * @param b The button associated with the ball for the cheat method
     */
    public PurpleBallBuilder(BallType type, BallPocketStrategy action, int point, Button b) {
        this.ballType = type;
        this.action = action;
        this.point = point;
        this.button = b;
        this.reset();
    }

    public void reset() {
        this.ball = new Ball();
        this.ball.setColour("purple");
        this.ball.setPoint(point);
        this.ball.setButton(button);

        if (ballType != null) {
            this.ball.setBallType(this.ballType);
        }
        if (this.action != null) {
            this.ball.setPocketAction(this.action);
        }
    }

    public void setXPos(double xPos) {
        this.ball.setInitialXPos(xPos);
    }

    public void setYPos(double yPos) {
        this.ball.setInitialYPos(yPos);
    }

    public void setXVel(double xVel) {
        this.ball.setInitialXVel(xVel);
    }

    public void setYVel(double yVel) {
        this.ball.setInitialYVel(yVel);
    }

    public void setMass(double mass) {
        this.ball.setMass(mass);
    }

    public void setBallType(BallType type) {
        this.ballType = type;
        this.ball.setBallType(type);
    }

    @Override
    public void setPoint(int point) {
        this.point = point;
        this.ball.setPoint(point);
    }

    public void setPocketAction(BallPocketStrategy action) {
        this.action = action;
        this.ball.setPocketAction(action);
    }

    public Ball finaliseBuild() {
        Ball ball = this.ball;
        this.reset();
        return ball;
    }
}
