import java.util.*;
import java.io.*;

public class Main {

    static char[][] field = new char[12][6];
    static boolean[][] visited = new boolean[12][6];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < 12 && y >= 0 && y < 6;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 12; i++) {
            field[i] = in.readLine().toCharArray();
        }

        int answer = 0;

        while (true) {
            boolean exploded = false;
            visited = new boolean[12][6];

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (visited[i][j] || field[i][j] == '.') continue;
                    if (bfs(i, j)) exploded = true;
                }
            }

            if (!exploded) {
                System.out.println(answer);
                return;
            }

            answer++;
            fall();
        }

    }

    private static boolean bfs(int x, int y) {
        List<int[]> pos = new ArrayList<>();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {x, y});
        char color = field[x][y];
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            pos.add(curr);
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (inRange(nx, ny) && !visited[nx][ny] && field[nx][ny] == color) {
                    queue.offer(new int[] {nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

        if (pos.size() < 4) return false;

        for (int[] p: pos) {
            field[p[0]][p[1]] = '.';
        }

        return true;
    }

    private static void fall() {
        char[][] newField = new char[12][6];
        for (int i = 0; i < 12; i++) {
            Arrays.fill(newField[i], '.');
        }

        for (int j = 0; j < 6; j++) {
            int currIdx = 11;
            for (int i = 11; i >= 0; i--) {
                if (field[i][j] != '.') {
                    newField[currIdx--][j] = field[i][j];
                }
            }
        }

        for (int i = 0; i < 12; i++) {
            field[i] = Arrays.copyOf(newField[i], 6);
        }
    }
}