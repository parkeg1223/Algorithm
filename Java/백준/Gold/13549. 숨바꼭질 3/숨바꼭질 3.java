import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[100001];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {N, 0});
        visited[N] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == K) {
                System.out.println(curr[1]);
                return;
            }

            int n = curr[0] * 2;
            if (n >= 0 && n <= 100000 && !visited[n]) {
                visited[n] = true;
                queue.offer(new int[] {n, curr[1]});
            }
            n = curr[0] - 1;
            if (n >= 0 && n <= 100000 && !visited[n]) {
                visited[n] = true;
                queue.offer(new int[] {n, curr[1] + 1});
            }
            n = curr[0] + 1;
            if (n >= 0 && n <= 100000 && !visited[n]) {
                visited[n] = true;
                queue.offer(new int[] {n, curr[1] + 1});
            }
        }
    }

}
