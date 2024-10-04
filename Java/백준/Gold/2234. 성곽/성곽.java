import java.io.*;
import java.util.*;

public class Main {

    static int N, M, nGroup = 1, maxWidth, maxWidth2;
    static int[][] field;
    static int[][] visited;
    static List<Integer> width = new ArrayList<>();
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static List<Set<Integer>> adj = new ArrayList<>();

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        field = new int[M][N];
        visited = new int[M][N];
        adj.add(new HashSet<>());
        width.add(0);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        for (int i = 1; i < nGroup; i++) {
            for (int group: adj.get(i)) {
                maxWidth2 = Math.max(maxWidth2, width.get(i) + width.get(group));
            }
        }
        System.out.println(nGroup-1 + "\n" + maxWidth + "\n" + maxWidth2);

    }

    public static void bfs() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == 0) {
                    int w = 0;
                    adj.add(new HashSet<>());
                    visited[i][j] = nGroup;
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[] {i, j});
                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        w++;
                        for (int k = 0; k < 4; k++) {
                            int nx = cur[0] + dx[k];
                            int ny = cur[1] + dy[k];
                            if (inRange(nx, ny)) {
                                if (((field[cur[0]][cur[1]] & (1<<k)) == 0) && visited[nx][ny] == 0) {
                                    visited[nx][ny] = nGroup;
                                    queue.offer(new int[]{nx, ny});
                                } else if (visited[nx][ny] > 0 && visited[nx][ny] != nGroup) {
                                    adj.get(nGroup).add(visited[nx][ny]);
                                }
                            }
                        }
                    }
                    nGroup++;
                    width.add(w);
                    maxWidth = Math.max(maxWidth, w);
                }
            }
        }
    }
}