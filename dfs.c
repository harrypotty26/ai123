#include <stdio.h>
#include <stdlib.h>

#define MAX_VERTICES 100

void dfs(int graph[MAX_VERTICES][MAX_VERTICES], int numVertices, int vertex, int visited[MAX_VERTICES]) {
    printf("%d ", vertex);
    visited[vertex] = 1;

    for (int i = 0; i < numVertices; i++) {
        if (graph[vertex][i] && !visited[i]) {
            dfs(graph, numVertices, i, visited);
        }
    }
}

int main() {
    int numVertices = 6;
    int graph[MAX_VERTICES][MAX_VERTICES] = {
        {0, 1, 1, 0, 0, 0},
        {1, 0, 0, 1, 1, 0},
        {1, 0, 0, 0, 0, 1},
        {0, 1, 0, 0, 0, 0},
        {0, 1, 0, 0, 0, 1},
        {0, 0, 1, 0, 1, 0}
    };

    int visited[MAX_VERTICES] = {0};

    printf("DFS starting from vertex 0:\n");
    dfs(graph, numVertices, 0, visited);
    printf("\n");

    return 0;
}