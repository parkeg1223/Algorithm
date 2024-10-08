import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] cost, next;
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cost = new int[n+1][n+1];
        next = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(cost[i], INF);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            cost[a][b] = Math.min(cost[a][b], l);
            next[a][b] = b;
            cost[b][a] = Math.min(cost[b][a], l);
            next[b][a] = a;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (cost[i][j] > cost[i][k] + cost[k][j]) {
                        cost[i][j] = cost[i][k] + cost[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            cost[i][i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (cost[i][j] == 0 || cost[i][j] == INF) sb.append("- ");
                else sb.append(next[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}