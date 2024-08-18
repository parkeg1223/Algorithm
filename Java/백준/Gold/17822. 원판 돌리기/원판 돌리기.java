import java.util.*;
import java.io.*;

public class Main {

    static int N, M, T, nNum;
    static List<List<Integer>> nList = new ArrayList<>();
    static List<Command> cList = new ArrayList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static class Command {
        int x, d, k;

        public Command(int x, int d, int k) {
            this.x = x;
            this.d = d;
            this.k = k;
        }
    }

    public static boolean inRange(int x) {
        return x > 0 && x <= N;
    }

    public static void main(String[] args) throws IOException {
        input();

        for (int i = 0; i < T; i++) {
            rotate(cList.get(i));
            postprocess();
        }

        System.out.println(getSum());
    }

    public static void rotate(Command command) {
        int x = command.x;
        int k = command.k;
        int dir = command.d == 0 ? 1 : -1;

        for (int i = x; i <= N; i+=x) {
            Collections.rotate(nList.get(i), dir * k);
        }
    }

    public static void postprocess() {
        boolean[][] visited = new boolean[N+1][M];

        boolean exist = false;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                int n = nList.get(i).get(j);
                if (n == 0) continue;
                Queue<int[]> queue = new ArrayDeque<>();
                queue.add(new int[] {i, j});
                visited[i][j] = true;

                boolean tExist = false;
                while (!queue.isEmpty()) {
                    int[] curr = queue.poll();
                    for (int k = 0; k < 4; k++) {
                        int nx = curr[0] + dx[k];
                        int ny = (curr[1] + dy[k]) % M;
                        if (ny < 0) ny += M;
                        if (inRange(nx) && !visited[nx][ny] && nList.get(nx).
                                get(ny) == n) {
                            exist = true;
                            tExist = true;
                            queue.offer(new int[] {nx, ny});
                            visited[nx][ny] = true;
                            nList.get(nx).set(ny, 0);
                            nNum--;
                        }
                    }
                }

                if (tExist) {
                    nList.get(i).set(j, 0);
                    nNum--;
                }
            }
        }

        if (!exist) {
            double avg = getSum() / (double)nNum;

            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < M; j++) {
                    int n = nList.get(i).get(j);
                    if (n != 0) {
                        if (n > avg) nList.get(i).set(j, n-1);
                        else if (n < avg) nList.get(i).set(j, n+1);
                    }
                }
            }
        }
    }

    public static int getSum() {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                sum += nList.get(i).get(j);
            }
        }
        return sum;
    }

    public static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        nNum = N*M;
        nList.add(new ArrayList<>());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            List<Integer> nums = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                nums.add(Integer.parseInt(st.nextToken()));
            }
            nList.add(nums);
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            cList.add(new Command(x, d, k));
        }
    }
}