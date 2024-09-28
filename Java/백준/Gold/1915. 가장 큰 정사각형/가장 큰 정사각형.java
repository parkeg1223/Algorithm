import java.io.*;
import java.util.*;

public class Main {

    static int n, m, maxSide;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dp = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            String s = in.readLine();
            for (int j = 1; j <= s.length(); j++) {
                dp[i][j] = s.charAt(j-1) - '0';
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (dp[i][j] == 1) {
                    dp[i][j] += Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]);
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        System.out.println(maxSide*maxSide);
    }
}