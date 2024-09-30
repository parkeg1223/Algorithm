import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[][] num = new int[N+1][3];
        int[][] dp;
        int answer = Integer.MAX_VALUE;
        StringTokenizer st;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(in.readLine());
            num[i][0] = Integer.parseInt(st.nextToken());
            num[i][1] = Integer.parseInt(st.nextToken());
            num[i][2] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 3; i++) {
            dp = new int[N+1][3];
            for (int j = 0; j < 3; j++) {
                if (i == j) dp[1][j] = num[1][j];
                else dp[1][j] = 1001;
            }

            for (int j = 2; j <= N; j++) {
                dp[j][0] = Math.min(dp[j-1][1], dp[j-1][2]) + num[j][0];
                dp[j][1] = Math.min(dp[j-1][0], dp[j-1][2]) + num[j][1];
                dp[j][2] = Math.min(dp[j-1][0], dp[j-1][1]) + num[j][2];
            }

            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    answer = Math.min(answer, dp[N][j]);
                }
            }
        }
        System.out.println(answer);
    }
}