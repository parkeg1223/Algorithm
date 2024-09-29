import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static boolean[][] dp;
    static int[] num;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(in.readLine());
        dp = new boolean[N+1][N+1];
        num = new int[N+1];

        StringTokenizer st = new StringTokenizer(in.readLine());
        int prev = 0;
        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
            if (prev == num[i]) dp[i-1][i] = true;
            prev = num[i];
        }

        for (int i = 1; i <= N; i++) {
            dp[i][i] = true;
        }

        for (int i = 2; i < N; i++) {
            for (int j = 1; j+i <= N; j++) {
                dp[j][j+i] = dp[j+1][j+i-1] && (num[j] == num[j+i]);
            }
        }

        M = Integer.parseInt(in.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            sb.append(dp[S][E] ? 1 : 0).append("\n");
        }
        System.out.print(sb);
    }
}