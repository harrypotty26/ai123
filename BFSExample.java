import java.util.*;

public class BFSExample {

    // Function to perform BFS traversal
    public static void BFS(List<List<Integer>> graph, int start) {
        int n = graph.size();
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        // Start from the given node
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " "); // Process the node (or print it)

            // Explore all adjacent nodes
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of nodes and edges: ");
        int n = sc.nextInt();
        int e = sc.nextInt();

        // Create an adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        System.out.println("Enter the edges (u v) where u and v are nodes: ");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u); // Comment this line if the graph is directed
        }

        System.out.print("Enter the starting node for BFS: ");
        int start = sc.nextInt();

        System.out.print("BFS Traversal starting from node " + start + ": ");
        BFS(graph, start);
    }
}