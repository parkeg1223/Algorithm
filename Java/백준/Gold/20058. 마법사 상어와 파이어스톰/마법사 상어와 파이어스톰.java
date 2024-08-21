import java.util.*;
import java.io.*;

public class Main {

    static int N, Q;
    static int[][] field;
    static int[] lArr;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        input();

        for (int i = 0; i < Q; i++) {
            rotate(lArr[i]);
            melt();
        }

        System.out.println(getTotalIce());
        System.out.println(getBiggestIceChunk());
    }

    public static void rotate(int l) {
        int[][] newField = new int[N][N];

        int n = (int)Math.pow(2, l);
        for (int i = 0; i < N; i+=n) {
            for (int j = 0; j < N; j+=n) {
                for (int k = 0; k < n; k++) {
                    for (int m = 0; m < n; m++) {
                        newField[i+k][j+m] = field[i+n-m-1][j+k];
                    }
                }
            }
        }

        field = newField;
    }

    public static void melt() {
        int[][] newField = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (field[i][j] == 0) continue;
                int touched = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (inRange(nx, ny) && field[nx][ny] > 0) touched++;
                }
                if (touched < 3) newField[i][j] = field[i][j] - 1;
                else newField[i][j] = field[i][j];
            }
        }

        field = newField;
    }

    public static int getTotalIce() {
        int totalIce = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                totalIce += field[i][j];
            }
        }

        return totalIce;
    }

    public static int getBiggestIceChunk() {
        int biggestIceChunk = 0;
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] || field[i][j] == 0) continue;

                int currSize = 0;
                visited[i][j] = true;
                queue.offer(new int[] {i, j});

                while (!queue.isEmpty()) {
                    int[] curr = queue.poll();
                    currSize++;
                    for (int k = 0; k < 4; k++) {
                        int nx = curr[0] + dx[k];
                        int ny = curr[1] + dy[k];
                        if (inRange(nx, ny) && !visited[nx][ny] && field[nx][ny] > 0) {
                            visited[nx][ny] = true;
                            queue.offer(new int[] {nx, ny});
                        }
                    }
                }

                biggestIceChunk = Math.max(biggestIceChunk, currSize);
            }
        }

        return biggestIceChunk;
    }

    public static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = (int)Math.pow(2, Integer.parseInt(st.nextToken()));
        Q = Integer.parseInt(st.nextToken());

        field = new int[N][N];
        lArr = new int[Q];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < Q; i++) {
            lArr[i] = Integer.parseInt(st.nextToken());
        }
    }
}