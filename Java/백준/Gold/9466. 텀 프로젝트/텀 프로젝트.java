import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(in.readLine());
            int answer = 0;
            int[] choice = new int[n+1];
            boolean[] visited = new boolean[n+1];
            Queue<Integer> queue = new ArrayDeque<>();

            st = new StringTokenizer(in.readLine());

            for (int i = 1; i <= n; i++) {
                choice[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (visited[i]) continue;
                int num = i;
                while (!visited[num]) {
                    visited[num] = true;
                    queue.add(num);
                    num = choice[num];
                }

                while (!queue.isEmpty() && queue.peek() != num) {
                    queue.poll();
                    answer++;
                }
                queue.clear();
            }
            sb.append(answer).append('\n');
        }

        System.out.println(sb);
    }
}