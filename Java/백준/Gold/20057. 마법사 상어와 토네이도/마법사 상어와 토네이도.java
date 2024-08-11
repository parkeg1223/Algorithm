import java.util.*;
import java.io.*;

public class Main {

    static int N, dir = 3, totalSand, currX, currY;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] field;

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        input();
        simulate();
        System.out.println(totalSand - calcSum());
    }

    private static void simulate() {
        int step = 0;
        currX = N/2; currY = N/2;
        do {
            step++;
            // 1회 + 1회 이동
            blow(step);
            blow(step);
        } while (step < N - 1);
        blow(step);
    }

    private static void blow(int step) {
        while (step-- > 0) {

            currX += dx[dir];
            currY += dy[dir];

            int nx = currX - dx[dir] + dx[(dir + 1) % 4];
            int ny = currY - dy[dir] + dy[(dir + 1) % 4];
            int amount = field[currX][currY] / 100;
            if (inRange(nx, ny)) field[nx][ny] += amount;

            nx = currX - dx[dir] + dx[(dir + 3) % 4];
            ny = currY - dy[dir] + dy[(dir + 3) % 4];
            if (inRange(nx, ny)) field[nx][ny] += amount;

            int total = field[currX][currY];
            total -= (2 * amount);

            nx = currX + 2 * dx[dir];
            ny = currY + 2 * dy[dir];
            amount = field[currX][currY] * 5 / 100;
            if (inRange(nx, ny)) field[nx][ny] += amount;
            total -= amount;

            nx = currX + dx[dir] + dx[(dir + 1) % 4];
            ny = currY + dy[dir] + dy[(dir + 1) % 4];
            amount = field[currX][currY] / 10;
            if (inRange(nx, ny)) field[nx][ny] += amount;

            nx = currX + dx[dir] + dx[(dir + 3) % 4];
            ny = currY + dy[dir] + dy[(dir + 3) % 4];
            if (inRange(nx, ny)) field[nx][ny] += amount;
            total -= (2 * amount);

            nx = currX + dx[(dir + 1) % 4];
            ny = currY + dy[(dir + 1) % 4];
            amount = field[currX][currY] * 7 / 100;
            if (inRange(nx, ny)) field[nx][ny] += amount;

            nx = currX + dx[(dir + 3) % 4];
            ny = currY + dy[(dir + 3) % 4];
            if (inRange(nx, ny)) field[nx][ny] += amount;
            total -= (2 * amount);

            nx = currX + 2 * dx[(dir + 1) % 4];
            ny = currY + 2 * dy[(dir + 1) % 4];
            amount = field[currX][currY] / 50;
            if (inRange(nx, ny)) field[nx][ny] += amount;

            nx = currX + 2 * dx[(dir + 3) % 4];
            ny = currY + 2 * dy[(dir + 3) % 4];
            if (inRange(nx, ny)) field[nx][ny] += amount;
            total -= (2 * amount);

            nx = currX + dx[dir];
            ny = currY + dy[dir];
            if (inRange(nx, ny)) field[nx][ny] += total;

            field[currX][currY] = 0;
        }

        // 다음 방향 회전
        dir = (dir+3) % 4;
    }

    private static int calcSum() {
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                total += field[i][j];
            }
        }

        return total;
    }

    private static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        field = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
                totalSand += field[i][j];
            }
        }
    }
}