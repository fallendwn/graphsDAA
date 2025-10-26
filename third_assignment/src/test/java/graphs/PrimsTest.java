package graphs;


import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import graphs.Util.Edge;
import graphs.Util.Pair;
public class PrimsTest {

    private final String csv_path = "src/test/json/prims_results.csv";

    @Test
    public void readJSON(){
        String[] inputFiles = {

            "src/test/json/input_small.json",
            "src/test/json/input_medium.json",
            "src/test/json/input_large.json"

        };
        ObjectMapper mapper = new ObjectMapper();
        for(String input : inputFiles){
            try (FileWriter csvWriter = new FileWriter(csv_path)){
            csvWriter.write("graph_id,size,totalWeight,time,pqoper,relaxations\n");
            JsonNode root = mapper.readTree(new File(input));
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

                Pair<List<Edge>, Long> result = PrimMST.Prim(edges,nodes.size());

                Metrics.logReport("Prims", graphNode.get("id").asInt());

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
                File outputFile = new File("src/test/json/PrimOutput_" + input.substring(input.lastIndexOf('_')+1));
                mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, outRoot);
                csvWriter.write(String.format("%d,%s,%d,%d,%d,%d\n",index, edges.size(),
                result.second, Metrics.getTimeMs(), Metrics.getPQOperations(), Metrics.getRelaxations()));
            }



        } catch (Exception e){

                e.printStackTrace();

            }
        }

        

    }


}

    