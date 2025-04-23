
// Interface of graph
interface Graph {
    boolean isConnected();
    int[] reachable(int vertex);
    Edge[] mst();
    int[] shortestPath(int vertex1,int vertex2);   
}
