/**
 *
 * @author Sebastian Rohrer
 */
public class Access {
    final private Direction dir; //Direction
    final private int currFl;    //current Floor
    final private int destFl;    //destination Floor
    
    Access (int currFl, int destFl, Direction dir) {
        this.dir = dir;
        this.currFl = currFl;
        this.destFl = destFl;
        
    }
    
    int getCurr() {
        return this.currFl;
    }
    
    int getDest() {
        return this.destFl;
    }
    
    Direction getDir() {
        return this.dir;
    }

    @Override
    public String toString() {
        return "Access { " + "dir=" + dir + ", currFl=" + currFl + ", destFl=" + destFl +  " } ";
    }
    
    
}
