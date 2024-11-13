import java.util.*;
import java.io.*;

public class Main {

    static int h, w;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static boolean inRange(int x, int y) {
        return x >= 0 && x < h && y >= 0 && y < w;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(in.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(in.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            char[][] field = new char[h][w];

            for (int i = 0; i < h; i++) {
                field[i] = in.readLine().toCharArray();
            }

            int nHerd = 0;
            boolean[][] visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0;j < w; j++) {
                    if (field[i][j] == '.' || visited[i][j]) continue;

                    nHerd++;
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[] {i, j});
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = cur[0] + dx[k];
                            int ny = cur[1] + dy[k];
                            if (inRange(nx, ny) && field[nx][ny] == '#' && !visited[nx][ny]) {
                                queue.offer(new int[] {nx, ny});
                                visited[nx][ny] = true;
                            }
                        }
                    }
                }
            }

            sb.append(nHerd).append("\n");
        }
        System.out.print(sb);
    }
}