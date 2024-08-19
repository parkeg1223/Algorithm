import java.util.*;
import java.io.*;

public class Main {

    static int N, M, score;
    static int[][] field;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static class BlockGroup implements Comparable<BlockGroup> {
        int size, nRainbow, r, c;
        List<int[]> coordinates;

        public BlockGroup(int size, int nRainbow, int r, int c) {
            this.size = size;
            this.nRainbow = nRainbow;
            this.r = r;
            this.c = c;
            this.coordinates = new ArrayList<>();
        }

        @Override
        public int compareTo(BlockGroup o) {
            if (this.size != o.size) return o.size - this.size;
            if (this.nRainbow != o.nRainbow) return o.nRainbow - this.nRainbow;
            if (this.r != o.r) return o.r - this.r;
            return o.c - this.c;
        }
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        input();
        while (true) {
            BlockGroup group = isBlockGroup();
            if (group.size <= 1) break;
            remove(group);
            gravitate();
            rotate();
            gravitate();
        }

        System.out.println(score);
    }

    public static void remove(BlockGroup group) {
        score += group.size * group.size;
        for (int[] coordinate: group.coordinates) {
            field[coordinate[0]][coordinate[1]] = -2;
        }
    }

    public static void rotate() {
        int[][] newField = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newField[i][j] = field[j][N-1-i];
            }
        }

        for (int i = 0; i < N; i++) {
            field[i] = Arrays.copyOf(newField[i], N);
        }
    }

    public static void gravitate() {
        for (int j = 0; j < N; j++) {
            int curr = N-1;
            for (int i = N-1; i >= 0; i--) {
                if (field[i][j] >= 0) {
                    field[curr--][j] = field[i][j];
                    if (curr+1 != i) field[i][j] = -2;
                } else if (field[i][j] == -1) {
                    curr = i-1;
                }
            }
        }
    }

    public static BlockGroup isBlockGroup() {
        // 블록 크기, 무지개 블록 수, 행, 열
        BlockGroup maxBg = new BlockGroup(0, 0, -1, -1);
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int val = field[i][j];
                if (val <= 0 || visited[i][j]) continue;

                BlockGroup group = new BlockGroup(0, 0, i, j);
                boolean[][] tempVisited = new boolean[N][N];
                Queue<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[] {i, j});
                visited[i][j] = true;

                while (!queue.isEmpty()) {
                    int[] curr = queue.poll();
                    group.size++;
                    if (field[curr[0]][curr[1]] == 0) group.nRainbow++;
                    group.coordinates.add(curr);
                    for (int k = 0; k < 4; k++) {
                        int nx = curr[0] + dx[k];
                        int ny = curr[1] + dy[k];
                        if (inRange(nx, ny) && ((field[nx][ny] == 0 && !tempVisited[nx][ny]) || (field[nx][ny] == val && !visited[nx][ny]))) {
                            if (field[nx][ny] != 0) visited[nx][ny] = true;
                            else tempVisited[nx][ny] = true;
                            queue.offer(new int[] {nx, ny});
                        }
                    }
                }

                if (maxBg.compareTo(group) > 0) maxBg = group;

            }
        }

        return maxBg;
    }

    public static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        field = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}