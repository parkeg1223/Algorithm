import java.io.*;
import java.util.*;

public class Main {

    static int N, M, sX, sY, nPerson;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] field;
    static boolean[][] visited;

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        field = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            field[i] = in.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (field[i][j] == 'I') {
                    sX = i; sY = j;
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {sX, sY});
        visited[sX][sY] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (inRange(nx, ny) && !visited[nx][ny] && field[nx][ny] != 'X') {
                    if (field[nx][ny] == 'P') nPerson++;
                    visited[nx][ny] = true;
                    queue.offer(new int[] {nx, ny});
                }
            }
        }

        System.out.println(nPerson != 0 ? nPerson : "TT");
    }
}