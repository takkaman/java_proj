package AdjacencyList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph {
    private Map<String, Vertex> vertices = new LinkedHashMap<String, Vertex>();
    public int edgeNum;
    public int verNum;
//    public Graph(String[] vertexNames){
//        vertices = new LinkedHashMap<String, Vertex>();
//        for(String name : vertexNames){
//            vertices.put(name, new Vertex(name));
//        }
//    }

    public Graph() {}

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

    public void addEdge(String srcName, String destName, int weight){
        System.out.println("Adding edge: "+srcName+"-"+destName);
        edgeNum++;
        Vertex s, d;
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

        boolean dup = false;
        for(Edge e: s.neighbours) {
            if (e.target.name == destName) {
                dup = true;
                break;
            }
        }
        if (dup) {
            System.out.println("Duplicated edge add fpr Vertex: "+srcName+" , "+destName);
            return;
        }
        Edge new_edge = new Edge(vertices.get(destName),weight);
        s.neighbours.add(new_edge);
        new_edge = new Edge(vertices.get(srcName),weight);
        d.neighbours.add(new_edge);
    }

    /**
     * <p>
     * 删除边， 取得两个顶点即可:
     * 查找原顶点对应的邻接表的边，边的另一端等于目标顶点就是要删除的边
     * <p>
     *
     * @param src 原顶点
     * @param dest 目标顶点
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
     *  默认最短路径充值，图被算一次选择一个顶点算了一次最短路径后会
     *  更改最短路径值，要算其他顶点开始的最短路径得重新设置每个顶点最短
     *  路径的初始值
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
}
