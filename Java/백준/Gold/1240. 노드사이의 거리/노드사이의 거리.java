import java.io.*;
import java.util.*;

public class Main {

    static int N, M, dist = Integer.MAX_VALUE;
    static boolean[] visited;
    static List<List<int[]>> adj = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];

        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            adj.get(a).add(new int[] {b, d});
            adj.get(b).add(new int[] {a, d});
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dfs(a, b, 0);
            sb.append(dist).append("\n");
            dist = Integer.MAX_VALUE;
            Arrays.fill(visited, false);
        }

        System.out.print(sb);
    }

    public static void dfs(int start, int end, int currDist) {
        if (start == end) {
            dist = Math.min(dist, currDist);
        }

        visited[start] = true;
        for (int[] v: adj.get(start)) {
            if (!visited[v[0]]) {
                dfs(v[0], end, currDist+v[1]);
            }
        }
    }
}