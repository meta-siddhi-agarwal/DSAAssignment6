import java.util.Scanner;

public class GraphMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of vertices -- ");
        int vertices = sc.nextInt();
        System.out.println("Enter the number of edges --- ");
        int edges = sc.nextInt();
        
        UndirectedWeightedGraph graph = new UndirectedWeightedGraph(vertices, edges);

        System.out.println("Enter the source, destination and edge weight for each edge -- ");
        for(int index = 0;index < edges;index++) {
            int source = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();

            graph.addEdge(source, dest, weight);
        }

        System.out.println("Source"+"\t"+"Destination"+"\t"+"Weight");
        for(int i=0;i<edges;i++) {
            System.out.println(graph.edgeList[i].source+"\t"+graph.edgeList[i].dest+"\t"+graph.edgeList[i].weight);
        }

        do {
            System.out.println("1: Check graph is connected or not");
            System.out.println("2: Display Reachable vertices from a given vertex");
            System.out.println("3: Minimum spanning tree");
            System.out.println("4: Shortest path");
            System.out.println("5: Exit");

            System.out.println("Enter the choice -- ");
            int choice = sc.nextInt();

            switch(choice) {
                case 1 : 
                    boolean result = graph.isConnected();
                    if(result) {
                        System.out.println("Graph is connected");
                    } else {
                        System.out.println("Graph is not connected");
                    }
                break;

                case 2:
                    System.out.println("Enter the vertex -- ");
                    int vertex = sc.nextInt();
                    if(vertex<0 || vertex >= vertices) {
                        System.out.println("Vertex is not valid!!");
                        return;
                    }
                    int reachableNodes[] = graph.reachable(vertex);
                    for(int i=0;i<reachableNodes.length;i++) {
                        System.out.println(vertex+"-->"+reachableNodes[i]);
                    }
                break;

                case 3:
                    System.out.println("Minimum Spanning Tree -- ");
                    Edge[] mstEdges = graph.mst();
                    for(int i=0;i<mstEdges.length;i++) {
                        System.out.println(mstEdges[i].source+" "+mstEdges[i].dest+" "+mstEdges[i].weight);
                    }
                break;

                case 4:
                    System.out.println("Enter the start vertex -- ");
                    int startVertex = sc.nextInt();
                    if(startVertex<0 || startVertex >= vertices) {
                        System.out.println("Start vertex is not valid!!");
                        return;
                    }
                    System.out.println("Enter the end vertex -- ");
                    int endVertex = sc.nextInt();
                    if(endVertex<0 || endVertex >= vertices) {
                        System.out.println("End vertex is not valid!!");
                        return;
                    }
                    System.out.println("Shortest Path --- ");
                    int[] shortestPath = graph.shortestPath(startVertex, endVertex);

                    for(int i=0;i<shortestPath.length-1;i++) {
                        System.out.print(shortestPath[i] + "-->");
                    }
                    System.out.print(shortestPath[shortestPath.length-1]);
                    System.out.println();
                break;
                case 5 :
                    System.out.println("Exiting....");
                    return;
                default :
                    System.out.println("Enter valid choice");
            }
            
        } while(true);
        
    }
    
}
