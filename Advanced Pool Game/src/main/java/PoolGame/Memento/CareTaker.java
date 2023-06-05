package PoolGame.Memento;

/**
 * The CareTaker responsible for the mementos safekeeping
 */
public class CareTaker {
    private Memento memento;
    /**
     * Get the memento
     * @return  the memento
     */
    public Memento getMemento() {
        return memento;
    }
    /**
     * Set the memento
     * @param memento the memento
     */
    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
