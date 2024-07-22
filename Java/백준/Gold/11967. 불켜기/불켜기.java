import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static boolean inRange(int x, int y) {
        return x > 0 && x <= N && y > 0 && y <= N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int answer = 1;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] bright = new boolean[N + 1][N + 1];
        boolean[][] visited = new boolean[N + 1][N + 1];
        ArrayList<int[]>[][] nList = new ArrayList[N + 1][N + 1];
        boolean[][] remains = new boolean[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                nList[i][j] = new ArrayList<>();
            }
        }

        queue.offer(new int[]{1, 1});
        bright[1][1] = true;
        visited[1][1] = true;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            nList[x][y].add(new int[]{a, b});
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int i = 0; i < nList[curr[0]][curr[1]].size(); i++) {
                int[] temp = nList[curr[0]][curr[1]].get(i);
                if (bright[temp[0]][temp[1]]) continue;
                bright[temp[0]][temp[1]] = true;
                answer++;

                boolean possible = false;
                for (int j = 0; j < 4; j++) {
                    int nx = temp[0] + dx[j];
                    int ny = temp[1] + dy[j];
                    if (inRange(nx, ny) && visited[nx][ny]) {
                        possible = true;
                        break;
                    }
                }
                if (!possible) remains[temp[0]][temp[1]] = true;
                else {
                    visited[temp[0]][temp[1]] = true;
                    queue.offer(new int[]{temp[0], temp[1]});
                }
            }

            // remains 처리
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (inRange(nx, ny) && remains[nx][ny] && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        System.out.println(answer);
    }
}