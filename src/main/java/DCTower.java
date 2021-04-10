import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sebastian Rohrer
 */
public class DCTower {
    
    //stores all requests from the user
    //is a thread safe queue, to provide access to multiple threads at once
    BlockingQueue<Access> requests = new ArrayBlockingQueue(1024);
    
    void addQueue(Access a) {
        requests.add(a);
    }
    
    BlockingQueue<Access> getQueue() {
        return this.requests;
    }
    
    DCTower() {
        //creates 7 Threads for each Elevator
        for (int i = 1; i < 8; i++) {
            new Thread(new Elevator(this.requests), String.valueOf(i)).start();
        }
    }
      
    
    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    
    public static void main(String[] args) throws InterruptedException {
        DCTower tower = new DCTower();
        
        //add Elevator Requests
        tower.addQueue(new Access(1, 10, Direction.UP));
        tower.addQueue(new Access(3, 13, Direction.UP));
        tower.addQueue(new Access(18, 8, Direction.DOWN));
        tower.addQueue(new Access(35, 0, Direction.DOWN));
        tower.addQueue(new Access(41, 45, Direction.UP));
        tower.addQueue(new Access(0, 55, Direction.UP));
        tower.addQueue(new Access(21, 45, Direction.UP));
        tower.addQueue(new Access(12, 0, Direction.DOWN));
        tower.addQueue(new Access(12, 32, Direction.UP));
        Thread.sleep(4000);
        tower.addQueue(new Access(54, 4, Direction.DOWN));
        tower.addQueue(new Access(0, 48, Direction.UP));
        Thread.sleep(12000);
        tower.addQueue(new Access(5, 19, Direction.UP));
        tower.addQueue(new Access(37, 9, Direction.DOWN));
        
        
        Thread.sleep(10000);
        System.exit(0);  
    }
    
}
