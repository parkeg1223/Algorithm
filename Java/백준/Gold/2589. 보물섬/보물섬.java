import java.io.*;
import java.util.*;

public class Main {

    static int n, m, maxDist;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] field;

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        field = new char[n][m];

        for (int i = 0; i < n; i++) {
            field[i] = in.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (field[i][j] == 'L') {
                    boolean[][] visited = new boolean[n][m];
                    Queue<int[]> queue = new ArrayDeque<>();
                    visited[i][j] = true;
                    queue.offer(new int[] {i, j, 0});

                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        maxDist = Math.max(cur[2], maxDist);
                        for (int k = 0; k < 4; k++) {
                            int nx = cur[0] + dx[k];
                            int ny = cur[1] + dy[k];
                            if (inRange(nx, ny) && !visited[nx][ny] && field[nx][ny] == 'L') {
                                visited[nx][ny] = true;
                                queue.offer(new int[] {nx, ny, cur[2]+1});
                            }
                        }
                    }
                }
            }
        }
        System.out.println(maxDist);
    }
}