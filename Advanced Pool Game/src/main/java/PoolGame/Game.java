package PoolGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import PoolGame.Builder.BallBuilderDirector;
import PoolGame.Config.BallConfig;
import PoolGame.Config.PocketConfig;
import PoolGame.Config.PocketsConfig;
import PoolGame.Items.Ball;
import PoolGame.Items.Pocket;
import PoolGame.Items.PoolTable;
import PoolGame.Memento.CareTaker;
import PoolGame.Observer.Observer;
import PoolGame.Observer.Subject;
import PoolGame.Strategy.PocketOnce;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/** The game class that runs the game */
public class Game implements Subject {

    private PoolTable table;
    private List<Observer> observers = new ArrayList<>();
    /** A boolean to be shown when the game is won*/
    public boolean shownWonText = false;
    /** A boolean to check if the level has been changed*/
    public boolean change =  false;
    /** A boolean to check if the cue ball has fell in order to reset*/
    public boolean fell = false;
    /** A boolean to check if the specific ball is in the pocket*/
    public boolean in = false;
    /** A boolean to check if cheat button has been pressed*/
    public boolean cheat = false;

    private int score = 0;
    private Text timer;
    private Button undo = new Button("Undo");

    private final Text winText = new Text(50, 50, "Win and Bye");
    /** Minute and second integers for timer to be converted to a string*/
    int mins = 0, secs = 0;
    /**
     * Initialise the game with the provided config
     *
     * @param config The config parser to load the config from
     * @param timer The game timer
     * @param score The game score
     */

