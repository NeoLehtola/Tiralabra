
package a_star;

/**
 *
 * @author neom
 */
public class Node {
    
    private int[] currentState;
    private int f;
    private int g;
    private int h;
    
    public Node(int[] currentState) {
        this.currentState = currentState;
    }
    
    
}
