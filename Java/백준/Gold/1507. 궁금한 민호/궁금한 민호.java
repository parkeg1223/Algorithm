import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] cost, directCost;
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        cost = new int[N+1][N+1];
        directCost = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 1; j <= N; j++) {
                directCost[i][j] = cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j || i == k || j == k) continue;
                    if (cost[i][j] == cost[i][k] + cost[k][j]) {
                        directCost[i][j] = 0;
                    } else if (cost[i][j] > cost[i][k] + cost[k][j]) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        int totalCost = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = i+1; j <= N; j++) {
                totalCost += directCost[i][j];
            }
        }

        System.out.println(totalCost);
    }
}