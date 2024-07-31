import java.util.*;
import java.io.*;

public class Main {

    static int F, S, G, U, D;
    static boolean[] visited;

    private static boolean inRange(int h) {
        return h > 0 && h <= F;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        visited = new boolean[F+1];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {S, 0});
        visited[S] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == G) {
                System.out.println(curr[1]);
                return;
            }
            int nh = curr[0] + U;
            if (inRange(nh) && !visited[nh]) {
                visited[nh] = true;
                queue.offer(new int[] {nh, curr[1] + 1});
            }
            nh = curr[0] - D;
            if (inRange(nh) && !visited[nh]) {
                visited[nh] = true;
                queue.offer(new int[] {nh, curr[1] + 1});
            }
        }

        System.out.println("use the stairs");
    }

}