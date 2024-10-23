#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

char board[3][3];
char AI = 'O', human = 'X';

void initializeBoard() {
    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
            board[i][j] = ' ';
}

void printBoard() {
    printf(" %c | %c | %c \n", board[0][0], board[0][1], board[0][2]);
    printf("---|---|---\n");
    printf(" %c | %c | %c \n", board[1][0], board[1][1], board[1][2]);
    printf("---|---|---\n");
    printf(" %c | %c | %c \n", board[2][0], board[2][1], board[2][2]);
}

int checkWinner() {
    for (int i = 0; i < 3; i++) {
        if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') return board[i][0];
        if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') return board[0][i];
    }
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') return board[0][0];
    if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') return board[0][2];
    return 0;
}

int minimax(int depth, int isMaximizing) {
    char winner = checkWinner();
    if (winner == AI) return 10;
    if (winner == human) return -10;

    int movesLeft = 0;
    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
            if (board[i][j] == ' ') movesLeft++;

    if (movesLeft == 0) return 0;

    if (isMaximizing) {
        int best = -1000;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ') {
                    board[i][j] = AI;
                    int score = minimax(depth + 1, 0);
                    board[i][j] = ' ';
                    best = (score > best) ? score : best;
                }
        return best;
    } else {
        int best = 1000;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ') {
                    board[i][j] = human;
                    int score = minimax(depth + 1, 1);
                    board[i][j] = ' ';
                    best = (score < best) ? score : best;
                }
        return best;
    }
}

void bestMove() {
    int bestVal = -1000;
    int moveRow = -1, moveCol = -1;

    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (board[i][j] == ' ') {
                board[i][j] = AI;
                int moveVal = minimax(0, 0);
                board[i][j] = ' ';
                if (moveVal > bestVal) {
                    moveRow = i;
                    moveCol = j;
                    bestVal = moveVal;
                }
            }
        }
    }
    board[moveRow][moveCol] = AI;
}

int main() {
    initializeBoard();
    int turn = 0;
    while (1) {
        printBoard();
        if (turn % 2 == 0) {
            int row, col;
            printf("Enter row and column (1-3): ");
            scanf("%d %d", &row, &col);
            row--; col--;
            if (board[row][col] == ' ') {
                board[row][col] = human;
                turn++;
            } else {
                printf("Invalid move!\n");
            }
        } else {
            bestMove();
            turn++;
        }
        if (checkWinner()) {
            printBoard();
            printf("Game Over\n");
            break;
        }
    }
    return 0;
}
 