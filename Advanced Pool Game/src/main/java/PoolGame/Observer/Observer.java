package PoolGame.Observer;
/** Observer updating interface for objects to be notified of changes
 */
public interface Observer {
    /** Updates to keep the state consistent*/
    public void update();
    public int getScore();
}