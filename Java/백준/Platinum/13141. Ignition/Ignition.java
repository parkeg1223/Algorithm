import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static double minTime = Integer.MAX_VALUE;
    static int[][] cost;
    static int[][] complete;
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cost = new int[N+1][N+1];
        complete = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(cost[i], INF);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            if (complete[s][e] < l) complete[s][e] = l;
            if (complete[e][s] < l) complete[e][s] = l;
            if (cost[s][e] > l) cost[s][e] = l;
            if (cost[e][s] > l) cost[e][s] = l;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (cost[i][j] > cost[i][k] + cost[k][j])
                        cost[i][j] = cost[i][k] + cost[k][j];
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            cost[i][i] = 0;
        }

        for (int i = 1; i <= N; i++) {
            double currTime = 0;
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    currTime = Math.max(currTime, (cost[i][j] + cost[i][k] + complete[j][k])/2.0);
                }
            }
            minTime = Math.min(minTime, currTime);
        }
        System.out.printf("%.1f", minTime);
    }
}