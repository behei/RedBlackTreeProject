package Behei.cs146.project3;

import static Behei.cs146.project3.Node.RED;
import static Behei.cs146.project3.Node.BLACK;

/**
 * Created by mishabehey on 4/20/17.
 */
public class RedBlackTree {


    //private boolean currentColor;
    private Node current, parent, grand, great, header;
    private static Node dummyNode;

    static {
        dummyNode = new Node(0);
        dummyNode.left = dummyNode;
        dummyNode.right = dummyNode;
    }


    public RedBlackTree(int NEGATIVE_INFINITY) {
        header = new Node(NEGATIVE_INFINITY);
        //currentNode.color = BLACK;
        header.left = dummyNode;
        header.right = dummyNode;
        //root.color = BLACK;
    }

    public boolean search(int data) {
        return search(header.right, data);
    }

    public boolean search(Node node, int data) {
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
    public void printTree() {
        printTree(header.right);
    }

    public void printTree(Node node) {
        if (node != dummyNode) {
            char color = 'B';
            if (node.color == RED)
                color = 'R';
            System.out.println(node.data + " " + color);
            printTree(node.left);
            printTree(node.right);
        }
    }

    public void treeMin() {
        treeMinimum(header);
    }

    public int treeMinimum(Node node) {
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

    public void delete(int data) {
        current = delete(current, data);
    }

    private Node delete(Node node, int data) {
        if (node == null) System.out.println("not in the tree");

        else if (data < node.data)
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

    public void insertRedBlack(int data)
    {
        current = parent = grand = header;
        dummyNode.data = data;

        while (current.data != data)
        {
            great = grand;
            grand = parent;
            parent = current;
            current = data < current.data ? current.left : current.right;

            if (current.left.color == RED && current.right.color == RED)
                flipColorOrRotate(data);
        }

        if (current != dummyNode)
            return;
        current = new Node(data, dummyNode, dummyNode);

        if (data < parent.data)
            parent.left = current;
        else
            parent.right = current;
        flipColorOrRotate(data);

    }

    private void flipColorOrRotate(int data)
    {
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;

        if (parent.color == RED)
        {
            //forced to apply rotation
            grand.color = RED;
            if (data < grand.data != data < parent.data)
                parent = rotation(data, grand);
            current = rotation(data, great);
            current.color = BLACK;
        }

        header.right.color = BLACK;
    }

    private Node rotation(int data, Node node)
    {
        if (data < node.data)
            return node.left = data < node.left.data ? rotateLeft(node.left) : rotateRight(node.right);
        else
            return node.right = data < node.right.data ? rotateLeft(node.right) : rotateRight(node.right);
    }

    private Node rotateLeft(Node node)
    {
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        return temp;
    }

    private Node rotateRight(Node node)
    {
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        return temp;
    }
    private int returnData(Node node) {
        while (node.right != null)
            node = node.right;

        return node.data;
    }
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
        Integer[] integer = {1, 5, 2, 7, 4, 8, 100, 1};
        RedBlackTree tree = new RedBlackTree(Integer.MIN_VALUE);
        //tree.insertRedBlack(integer[0]);
        for (Integer n : integer) tree.insertRedBlack(n);
        tree.printTree();
        //tree.treeMax();
        //tree.treeMin();
        tree.search(2);
        tree.search(11);
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
