
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
    
    void addQueue(Access a){
        requests.add(a);
    }
    
    BlockingQueue<Access> getQueue(){
        return this.requests;
    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        DCTower tower = new DCTower();
        
        Access a1 = new Access(1, 10, Direction.UP);
        Access a2 = new Access(10, 1, Direction.DOWN);
        
        tower.addQueue(a1);
        tower.addQueue(a2);
        
        Elevator e1 = new Elevator(tower.getQueue());
        Elevator e2 = new Elevator(tower.getQueue());
      
        new Thread(e1).start();
        new Thread(e2).start();
        
    }
    
}
