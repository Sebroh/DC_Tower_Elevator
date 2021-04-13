/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Sebastian
 */
public class DCTowerTest {
    
    
    
    public DCTowerTest() {
    }
    
    @BeforeAll
    public void setUpClass() {
        
    }
    
    @AfterAll
    public void tearDownClass(){
        
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    /**
     *
     * @throws InterruptedException
     */
    
    @Test
    public void test10People() throws InterruptedException {
        DCTower tower = new DCTower();
        
        tower.addRequest(new Access(4, 34, Direction.UP));
        tower.addRequest(new Access(3, 31, Direction.UP));
        tower.addRequest(new Access(34, 8, Direction.DOWN));
        tower.addRequest(new Access(31, 13, Direction.DOWN));
        tower.addRequest(new Access(41, 55, Direction.UP));
        tower.addRequest(new Access(42, 43, Direction.UP));
        tower.addRequest(new Access(0, 55, Direction.UP));
        tower.addRequest(new Access(3, 50, Direction.UP));
        tower.addRequest(new Access(45, 23, Direction.DOWN));
        tower.addRequest(new Access(26, 48, Direction.UP));
        
        tower.stopElevators();

        assertEquals(tower.getEnterCounter(), tower.getLeaveCounter());
    }
    
    public void test20People() throws InterruptedException {
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

        assertEquals(tower.getEnterCounter(), tower.getLeaveCounter());
    }
    
    public void test7People() throws InterruptedException {
        DCTower tower = new DCTower();
        
        tower.addRequest(new Access(5, 16, Direction.UP));
        tower.addRequest(new Access(9, 23, Direction.UP));
        tower.addRequest(new Access(55, 43, Direction.DOWN));
        tower.addRequest(new Access(23, 0, Direction.DOWN));
        tower.addRequest(new Access(32, 31, Direction.DOWN));
        tower.addRequest(new Access(5, 19, Direction.UP));
        tower.addRequest(new Access(37, 9, Direction.DOWN));
        
        tower.stopElevators();

        assertEquals(tower.getEnterCounter(), tower.getLeaveCounter());
    }
    
}
