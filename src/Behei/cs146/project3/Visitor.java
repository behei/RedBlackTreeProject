package Behei.cs146.project3;

/**
 * Created by mishabehey on 4/20/17.
 */
public interface Visitor <Key extends Comparable<Key>, Value> {
    /**
     * this method is called at each node
     * @param node the visited node
     */

    void visit(Node node);
}
