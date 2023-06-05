package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;


import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.List;


class GameWindow {
    private final GraphicsContext gc;
    private Scene scene;
    private Table table;
    private List<Components> balls;
    private List<List<Double>> pockets = new ArrayList<>();
    private BallPit b;
    private Pane pane;
    final boolean[] added = {false};

    GameWindow(Table table, List<Components> balls, List<List<Double>> pockets) {
        this.table = table;
        this.balls = balls;
        this.pockets = pockets;

        b = new BallPit(balls, table, pockets, this);
        this.balls = b.getBalls();
        pane = new Pane();

        this.scene = new Scene(pane, table.getX(), table.getY());

        this.scene.setFill(Paint.valueOf(table.getColour()));
        Canvas canvas = new Canvas(table.getX(), table.getY());
        gc = canvas.getGraphicsContext2D();
        pane.getChildren().add(canvas);



    }
    void setBalls(List<Components> new_ball){
        this.balls = new_ball;
    }

    Scene getScene() {
        return this.scene;
    }
//    void hit(Components ball){
//        ball.
//    }
    void run() {

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17),
                t -> this.draw()));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    void setPit(BallPit p){
        b = p;
    }
    private void draw() {
        b.tick();
        gc.clearRect(0, 0, table.getX(), table.getY());
        if(balls.size() == 1){
            System.out.println("win and bye");
            return;
        }
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {


            @Override
            public void handle(MouseEvent event) {
//                System.out.println(event.getY());
                //Adding a cue stick when pressing on white cue ball
                if(added[0] == false) {
                    for (Components c : balls) {
                        if (c.getColour().equals("white") && (event.getSceneX() >= c.getX() - 25 && event.getSceneX() <= c.getX() + 25) &&
                                (event.getSceneY() >= c.getY() - 25 && event.getSceneY() <= c.getY() + 25)) {
                            added[0] = true;
                            Line cue_stick = new Line(c.getX(), c.getY(), c.getX() + 20, c.getY() + 20);
                            cue_stick.setStrokeWidth(3);
                            pane.getChildren().add(cue_stick);
                            Rotate rotate = new Rotate(0);

                            rotate.setPivotX(c.getX());
                            rotate.setPivotY(c.getY());

                            cue_stick.getTransforms().add(rotate);

                            pane.setOnMouseMoved((event2) -> {
                                double newX = event2.getSceneX();
                                double newY = event2.getSceneY();
                                rotate.setAngle(Math.toDegrees(Math.atan2(newY - c.getY(), newX - c.getX())));
                            });
                            final double[] ix = {0};
                            final double[] iy = {0};
                            pane.setOnMouseDragged((event3 -> {
                                cue_stick.setEndX(cue_stick.getEndX() + 0.7);
                                cue_stick.setEndY(cue_stick.getEndY() + 0.7);

//                                System.out.println(cue_stick.getEndX()/1000);
                                ix[0]+=(Math.abs(cue_stick.getEndX()-c.getX())/1000+0.1);
                                iy[0]+=(Math.abs(cue_stick.getEndY()-c.getY())/1000+0.1);

//                                System.out.println(cue_stick.getEndX()+" "
//                                + cue_stick.getEndY());

                            }));
                            pane.setOnMouseReleased((event3 -> {
                                pane.getChildren().remove(cue_stick);
//                                System.out.println(event3.getX()+ "  "+ event3.getY());
//                                System.out.println(c.getX()+ "  "+c.getY());


                                added[0] = false;
//                                if(event3.getX() <= c.getX()+25 && event3.getY() >= c.getY()-25){
//                                    c.setyVel(iy[0]);
//                                    c.setxVel(1);
//
//                                }

                                if(event3.getX() > c.getX() &&  event3.getY() > c.getY()){
                                    c.setxVel(-ix[0]);
                                    c.setyVel(-iy[0]);
                                }else if(event3.getX() < c.getX() &&  event3.getY() < c.getY()){
                                    c.setxVel(ix[0]);
                                    c.setyVel(iy[0]);
                                }else if(event3.getX() > c.getX() &&  event3.getY() < c.getY()){
                                    c.setxVel(-ix[0]);
                                    c.setyVel(iy[0]);
                                }else if(event3.getX() < c.getX() &&  event3.getY() > c.getY()){
                                    c.setxVel(ix[0]);
                                    c.setyVel(-iy[0]);
                                }

                                rotate.setPivotX(c.getX());
                                rotate.setPivotY(c.getY());
                                cue_stick.setEndY(c.getY());
                                cue_stick.setEndX(c.getX());
                                ix[0] = 0;
                                iy[0] = 0;

                            }));
                        }
                    }
                }
            }
        });
        for(List<Double> l: pockets){
            gc.setFill(Paint.valueOf("GREY"));
            gc.fillOval(l.get(0) -  22,
                    l.get(1) - 22,
                    22 * 2,
                    22 * 2);
        }

        for(Components c: b.getBalls()){

            gc.setFill(Paint.valueOf(c.getColour()));
            gc.fillOval(c.getX() -  15,
                    c.getY() - 15,
                    15 * 2,
                    15 * 2);
        }

    }
}
