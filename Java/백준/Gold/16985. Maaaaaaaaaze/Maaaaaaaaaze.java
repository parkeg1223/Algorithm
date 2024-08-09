import java.util.*;
import java.io.*;

public class Main {

    /**
     * 1. 0 ~ 4 순서 만들기: 판 쌓는 순서
     * 2. 0 ~ 1023: 회전 모양 만들기
     * 2. field 만들기
     * 3. (0, 0, 0)에서 출발, (4, 4, 4) 도달 가능 여부 찾기
     */

    static int path = Integer.MAX_VALUE;
    static int[][][] field = new int[5][5][5];
    static boolean[] visited = new boolean[5];
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static int[] dx = {-1, 0, 1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};

    private static boolean inRange(int z, int x, int y) {
        return z >= 0 && z < 5 && x >= 0 && x < 5 && y >= 0 && y < 5;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                st = new StringTokenizer(in.readLine());
                for(int k = 0; k < 5; k++) {
                    field[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        int[] stack = new int[5];
        dfs(stack, 0);

        if (path == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(path);
    }

    private static void dfs(int[] arr, int k) {
        if (k == 5) {
            int[][][] tField = new int[5][5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    tField[i][j] = Arrays.copyOf(field[arr[i]][j], 5);
                }
            }

            int[][][] temp;
            for (int i = 0; i < 1024; i++) {
                temp = makeMaze(tField, i);
                path = Math.min(bfs(temp), path);
            }
            return;
        }

        for (int i = 0; i < 5; i++) {
            if (visited[i]) continue;
            arr[k] = i;
            visited[i] = true;
            dfs(arr, k+1);
            visited[i] = false;
        }
    }

    private static int bfs(int[][][] tField) {
        if (tField[0][0][0] == 0 || tField[4][4][4] == 0) {
            return Integer.MAX_VALUE;
        }
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[5][5][5];
        queue.offer(new int[] {0, 0, 0, 0});
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == 4 && curr[1] == 4 && curr[2] == 4) {
//                System.out.println(curr[3]);
                return curr[3];
            }
            for (int i = 0; i < 6; i++) {
                int nz = curr[0] + dz[i];
                int nx = curr[1] + dx[i];
                int ny = curr[2] + dy[i];
                if (inRange(nz, nx, ny) && !visited[nz][nx][ny] && tField[nz][nx][ny] == 1) {
                    visited[nz][nx][ny] = true;
                    queue.offer(new int[] {nz, nx, ny, curr[3] + 1});
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    private static int[][][] makeMaze(int[][][] tField, int k) {
        int[][][] newField = new int[5][5][5];

        String rotateInfo = Integer.toString(k, 4);
        int nLength = rotateInfo.length();
        for (int i = 0; i < 5-nLength; i++) {
            rotateInfo = "0" + rotateInfo;
        }

        for (int i = 0; i < 5; i++) {
            int[][] rotatedField = rotate(tField[i], rotateInfo.charAt(i) - '0');
            for (int j = 0; j < 5; j++) {
                newField[i][j] = Arrays.copyOf(rotatedField[j], 5);
            }
        }

        return newField;
    }

    private static int[][] rotate(int[][] tField, int nRotation) {
        if (nRotation == 0) return tField;
        int[][] newField = new int[5][5];

        if (nRotation == 1) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    newField[i][j] = tField[4-j][i];
                }
            }
        } else if (nRotation == 2) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    newField[i][j] = tField[4-i][4-j];
                }
            }
        } else if (nRotation == 3) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    newField[i][j] = tField[j][4-i];
                }
            }
        }

        return newField;
    }
}