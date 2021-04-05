package com.wilchen.intersection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class IntersectionTest {

    private Intersection intersection;

    @BeforeEach
    public void setUp() throws Exception {
        intersection = new Intersection(1);
    }

    @Test
    public void testTraffic(){
        Queue<Integer> q = new LinkedList<Integer>();
        Queue[] qList = new Queue[1];
        qList[0] = q;
        intersection.addTraffic(qList, 1);
        assertNotNull(qList);
    }

    @Test
    public void happyPath(){
        intersection.run();
    }
}