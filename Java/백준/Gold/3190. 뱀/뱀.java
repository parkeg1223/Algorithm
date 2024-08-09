import java.util.*;
import java.io.*;

public class Main {

    /**
     * 1. 머리 이동한 좌표가 벽 or 자기 자신이면 종료
     * 2. 머리 좌표에 사과 있으면 사과 없애고 이동 종료
     * 3. 사과 없으면 꼬리 비우기
     */

    static int N, K, L, sDir = 1;
    static int[][] field;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static ArrayDeque<int[]> snake = new ArrayDeque<>();
    static ArrayDeque<Command> commands = new ArrayDeque<>();

    static class Command {
        int x;
        char d;

        Command(int x, char d) {
            this.x = x;
            this.d = d;
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        input();
        field[0][0] = 1;
        snake.offer(new int[]{0, 0});
        int t = 1;
        while (move(t)) t++;

        System.out.println(t);
    }

    private static boolean move(int t) {
        int[] curr = snake.peek();
        int nx = curr[0] + dx[sDir];
        int ny = curr[1] + dy[sDir];
        if (!inRange(nx, ny) || field[nx][ny] == 1) return false;
        snake.offerFirst(new int[] {nx, ny});
        if (field[nx][ny] == 0) {
            int[] tail = snake.pollLast();
            field[tail[0]][tail[1]] = 0;
        }
        field[nx][ny] = 1;

        if (!commands.isEmpty() && commands.peek().x == t) {
            Command command = commands.poll();
            if (command.d == 'L') sDir = (sDir + 3) % 4;
            else if (command.d == 'D') sDir = (sDir + 1) % 4;
        }
        return true;
    }

    private static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        field = new int[N][N];

        K = Integer.parseInt(in.readLine());
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            field[r][c] = 2;
        }

        L = Integer.parseInt(in.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);
            commands.offer(new Command(x, d));
        }

    }
}