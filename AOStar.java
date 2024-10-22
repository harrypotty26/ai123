import java.util.*;

class Node {
    String name;
    int cost;

    public Node(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }
}

class Condition {
    List<String> orNodes = new ArrayList<>();
    List<String> andNodes = new ArrayList<>();
}

public class AOStar {

    static final int MAX_NODES = 100;
    static final int INT_MAX = Integer.MAX_VALUE;

    static List<Node> nodes = new ArrayList<>();
    static List<Condition> conditions = new ArrayList<>();

    // Function to get the cost of a node by its name
    public static int getNodeCost(String name) {
        for (Node node : nodes) {
            if (node.name.equals(name)) {
                return node.cost;
            }
        }
        return INT_MAX; // Return a large value if node not found
    }

    // Function to calculate costs based on AND/OR conditions
    public static int calculateCost(Condition condition) {
        int totalCost = 0;

        // Calculate AND cost
        if (!condition.andNodes.isEmpty()) {
            for (String andNode : condition.andNodes) {
                totalCost += getNodeCost(andNode);
            }
        }

        // Calculate OR cost
        if (!condition.orNodes.isEmpty()) {
            int minOrCost = INT_MAX;
            for (String orNode : condition.orNodes) {
                int cost = getNodeCost(orNode);
                if (cost < minOrCost) {
                    minOrCost = cost;
                }
            }
            totalCost += minOrCost;
        }

        return totalCost;
    }

    // Function to update the costs for all nodes based on conditions
    public static void updateCosts() {
        for (int i = 0; i < conditions.size(); i++) {
            int cost = calculateCost(conditions.get(i));
            nodes.get(i).cost = cost;
            System.out.println("Updated Cost for " + nodes.get(i).name + ": " + nodes.get(i).cost);
        }
    }

    // Function to print the shortest path
    public static void printShortestPath(String start) {
        for (Node node : nodes) {
            if (node.name.equals(start)) {
                System.out.println("Shortest Path from " + start + ": Cost = " + node.cost);
                return;
            }
        }
        System.out.println("Node " + start + " not found!");
    }

    public static void main(String[] args) {
        // Initialize nodes and their costs
        nodes.add(new Node("A", -1));
        nodes.add(new Node("B", 5));
        nodes.add(new Node("C", 2));
        nodes.add(new Node("D", 4));
        nodes.add(new Node("E", 7));
        nodes.add(new Node("F", 9));
        nodes.add(new Node("G", 3));
        nodes.add(new Node("H", 0));
        nodes.add(new Node("I", 0));
        nodes.add(new Node("J", 0));

        // Initialize conditions
        // A: OR(B), AND(C, D)
        Condition condA = new Condition();
        condA.orNodes.add("B");
        condA.andNodes.add("C");
        condA.andNodes.add("D");
        conditions.add(condA);

        // B: OR(E, F)
        Condition condB = new Condition();
        condB.orNodes.add("E");
        condB.orNodes.add("F");
        conditions.add(condB);

        // C: OR(G), AND(H, I)
        Condition condC = new Condition();
        condC.orNodes.add("G");
        condC.andNodes.add("H");
        condC.andNodes.add("I");
        conditions.add(condC);

        // D: OR(J)
        Condition condD = new Condition();
        condD.orNodes.add("J");
        conditions.add(condD);

        // Update costs and print the shortest path
        System.out.println("Updated Costs:");
        updateCosts();
        System.out.println();

        printShortestPath("A");
    }
}