import java.util.*;

class Node implements Comparable<Node> {
    int vertex;
    double cost;
    double heuristic;
    Node parent;

    public Node(int v, double c, double h, Node p) {
        this.vertex = v;
        this.cost = c;
        this.heuristic = h;
        this.parent = p;
    }

    public double totalCost() {
        return this.cost + this.heuristic;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.totalCost(), other.totalCost());
    }
}

public class AStarAlgorithm {

    // Heuristic function (Manhattan distance in this case)
    public static double heuristic(int current, int goal) {
        return Math.abs(current - goal);
    }

    // Reconstruct the path from the goal node back to the start node
    public static List<Integer> reconstructPath(Node node) {
        List<Integer> path = new ArrayList<>();
        while (node != null) {
            path.add(node.vertex);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }

    // A* algorithm implementation
    public static List<Integer> aStar(int[][] adjMatrix, int start, int goal) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Map<Integer, Double> gScore = new HashMap<>();
        Map<Integer, Node> allNodes = new HashMap<>();

        // Initialize gScore for all nodes with infinity
        for (int i = 0; i < adjMatrix.length; i++) {
            gScore.put(i, Double.POSITIVE_INFINITY);
        }

        gScore.put(start, 0.0);
        openSet.add(new Node(start, 0, heuristic(start, goal), null));
        allNodes.put(start, new Node(start, 0, heuristic(start, goal), null));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            // If goal is reached, reconstruct the path
            if (current.vertex == goal) {
                List<Integer> path = reconstructPath(allNodes.get(goal));
                // Clean up dynamically allocated nodes
                return path;
            }

            // Iterate over all neighbors
            for (int i = 0; i < adjMatrix.length; i++) {
                if (adjMatrix[current.vertex][i] == 0) continue;

                double tentative_gScore = gScore.get(current.vertex) + adjMatrix[current.vertex][i];

                if (tentative_gScore < gScore.get(i)) {
                    gScore.put(i, tentative_gScore);
                    Node neighborNode = new Node(i, tentative_gScore, heuristic(i, goal), new Node(current.vertex, current.cost, current.heuristic, current.parent));

                    openSet.add(neighborNode);
                    allNodes.put(i, neighborNode);
                }
            }
        }

        // Return empty list if no path is found
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        int[][] adjMatrix = {
                {0, 1, 4, 0, 0, 0},
                {1, 0, 4, 2, 7, 0},
                {4, 4, 0, 3, 5, 0},
                {0, 2, 3, 0, 4, 6},
                {0, 7, 5, 4, 0, 7},
                {0, 0, 0, 6, 7, 0}
        };

        int start = 0;
        int goal = 4;

        List<Integer> path = aStar(adjMatrix, start, goal);

        if (!path.isEmpty()) {
            System.out.print("Path found: ");
            for (int vertex : path) {
                System.out.print(vertex + " ");
            }
            System.out.println();
        } else {
            System.out.println("No path found.");
        }
    }
}
