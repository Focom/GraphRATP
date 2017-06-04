import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by sami- on 03/06/2017.
 */
public abstract class ShortestPath {
    protected Set<Integer> pathSet;
    protected Graph graph;
    protected int s;
    protected int t;
    protected Boolean[] marked;
    protected Integer[] previous;
    protected Double[] distance;
    protected Double[] excentricity;

    public ShortestPath(){
    }

    public ShortestPath(Graph graph, String name,String name2){
        this.graph=graph;
        this.graph=graph;
        this.s=graph.indexOfName(name);
        this.t=graph.indexOfName(name2);

    }
    protected void excentricity(){
        System.out.println();
        excentricity=new Double[graph.getGraphOrder()];
        for (int i = 0; i < excentricity.length; i++) {
            exploreStart(i,false);
            excentricity[i]=maxValue(distance);
        }
        System.out.print("excentricity of each vertex : ");disp(excentricity);
        System.out.println("diameter : "+ maxValue(excentricity));
        System.out.println("radius : "+minValue(excentricity));
        exploreStart(Arrays.asList(excentricity).indexOf(maxValue(excentricity)),false);
        printSP(Arrays.asList(distance).indexOf(maxValue(distance)));
    }

    abstract void exploreStart(int s,boolean dispResult);

    protected void printResult(){

    }

    public boolean isConnected() {
        return (pathSet.size()==graph.getGraphOrder());
    }

    public int cc() {
        return pathSet.size();
    }

    protected <T>void disp(T[] marked2) {
        for (int i = 0; i < marked2.length; i++) {
            System.out.print(i+":"+marked2[i]+" | ");
        }
        System.out.println();
    }

    protected void printSP(int v) {
        ArrayList<Integer> path=new ArrayList<Integer>();
        path.add(v);
        if (previous[v]>0){
            path.add(0,previous[v]);
            printSPbis(previous[v],path);
        }
        System.out.println(path.toString());

        System.out.println(graph.getNodes()[path.get(0)].getNom());
        for (int i = 1; i <path.size(); i++) {
            System.out.print(graph.getNodes()[path.get(i)].getNom());
            if(this.getClass()==DijkstraSP.class){
                System.out.println( "\t \t  \t \t distance from previous : "+graph.getDistance(path.get(i-1),path.get(i)));
            }else {
                System.out.println();
            }
        }

    }

    protected void printSPbis(int v, ArrayList<Integer>path) {
        if (previous[v]>0){
            path.add(0,previous[v]);
            printSPbis(previous[v],path);
        }
    }

    protected void print(ArrayList<Integer> pathSet) {
        Iterator<Integer> it = pathSet.iterator();
        while(it.hasNext()){
            System.out.println(graph.getNodes()[it.next()].getNom() + " | ");
        }
    }
    protected static double maxValue(Double[] distance2) {
        double max = distance2[0];
        for (int i = 0; i < distance2.length; i++) {
            if (distance2[i] > max) {
                max = distance2[i];
            }
        }
        return max;
    }

    protected static double minValue(Double[] array) {
        double min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    protected ArrayList<Integer> clone(ArrayList<Integer> listToClone) {
        ArrayList<Integer> clone=new ArrayList<>();
        for (int i = 0; i < listToClone.size(); i++) {
            clone.add(listToClone.get(i));
        }
        return clone;
    }

    public boolean haspathTo(int v){
        return marked[v];
    }
    public double  distTo(int v) {
        return distance[v];
    }
}