
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sebastian
 */
public class DCTower {
    BlockingQueue<Access> requests = new ArrayBlockingQueue(1024);
    
    void addQueue(Access a) {
        requests.add(a);
    }
    
    BlockingQueue<Access> getQueue() {
        return this.requests;
    }
    
    DCTower() {
        //create 7 Threads for each Elevator
        for (int i = 1; i < 8; i++) {
            new Thread(new Elevator(this.requests)).start();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws InterruptedException {
        DCTower tower = new DCTower();
        
        tower.addQueue(new Access(1, 10, Direction.UP));
        tower.addQueue(new Access(3, 13, Direction.UP));
        tower.addQueue(new Access(18, 8, Direction.DOWN));
        tower.addQueue(new Access(35, 0, Direction.DOWN));
        Thread.sleep(4000);
        tower.addQueue(new Access(54, 4, Direction.DOWN));
        tower.addQueue(new Access(0, 48, Direction.UP));
        Thread.sleep(12000);
        tower.addQueue(new Access(5, 19, Direction.UP));
        tower.addQueue(new Access(37, 9, Direction.DOWN));
        
        
    }
    
}
