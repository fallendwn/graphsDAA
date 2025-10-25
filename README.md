<H2>Minimum spanning tree algorithms (Kruskal and Prims)</H2>
This project implements two algorithms for finding Minimum spanning tree in weighted undirected graph.

<ul>

  <li>Kruskal algorithms</li>
  <li>Prim algorithms</li>
  
</ul>

<h3>How it works</h3>

Kruskal Algorithm

<ol>

  <li>Sorts edges by eight</li>  
  <li>Adds edges by Disjoint Set Union</li>
  <li>Stops when mst has n-1 edges</li>
  
</ol>

Prim's Algorithm

<ul>

  <li>Builds MST from 1 node</li>
  <li>Through Priority Queue pick smallest edge connecting tree to a new vertex</li>
  <li>Repeats untill end</li>
  
</ul>

<h3>Input Data</h3>
Input data are stored in JSON files (small_graphs.json, medium_graphs.json, large_graphs.json) and also output is stored in the same package in json format.

<h3>Metrics</h3>

Both algorithms collect metrics.
<ul>
  <li>Unions, finds</li>
  <li>Priority queue operations</li>
  <li>Relaxations</li>
  <li>Time</li>
</ul>
