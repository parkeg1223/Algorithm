import java.util.*;
import java.io.*;

public class Main {

    static int N, M, rx, ry, bx, by;
    static boolean rOut, bOut;
    static char[][] field;
    static boolean[][][][] visited;

    public static class Board {
        char[][] b;
        int step;
        int rx, ry, bx, by;

        Board(char[][] b, int step, int rx, int ry, int bx, int by) {
            this.b = b;
            this.step = step;
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(bfs());
    }

    private static int bfs() {

        Queue<Board> queue = new ArrayDeque<>();
        queue.offer(new Board(field, 1, rx, ry, bx, by));
        visited[rx][ry][bx][by] = true;
        while (!queue.isEmpty()) {
            Board b = queue.poll();
            if (b.step > 10) return -1;
            Board temp;
            if ((temp = incline(b)) != null) {
                if (!visited[temp.rx][temp.ry][temp.bx][temp.by]) {
                    visited[temp.rx][temp.ry][temp.bx][temp.by] = true;
                    queue.offer(temp);
                }
            } else if (rOut && !bOut) return b.step;

            if ((temp = incline(rotate(b, 90))) != null) {
                if (!visited[N-1-temp.ry][temp.rx][N-1-temp.by][temp.bx]) {
                    visited[N-1-temp.ry][temp.rx][N-1-temp.by][temp.bx] = true;
                    queue.offer(rotate(temp, 270));
                }
            } else if (rOut && !bOut) return b.step;

            if ((temp = incline(rotate(b, 180))) != null) {
                if (!visited[N-1-temp.rx][M-1-temp.ry][N-1-temp.bx][M-1-temp.by]) {
                    visited[N-1-temp.rx][M-1-temp.ry][N-1-temp.bx][M-1-temp.by] = true;
                    queue.offer(rotate(temp, 180));
                }
            } else if (rOut && !bOut) return b.step;

            if ((temp = incline(rotate(b, 270))) != null) {
                if (!visited[temp.ry][M-1-temp.rx][temp.by][M-1-temp.bx]) {
                    visited[temp.ry][M-1-temp.rx][temp.by][M-1-temp.bx] = true;
                    queue.offer(rotate(temp, 90));
                }
            } else if (rOut && !bOut) return b.step;
        }

        return -1;
    }

    public static Board incline(Board b) {
        char[][] board = b.b;

        int r = board.length;
        int c = board[0].length;
        char[][] newField = new char[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == '#') newField[i][j] = '#';
                else if (board[i][j] == 'O') newField[i][j] = 'O';
                else newField[i][j] = '.';
            }
        }

        rOut = false; bOut = false;
        int rx = 0, ry = 0, bx = 0, by = 0;

        for (int i = 1; i < r-1; i++) {
            int currIdx = c-2;
            boolean isOut = false;
            for (int j = c-2; j >= 1; j--) {
                if (board[i][j] == '#') {
                    currIdx = j-1;
                    if (isOut) isOut = false;
                } else if (board[i][j] == 'O') isOut = true;
                else if (board[i][j] == 'R') {
                    if (isOut) {
                        rOut = true;
                    } else {
                        newField[i][currIdx--] = 'R';
                        rx = i;
                        ry = currIdx+1;
                    }
                } else if (board[i][j] == 'B') {
                    if (isOut) {
                        bOut = true;
                    } else {
                        newField[i][currIdx--] = 'B';
                        bx = i;
                        by = currIdx+1;
                    }
                }
            }
        }

        if (rOut || bOut) return null;
        return new Board(newField, b.step+1, rx, ry, bx, by);
    }

    public static Board rotate(Board b, int angle) {
        char[][] newField = null;
        char[][] board = b.b;
        int r = board.length;
        int c = board[0].length;

        if (angle == 90) {
            newField = new char[c][r];
            for (int i = 0; i < c; i++) {
                for (int j = 0; j < r; j++) {
                    newField[i][j] = board[r-1-j][i];
                }
            }
        } else if (angle == 180) {
            newField = new char[r][c];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    newField[i][j] = board[r-1-i][c-1-j];
                }
            }
        } else if (angle == 270) {
            newField = new char[c][r];
            for (int i = 0; i < c; i++) {
                for (int j = 0; j < r; j++) {
                    newField[i][j] = board[j][c-1-i];
                }
            }
        }

        return new Board(newField, b.step, b.rx, b.ry, b.bx, b.by);
    }


    private static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        field = new char[N][M];
        visited = new boolean[N][M][N][M];

        for (int i = 0; i < N; i++) {
            field[i] = in.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (field[i][j] == 'R') {
                    rx = i; ry = j;
                } else if (field[i][j] == 'B') {
                    bx = i; by = j;
                }
            }
        }
    }
}