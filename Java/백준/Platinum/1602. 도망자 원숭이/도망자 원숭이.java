import java.io.*;
import java.util.*;

public class Main {

    static int N, M, Q;
    static final int INF = 0x3f3f3f3f;
    static int[][] cost, mm;
    static List<int[]> dog = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        cost = new int[N+1][N+1];
        mm = new int[N+1][N+1];

        st = new StringTokenizer(in.readLine());
        for (int i = 1; i <= N; i++) {
            int[] d = new int[] {i, Integer.parseInt(st.nextToken())};
            Arrays.fill(cost[i], INF);
            mm[i][i] = d[1];
            dog.add(d);
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (cost[a][b] > d) {
                cost[a][b] = d;
                mm[a][b] = Math.max(dog.get(a-1)[1], dog.get(b-1)[1]);
            }
            if (cost[b][a] > d) {
                cost[b][a] = d;
                mm[b][a] = Math.max(dog.get(a-1)[1], dog.get(b-1)[1]);
            }
        }

        dog.sort(Comparator.comparingInt(o -> o[1]));

        for (int l = 0; l < N; l++) {
            int k = dog.get(l)[0];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (cost[i][j] + mm[i][j] > cost[i][k] + cost[k][j] + Math.max(mm[i][k], mm[k][j])) {
                        cost[i][j] = cost[i][k] + cost[k][j];
                        mm[i][j] = Math.max(mm[i][k], mm[k][j]);
                    }
                }
            }
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            sb.append(cost[s][t] == INF ? -1 : cost[s][t] + mm[s][t]).append("\n");
        }
        System.out.print(sb);
    }
}