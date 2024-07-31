import java.util.*;
import java.io.*;

public class Main {

    static int L, R, C;
    static int[] s = new int[3];
    static int[] e = new int[3];
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static int[] dx = {-1, 0, 1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static char[][][] field;
    static boolean[][][] visited;

    private static boolean inRange(int x, int y, int z) {
        return x >= 0 && x < R && y >= 0 && y < C && z >= 0 && z < L;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        while (true) {
            st = new StringTokenizer(in.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if (L == 0 && R == 0 && C == 0) return;

            field = new char[L][R][C];
            visited = new boolean[L][R][C];

            for (int k = 0; k < L; k++) {
                for (int i = 0; i < R; i++) {
                    field[k][i] = in.readLine().toCharArray();
                    for (int j = 0; j < C; j++) {
                        if (field[k][i][j] == 'S') {
                            s[0] = k; s[1] = i; s[2] = j;
                        } else if (field[k][i][j] == 'E') {
                            e[0] = k; e[1] = i; e[2] = j;
                        }
                    }
                }
                in.readLine();
            }

            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[] {s[0], s[1], s[2], 0});
            visited[s[0]][s[1]][s[2]] = true;
            boolean isFound = false;
            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                if (curr[0] == e[0] && curr[1] == e[1] && curr[2] == e[2]) {
                    isFound = true;
                    System.out.printf("Escaped in %d minute(s).\n", curr[3]);
                    break;
                }

                for (int i = 0; i < 6; i++) {
                    int nz = curr[0] + dz[i];
                    int nx = curr[1] + dx[i];
                    int ny = curr[2] + dy[i];
                    if (inRange(nx, ny, nz) && !visited[nz][nx][ny] && field[nz][nx][ny] != '#') {
                        visited[nz][nx][ny] = true;
                        queue.offer(new int[] {nz, nx, ny, curr[3] + 1});
                    }
                }
            }

            if (!isFound) System.out.println("Trapped!");
        }
    }

}