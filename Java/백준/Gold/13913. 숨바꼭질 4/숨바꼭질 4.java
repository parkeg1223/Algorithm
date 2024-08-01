import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] prev = new int[100_001];
        Arrays.fill(prev, -1);
        boolean[] visited = new boolean[100_001];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{N, 0});
        visited[N] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == K) {
                sb.append(curr[1]).append("\n");
                int[] step = new int[curr[1] + 1];
                int pr = K;
                for (int i = curr[1]; i >= 0; i--) {
                    step[i] = pr;
                    pr = prev[pr];
                }
                for (int i = 0; i <= curr[1]; i++) {
                    sb.append(step[i]).append(' ');
                }
                System.out.println(sb);
                return;
            }

            int n = curr[0] - 1;
            if (n >= 0 && n <= 100000 && !visited[n]) {
                visited[n] = true;
                prev[n] = curr[0];
                queue.offer(new int[] {n, curr[1] + 1});
            }
            n = curr[0] + 1;
            if (n >= 0 && n <= 100000 && !visited[n]) {
                visited[n] = true;
                prev[n] = curr[0];
                queue.offer(new int[] {n, curr[1] + 1});
            }
            n = curr[0] * 2;
            if (n >= 0 && n <= 100000 && !visited[n]) {
                visited[n] = true;
                prev[n] = curr[0];
                queue.offer(new int[] {n, curr[1] + 1});
            }
        }
    }
}