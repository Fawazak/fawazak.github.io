package org.example;

public class BallFactory implements ComponentsFactory {

    private final String colour;
    private  double positionX;
    private  double positionY;
    private  double velocityX;
    private  double velocityY;
    private  double mass;




    public BallFactory(String colour, double positionX, double positionY,double velocityX,double velocityY, double mass) {
        this.colour = colour;
        this.mass = mass;
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;

    }
    @Override
    public Components create() {
//what to return what to call new ball class
        BallDirector director = new BallDirector();

        // Director gets the concrete builder object from the client
        // (application code). That's because application knows better which
        // builder to use to get a specific product.
        BallBuilderImpl builder = new BallBuilderImpl();
        director.construct(builder, colour, positionX, positionY,velocityX,velocityY, mass);

        // The final product is often retrieved from a builder object, since
        // Director is not aware and not dependent on concrete builders and
        // products.
        Components ball = builder.build();
        return ball;
    }
}
