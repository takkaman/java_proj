package AdjacencyList;

import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;

/**
 * <p>
 * Dijkstra shrtest path
 * <p>
 */
public class Dijkstra{

    public static void main(String[] arg){

        Dijkstra obj = new Dijkstra();
        int i;
        double far;
        // Create a new graph.
        Graph g = obj.createGraph();
        System.out.println("Max degree of the Graph is: "+g.calcDegree());
        System.out.println("Vertex num is: "+g.verNum);
        System.out.println("Edge num is: "+g.edgeNum);
        // Calculate Dijkstra.
        Map<String, Double> close = new HashMap<>();
//        obj.calcSP(g, g.getVertex("30495974"));
//////        // Print the minimum Distance.
//        i = 1;
//        for (Vertex v : g.getVertices().values()) {
//            System.out.print("Vertex " + i + " - " + v + " , Dist - " + v.minDistance + " , Path - ");
//            i++;
//            for (Vertex pathvert : v.path) {
//                System.out.print(pathvert + " ");
//            }
//            System.out.println("" + v);
//        }
//
//        obj.calcSP(g, g.getVertex("18041656"));
//////        // Print the minimum Distance.
//        i = 1;
//        for (Vertex v : g.getVertices().values()) {
//            System.out.print("Vertex " + i + " - " + v + " , Dist - " + v.minDistance + " , Path - ");
//            i++;
//            for (Vertex pathvert : v.path) {
//                System.out.print(pathvert + " ");
//            }
//            System.out.println("" + v);
//        }

        for (String vertex: g.getVertices().keySet()) {
            far = 0;
            obj.calcSP(g, g.getVertex(vertex));
            i = 1;
//        // Print the minimum Distance.
            for (Vertex v : g.getVertices().values()) {
//                System.out.print("Vertex " + i + " - " + v + " , Dist - " + v.minDistance + " , Path - ");
//                i++;
                far += v.minDistance;
//                for (Vertex pathvert : v.path) {
//                    System.out.print(pathvert + " ");
//                }
//                System.out.println("" + v);
            }
            System.out.println("Vector: "+vertex+" far: "+far+" close: "+1/far);
            close.put(vertex, 1/far);
        }
//
//        System.out.println("---------**********------------");
//        Edge[] delEdges = g.delEdge("v0", "v7");
//        System.out.println("Deleted edge vertexes: " + delEdges[0].target.name+" , "+ delEdges[1].target.name);
//        g.resetMinDistance();
//        obj.calculate(g.getVertex("v0"));
//        // 删除一条边后的最短路径
//        for(Vertex v : g.getVertices().values()){
//            System.out.print("Vertex - " + v + " , Dist - " + v.minDistance
//                    + " , Path - ");
//            for (Vertex pathvert : v.path) {
//                System.out.print(pathvert + " ");
//            }
//            System.out.println("" + v);
//        }
    }

    public Graph createGraph() {
        String fileName;
//        fileName ="./428333.edges";
        String line;
        Graph g = new Graph();
//        List<String> vertexNames = new ArrayList<>();
//        try
//        {
//            BufferedReader in=new BufferedReader(new FileReader(fileName));
//            line=in.readLine();
//            while (line!=null) {
////				System.out.println(line);
//                String src = line.split(" ")[0];
//                String dst = line.split(" ")[1];
////                vertexNames.add(src);
////                vertexNames.add(dst);
////                System.out.println(src+" "+dst);
//                g.addEdge(src, dst, 1);
//                line=in.readLine();
//            }
//            in.close();
//        } catch (Exception e) {
//			e.printStackTrace();
//        }

        fileName="./78813.edges";
//        List<String> vertexNames = new ArrayList<>();
        try
        {
            BufferedReader in=new BufferedReader(new FileReader(fileName));
            line=in.readLine();
            while (line!=null) {
//				System.out.println(line);
                String src = line.split(" ")[0];
                String dst = line.split(" ")[1];
//                vertexNames.add(src);
//                vertexNames.add(dst);
//                System.out.println(src+" "+dst);
                g.addEdge(src, dst, 1);
                line=in.readLine();
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Graph g = new Graph(new String[]{"v0", "v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8"});
//        // Add the required edges.
//        g.addEdge("v0", "v1", 4); g.addEdge("v0", "v7", 8);
//        g.addEdge("v1", "v2", 8); g.addEdge("v1", "v7", 11); g.addEdge("v2", "v1", 8);
//        g.addEdge("v2", "v8", 2); g.addEdge("v2", "v5", 4); g.addEdge("v2", "v3", 7);
//        g.addEdge("v3", "v2", 7); g.addEdge("v3", "v5", 14); g.addEdge("v3", "v4", 9);
//        g.addEdge("v4", "v3", 9); g.addEdge("v4", "v5", 10);
//        g.addEdge("v5", "v4", 10); g.addEdge("v5", "v3", 9); g.addEdge("v5", "v2", 4);
//        g.addEdge("v5", "v6", 2);
//        g.addEdge("v6", "v7", 1); g.addEdge("v6", "v8", 6); g.addEdge("v6", "v5", 2);
//        g.addEdge("v7", "v0", 8); g.addEdge("v7", "v8", 7); g.addEdge("v7", "v1", 11);
//        g.addEdge("v7", "v6", 1);
//        g.addEdge("v8", "v2", 2); g.addEdge("v8", "v7", 7); g.addEdge("v8", "v6", 6);

//
        return g;
    }

    public void calcSP(Graph g, Vertex source){
        // Algo:
        // 1. Take the unvisited node with minimum weight.
        // 2. Visit all its neighbours.
        // 3. Update the distances for all the neighbours (In the Priority Queue).
        // Repeat the process till all the connected nodes are visited.

        // clear existing info
        System.out.println("Calc SP from vertex:" + source.name);
        g.resetMinDistance();
        source.minDistance = 0;
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
        queue.add(source);

        while(!queue.isEmpty()){

            Vertex u = queue.poll();

            for(Edge neighbour:u.neighbours){
                Double newDist = u.minDistance+neighbour.weight;

                if(neighbour.target.minDistance>newDist){
                    // Remove the node from the queue to update the distance value.
                    queue.remove(neighbour.target);
                    neighbour.target.minDistance = newDist;

                    // Take the path visited till now and add the new node.s
                    neighbour.target.path = new LinkedList<Vertex>(u.path);
                    neighbour.target.path.add(u);

                    //Reenter the node with new distance.
                    queue.add(neighbour.target);
                }
            }
        }
    }

}
