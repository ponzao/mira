package org.ponzao;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.expectNew;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { AStar.class })
public class AStarTest {
    @SuppressWarnings("unchecked")
    @Test(expected = RuntimeException.class)
    public void test_Route_Doesnt_Have_Route_From_Start_To_Goal()
            throws Exception {
        final Grid gridMock = createMock(Grid.class);
        final PriorityQueue<Node> openMock = createMock(PriorityQueue.class);
        final AStar as = new AStar(gridMock, openMock);

        final Node startNodeMock = createMock(Node.class);
        expect(gridMock.getStart()).andReturn(startNodeMock);

        final Node goalNodeMock = createMock(Node.class);
        expect(gridMock.getGoal()).andReturn(goalNodeMock);

        final Node[] neighbors = new Node[] {};
        expect(
                gridMock
                        .getAccessibleNeighborsWithValuesUpdatedOf(startNodeMock))
                .andReturn(neighbors);
        openMock.add(goalNodeMock);

        expect(openMock.size()).andReturn(0);

        replayAll();
        as.findRoute();
        verifyAll();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test_Find_Route_From_Grid_Of_Just_Start_Goal_Nodes()
            throws Exception {
        final Grid gridMock = createMock(Grid.class);
        final PriorityQueue<Node> openMock = createMock(PriorityQueue.class);
        final AStar as = new AStar(gridMock, openMock);

        final Node startNodeMock = createMock(Node.class);
        expect(gridMock.getStart()).andReturn(startNodeMock);

        final Node goalNodeMock = createMock(Node.class);
        expect(gridMock.getGoal()).andReturn(goalNodeMock);

        final Node[] neighbors = new Node[] { goalNodeMock };
        expect(
                gridMock
                        .getAccessibleNeighborsWithValuesUpdatedOf(startNodeMock))
                .andReturn(neighbors);
        openMock.add(goalNodeMock);

        expect(openMock.size()).andReturn(1);
        expect(openMock.remove()).andReturn(goalNodeMock);

        final String gridAsString = "SG\n";
        expect(gridMock.asString()).andReturn(gridAsString);

        final Grid gridWithRouteMock = createMock(Grid.class);
        expectNew(Grid.class, gridAsString).andReturn(gridWithRouteMock);

        expect(goalNodeMock.getParent()).andReturn(startNodeMock);
        expect(startNodeMock.getParent()).andReturn(null);

        final double cost = 1.0;
        expect(goalNodeMock.getCost()).andReturn(cost);
        expect(gridWithRouteMock.asString()).andReturn("SG\n");

        replayAll();
        final Route route = as.findRoute();
        verifyAll();

        assertEquals(1.0, route.getTotalCost(), 0);
        assertEquals(2, route.getNodes());
        assertEquals("SG\n", route.getRoute());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test_Find_Route_From_Grid_Of_Start_To_Goal_Through_One_Node()
            throws Exception {
        final Grid gridMock = createMock(Grid.class);
        final PriorityQueue<Node> openMock = createMock(PriorityQueue.class);
        final AStar as = new AStar(gridMock, openMock);

        final Node startNodeMock = createMock(Node.class);
        expect(gridMock.getStart()).andReturn(startNodeMock);

        final Node goalNodeMock = createMock(Node.class);
        expect(gridMock.getGoal()).andReturn(goalNodeMock);

        final Node middleNodeMock = createMock(Node.class);
        Node[] neighbors = new Node[] { middleNodeMock };
        expect(
                gridMock
                        .getAccessibleNeighborsWithValuesUpdatedOf(startNodeMock))
                .andReturn(neighbors);
        openMock.add(neighbors[0]);

        expect(openMock.size()).andReturn(1);
        expect(openMock.remove()).andReturn(neighbors[0]);

        neighbors = new Node[] { goalNodeMock };
        expect(
                gridMock
                        .getAccessibleNeighborsWithValuesUpdatedOf(middleNodeMock))
                .andReturn(neighbors);
        openMock.add(neighbors[0]);

        expect(openMock.size()).andReturn(1);
        expect(openMock.remove()).andReturn(neighbors[0]);

        final String gridAsString = "S.G\n";
        expect(gridMock.asString()).andReturn(gridAsString);

        final Grid gridWithRouteMock = createMock(Grid.class);
        expectNew(Grid.class, gridAsString).andReturn(gridWithRouteMock);

        expect(goalNodeMock.getParent()).andReturn(middleNodeMock);

        expect(middleNodeMock.getParent()).andReturn(startNodeMock);
        expect(middleNodeMock.getRow()).andReturn(0).times(2);
        expect(middleNodeMock.getColumn()).andReturn(1).times(2);
        final Node middleNodeVisitedRepresantationMock = createMock(Node.class);

        expectNew(Node.class, '*', 0, 1).andReturn(
                middleNodeVisitedRepresantationMock);
        gridWithRouteMock.setNode(middleNodeVisitedRepresantationMock, 0, 1);

        expect(startNodeMock.getParent()).andReturn(null);

        final double cost = 2.0;
        expect(goalNodeMock.getCost()).andReturn(cost);
        expect(gridWithRouteMock.asString()).andReturn("S*G\n");

        replayAll();
        final Route route = as.findRoute();
        verifyAll();

        assertEquals(2.0, route.getTotalCost(), 0);
        assertEquals(3, route.getNodes());
        assertEquals("S*G\n", route.getRoute());
    }
}
