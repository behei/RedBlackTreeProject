package Behei.cs146.project3;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class RedBlackTreeTest {
    @Test
    public void testValues() {
        RedBlackTree rbt = new RedBlackTree();
        rbt.insert("D");
        rbt.insert("B");
        rbt.insert("A");
        rbt.insert("C");
        rbt.insert("F");
        rbt.insert("E");
        rbt.insert("H");
        rbt.insert("G");
        rbt.insert("I");
        rbt.insert("J");

        //System.out.println(rbt.specialPreOrderVisitForJUnit(rbt.root));
        assertEquals("DBACFEHGIJ", rbt.specialPreOrderVisitForJUnit(rbt.root));

    }

    @Test
    public void testColor() {
        RedBlackTree rbt = new RedBlackTree();
        rbt.insert("D");
        rbt.insert("B");
        rbt.insert("A");
        rbt.insert("C");
        rbt.insert("F");
        rbt.insert("E");
        rbt.insert("H");
        rbt.insert("G");
        rbt.insert("I");
        rbt.insert("J");
        //System.out.println(rbt.specialPreOrderVisitColorForJUnit(rbt.root));
        assertEquals("1111110110", rbt.specialPreOrderVisitColorForJUnit(rbt.root));
    }


}