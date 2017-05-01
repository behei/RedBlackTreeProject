package Behei.cs146.project3;

/**
 * Java Program that implements Red Black Tree
 *
 * @author Mykhailo Behei
 * @version 0.0.1
 * @since 04/30/2017
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RedBlackTree {

    protected Node root; //a root Node
    private static final int BLACK = 1; //black equal to 1
    private static final int RED = 0; //red equal to zero
    private String result = "";
    private String resultColor = "";

    class Node { //create a Node class inside of RedBlackTree class to save up space
        protected String data; //data that will be save
        private Node left, right; //left and right nodes
        private Node parent; //a parent node
        int color; //a node's color

        Node(String data) {  //create a new node
            this.data = data;
            this.left = null;
            this.right = null;
            this.color = RED;
        }
    }

    /**
     * method that returns a sibling of a node
     *
     * @param node as a node
     * @return sibling
     */
    private Node getSibling(Node node) {
        if (node.parent == null || node.parent.left == null || node.parent.right == null) return null;
        else {
            if (node == node.parent.left)
                return node.parent.right;
            else
                return node.parent.left;
        }
    }

    /**
     * returns aunt of a node, which is a sibling of a parent
     *
     * @param node that has an aunt
     * @return an aunt
     */
    private Node getAunt(Node node) {
        return getSibling(node.parent);
    }

    /**
     * returns parent a node
     *
     * @param node that has a parent
     * @return a parent
     */
    private Node getParent(Node node) {
        if (node.parent != null) {
            return node.parent;
        } else return null;
    }

    /**
     * returns a grandparent of a node
     *
     * @param node that has a grandparent
     * @return a grandparent
     */
    private Node getGrandParent(Node node) {
        if (node.parent != null) {
            return node.parent.parent;
        } else return null;
    }

    /**
     * starts a search of a node
     *
     * @param data that needs to be found
     * @return either found or not
     */
    private boolean search(String data) {
        if (search(root, data)) return true;
        else return false;
    }

    /**
     * method that handles the logic of search
     *
     * @param node as node that is searched for
     * @param data is used to compare values
     * @return either found or not
     */
    private boolean search(Node node, String data) {
        boolean found;
        found = false;
        while (node != null && !found) {
            if (node.data.compareTo(data) > 0)
                node = node.left;
            else if (node.data.compareTo(data) < 0)
                node = node.right;
            else {
                found = true;
                break;
            }
            found = search(node, data);
        }


        return found;
    }

    /**
     * prints tree recursively
     * preOrder printing
     */
    public void preOrderVisit() {
         preOrderVisit(root);
    }

    /**
     * prints tree recursively 
     * @param node as parameter to walk through a tree
     */
    private void preOrderVisit(Node node) {
        if (node == null) return;
        //result += node.data;
        //System.out.println(result);
        System.out.println(node.data + " " + node.color);
        preOrderVisit(node.left);
        preOrderVisit(node.right);
    }

    /**
     * I couldn't figure out how to use Visitor
     * so I added this method to store values of RBT
     * in a string
     * @param node as root
     * @return all the values in a RBT in preOrder
     */
    protected String specialPreOrderVisitForJUnit (Node node) {
        if (node == null) return "";
        result += node.data;
        specialPreOrderVisitForJUnit(node.left);
        specialPreOrderVisitForJUnit(node.right);
        return result;
    }

    protected String specialPreOrderVisitColorForJUnit (Node node)
    {
        if (node == null) return "";
        resultColor += node.color;
        specialPreOrderVisitColorForJUnit(node.left);
        specialPreOrderVisitColorForJUnit(node.right);
        return resultColor;
    }


    /**
     * insert node and data into a tree
     *
     * @param data needs to be inserted
     */
    public void insert(String data) {
        root = insert(root, data);
    }

    /**
     * handles the logic of an insert
     *
     * @param node that will be created
     * @param data that will be inserted
     * @return inserted node
     */
    private Node insert(Node node, String data) {
        Node temp = null;
        Node insertNode = new Node(data);
        while (node != null) {
            temp = node;
            if (data.compareTo(node.data) < 0)
                node = node.left;
            else
                node = node.right;
        }
        insertNode.parent = temp;
        if (temp == null) {
            root = insertNode;
            insertNode.color = BLACK;//black root
            return root;
        } else {
            if (insertNode.data.compareTo(temp.data) < 0)
                temp.left = insertNode;
            else
                temp.right = insertNode;
        }
        insertNode.left = null;
        insertNode.right = null;
        insertNode.color = RED;
        fixTree(insertNode);
        return root;

    }

    /**
     * since we have an RBT tree, it needs to follow the definition
     *
     * @param node that needs a fix-up
     * @return fixed tree
     */
    private Node fixTree(Node node) {

        Node parentCurrent;
        parentCurrent = getParent(node);
        Node greatParent;
        greatParent = getGrandParent(node);
        Node aunt;
        aunt = getAunt(node);

        if (parentCurrent == null) {
            return null;
        } else {
            if (parentCurrent.color == BLACK)
                return node;
            else if (parentCurrent.color == RED) {
                if (aunt == null || aunt.color == BLACK)
                    if (node != parentCurrent.right || parentCurrent != greatParent.left) {
                        if (node == parentCurrent.left && parentCurrent == greatParent.right) {
                            Node currentParent = node.parent;
                            node.parent = currentParent.parent;

                            currentParent.parent.right = node;

                            Node currentRight = node.right;
                            currentRight.parent = currentParent;
                            currentParent.left = currentRight;

                            node.right = currentParent;
                            currentParent.parent = node;

                            rotateLeft(node.right);
                        } else {
                            if (parentCurrent.left == node && greatParent.left == parentCurrent) {
                                rotateRight(node);
                            } else if (parentCurrent.right == node && greatParent.right == parentCurrent)
                                rotateLeft(node);
                        }
                    } else {
                        Node currentParent = node.parent;
                        node.parent = currentParent.parent;
                        currentParent.parent.left = node;

                        Node currentLeft = node.left;
                        currentLeft.parent = currentParent;
                        currentParent.right = currentLeft;
                        node.left = currentParent;
                        currentParent.parent = node;


                        rotateRight(node.left);
                    }
                else {
                    if (aunt != null && aunt.color == RED) {
                        parentCurrent.color = BLACK;
                        aunt.color = BLACK;
                        if (greatParent == root) {
                            return root;
                        }
                        greatParent.color = RED;
                        return fixTree(greatParent);
                    }
                }
            }
        }
        return root;
    }

    /**
     * handles the rotate left logic
     *
     * @param node rotated
     */
    private void rotateLeft(Node node) {
        Node parentCurrent;
        parentCurrent = getParent(node);
        Node greatParent;
        greatParent = getGrandParent(node);
        if (parentCurrent != null) {
            parentCurrent.color = BLACK;
        }
        if (greatParent != null) {
            greatParent.color = RED;
        }

        if (greatParent != null) {
            if (parentCurrent != null) {
                greatParent.right = parentCurrent.left;
            }
        }

        if (parentCurrent != null) {
            if (parentCurrent.left == null) {
            } else {
                parentCurrent.left.parent = greatParent;
            }
        }

        if (parentCurrent != null) {
            parentCurrent.left = parentCurrent.parent;
        }

        if (parentCurrent != null) {
            if (greatParent != null) {
                parentCurrent.parent = greatParent.parent;
            }
        }

        if (greatParent != null) {
            if (greatParent.parent != null) {
                if (parentCurrent.parent == greatParent.parent.left)
                    greatParent.parent.left = parentCurrent;
                else
                    greatParent.parent.right = parentCurrent;
            } else root = parentCurrent;
        }

        if (greatParent != null) {
            greatParent.parent = parentCurrent;
        }
    }

    /**
     * handles the return right logic
     *
     * @param node that is rotated around
     */
    private void rotateRight(Node node) {
        Node parentCurrent;
        parentCurrent = getParent(node);
        Node greatParent;
        greatParent = getGrandParent(node);
        if (parentCurrent != null) {
            parentCurrent.color = BLACK; //black
        }
        if (greatParent != null) {
            greatParent.color = RED; //red
        }

        if (greatParent != null) {
            if (parentCurrent != null) {
                greatParent.left = parentCurrent.right;
            }
        }
        if (parentCurrent != null && parentCurrent.right != null) {
            parentCurrent.right.parent = greatParent;
        }

        if (parentCurrent != null) {
            parentCurrent.right = parentCurrent.parent;
        }

        if (parentCurrent != null) {
            if (greatParent != null) {
                parentCurrent.parent = greatParent.parent;
            }
        }

        if (greatParent != null) {
            if (greatParent.parent == null)
                root = parentCurrent;
            else {
                if (parentCurrent != null) {
                    if (parentCurrent.parent == greatParent.parent.left)
                        greatParent.parent.left = parentCurrent;
                    else
                        greatParent.parent.right = parentCurrent;
                }
            }
        }

        if (greatParent != null) {
            greatParent.parent = parentCurrent;
        }

    }

    /**
     * Dictionary is being read into the RBT
     * Time is tested in here
     * Search is tested in here
     *
     * @param args as args
     */
    public static void main(String[] args) {

        File file = new File("dictionary.txt");
        RedBlackTree dictionary = new RedBlackTree();
        try {
            Scanner scanner = new Scanner(file);
            long currentTime = System.currentTimeMillis();
            while (scanner.hasNext()) {
                String string = scanner.nextLine();
                dictionary.insert(string);
            }
            long endingTime = System.currentTimeMillis();
            scanner.close();
            int totalTime = (int) (endingTime - currentTime);
            System.out.println("The total time to insert is: " + totalTime + " ms");

            currentTime = System.currentTimeMillis();
            boolean searchResult = dictionary.search("aaaa");
            endingTime = System.currentTimeMillis();
            long totalTimeMs = (endingTime - currentTime);
            if (searchResult)
            {
                System.out.println("The time to look up a string in Dictionary " + totalTimeMs + " ms");
                System.out.println("That's some fast search right there! :)");
            }
            else
            {
                System.out.println("Your word is not in our dictionary!");
                System.out.println("Time for the unsuccessful search... " + totalTimeMs + " ms");
            }
            //the line below can be uncommented to check the preOrderVisit
            //dictionary.preOrderVisit();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public interface Visitor {
        void visit(Node n);
    }
}
