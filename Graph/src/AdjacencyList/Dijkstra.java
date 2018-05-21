package AdjacencyList;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * <p>
 * Dijkstra shrtest path
 * <p>
 */
public class Dijkstra{

    public static void main(String[] arg){

        Dijkstra obj = new Dijkstra();
        int i, j, k = 1;
        double far;
        // Create a new graph.
        Graph gInit = obj.createGraph();
        List<Graph> gg = obj.createGraphs(gInit);
        System.out.println(gg.size());
        // 1. Degree centrality
        for (Graph g: gg) {
            System.out.println("Component: "+k);
            k++;
            System.out.println("1. Degree centrality report:");
            System.out.println("Max degree of the Graph is: " + g.calcDegree());
            System.out.println("Vertex num is: " + g.verNum);
            System.out.println("Edge num is: " + g.edgeNum);
            List<Vertex> degreeList = g.getDegrees();
            for (i = 0; i < degreeList.size(); i++) {
                if (i == 5) break;
                System.out.println(degreeList.get(i).name + "=" + degreeList.get(i).degree);
            }

            // Calculate Dijkstra.
            Map<String, Double> close = new HashMap<>();
            Map<String, Integer> betweeness = new HashMap<>();
//        obj.calcSP(g, g.getVertex("159323374"));
//////        // Print the minimum Distance.
//        i = 1;
//        for (Vertex v : g.getVertices().values()) {
//            System.out.print("Vertex " + i + " - " + v + " , Dist - " + v.minDistance + " , SP path num: " + v.pathCnt+"\n");
//            i++;
////            int j = 1;
////            for (ArrayList<Vertex> p : v.paths) {
////                System.out.print("Path: "+j+" - ");
////                for (Vertex pathvert : p) {
////                    System.out.print(pathvert + " ");
////                }
////                j++;
////                System.out.println("" + v);
////            }
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
            List<String> vertexes = new ArrayList<>(g.getVertices().keySet());
            Collections.sort(vertexes, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    int o1i = Integer.valueOf(o1);
                    int o2i = Integer.valueOf(o2);
                    if (o1i > o2i) return 1;
                    if (o1i == o2i) return 0;
                    return -1;
                }
            });

            // 2. Closeness centrality
            System.out.println("2. Closeness centrality report:");
            for (String vertex : vertexes) {
                far = 0;
                obj.calcSP(g, g.getVertex(vertex));
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
                //System.out.printf("Vector: %-12s far: %-8d close: %.8f\n", vertex, (int)far, 1/far);
                close.put(vertex, 1 / far);
            }
            Double MaxValueMap = (Collections.max(close.values()));
            int n = 5;
            List<Map.Entry<String, Double>> greatest = findGreatest(close, 5);
            System.out.println("top" + n + "entries");
            for (Map.Entry<String, Double> entry : greatest) {
                System.out.println(entry);
            }
            for (Map.Entry<String, Double> entry : close.entrySet()) {
                if (entry.getValue() == MaxValueMap) {
                    System.out.println(entry.getKey());
                }
            }

            // 3. Betweenness centrality:
            for (String v : vertexes) {
                betweeness.put(v, 0);
            }
            int ttlSPNum = 0;
            System.out.println("3. Betweenness centrality report:");
            for (i = 0; i < vertexes.size(); i++) {
                obj.calcSP(g, g.getVertex(vertexes.get(i)));
                for (j = i + 1; j < vertexes.size(); j++) {
                    ttlSPNum += g.getVertex(vertexes.get(j)).paths.size();
//                for (int k = 0; k < vertexes.size(); k++) {
//                    Vertex kv = g.getVertex(vertexes.get(k));
//                    if (k == i || k == j) continue;
                    for (ArrayList<Vertex> p : g.getVertex(vertexes.get(j)).paths) {
                        for (Vertex vv : p) {
                            betweeness.put(vv.name, betweeness.get(vv.name) + 1);
                        }
                    }
                }
            }
            for (String v : vertexes) {
                // System.out.printf("Vector: %-12s betweeness: %.8f\n", v, Double.valueOf(betweeness.get(v)*1.000/ttlSPNum));

                betweeness.put(v, betweeness.get(v));
            }
            //System.out.println(betweeness);
            //System.out.println("Total SP num: "+ttlSPNum);
            //betweeness.put(vertexes, Double.valueOf(betweeness.get()*1.000/ttlSPNum));
            int maxxValueInMap = (Collections.max(betweeness.values()));  // This will return max value in the Hashmap
            for (Map.Entry<String, Integer> entry : betweeness.entrySet()) {  // Itrate through hashmap
                if (entry.getValue() == maxxValueInMap) {
                    System.out.println(entry.getKey());     // Print the key with max value
                }
            }
            int x = 5;
            List<Map.Entry<String, Integer>> greatest2 = findGreatest(betweeness, 5);
            System.out.println("top" + n + "entries");
            for (Map.Entry<String, Integer> entry : greatest2) {
                System.out.println(entry);
            }
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

