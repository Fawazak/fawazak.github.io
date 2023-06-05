package PoolGame.Observer;
/** The Subject for the observer design pattern responsible */
public interface Subject {
    /** Attaching the observer objects
     * @param observer the observer we want to attach
     */
    void attach(Observer observer);
    /** Detaching the observer objects
     * @param observer the observer we want to detach
     */
    void detach(Observer observer);
    /** Notify the subject of a specific change*/
    void alert();


    int getScore();

}
