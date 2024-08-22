import java.util.*;
import java.io.*;

public class Main {

    static int N, M, D, nEnemy, maxKill;
    static int[][] field, tempField;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] arr;
    static boolean[] visited;

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public static void main(String[] args) throws IOException {
        input();
        dfs(0, 0);
        System.out.println(maxKill);
    }

    public static void dfs(int n, int step) {
        if (step == 3) {
            simulate();
            return;
        }

        for (int i = n; i < M; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[step] = i;
                dfs(i+1, step+1);
                visited[i] = false;
            }
        }
    }

    public static void simulate() {
        int enemy = nEnemy;
        int kill = 0;

        for (int i = 0; i < N; i++) {
            tempField[i] = Arrays.copyOf(field[i], M);
        }

        int tempResult = 0;
        while (enemy > 0) {
            tempResult = shoot();
            kill += tempResult;
            enemy -= tempResult;
            enemy -= down();
//            print();
        }

        maxKill = Math.max(maxKill, kill);
    }

    public static void print() {
        System.out.println("------------ map print ---------------");
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(tempField[i]));
        }
    }

    public static int shoot() {
        int score = 0;
        List<int[]> eList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            int[] max = null;
            boolean[][] visited = new boolean[N][M];
            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[] {N-1, arr[i], 1});

            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                if (max != null && max[2] < curr[2]) break;
                if (tempField[curr[0]][curr[1]] == 1) {
                    if (max == null || max[1] > curr[1]) {
                        max = Arrays.copyOf(curr, 3);
                    }
                }

                for (int j = 0; j < 4; j++) {
                    int nx = curr[0] + dx[j];
                    int ny = curr[1] + dy[j];
                    if (inRange(nx, ny) && !visited[nx][ny] && curr[2] < D) {
                        visited[nx][ny] = true;
                        queue.offer(new int[] {nx, ny, curr[2] + 1});
                    }
                }
            }

            if (max == null) continue;
            eList.add(new int[] {max[0], max[1]});
        }

        for (int[] enemy: eList) {
            if (tempField[enemy[0]][enemy[1]] == 1) score++;
            tempField[enemy[0]][enemy[1]] = 0;
        }

        return score;
    }

    public static int down() {
        int removed = 0;
        for (int j = 0; j < M; j++) {
            if (tempField[N-1][j] == 1) removed++;
        }

        for (int i = N-1; i >= 1; i--) {
            tempField[i] = Arrays.copyOf(tempField[i-1], M);
        }

        Arrays.fill(tempField[0], 0);

        return removed;
    }

    public static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        field = new int[N][M];
        tempField = new int[N][M];
        visited = new boolean[M];
        arr = new int[3];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
                if (field[i][j] == 1) nEnemy++;
            }
        }
    }
}