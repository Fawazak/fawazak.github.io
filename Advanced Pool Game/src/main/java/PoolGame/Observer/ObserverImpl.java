package PoolGame.Observer;

public class ObserverImpl implements Observer{
    private final Subject subject;
    private int score;

    public ObserverImpl(Subject subject){
        this.subject = subject;
        this.score = subject.getScore();

    }
    @Override
    public void update() {

        this.score = subject.getScore();


    }
    @Override
    public int getScore() {
        return this.score;
    }
}
