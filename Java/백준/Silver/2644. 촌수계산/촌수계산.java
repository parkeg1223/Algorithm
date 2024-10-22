import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static List<Integer>[] adj;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(in.readLine());
        adj = new ArrayList[n+1];
        visited = new int[n+1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        st = new StringTokenizer(in.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(in.readLine());
        while (m-- > 0) {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adj[x].add(y);
            adj[y].add(x);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(a);
        visited[a] = 1;

        while (!queue.isEmpty()) {
            int p = queue.poll();
            if (p == b) {
                System.out.println(visited[p] - 1);
                return;
            }
            for (int t: adj[p]) {
                if (visited[t] == 0) {
                    visited[t] = visited[p] + 1;
                    queue.offer(t);
                }
            }
        }

        System.out.println(-1);
    }
}