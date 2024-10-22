import java.util.Scanner;

public class GraphColoring {

    static final int MAX_VERTICES = 100;  // Define a maximum number of vertices

    // Function to check if the current color assignment is safe for vertex v
    static boolean isSafe(int[][] graph, int[] color, int v, int c, int V) {
        for (int i = 0; i < V; i++) {
            if (graph[v][i] == 1 && color[i] == c) {
                return false;  // An adjacent vertex has the same color
            }
        }
        return true;
    }

    // Function to solve the graph coloring problem using backtracking
    static boolean graphColoringUtil(int[][] graph, int m, int[] color, int v, int V) {
        if (v == V) {
            return true;  // All vertices are assigned a color
        }

        for (int c = 1; c <= m; c++) {  // Try different colors
            if (isSafe(graph, color, v, c, V)) {
                color[v] = c;  // Assign color c to vertex v

                // Recur to assign colors to the rest of the vertices
                if (graphColoringUtil(graph, m, color, v + 1, V)) {
                    return true;
                }

                // Backtrack
                color[v] = 0;  // Remove assigned color
            }
        }
        return false;  // No valid coloring found
    }

    // Function to solve the m-coloring problem
    static boolean graphColoring(int[][] graph, int m, int V) {
        int[] color = new int[V];  // Array to store color assignments

        // Initialize all vertices as uncolored (0)
        for (int i = 0; i < V; i++) {
            color[i] = 0;
        }

        // Start the coloring process from vertex 0
        if (!graphColoringUtil(graph, m, color, 0, V)) {
            System.out.println("Solution does not exist");
            return false;
        }

        // Print the solution
        System.out.println("Solution exists: Following are the assigned colors:");
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + " ---> Color " + color[i]);
        }
        return true;
    }

    // Driver code
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = sc.nextInt();

        int[][] graph = new int[V][V];

        // Input the adjacency matrix
        System.out.println("Enter the adjacency matrix (" + V + " x " + V + "):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        // Input the number of colors
        System.out.print("Enter the number of colors: ");
        int m = sc.nextInt();

        // Call the graph coloring function
        graphColoring(graph, m, V);

        sc.close();
    }
}