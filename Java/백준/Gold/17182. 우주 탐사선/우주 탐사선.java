import java.util.*;
import java.io.*;

public class Main {

    static int N, K, minTime = Integer.MAX_VALUE;
    static boolean[] visited;
    static int[][] cost;
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        cost = new int[N][N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                if (cost[i][j] == 0) cost[i][j] = INF;
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (cost[i][j] > cost[i][k] + cost[k][j])
                        cost[i][j] = cost[i][k] + cost[k][j];
                }
            }
        }
        
        visited[K] = true;
        dfs(K, 1, 0);
        System.out.println(minTime);
    }

    public static void dfs(int curr, int step, int c) {
        if (step == N) {
            minTime = Math.min(minTime, c);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, step+1, c+cost[curr][i]);
                visited[i] = false;
            }
        }
    }
}