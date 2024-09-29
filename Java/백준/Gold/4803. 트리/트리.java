import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static ArrayList<Integer>[] trees;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int tc = 1; ; tc++) {
            st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) break;

            trees = new ArrayList[n];
            visited = new boolean[n];
            int nTree = 0;
            for (int i = 0; i < n; i++) {
                trees[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(in.readLine());
                int v1 = Integer.parseInt(st.nextToken()) - 1;
                int v2 = Integer.parseInt(st.nextToken()) - 1;
                trees[v1].add(v2);
                trees[v2].add(v1);
            }

            for (int i = 0; i < n; i++) {
                if (visited[i]) continue;
                visited[i] = true;
                if (dfs(-1, i)) nTree++;
            }

            sb.append("Case ").append(tc).append(": ");
            if (nTree == 0) {
                sb.append("No trees.\n");
            } else if (nTree == 1) {
                sb.append("There is one tree.\n");
            } else {
                sb.append("A forest of ").append(nTree).append(" trees.\n");
            }
        }
        System.out.print(sb);
    }

    public static boolean dfs(int start, int next) {
        for (int v: trees[next]) {
            if (v == start) continue;
            if (visited[v]) return false;
            visited[v] = true;
            if (!dfs(next, v)) return false;
        }
        return true;
    }
}