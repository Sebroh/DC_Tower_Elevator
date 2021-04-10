
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
        ArrayList<Integer> floors = new ArrayList<Integer>();
        floors.add(destFl);
        System.out.println("Elevator: " + Thread.currentThread().getName() + " Person got on at: " + currFl + " People in Elevator: " + floors.size());
        
        while(!floors.isEmpty()){
            System.out.println("Elevator: " + Thread.currentThread().getName() + " at Floor: " + tmpFl);
            Thread.sleep(300);
            if(dir == dir.UP) {
                
                if(!requests.isEmpty()){
                    for (Access i : requests){
                        if(i.getDir() == dir && i.getCurr() <= tmpFl){
                            floors.add(i.getDest());
                            System.out.println("Elevator: " + Thread.currentThread().getName() + " Person got on at: " + i.getCurr()+ " People in Elevator: " + floors.size());
                            requests.remove(i);
                        }
                        
                    }
                                        
                }
                if(!floors.isEmpty()){
                    if(floors.contains(tmpFl)){
                        int index = floors.indexOf(tmpFl);
                        System.out.println("Elevator: " + Thread.currentThread().getName() + " Person left at: " + tmpFl + " People in Elevator: " + (floors.size() - 1));
                        floors.remove(index);
                    }
                    
                } 
                
                
                tmpFl += (!floors.isEmpty()) ? 1 : 0;
            } else {
                
                if(!requests.isEmpty()){
                    for (Access i : requests){
                        if(i.getDir() == dir && i.getCurr() >= tmpFl){
                            floors.add(i.getDest());
                            System.out.println("Elevator: " + Thread.currentThread().getName() + " Person got on at: " + i.getCurr()+ " People in Elevator: " + floors.size());
                            requests.remove(i);
                        }
                        
                    } 
                    
                }
                if(!floors.isEmpty()){
                   if(floors.contains(tmpFl)){
                        int index = floors.indexOf(tmpFl);
                        System.out.println("Elevator: " + Thread.currentThread().getName() + " Person left at: " + tmpFl + " People in Elevator: " + (floors.size() - 1));
                        floors.remove(index);
                    } 
                }
                
                
                tmpFl -= (!floors.isEmpty()) ? 1 : 0;
            }
            
            
        }
        
        
    }
    
}
