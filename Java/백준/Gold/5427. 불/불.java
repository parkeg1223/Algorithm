import java.util.*;
import java.io.*;

public class Main {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int w, h;
    static int[][] field;
    static boolean[][] visited;
    static Queue<int[]> fire;
    static Queue<int[]> sg;

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < h && y >= 0 && y < w;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(in.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            field = new int[h][w];
            visited = new boolean[h][w];
            fire = new ArrayDeque<>();
            sg = new ArrayDeque<>();

            for (int i = 0; i < h; i++) {
                String s = in.readLine();
                for (int j = 0; j < w; j++) {
                    char c = s.charAt(j);
                    if (c == '#') field[i][j] = -1;
                    else {
                        field[i][j] = 0;
                        if (c == '*') {
                            fire.offer(new int[] {i, j, 0});
                            visited[i][j] = true;
                        } else if (c == '@') {
                            sg.offer(new int[] {i, j, 0});
                        }
                    }
                }
            }

            makeFireRoute();
            resetVisited();
            int answer = makeSGRoute();
            sb.append(answer == -1 ? "IMPOSSIBLE" : answer).append('\n');
        }

        System.out.print(sb);
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

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (field[i][j] == 0 && !visited[i][j]) {
                    field[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }

    private static int makeSGRoute() {
        if (!sg.isEmpty()) visited[sg.peek()[0]][sg.peek()[1]] = true;
        while (!sg.isEmpty()) {
            int[] curr = sg.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (!inRange(nx, ny)) {
                    return curr[2] + 1;
                }
                if (field[nx][ny] != -1 && field[nx][ny] > curr[2]+1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    sg.offer(new int[] {nx, ny, curr[2] + 1});
                }
            }
        }

        return -1;
    }

    private static void resetVisited() {
        for (int i = 0; i < h; i++) {
            Arrays.fill(visited[i], false);
        }
    }
}
