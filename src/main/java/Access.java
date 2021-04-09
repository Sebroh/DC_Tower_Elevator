/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *

/**
 *
 * @author Sebastian
 */
public class Access {
    private final Direction dir;
    private final int currFl;
    private final int destFl;
    
    Access (int currFl, int destFl, Direction dir) {
        this.dir = dir;
        this.currFl = currFl;
        this.destFl = destFl;
    }
    
    int getCurr(){
        return this.currFl;
    }
    
    int getDest(){
        return this.destFl;
    }
    
    Direction getDir(){
        return this.getDir();
    }
}
