package Behei.cs146.project3;

import static Behei.cs146.project3.Node.RED;
import static Behei.cs146.project3.Node.BLACK;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by mishabehey on 4/20/17.
 */
public class RedBlackTree {


    //private boolean currentColor;
    private Node current;
    private Node parent;
    private Node grand;
    private Node great;
    private Node header;
    private static Node dummyNode;

    static {
        dummyNode = new Node("0");
        dummyNode.left = dummyNode;
        dummyNode.right = dummyNode;
    }


    private RedBlackTree(String defaultString) {
        header = new Node(defaultString);
        //currentNode.color = BLACK;
        header.left = dummyNode;
        header.right = dummyNode;
        //root.color = BLACK;
    }



    private boolean search(String data) {
        return search(header, data);
    }

    private boolean search(Node node, String data) {
        boolean found = false;
        while ((node != dummyNode) && !found)
        {
            if (node.data.compareTo(data) > 0)
                node = node.left;
            else if (node.data.compareTo(data) < 0)
                node = node.right;
            else
            {
                System.out.println("found");
                found = true;
                break;
            }
            found = search(node, data);
        }


        return found;
        /*
        if (node.data == data || node == dummyNode) {
            System.out.println("found");
            return true;
        } else if (data < node.data)
            return search(node.left, data);
        else if (data > node.data)
            return search(node.right, data);
        else {
            System.out.println("not found");
            return false;
        }
        */
    }
    /*
    public void insert(int data) {
        root = insert(root, data);
    }

    //has to be updated to follow RBT pattern
    private Node insert(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }
        if (data < node.data) {
            if (node.left != null) {
                insert(node.left, data);
                node.color = RED;
            } else {
                node.color = RED;
                node.left = new Node(data);

            }
        }

        if (data > node.data) {
            if (node.right != null) {
                insert(node.right, data);
                node.color = RED;
            } else {
                node.color = RED;
                node.right = new Node(data);
            }
        }

        return node;
    }
    */
    private void printTree() {
        printTree(header.right);
    }

    private void printTree(Node node) {
        if (node != dummyNode) {
            char color = 'B';
            if (node.color == RED)
                color = 'R';
            System.out.println(node.data + " " + color);
            printTree(node.left);
            printTree(node.right);
        }
    }
    /*
    public void treeMin() {
        treeMinimum(header);
    }

    public <Key> int treeMinimum(Node node) {
        while (node.left != null)
            node = node.left;
        System.out.println(node.data);
        return node.data;
    }

    public void treeMax() {
        treeMax(header);
    }

    private void treeMax(Node node) {
        while (node.right != null)
            node = node.right;

        System.out.println(node.data);
    }
*
    public void delete(int data) {
        current = delete(current, data);
    }

    private <Key >Node delete(Node node, Key data) {
        if (node == null) System.out.println("not in the tree");

        else if ()
            node.left = delete(node.left, data);
        else if (data > node.data)
            node.right = delete(node.right, data);
        else {
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;
            else {
                node.data = returnData(node.left);
                node.left = delete(node.left, node.data);
            }
        }

        return node;
    }
**/

    public void insertRedBlack(String item )
    {
        current = parent = grand = header;
        dummyNode.data = item;
        while (!Objects.equals(current.data, item))
        {
            great = grand;
            grand = parent;
            parent = current;
            current = (item.compareTo(current.data) < 0)? current.left : current.right;
            // Check if two red children and fix if so
            if (current.left.color == RED && current.right.color == RED)
                flipColorOrRotate( item );
        }
        // Insertion fails if already present
        if (current != dummyNode)
            return;
        current = new Node(item, dummyNode, dummyNode);
        // Attach to parent
        if (item.compareTo(parent.data) < 0)
            parent.left = current;
        else
            parent.right = current;
        flipColorOrRotate( item );
    }

    private void flipColorOrRotate(String item)
    {
        // Do the color flip
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;

        if (parent.color == RED)
        {
            // Have to rotate
            grand.color = RED;
            if (item.compareTo(grand.data) < 0 !=  item.compareTo(parent.data ) < 0)
                parent = rotation( item, grand );  // Start dbl rotate
            current = rotation(item, great );
            current.color = BLACK;
        }
        // Make root black
        header.right.color = BLACK;
    }


    private Node rotation(String item, Node parent)
    {
        if(item.compareTo(parent.data) < 0)
            return parent.left = item.compareTo(parent.left.data) < 0 ? rotateWithLeftChild(parent.left) : rotateWithRightChild(parent.left) ;
        else
            return parent.right = item.compareTo(parent.right.data) < 0 ? rotateWithLeftChild(parent.right) : rotateWithRightChild(parent.right);
    }
    /* Rotate binary tree node with left child */
    private Node rotateWithLeftChild(Node k2)
    {
        Node k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }
    /* Rotate binary tree node with right child */
    private Node rotateWithRightChild(Node k1)
    {
        Node k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }

    /*
    private int returnData(Node node) {
        while (node.right != null)
            node = node.right;

        return node.data;
    }
    */

    /*
    public boolean checkIfSameParent() {
        return checkIfSameParent(root, root.left, root.right);
    }

    private boolean checkIfSameParent(Node node, Node nodeLeft, Node nodeRight) {
        return (node != null) &&
                (((node.left == nodeLeft) && (node.right == nodeRight))
                        || ((node.left == nodeRight) && (node.right == nodeLeft))
                        || checkIfSameParent(node.left, nodeLeft, nodeRight)
                        || checkIfSameParent(node.right, nodeLeft, nodeLeft));

    }
    */
    public static void main(String[] args) {
        //Integer[] integer = {5, 7, 9, 8, 12, 11};
        String[] strings = {"string", "secondString", "thirdString"};
        RedBlackTree tree = new RedBlackTree("0");
        tree.printTree();
        System.out.println();
        tree.insertRedBlack("a");
        tree.insertRedBlack("aa");
        tree.insertRedBlack("bbbb");
        tree.insertRedBlack("aaaa");
        tree.printTree();
        tree.search("aa");

        //File file = new File("dictionary.txt");
        /*RedBlackTree treeFile = new RedBlackTree("0");
        try
        {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext())
            {
                String string = scanner.nextLine();
                treeFile.insertRedBlack(string);
                //System.out.println(string);
            }

            scanner.close();
        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        treeFile.printTree();
        treeFile.search("aaaa");
        */
        //for (String string : strings) tree.insertRedBlack(string);
        //tree2.printTree();
        //tree.treeMax();
        //tree.treeMin();
        //tree.search(2);
        //tree.search(11);
        //tree.search(8);

       // tree.delete(2);
        //tree.delete(30);
        //tree.printTree();



    }
    /*
    public int treeSuccessor (Node node) {
        if (node.right != null)
            return
    }
    */



    /*
    public RedBlackTree() {
        //this();
    }


    public boolean checkIfEmpty() {
        if (root == null)
            return true;
        else
            return false;
    }

    public int getSize(Node node) {
        if (node == null)
            return 0;

        else
            return node.getSize();

    }

    public void insert(int value) {
        root = insert(root, value);
    }

    public Node<Key, Value> insert(Node<Key, Value> node, <Key, Value> value) {
        if (node == null)
        {
            node = new Node(node, value, "black", 0);

        }
    }

    public Node search(int value) {
        return search(root, value);
    }

    private Node search(Node node, int value) {
        if (node == null || value == (int) node.value) {
            return node;
        }
        if (value < (int) node.value)
            return search(node.left, value);
        else
            return search(node.right, value);
    }
    */
}
