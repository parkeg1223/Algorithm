import java.io.*;
import java.util.*;

public class Main {

    static int[][] field = new int[5][5];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[] visited = new boolean[1<<25];
    static int answer;

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < 5 && y >= 0 && y < 5;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String s = in.readLine();
            for (int j = 0; j < 5; j++) {
                if (s.charAt(j) == 'S') field[i][j] = 1;
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                dfs(1<<(i*5+j),  field[i][j], 1);
            }
        }

        System.out.println(answer);
    }

    public static void dfs(int state, int nS, int nMem) {
        visited[state] = true;
        if (nMem == 7) {
            if (nS >= 4) answer++;
            return;
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ((state & (1<<(i*5+j))) == 0) continue;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (!inRange(nx, ny) || (state & (1<<(nx*5+ny))) > 0) continue;
                    int next = state | (1<<(nx*5+ny));
                    if (visited[next]) continue;
                    dfs(next, nS+field[nx][ny], nMem+1);
                }
            }
        }
    }
}