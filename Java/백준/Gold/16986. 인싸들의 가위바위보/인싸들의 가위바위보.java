import java.util.*;
import java.io.*;

public class Main {

    static int N, K;
    static boolean isWin;
    static int[][] relation;
    static int[] oppIdx = new int[3];
    static int[][] oppInfo = new int[3][20];

    public static void main(String[] args) throws IOException {
        input();

        if (N < K) {
            System.out.println(0);
            return;
        }

        dfs(0, 1, 0, new int[3]);

        if (isWin) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static int check(int[] winInfo) {
        for (int i = 0; i < 3; i++) {
            if (winInfo[i] == K) return i;
        }
        return -1;
    }

    private static void dfs(int m1, int m2, int action, int[] winInfo) {
        if (isWin) return;

        int winner = check(winInfo);
        if (winner != -1) {
            if (winner == 0) isWin = true;
            return;
        }

        int max = Math.max(m1, m2);
        int min = Math.min(m1, m2);

        if (min == 0) {
            // (0, 1), (0, 2)
            for (int i = 0; i < N; i++) {
                if ((action & (1 << i)) == 0) {
                    int newAction = action | (1 << i);
                    if (relation[i][oppInfo[max][oppIdx[max]]] == 2) {
                        // 지우가 이김
                        winInfo[0]++;
                        oppIdx[max]++;
                        dfs(0, 3 - max, newAction, winInfo);
                        oppIdx[max]--;
                        winInfo[0]--;
                    } else {
                        // 지우가 짐
                        winInfo[max]++;
                        oppIdx[max]++;
                        dfs(1, 2, newAction, winInfo);
                        oppIdx[max]--;
                        winInfo[max]--;
                    }
                }
            }
        } else {
            // (1, 2)
            if (relation[oppInfo[1][oppIdx[1]]][oppInfo[2][oppIdx[2]]] == 2) {
                // 경희가 이김
                winInfo[1]++;
                oppIdx[1]++; oppIdx[2]++;
                dfs(0, 1, action, winInfo);
                oppIdx[1]--; oppIdx[2]--;
                winInfo[1]--;
            } else {
                // 민호가 이김
                winInfo[2]++;
                oppIdx[1]++; oppIdx[2]++;
                dfs(0, 2, action, winInfo);
                oppIdx[1]--; oppIdx[2]--;
                winInfo[2]--;
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        relation = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                relation[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < 3; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < 20; j++) {
                oppInfo[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }
    }
}