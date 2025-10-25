package graphs;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import graphs.Util.Edge;
import graphs.Util.Pair;

public class MutualEdgeTests {
    
    @Test
    public void emptyGraph(){

        List<Edge> edges = new ArrayList<>();

        Pair<List<Edge>, Long> resultPrim = PrimMST.Prim(edges, 0);
        Pair<List<Edge>, Long> resultKraskal = KraskalMST.kruskal(edges, 0);
        assertTrue(resultPrim.first.isEmpty());
        assertTrue(resultKraskal.first.isEmpty());
    }

    @Test
    public void singleVertexGraph(){

        List<Edge> edges = new ArrayList<>();
        Pair<List<Edge>, Long> resultPrim = PrimMST.Prim(edges, 1);
        Pair<List<Edge>, Long> resultKraskal = KraskalMST.kruskal(edges, 1);
        assertTrue(resultPrim.first.isEmpty());
        assertTrue(resultKraskal.first.isEmpty());
        
    }
    @Test
    public void disconnectedGraph(){

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0,1,5));

        Pair<List<Edge>, Long> prim = PrimMST.Prim(edges, 3);
        Pair<List<Edge>, Long> kruskal = KraskalMST.kruskal(edges,3);

        assertTrue(prim.first.size() < 2);
        assertTrue(kruskal.first.size() < 2);



    }

    @Test
    public void compareMST(){

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0,1,4));
        edges.add(new Edge(0,2,3));
        edges.add(new Edge(1,2,1));
        edges.add(new Edge(1,3,2));
        edges.add(new Edge(2,3,4));

        Pair<List<Edge>, Long> prim = PrimMST.Prim(edges,4);
        Pair<List<Edge>, Long> kraskal = KraskalMST.kruskal(edges, 4);

        assertEquals(prim.second, kraskal.second);

    }

}
