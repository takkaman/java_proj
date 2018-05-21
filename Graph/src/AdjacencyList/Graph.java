package AdjacencyList;

import java.util.*;

public class Graph {
    private Map<String, Vertex> vertices = new LinkedHashMap<String, Vertex>();
    private List<Vertex> degrees = new ArrayList<>();
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

    public void addEdge(String srcName, String destName, int weight){
//        System.out.println("Adding edge: "+srcName+"-"+destName);

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
            if (e.target.name.compareTo(destName) == 0) {
                dup = true;
                break;
            }
        }
        if (dup) {
            // System.out.println("Duplicated edge add fpr Vertex: "+srcName+" , "+destName);
            return;
        }
//        if (srcName.compareTo("11336782") == 0) {
//            System.out.println(srcName+" "+destName);
//        }
//        if (destName.compareTo("11336782") == 0) {
//            System.out.println(destName+" "+srcName);
//        }
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

    public void removeVertex(String vertName){
        vertices.remove(vertName);
    }
}
