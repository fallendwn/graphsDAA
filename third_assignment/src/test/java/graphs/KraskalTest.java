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

public class KraskalTest {
    private final String csv_path = "src/test/json/kraskal_results.csv";

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
            csvWriter.write("graph_id,size,totalWeight,time,comparisons,unions,finds");
            JsonNode root = mapper.readTree(new File(input));
            ObjectNode  outRoot = (ObjectNode) root.deepCopy();
            ArrayNode graphsInOutput = (ArrayNode) outRoot.get("graphs");
            int index = 0;
            for(JsonNode graphNode : root.get("graphs")){

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
                Metrics.logReport("Kruskal", graphNode.get("id").asInt());
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
                File outputFile = new File("src/test/json/KraskalOutput_" + input.substring(input.lastIndexOf('_')+1));
                mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, outRoot);

                csvWriter.write(String.format("%d,%s,%d,%d,%d,%d,%d",index, edges.size(),
                result.second, Metrics.getTimeMs(), Metrics.getComparisons(), Metrics.getUnions(), Metrics.getFinds()));
            }



        } catch (Exception e){

                e.printStackTrace();

            }
        
        }
        

    }

}
