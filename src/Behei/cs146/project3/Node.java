package Behei.cs146.project3;

/**
 * Created by mishabehey on 4/20/17.
 * Node needs a key of a node
 * it needs a value associate with a key
 */
public class Node {

    protected static final boolean RED = false;
    protected static final boolean BLACK = true;

    protected Node left, right;
    protected int data;
    protected boolean color;

    public Node(int data) {
        this (data, null,null);
    }
    public Node (int data, Node left, Node right)
    {
        this.left = left;
        this.right = right;
        this.data = data;
        this.color = BLACK;
    }
    /*
    public boolean search (Node node, int data) {
        if (node == null || data == node.data)
        {
            return true;
        }
        else if (data < node.data)
        {
            return search(node.left, data);
        }
        else
            return search(node.right, data);
    }

    public void insert(Node node, int data) {
        if (data < node.data) {
            if (node.left != null)
                insert(node.left, data);
            else
                node.left = new Node(data);
        }

        if (data > node.data) {
            if (node.right != null)
                insert(node.right, data);
            else
                node.right = new Node(data);
        }
    }

    public int returnMinValue(Node node) {
        while (node.left != null)
            node = node.left;

        return node.data;
    }

    public int returnMaxValue(Node node) {
        while (node.right != null)
            node = node.right;

        return node.data;
    }


    private Key key; // key
    private Node<Key> right, left; //sub-trees
    private String color; // red | black
    private int size;


    public Node(Key k, String color, int size) {
        this.key = k;
        this.color = color;
    }



    public Key getKey() {
        return this.key;
    }


    public String getColor() {
        return this.color.toString();
    }

    */
}
