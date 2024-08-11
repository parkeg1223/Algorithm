import java.util.*;
import java.io.*;

public class Main {

    static int N, M, H, nLad = Integer.MAX_VALUE;
    static boolean possible;
    static boolean[][][] ladder;

    public static void main(String[] args) throws IOException {
        input();
        for (int i = 0; i <= 3; i++) {
            dfs(1, 1, 0, i);
            if (possible) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }

    private static void dfs(int sx, int sy, int step, int maxLad) {

        if (isComplete()) {
            possible = true;
            nLad = Math.min(nLad, step);
            return;
        }

        if (step >= maxLad) return;

        for (int i = sx; i <= H; i++) {
            for (int j = sy; j < N; j++) {

                if (ladder[j][j+1][i]) continue;
                if (j > 1 && ladder[j-1][j][i]) continue;
                if (j < N-1 && ladder[j+1][j+2][i]) continue;


                ladder[j][j+1][i] = true;
                ladder[j+1][j][i] = true;
                if (sy == N-1) {
                    dfs(sx+1, 1, step+1, maxLad);
                } else {
                    dfs(sx, sy+1, step+1, maxLad);
                }
                ladder[j][j+1][i] = false;
                ladder[j+1][j][i] = false;
            }
        }

    }

    private static boolean isComplete() {
        for (int i = 1; i <= N; i++) {
            int currIdx = i;
            for (int j = 1; j <= H; j++) {
                if (currIdx < N && ladder[currIdx][currIdx+1][j]) {
                    currIdx++;
                } else if (currIdx > 1 && ladder[currIdx][currIdx-1][j]) {
                    currIdx--;
                }
            }
            if (currIdx != i) return false;
        }

        return true;
    }

    private static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new boolean[N+1][N+1][H+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[b][b+1][a] = true;
            ladder[b+1][b][a] = true;
        }
    }
}