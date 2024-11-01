import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K, X;
    static List<List<Integer>> adj = new ArrayList<>();
    static List<Integer> answer = new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj.get(a).add(b);
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {X, 0});
        visited[X] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[1] > K) break;
            if (cur[1] == K) answer.add(cur[0]);
            for (int v: adj.get(cur[0])) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.offer(new int[] {v, cur[1] + 1});
                }
            }
        }

        if (answer.isEmpty()) {
            System.out.println(-1);
            return;
        }

        Collections.sort(answer);
        for (int v: answer) {
            sb.append(v).append("\n");
        }

        System.out.print(sb);
    }
}