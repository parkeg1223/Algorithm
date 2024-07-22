import java.util.*;
import java.io.*;

public class Main {

        public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[][] visited = new boolean[2][500001];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(N);
        int time = 0, next = 0;
        visited[0][N] = true;

        while (!queue.isEmpty()) {
            if (K > 500000) {
                System.out.println(-1);
                return;
            }

            next = time % 2;
            if (visited[next][K]) {
                System.out.println(time);
                return;
            }

            K += (++time);

            for (int i = 0, size = queue.size(); i < size; i++) {
                int curr = queue.poll();
                next = time % 2;

                if (curr-1 >= 0 && !visited[next][curr-1]) {
                    visited[next][curr-1] = true;
                    queue.offer(curr-1);
                }

                if (curr+1 <= 500000 && !visited[next][curr+1]) {
                    visited[next][curr+1] = true;
                    queue.offer(curr+1);
                }

                if (curr*2 <= 500000 && !visited[next][curr*2]) {
                    visited[next][curr*2] = true;
                    queue.offer(curr*2);
                }
            }
        }
    }
}