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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Access getBüro = new Access(0, 35, Direction.UP);
        System.out.println("Want to Office from " + getBüro.getCurr() + " Floor");
    }
    
}
