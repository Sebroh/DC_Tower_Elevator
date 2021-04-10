
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sebastian
 */
public class Elevator implements Runnable{
    
    BlockingQueue<Access> requests = null;
    Access curr;
    
    @Override
    public void run() {
        while(true){
            try {
                curr = requests.take();
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Elevator.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                handleDest(curr.getDest(), curr.getCurr(), curr.getDir());
            } catch (InterruptedException ex) {
                Logger.getLogger(Elevator.class.getName()).log(Level.SEVERE, null, ex);
            }
  
        }
        
    }
    
    Elevator(BlockingQueue<Access> request){
        this.requests = request;
    }
    
    void handleDest(int destFl, int currFl, Direction dir) throws InterruptedException{
        int tmpFl = currFl;
        while(tmpFl != destFl){
            Thread.sleep(300);
            if(dir == dir.UP) {
                tmpFl += 1;
            } else {
                tmpFl -= 1;
            }
            
            System.out.println("Elevator: " + Thread.currentThread().getId() + " at Floor: " + tmpFl);
        }
    }
    
}
