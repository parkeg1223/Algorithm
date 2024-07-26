import java.util.*;
import java.io.*;

public class Main {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int R, C;
    static int[][] field;
    static boolean[][] visited;
    static Queue<int[]> fire;
    static Queue<int[]> jh;

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        field = new int[R][C];
        visited = new boolean[R][C];
        fire = new ArrayDeque<>();
        jh = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            String s = in.readLine();
            for (int j = 0; j < C; j++) {
                char c = s.charAt(j);
                if (c == '#') field[i][j] = -1;
                else {
                    field[i][j] = 0;
                    if (c == 'F') {
                        fire.offer(new int[] {i, j, 0});
                        visited[i][j] = true;
                    } else if (c == 'J') {
                        jh.offer(new int[] {i, j, 0});
                    }
                }
            }
        }

        makeFireRoute();
        resetVisited();
        int answer = makeSGRoute();
        System.out.println(answer == -1 ? "IMPOSSIBLE" : answer);
    }

    private static void makeFireRoute() {
        while (!fire.isEmpty()) {
            int[] curr = fire.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (inRange(nx, ny) && field[nx][ny] != -1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    field[nx][ny] = field[curr[0]][curr[1]] + 1;
                    fire.offer(new int[] {nx, ny, curr[2] + 1});
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (field[i][j] == 0 && !visited[i][j]) {
                    field[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }

    private static int makeSGRoute() {
        if (!jh.isEmpty()) visited[jh.peek()[0]][jh.peek()[1]] = true;
        while (!jh.isEmpty()) {
            int[] curr = jh.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (!inRange(nx, ny)) {
                    return curr[2] + 1;
                }
                if (field[nx][ny] != -1 && field[nx][ny] > curr[2]+1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    jh.offer(new int[] {nx, ny, curr[2] + 1});
                }
            }
        }

        return -1;
    }

    private static void resetVisited() {
        for (int i = 0; i < R; i++) {
            Arrays.fill(visited[i], false);
        }
    }
}