import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static List<Integer>[] adj;
    static int[] inDeg, delay, time;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            adj = new ArrayList[N+1];
            inDeg = new int[N+1];
            delay = new int[N+1];
            time = new int[N+1];

            st = new StringTokenizer(in.readLine());
            for (int i = 1; i <= N; i++) {
                adj[i] = new ArrayList<>();
                delay[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adj[u].add(v);
                inDeg[v]++;
            }

            int finalBuilding = Integer.parseInt(in.readLine());
            Queue<Integer> queue = new ArrayDeque<>();
            for (int i = 1; i <= N; i++) {
                if (inDeg[i] == 0) {
                    queue.offer(i);
                    time[i] = delay[i];
                }
            }

            while (!queue.isEmpty()) {
                int building = queue.poll();
                if (building == finalBuilding) {
                    sb.append(time[building]).append("\n");
                }
                for (int next: adj[building]) {
                    time[next] = Math.max(time[next], time[building] + delay[next]);
                    if (--inDeg[next] == 0) queue.offer(next);
                }
            }
        }
        System.out.print(sb);
    }
}