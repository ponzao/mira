package org.ponzao.it;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.ponzao.AStar;
import org.ponzao.BinaryHeap;
import org.ponzao.Grid;
import org.ponzao.Node;
import org.ponzao.Route;

public class AStarIntegrationTest {
    @Test
    public void test_2x1_Map_From_Left_To_Right() {
        final AStar as = new AStar(new Grid("SG\n"), new BinaryHeap<Node>());
        final Route route = as.findRoute();
        assertEquals(1.0, route.getTotalCost(), 0);
        assertEquals(2, route.getNodes());
        assertEquals("SG\n", route.getRoute());
    }

    @Test
    public void test_3x1_Map_From_Left_To_Right() {
        final AStar as = new AStar(new Grid("S.G\n"), new BinaryHeap<Node>());
        final Route route = as.findRoute();
        assertEquals(2.0, route.getTotalCost(), 0);
        assertEquals(3, route.getNodes());
        assertEquals("S*G\n", route.getRoute());
    }

    @Test
    public void test_2x2_Map_From_Top_Left_To_Bottom_Right() {
        final AStar as = new AStar(new Grid("S.\n.G\n"), new BinaryHeap<Node>());
        final Route route = as.findRoute();
        assertEquals(Math.sqrt(2), route.getTotalCost(), 0);
        assertEquals(2, route.getNodes());
        assertEquals("S.\n.G\n", route.getRoute());
    }

    @Test
    public void test_3x3_Map_From_Top_Left_To_Bottom_Right() {
        final AStar as = new AStar(new Grid("S..\n...\n..G\n"),
                new BinaryHeap<Node>());
        final Route route = as.findRoute();
        assertEquals(Math.sqrt(2) * 2, route.getTotalCost(), 0);
        assertEquals(3, route.getNodes());
        assertEquals("S..\n.*.\n..G\n", route.getRoute());
    }

    @Test
    public void test_3x3_Start_In_Left_Upper_Corner_Goal_In_Bottom_Left_Blocked() {
        final AStar as = new AStar(new Grid("S..\n##.\nG..\n"),
                new BinaryHeap<Node>());
        final Route route = as.findRoute();
        assertEquals(6.0, route.getTotalCost(), 0);
        assertEquals(7, route.getNodes());
        assertEquals("S**\n##*\nG**\n", route.getRoute());
    }

    @Test
    public void test_7x5_Start_Node_In_Left_Middle_Area_Goal_In_Right_Middle_Area_Center_Between_Blocked() {
        final AStar as = new AStar(new Grid(
                ".......\n...#...\n.S.#.G.\n...#...\n.......\n"),
                new BinaryHeap<Node>());
        final Route route = as.findRoute();
        assertEquals(2 * Math.sqrt(2) + 4, route.getTotalCost(), 0);
        assertEquals(7, route.getNodes());
        assertEquals("..***..\n..*#*..\n.S.#.G.\n...#...\n.......\n", route
                .getRoute());
    }

}
