import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] field;
    static boolean[][] visited;

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        field = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < m; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] answer = bfs();
        System.out.println(answer[0] + "\n" + answer[1]);
    }

    public static int[] bfs() {
        int nDrawing = 0, maxWidth = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (field[i][j] == 0 || visited[i][j]) continue;
                visited[i][j] = true;
                nDrawing++;
                int currWidth = 0;
                Queue<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[] {i, j});
                while (!queue.isEmpty()) {
                    int[] curr = queue.poll();
                    currWidth++;
                    for (int k = 0; k < 4; k++) {
                        int nx = curr[0] + dx[k], ny = curr[1] + dy[k];
                        if (inRange(nx, ny) && field[nx][ny] == 1 && !visited[nx][ny]) {
                            queue.offer(new int[] {nx, ny});
                            visited[nx][ny] = true;
                        }
                    }
                }
                maxWidth = Math.max(maxWidth, currWidth);
            }
        }
        return new int[] {nDrawing, maxWidth};
    }
}