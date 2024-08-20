import java.util.*;
import java.io.*;

public class Main {

    static int N, M, gas, tX, tY, nPas;
    static int[][] field;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Passenger[] ps;

    public static class Passenger implements Comparable<Passenger> {
        int sX, sY, eX, eY, dist;

        public Passenger(int sX, int sY, int eX, int eY) {
            this.sX = sX;
            this.sY = sY;
            this.eX = eX;
            this.eY = eY;
            this.dist = 0;
        }

        @Override
        public int compareTo(Passenger o) {
            if (this.sX != o.sX) return this.sX - o.sX;
            return this.sY - o.sY;
        }

        @Override
        public String toString() {
            return "Passenger{" +
                    "sX=" + sX +
                    ", sY=" + sY +
                    ", eX=" + eX +
                    ", eY=" + eY +
                    ", dist=" + dist +
                    '}';
        }
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        input();

        if (simulate()) System.out.println(gas);
        else System.out.println(-1);
    }

    public static boolean simulate() {

        while (nPas > 0) {
            Passenger nearest = null;
            int minDist = Integer.MAX_VALUE;
            Queue<int[]> queue = new ArrayDeque<>();

            boolean[][] visited = new boolean[N][N];
            queue.offer(new int[] {tX, tY, 0});
            visited[tX][tY] = true;

            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                if (curr[2] > minDist) break;
                if (field[curr[0]][curr[1]] > 0) {
                    minDist = curr[2];
                    if (nearest == null || nearest.compareTo(ps[field[curr[0]][curr[1]]]) > 0) {
                        nearest = ps[field[curr[0]][curr[1]]];
                        nearest.dist = curr[2];
                    }
                }
                for (int i = 0; i < 4; i++) {
                    int nx = curr[0] + dx[i];
                    int ny = curr[1] + dy[i];
                    if (inRange(nx, ny) && !visited[nx][ny] && field[nx][ny] != -1) {
                        visited[nx][ny] = true;
                        queue.offer(new int[] {nx, ny, curr[2]+1});
                    }
                }
            }

            if (nearest == null || (gas -= nearest.dist) < 0) return false;

            queue.clear();
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
            }

            queue.offer(new int[] {nearest.sX, nearest.sY, 0});
            visited[nearest.sX][nearest.sY] = true;
            boolean found = false;
            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                if (curr[0] == nearest.eX && curr[1] == nearest.eY) {
                    nearest.dist = curr[2];
                    found = true;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = curr[0] + dx[i];
                    int ny = curr[1] + dy[i];
                    if (inRange(nx, ny) && !visited[nx][ny] && field[nx][ny] != -1) {
                        visited[nx][ny] = true;
                        queue.offer(new int[] {nx, ny, curr[2]+1});
                    }
                }
            }

            if (!found || (gas -= nearest.dist) < 0) return false;
            gas += (2 * nearest.dist);
            field[nearest.sX][nearest.sY] = 0;
            nPas--;
            tX = nearest.eX;
            tY = nearest.eY;
        }

        return nPas == 0;
    }

    public static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        gas = Integer.parseInt(st.nextToken());

        field = new int[N][N];
        ps = new Passenger[M+1];
        nPas = M;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken()) * (-1);
            }
        }

        st = new StringTokenizer(in.readLine());
        tX = Integer.parseInt(st.nextToken()) - 1;
        tY = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(in.readLine());
            int sX = Integer.parseInt(st.nextToken()) - 1;
            int sY = Integer.parseInt(st.nextToken()) - 1;
            int eX = Integer.parseInt(st.nextToken()) - 1;
            int eY = Integer.parseInt(st.nextToken()) - 1;
            field[sX][sY] = i;
            ps[i] = new Passenger(sX, sY, eX, eY);
        }
    }
}