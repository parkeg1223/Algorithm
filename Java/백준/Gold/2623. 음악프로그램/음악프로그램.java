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
            int nSinger = Integer.parseInt(st.nextToken());
            int prev = 0, next = 0;
            for (int j = 0; j < nSinger-1; j++) {
                if (prev == 0) prev = Integer.parseInt(st.nextToken());
                next = Integer.parseInt(st.nextToken());
                adj[prev].add(next);
                inDeg[next]++;
                prev = next;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (inDeg[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int singer = queue.poll();
            sb.append(singer).append("\n");
            for (int next: adj[singer]) {
                if (--inDeg[next] == 0) queue.offer(next);
            }
        }

        for (int i = 0; i < N; i++) {
            if (inDeg[i] != 0) {
                System.out.println(0);
                return;
            }
        }
        System.out.print(sb);
    }
}