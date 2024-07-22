import java.util.*;
import java.io.*;

public class Main {

    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
    static int N;

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(in.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(in.readLine());
            st = new StringTokenizer(in.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(in.readLine());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            Queue<int[]> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[N][N];
            visited[sx][sy] = true;
            queue.offer(new int[] {sx, sy, 0});
            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                if (curr[0] == ex && curr[1] == ey) {
                    sb.append(curr[2]).append('\n');
                    break;
                }
                for (int i = 0; i < 8; i++) {
                    int nx = curr[0] + dx[i];
                    int ny = curr[1] + dy[i];
                    if (inRange(nx, ny) && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.offer(new int[] {nx, ny, curr[2] + 1});
                    }
                }
            }
        }

        System.out.println(sb);
    }
}