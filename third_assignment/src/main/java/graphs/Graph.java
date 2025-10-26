package graphs;

import java.util.ArrayList;
import java.util.List;

import graphs.Util.Edge;

public class Graph {
    
    private final List<Edge> edges;
    private final int count;

    public Graph(int count){

        this.count = count;
        this.edges = new ArrayList<>();

    }

    public void addEdge(int u, int v, int w){
        
        edges.add(new Edge(u,v,w));

    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getCount() {
        return count;
    }

    public boolean isEmpty() {
        return edges.isEmpty() || count == 0;
    }

}
