import java.io.*;
import java.util.*;

public class Main {

    static int[][] field, score;
    static int[] dice = {1, 6, 5, 2, 4, 3};     // 위, 아래, 앞, 뒤, 좌, 우
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M, K, curX = 1, curY = 1, curDir = 1, answer;

    public static boolean inRange(int x, int y) {
        return x > 0 && x <= N && y > 0 && y <= M;
    }

    public static void main(String[] args) throws IOException {
        input();
        setScore();
        simulate();
        System.out.println(answer);
    }

    public static void setScore() {
        boolean[][] visited = new boolean[N+1][M+1];
        List<int[]> pos;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (visited[i][j]) continue;
                visited[i][j] = true;
                pos = new ArrayList<>();
                int val = field[i][j];
                int tempScore = 0;
                Queue<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[] {i, j});
                while (!queue.isEmpty()) {
                    int[] curr = queue.poll();
                    pos.add(curr);
                    tempScore++;
                    for (int k = 0; k < 4; k++) {
                        int nx = curr[0] + dx[k];
                        int ny = curr[1] + dy[k];
                        if (inRange(nx, ny) && !visited[nx][ny] && field[nx][ny] == val) {
                            visited[nx][ny] = true;
                            queue.offer(new int[] {nx, ny});
                        }
                    }
                }

                for (int[] p: pos) {
                    score[p[0]][p[1]] = tempScore;
                }
            }
        }
    }

    public static void simulate() {
        while (K-- > 0) {
            // 1. 주사위 이동
            curX += dx[curDir];
            curY += dy[curDir];
            moveDice(curDir);

            // 2. 점수 획득
            answer += field[curX][curY] * score[curX][curY];

            // 3. 다음 이동방향 결정
            if (dice[1] > field[curX][curY]) curDir = (curDir + 1) % 4;
            else if (dice[1] < field[curX][curY]) curDir = (curDir + 3) % 4;

            if (!inRange(curX+dx[curDir], curY+dy[curDir])) curDir ^= 2;
        }
    }

    public static void moveDice(int dir) {
        int temp;
        if (dir == 0) {
            temp = dice[0];
            dice[0] = dice[2];
            dice[2] = dice[1];
            dice[1] = dice[3];
            dice[3] = temp;
        } else if (dir == 1) {
            temp = dice[0];
            dice[0] = dice[4];
            dice[4] = dice[1];
            dice[1] = dice[5];
            dice[5] = temp;
        } else if (dir == 2) {
            temp = dice[0];
            dice[0] = dice[3];
            dice[3] = dice[1];
            dice[1] = dice[2];
            dice[2] = temp;
        } else if (dir == 3) {
            temp = dice[0];
            dice[0] = dice[5];
            dice[5] = dice[1];
            dice[1] = dice[4];
            dice[4] = temp;
        }
    }

    public static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        field = new int[N+1][M+1];
        score = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 1; j <= M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}