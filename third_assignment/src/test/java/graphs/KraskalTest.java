package graphs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import graphs.Util.Edge;
import graphs.Util.Pair;

public class KraskalTest {
    
    @Test
    public void readJSON(){
        try{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File("src/test/java/graphs/input.json"));
        ObjectNode  outRoot = (ObjectNode) root.deepCopy();
        ArrayNode graphsInOutput = (ArrayNode) outRoot.get("graphs");
        int index = 0;
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

            Pair<List<Edge>, Long> result = KraskalMST.kruskal(edges,nodes.size());

                        ObjectNode output = (ObjectNode) graphsInOutput.get(index);

            ArrayNode mstArray = mapper.createArrayNode();
            
            for(Edge e : result.first){

                ObjectNode edgeJson = mapper.createObjectNode();
                edgeJson.put("from", nodes.get(e.u));
                edgeJson.put("to", nodes.get(e.v));
                edgeJson.put("weight", e.w);
                mstArray.add(edgeJson);


            }

            output.set("mst", mstArray);
            output.put("totalWeight", result.second);
            index++;
            File outputFile = new File("src/test/java/graphs/KraskalOutput.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, outRoot);


        }



    } catch (Exception e){

            e.printStackTrace();

        }
        

        

    }

}
