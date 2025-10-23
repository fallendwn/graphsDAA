package graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import graphs.Util.Edge;
import graphs.Util.Pair;

public class PrimMST {

    public static Pair<List<Edge>, Long> Prim(List<Edge> edges, int n){

        List<List<Edge>> adj = new ArrayList<>();

        for(int i = 0; i < n ; i ++){

            adj.add(new ArrayList<>());

        }
        
        for(Edge e : edges){

            adj.get(e.u).add(new Edge(e.u, e.v, (int) e.w));
            adj.get(e.v).add(new Edge(e.v, e.u, (int) e.w));

        }
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(e->e.w));
        boolean[] visited = new boolean[n];

        List<Edge> mstEdges = new ArrayList<>();
        long totalWeight = 0;

        visited[0] = true;
        pq.addAll(adj.get(0));

        while(!pq.isEmpty() && mstEdges.size() < n-1){

            Edge minEdge = pq.poll();

            if (visited[minEdge.v]){
                
                continue;

            }

            visited[minEdge.v] = true;
            mstEdges.add(minEdge);
            totalWeight += minEdge.w;

            for(Edge next : adj.get(minEdge.v)){

                if(!visited[next.v]){

                    pq.add(next);

                }

            }

        }
        return new Pair<>(mstEdges,totalWeight);

    }

}
