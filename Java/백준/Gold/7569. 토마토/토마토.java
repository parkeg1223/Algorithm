import java.util.*;
import java.io.*;

public class Main {

    static int[] dx = {-1, 0, 1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static int N, M, H, nUnripeTomatoes;
    static int[][][] tomatoes;
    static boolean[][][] visited;
    static Queue<int[]> queue = new ArrayDeque<>();

    private static boolean inRange(int x, int y, int z) {
        return x >= 0 && x < N && y >= 0 && y < M && z >= 0 && z < H;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        tomatoes = new int[H][N][M];
        visited = new boolean[H][N][M];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(in.readLine());
                for (int k = 0; k < M; k++) {
                    tomatoes[i][j][k] = Integer.parseInt(st.nextToken());
                    if (tomatoes[i][j][k] == 1) {
                        visited[i][j][k] = true;
                        queue.offer(new int[] {i, j, k, 0});
                    }
                }
            }
        }

        ripe();
    }

    private static void ripe() {
        int maxDay = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            maxDay = Math.max(maxDay, curr[3]);
            for (int l = 0; l < 6; l++) {
                int nx = curr[1] + dx[l];
                int ny = curr[2] + dy[l];
                int nz = curr[0] + dz[l];
                if (inRange(nx, ny, nz) && tomatoes[nz][nx][ny] == 0 && !visited[nz][nx][ny]) {
                    visited[nz][nx][ny] = true;
                    queue.offer(new int[] {nz, nx, ny, curr[3]+1});
                }
            }
        }

        if (!getNumOfUnripeTomatoes()) {
            System.out.println(-1);
        } else {
            System.out.println(maxDay);
        }
    }

    private static boolean getNumOfUnripeTomatoes() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (!visited[i][j][k] && tomatoes[i][j][k] == 0) return false;
                }
            }
        }

        return true;
    }
}