import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] dp, field;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int tc = 1; ; tc++) {
            N = Integer.parseInt(in.readLine());
            if (N == 0) break;
            dp = new int[N+1][3];
            field = new int[N+1][3];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < 3; j++) {
                    field[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[1][0] = 1001;
            dp[1][1] = field[1][1];
            dp[1][2] = dp[1][1] + field[1][2];
            for (int i = 2; i <= N; i++) {
                dp[i][0] = field[i][0] + Math.min(dp[i-1][0], dp[i-1][1]);
                dp[i][1] = field[i][1] + Math.min(Math.min(Math.min(dp[i][0], dp[i-1][0]), dp[i-1][1]), dp[i-1][2]);
                dp[i][2] = field[i][2] + Math.min(Math.min(dp[i][1], dp[i-1][1]), dp[i-1][2]);
            }
            sb.append(tc).append(". ").append(dp[N][1]).append("\n");
        }
        System.out.print(sb);
    }
}