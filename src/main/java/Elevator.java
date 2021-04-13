import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


/**
 *
 * @author Sebastian Rohrer
 */

public final class Elevator extends Thread{
    
    //Link to request Queue to receive active Request
    final private BlockingQueue<Access> requests;
    //current Passangers Request
    private Access curr;
    //current Floor of the Elevator
    private int currentFloor;
    //counter gets incremented for every person that leaves for testing purposes
    AtomicInteger leaveCounter;
    
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
                    System.out.println("Elevator: " + Thread.currentThread().getName() 
                            + " got Request: " + curr.toString() + "People in Elevator: 1");
                    handleDest(curr.getDest(), curr.getCurr(), curr.getDir());

            }
            
        } catch (InterruptedException ex) {
                System.out.println("Elevator: " + Thread.currentThread().getName() 
                        + " stopped!");
            }
       
    }
    
    Elevator(BlockingQueue<Access> request, AtomicInteger leaveCounter) {
        this.currentFloor = 0;
        this.requests = request;
        this.leaveCounter = leaveCounter;
    }
    
    //Elevator algorithm to handle multiple passengers on different floors 
    //with the same direction
    void handleDest(int destFl, int currFl, Direction dir) throws InterruptedException {
        
        //stores the different floors it has to stop
        ArrayList<Integer> floors = new ArrayList<>();
        
        floors.add(destFl);
        
        //the elevator goes in one direction unitl no passengers are left in it
        while(!floors.isEmpty()){
            
            System.out.println("Elevator: " + 
                    Thread.currentThread().getName() + " at Floor: " + this.currentFloor);
            Thread.sleep(300);
            
            if(dir == dir.UP) {
                
                //check if another request is in the buffer and if the elevator 
                //could make a stopover
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
                                    " Person got on at: " + req.toString()+ 
                                    " People in Elevator: " + floors.size());
                        } else {
                            requests.add(req);
                        }

                    }

                }
                
                
                //checks if a destination floor is reached
                if (!floors.isEmpty()) {
                    //if more than one people leave at the same floor 
                    for(int i = 0;i < floors.size();i++){
                        if(floors.get(i) == this.currentFloor){
                            int index = floors.indexOf(i);
                            System.out.println("Elevator: " + 
                                Thread.currentThread().getName() + 
                                " Person left at: " + this.currentFloor + 
                                " People in Elevator: " + (floors.size() - 1));
                            floors.remove(i);
                            leaveCounter.getAndIncrement();
                        }
                    }
                    
                } 
                
                //Check if Elevator drives correctly and doesnt move to high
                if(this.currentFloor > 55){
                    System.out.println("Elevator: " + 
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
                                    " Person got on at: " + req.toString()+ 
                                    " People in Elevator: " + floors.size());

                        } else {
                            requests.add(req);
                        }

                    } 

                }
                
                
                //checks if a destination floor is reached
                if (!floors.isEmpty()) {
                    //if more than one people leave at the same floor 
                   for(int i = 0;i < floors.size();i++){
                        if(floors.get(i) == this.currentFloor){
                            int index = floors.indexOf(i);
                            System.out.println("Elevator: " + 
                                Thread.currentThread().getName() + 
                                " Person left at: " + this.currentFloor + 
                                " People in Elevator: " + (floors.size() - 1));
                            floors.remove(i);
                            leaveCounter.getAndIncrement();
                        }
                    }
                }
                
                //Check if Elevator drives correctly and doesnt move to low
                if(this.currentFloor < 0){
                    System.out.println("Elevator: " + 
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
