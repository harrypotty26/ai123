import java.util.Scanner;

class Graph {
    private static final int MAX = 100;
    private int[][] adjMatrix = new int[MAX][MAX];
    private int num;
    private int[] heuristic = new int[MAX];

    public Graph(int num) {
        this.num = num;
    }

    public void addEdge(int v1, int v2) {
        adjMatrix[v1][v2] = 1;
        adjMatrix[v2][v1] = 1;
    }

    public void setHeuristic(int vertex, int value) {
        heuristic[vertex] = value;
    }

    public int getNum() {
        return num;
    }

    public int[][] getAdjMatrix() {
        return adjMatrix;
    }

    public int[] getHeuristic() {
        return heuristic;
    }
}

public class BestFirstSearch {
    private static final int INF = 10000;

    // Function to get the next vertex with the minimum heuristic
    private static int getMin(int[] heuristics, boolean[] visited, int num, int current, Graph g) {
        int min = INF;
        int minIndex = -1;
        int[][] adjMatrix = g.getAdjMatrix();

        for (int i = 0; i < num; i++) {
            if (!visited[i] && heuristics[i] < min && adjMatrix[current][i] == 1) {
                min = heuristics[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    // Best-First Search algorithm
    public static void bestFirstSearch(Graph g, int start, int goal) {
        boolean[] visited = new boolean[g.getNum()];
        int current = start;
        visited[start] = true;

        System.out.print("Best-First Search Traversal: ");

        while (current != -1) {
            System.out.print(current + " ");

            if (current == goal) {
                System.out.println("\nGoal node " + goal + " reached.");
                return;
            }

            int next = getMin(g.getHeuristic(), visited, g.getNum(), current, g);

            if (next == -1) {
                System.out.println("\nGoal node " + goal + " not reachable.");
                return;
            }

            visited[next] = true;
            current = next;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int num = scanner.nextInt();
        Graph g = new Graph(num);

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        System.out.println("Enter the edges (v1 v2):");
        for (int i = 0; i < edges; i++) {
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            g.addEdge(v1, v2);
        }

        System.out.println("Enter heuristic values:");
        for (int i = 0; i < num; i++) {
            System.out.print("Heuristic value for vertex " + i + ": ");
            int h = scanner.nextInt();
            g.setHeuristic(i, h);
        }

        System.out.println("Graph:");
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                System.out.print(g.getAdjMatrix()[i][j] + " ");
            }
            System.out.println();
        }

        System.out.print("Enter the start vertex: ");
        int start = scanner.nextInt();

        System.out.print("Enter the goal vertex: ");
        int goal = scanner.nextInt();

        bestFirstSearch(g, start, goal);

        scanner.close();
    }
}
