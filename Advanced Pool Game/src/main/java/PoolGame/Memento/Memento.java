package PoolGame.Memento;

import javafx.scene.text.Text;
/** The Memento Class which stores the internal state of the originator*/

public class Memento {
    private double xPos;
    private double yPos;
    private int mins;
    private int secs;
    private int score;
    private int fallCounter;
    /** Initialise the Memento through setting the parameters into the local variables
     * @param xPos the x position of the ball
     * @param yPos the y position of the ball
     * @param mins the minutes of the timer
     * @param secs the seconds of the timer
     * @param fallCounter the number of times the ball has already fell
     * @param score the score the game
     * */
    public Memento(double xPos, double yPos, int mins, int secs, int fallCounter, int score){
        this.xPos = xPos;
        this.yPos = yPos;
        this.mins = mins;
        this.secs = secs;
        this.fallCounter = fallCounter;
        this.score = score;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public int getMins() {
        return mins;
    }

    public int getSecs() {
        return secs;
    }

    public int getFallCounter() {
        return fallCounter;
    }

    public int getScore() {
        return score;
    }
}
