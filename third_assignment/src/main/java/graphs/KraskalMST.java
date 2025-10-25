package graphs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import graphs.Util.DisjointSetUnion;
import graphs.Util.Edge;
import graphs.Util.Pair;

public class KraskalMST{

    public static Pair<List<Edge>, Long> kruskal(List<Edge> edges, int n){
        Metrics.reset();
        Metrics.startTimer();
        Collections.sort(edges);
        DisjointSetUnion dsu = new DisjointSetUnion(n);

        List<Edge> MSTlist = new ArrayList<>();
        long totalWeight = 0;

        for (Edge e : edges){
            Metrics.increaseComp();
            if (dsu.union(e.u, e.v)){

                MSTlist.add(e);
                totalWeight+=e.w;
                if(MSTlist.size() == n-1){

                    break;

                }

            }

        }
        Metrics.stopTimer();
        return new Pair<>(MSTlist, totalWeight);
    }


}
