package graphs;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Metrics {

    private static final String name = "output/mst_metrics.csv";

    private static long compar;
    private static long unions;
    private static long finds;
    private static long pqOper;
    private static long relaxation;
    private static long timeMs;

    private static long startTime = 0;
    private static long endTime = 0;



    public static void reset(){

        compar = 0;
        unions = 0;
        pqOper = 0;
        relaxation = 0;
        timeMs = 0;
        startTime = 0;
        endTime = 0;

    }
    public static void increaseComp(){

        compar++;

    }

    public static void increaseUnions(){

        unions++;

    }

    public static void increaseFinds(){

        finds++;

    }

    public static void increasePQ(){

        pqOper++;

    }

    public static void increaseRelax(){

        relaxation++;

    }
    
    public static void startTimer(){

        startTime = System.nanoTime();

    }

    public static void stopTimer(){

        endTime = System.nanoTime();

    }

    public static long getTimeMs(){

        return (endTime - startTime) / 1_000_000;

    }

    public static long getComparisons() { 
        return compar; 
    }
    public static long getUnions() {
        return unions; 
    }
    public static long getFinds() {
        return finds; 
    }
    public static long getPQOperations() {
        return pqOper;
    }
    public static long getRelaxations() {
        return relaxation;
    }





    public static void logReport(String operation, int graphId){

        try{
            FileWriter fw = new FileWriter(name,true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("graphid, algorithm, time, compar, unions, fins, pqop, relaxation");
            pw.printf("%d, %s, %d, %d, %d, %d, %d, %d", graphId, operation, getTimeMs(), compar, unions, finds, pqOper, relaxation);
        }
        catch(Exception e){
            
            e.printStackTrace();

        }
    }

}
