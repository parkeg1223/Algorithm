import java.io.*;
import java.util.*;

public class Main {

    static char[][] field;
    static int[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int h, w, maxDoc;

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < h+2 && y >= 0 && y < w+2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(in.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(in.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            field = new char[h+2][w+2];
            visited = new int[h+2][w+2];
            maxDoc = 0;

            for (int i = 0; i < h+2; i++) {
                Arrays.fill(visited[i], -1);
            }

            Queue<int[]> queue = new ArrayDeque<>();
            for (int i = 1; i <= h; i++) {
                String s = in.readLine();
                field[i][0] = field[i][w+1] = '.';
                for (int j = 1; j <= w; j++) {
                    field[i][j] = s.charAt(j-1);
                }
            }
            Arrays.fill(field[0], '.');
            Arrays.fill(field[h+1], '.');

            String k = in.readLine();
            int key = 0;
            for (int i = 0; i < k.length(); i++) {
                key |= (1<<(k.charAt(i)-'a'));
            }

            queue.offer(new int[] {0, 0});
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];
                    if (inRange(nx, ny) && visited[nx][ny] != key && field[nx][ny] != '*') {
                        if (field[nx][ny] == '$') {
                            maxDoc++;
                        } else if (field[nx][ny] >= 'a' && field[nx][ny] <= 'z') {
                            key |= (1<<(field[nx][ny]-'a'));
                        } else if (field[nx][ny] >= 'A' && field[nx][ny] <= 'Z') {
                            if ((key & (1<<(field[nx][ny]-'A'))) == 0) continue;
                        }
                        visited[nx][ny] = key;
                        queue.offer(new int[] {nx, ny});
                        field[nx][ny] = '.';
                    }
                }
            }
            sb.append(maxDoc).append("\n");
        }
        System.out.print(sb);
    }

}