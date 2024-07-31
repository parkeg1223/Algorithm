import java.util.*;
import java.io.*;

public class Main {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M, K;
    static boolean[][] field;
    static boolean[][] visited;
    static List<Integer> wList = new ArrayList<>();

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        field = new boolean[M][N];
        visited = new boolean[M][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(in.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int y = y1; y < y2; y++) {
                for (int x = x1; x < x2; x++) {
                    field[y][x] = true;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                bfs(i, j);
            }
        }

        Collections.sort(wList);
        sb.append(wList.size()).append('\n');
        for (int width: wList) {
            sb.append(width).append(" ");
        }

        System.out.println(sb);
    }

    public static void bfs(int x, int y) {
        if (field[x][y] || visited[x][y]) return;

        int currWidth = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {x, y});
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            currWidth++;
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (inRange(nx, ny) && !field[nx][ny] && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new int[] {nx, ny});
                }
            }
        }

        wList.add(currWidth);
    }

}