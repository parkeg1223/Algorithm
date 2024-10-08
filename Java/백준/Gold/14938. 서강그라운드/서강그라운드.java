import java.io.*;
import java.util.*;

public class Main {

    static int n, m, r;
    static int[] item;
    static int[][] cost;
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        item = new int[n+1];
        cost = new int[n+1][n+1];

        st = new StringTokenizer(in.readLine());
        for (int i = 1; i <= n; i++) {
            item[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(cost[i], INF);
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            cost[a][b] = Math.min(cost[a][b], l);
            cost[b][a] = Math.min(cost[b][a], l);
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

        int maxItem = 0;
        for (int i = 1; i <= n; i++) {
            int currItem = 0;
            for (int j = 1; j <= n; j++) {
                if (cost[i][j] <= m) currItem += item[j];
            }
            maxItem = Math.max(maxItem, currItem);
        }
        System.out.println(maxItem);
    }
}