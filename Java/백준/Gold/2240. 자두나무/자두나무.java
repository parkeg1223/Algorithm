import java.io.*;
import java.util.*;

public class Main {

    static int[][][] ans;
    static int[] pInfo;
    static int T, W;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        ans = new int[T+1][W+1][2];
        pInfo = new int[T+1];

        for (int i = 1; i <= T; i++) {
            pInfo[i] = Integer.parseInt(in.readLine())-1;
        }

        if (pInfo[1] == 0) ans[1][W][0] = 1;
        else ans[1][W-1][1] = 1;

        for (int i = 2; i <= T; i++) {
            for (int j = Math.max(0, W-i+1); j < W; j++) {
                // 받은 경우
                ans[i][j][pInfo[i]] = Math.max(ans[i-1][j][pInfo[i]] + 1, ans[i-1][j+1][(pInfo[i]+1)%2] + 1);        // 그대로 있어서 받음

                // 못 받은 경우
                ans[i][j][(pInfo[i]+1)%2] = Math.max(ans[i-1][j][(pInfo[i]+1)%2], ans[i-1][j+1][pInfo[i]]);    // 그대로 있어서 못받음
            }
            ans[i][W][pInfo[i]] = ans[i-1][W][pInfo[i]] + 1;
            ans[i][W][(pInfo[i]+1)%2] = ans[i-1][W][(pInfo[i]+1)%2];

        }

        int max = 0;
        for (int i = 0; i <= W; i++) {
            for (int j = 0; j < 2; j++) {
                max = Math.max(max, ans[T][i][j]);

            }
        }
        System.out.println(max);
    }
}