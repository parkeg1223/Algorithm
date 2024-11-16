import java.util.*;
import java.io.*;

public class Main {

    static int N, M, x, y, K;
    static int[][] field;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    // {윗면, 바닥면, 앞면, 뒷면, 왼면, 오른면}
    static int[] dice = new int[6];

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        field = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(in.readLine());
        while (K-- > 0) {
            int dir = Integer.parseInt(st.nextToken()) - 1;
            if (move(dir)) sb.append(dice[0]).append('\n');
        }

        System.out.print(sb);
    }

    private static boolean move(int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if (!inRange(nx, ny)) return false;

        x = nx;
        y = ny;

        int temp = dice[0];
        if (dir == 0) {
            dice[0] = dice[4];
            dice[4] = dice[1];
            dice[1] = dice[5];
            dice[5] = temp;
        } else if (dir == 1) {
            dice[0] = dice[5];
            dice[5] = dice[1];
            dice[1] = dice[4];
            dice[4] = temp;
        } else if (dir == 2) {
            dice[0] = dice[2];
            dice[2] = dice[1];
            dice[1] = dice[3];
            dice[3] = temp;
        } else if (dir == 3) {
            dice[0] = dice[3];
            dice[3] = dice[1];
            dice[1] = dice[2];
            dice[2] = temp;
        }

        if (field[nx][ny] == 0) field[nx][ny] = dice[1];
        else {
            dice[1] = field[nx][ny];
            field[nx][ny] = 0;
        }

        return true;
    }
}