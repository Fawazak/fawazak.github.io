package PoolGame.Items;

import PoolGame.Drawable;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/** The pocket of a pool table */
public class Pocket implements Drawable {
    
    /** The radius of the pocket */
//    public static final double RADIUS = Ball.RADIUS + 5;
    /** The colour of the pocket */
    protected Color colour = Color.BLACK;
    /** The JavaFX shape of the pocket */
    protected Circle shape;
    private double posX;
    private double posY;

    private double radius;
    /**
     * Initialise the pool table pocket with the provided value
     * @param posX The x coordinate position of the pocket
     * @param posY The y coordinate position of the pocket
     * @param radius The radius of the pocket
     */
    public Pocket(double posX, double posY, double radius) {
        this.shape = new Circle(posX, posY, radius, this.colour);
    }



    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("radius for pocket cannot be less than or equal to 0");
        }
        this.radius = radius;
    }
    public void setXPos(double xPos) {
        this.shape.setCenterX(xPos);
    }

    public void setYPos(double yPos) {
        this.shape.setCenterY(yPos);
    }
    /**
     * Check if a point is in the pocket bounds
     * @param point A point to check.
     * @return True if the point is in the pocket bounds, false otherwise
     */
    public boolean isInPocket(Point2D point) {
        return this.shape.getBoundsInLocal().contains(point);
    }

    public Node getNode() {
        return this.shape;
    }

    public void addToGroup(ObservableList<Node> groupChildren) {
        groupChildren.add(this.shape);
    }
}
