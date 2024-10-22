import java.util.*;

public class dfs{
    static final int MAX_VERTICES = 100;

    // Function to perform DFS traversal
    public static void dfs(int[][] graph, int numVertices, int vertex, boolean[] visited) {
        System.out.print(vertex + " ");
        visited[vertex] = true;

        // Visit all adjacent vertices recursively
        for (int i = 0; i < numVertices; i++) {
            if (graph[vertex][i] == 1 && !visited[i]) {
                dfs(graph, numVertices, i, visited);
            }
        }
    }

    public static void main(String[] args) {
        int numVertices = 6;

        // Adjacency matrix representation of the graph
        int[][] graph = {
            {0, 1, 1, 0, 0, 0},
            {1, 0, 0, 1, 1, 0},
            {1, 0, 0, 0, 0, 1},
            {0, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1},
            {0, 0, 1, 0, 1, 0}
        };

        // Array to track visited vertices
        boolean[] visited = new boolean[MAX_VERTICES];

        System.out.println("DFS starting from vertex 0:");
        dfs(graph, numVertices, 0, visited);
        System.out.println();
    }
}
