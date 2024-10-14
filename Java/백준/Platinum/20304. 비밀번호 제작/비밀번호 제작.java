import java.io.*;
import java.util.*;

public class Main {
    static int N, M, len;
    static int[] password;
    static int[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        M = Integer.parseInt(in.readLine());
        len = Integer.toString(N, 2).length();
        password = new int[M];
        distance = new int[N+1];
        Arrays.fill(distance, -1);

        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < M; i++) {
            password[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            Queue<Integer> queue = new ArrayDeque<>();
            int p = password[i];
            queue.offer(p);
            distance[p] = 0;

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int j = 0; j < len; j++) {
                    int np = cur ^ (1<<j);
                    if (np > N) continue;
                    if (distance[np] == -1 || distance[np] > distance[cur] + 1) {
                        distance[np] = distance[cur] + 1;
                        queue.offer(np);
                    }
                }
            }
        }

        int maxSec = 0;
        for (int i = 0; i <= N; i++) {
            if (maxSec < distance[i]) maxSec = distance[i];
        }

        System.out.println(maxSec);
    }
}