import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] field;
    static boolean[][] visited;
    static List<int[]> cygnus = new ArrayList<>();

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        field = new char[R][C];
        visited = new boolean[R][C];

        Queue<int[]> queue = new ArrayDeque<>();
        Queue<int[]> next = new ArrayDeque<>();
        Queue<int[]> water = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            field[i] = in.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (field[i][j] != 'X') {
                    water.add(new int[] {i, j});
                    if (field[i][j] == 'L') {
                        cygnus.add(new int[] {i, j});
                    }
                }
            }
        }

        // bfs
        queue.offer(cygnus.get(0));
        visited[cygnus.get(0)[0]][cygnus.get(0)[1]] = true;
        for (int d = 0; ; d++) {
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                if (cur[0] == cygnus.get(1)[0] && cur[1] == cygnus.get(1)[1]) {
                    System.out.println(d);
                    return;
                }
                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];
                    if (inRange(nx, ny) && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        if (field[nx][ny] == 'X') {
                            next.offer(new int[] {nx, ny});
                            continue;
                        }

                        queue.offer(new int[] {nx, ny});
                    }
                }
            }
            queue = next;
            next = new ArrayDeque<>();

            int wSize = water.size();
            for (int i = 0; i < wSize; i++) {
                int[] cur = water.poll();
                for (int k = 0; k < 4; k++) {
                    int nx = cur[0] + dx[k];
                    int ny = cur[1] + dy[k];
                    if (inRange(nx, ny) && field[nx][ny] == 'X') {
                        field[nx][ny] = '.';
                        water.offer(new int[] {nx, ny});
                    }
                }
            }
        }
    }
}