        fileName="./788813.edges";
//        List<String> vertexNames = new ArrayList<>();
        try
        {
            BufferedReader in=new BufferedReader(new FileReader(fileName));
            line=in.readLine();
            while (line!=null) {
//				System.out.println(line);
                String src = line.split(" ")[0];
                String dst = line.split(" ")[1];
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

    public List<Graph> createGraphs(Graph g) {
        List<Graph> gg = new ArrayList<>();
        Graph tmpG = g, tmp;

        boolean hasDisconnG = true;
        while (hasDisconnG) {
            List<Vertex> vertexes = new ArrayList<>(tmpG.getVertices().values());
            calcSP(tmpG, vertexes.get(0));
            gg.add(tmpG);
            hasDisconnG = false;
            List<Vertex> vv = new ArrayList<>();
            for (Vertex v : tmpG.getVertices().values()) {
                if (v.minDistance == Double.POSITIVE_INFINITY) {
                    // System.out.println("Vertex: " + v.name + ", dist: " + v.minDistance);
                    vv.add(v);
                    hasDisconnG = true;
                }
            }

            if (hasDisconnG) {
                tmp = new Graph();
                for (Vertex v: vv) {
                    tmpG.removeVertex(v.name);
                    for (Edge e: v.neighbours) {
                        tmp.addEdge(v.name, e.target.name, 1);
                    }
                }
                tmpG = tmp;
            }
        }
        return gg;
    }

    private static <K, V extends Comparable<? super V>> List<Map.Entry<K, V>>
    findGreatest(Map<K, V> map, int n)
    {
        Comparator<? super Map.Entry<K, V>> comparator =
                new Comparator<Map.Entry<K, V>>()
                {
                    @Override
                    public int compare(Map.Entry<K, V> e0, Map.Entry<K, V> e1)
                    {
                        V v0 = e0.getValue();
                        V v1 = e1.getValue();
                        return v0.compareTo(v1);
                    }
                };
        PriorityQueue<Map.Entry<K, V>> highest =
                new PriorityQueue<Map.Entry<K,V>>(n, comparator);
        for (Map.Entry<K, V> entry : map.entrySet())
        {
            highest.offer(entry);
            while (highest.size() > n)
            {
                highest.poll();
            }
        }

        List<Map.Entry<K, V>> result = new ArrayList<Map.Entry<K,V>>();
        while (highest.size() > 0)
        {
            result.add(highest.poll());
        }
        return result;
    }


    public void calcSP(Graph g, Vertex source){
        // Algorithm:
        // 1. Take the unvisited node with minimum weight.
        // 2. Visit all its neighbours.
        // 3. Update the distances for all the neighbours (In the Priority Queue).
        // Repeat the process till all the connected nodes are visited.

        // clear existing info
//        System.out.println("Calc SP from vertex:" + source.name);
        g.resetMinDistance();
        source.minDistance = 0;
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
        queue.add(source);

        while(!queue.isEmpty()){
            Vertex u = queue.poll();
            for(Edge neighbour:u.neighbours){
//                System.out.println("Scanning vector: "+neighbour.target.name);
                Double newDist = u.minDistance+neighbour.weight;

                if(neighbour.target.minDistance>newDist){
                    // Remove the node from the queue to update the distance value.
                    queue.remove(neighbour.target);
                    neighbour.target.minDistance = newDist;

                    // Take the path visited till now and add the new node.s
                    neighbour.target.path = new ArrayList<>(u.path);
                    neighbour.target.path.add(u);
//                    System.out.println("Path");
//                    for (Vertex vv: neighbour.target.path) {
//                        System.out.print(vv.name);
//                    }
//                    System.out.print("\n");

//                    System.out.println("Paths");
                    neighbour.target.pathCnt = 0;
                    neighbour.target.paths = new ArrayList<ArrayList<Vertex>>();
                    if (u.paths.size() == 0) {
                        ArrayList<Vertex> p = new ArrayList<Vertex>();
                        p.add(u);
                        neighbour.target.paths.add(p);
                        neighbour.target.pathCnt++;
                    } else {
                        for (ArrayList<Vertex> p : u.paths) {
                            ArrayList<Vertex> p1 = new ArrayList<>(p);
                            p1.add(u);
//                            for (Vertex vv : p1) {
//                                System.out.print(vv.name);
//                            }
//                            System.out.print("\n");
                            neighbour.target.paths.add(p1);
                            neighbour.target.pathCnt++;
                        }
                    }

                    //Reenter the node with new distance.
                    queue.add(neighbour.target);
                }
                else if (neighbour.target.minDistance == newDist) {
                    queue.remove(neighbour.target);
                    for(ArrayList<Vertex> p: u.paths) {
                        ArrayList<Vertex> p1 = new ArrayList<>(p);
                        p1.add(u);
                        neighbour.target.paths.add(p1);
                        neighbour.target.pathCnt++;
                    }
                    queue.add(neighbour.target);
                }
            }
        }
    }

}
