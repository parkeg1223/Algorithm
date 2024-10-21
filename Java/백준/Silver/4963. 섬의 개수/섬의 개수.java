import java.io.*;
import java.util.*;

public class Main {

    static int w, h;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[][] field;
    static boolean[][] visited;

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < h && y >= 0 && y < w;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(in.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0) break;

            int nIsland = 0;
            field = new int[h][w];
            visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < w; j++) {
                    field[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (field[i][j] == 0 || visited[i][j]) continue;

                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[] {i, j});
                    visited[i][j] = true;
                    nIsland++;

                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        for (int k = 0; k < 8; k++) {
                            int nx = cur[0] + dx[k];
                            int ny = cur[1] + dy[k];
                            if (inRange(nx, ny) && field[nx][ny] == 1 && !visited[nx][ny]) {
                                visited[nx][ny] = true;
                                queue.offer(new int[] {nx, ny});
                            }
                        }
                    }
                }
            }
            sb.append(nIsland).append("\n");
        }
        System.out.print(sb);
    }
}