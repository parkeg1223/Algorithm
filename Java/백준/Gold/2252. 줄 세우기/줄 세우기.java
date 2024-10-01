import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static List<Integer>[] adj;
    static int[] inDeg;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N+1];
        inDeg = new int[N+1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            inDeg[v]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (inDeg[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            sb.append(u).append(" ");
            for (int v: adj[u]) {
                if (--inDeg[v] == 0) queue.offer(v);
            }
        }

        System.out.println(sb);
    }
}