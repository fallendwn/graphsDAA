package graphs;

public class Util {

    public static class Edge implements Comparable<Edge>{
        int u,v;
        long w;
        public Edge(int u, int v, int w){

            this.u = u;
            this.v = v;
            this.w = w;

        }

        @Override
        public int compareTo(Edge other){

            return Long.compare(this.w, other.w);

        }

    }

    public static class DisjointSetUnion{

        private final int[] parent;
        private final int[] rank;
        public DisjointSetUnion(int n){

            parent = new int[n];
            rank = new int[n];
            for(int i = 0 ; i < n ; i++){

                parent[i] = i;
                rank[i] = 0;

            }

        }

        public int find(int i){

            if (parent[i] == i){

                return i;

            }
            else{
                parent[i] = find(parent[i]);
            }

            return parent[i];

        }

        public boolean union(int i, int j){

            int rooti = find(i);
            int rootj = find(j);

            if (rooti == rootj) return false;

            if (rank[rooti] < rank[rootj]){

                parent[rooti] = rootj;

            }else if(rank[rootj] < rank[rooti]){

                parent[rootj] = rooti;

            }else{

                parent[rootj] = rooti;
                rank[rooti] ++;

            }
            return true;
        }

    }

    static class Pair<A,B>{

        public final A first;
        public final B second;

        public Pair(A a, B b){

            this.first = a;
            this.second = b;

        }

    }
}
