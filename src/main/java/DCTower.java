import java.lang.Thread.State;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Sebastian Rohrer
 */
public final class DCTower {
    private final BlockingQueue<Access> requests;
    //every Thread increments this counter if a person left the Elevator
    private final AtomicInteger leaveCounter; 
    //gets incremented if a person gets in the Elevator
    private int enterCouter;
    
    
    //stores all requests from the user
    //is a thread safe queue, to provide access to multiple threads at once
    void addRequest(Access a) {
        requests.add(a);
        this.enterCouter++;
    }
    
    BlockingQueue<Access> getQueue() {
        return this.requests;
    }
    
    DCTower() {
        this.leaveCounter = new AtomicInteger();
        this.requests = new LinkedBlockingQueue<>();
        //creates 7 Threads for each Elevator
        for (int i = 1; i < 8; i++) {
            new Thread(new Elevator(this.requests, this.leaveCounter), String.valueOf(i)).start();
        }
    }
    
    void stopElevators() throws InterruptedException{
        while(!requests.isEmpty()) 
        for(int i = 1;i < 8; i++){
           //get all Elevator Threads
           for(Thread t : Thread.getAllStackTraces().keySet()) {
                if(t.getName().equals(String.valueOf(i))) {
                    //wait till Elevators are empty and Threads are waiting 
                    //for new Requests
                    while(t.getState() != State.WAITING){
                        Thread.sleep(500);
                    }
                    t.interrupt();
                }
            } 
        }
        
    }
    
    int getLeaveCounter(){
        return this.leaveCounter.get();
    }
    
    int getEnterCounter(){
        return this.enterCouter;
    }
    
    public static void main(String[] args) throws InterruptedException{
        DCTower tower = new DCTower();
        
        tower.addRequest(new Access(1, 10, Direction.UP));
        tower.addRequest(new Access(3, 13, Direction.UP));
        tower.addRequest(new Access(18, 8, Direction.DOWN));
        tower.addRequest(new Access(35, 0, Direction.DOWN));
        tower.addRequest(new Access(41, 45, Direction.UP));
        tower.addRequest(new Access(42, 51, Direction.UP));
        tower.addRequest(new Access(0, 55, Direction.UP));
        tower.addRequest(new Access(43, 50, Direction.UP));
        tower.addRequest(new Access(55, 23, Direction.DOWN));
        tower.addRequest(new Access(21, 45, Direction.UP));
        tower.addRequest(new Access(12, 0, Direction.DOWN));
        tower.addRequest(new Access(12, 32, Direction.UP));
        tower.addRequest(new Access(55, 0, Direction.DOWN));
        tower.addRequest(new Access(23, 32, Direction.UP));
        tower.addRequest(new Access(54, 4, Direction.DOWN));
        tower.addRequest(new Access(0, 48, Direction.UP));
        tower.addRequest(new Access(5, 38, Direction.UP));
        tower.addRequest(new Access(32, 31, Direction.DOWN));
        tower.addRequest(new Access(5, 19, Direction.UP));
        tower.addRequest(new Access(37, 9, Direction.DOWN));
        
        tower.stopElevators();
    }
    
}
