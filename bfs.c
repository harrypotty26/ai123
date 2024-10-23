#include <stdio.h>
#include <stdlib.h>

#define MAX_VERTICES 100

typedef struct Node {
    int vertex;
    struct Node* next;
} Node;

typedef struct Queue {
    Node* front;
    Node* rear;
} Queue;

void enqueue(Queue* q, int vertex) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->vertex = vertex;
    newNode->next = NULL;
    if (q->rear) {
        q->rear->next = newNode;
    } else {
        q->front = newNode;
    }
    q->rear = newNode;
}

int dequeue(Queue* q) {
    if (!q->front) return -1;
    Node* temp = q->front;
    int vertex = temp->vertex;
    q->front = q->front->next;
    if (!q->front) {
        q->rear = NULL;
    }
    free(temp);
    return vertex;
}

int isEmpty(Queue* q) {
    return q->front == NULL;
}

void bfs(int graph[MAX_VERTICES][MAX_VERTICES], int numVertices, int start) {
    int visited[MAX_VERTICES] = {0};
    Queue q = {NULL, NULL};

    enqueue(&q, start);
    visited[start] = 1;

    while (!isEmpty(&q)) {
        int vertex = dequeue(&q);
        printf("%d ", vertex);

        for (int i = 0; i < numVertices; i++) {
            if (graph[vertex][i] && !visited[i]) {
                enqueue(&q, i);
                visited[i] = 1;
            }
        }
    }
}

int main() {
    int numVertices = 6;
    int graph[MAX_VERTICES][MAX_VERTICES] = {
        {0, 1, 1, 0, 0, 1},
        {1, 0, 0, 1, 1, 0},
        {1, 0, 0, 0, 0, 1},
        {0, 1, 0, 0, 1, 0},
        {0, 1, 0, 0, 0, 1},
        {0, 0, 1, 0, 1, 0}
    };

    printf("BFS starting from vertex 0:\n");
    bfs(graph, numVertices, 0);
    printf("\n");

    return 0;
}