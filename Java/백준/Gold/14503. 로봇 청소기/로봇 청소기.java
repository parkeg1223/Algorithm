import java.util.*;
import java.io.*;

public class Main {

    static int N, M, r, c, d;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] field;
    static boolean[][] clean;

    public static void main(String[] args) throws IOException {
        input();
        int cleaned = 0;
        while (true) {
            if (!clean[r][c]) {
                clean[r][c] = true;
                cleaned++;
            }
            boolean dirty = false;
            for (int i = 0; i < 4; i++) {
                int nx = r + dx[i];
                int ny = c + dy[i];
                if (field[nx][ny] == 0 && !clean[nx][ny]) {
                    dirty = true;
                    break;
                }
            }
            if (dirty) {
                while (true) {
                    d = (d+3) % 4;
                    int nx = r + dx[d];
                    int ny = c + dy[d];
                    if (field[nx][ny] == 0 && !clean[nx][ny]) {
                        r = nx; c = ny;
                        break;
                    }
                }
            } else {
                int nx = r + dx[(d+2)%4];
                int ny = c + dy[(d+2)%4];
                if (field[nx][ny] == 1) break;
                r = nx; c = ny;
            }
        }
        System.out.println(cleaned);
    }

    public static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        field = new int[N][M];
        clean = new boolean[N][M];

        st = new StringTokenizer(in.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}