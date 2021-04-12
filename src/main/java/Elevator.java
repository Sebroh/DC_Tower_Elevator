
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
 * @author Sebastian Rohrer
 */

public class Elevator extends Thread{
    
    //Link to request Queue to receive active Request
    BlockingQueue<Access> requests = null;
    //current Passangers Request
    Access curr;
    //current Floor of the Elevator
    int currentFloor = 0;
    
    
    //Method that runs in the Thread
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                    //get next request in queue, if empty it waits
                    curr = requests.take();
                    //drive Elevator to Passengers current Location, because
                    //all Elevators start at 0
                     if(curr.getCurr() != this.currentFloor){
                        driveToCurr(curr.getCurr());
                    }
                    //bring Passengers to their destination
                    handleDest(curr.getDest(), curr.getCurr(), curr.getDir());

            }
            
        } catch (InterruptedException ex) {
                System.out.println("Elevator: " + Thread.currentThread().getName() 
                        + " stopped!");
            }
       
    }
    
    Elevator(BlockingQueue<Access> request) {
        this.requests = request;
    }
    
    //Elevator algorithm to handle multiple passengers on different floors 
    //with the same direction
    void handleDest(int destFl, int currFl, Direction dir) throws InterruptedException {
        
        //stores the different floors it has to stop
        ArrayList<Integer> floors = new ArrayList<>();
        
        floors.add(destFl);
        
        System.out.println("Elevator: " + Thread.currentThread().getName() + 
                " Person got on at: " + currFl + " People in Elevator: " + 
                floors.size());
        
        //the elevator goes in one direction unitl no passengers are left in it
        while(!floors.isEmpty()){
            
            System.out.println("Elevator: " + 
                    Thread.currentThread().getName() + " at Floor: " + this.currentFloor);
            Thread.sleep(300);
            
            if(dir == dir.UP) {
                
                //check if another request is in the buffer and if the elevator 
                //could make a stopover
                synchronized (this){
                    if (!requests.isEmpty()) {
                        for (int i = 0;i < requests.size();i++) {

                            //Blocking queue is not able to get specific element, so
                            //every element from the queue is taken, checked and 
                            //if not taken it gets added back
                            Access req = requests.take();
                            if (req.getDir() == dir && req.getCurr() == this.currentFloor) {

                                floors.add(req.getDest());
                                System.out.println("Elevator: " + 
                                        Thread.currentThread().getName() + 
                                        " Person got on at: " + req.getCurr()+ 
                                        " People in Elevator: " + floors.size());
                            } else {
                                requests.add(req);
                            }

                        }

                    }
                }
                
                
                //checks if a destination floor is reached
                if (!floors.isEmpty()) {
                    if (floors.contains(this.currentFloor)) {
                        
                        int index = floors.indexOf(this.currentFloor);
                        System.out.println("Elevator: " + 
                                Thread.currentThread().getName() + 
                                " Person left at: " + this.currentFloor + 
                                " People in Elevator: " + (floors.size() - 1));
                        floors.remove(index);
                    }
                    
                } 
                
                if(this.currentFloor > 55){
                    System.out.println("FALSE Elevator: " + 
                            Thread.currentThread().getName() + 
                            " Elevator Dest: " + floors.get(0));
                    break;
                }
                
                //moves elevator up
                this.currentFloor += (!floors.isEmpty()) ? 1 : 0;
                
            //else direction is down
            } else {
                
                //check if another request is in the buffer and if the elevator 
                //could make a stopover
                synchronized (this){
                    if (!requests.isEmpty()) {
                        for (int i = 0;i < requests.size();i++) {

                            //Blocking queue is not able to get specific element, so
                            //every element from the queue is taken, checked and 
                            //if not taken it gets added back at the tail
                            Access req = requests.take();
                            if (req.getDir() == dir && req.getCurr() == this.currentFloor) {

                                floors.add(req.getDest());
                                System.out.println("Elevator: " +
                                        Thread.currentThread().getName() + 
                                        " Person got on at: " + req.getCurr()+ 
                                        " People in Elevator: " + floors.size());

                            }

                        } 

                    }
                }
                
                
                //checks if a destination floor is reached
                if (!floors.isEmpty()) {
                   if (floors.contains(this.currentFloor)) {
                       
                        int index = floors.indexOf(this.currentFloor);
                        System.out.println("Elevator: " + 
                                Thread.currentThread().getName() + 
                                " Person left at: " + this.currentFloor + 
                                " People in Elevator: " + (floors.size() - 1));
                        floors.remove(index);
                    } 
                }
                
                if(this.currentFloor < 0){
                    System.out.println("FALSE Elevator: " + 
                            Thread.currentThread().getName() + 
                            " Elevator Dest: " + floors.get(0));
                    break;
                }
                
                //moves elevator down 
                this.currentFloor -= (!floors.isEmpty()) ? 1 : 0;
                
            }
               
        }
                
    }
    
    void driveToCurr(int DestCurr) throws InterruptedException{
        //check if Passenger is up or down to the current Elevator position
        Direction dir = (DestCurr > this.currentFloor) ? Direction.UP : Direction.DOWN;
        
        if (dir == Direction.UP) {
            while (this.currentFloor != DestCurr) {
                System.out.println("Elevator: " + Thread.currentThread().getName() + 
                        " is driving from current Floor: " + this.currentFloor 
                        + " to Passenger Floor: " + DestCurr);
                Thread.sleep(300);
                this.currentFloor += 1;
            }
            
        } else {
            while (this.currentFloor != DestCurr) {
                System.out.println("Elevator: " + Thread.currentThread().getName() + 
                        " is driving from current Floor: " + this.currentFloor 
                        + " to Passenger Floor: " + DestCurr);
                Thread.sleep(300);
                this.currentFloor -= 1;
            }
        }
    }
    
    
    
}
