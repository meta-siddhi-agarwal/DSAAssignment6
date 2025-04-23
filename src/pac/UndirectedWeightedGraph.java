import java.util.Arrays;
import java.util.Comparator;

public class UndirectedWeightedGraph implements Graph{
    int V;
    int E;
    int countEdges;
    Edge edgeList[];

    /**
     * Constructor to initialize the vertex, edge and the edgelist array
     * @param {V} the number of vertices
     * @param {E} the number of edges
     */
    public UndirectedWeightedGraph(int V,int E){
        this.V = V;
        this.E = E;
        this.countEdges = 0;
        this.edgeList = new Edge[E];
    }

    /**
     * Method to add edge in a graph
     * @param {source} the starting vertex
     * @param {dest} the destination vertex
     * @param {weight} the edge weight
     */
    void addEdge(int source,int dest,int weight) {
        if(countEdges == edgeList.length) {
            System.out.println("The edge list is full !!");
            return;
        }

        edgeList[countEdges] = new Edge(source, dest, weight);
        countEdges++;
    }

    /**
     * Method for Depth-First Search traversal pf graph
     * @param {vertex} the starting vertex 
     * @param {visited} visited array to keep track of visited vertices
     */
    void dfs(int vertex,boolean visited[]) {
        visited[vertex] = true;
        for(int i = 0;i < E;i++) {
            Edge edge = edgeList[i];
            if((edge.source == vertex) && !visited[edge.dest]) {
                dfs(edge.dest, visited);
            }
            else if((edge.dest == vertex) && !visited[edge.source]) {
                dfs(edge.source,visited);
            }
        }
    }

    /**
     * Method to check if graph is connected graph or not
     * @return returns {@code true} if graph is connected else {@code false}
     */
    public boolean isConnected() {
        boolean visited[] = new boolean[V];
        dfs(0,visited);

        for(int i=0;i<V;i++) {
            if(!visited[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Method to return the vertices reachable from a given starting vertex
     * @param {vertex} the starting vertex 
     * @return returns the reachable vertices
     */
    public int[] reachable(int vertex) {
        boolean visited[] = new boolean[V];
        dfs(vertex, visited);

        int count = 0;
        for(boolean vis :  visited) {
            if(vis) {
                count++;
            }
        }

        int reachableNodes[] = new int[count];
        int index = 0;
        for(int i=0;i<V;i++) {
            if(visited[i]) {
                reachableNodes[index] = i;
                index++;
            }
        }

        return reachableNodes;
    }

    // Used to compare the edge weights
    public static Comparator<Edge> weightComparator = new Comparator<Edge>() {
        @Override
        public int compare(Edge e1, Edge e2) {
            return (e1.weight - e2.weight);
        }
    };

    /* int findParent(int u,int parent[]) {
        if(u==parent[u]) return u;

        return parent[u]=findParent(parent[u], parent);
    }

    void union(int u,int v,int parent[],int rank[]) {
        u = findParent(u, parent);
        v = findParent(v, parent);
        if(rank[u]<rank[v]) {
            parent[u] = v;
        }else if(rank[v]<rank[u]) {
            parent[v] = u;
        }
        else {
            parent[v] = u;
            rank[u]++; 
        }
    } */

    /**
     * Method to find the minimum spanning tree
     * @return returns the edgeList for the minimum spanning tree
     */
    public Edge[] mst() {

        DisjointSet dSet = new DisjointSet(V);

        Edge[] mstEdges = new Edge[V - 1];
        Edge[] sortedEdges = new Edge[countEdges];
        for (int index = 0; index < countEdges; index++) {
            sortedEdges[index] = edgeList[index];
        }
        Arrays.sort(sortedEdges, weightComparator);

        int costMST = 0;
        int countMstEdges = 0;
        for(Edge edge : sortedEdges) {
            int weight = edge.weight;
            int u = edge.source;
            int v = edge.dest;

            if(dSet.findUParent(u) != dSet.findUParent(v)) {
                costMST+=weight;
                mstEdges[countMstEdges++] = edge;
                dSet.unionByRank(u, v);
            }
        }

        System.out.println("Minimum cost: "+costMST);
        return mstEdges;
    }

    /**
     * Method to return the shortest path
     * @param {startVertex} the starting vertex
     * @param {endVertex} the end vertex
     * @return returns the shortest path
     */
    public int[] shortestPath(int startVertex, int endVertex) {
        int[] distance = new int[V];
        boolean[] visited = new boolean[V];
        int[] previous = new int[V];

        for (int index = 0; index < V; index++) {
            distance[index] = Integer.MAX_VALUE;
            previous[index] = -1;
        }

        distance[startVertex] = 0;

        for (int index = 0; index < V; index++) {
            int currentVertex = -1;

            for (int inIndex = 0; inIndex < V; inIndex++) {
                if (!(visited[inIndex]) && ((currentVertex == -1) || (distance[inIndex] < distance[currentVertex]))) {
                    currentVertex = inIndex;
                }
            }
            if (distance[currentVertex] == Integer.MAX_VALUE) {
                break;
            }
            visited[currentVertex] = true;

            for (int inIndex = 0; inIndex < countEdges; inIndex++) {
                Edge edge = edgeList[inIndex];
                int neighbour = -1;

                if (edge.source == currentVertex) {
                    neighbour = edge.dest;
                } else if (edge.dest == currentVertex) {
                    neighbour = edge.source;
                }

                if ((neighbour != -1) && (distance[currentVertex] + edge.weight < distance[neighbour])) {
                    distance[neighbour] = distance[currentVertex] + edge.weight;
                    previous[neighbour] = currentVertex;
                }
            }
        }

        int[] path = new int[V];
        int pathLength = 0;
        for (int vertex = endVertex; vertex != -1; vertex = previous[vertex]) {
            path[pathLength] = vertex;
            pathLength++;
        }

        int[] shortestPath = new int[pathLength];
        for (int index = 0; index < pathLength; index++) {
            shortestPath[index] = path[pathLength - index - 1];
        }

        return shortestPath;
    }

}
