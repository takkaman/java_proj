package AdjacencyList;
import java.util.*;

public class Vertex implements Comparable<Vertex>{

    public final String name;
    public ArrayList<Edge> neighbours;
    public int degree;
    public ArrayList<Vertex> path;
    public ArrayList<ArrayList<Vertex>> paths;
    public int pathCnt;
    public double minDistance;
    public Vertex previous;

    public int compareTo(Vertex other){
        return Double.compare(minDistance,other.minDistance);
    }

    public Vertex(String name){
        this.name = name;
        neighbours = new ArrayList<Edge>();
        path = new ArrayList<>();
        paths = new ArrayList<ArrayList<Vertex>>();
        pathCnt = 0;
    }

    public String toString(){
        return name;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    public void setDegree() {
        this.degree = this.neighbours.size();
    }

}
