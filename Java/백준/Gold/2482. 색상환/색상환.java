import java.io.*;

public class Main {

    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        K = Integer.parseInt(in.readLine());
        int[][] dp = new int[N+1][K+1];
        dp[0][0] = 1;
        dp[1][0] = 1; dp[1][1] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i][0] = 1;
            dp[i][1] = i;
            for (int j = 2; j <= K; j++) {
               dp[i][j] = (dp[i-2][j-1] + dp[i-1][j]) % 1_000_000_003;
            }
        }
        System.out.println((dp[N-1][K] + dp[N-3][K-1]) % 1_000_000_003);
    }
}