import java.io.*;
import java.util.*;

public class Main {

    static int n, m, k;
    static final int INF = 0x3f3f3f3f;
    static int[][] cost;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cost = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(cost[i], INF);
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            cost[u][v] = 0;
            cost[v][u] = 1-b;
        }


        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (cost[i][j] > cost[i][k] + cost[k][j]) {
                        cost[i][j] = cost[i][k] + cost[k][j];
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            cost[i][i] = 0;
        }

        k = Integer.parseInt(in.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(cost[s][e]).append("\n");
        }
        System.out.print(sb);
    }
}