    public Game(ConfigReader config, Text timer, Text score) {
        this.setup(config, timer, score);
    }
    /**
     * Set the timer by setting minutes and seconds
     * @param mins2 the timer minutes
     * @param secs2 the timer seconds
     */
    public void setTimer(int mins2, int secs2){

        this.mins = mins2;
        this.secs = secs2;

    }
    private void setup(ConfigReader config,Text timer, Text score) {

        this.timer = timer;
        timer.setX(625);
        timer.setY(150);

        this.table = new PoolTable(config.getConfig().getTableConfig());
        List<BallConfig> ballsConf = config.getConfig().getBallsConfig().getBallConfigs();
        List<Ball> balls = new ArrayList<>();

        PocketsConfig pocketsConf = config.getConfig().getTableConfig().getPocketsConfig();
        List<Pocket> pockets = new ArrayList<>();
        BallBuilderDirector builder = new BallBuilderDirector();

        builder.registerDefault();
        for (BallConfig ballConf: ballsConf) {
            Ball ball = builder.construct(ballConf);
            if (ball == null) {
                System.err.println("WARNING: Unknown ball, skipping...");
            } else {
                balls.add(ball);
            }
        }
        for (PocketConfig p: pocketsConf.getPocketConfigs()) {
            Pocket pa = new Pocket(p.getPositionConfig().getX(), p.getPositionConfig().getY(),p.getRadius());
            if (pa == null) {
                System.err.println("WARNING: Unknown pocket, skipping...");
            } else {
                pockets.add(pa);
            }
        }
        //Sets up the undo button to recover states
        undo.setLayoutX(600);
        undo.setLayoutY(275);
        EventHandler<ActionEvent> undoEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                for (Ball ball : table.getBalls()) {
                    CareTaker c = ball.getCaretaker();
                    if(c != null){
                        ball.added = false;
                        ball.recoverState(c.getMemento(), Game.this);
                    }
                }
            }
        };
        undo.setOnAction(undoEvent);

        this.table.setupBalls(balls);
        this.table.setupPockets(pockets);
        this.winText.setVisible(false);
        this.winText.setX(table.getDimX() / 2);
        this.winText.setY(table.getDimY() / 2);


    }


    /**
     * Add the point to the score and notify subject using the alert
     * @param point the balls specific point
     */
    public void addScore(int point){
        this.score += point;
        alert();

    }
    /**
     * Get the window dimension in the x-axis
     * @return The x-axis size of the window dimension
     */
    public double getWindowDimX() {
        return this.table.getDimX();
    }

    /**
     * Get the window dimension in the y-axis
     * @return The y-axis size of the window dimension
     */
    public double getWindowDimY() {
        return this.table.getDimY();
    }

    /**
     * Get the pool table associated with the game
     * @return The pool table instance of the game
     */
    public PoolTable getPoolTable() {
        return this.table;
    }

    /** Add all drawable object to the JavaFX group
     * @param root The JavaFX `Group` instance
    */
    public void addDrawables(Group root) {
        ObservableList<Node> groupChildren = root.getChildren();

        table.addToGroup(groupChildren);
        groupChildren.add(this.winText);
        groupChildren.add(this.timer);
        groupChildren.add(this.undo);
        int i = 0;
        for(Ball b: table.getBalls()){

            if(b.getButton() != null ) {
                groupChildren.remove(b.getButton());
                b.getButton().setLayoutX(725);
                b.getButton().setLayoutY(10+i);
                groupChildren.add(b.getButton());
                i+=30;
            }
        }
    }
    /** Remove all drawable object from the JavaFX group
     * @param root The JavaFX `Group` instance
     */
    public void removeDrawables(Group root) {
        ObservableList<Node> groupChildren = root.getChildren();

        groupChildren.remove(this.timer);

        for(Ball b: table.getBalls()){

            if(b.getButton() != null) {
                groupChildren.remove(b.getButton());
            }
        }
    }

    /** Reset the game */
    public void reset() {
        fell = true;
        this.timer.setText("0:00");
        this.score = 0;
        alert();
        this.winText.setVisible(false);
        this.shownWonText = false;
        this.table.reset();
    }


    /** Code to execute every tick. */
    public void tick() {
        if (table.hasWon() && !this.shownWonText) {
            System.out.println(this.winText.getText());
            this.winText.setVisible(true);
            this.shownWonText = true;
        }

        table.checkPocket(this);

        table.handleCollision();
        this.table.applyFrictionToBalls();

        //Saves all the balls previous states everytime the cue ball is hit
        for (Ball ball : this.table.getBalls()) {
            ball.move();
        }
        save();
        //Keep checking for the cheat button being pressed
        cheat();

    }
    /** Attach for the Observer DP to the observers list
     * @param observer The Observer
     */
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    /** Detach for the Observer DP from the observers list
     * @param observer The Observer
     */
    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    /** Saves the state by looping through the balls and setting mementos*/
    public void save(){
        //Saves all the balls previous states everytime the cue ball is hit
        for (Ball ball : this.table.getBalls()) {
            if(ball.hit || this.cheat) {
                ball.hit = false;
                this.cheat = false;
                for (Ball ball2 : this.table.getBalls()) {
                    //Creates a new caretaker for every ball and sets it
                    CareTaker c = new CareTaker();
                    ball2.setCaretaker(c);
                    c.setMemento(ball2.saveState(mins, secs, score));

                }
            }

        }
    }

    /**Alert for the Observer DP through updating the observers*/
    @Override
    public void alert() {
        for (Observer o : observers) {
            o.update();
        }

    }
    /**
     * Get the score of the game
     * @return The game score
     */
    @Override
    public int getScore() {
        return this.score;
    }
    /**
     * Sets the score of the game using alert to notify of change
     * @param scores  The new game score
     */
    public void setScore(int scores) {
        this.score = scores;
        alert();
    }
    /**
     * Adding a button and event for every ball when cheating */
    public void cheat(){
        for(Ball b: table.getBalls()){
            //Creating a cheat event for every button for every ball.
            EventHandler<ActionEvent> cheatEvent = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {



                    for(Ball b2: table.getBalls()){


                        if(Objects.equals(b2.getColour(), b.getColour())){

                            //Incrementing is done to ensure that if an undo is pressed it only enables the
                            // balls again if they existed in the last memento
                            if(b2.getPocketAction().getThresh() == 1){
                                b2.incrementFallCounter();
                            }
                            else{
                                //Increment the amount of times, so it doesn't reappear with every undo
                                for( int i = 0; i < b2.getPocketAction().getThresh()-1; i++){
                                    b2.incrementFallCounter();
                                }
                            }


                            if(!b2.isDisabled()){
                                b2.disable();
                                cheat = true;
                                save();
                            }


                        }

                    }
                    for(Ball b2: table.getBalls()) {
                        if (Objects.equals(b2.getColour(), b.getColour())) {
                            // Only add the score and alert observer if they have disabled in the previous loop
                            if (b2.isDisabled() && !(b2.added)) {
                                b2.added = true;
                                addScore(b2.getPoint());
                                alert();
                            }

                        }

                    }
                }

            };
            //Set the button to that cheat event
            if(b.getButton()!= null){
                b.getButton().setOnAction(cheatEvent);
            }

        }
    }
}
