import java.util.*;
import java.io.*;

public class Main {

    static int N, M, K;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] field;
    static boolean[][][] visited;

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        field = new char[N][M];
        visited = new boolean[N][M][K+1];

        for (int i = 0; i < N; i++) {
            field[i] = in.readLine().toCharArray();
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {0, 0, 1, 0, K});
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == N-1 && curr[1] == M-1) {
                System.out.println(curr[2]);
                return;
            }

            if (curr[3] == 1) {
                visited[curr[0]][curr[1]][curr[4]] = true;
                queue.offer(new int[] {curr[0], curr[1], curr[2]+1, 0, curr[4]});
            }

            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (inRange(nx, ny)) {
                    if (field[nx][ny] == '1' && curr[3] == 0) {
                        if (curr[4] > 0 && !visited[nx][ny][curr[4]-1]) {
                            visited[nx][ny][curr[4]-1] = true;
                            queue.offer(new int[] {nx, ny, curr[2]+1, 1, curr[4]-1});
                        }
                    } else if (field[nx][ny] == '0') {
                        if (!visited[nx][ny][curr[4]]) {
                            visited[nx][ny][curr[4]] = true;
                            queue.offer(new int[] {nx, ny, curr[2]+1, (curr[3]+1)%2, curr[4]});
                        }
                    }
                }
            }
        }

        System.out.println(-1);
    }
}