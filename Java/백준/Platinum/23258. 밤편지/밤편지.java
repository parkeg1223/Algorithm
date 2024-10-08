import java.io.*;
import java.util.*;

public class Main {

    static int N, Q;
    static final int INF = 0x3f3f3f3f;
    static int[][][] cost;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        cost = new int[N+1][N+1][N+2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 1; j <= N; j++) {
                cost[i][j][1] = Integer.parseInt(st.nextToken());
                if (cost[i][j][1] == 0) cost[i][j][1] = INF;
            }
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (cost[i][j][k] > cost[i][k][k] + cost[k][j][k]) {
                        cost[i][j][k+1] = cost[i][k][k] + cost[k][j][k];
                    } else cost[i][j][k+1] = cost[i][j][k];
                }
            }
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(in.readLine());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (s == e) sb.append(0).append("\n");
            else sb.append(cost[s][e][c] == INF ? -1 : cost[s][e][c]).append("\n");
        }
        System.out.print(sb);
    }
}