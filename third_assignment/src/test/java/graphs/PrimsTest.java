package graphs;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import graphs.Util.Edge;
import graphs.Util.Pair;
public class PrimsTest {


    @Test
    public void readJSON(){
        try{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File("src/test/java/graphs/input.json"));

        for(JsonNode graphNode : root.get("graphs")){

            int id = graphNode.get("id").asInt();
            List<String> nodes = new ArrayList<>();
            graphNode.get("nodes").forEach(n -> nodes.add(n.asText()));

            List<Edge> edges = new ArrayList<>();
            for (JsonNode edgeNode : graphNode.get("edges")){

                String from = edgeNode.get("from").asText();
                String to = edgeNode.get("to").asText();
                int weight = edgeNode.get("weight").asInt();

                int u = from.charAt(0) - 'A';
                int v = to.charAt(0) - 'A';
                edges.add(new Edge(u,v,weight));

            }

            Pair<List<Edge>, Long> result = PrimMST.Prim(edges,nodes.size());

            System.out.println("Graph ID: " + id);
            System.out.println("MST edges: ");
            for (Edge e : result.first){

                System.out.println(nodes.get(e.u) + " - " + nodes.get(e.v) + " (" + e.w + ")");

            }
            


        }



    } catch (Exception e){

            e.printStackTrace();

        }
        

        

    }

}

    