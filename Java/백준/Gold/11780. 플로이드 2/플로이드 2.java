import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] cost, next;
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n = Integer.parseInt(in.readLine());
        m = Integer.parseInt(in.readLine());
        cost = new int[n+1][n+1];
        next = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(cost[i], INF);
        }

        for (int idx = 0; idx < m; idx++) {
            st = new StringTokenizer(in.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (cost[i][j] > c) {
                cost[i][j] = c;
                next[i][j] = j;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (cost[j][k] > cost[j][i] + cost[i][k]) {
                        cost[j][k] = cost[j][i] + cost[i][k];
                        next[j][k] = next[j][i];
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            cost[i][i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(cost[i][j] != INF ? cost[i][j] : 0).append(" ");
            }
            sb.append("\n");
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (cost[i][j] == 0 || cost[i][j] == INF) sb.append(0).append("\n");
                else {
                    int src = i;
                    List<Integer> path = new ArrayList<>();
                    while (src != j) {
                        path.add(src);
                        src = next[src][j];
                    }
                    path.add(j);
                    sb.append(path.size()).append(" ");
                    for (int city: path) {
                        sb.append(city).append(" ");
                    }
                    sb.append("\n");
                }
            }
        }
        System.out.print(sb);
    }
}