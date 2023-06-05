package org.example;

public class TableFactory implements ComponentsFactory {


    private double y;
    private double x;
    private final String colour;
    private  double friction;
    public TableFactory(String colour, double friction, double x, double y) {
        this.colour = colour;
        this.x = x;
        this.y = y;
        this.friction =friction;

    }
    @Override
    public Components create() {
        return new Table(colour, friction,x,y);
    }
}
