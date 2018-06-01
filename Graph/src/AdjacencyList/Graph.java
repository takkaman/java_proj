package AdjacencyList;

import java.util.*;

public class Graph {
    // vertex name to vertex object map
    private Map<String, Vertex> vertices = new LinkedHashMap<String, Vertex>();
    // vertex degrees list
    private List<Vertex> degrees = new ArrayList<>();
    public int edgeNum;
    public int verNum;

    public Graph() {}

    // calculate maximum degree of a graph
    public int calcDegree() {
        int degree = 0;
        for (Map.Entry<String, Vertex> v: vertices.entrySet()) {
            int d = v.getValue().neighbours.size();
            if (d > degree) {
                degree = d;
            }
        }
        return degree;
    }

    /**
     * get degrees of each vertex and return with descending order
     */
    public List<Vertex> getDegrees() {
        List<Vertex> list = new ArrayList<Vertex>(vertices.values());
        Collections.sort(list, new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                if (o1.degree < o2.degree) return 1;
                if (o1.degree == o2.degree) return 0;
                return -1;
            }
        });
        return list;
    }

    /**
     * undirected graph with default weight 1
     * if edge defined multiple times, choose the 1st one
     */
    public void addEdge(String srcName, String destName, int weight){
//        System.out.println("Adding edge: "+srcName+"-"+destName);

        Vertex s, d;
        // create vertex and add into graph if not exist
        if (!vertices.containsKey(srcName)) {
            s = new Vertex(srcName);
            vertices.put(srcName, s);
            verNum++;
        } else {
            s = vertices.get(srcName);
        }
        if (!vertices.containsKey(destName)) {
            d = new Vertex(destName);
            vertices.put(destName, d);
            verNum++;
        } else {
            d = vertices.get(destName);
        }

        // check whether edge duplicated defined
        boolean dup = false;
        for(Edge e: s.neighbours) {
            if (e.target.name.compareTo(destName) == 0) {
                dup = true;
                break;
            }
        }
        if (dup) {
            // System.out.println("Duplicated edge add fpr Vertex: "+srcName+" , "+destName);
            return;
        }

        edgeNum++;
        Edge new_edge = new Edge(vertices.get(destName),weight);
        s.neighbours.add(new_edge);
        s.setDegree();
        new_edge = new Edge(vertices.get(srcName),weight);
        d.neighbours.add(new_edge);
        d.setDegree();
    }

    /**
     * <p>
     * delete edge， input is src and dst vertex:
     * traverse adjacent list，find edge with target equals dst and delete
     * <p>
     *
     * @param src source vertex
     * @param dest target vertex
     * @return
     */
    public Edge[] delEdge(String src, String dest){
        Edge[] delEdges = new Edge[2];
        Vertex s = vertices.get(src);
        Vertex d = vertices.get(dest);

        Edge delEdge = null;
        for(Edge edge : s.neighbours){
            if(edge.target == d){
                delEdges[0] = edge;
            }
        }
        s.neighbours.remove(delEdges[0]);

        for(Edge edge : d.neighbours){
            if(edge.target == s){
                delEdges[1] = edge;
            }
        }
        d.neighbours.remove(delEdges[1]);

        return delEdges;
    }

    /**
     *  <p>
     *  reset shortest distance
     *  need to reset distances once choose another vertex as source vertex
     *  to calculate dijkstra shortest path
     *  <p>
     */
    public void resetMinDistance(){
        for (String key : vertices.keySet()) {
            Vertex v = vertices.get(key);
            v.setMinDistance(Double.POSITIVE_INFINITY);
            v.path = new ArrayList<>();
            v.paths = new ArrayList<ArrayList<Vertex>>();
            v.pathCnt = 0;
        }
    }

    public Map<String,Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertex(String vertName){
        return vertices.get(vertName);
    }

    public void removeVertex(String vertName){
        vertices.remove(vertName);
    }
}
